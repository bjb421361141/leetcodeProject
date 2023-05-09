//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 104 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 104 
// 
// Related Topics 数组 排序 
// 👍 1138 👎 0

package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

public class MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,3},{2,6},{8,10}, {15,18}};
        Solution solution = new MergeIntervals().new Solution();
        int[][] ans = solution.merge(intervals);
        System.out.println(ans);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] merge(int[][] intervals) {
            sortInterval(intervals); //根据首个元素先排序 后合并
            Deque<int[]> ansQueue = new ArrayDeque<>();
            int[] tmp = null;
            for (int i = 0; i < intervals.length; i++) {
                if (tmp == null) {
                    tmp = intervals[i];
                    continue;
                }
                //隐含条件 tmp[0] < intervals[i][0]
                if (tmp[1] < intervals[i][0]) { //没有交集
                    ansQueue.offer(tmp);
                    tmp = intervals[i];
                } else {
                    if (tmp[1] < intervals[i][1]) {
                        tmp[1] = intervals[i][1];
                    }
                }
            }
            ansQueue.offer(tmp);
            int size = ansQueue.size();
            int[][] ans = new int[size][];
            for (int i = 0; i < size; i++) {
                ans[i] = ansQueue.pollFirst();
            }
            return ans;
        }

        private void sortInterval(int[][] intervals) {
            for (int i = 0; i < intervals.length; i++) {
                int minVal = intervals[i][0];
                int minValIdx = i;
                int currentIdx = i + 1;
                while (currentIdx < intervals.length) {
                    if (intervals[currentIdx][0] < minVal) {
                        minVal = intervals[currentIdx][0];
                        minValIdx = currentIdx;
                    }
                    currentIdx++;
                }
                if (i != minValIdx) {
                    int[] tmp = intervals[i];
                    intervals[i] = intervals[minValIdx];
                    intervals[minValIdx] = tmp;
                }
            }
        }
    }


    class Solution1 {
        /**
         * 标准答案
         */
        public int[][] merge(int[][] intervals) {
            if (intervals.length == 0) {
                return new int[0][2];
            }
            Arrays.sort(intervals, new Comparator<int[]>() {
                public int compare(int[] interval1, int[] interval2) {
                    return interval1[0] - interval2[0];
                }
            });
            List<int[]> merged = new ArrayList<int[]>();
            for (int i = 0; i < intervals.length; ++i) {
                int L = intervals[i][0], R = intervals[i][1];
                if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                    merged.add(new int[]{L, R});
                } else {
                    merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
                }
            }
            return merged.toArray(new int[merged.size()][]);
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}