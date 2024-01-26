package com.mj.union;

/**
 * Quick Union - 基于rank的优化 - 路径分裂(Path Spliting)
 * @author MJ Lee
 *
 */
public class _07_UnionFind_QU_R_PS extends _05_UnionFind_QU_R {

	public _07_UnionFind_QU_R_PS(int capacity) {
		super(capacity);
	}

	/**
	 *路径分裂：使路径上的每个节点都指向其祖父节点（parent的parent)
	 *<br/>
	 *<img src="https://gitee.com/codetheory/img-on1/raw/master/img01/1706246165874-2024-1-2613:16:06.png"  />
	 */
	@Override
	public int find(int v) { 
		rangeCheck(v);
		while (v != parents[v]) {
			int p = parents[v];
			parents[v] = parents[parents[v]];
			v = p;
		}
		return v;
	}
}