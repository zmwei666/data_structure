//给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,null,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
//
// Related Topics 栈 树 深度优先搜索 二叉树 👍 2044 👎 0


package leetcode.editor.cn;

import 二叉树.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的中序遍历
 * @author CodeTheory
 * @date 2024-03-01 10:25:49
 */
public class P94_BinaryTreeInorderTraversal{
	 public static void main(String[] args) {
	 	 Solution solution = new P94_BinaryTreeInorderTraversal().new Solution();
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
    public List<Integer> inorderTraversal(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<>();
		inorder(root, result);
		return result;
	}
	public void inorder(TreeNode node, List<Integer> arr) {
		if (node == null) {
			return;
		}
		inorder(node.left, arr);
		arr.add(node.val);
		inorder(node.right, arr);
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}