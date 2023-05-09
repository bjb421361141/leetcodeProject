//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 排序 
// 👍 3856 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ThreeSum {
    public static void main(String[] args) {
        int[] arr = new int[]{-1, 0, 1, 2, -1, -4};
//        Solution solution = new ThreeSum().new Solution();
//        Solution1 solution = new ThreeSum().new Solution1();
        Solution2 solution = new ThreeSum().new Solution2();
        solution.threeSum(arr);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            if (nums.length < 3) {
                return ans;
            }
            Arrays.sort(nums);
            Set<List<Integer>> tmp = new HashSet<>();
            for (int first = 0; first < nums.length - 2; first++) {
                int firstNum = nums[first];
                for (int second = first + 1; second < nums.length - 1; second++) {
                    int secondNum = nums[second];
                    for (int third = second + 1; third < nums.length; third++) {
                        int thirdNum = nums[third];
                        if (firstNum + secondNum + thirdNum == 0) {
                            List ansLst = new ArrayList<>();
                            ansLst.add(firstNum);
                            ansLst.add(secondNum);
                            ansLst.add(thirdNum);
                            tmp.add(ansLst);
                        }
                    }
                }
            }
            ans.addAll(tmp);
            return ans;
        }
    }

    class Solution1 {
        public List<List<Integer>> threeSum(int[] nums) {
            if (nums == null || nums.length < 3)
                return new ArrayList<>();

            Set<List<Integer>> res = new HashSet<>();
            Arrays.sort(nums); // O(nlogn)

            // O(n^2)
            for (int i = 0; i < nums.length; i++) {
                // 在 i + 1 ... nums.length - 1 中查找相加等于 -nums[i] 的两个数
                int target = -nums[i];
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[left] + nums[right];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
            return new ArrayList<>(res); // O(n)
        }
    }

    /**
     * 固定一个数，另外两个数做两数之和--使用双指针
     */
    class Solution2 {
        public List<List<Integer>> threeSum(int[] nums) {
            if (nums == null || nums.length < 3)
                return new ArrayList<>();
            int n = nums.length;
            List<List<Integer>> ans = new ArrayList<>();
            Arrays.sort(nums); // O(nlogn)
            for (int first = 0; first < nums.length; ++first) {
                //固定第一位 ，后续数组中的求两个元素和的问题
                if (first > 0 && nums[first] == nums[first - 1]) { //过滤相同固定元素
                    continue;
                }
                int targetVal = -nums[first];
                int secondIdx = first + 1;
                int thirdIdx = nums.length - 1;
                while (secondIdx < thirdIdx) {
                    int sumVal = nums[secondIdx] + nums[thirdIdx];
                    if (sumVal > targetVal) {
                        thirdIdx--;
                    } else if ((sumVal < targetVal)) {
                        secondIdx++;
                    } else {
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(nums[first]);
                        tmp.add(nums[secondIdx]);
                        tmp.add(nums[thirdIdx]);
                        ans.add(tmp);
                        //过滤相同元素
                        while (secondIdx < thirdIdx && nums[secondIdx] == nums[secondIdx + 1]) {
                            secondIdx++;
                        }
                        while (secondIdx < thirdIdx && nums[thirdIdx] == nums[thirdIdx-1]) {
                            thirdIdx--;
                        }
                        secondIdx++;
                        thirdIdx--;
                    }
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}