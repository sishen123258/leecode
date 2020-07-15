package SearchBST_700;

import base.TreeNode;

public class Solution {

    TreeNode treeNode;

    public TreeNode searchBST(TreeNode root, int val) {


        if(root==null){
            return null;
        }

        if(root.val>val){
            searchBST(root.left,val);
        }

        if(root.val==val){
            treeNode=root;
        }

        if(root.val<val){
            searchBST(root.right,val);
        }

        return treeNode;
    }

}
