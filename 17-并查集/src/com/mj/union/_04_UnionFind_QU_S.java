package com.mj.union;

/**
 * Quick Union - 基于size的优化
 * @author MJ Lee
 */
public class _04_UnionFind_QU_S extends _03_UnionFind_QU {
	private int[] sizes;  // 存放元素个数的数组

	public _04_UnionFind_QU_S(int capacity) {
		super(capacity);
		
		sizes = new int[capacity];
		for (int i = 0; i < sizes.length; i++) {
			sizes[i] = 1;  // 初始化的时候, 每个元素都是独立的, 所以个数都是1
		}
	}

	/**
	 * 将v1的根节点嫁接到v2的根节点上
	 * <br/>
	 * <img src="https://gitee.com/codetheory/img-on1/raw/master/img01/1706244401141-2024-1-2612:46:41.png"  />
	 */
	public void union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		if (p1 == p2) return;
		
		if (sizes[p1] < sizes[p2]) {  // 对比两个链条的元素个数
			parents[p1] = p2;  // 元素个数少的那个嫁接到元素个数多的那个上面
			sizes[p2] += sizes[p1];  // 更新元素个数
		} else {
			parents[p2] = p1;
			sizes[p1] += sizes[p2];
		}
	}

}