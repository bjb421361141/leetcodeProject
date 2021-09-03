//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。 
//
// 
//
// 进阶： 
//
// 
// 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 
// 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？ 
// 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,2,3,4,5,6,7], k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 
//输入：nums = [-1,-100,3,99], k = 2
//输出：[3,99,-1,-100]
//解释: 
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 104 
// -231 <= nums[i] <= 231 - 1 
// 0 <= k <= 105 
// 
//
// 
// 
// Related Topics 数组 数学 双指针 
// 👍 1102 👎 0

package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

public class RotateArray {
    public static void main(String[] args) {
//        Solution solution = new RotateArray().new Solution();
        Solution1 solution = new RotateArray().new Solution1();
        int[] tmp = new int[]{1, 2, 3, 4, 5, 6, 7};
        solution.rotate(tmp, 3);
        for (int t : tmp) {
            System.out.print(t + ",");
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 使用中间存储将数组重新排列
         */
        public void rotate(int[] nums, int k) {
            int moveCount = k % nums.length; //移动步数
            int[] tmpArr = new int[moveCount];
            for (int i = 0; i < moveCount; i++) {
                tmpArr[moveCount - i - 1] = nums[nums.length - i - 1];
            }
            //移动nums 中的元素
            for (int i = nums.length - 1; i >= 0; i--) { //最后的一个位置 length-1 和 length- moveCount -1 的 位置对应
                if (i < moveCount) {
                    nums[i] = tmpArr[i];
                } else {
                    nums[i] = nums[i - moveCount];
                }
            }
        }
    }

    class Solution1 {
        /**
         * 使用额外的数组
         *  [1,2,3,4,5] -->(移动两步) [0,0,1,2,3,| 4,5]
         *  给新下标重新按数组长度进行分组 i+k %n
         */
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            int[] newArr = new int[n];
            for (int i = 0; i < n; ++i) {
                newArr[(i + k) % n] = nums[i];  //重新计算数组下标的位置 i+k% n
            }
            System.arraycopy(newArr, 0, nums, 0, n);
        }
    }

    class Solution2 {
        /**
         * 环状遍历
         * 计算元素移动步数k ，需要几个循环才可以循环完 [1,2,3,4,5] k= 2  [1,3,5] ,[2,4]
         *
         */
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k = k % n;
            int count = gcd(k, n);
            for (int start = 0; start < count; ++start) {
                int current = start;
                int prev = nums[start];
                do {
                    int next = (current + k) % n;
                    int temp = nums[next];
                    nums[next] = prev;
                    prev = temp;
                    current = next;
                } while (start != current);
            }
        }

        public int gcd(int x, int y) {
            return y > 0 ? gcd(y, x % y) : x;
        }
    }

    class Solution3 {
        /**
         * 数组翻转
         */
        public void rotate(int[] nums, int k) {
            k %= nums.length;
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }

        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start += 1;
                end -= 1;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}