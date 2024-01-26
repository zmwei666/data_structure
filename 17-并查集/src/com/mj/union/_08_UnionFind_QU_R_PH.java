package com.mj.union;

/**
 * Quick Union - 基于rank的优化 - 路径减半(Path Halving)
 * @author MJ Lee
 *
 */
public class _08_UnionFind_QU_R_PH extends _05_UnionFind_QU_R {

	public _08_UnionFind_QU_R_PH(int capacity) {
		super(capacity);
	}

	/**
	 * 路径减半：使路径上每隔一个节点就指向其祖父节点（parent的parent)
	 * <br/>
	 *<img src="https://gitee.com/codetheory/img-on1/raw/master/img01/1706246448238-2024-1-2613:20:48.png"  />
	 */
	@Override
	public int find(int v) { 
		rangeCheck(v);
		while (v != parents[v]) {  // 只操作1 3 5 7, 其他的节点不用管
			parents[v] = parents[parents[v]];
			v = parents[v];
		}
		return v;
	}
}