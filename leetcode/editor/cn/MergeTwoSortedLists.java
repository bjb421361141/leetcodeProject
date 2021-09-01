//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
//
//
//
// 示例 1：
//
//
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
//
//
// 示例 2：
//
//
//输入：l1 = [], l2 = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：l1 = [], l2 = [0]
//输出：[0]
//
//
//
//
// 提示：
//
//
// 两个链表的节点数目范围是 [0, 50]
// -100 <= Node.val <= 100
// l1 和 l2 均按 非递减顺序 排列
//
// Related Topics 递归 链表
// 👍 1877 👎 0

package leetcode.editor.cn;

import java.util.List;

public class MergeTwoSortedLists {
    public static void main(String[] args) {
        Solution solution = new MergeTwoSortedLists().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    //  Definition for singly-linked list.
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
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null && l2 == null) {
                return null;
            } else if (l1 == null) {
                return l2;
            } else if (l2 == null) {
                return l1;
            }
            if (l1.val > l2.val) {
                return mergeTwoLists(l2, l1);
            }
            ListNode ansListNode = new ListNode(l1.val, null);
            ListNode firstListNode = ansListNode;
            l1 = l1.next;
            while (l1 != null || l2 != null) {
                if (l1 == null) {
                    ansListNode.next = new ListNode(l2.val);
                    l2 = l2.next;
                } else if (l2 == null) {
                    ansListNode.next = new ListNode(l1.val);
                    l1 = l1.next;
                } else if (l1.val < l2.val) {
                    ansListNode.next = new ListNode(l1.val);
                    l1 = l1.next;
                } else {
                    ansListNode.next = new ListNode(l2.val);
                    l2 = l2.next;
                }
                ansListNode = ansListNode.next;
            }
            return firstListNode;
        }
    }

    class Solution1 {
        /**
         * 递归实现
         * 返回最小的元素
         */
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            } else if (l2 == null) {
                return l1;
            } else if (l1.val < l2.val) { //取最小值
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
        }
    }

    class Solution2 {
        /**
         * 迭代实现
         * 使用哨兵保留前一元素，比较后修改指向并移动哨兵
         */
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode prehead = new ListNode(-1); //链表头
            ListNode prev = prehead;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    prev.next = l1;
                    l1 = l1.next;
                } else {
                    prev.next = l2;
                    l2 = l2.next;
                }
                prev = prev.next;
            }
            prev.next = l1 == null ? l2 : l1;
            return prehead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}