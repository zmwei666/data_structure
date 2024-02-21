package 链表;

public class ListNode {
	public int val;
	public ListNode next;

	public ListNode(){}
	public ListNode(int val) {
		this.val = val;
	}
	public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

	public static ListNode arrayToListNode(int[] arr) {
		ListNode head = new ListNode(arr[0]);
		ListNode prev = head;
		for (int i = 1; i < arr.length; i++) {
			ListNode listNode = new ListNode(arr[i]);
			prev.next = listNode;
			prev = listNode;
		}
		return head;
	}

	@Override
	public String toString() {
		return  val +"-" + next;
	}
}