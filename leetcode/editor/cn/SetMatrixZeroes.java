//给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。 
//
// 进阶： 
//
// 
// 一个直观的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。 
// 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。 
// 你能想出一个仅使用常量空间的解决方案吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
//输出：[[1,0,1],[0,0,0],[1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
//输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[0].length 
// 1 <= m, n <= 200 
// -231 <= matrix[i][j] <= 231 - 1 
// 
// Related Topics 数组 哈希表 矩阵 
// 👍 547 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SetMatrixZeroes {
    public static void main(String[] args) {
        Solution solution = new SetMatrixZeroes().new Solution();
//        solution.setZeroes(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}});
        solution.setZeroes(new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void setZeroes(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length; //n列
            boolean[] rowZeroIntIdxIdx = new boolean[m];
            boolean[] colZeroIntIdxIdx = new boolean[n];
            //遍历查询 为0的元素
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (0 == matrix[i][j]) {
                        rowZeroIntIdxIdx[i] = true;
                        colZeroIntIdxIdx[j] = true;
                    }
                }
            }
            //将 同一列，同一行的元素置为0
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (rowZeroIntIdxIdx[i] || colZeroIntIdxIdx[j]) {
                        matrix[i][j] = 0;
                    }
//                    System.out.print(matrix[i][j] + ",");
                }
//                System.out.println("");
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}