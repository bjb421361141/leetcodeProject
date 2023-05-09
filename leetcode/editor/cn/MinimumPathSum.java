//给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。 
//
// 说明：每次只能向下或者向右移动一步。 
//
// 
//
// 示例 1： 
// 
// 
//输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
//输出：7
//解释：因为路径 1→3→1→1→1 的总和最小。
// 
//
// 示例 2： 
//
// 
//输入：grid = [[1,2,3],[4,5,6]]
//输出：12
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// 0 <= grid[i][j] <= 100 
// 
//
// Related Topics 数组 动态规划 矩阵 👍 1475 👎 0


package leetcode.editor.cn;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

public class MinimumPathSum {
    public static void main(String[] args) {
        Solution solution = new MinimumPathSum().new Solution();
        int[][] testData = new int[][]{
                {1, 3, 1,0},
                {1, 5, 1,5},
                {4, 2, 1,8},
                {1, 2, 1,2},
//                {1, 9, 9, 9},
//                {1, 1, 1, 9},
//                {9, 9, 1, 9},
//                {9, 9, 1, 1},
        };
//        System.out.println(solution.minPathSum(testData));
        solution.markMinPath(testData);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 坐标类
         */
        class Point {
            int x;
            int y;

            Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }
        }

        public int minPathSum(int[][] grid) {
            int[][] dp = new int[grid.length][grid[0].length]; //所经路线最小的和
            dp[0][0] = grid[0][0];
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++) {
                    if (i > 0 && j > 0) {
                        int upBlockSum = dp[i - 1][j] + grid[i][j];
                        int leftBlockSum = dp[i][j - 1] + grid[i][j];
                        dp[i][j] = Math.min(upBlockSum, leftBlockSum);
                    } else if (i > 0) {
                        dp[i][j] = dp[i - 1][j] + grid[i][j];
                    } else if (j > 0) {
                        dp[i][j] = dp[i][j - 1] + grid[i][j];
                    }
                }
            }
            return dp[grid.length - 1][grid[0].length - 1];
        }

        /**
         * 打印出最小和路径
         */
        public String printMinPath(int[][] grid) {
            int[][] dp = new int[grid.length][grid[0].length]; //所经路线最小的和
            String[] pathDp = new String[grid.length * grid[0].length]; //保存各个格子的路径信息
            pathDp[0] = grid[0][0] + "";
            dp[0][0] = grid[0][0];
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++) {
                    if (i > 0 && j > 0) {
                        int upBlockSum = dp[i - 1][j];
                        int leftBlockSum = dp[i][j - 1];
                        if (upBlockSum < leftBlockSum) {
                            dp[i][j] = upBlockSum + grid[i][j];
                            pathDp[i * grid[0].length + j] = pathDp[i * grid[0].length + j - grid[0].length] + "," + grid[i][j];
                        } else {
                            dp[i][j] = leftBlockSum + grid[i][j];
                            pathDp[i * grid[0].length + j] = pathDp[i * grid[0].length + j - 1] + "," + grid[i][j];
                        }

                    } else if (i > 0) {
                        dp[i][j] = dp[i - 1][j] + grid[i][j];
                        pathDp[i * grid[0].length + j] = pathDp[i * grid[0].length + j - grid[0].length] + "," + grid[i][j];
                    } else if (j > 0) {
                        dp[i][j] = dp[i][j - 1] + grid[i][j];
                        pathDp[j] = pathDp[j - 1] + "," + grid[i][j];
                    }
                }
            }
            return pathDp[grid.length * grid[0].length - 1];
        }

        public void markMinPath(int[][] grid) {
            this.markMinPath(grid, grid.length - 1, grid[0].length - 1);
        }

        public void markMinPath(int[][] grid, int x, int y) {
            if (x < 0 || y < 0) {
                return;
            } else if (x >= grid.length || y >= grid[0].length) {
                return;
            }
            int[][] rsltgrid = new int[grid.length][];
            for (int i = 0; i < rsltgrid.length; i++) {
                rsltgrid[i] = Arrays.copyOf(grid[i], grid[i].length);
            }
            int[][] dp = new int[grid.length][grid[0].length]; //所经路线最小的和
            String[] pathDp = new String[grid.length * grid[0].length]; //保存各个格子的路径信息
            pathDp[0] = "(0,0)";
            dp[0][0] = grid[0][0];
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++) {
                    if (i > 0 && j > 0) {
                        int upBlockSum = dp[i - 1][j];
                        int leftBlockSum = dp[i][j - 1];
                        if (upBlockSum < leftBlockSum) {
                            dp[i][j] = upBlockSum + grid[i][j];
                            pathDp[i * grid[0].length + j] = pathDp[i * grid[0].length + j - grid[0].length] + ";" + "(" + i + "," + j + ")";
                        } else {
                            dp[i][j] = leftBlockSum + grid[i][j];
                            pathDp[i * grid[0].length + j] = pathDp[i * grid[0].length + j - 1] + ";" + "(" + i + "," + j + ")";
                        }

                    } else if (i > 0) {
                        dp[i][j] = dp[i - 1][j] + grid[i][j];
                        pathDp[i * grid[0].length + j] = pathDp[i * grid[0].length + j - grid[0].length] + ";" + "(" + i + "," + j + ")";
                        ;
                    } else if (j > 0) {
                        dp[i][j] = dp[i][j - 1] + grid[i][j];
                        pathDp[j] = pathDp[j - 1] + ";" + "(" + i + "," + j + ")";
                    }
                }
            }
            //解析坐标点
            java.util.regex.Pattern r = java.util.regex.Pattern.compile("\\((\\d+),(\\d+)\\)");
            String[] pathPoint = pathDp[x * grid.length + y].split(";");
            for (int i = 0; i < pathPoint.length; i++) {
                try {
                    Matcher m = r.matcher(pathPoint[i]);
                    if (m.find()) {
                        int xPoint = Integer.parseInt(m.group(1));
                        int yPoint = Integer.parseInt(m.group(2));
                        rsltgrid[xPoint][yPoint] = -1;
                    }
                } catch (Exception e) {
                    //吞掉所有异常
                }
            }
            System.out.println("原grid方块：");
            System.out.println(printTwoDimensionArray(grid));
            System.out.println("标记最短路径grid方块：");
            System.out.println(printTwoDimensionArray(rsltgrid));
        }

        public String printTwoDimensionArray(int[][] grid) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (j == 0) {
                        sb.append(grid[i][j]);
                    } else {
                        sb.append(",").append(grid[i][j]);
                    }
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}