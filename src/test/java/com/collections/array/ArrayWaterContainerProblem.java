package com.collections.array;

public class ArrayWaterContainerProblem {
    /**
     * Returns the max area using two pointers approach
     * Time Complexity : O(n)
     *
     * @param height
     * @return maxArea
     */
    public int getMaxAreaUsingTwoPointers(int[] height) {
        int maxArea = 0;
        int i = 0, j = height.length - 1;

        while (i < j) {
            maxArea = Math.max(maxArea, (j - i) * Math.min(height[i], height[j]));
            if (height[i] > height[j]) {
                i--;
                j--;
            } else {
                i++;
            }
        }

        return maxArea;
    }

    /**
     * Returns the max area using brute force approach
     * Time Complexity - O(n2)
     *
     * @param height
     * @return maxArea
     */
    public int getMaxAreaUsingBruteForceApproach(int[] height) {
        int maxArea = 0;

        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                maxArea = Math.max(maxArea, (j - i) * Math.min(height[i], height[j]));
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] inputHeightArray = new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new ArrayWaterContainerProblem().getMaxAreaUsingTwoPointers(inputHeightArray));
        System.out.println(new ArrayWaterContainerProblem().getMaxAreaUsingBruteForceApproach(inputHeightArray));
    }
}
