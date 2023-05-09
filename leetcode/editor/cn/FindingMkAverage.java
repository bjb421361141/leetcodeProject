//给你两个整数 m 和 k ，以及数据流形式的若干整数。你需要实现一个数据结构，计算这个数据流的 MK 平均值 。 
//
// MK 平均值 按照如下步骤计算： 
//
// 
// 如果数据流中的整数少于 m 个，MK 平均值 为 -1 ，否则将数据流中最后 m 个元素拷贝到一个独立的容器中。 
// 从这个容器中删除最小的 k 个数和最大的 k 个数。 
// 计算剩余元素的平均值，并 向下取整到最近的整数 。 
// 
//
// 请你实现 MKAverage 类： 
//
// 
// MKAverage(int m, int k) 用一个空的数据流和两个整数 m 和 k 初始化 MKAverage 对象。 
// void addElement(int num) 往数据流中插入一个新的元素 num 。 
// int calculateMKAverage() 对当前的数据流计算并返回 MK 平均数 ，结果需 向下取整到最近的整数 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：
//["MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement", 
//"calculateMKAverage", "addElement", "addElement", "addElement", 
//"calculateMKAverage"]
//[[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
//输出：
//[null, null, null, -1, null, 3, null, null, null, 5]
//
//解释：
//MKAverage obj = new MKAverage(3, 1); 
//obj.addElement(3);        // 当前元素为 [3]
//obj.addElement(1);        // 当前元素为 [3,1]
//obj.calculateMKAverage(); // 返回 -1 ，因为 m = 3 ，但数据流中只有 2 个元素
//obj.addElement(10);       // 当前元素为 [3,1,10]
//obj.calculateMKAverage(); // 最后 3 个元素为 [3,1,10]
//                          // 删除最小以及最大的 1 个元素后，容器为 [3]
//                          // [3] 的平均值等于 3/1 = 3 ，故返回 3
//obj.addElement(5);        // 当前元素为 [3,1,10,5]
//obj.addElement(5);        // 当前元素为 [3,1,10,5,5]
//obj.addElement(5);        // 当前元素为 [3,1,10,5,5,5]
//obj.calculateMKAverage(); // 最后 3 个元素为 [5,5,5]
//                          // 删除最小以及最大的 1 个元素后，容器为 [5]
//                          // [5] 的平均值等于 5/1 = 5 ，故返回 5
// 
//
// 
//
// 提示： 
//
// 
// 3 <= m <= 10⁵ 
// 1 <= k*2 < m 
// 1 <= num <= 10⁵ 
// addElement 与 calculateMKAverage 总操作次数不超过 10⁵ 次。 
// 
//
// Related Topics 设计 队列 数据流 有序集合 堆（优先队列） 👍 104 👎 0


package leetcode.editor.cn;

import java.util.*;

public class FindingMkAverage {
    public static void main(String[] args) {
        int rsl;
        MKAverage solution = new FindingMkAverage().new MKAverage(3, 1);
        solution.addElement(17612);
        solution.addElement(74607);
        rsl = solution.calculateMKAverage();
        System.out.println(rsl);
        solution.addElement(8272);
        solution.addElement(33433);
        rsl = solution.calculateMKAverage();
        System.out.println(rsl);
        solution.addElement(15456);
        solution.addElement(64938);
        rsl = solution.calculateMKAverage();
        System.out.println(rsl);
        solution.addElement(99741);

//        [[3,1],[17612],[74607],[],[8272],[33433],[],[15456],[64938],[],[99741]]

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MKAverage {
        int m, k;
        Queue<Integer> queue = new ArrayDeque<>(); //用于存放最后m个数
        TreeMap<Integer, Integer> s1, s2, s3; //key:数值 value:次数 分别存放最低值，中间值，最高值
        int s1Size, s2Size, s3Size;
        int s2Sum; //中间值的和，避免中间值过多产生计算


        /**
         * 1 <= k < 2*k < m
         *
         * @param m
         * @param k
         */
        public MKAverage(int m, int k) {
            this.m = m;
            this.k = k;
            s1 = new TreeMap<>();
            s2 = new TreeMap<>();
            s3 = new TreeMap<>();
            s1Size = s2Size = s3Size = 0;
            s2Sum = 0;
        }

        /**
         * 在添加时做好数据的区分
         *
         * @param num
         */
        public void addElement(int num) {
            queue.offer(num); //添加数据到容器
            if (queue.size() <= m) { //当值小于、等于m
                this.addSTreeElement(s2, num, "s2");
                s2Sum += num;
                if (queue.size() == m) {
                    int count = 0;
                    while (count < k) {
                        int minNum = s2.firstKey();
                        int maxNum = s2.lastKey();
                        this.removeSTreeElement(s2, minNum, "s2");
                        this.addSTreeElement(s1, minNum, "s1");
                        s2Sum -= minNum;

                        this.removeSTreeElement(s2, maxNum, "s2");
                        this.addSTreeElement(s3, maxNum, "s3");
                        s2Sum -= maxNum;
                        count++;
                    }
                }
            } else { // m+1 的情况
                //添加数据，并保持最大最小容器数量一样
                if (s1.lastKey() > num) {
                    int s1MaxNum = s1.lastKey();
                    this.addSTreeElement(s1, num, "s1");
                    this.removeSTreeElement(s1, s1MaxNum, "s1");
                    this.addSTreeElement(s2, s1MaxNum, "s2");
                    s2Sum += s1MaxNum;
                } else if (s3.firstKey() < num) {
                    int s3MinNum = s3.firstKey();
                    this.addSTreeElement(s3, num, "s3");
                    this.removeSTreeElement(s3, s3MinNum, "s3");
                    this.addSTreeElement(s2, s3MinNum, "s2");
                    s2Sum += s3MinNum;
                } else {
                    this.addSTreeElement(s2, num, "s2");
                    s2Sum += num;
                }
                //移除一个数
                int deleteNum = queue.poll();
                if (s1.containsKey(deleteNum)) {
                    this.removeSTreeElement(s1, deleteNum, "s1");
                    int min = s2.firstKey();
                    this.removeSTreeElement(s2, min, "s2");
                    s2Sum -= min;
                    this.addSTreeElement(s1, min, "s1");
                } else if (s3.containsKey(deleteNum)) {
                    this.removeSTreeElement(s3, deleteNum, "s3");
                    int max = s2.lastKey();
                    this.removeSTreeElement(s2, max, "s2");
                    s2Sum -= max;
                    this.addSTreeElement(s3, max, "s3");
                } else if (s2.containsKey(deleteNum)) {
                    this.removeSTreeElement(s2, deleteNum, "s2");
                    s2Sum -= deleteNum;
                }
            }

        }

        private void addSTreeElement(TreeMap<Integer, Integer> treeMap, Integer element, String labelMark) {
            int times = treeMap.getOrDefault(element, 0);
            treeMap.put(element, ++times);
            if ("s1".equals(labelMark)) {
                s1Size++;
            } else if ("s2".equals(labelMark)) {
                s2Size++;
            } else if ("s3".equals(labelMark)) {
                s3Size++;
            }
        }

        private void removeSTreeElement(TreeMap<Integer, Integer> treeMap, Integer delElement, String labelMark) {
            int times = treeMap.getOrDefault(delElement, 0);
            if (times != 0) {
                times--;
                if (times == 0) {
                    treeMap.remove(delElement); //移除对象
                } else {
                    treeMap.put(delElement, times);
                }
                if ("s1".equals(labelMark)) {
                    s1Size--;
                } else if ("s2".equals(labelMark)) {
                    s2Size--;
                } else if ("s3".equals(labelMark)) {
                    s3Size--;
                }
            }
        }

        public int calculateMKAverage() {
            if (queue.size() < m) {
                return -1;
            }
            return s2Sum / (m - 2 * k);
        }
    }

/**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage obj = new MKAverage(m, k);
 * obj.addElement(num);
 * int param_2 = obj.calculateMKAverage();
 */
//leetcode submit region end(Prohibit modification and deletion)

}