//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：[3,2,3]
//输出：3 
//
// 示例 2： 
//
// 
//输入：[2,2,1,1,1,2,2]
//输出：2
// 
//
// 
//
// 进阶： 
//
// 
// 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。 
// 
// Related Topics 数组 哈希表 分治 计数 排序 
// 👍 1163 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

public class MajorityElement {
    public static void main(String[] args) {
//        Solution solution = new MajorityElement().new Solution();
        Solution1 solution = new MajorityElement().new Solution1();
        int[] nums = new int[]{3, 2, 3,1,0};
        solution.majorityElement(nums);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            int maxCount = 0;
            int maxCountNum = nums[0];
            int countingVal = nums[0];
            int count = 0;
            for (int num : nums) {
                if (num == countingVal) {
                    count++;
                } else {
                    if (count > maxCount) {
                        maxCount = count;
                        maxCountNum = countingVal;
                    }
                    countingVal = num;
                    count = 1;
                }
            }
            return count > maxCount ? countingVal : maxCountNum;
        }
    }

    /**
     * 众数的选择,当且仅当众数超过一般的时候使用
     */
    class Solution1 {
        public int majorityElement(int[] nums) {
            int count = 0;
            Integer candidate = null;

            for (int num : nums) {
                if (count == 0) {
                    candidate = num;
                }
                count += (num == candidate) ? 1 : -1;
            }

            return candidate;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}