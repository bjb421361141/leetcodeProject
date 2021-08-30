//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：nums = [-1]
//输出：-1
// 
//
// 示例 5： 
//
// 
//输入：nums = [-100000]
//输出：-100000
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// -105 <= nums[i] <= 105 
// 
//
// 
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。 
// Related Topics 数组 分治 动态规划 
// 👍 3604 👎 0

package leetcode.editor.cn;

public class MaximumSubarray {
    public static void main(String[] args) {
//        Solution solution = new MaximumSubarray().new Solution();
//        Solution2 solution2 = new MaximumSubarray().new Solution2();
        Solution3 solution3 = new MaximumSubarray().new Solution3();
//        System.out.println(solution.maxSubArray(new int[]{0,100,-1,100,1,-3}));
//        System.out.println(solution2.maxSubArray(new int[]{10, 0, -1, -2, 13, 3}));
        System.out.println(solution3.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray(int[] nums) {
            Result maxRs = null;
            for (int arrLen = 1; arrLen <= nums.length; arrLen++) {
                Result rsTmp = this.getMaxSubArray(nums, arrLen);
                if (maxRs == null || rsTmp.compareTo(maxRs) > 0) {
                    maxRs = rsTmp;
                }
            }
            return maxRs.value;
        }

        private Result getMaxSubArray(int[] nums, int arrLen) {
            if (arrLen > nums.length) {
                return null;
            }

            Result rsObj = null;
            int maxVal = 0;
            for (int i = 0; i < nums.length; i++) {
                maxVal += nums[i];
                if (i >= arrLen) {
                    maxVal -= nums[i - arrLen];
                    Result tmp = new Result(maxVal, arrLen, i - arrLen, i);
                    if (tmp.compareTo(rsObj) > 0) {
                        rsObj = tmp;
                    }
                } else if (i == arrLen - 1) {
                    rsObj = new Result(maxVal, arrLen, 0, arrLen - 1);
                }
            }
            return rsObj;
        }


        class Result {
            int value;
            int length;
            int startIdx;
            int endIdx;

            public Result(int value, int length, int startIdx, int endIdx) {
                this.value = value;
                this.length = length;
                this.startIdx = startIdx;
                this.endIdx = endIdx;
            }

            public int compareTo(Result result) {
                if (this.value < result.value) {
                    return -1;
                } else if (this.value > result.value) {
                    return 1;
                } else {
                    return 0;
                }
            }


        }
    }

    class Solution2 {
        /**
         * 思路 ：判断 nums[i] 单独成为一段还是加入 f(i-1)f(i−1) 对应的那一段
         *
         * @param nums
         * @return
         */
        public int maxSubArray(int[] nums) {
            //pre 中 存储 f(i−1) 的值
            int pre = 0, maxAns = nums[0];
            for (int x : nums) {
                pre = Math.max(pre + x, x);
                maxAns = Math.max(maxAns, pre);
            }
            return maxAns;
        }
    }

    class Solution3 {

        public int maxSubArray(int[] nums) {
//           [-2,1,-3,(4,-1,2,1,)-5,4]
            Status rs = this.getMaxSubArrayStatus(nums, 0, nums.length - 1);
            return rs.msum;
        }

        /**
         * 分治法解决问题 先处理最小元素集合的情况 再向上合并集合
         *
         * @param nums     要处理的数组
         * @param startIdx 开始元素下标
         * @param endIdx   结束元素下标
         * @return Status 左元素起最大连续值 ,右元素起最大连续值  区间内所有值  区间内最大连续值
         */
        private Status getMaxSubArrayStatus(int[] nums, int startIdx, int endIdx) {
            if (startIdx == endIdx) {
                return new Status(nums[startIdx], nums[startIdx], nums[startIdx], nums[startIdx]); //返回
            }
            int midIdx = ((endIdx - startIdx) >> 1) + startIdx;
            Status lstatus = getMaxSubArrayStatus(nums, startIdx, midIdx); //左区间取相应的数据
            Status rstatus = getMaxSubArrayStatus(nums, midIdx + 1, endIdx); //右区间取相应的数据
            Status maxStatus = this.mergeSubArray(lstatus, rstatus); //处理左右两区间的数据得出该区间相应数据
            return maxStatus;
        }

        private Status mergeSubArray(Status lstatus, Status rstatus) {
            int lsum, rsum, isum, msum;
            isum = lstatus.isum + rstatus.isum;
            lsum = Math.max(lstatus.isum + rstatus.lsum, lstatus.lsum); //左区间的 lsum 或 左区间isum + 右区间的 lsum
            rsum = Math.max(lstatus.rsum + rstatus.isum, rstatus.rsum);
            msum = Math.max(Math.max(lstatus.msum, rstatus.msum), lstatus.rsum + rstatus.lsum);
            return new Status(lsum, rsum, isum, msum); //返回;
        }

        class Status {
            int lsum, rsum, isum, msum;

            public Status(int lsum, int rsum, int isum, int msum) {
                this.lsum = lsum;
                this.rsum = rsum;
                this.isum = isum;
                this.msum = msum;
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}