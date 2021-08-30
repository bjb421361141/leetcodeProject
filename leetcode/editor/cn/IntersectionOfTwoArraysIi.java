//给定两个数组，编写一个函数来计算它们的交集。 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2,2]
// 
//
// 示例 2: 
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[4,9] 
//
// 
//
// 说明： 
//
// 
// 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。 
// 我们可以不考虑输出结果的顺序。 
// 
//
// 进阶： 
//
// 
// 如果给定的数组已经排好序呢？你将如何优化你的算法？ 
// 如果 nums1 的大小比 nums2 小很多，哪种方法更优？ 
// 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？ 
// 
// Related Topics 数组 哈希表 双指针 二分查找 排序 
// 👍 542 👎 0

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IntersectionOfTwoArraysIi {
    public static void main(String[] args) {
//        Solution solution = new IntersectionOfTwoArraysIi().new Solution();
        Solution2 solution = new IntersectionOfTwoArraysIi().new Solution2();
        solution.intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2});
        solution.intersect(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 使用排序和双指针
         */
        public int[] intersect(int[] nums1, int[] nums2) {
            int[] result = new int[Math.min(nums1.length, nums2.length)];
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int nums1Idx = 0, nums2Idx = 0, idx = 0;
            do {
                if (nums1[nums1Idx] == nums2[nums2Idx]) {
                    result[idx++] = nums1[nums1Idx];
                    nums1Idx++;
                    nums2Idx++;
                } else if (nums1[nums1Idx] < nums2[nums2Idx]) {
                    nums1Idx++;
                } else if (nums1[nums1Idx] > nums2[nums2Idx]) {
                    nums2Idx++;
                }
            } while (nums1Idx < nums1.length && nums2Idx < nums2.length);
            if (idx > 0) {
                int[] resultArr = new int[idx];
                for (int i = 0; i < idx; i++) {
                    resultArr[i] = result[i];
                }
                return resultArr;
            }
            return new int[]{};
        }
    }

    class Solution2 {
        /**
         * 使用hashMap
         */
        public int[] intersect(int[] nums1, int[] nums2) {
            if (nums1.length > nums2.length) {
                return intersect(nums2, nums1);
            }

            Map<Integer, Integer> num1Map = new HashMap<>();
            for (int keyNum : nums1) {
                if (num1Map.containsKey(keyNum)) {
                    num1Map.put(keyNum, num1Map.get(keyNum) + 1);
                } else {
                    num1Map.put(keyNum, 1);
                }
            }
            int[] result = new int[nums1.length];
            int count = 0;
            for (int keyNum : nums2) {
                if (num1Map.containsKey(keyNum)) {
                    result[count++] =keyNum;
                    if (num1Map.get(keyNum) > 1) {
                        num1Map.put(keyNum, num1Map.get(keyNum) - 1);
                    } else {
                        num1Map.remove(keyNum);
                    }
                }
            }
            return Arrays.copyOfRange(result, 0, count);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}