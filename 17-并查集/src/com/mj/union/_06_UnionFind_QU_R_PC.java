package com.mj.union;

/**
 * Quick Union - 基于rank的优化 - 路径压缩(Path Compression)
 * @author MJ Lee
 *
 */
public class _06_UnionFind_QU_R_PC extends _05_UnionFind_QU_R {

	public _06_UnionFind_QU_R_PC(int capacity) {
		super(capacity);
	}

	/**
	 * 在find的时候, 顺便把路径上的所有节点都指向根节点, 从而减低数的高度
	 * <br/>
	 *<img src="https://gitee.com/codetheory/img-on1/raw/master/img01/1706245287762-2024-1-2613:01:27.png"  />
	 * @param v
	 * @return
	 */
	@Override
	public int find(int v) { // v == 1, parents[v] == 2
		rangeCheck(v);
		if (parents[v] != v) {
			parents[v] = find(parents[v]);  // 修改 1 的父节点为 4
		}
		return parents[v];
	}
}