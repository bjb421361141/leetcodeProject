//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。 
//
// 
//
// 示例： 
//二叉树：[3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层序遍历结果： 
//
// 
//[
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索 二叉树 
// 👍 993 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.structs.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new BinaryTreeLevelOrderTraversal().new Solution();
        TreeNode root = new TreeNode(new Integer[]{1, 2, 2, 3, 4, 4, 3});
//        System.out.println(root);
        List<List<Integer>> ansList = solution.levelOrder(root);
        for (List<Integer> subList : ansList) {
            for (Integer i : subList) {
                System.out.print(i + ",");
            }
            System.out.println("");
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)


    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            Map<Integer, List<Integer>> levelMap = new HashMap<>();
            this.getTreeNodeLevelInf(root, levelMap, 0);
            List<List<Integer>> ans = new ArrayList<>(levelMap.size());
            for (Map.Entry<Integer, List<Integer>> entry : levelMap.entrySet()) {
                ans.add(entry.getKey(), entry.getValue());
            }
            return ans;
        }

        /**
         * 根据树节点 将层级元素归到同一个list中
         *
         * @param root
         * @param levelMap
         * @param level
         */
        private void getTreeNodeLevelInf(TreeNode root, Map<Integer, List<Integer>> levelMap, int level) {
            if (root == null) {
                return;
            }
            if (!levelMap.containsKey(level)) {
                List<Integer> list = new ArrayList<>();
                list.add(root.val);
                levelMap.put(level, list);
            } else {
                levelMap.get(level).add(root.val);
            }
            getTreeNodeLevelInf(root.left, levelMap, level + 1);
            getTreeNodeLevelInf(root.right, levelMap, level + 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}