//给定一个二叉树 root ，返回其最大深度。 
//
// 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：root = [1,null,2]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数量在 [0, 10⁴] 区间内。 
// -100 <= Node.val <= 100 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1788 👎 0


package leetcode.editor.cn;

import 二叉树.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 二叉树的最大深度
 * @author CodeTheory
 * @date 2024-03-07 16:07:43
 */
public class P104_MaximumDepthOfBinaryTree{
	 public static void main(String[] args) {
	 	 Solution solution = new P104_MaximumDepthOfBinaryTree().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
		int max = 0;
		if (root == null) {
			return max;
		}
		Deque<TreeNode> deque = new LinkedList<>();
		deque.offer(root);
		while (!deque.isEmpty()) {
			max++;
			int size = deque.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = deque.poll();
				if (node.left != null) {
					deque.offer(node.left);
				}
				if (node.right != null) {
					deque.offer(node.right);
				}
			}
		}
		return max;

	}

}
//leetcode submit region end(Prohibit modification and deletion)

}