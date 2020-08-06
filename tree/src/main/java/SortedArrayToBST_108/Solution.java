package SortedArrayToBST_108;

import base.TreeNode;


class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {

        return doIt(nums, 0, nums.length - 1);
    }

    public TreeNode doIt(int[] nums, int start, int end) {

        if (start > end) {
            return null;
        }


        int midle = (end + start) / 2;
        int centeralVal = nums[midle];

        TreeNode node = new TreeNode(centeralVal);

        node.left = doIt(nums, start, midle - 1);
        node.right = doIt(nums, midle + 1, end);

        return node;

    }
}