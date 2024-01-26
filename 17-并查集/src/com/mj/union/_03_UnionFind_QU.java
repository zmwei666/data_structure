package com.mj.union;

/**
 * Quick Union
 * @author MJ Lee
 *
 */
public class _03_UnionFind_QU extends _01_UnionFind {

	public _03_UnionFind_QU(int capacity) {
		super(capacity);
	}

	/**
	 * 通过parent链条不断地向上找，直到找到根节点
	 * <img src="https://gitee.com/codetheory/img-on1/raw/master/img01/1706242254153-2024-1-2612:10:54.png"  />
	 */
	public int find(int v) {
		rangeCheck(v);
		while (v != parents[v]) {  // 可以看找1
			v = parents[v];
		}
		return v;
	}

	/**
	 * 将v1的根节点嫁接到v2的根节点上
	 * <img src="https://gitee.com/codetheory/img-on1/raw/master/img01/1706243184478-2024-1-2612:26:24.png"  />
	 */
	public void union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		if (p1 == p2) return;
		parents[p1] = p2;  // 这里的p1是v1的父节点, 而p1的值就是这个父节点对应的下标, 所以可以直接用
	}

}