//给你一个整数数组 digits，你可以通过按任意顺序连接其中某些数字来形成 3 的倍数，请你返回所能得到的最大的 3 的倍数。 
//
// 由于答案可能不在整数数据类型范围内，请以字符串形式返回答案。 
//
// 如果无法得到答案，请返回一个空字符串。 
//
// 
//
// 示例 1： 
//
// 输入：digits = [8,1,9]
//输出："981"
// 
//
// 示例 2： 
//
// 输入：digits = [8,6,7,1,0]
//输出："8760"
// 
//
// 示例 3： 
//
// 输入：digits = [1]
//输出：""
// 
//
// 示例 4： 
//
// 输入：digits = [0,0,0,0,0,0]
//输出："0"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= digits.length <= 10^4 
// 0 <= digits[i] <= 9 
// 返回的结果不应包含不必要的前导零。 
// 
//
// Related Topics 贪心 数组 动态规划 👍 78 👎 0


package leetcode.editor.cn;

import java.util.*;

public class LargestMultipleOfThree {
    public static void main(String[] args) {
//        Solution solution = new LargestMultipleOfThree().new Solution();
        Solution1 solution = new LargestMultipleOfThree().new Solution1();
//        System.out.println(solution.largestMultipleOfThree(new int[]{8, 1, 9}));
        System.out.println(solution.largestMultipleOfThree(new int[]{8, 6, 7, 1, 0}));
//        System.out.println(solution.largestMultipleOfThree(new int[]{1}));
//        System.out.println(solution.largestMultipleOfThree(new int[]{0, 0, 0, 0, 0, 0}));
//        System.out.println(solution.largestMultipleOfThree(new int[]{1, 1, 1}));
//        System.out.println(solution.largestMultipleOfThree(new int[]{1, 1, 1, 2}));
//        System.out.println(solution.largestMultipleOfThree(new int[]{1, 1, 1, 2, 2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String largestMultipleOfThree(int[] digits) {
            List<Integer>[] rslLst = new ArrayList[3];
            for (int i = 0; i < 3; i++) {
                rslLst[i] = new ArrayList<>();
            }
            int sum = 0;
            for (int i = 0; i < digits.length; i++) {
                rslLst[digits[i] % 3].add(digits[i]);
                sum += digits[i];
            }
            for (int i = 0; i < rslLst.length; i++) {
                Collections.sort(rslLst[i], Collections.reverseOrder());
            }
            //如果余数为1 则 去除一个余数为1的数 或者去除两个余数为2的数
            //如果余数为2 则 去除一个余数为2的数 或者去除两个余数为1的数
            int ys = sum % 3;
            if (ys != 0) {
                if (rslLst[ys].size() > 0) { //同余数组有值，去除一个数字
                    rslLst[ys].remove(rslLst[ys].size() - 1);
                } else if (rslLst[3 - ys].size() > 1) {
                    rslLst[3 - ys].remove(rslLst[3 - ys].size() - 1);
                    rslLst[3 - ys].remove(rslLst[3 - ys].size() - 1);
                } else {
                    return "";
                }
            }
            if (rslLst[0].isEmpty() && rslLst[1].isEmpty() && rslLst[2].isEmpty()) {
                return "";
            } else {
                List<Integer> maxNumArr = new ArrayList<>(rslLst[0]);
                maxNumArr.addAll(rslLst[1]);
                maxNumArr.addAll(rslLst[2]);
                //排序然后再进行处理最大值输出
                maxNumArr.sort((o1, o2) -> {
                    return Integer.compare(o2, o1);
                });
                StringBuilder sb = new StringBuilder();
                for (Integer integer : maxNumArr) {
                    sb.append(integer);
                    if ('0' == sb.charAt(0)) {
                        return "0";
                    }
                }
                return sb.toString();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 使用dp数组进行解决 fixme 这题等后面学完动态规划思路再进行解决
     *
     * 如何确定可以使用动态规划来求解问题
     * 如何确定本题的状态定义
     * 如何确定状态转移方程
     * 对状态转移的要求是什么
     * 如何分析动态规划的时间复杂度
     *
     * 使用dp数组的本质是，问题可以转为数组描述，且解可以用数组展示
     */
    class Solution1 {
        public String largestMultipleOfThree(int[] digits) {
//            int n = digits.length;
//            Arrays.sort(digits);
//            String[][] dp = new String[n+1][3];
//            dp[0][0] = "0";
//            dp[0][1] = "";
//            dp[0][2] = "";
//            for(int i=1;i<=n;i++) {
//                for (int j = 2; j >= 0; j--)
//                    if(dp[i - 1][j].length() < (dp[i - 1][(j - digits[i - 1] % 3 + 3) % 3] +digits[i-1]).length()) {
//                        dp[i][j] = dp[i - 1][(j - digits[i - 1] % 3 + 3) % 3] +digits[i-1];
//                    } else {
//                        dp[i][j] = dp[i - 1][j];
//                    }
//            }
//            for(int i=1;i<=n;i++) {
//                for (int j = 0; j <= 2; j++){
//                    System.out.print(dp[i][j]+",");
//                }
//                System.out.println();
//            }
//
            return "";
        }
    }

}