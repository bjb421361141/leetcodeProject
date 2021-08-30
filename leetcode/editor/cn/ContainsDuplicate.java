//给定一个整数数组，判断是否存在重复元素。 
//
// 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。 
//
// 
//
// 示例 1: 
//
// 
//输入: [1,2,3,1]
//输出: true 
//
// 示例 2: 
//
// 
//输入: [1,2,3,4]
//输出: false 
//
// 示例 3: 
//
// 
//输入: [1,1,1,3,3,4,3,2,4,2]
//输出: true 
// Related Topics 数组 哈希表 排序 
// 👍 457 👎 0

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;

public class ContainsDuplicate {
    public static void main(String[] args) {
        Solution solution = new ContainsDuplicate().new Solution();
        Solution2 solution2 = new ContainsDuplicate().new Solution2();
        System.out.println(solution.containsDuplicate(new int[]{3,3}));
        System.out.println(solution2.containsDuplicate(new int[]{3,3}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean containsDuplicate(int[] nums) {
            HashSet<Integer> set = new HashSet<>();
            for (int n : nums) {
                if (set.contains(n)) {
                    return true;
                } else {
                    set.add(n);
                }
            }
            return false;
        }
    }
    class Solution2 {
        public boolean containsDuplicate(int[] nums) {
            Arrays.sort(nums);
            int peek = nums[0];
            for(int i =1;i <= nums.length-1;i++) {
                if(peek == nums[i]){
                    return true;
                }
                peek = nums[i];
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}