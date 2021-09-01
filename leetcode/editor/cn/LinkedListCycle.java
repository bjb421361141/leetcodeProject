//给定一个链表，判断链表中是否有环。 
//
// 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的
//位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。 
//
// 如果链表中存在环，则返回 true 。 否则，返回 false 。 
//
// 
//
// 进阶： 
//
// 你能用 O(1)（即，常量）内存解决此问题吗？ 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：head = [3,2,0,-4], pos = 1
//输出：true
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 示例 2： 
//
// 
//
// 输入：head = [1,2], pos = 0
//输出：true
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 示例 3： 
//
// 
//
// 输入：head = [1], pos = -1
//输出：false
//解释：链表中没有环。
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围是 [0, 104] 
// -105 <= Node.val <= 105 
// pos 为 -1 或者链表中的一个 有效索引 。 
// 
// Related Topics 哈希表 链表 双指针 
// 👍 1184 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {
    public static void main(String[] args) {
        Solution solution = new LinkedListCycle().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    //  Definition for singly-linked list.
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public class Solution {
        public boolean hasCycle(ListNode head) {
            //Set<ListNode> seen = new HashSet<ListNode>();
            HashMap<ListNode, Integer> map = new HashMap<>();
            while (head != null) { //直接使用入参
                if (map.containsKey(head)) {
                    return true;
                }
                map.put(head, 1);
                head = head.next;
            }
            return false;
        }
    }

    public class Solution1 {
        /**
         * 快慢指针
         * 快指针fast每次向后移动两个位置，如果链表中有环，就会先进入环，从而和慢指针slow相遇，
         * 但如果fast每次向后移动一个位置，那么它和慢指针slow之间，一直有一个结点距离，即使链表中有环，也不会相遇
         */
        public boolean hasCycle(ListNode head) {
            if (head != null && head.next != null) {
                return false;
            }
            ListNode slowNode = head;
            ListNode fastNode = head.next;
            while (slowNode != fastNode) {
                if (fastNode == null || fastNode.next == null) { //判断快指针是否先到达节点
                    return false;
                }
                slowNode = slowNode.next;
                fastNode = fastNode.next.next;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}