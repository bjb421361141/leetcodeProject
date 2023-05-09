//给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。 
//
// 在「杨辉三角」中，每个数是它左上方和右上方的数的和。 
//
// 
//
// 
//
// 示例 1: 
//
// 
//输入: rowIndex = 3
//输出: [1,3,3,1]
// 
//
// 示例 2: 
//
// 
//输入: rowIndex = 0
//输出: [1]
// 
//
// 示例 3: 
//
// 
//输入: rowIndex = 1
//输出: [1,1]
// 
//
// 
//
// 提示: 
//
// 
// 0 <= rowIndex <= 33 
// 
//
// 
//
// 进阶： 
//
// 你可以优化你的算法到 O(rowIndex) 空间复杂度吗？ 
// Related Topics 数组 动态规划 
// 👍 326 👎 0

package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PascalsTriangleIi {
    public static void main(String[] args) {
        Solution solution = new PascalsTriangleIi().new Solution();
        List<Integer> row = solution.getRow(3);
        System.out.println(row);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> getRow(int rowIndex) {
        if(rowIndex < 0) {
            return null;
        }
        List<Integer> ans = new ArrayList<Integer>();
        if(rowIndex == 0) {
            ans.add(1);
            return ans;
        }
        Deque<Integer> preRow = new ArrayDeque<>();
        for (int i = 0; i < rowIndex; i++) {
            int times = preRow.size();
            preRow.offer(1);
            if(times > 0) {
                int preVal = preRow.pollFirst();
                for (int j = 1; j < times; j++) {
                    int curVal = preRow.pollFirst();
                    preRow.offer(preVal + curVal);
                    preVal = curVal;
                }
            }
            preRow.offer(1);
        }
        while(!preRow.isEmpty()) {
            ans.add(preRow.pollFirst());
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
