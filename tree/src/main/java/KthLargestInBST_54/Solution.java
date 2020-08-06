package KthLargestInBST_54;

import base.TreeNode;

public class Solution {

    int value;

    Integer index;

    public Integer kthLargest(TreeNode root,int k){


       if(root==null){
          return null;
       }

       Integer right=kthLargest(root.right,k);

       if(right==null && index==null){
           index=1;
       }else{
           index+=1;
       }

       return null;


    }



}
