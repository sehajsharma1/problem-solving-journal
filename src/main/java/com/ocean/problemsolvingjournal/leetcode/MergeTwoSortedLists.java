package leetcode.code.java;

/*You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.

Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
*/

public class MergeTwoSortedLists {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        // Create list1 = [1,2,4]
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        // Create list2 = [1,3,4]
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        MergeTwoSortedLists linkedList = new MergeTwoSortedLists();
        linkedList.mergeTwoLists(list1, list2);

    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Dummy node to simplify edge cases
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // While both lists are not empty
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // Attach the remaining part of the non-empty list
        current.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }
}
