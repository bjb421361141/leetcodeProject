//给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。 
//
// 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。 
//
// 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。 
//
// 
//
// 示例 1： 
//
// 
//输入：[7,1,5,3,6,4]
//输出：5
//解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
// 
//
// 示例 2： 
//
// 
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 105 
// 0 <= prices[i] <= 104 
// 
// Related Topics 数组 动态规划 
// 👍 1808 👎 0

package leetcode.editor.cn;

public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStock().new Solution();
        solution.maxProfit(new int[]{1,2,4,2,5,7,2,4,9,0});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
            try {
                Status rsStatus = this.getArrayMaxProfit(prices, 0, prices.length - 1);
                System.out.println(rsStatus.value);
                return rsStatus.value;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }

        private Status getArrayMaxProfit(int[] prices, int startIdx, int endIdx) throws Exception {
            if (startIdx == endIdx) {
                return new Status(startIdx, endIdx, 0);
            }
            int midIdx = ((endIdx - startIdx) >> 1) + startIdx;
            Status lStatus = getArrayMaxProfit(prices, startIdx, midIdx);
            Status rStatus = getArrayMaxProfit(prices, midIdx + 1, endIdx);
//            算出这个集合中的最大利润
            int lvalue = lStatus.value;
            int mvalue = prices[rStatus.maxIdx] - Math.min(prices[lStatus.minIdx], prices[rStatus.minIdx]);
            int rvalue = rStatus.value;
            if (mvalue >= lvalue && mvalue >= rvalue) {
                return new Status(prices[lStatus.minIdx] < prices[rStatus.minIdx] ? lStatus.minIdx : rStatus.minIdx, rStatus.maxIdx, mvalue);
            } else if (lvalue >= mvalue && lvalue >= rvalue) {
                return lStatus;
            } else {
                return rStatus;
            }
        }

        class Status {
            int minIdx, maxIdx, value;

            public Status(int minIdx, int maxIdx, int value) throws Exception {
                if (minIdx > maxIdx) {
                    throw new Exception("异常");
                }
                this.minIdx = minIdx;
                this.maxIdx = maxIdx;
                this.value = value;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}