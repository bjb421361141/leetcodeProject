//给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。 
//
// 说明： 
//
// 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？ 
//
// 示例 1: 
//
// 输入: [2,2,1]
//输出: 1
// 
//
// 示例 2: 
//
// 输入: [4,1,2,1,2]
//输出: 4 
// Related Topics 位运算 数组 
// 👍 2058 👎 0

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SingleNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{-336,513,-560,-481,-174,101,-997,40,-527,-784,-283,-336,513,-560,-481,-174,101,-997,40,-527,-784,-283,354};
        Solution solution = new SingleNumber().new Solution();
        solution.singleNumber(nums);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        /**
         * 一般解法 适用于仅有一个单独存在的数的情况
          * @param nums
         * @return
         */
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int oldNum = nums[0];
        int numsCount = 1;
        for (int i = 1; i < nums.length; i++) {
            if(oldNum != nums[i]) {
                if(numsCount == 1) {
                    return oldNum;
                }
                oldNum = nums[i];
                numsCount = 1;
            }else {
                numsCount++;
            }
        }
        return nums[nums.length-1];
    }
}

    class Solution1 {
        /**
         * 数组中只有一个数出现一次，其余出现两次的情况
         * @param nums
         * @return
         */
        public int singleNumber(int[] nums) {
           int singleNum = 0;
            for (int num : nums) {
                singleNum ^= num;
            }
            return singleNum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}