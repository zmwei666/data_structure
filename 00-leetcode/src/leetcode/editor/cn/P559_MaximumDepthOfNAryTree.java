//给定一个 N 叉树，找到其最大深度。 
//
// 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。 
//
// N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,null,3,2,4,null,5,6]
//输出：3
// 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,
//null,13,null,null,14]
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 树的深度不会超过 1000 。 
// 树的节点数目位于 [0, 10⁴] 之间。 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 👍 384 👎 0


package leetcode.editor.cn;

import leetcode.editor.cn.structure.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * N 叉树的最大深度
 * @author CodeTheory
 * @date 2024-03-07 16:30:50
 */
public class P559_MaximumDepthOfNAryTree{
	 public static void main(String[] args) {
	 	 Solution solution = new P559_MaximumDepthOfNAryTree().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public int maxDepth(Node root) {
		if (root == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		int max = 0;
		while (!queue.isEmpty()) {
			max++;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node node = queue.poll();
				for (Node child : node.children) {
					if (child != null) {
						queue.offer(child);
					}
				}
			}
		}
		return max;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}