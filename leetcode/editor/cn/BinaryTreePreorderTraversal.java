//给你二叉树的根节点 root ，返回它节点值的 前序 遍历。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,2,3]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 
// 👍 636 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * DLR--前序遍历（根在前，从左往右，一棵树的根永远在左子树前面，左子树又永远在右子树前面 ）
 * LDR--中序遍历（根在中，从左往右，一棵树的左子树永远在根前面，根永远在右子树前面）
 * LRD--后序遍历（根在后，从左往右，一棵树的左子树永远在右子树前面，右子树永远在根前面）
 */
public class BinaryTreePreorderTraversal {
    public static void main(String[] args) {
        Solution solution = new BinaryTreePreorderTraversal().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    //Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        /**
         * 树节点遍历
         *
         * @param root
         * @return
         */
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> preOrderTreeNode = new ArrayList<>();
            this.getDLRTreeNode(root, preOrderTreeNode);
            return preOrderTreeNode;
        }

        /**
         * 前序遍历
         */
        private void getDLRTreeNode(TreeNode root, List<Integer> ansList) {
            if (root == null) {
                return;
            }
            ansList.add(root.val);
            getDLRTreeNode(root.left, ansList);
            getDLRTreeNode(root.right, ansList);
        }


        /**
         * 中序遍历
         */
        private void getLDRTreeNode(TreeNode root, List<Integer> ansList) {
            if (root == null) {
                return;
            }
            getLDRTreeNode(root.left, ansList);
            ansList.add(root.val);
            getLDRTreeNode(root.right, ansList);
        }

        /**
         * 后序遍历
         */
        private void getLRDTreeNode(TreeNode root, List<Integer> ansList) {
            if (root == null) {
                return;
            }
            getLRDTreeNode(root.left, ansList);
            getLRDTreeNode(root.right, ansList);
            ansList.add(root.val);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}