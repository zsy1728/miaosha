package leetcode;

import java.lang.reflect.Array;
import java.util.*;

public class Test {
    public int[][] merge(int[][] intervals){
        if (intervals.length == 0)
            return new int[0][2];
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++)
            dp[0][i] = 1;
        for (int i = 0; i < m; i++)
            dp[i][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
    public void sort(int[] array) {
        quick(array,0,array.length-1);
    }
    void quick(int[] a, int low, int high){
        if(low >= high) return;
        int l = low, r = high;
        int remix = a[low];
        while(l < r){
            while(l < r && a[r] > remix) r--;
            if(l < r){
                a[l] = a[r];
                l++;
            }
            while(l < r && a[l] < remix) l++;
            if(l < r){
                a[r] = a[l];
                r--;
            }
        }
        if(l == r) a[l] = remix;
        quick(a,low,l - 1);
        quick(a,l + 1,high);
    }
    public void sortColors(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }
        int l = 0, r = len;
        int i = 0;
        while(i < r){
            if(nums[i] == 0){
                swap(nums,l, i);
                l ++;
                i ++;
            }
            if(nums[i] == 1){
                i++;
            }
            if(nums[i] == 2){
                r --;
                swap(nums,r,i);
            }
        }
    }
    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }



    /**
     * 大数相乘方法二
     */
    public static String bigNumberMultiply2(String num1, String num2){
        // 分配一个空间，用来存储运算的结果，num1长的数 * num2长的数，结果不会超过num1+num2长
        int[] result = new int[num1.length() + num2.length()];

        // 先不考虑进位问题，根据竖式的乘法运算，num1的第i位与num2的第j位相乘，结果应该存放在结果的第i+j位上
        for (int i = 0; i < num1.length(); i++){
            for (int j = 0; j < num2.length(); j++){
                result[i + j + 1] += (num1.charAt(i)-'0') * (num2.charAt(j)-'0');	 // (因为进位的问题，最终放置到第i+j+1位)
            }
        }

        StringBuilder res= new StringBuilder();
        //单独处理进位
        for(int k = result.length-1; k > 0; k--){
            if(result[k] > 10){
                result[k - 1] += result[k] / 10;
                result[k] %= 10;
            }
            res.append(result[k]);
        }
        if(result[0] != 0)res.append(result[0]);
        return res.reverse().toString();
    }


    public static void main(String[] args) {
        String[] s = new String[1000006];
        String a = "2";
        String b = "3";
        String c = "7";
        for(int i=0; i<101; i++){
            for(int j=0; j<101; j++){
                for(int k=0; k<101; k++){

                }
            }
        }
        Arrays.sort(s);
    }

}
