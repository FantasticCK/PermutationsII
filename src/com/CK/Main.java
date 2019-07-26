package com.CK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        Solution solution = new Solution();
        System.out.println(solution.permuteUnique(nums).toString());
    }
}

class Solution {
    List<List<Integer>> resList = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) return resList;
        boolean[] visited = new boolean[nums.length];
        List<Integer> dfsList = new ArrayList<>();
        helper(nums, dfsList, visited);
        return resList;
    }

    private void helper(int[] nums, List<Integer> dfsList, boolean[] visited) {
        if (dfsList.size() == nums.length) {
            boolean exist = false;
            for (int r = 0; r < resList.size(); r++) {
                exist = true;
                for (int index = 0; index < resList.get(r).size(); index++) {
                    if (!resList.get(r).get(index).equals(dfsList.get(index))) {
                        exist = false;
                    }
                }
                if (exist) break;
            }
            if (!exist || resList.isEmpty()) resList.add(new ArrayList<>(dfsList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    dfsList.add(nums[i]);
                    helper(nums, dfsList, visited);
                    visited[i] = false;
                    dfsList.remove(dfsList.size() - 1);
                }
            }
        }
    }
}

class Solution2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
                used[i] = true;
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}