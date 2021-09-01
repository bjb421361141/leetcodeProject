//给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,6,3,4,5,6], val = 6
//输出：[1,2,3,4,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [], val = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [7,7,7,7], val = 7
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 列表中的节点数目在范围 [0, 104] 内 
// 1 <= Node.val <= 50 
// 0 <= val <= 50 
// 
// Related Topics 递归 链表 
// 👍 687 👎 0

package leetcode.editor.cn;

public class RemoveLinkedListElements {
    public static void main(String[] args) {
        Solution solution = new RemoveLinkedListElements().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    public class ListNode {
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

        ListNode add(ListNode lastNode) {
            this.next = lastNode;
            return this.next;
        }
    }

    class Solution {
        public ListNode removeElements(ListNode head, int val) {
            if (head == null || head.next == null) {
                return head != null ? (head.val == val) ? null : head : null;
            }
            //不止一个元素
            ListNode prehead = new ListNode(-1); //链表头
            ListNode prev = prehead;
            while (head != null) {
                if (head.val != val) {
                    prev.next = head;
                    prev = prev.next; //将prev 下移
                }
                head = head.next;
            }
            prev.next = null;
            return prehead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}