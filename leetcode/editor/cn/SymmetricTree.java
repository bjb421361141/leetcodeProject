//给定一个二叉树，检查它是否是镜像对称的。 
//
// 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
// 
//
// 
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// 进阶： 
//
// 你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 1526 👎 0

package leetcode.editor.cn;

import leetcode.editor.cn.structs.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class SymmetricTree {
    public static void main(String[] args) {
//        Solution solution = new SymmetricTree().new Solution();
        Solution1 solution = new SymmetricTree().new Solution1();
//        TreeNode root = new TreeNode(new Integer[]{1, 2, 2, 3, 4, 4, 3});
        TreeNode root = new TreeNode(new Integer[]{1, 2, 2, null, 3, null, 3});
        System.out.println(solution.isSymmetric(root));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    //  Definition for a binary tree node.


    class Solution {
        TreeNode defaultTreeNode = new TreeNode(Integer.MIN_VALUE);

        public boolean isSymmetric(TreeNode root) {
            Deque<TreeNode> queen = new ArrayDeque<>();
            queen.add(root);
            while (!queen.isEmpty()) {
                int size = queen.size();
                int[] tmpArr = new int[size];
                boolean numflag = false;  //判断是否有存在treeNode
                for (int i = 0; i < size; i++) {
                    TreeNode curr = queen.poll(); //取出头部元素
                    if (curr.val != Integer.MIN_VALUE) {
                        numflag = true;
                    }
                    tmpArr[i] = curr.val;
                    queen.offer(curr.left != null ? curr.left : defaultTreeNode);
                    queen.offer(curr.right != null ? curr.right : defaultTreeNode);
                }
                if (!numflag) {  //全都是最小值就是最后一层
                    break;
                }
                if (!isSymmetricArr(tmpArr)) {
                    return false;
                }
            }
            return true;
        }

        public boolean isSymmetricArr(int[] arrs) {
            int start = 0;
            int end = arrs.length - 1;
            while (start <= end) {
                if (arrs[start] != arrs[end]) {
                    return false;
                }
                start++;
                end--;
            }
            return true;
        }
    }

    class Solution1 {

        public boolean isSymmetric(TreeNode root) {
            return isTwoTreeSymmetric(root.left, root.right);
        }

        /**
         * 比较两个元素是否相同
         *
         * @param leftNode  左边元素
         * @param rightNode 右边元素
         * @return 如果两者一样 返回true 否则返回false
         */
        private boolean isTwoTreeSymmetric(TreeNode leftNode, TreeNode rightNode) {
            if ((leftNode == null && rightNode != null) || (leftNode != null && rightNode == null)) {
                return false;
            } else if (leftNode == null) { //左元素为空 则都为空
                return true;
            }
            //比较左边的做元素和右边的右元素是否一致
            if (!isTwoTreeSymmetric(leftNode.left, rightNode.right)) {
                return false;
            }
            if (leftNode.val != rightNode.val) {
                return false;
            }
            if (!isTwoTreeSymmetric(leftNode.right, rightNode.left)) {
                return false;
            }
            return true;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}