//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：[2,1]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围是 [0, 5000] 
// -5000 <= Node.val <= 5000 
// 
//
// 
//
// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？ 
// 
// 
// Related Topics 递归 链表 
// 👍 1934 👎 0

package leetcode.editor.cn;

import java.util.List;

public class ReverseLinkedList {
    public static void main(String[] args) {
        Solution solution = new ReverseLinkedList().new Solution();
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.add(new ListNode(2)).add(new ListNode(3)).add(new ListNode(4)).add(new ListNode(5));
        ListNode c = solution.reverseList(head.next);
        while (c != null) {
            System.out.print(c.val + ",");
            c = c.next;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    public static class ListNode {
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

        ListNode add(ListNode a) {
            this.next = a;
            return this.next;
        }
    }

    class Solution {
        /**
         * 使用递归
         */
        public ListNode reverseList(ListNode head) {
            if(head == null || head.next == null){
                return head;
            }
            ListNode lastNode = this.reverse(head);
            head.next = null;
            return lastNode;
        }

        private ListNode reverse(ListNode node) {
            if (node.next == null) {
                return node;
            }
            ListNode nextNode = node.next;
            ListNode lastNode = reverseList(node.next);
            nextNode.next = node;
            return lastNode;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}