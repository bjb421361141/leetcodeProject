//翻转一棵二叉树。 
//
// 示例： 
//
// 输入： 
//
//      4
//   /   \
//  2     7
// / \   / \
//1   3 6   9 
//
// 输出： 
//
//      4
//   /   \
//  7     2
// / \   / \
//9   6 3   1 
//
// 备注: 
//这个问题是受到 Max Howell 的 原问题 启发的 ： 
//
// 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 986 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.structs.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class InvertBinaryTree {
    public static void main(String[] args) {
        Solution solution = new InvertBinaryTree().new Solution();
//        TreeNode root = new TreeNode(new Integer[]{1, 2, 2, 3, 4, 4, 3});
        TreeNode root = new TreeNode(new Integer[]{1, 2, 3, null, 4, null, 5});
        root = solution.invertTree(root);
        System.out.println("n");
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    //  Definition for a binary tree node.

    class Solution {

        /**
         * 后续遍历最后返回 root
         *
         * @param root
         * @return
         */
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            this.switchTreeNode(root.left);
            this.switchTreeNode(root.right);
            this.switchTreeNode(root);
            return root;
        }

        /**
         * 对换两个元素
         *
         * @param parentNode 左边元素
         */
        private void switchTreeNode(TreeNode parentNode) {
            if (parentNode == null) {
                return;
            }
            TreeNode tmpNode = parentNode.left;
            parentNode.left = parentNode.right;
            parentNode.right = tmpNode;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}