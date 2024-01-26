package com.mj.union;

/**
 * Quick Union - 基于rank的优化 就是让树的高度尽可能的低
 * 基于size的优化也会导致树的高度过高
 * @author MJ Lee
 */
public class _05_UnionFind_QU_R extends _03_UnionFind_QU {
	private int[] ranks;  // 存放树的高度的数组

	public _05_UnionFind_QU_R(int capacity) {
		super(capacity);

		ranks = new int[capacity];
		for (int i = 0; i < ranks.length; i++) {
			ranks[i] = 1;
		}
	}

	/**
	 * 基于rank的优化 就是让树的高度尽可能的低
	 * <br/>
	 *<img src="https://gitee.com/codetheory/img-on1/raw/master/img01/1706244642929-2024-1-2612:50:43.png"  />
	 */
	public void union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		if (p1 == p2) return;
		
		if (ranks[p1] < ranks[p2]) {  // 对比两个链条的高度
			parents[p1] = p2;  // 高度低的那个嫁接到高度高的那个上面
		} else if (ranks[p1] > ranks[p2]) {
			parents[p2] = p1;
		} else { // 高度一样的话, 那么谁嫁接到谁的下面都可以, 但是要更新高度
			parents[p1] = p2;
			ranks[p2] += 1;
		}
	}
}