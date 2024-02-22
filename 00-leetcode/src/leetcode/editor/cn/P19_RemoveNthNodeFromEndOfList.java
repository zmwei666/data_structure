//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
//
// 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
//
// Related Topics 链表 双指针 👍 2808 👎 0


package leetcode.editor.cn;

import 链表.ListNode;

import java.util.Stack;

/**
 * 删除链表的倒数第 N 个结点
 * @author CodeTheory
 * @date 2024-02-22 16:50:51
 */
public class P19_RemoveNthNodeFromEndOfList{
	 public static void main(String[] args) {
	 	 Solution solution = new Solution();
//		 ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//		 ListNode listNode = new ListNode(1);

		 System.out.println(solution.removeNthFromEnd(new ListNode(1, new ListNode(2)), 2));
		 System.out.println(solution.removeNthFromEnd(new ListNode(1), 1));
		 System.out.println(solution.removeNthFromEnd(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2));

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
static class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null) {
			return null;
		}
		Stack<ListNode> stack = new Stack<>();
		ListNode curr = head;
		while (curr != null) {
			stack.push(curr);
			curr = curr.next;
		}
		for (int i = 0; i < n; i++) {
			stack.pop();
		}
		if (stack.isEmpty()) {
			return head.next;
		}
		ListNode peek = stack.peek();
		peek.next = peek.next.next;
		return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}