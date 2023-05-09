//给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。 
//
// 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。 
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,0,2,1,1,0]
//输出：[0,0,1,1,2,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,0,1]
//输出：[0,1,2]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[0]
// 
//
// 示例 4： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 300 
// nums[i] 为 0、1 或 2 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以不使用代码库中的排序函数来解决这道题吗？ 
// 你能想出一个仅使用常数空间的一趟扫描算法吗？ 
// 
// Related Topics 数组 双指针 排序 
// 👍 1038 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

public class SortColors {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 1, 2, 0, 1, 2, 0};
//        Solution solution = new SortColors().new Solution();
//        Solution1 solution = new SortColors().new Solution1();
        Solution2 solution = new SortColors().new Solution2();
        solution.sortColors(nums);
        System.out.println(nums);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 常用排序
         *
         * @param nums
         */
        public void sortColors(int[] nums) {
            //原地排序不使用额外的空间
            if (nums.length < 2) {
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                int minValIdx = i;
                int startIdx = i + 1;
                while (startIdx < nums.length) {
                    if (nums[startIdx] < nums[minValIdx]) {
                        minValIdx = startIdx;
                    }
                    startIdx++;
                }
                if (i != minValIdx) {
                    int tmp = nums[minValIdx];
                    nums[minValIdx] = nums[i];
                    nums[i] = tmp;
                }
            }
        }
    }

    class Solution1 {
        public void sortColors(int[] nums) {
            if (nums.length < 2) {
                return;
            }
            //使用单指针的方法进行数据的交换
            int currentIdx = 0;
            int course = currentIdx;
            while (course < nums.length) {
                if (nums[currentIdx] == 0) {
                    currentIdx++;
                } else {
                    if (nums[course] == 0) {
                        nums[course] = nums[currentIdx];
                        nums[currentIdx] = 0;
                        currentIdx++;
                    }
                }
                course++;
            }
            course = currentIdx;
            while (course < nums.length) {
                if (nums[currentIdx] == 1) {
                    currentIdx++;
                } else {
                    if (nums[course] == 1) {
                        nums[course] = nums[currentIdx];
                        nums[currentIdx] = 1;
                        currentIdx++;
                    }
                }
                course++;
            }
//            currentIdx 为非0状态
        }
    }

    class Solution2 {
        public void sortColors(int[] nums) {
            if (nums.length < 2) {
                return;
            }
            int idx = 0;
            int p0Idx = 0;
            int p2Idx = nums.length - 1;
            while (idx <= p2Idx) {
//                确定p0和p2位置上的值分别不是0 和 2
                if (nums[p0Idx] == 0) { //推移至非0位置
                    p0Idx++;
                    idx++;
                } else {
                    if (nums[idx] == 2) {
                        //和尾部元素交换
                        nums[idx] = nums[p2Idx];
                        nums[p2Idx] = 2;
                        p2Idx--;
                        continue;
                    } else if (nums[idx] == 0) {
                        //和头部元素交换
                        nums[idx] = nums[p0Idx];
                        nums[p0Idx] = 0;
                        p0Idx++;
                    }
                    idx++;
                }

            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}