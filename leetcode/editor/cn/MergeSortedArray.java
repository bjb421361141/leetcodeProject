//给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。 
//
// 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。 
//
// 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并
//的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
//输出：[1,2,2,3,5,6]
//解释：需要合并 [1,2,3] 和 [2,5,6] 。
//合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1], m = 1, nums2 = [], n = 0
//输出：[1]
//解释：需要合并 [1] 和 [] 。
//合并结果是 [1] 。
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [0], m = 0, nums2 = [1], n = 1
//输出：[1]
//解释：需要合并的数组是 [] 和 [1] 。
//合并结果是 [1] 。
//注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m + n 
// nums2.length == n 
// 0 <= m, n <= 200 
// 1 <= m + n <= 200 
// -109 <= nums1[i], nums2[j] <= 109 
// 
//
// 
//
// 进阶：你可以设计实现一个时间复杂度为 O(m + n) 的算法解决此问题吗？ 
// Related Topics 数组 双指针 排序 
// 👍 1065 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

public class MergeSortedArray {
    public static void main(String[] args) {
//        Solution solution = new MergeSortedArray().new Solution();
//        Solution2 solution = new MergeSortedArray().new Solution2();
        Solution3 solution = new MergeSortedArray().new Solution3();
//        nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
        solution.merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
//        solution.merge(new int[]{1}, 1, new int[]{}, 0);
//        solution.merge(new int[]{0}, 0, new int[]{1}, 1);
//        solution.merge(new int[]{2, 0}, 1, new int[]{1}, 1);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            //fixme 校验条件
            //涉及到数组元素的移动
            if (n > 0) {
                int num1Idx = 0;
                int num2Idx = 0;
                do {
                    if (nums1[num1Idx] < nums2[num2Idx]) {
                        num1Idx++;
                    } else {
                        this.moveElement(nums1, m + num2Idx, num1Idx, nums2[num2Idx]);
                        num2Idx++;
                    }
                    //两种情况
                    //1.num1Idx >= m + num2Idx 说明nums1中的元素全部遍历结束
                    //2.num2Idx >= n 说明nums2中元素全部进行了插入
                } while (num1Idx < m + num2Idx && num2Idx < n);
                //处理特殊情况 将剩余的数据插入到nums1的末端
                if (m + num2Idx < m + n) {
                    for (int i = num2Idx; i < n; i++) {
                        nums1[m + i] = nums2[i];
                    }
                }
            }
        }

        /**
         * 往后移动一位数
         *
         * @param nums1      要插入的数组
         * @param numsLength 数组长度
         * @param insertIdx  插入元素的下标
         * @param value      插入元素值
         */
        private void moveElement(int[] nums1, int numsLength, int insertIdx, int value) {
            for (int i = numsLength; insertIdx < i; i--) {
                nums1[i] = nums1[i - 1];
            }
            nums1[insertIdx] = value;
        }
    }

    class Solution1 {
        /**
         * 合并后排序
         */
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            for (int i = 0; m + i < m + n; i++) {
                nums1[m + i] = nums2[i];
            }
            Arrays.sort(nums1);
        }
    }

    class Solution2 {
        /**
         * 双指标排序
         */
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int nums1Idx = 0, nums2Idx = 0, cur = 0;
            int[] sorted = new int[m + n];
            while (nums1Idx < m || nums2Idx < n) {
                if (nums1Idx == m) {
                    cur = nums2[nums2Idx++];
                } else if (nums2Idx == n) {
                    cur = nums1[nums1Idx++];
                } else if (nums1[nums1Idx] <= nums2[nums2Idx]) {
                    cur = nums1[nums1Idx++];
                } else {
                    cur = nums2[nums2Idx++];
                }
                sorted[nums1Idx + nums2Idx - 1] = cur;
            }
            for (int i = 0; i != m + n; ++i) {
                nums1[i] = sorted[i];
            }
            for (int i : nums1) {
                System.out.println(i);
            }
        }
    }

    class Solution3 {
        /**
         * 逆向双指标
         */
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int nums1Idx = m - 1, nums2Idx = n - 1;
            int tail = m + n - 1;
            int cur;
            //直接操作 nums1 数据
            while (nums1Idx >= 0 || nums2Idx >= 0) {
                if (nums1Idx == -1) {
                    cur = nums2[nums2Idx--];
                } else if (nums2Idx == -1) {
                    cur = nums1[nums1Idx--];
                } else if (nums1[nums1Idx] <= nums2[nums2Idx]) {
                    cur = nums2[nums2Idx--];
                } else {
                    cur = nums1[nums1Idx--];
                }
                nums1[tail--] = cur;
            }
            for (int i : nums1) {
                System.out.println(i);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}