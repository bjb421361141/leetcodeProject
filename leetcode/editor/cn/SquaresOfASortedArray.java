//给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
//
//
//
//
//
//
// 示例 1：
//
//
//输入：nums = [-4,-1,0,3,10]
//输出：[0,1,9,16,100]
//解释：平方后，数组变为 [16,1,0,9,100]
//排序后，数组变为 [0,1,9,16,100]
//
// 示例 2：
//
//
//输入：nums = [-7,-3,2,3,11]
//输出：[4,9,9,49,121]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 104
// -104 <= nums[i] <= 104
// nums 已按 非递减顺序 排序
//
//
//
//
// 进阶：
//
//
// 请你设计时间复杂度为 O(n) 的算法解决本问题
//
// Related Topics 数组 双指针 排序
// 👍 288 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

public class SquaresOfASortedArray {
    public static void main(String[] args) {
//        Solution solution = new SquaresOfASortedArray().new Solution();
        Solution1 solution = new SquaresOfASortedArray().new Solution1();
        solution.sortedSquares(new int[]{-4,-2,-1,1});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] sortedSquares(int[] nums) {
            int[] result = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                result[i] = nums[i] * nums[i];
            }
            Arrays.sort(result);
            return result;
        }
    }

    class Solution1 {
        public int[] sortedSquares(int[] nums) {
            int negativeIdx = -1; //纯负数的下标末尾
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < 0) {
                    negativeIdx = i;
                } else {
                    break;
                }
            }

            int[] result = new int[nums.length];
            int idx = 0;
            int fpIdx = negativeIdx, bpIdx = negativeIdx + 1;
            while (bpIdx < nums.length || fpIdx >= 0) {
                if (fpIdx < 0) { //没有负数的情况
                    result[idx] = nums[bpIdx] * nums[bpIdx]; //直接正向排序
                    bpIdx++;
                } else if (bpIdx == nums.length ) { //负数是最后一个元素
                    result[idx] = nums[fpIdx] * nums[fpIdx];
                    fpIdx--;
                } else if (nums[fpIdx] * nums[fpIdx] < nums[bpIdx] * nums[bpIdx]) {
                    result[idx] = nums[fpIdx] * nums[fpIdx];
                    fpIdx--;
                } else {
                    result[idx] = nums[bpIdx] * nums[bpIdx];
                    bpIdx++;
                }
                ++idx;
            }
            for (int i = 0; i < result.length; i++) {
                System.out.println(result[i]);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}