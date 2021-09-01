//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。 
//
// 请必须使用时间复杂度为 O(log n) 的算法。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,3,5,6], target = 5
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: nums = [1,3,5,6], target = 2
//输出: 1
// 
//
// 示例 3: 
//
// 
//输入: nums = [1,3,5,6], target = 7
//输出: 4
// 
//
// 示例 4: 
//
// 
//输入: nums = [1,3,5,6], target = 0
//输出: 0
// 
//
// 示例 5: 
//
// 
//输入: nums = [1], target = 0
//输出: 0
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 104 
// -104 <= nums[i] <= 104 
// nums 为无重复元素的升序排列数组 
// -104 <= target <= 104 
// 
// Related Topics 数组 二分查找 
// 👍 1045 👎 0

package leetcode.editor.cn;

public class SearchInsertPosition {
    public static void main(String[] args) {
        Solution solution = new SearchInsertPosition().new Solution();
        System.out.println(solution.searchInsert(new int[]{1, 3, 5, 6}, 4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    //nums[pos−1]< target < nums[pos]   //找到一个小于他且离他最近的一个元素 取得这个元素的下标+1
    class Solution {
        public int searchInsert(int[] nums, int target) {
            int start = 0;
            int end = nums.length - 1;
            return this.getIntPosition(nums, target, start, end);
        }

        private int getIntPosition(int[] nums, int target, int start, int end) {
            if (start > end) {
                int pos = start >= nums.length ? start : (nums[start] < target ? start + 1 : start);
                return pos;
            }
            int midIdx = ((end - start) >> 1) + start;
            if (target < nums[midIdx]) {
                return getIntPosition(nums, target, start, midIdx - 1);
            } else if (target > nums[midIdx]) {
                return getIntPosition(nums, target, midIdx + 1, end);
            } else {
                return midIdx;
            }
        }
    }

    class Solution1 {
        public int searchInsert(int[] nums, int target) {
            int n = nums.length;
            int left = 0, right = n - 1, ans = n;
            while (left <= right) {
                int mid = ((right - left) >> 1) + left;
                if (target <= nums[mid]) {
                    ans = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}