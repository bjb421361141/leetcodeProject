//给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。 
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
//输入: numRows = 5
//输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
// 
//
// 示例 2: 
//
// 
//输入: numRows = 1
//输出: [[1]]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= numRows <= 30 
// 
// Related Topics 数组 动态规划 
// 👍 606 👎 0

package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PascalsTriangle {
    public static void main(String[] args) {
        Solution solution = new PascalsTriangle().new Solution();
        List<List<Integer>> ans = solution.generate(5);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> generate(int numRows) {
            if(numRows <=0 || numRows > 33) {
                return null;
            }
            List<List<Integer>> tmp = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                List<Integer> spRow = new ArrayList<>();
                if (i == 0) {
                    spRow.add(1);
                } else if (i == 1) {
                    spRow.add(1);
                    spRow.add(1);
                } else {
                    List<Integer> preRow = tmp.get(i - 1);
                    spRow.add(1);
                    for (int j = 0; j < preRow.size() - 1; j++) {
                        spRow.add(preRow.get(j) + preRow.get(j + 1));
                    }
                    spRow.add(1);
                }
                tmp.add(spRow);
            }
            return tmp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}