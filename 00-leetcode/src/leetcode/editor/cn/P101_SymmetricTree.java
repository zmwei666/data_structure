//给你一个二叉树的根节点 root ， 检查它是否轴对称。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,2,2,3,4,4,3]
//输出：true
// 
//
// 示例 2： 
// 
// 
//输入：root = [1,2,2,null,3,null,3]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [1, 1000] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你可以运用递归和迭代两种方法解决这个问题吗？ 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 2663 👎 0


package leetcode.editor.cn;

import 二叉树.TreeNode;

import java.util.Stack;

/**
 * 对称二叉树
 * @author CodeTheory
 * @date 2024-03-06 18:54:39
 */
public class P101_SymmetricTree{
	 public static void main(String[] args) {
	 	 Solution solution = new P101_SymmetricTree().new Solution();
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
    public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return false;
		}
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root.right);
		stack.push(root.left);
		while (!stack.isEmpty()) {
			TreeNode left = stack.pop();
			TreeNode right = stack.pop();

			if (left == null && right == null) {
				continue;
			}
			if (left == null || right == null || left.val != right.val) {
				return false;
			}
			stack.push(right.left);
			stack.push(left.right);
			stack.push(right.right);
			stack.push(left.left);
		}
		return true;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}