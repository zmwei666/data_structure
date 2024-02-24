package com.mj.graph;

import com.mj.MinHeap;
import com.mj.UnionFind;

import java.util.*;
import java.util.Map.Entry;

@SuppressWarnings("unchecked")
public class ListGraph<V, E> extends Graph<V, E> {
	public ListGraph() {}
	public ListGraph(WeightManager<E> weightManager) {
		super(weightManager);
	}

	private static class Vertex<V, E> {  // 顶点
		V value;
		Set<Edge<V, E>> inEdges = new HashSet<>();
		Set<Edge<V, E>> outEdges = new HashSet<>();
		Vertex(V value) {
			this.value = value;
		}
		@Override
		public boolean equals(Object obj) {
			return Objects.equals(value, ((Vertex<V, E>)obj).value);
		}
		@Override
		public int hashCode() {
			return value == null ? 0 : value.hashCode();
		}
		@Override
		public String toString() {
			return value == null ? "null" : value.toString();
		}
	}
	
	private static class Edge<V, E> { // 边
		Vertex<V, E> from;
		Vertex<V, E> to;
		E weight;
		
		Edge(Vertex<V, E> from, Vertex<V, E> to) {
			this.from = from;
			this.to = to;
		}
		
		EdgeInfo<V, E> info() {
			return new EdgeInfo<>(from.value, to.value, weight);
		}
		
		@Override
		public boolean equals(Object obj) {
			Edge<V, E> edge = (Edge<V, E>) obj;
			return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
		}
		@Override
		public int hashCode() {
			return from.hashCode() * 31 + to.hashCode();
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
	}
	
	private Map<V, Vertex<V, E>> vertices = new HashMap<>();
	private Set<Edge<V, E>> edges = new HashSet<>();

	// 自定义的比较规则 专门比较边的 边是 weight  写法相当于  private Comparator<Edge<V, E>> edgeComparator = new Comparator<Edge<V, E>>() { ... }
	private Comparator<Edge<V, E>> edgeComparator = (Edge<V, E> e1, Edge<V, E> e2) -> {
		return weightManager.compare(e1.weight, e2.weight);
	};
	
	public void print() {
		System.out.println("[顶点]-------------------");
		vertices.forEach((V v, Vertex<V, E> vertex) -> {
			System.out.println(v);
			System.out.println("out-----------");
			System.out.println(vertex.outEdges);
			System.out.println("in-----------");
			System.out.println(vertex.inEdges);
		});

		System.out.println("[边]-------------------");
		edges.forEach((Edge<V, E> edge) -> {
			System.out.println(edge);
		});
	}

	@Override
	public int edgesSize() {
		return edges.size();
	}

	@Override
	public int verticesSize() {
		return vertices.size();
	}

	@Override
	public void addVertex(V v) {
		if (vertices.containsKey(v)) return;
		vertices.put(v, new Vertex<>(v));
	}

	@Override
	public void addEdge(V from, V to) {
		addEdge(from, to, null);
	}

	@Override
	public void addEdge(V from, V to, E weight) {
		Vertex<V, E> fromVertex = vertices.get(from);  // 从顶点里找from
		if (fromVertex == null) {  // 如果没有, 就创建一个
			fromVertex = new Vertex<>(from);
			vertices.put(from, fromVertex);  // 放到顶点里
		}
		
		Vertex<V, E> toVertex = vertices.get(to);
		if (toVertex == null) {
			toVertex = new Vertex<>(to);
			vertices.put(to, toVertex);
		}

		Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
		edge.weight = weight;

		// 为什么不先判断是否存在，然后get这个元素，然后替换
		// 因为HashSet没有get方法，set只存储key，不存储value
		if (fromVertex.outEdges.remove(edge)) {  // 如果删除成功, 就说明存在, 那就把to也删掉
			toVertex.inEdges.remove(edge);
			edges.remove(edge);
		}
		fromVertex.outEdges.add(edge);
		toVertex.inEdges.add(edge);
		edges.add(edge);
	}

	@Override
	public void removeEdge(V from, V to) {
		// 确保两个定点都存在
		Vertex<V, E> fromVertex = vertices.get(from);
		if (fromVertex == null) return;
		
		Vertex<V, E> toVertex = vertices.get(to);
		if (toVertex == null) return;
		
		Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
		if (fromVertex.outEdges.remove(edge)) {
			toVertex.inEdges.remove(edge);
			edges.remove(edge);
		}
	}

	/**
	 * 删除定点
	 * <br>
	 * <img src="https://gitee.com/codetheory/img-on1/raw/master/img01/1706497846712-2024-1-2911:10:46.png"  />
	 */
	@Override
	public void removeVertex(V v) {
		Vertex<V, E> vertex = vertices.remove(v);  // 先删, 如果有, remove会返回删除的value
		if (vertex == null) return;  // 如果为null, 那就说明不存在, 直接return

		// 这里为什么使用迭代器, 因为这里需要边遍历, 边删除
		// 删除 outEdges  比如删除 V0 6
		for (Iterator<Edge<V, E>> iterator = vertex.outEdges.iterator(); iterator.hasNext();) {
			Edge<V, E> edge = iterator.next();  // 拿到6这条边 V0 -> V4
			edge.to.inEdges.remove(edge);  // edge.to 是 V4  V4的inEdges是所有指向V4的边  然后remove
			// 将当前遍历到的元素edge从集合vertex.outEdges中删掉
			iterator.remove();  // 删除V0的outEdge  -> 6  V0 -> V4
			edges.remove(edge);
		}

		for (Iterator<Edge<V, E>> iterator = vertex.inEdges.iterator(); iterator.hasNext();) {
			Edge<V, E> edge = iterator.next();
			edge.from.outEdges.remove(edge);
			// 将当前遍历到的元素edge从集合vertex.inEdges中删掉
			iterator.remove(); 
			edges.remove(edge);
		}
	}

	/**
	 * 广度优先搜索 会有一些节点访问不到
	 * @param begin 起点
	 * <br/>
	 * <img src="https://gitee.com/codetheory/img-on1/raw/master/img01/1706502804314-2024-1-2912:33:24.png"  />
	 */
	@Override
	public void bfs(V begin, VertexVisitor<V> visitor) {
		if (visitor == null) return;
		Vertex<V, E> beginVertex = vertices.get(begin);
		if (beginVertex == null) return;
		
		Set<Vertex<V, E>> visitedVertices = new HashSet<>();  // 用来存放访问过的节点
		Queue<Vertex<V, E>> queue = new LinkedList<>();
		queue.offer(beginVertex);
		visitedVertices.add(beginVertex);
		
		while (!queue.isEmpty()) {
			Vertex<V, E> vertex = queue.poll();
			if (visitor.visit(vertex.value)) return;
			
			for (Edge<V, E> edge : vertex.outEdges) {
				if (visitedVertices.contains(edge.to)) continue;
				queue.offer(edge.to);
				visitedVertices.add(edge.to);  // 这一行一定要有, 不然有环会出现bug, 比如遍历0, 添加1, 4, 然后遍历1, 又加4 , 此时队列里就有两个4了
			}
		}
	}

	/**
	 * 深度优先搜索 会有一些节点访问不到
	 * @param begin 起点
	 * <br/>
	 * <img src="https://gitee.com/codetheory/img-on1/raw/master/img01/1706509104903-2024-1-2914:18:25.png"  />
	 */
	@Override
	public void dfs(V begin, VertexVisitor<V> visitor) {
		if (visitor == null) return;
		Vertex<V, E> beginVertex = vertices.get(begin);
		if (beginVertex == null) return;

		Set<Vertex<V, E>> visitedVertices = new HashSet<>();  // 存放访问过的元素
		Stack<Vertex<V, E>> stack = new Stack<>();
		
		// 先访问起点
		stack.push(beginVertex);
		visitedVertices.add(beginVertex);
		if (visitor.visit(begin)) return;
		
		while (!stack.isEmpty()) {
			Vertex<V, E> vertex = stack.pop();
			
			for (Edge<V, E> edge : vertex.outEdges) {  // 访问定点的outEdges,
				if (visitedVertices.contains(edge.to)) continue;  // 如果访问这条边, 那么就换下一条边

				// 访问到这里, 说明这条边没有访问过, 那么就把这条边的from和to都放到栈里
				stack.push(edge.from);
				stack.push(edge.to);
				visitedVertices.add(edge.to);
				if (visitor.visit(edge.to.value)) return;
				
				break; // 然后退出循环, 这里就是要一直沿着这条边访问到末尾, 而不是把定点的所有边全放入栈
			}
		}
	}

	/**
	 * 拓扑排序 必须为有向无环图
	 * <br/>
	 * <img src="https://gitee.com/codetheory/img-on1/raw/master/img01/1706512572837-2024-1-2915:16:12.png"  />
	 * C - E 初始度就为0, 则直接进入队列, 不放入入度表
	 */
	@Override
	public List<V> topologicalSort() {
		List<V> list = new ArrayList<>();  // 存放排序的结果
		Queue<Vertex<V, E>> queue = new LinkedList<>();  // 存放度为0的节点
		Map<Vertex<V, E>, Integer> ins = new HashMap<>();  // 入度表, 存放所有节点的入度
		// 初始化（将度为0的节点都放入队列）
		vertices.forEach((V v, Vertex<V, E> vertex) -> {
			int in = vertex.inEdges.size();
			if (in == 0) {
				queue.offer(vertex);  // 如果度为0, 则放入队列
			} else {
				ins.put(vertex, in);  // 否则, 就放入入度表
			}
		});
		
		while (!queue.isEmpty()) {
			Vertex<V, E> vertex = queue.poll();
			// 放入返回结果中
			list.add(vertex.value);
			
			for (Edge<V, E> edge : vertex.outEdges) {
				int toIn = ins.get(edge.to) - 1;  // 入度表 当前顶点的to的度数 -1
				if (toIn == 0) {  // 减完后, 如果度数为0, 就放入队列
					queue.offer(edge.to);
				} else {  // 否则, 就更新入度表
					ins.put(edge.to, toIn);
				}
			}
		}
		
		return list;
	}

	/**
	 * 最小生成树
	 * @return 分别是两种算法返回
	 */
	@Override
	public Set<EdgeInfo<V, E>> mst() {
		return Math.random() > 0.5 ? prim() : kruskal();
	}


	/**
	 * 流程:
	 * 就是想随便拿到一个顶点(因为是set) -> 把他放入已经切过的顶点里面(变为了绿色) -> 把他的所有的out边都放入小顶堆里面
	 * -> 然后从小顶堆里面拿出最小的边, 放入最小边集, 相当与是选中这条边了 -> 然后再把这条边的to顶点放入已经切过的顶点里面
	 * -> 然后再把这个to顶点的out边放入堆里面 -> 重复上面的步骤, 直到堆为空, 或者已经切过的顶点的个数等于所有顶点的个数
	 * <br/>
	 * <img src="https://gitee.com/codetheory/img-on1/raw/master/img01/1706585197303-2024-1-3011:26:38.png"  />
	 */
	private Set<EdgeInfo<V, E>> prim() {
		Iterator<Vertex<V, E>> it = vertices.values().iterator();  // 所有顶点
		if (!it.hasNext()) return null;
		Vertex<V, E> vertex = it.next();  // 随便拿到一个顶点
		Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();  // 存放最小边集
		Set<Vertex<V, E>> addedVertices = new HashSet<>();  // 存放已经切过的顶点
		addedVertices.add(vertex);
		MinHeap<Edge<V, E>> heap = new MinHeap<>(vertex.outEdges, edgeComparator);  // vertex.outEdges 就是顶点 出去的边
		int verticesSize = vertices.size();  // 循环次数 = 边的个数 = 顶点 - 1 = 已经切过的顶点个数
		while (!heap.isEmpty() && addedVertices.size() < verticesSize) {  // 堆不为空, 并且切过的顶点的个数小于所有顶点的个数
			Edge<V, E> edge = heap.remove();  // 拿出最小的边
			if (addedVertices.contains(edge.to)) continue;  // 如果这条边的to顶点已经切过了, 就进入下一个循环
			edgeInfos.add(edge.info());  // 否则, 就把这条边放入最小边集
			addedVertices.add(edge.to);  // 把这条边的to顶点放入已经切过的顶点里面
			heap.addAll(edge.to.outEdges);  // 把这个to顶点的out边全部放入堆里面, 但是因为这里是无向边, 所以这里的outEdges包含edge, 所以又会加一遍, 所以需要上面那个continue
		}
		return edgeInfos;  // 返回最小边集
	}
	// 假设是无向的
	private Set<EdgeInfo<V, E>> kruskal() {
		int edgeSize = vertices.size() - 1;
		if (edgeSize == -1) return null;  // 如果一个顶点都没有的话，返回null
		// 存放边的信息
		Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
		// 最小堆
		MinHeap<Edge<V, E>> heap = new MinHeap<>(edges, edgeComparator);
		// 创建一个并查集
		UnionFind<Vertex<V, E>> uf = new UnionFind<>();
		// 将所有的顶点都添加到并查集中  初始化并查集 所有的顶点都单独作为一个集合
		vertices.forEach((V v, Vertex<V, E> vertex) -> {
			uf.makeSet(vertex);
		});
		while (!heap.isEmpty() && edgeInfos.size() < edgeSize) {
			Edge<V, E> edge = heap.remove();
			// 如果边的from 和 to 都在同一集合里面的话, 则说明不能链接, 否则会形成环
			if (uf.isSame(edge.from, edge.to)) {
                continue;
            }
			edgeInfos.add(edge.info());
			// 将两个顶点归为一个集合
			uf.union(edge.from, edge.to);
		}
		return edgeInfos;
	}

//	public Map<V, E> shortestPath(V begin) {
//		Vertex<V, E> beginVertex = vertices.get(begin);
//		if (beginVertex == null) return null;
//		
//		Map<V, E> selectedPaths = new HashMap<>();
//		Map<Vertex<V, E>, E> paths = new HashMap<>();
//		// 初始化paths
//		for (Edge<V, E> edge : beginVertex.outEdges) {
//			paths.put(edge.to, edge.weight);
//		}
//
//		while (!paths.isEmpty()) {
//			Entry<Vertex<V, E>, E> minEntry = getMinPath(paths);
//			// minVertex离开桌面
//			Vertex<V, E> minVertex = minEntry.getKey();
//			selectedPaths.put(minVertex.value, minEntry.getValue());
//			paths.remove(minVertex);
//			// 对它的minVertex的outEdges进行松弛操作
//			for (Edge<V, E> edge : minVertex.outEdges) {
//				// 如果edge.to已经离开桌面，就没必要进行松弛操作
//				if (selectedPaths.containsKey(edge.to.value)) continue;
//				// 新的可选择的最短路径：beginVertex到edge.from的最短路径 + edge.weight
//				E newWeight = weightManager.add(minEntry.getValue(), edge.weight);
//				// 以前的最短路径：beginVertex到edge.to的最短路径
//				E oldWeight = paths.get(edge.to);
//				if (oldWeight == null || weightManager.compare(newWeight, oldWeight) < 0) {
//					paths.put(edge.to, newWeight);
//				}
//			}
//		}
//		
//		selectedPaths.remove(begin);
//		return selectedPaths;
//	}
	
	@Override
	public Map<V, PathInfo<V, E>> shortestPath(V begin) {
		return dijkstra(begin);
	}

	@SuppressWarnings("unused")
	private Map<V, PathInfo<V, E>> bellmanFord(V begin) {
		Vertex<V, E> beginVertex = vertices.get(begin);
		if (beginVertex == null) return null;
		
		Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
		selectedPaths.put(begin, new PathInfo<>(weightManager.zero()));
		
		int count = vertices.size() - 1;
		for (int i = 0; i < count; i++) { // v - 1 次
			for (Edge<V, E> edge : edges) {
				PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
				if (fromPath == null) continue;
				relax(edge, fromPath, selectedPaths);
			}
		}
		
		for (Edge<V, E> edge : edges) {
			PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
			if (fromPath == null) continue;
			if (relax(edge, fromPath, selectedPaths)) {
				System.out.println("有负权环");
				return null;
			}
		}
		
		selectedPaths.remove(begin);
		return selectedPaths;
	}

	
	/**
	 * 松弛
	 * @param edge 需要进行松弛的边
	 * @param fromPath edge的from的最短路径信息
	 * @param paths 存放着其他点（对于dijkstra来说，就是还没有离开桌面的点）的最短路径信息
	 */
	private boolean relax(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<V, PathInfo<V, E>> paths) {
		// 新的可选择的最短路径：beginVertex到edge.from的最短路径 + edge.weight
		E newWeight = weightManager.add(fromPath.weight, edge.weight);
		// 以前的最短路径：beginVertex到edge.to的最短路径
		PathInfo<V, E> oldPath = paths.get(edge.to.value);
		if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) return false;
		
		if (oldPath == null) {
			oldPath = new PathInfo<>();
			paths.put(edge.to.value, oldPath);
		} else {
			oldPath.edgeInfos.clear();
		}

		oldPath.weight = newWeight;
		oldPath.edgeInfos.addAll(fromPath.edgeInfos);
		oldPath.edgeInfos.add(edge.info());
		
		return true;
	}
	
	private Map<V, PathInfo<V, E>> dijkstra(V begin) {
		Vertex<V, E> beginVertex = vertices.get(begin);
		if (beginVertex == null) return null;
		
		Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
		Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>();
		paths.put(beginVertex, new PathInfo<>(weightManager.zero()));
		// 初始化paths
//		for (Edge<V, E> edge : beginVertex.outEdges) {
//			PathInfo<V, E> path = new PathInfo<>();
//			path.weight = edge.weight;
//			path.edgeInfos.add(edge.info());
//			paths.put(edge.to, path);
//		}

		while (!paths.isEmpty()) {
			Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = getMinPath(paths);
			// minVertex离开桌面
			Vertex<V, E> minVertex = minEntry.getKey();
			PathInfo<V, E> minPath = minEntry.getValue();
			selectedPaths.put(minVertex.value, minPath);
			paths.remove(minVertex);
			// 对它的minVertex的outEdges进行松弛操作
			for (Edge<V, E> edge : minVertex.outEdges) {
				// 如果edge.to已经离开桌面，就没必要进行松弛操作
				if (selectedPaths.containsKey(edge.to.value)) continue;
				relaxForDijkstra(edge, minPath, paths);
			}
		}
		
		selectedPaths.remove(begin);
		return selectedPaths;
	}
	
	/**
	 * 松弛
	 * @param edge 需要进行松弛的边
	 * @param fromPath edge的from的最短路径信息
	 * @param paths 存放着其他点（对于dijkstra来说，就是还没有离开桌面的点）的最短路径信息
	 */
	private void relaxForDijkstra(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<Vertex<V, E>, PathInfo<V, E>> paths) {
		// 新的可选择的最短路径：beginVertex到edge.from的最短路径 + edge.weight
		E newWeight = weightManager.add(fromPath.weight, edge.weight);
		// 以前的最短路径：beginVertex到edge.to的最短路径
		PathInfo<V, E> oldPath = paths.get(edge.to);
		if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) return;
		
		if (oldPath == null) {
			oldPath = new PathInfo<>();
			paths.put(edge.to, oldPath);
		} else {
			oldPath.edgeInfos.clear();
		}

		oldPath.weight = newWeight;
		oldPath.edgeInfos.addAll(fromPath.edgeInfos);
		oldPath.edgeInfos.add(edge.info());
	}
	
	/**
	 * 从paths中挑一个最小的路径出来
	 * @param paths
	 * @return
	 */
	private Entry<Vertex<V, E>, PathInfo<V, E>> getMinPath(Map<Vertex<V, E>, PathInfo<V, E>> paths) {
		Iterator<Entry<Vertex<V, E>, PathInfo<V, E>>> it = paths.entrySet().iterator();
		Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = it.next();
		while (it.hasNext()) {
			Entry<Vertex<V, E>, PathInfo<V, E>> entry = it.next();
			if (weightManager.compare(entry.getValue().weight, minEntry.getValue().weight) < 0) {
				minEntry = entry;
			}
		}
		return minEntry;
	}
	
	@Override
	public Map<V, Map<V, PathInfo<V, E>>> shortestPath() {
		Map<V, Map<V, PathInfo<V, E>>> paths = new HashMap<>();
		// 初始化
		for (Edge<V, E> edge : edges) {
			Map<V, PathInfo<V, E>> map = paths.get(edge.from.value);
			if (map == null) {
				map = new HashMap<>();
				paths.put(edge.from.value, map);
			}
			
			PathInfo<V, E> pathInfo = new PathInfo<>(edge.weight);
			pathInfo.edgeInfos.add(edge.info());
			map.put(edge.to.value, pathInfo);
		}

		vertices.forEach((V v2, Vertex<V, E> vertex2) -> {
			vertices.forEach((V v1, Vertex<V, E> vertex1) -> {
				vertices.forEach((V v3, Vertex<V, E> vertex3) -> {
					if (v1.equals(v2) || v2.equals(v3) || v1.equals(v3)) return;
					
					// v1 -> v2
					PathInfo<V, E> path12 = getPathInfo(v1, v2, paths);
					if (path12 == null) return;
					
					// v2 -> v3
					PathInfo<V, E> path23 = getPathInfo(v2, v3, paths);
					if (path23 == null) return;
					
					// v1 -> v3 
					PathInfo<V, E> path13 = getPathInfo(v1, v3, paths);
					
					E newWeight = weightManager.add(path12.weight, path23.weight);
					if (path13 != null && weightManager.compare(newWeight, path13.weight) >= 0) return;
					
					if (path13 == null) {
						path13 = new PathInfo<V, E>();
						paths.get(v1).put(v3, path13);
					} else {
						path13.edgeInfos.clear();
					}
					
					path13.weight = newWeight;
					path13.edgeInfos.addAll(path12.edgeInfos);
					path13.edgeInfos.addAll(path23.edgeInfos);
				});
			});
		});
		
		return paths;
	}
	
	private PathInfo<V, E> getPathInfo(V from, V to, Map<V, Map<V, PathInfo<V, E>>> paths) {
		Map<V, PathInfo<V, E>> map = paths.get(from);
		return map == null ? null : map.get(to);
	}

// 广度优先 没有实现访问接口版本

//	@Override
//	public void bfs(V begin) {
//		Vertex<V, E> beginVertex = vertices.get(begin);
//		if (beginVertex == null) return;
//		
//		Set<Vertex<V, E>> visitedVertices = new HashSet<>();
//		Queue<Vertex<V, E>> queue = new LinkedList<>();
//		queue.offer(beginVertex);
//		visitedVertices.add(beginVertex);
//		
//		while (!queue.isEmpty()) {
//			Vertex<V, E> vertex = queue.poll();
//			System.out.println(vertex.value);
//			
//			for (Edge<V, E> edge : vertex.outEdges) {
//				if (visitedVertices.contains(edge.to)) continue;
//				queue.offer(edge.to);
//				visitedVertices.add(edge.to);
//			}
//		}
//	}
//


// 深度优先 没有实现访问接口版本

//	@Override
//	public void dfs(V begin) {
//		Vertex<V, E> beginVertex = vertices.get(begin);
//		if (beginVertex == null) return;
//
//		Set<Vertex<V, E>> visitedVertices = new HashSet<>();
//		Stack<Vertex<V, E>> stack = new Stack<>();
//		
//		// 先访问起点
//		stack.push(beginVertex);
//		visitedVertices.add(beginVertex);
//		System.out.println(beginVertex.value);
//		
//		while (!stack.isEmpty()) {
//			Vertex<V, E> vertex = stack.pop();
//			
//			for (Edge<V, E> edge : vertex.outEdges) {
//				if (visitedVertices.contains(edge.to)) continue;
//				
//				stack.push(edge.from);
//				stack.push(edge.to);
//				visitedVertices.add(edge.to);
//				System.out.println(edge.to.value);
//				
//				break;
//			}
//		}
//	}



// 广度优先 递归实现

//	public void dfs2(V begin) {
//		Vertex<V, E> beginVertex = vertices.get(begin);
//		if (beginVertex == null) return;
//		dfs2(beginVertex, new HashSet<>());
//	}
//	
//	private void dfs2(Vertex<V, E> vertex, Set<Vertex<V, E>> visitedVertices) {
//		System.out.println(vertex.value);
//		visitedVertices.add(vertex);
//
//		for (Edge<V, E> edge : vertex.outEdges) {
//			if (visitedVertices.contains(edge.to)) continue;
//			dfs2(edge.to, visitedVertices);
//		}
//	}
}