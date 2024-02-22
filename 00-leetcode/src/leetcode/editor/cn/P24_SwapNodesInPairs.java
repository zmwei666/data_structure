//给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
// 
//
// 示例 2： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 100] 内 
// 0 <= Node.val <= 100 
// 
//
// Related Topics 递归 链表 👍 2150 👎 0


package leetcode.editor.cn;

import 链表.ListNode;

/**
 * 两两交换链表中的节点
 * @author CodeTheory
 * @date 2024-02-22 15:53:02
 */
public class P24_SwapNodesInPairs{
	 public static void main(String[] args) {
//	 	 Solution solution = new Solution();
//		 solution.swapPairs(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null)))));
//		 solution.swapPairs(null);
//		 solution.swapPairs(new ListNode(1));
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
		ListNode curr = head, prev = null, next;
		while (curr != null && curr.next != null) {
			next = curr.next;

			// 交换节点
			curr.next = next.next;
			next.next = curr;
			if (prev != null) {
				prev.next = next;
			} else {
				head = next;
			}

			prev = curr;
			curr = curr.next;
		}
		return head;

	}
}
//leetcode submit region end(Prohibit modification and deletion)

}