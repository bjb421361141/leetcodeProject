package leetcode.editor.cn.structs;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(Integer[] vals) {
        this.val = vals[0];
        //处理余下的元素
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        treeNodes.add(this);
        this.createTreeNode(vals, treeNodes, 1, 1, 2);
    }

    private void createTreeNode(Integer[] vals, List<TreeNode> treeNodes, int level, int startIdx, int endIdx) {
        if (endIdx >= vals.length) {
            return;
        }
        List<TreeNode> nextTreeNodes = new ArrayList<>();
        int nodeIdx = 0;
        for (TreeNode curr : treeNodes) {
            int currLeftIdx = startIdx + nodeIdx * 2;
            int currRightIdx = startIdx + nodeIdx * 2 + 1;
            if (vals[currLeftIdx] != null) {
                curr.left = new TreeNode(vals[currLeftIdx]);
            }
            if (vals[currRightIdx] != null) {
                curr.right = new TreeNode(vals[currRightIdx]);
            }
            nextTreeNodes.add(curr.left);
            nextTreeNodes.add(curr.right);
            nodeIdx++;
        }
        int size = new Double(Math.pow(2, level + 1)).intValue();
        int start = endIdx + 1;
        int end = endIdx + size;
        createTreeNode(vals, nextTreeNodes, level + 1, start, end);
    }


    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
