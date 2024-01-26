package com.mj.union;

/**
 * Quick Find
 * @author MJ Lee
 *
 */
public class _02_UnionFind_QF extends _01_UnionFind {
	public _02_UnionFind_QF(int capacity) {
		super(capacity);
	}

	/*
	 * 父节点就是根节点
	 */
	public int find(int v) {
		rangeCheck(v);
		return parents[v];  // 返回村庄属于的集合  父节点就是那个集合
	}

	/**
	 * 将v1所在集合的所有元素，都嫁接到v2的父节点上
	 */
	public void union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		if (p1 == p2) return;

		/*
		   让每一个村庄独立
		   index 0 1 2 3 4 5 6 7 8 9
		   value 0 1 2 3 4 5 6 7 8 9

		   union(1, 0)  1 to 0

		   找到index 0号村庄
		   然后让index 0号村庄的父节点指向index 1号村庄

		   村庄   0 1 2 3 4 5 6 7 8 9
		   父节点 0 0 2 3 4 5 6 7 8 9

		   就实现了  0 <- 1
		 */
		for (int i = 0; i < parents.length; i++) { // 遍历数组, 找到所有跟v1的父节点一样的元素, 然后让他们的父节点指向v2的父节点
			if (parents[i] == p1) { // 找到index 0号村庄
				parents[i] = p2;  // 然后让index 0号村庄的父节点指向index 1号村庄
			}
		}
	}
}