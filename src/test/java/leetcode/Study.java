package leetcode;

import java.util.*;

public class Study {
    public int trap(int[] height){
        Stack<Integer> stack = new Stack<>();
        int water = 0;
        if(height.length < 3) return 0;
        for (int i = 0; i < height.length; i++) {
            while(!stack.isEmpty() && height[i] > height[stack.peek()]){
                int popNum = stack.pop();
                while (!stack.isEmpty() && height[popNum] == height[stack.peek()]){
                    stack.pop();
                }
                if(!stack.isEmpty()){
                    int temp = height[stack.peek()];
                    int hi = Math.min(temp - height[popNum],height[i] - height[popNum]);
                    int wid = i - stack.peek() - 1;
                    water += hi * wid;
                }
            }
            stack.push(i);
        }
        return water;
    }

    public int largestRectangleArea1(int[] heights) {
        int n = heights.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int now = heights[i];
            int temp = 0;
            int index = 0;
            while(index < n){
                if(heights[index] >= now){
                    int tt = 0;
                    while(index < n && heights[index] >= now){
                        tt += now;
                        index ++;
                    }
                    temp = Math.max(tt,temp);
                }
                index++;
            }
            res = Math.max(res, temp);
        }
        return res;
    }
    public int largestRectangleArea(int[] heights) {
        int len = heights.length + 2;
        int[] newHeight = new int[len];
        newHeight[0] = newHeight[len - 1] = 0;
        // [1,2,3]->[0,1,2,3,0]
        for(int i = 1; i < len - 1; i++) {
            newHeight[i] = heights[i - 1];
        }
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < len; i++) {
            while(!stack.isEmpty() && newHeight[stack.peek()] > newHeight[i]){
                int pop = stack.pop();
                int w = i - stack.peek() - 1;
                int h = newHeight[pop];
                res = Math.max(res, w * h);
            }
            stack.push(i);
        }
        return res;
    }
    static class Res{
        int a,b;
        Res(int x,int y){
            a = x;
            b = y;
        }
    }

    boolean[] isChose = null;
    void dfs(int[][] isConnected, int now, int n){
        isChose[now] = true;
        for(int i = now, j = 0; j < n ; j++) {
            if(isConnected[i][j] == 1 && !isChose[j])
                dfs(isConnected, j, n);
        }
    }
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        isChose = new boolean[n];
        int res = 0;
        Arrays.fill(isChose,false);
        for(int i = 0,j = 0; i < n ; i++, j++){
            if(!isChose[i]){
                dfs(isConnected, i, n);
                res ++;
            }
        }
        return res;
    }

    public static int myAtoi(String s) {
        char[] chs = s.toCharArray();
        int n = s.length();
        int i = 0;
        while(i < n && chs[i] == ' '){
            i ++;
        }
        if(i == n) return 0;
        int sign = 1;
        if(chs[i] == '-'){
            sign = -1;
            i++;
        }else if(chs[i] == '+'){
            i++;
        }
        int res = 0;
        while(i < n ){
            char cur = chs[i];
            if(cur > '9' || cur < '0')
                break;
            if(res > Integer.MAX_VALUE / 10 ||
                    (res == Integer.MAX_VALUE / 10 && cur - '0' > Integer.MAX_VALUE % 10))
                return Integer.MAX_VALUE;
            if(res < Integer.MIN_VALUE / 10 ||
                    (res == Integer.MIN_VALUE / 10 && cur - '0' > - (Integer.MIN_VALUE % 10)))
                return Integer.MIN_VALUE;
            res = res * 10 + sign * (cur - '0');
            i++;
        }

        return res ;

    }

    public static int translateNum(int num) {
        String str = String.valueOf(num);
        char[] chs =str.toCharArray();
        int n = chs.length;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2;i < n+1;i ++){
            int temp = Integer.parseInt(str.substring(i-2,i));
            if(temp > 25||temp<10)
                dp[i] = dp[i-1];
            else
                dp[i] = dp[i-1]+dp[i-2];
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    public int maxValue(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = grid[i][j] + Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }

        return dp[row-1][col-1];
    }
    public int search(int[] nums, int target) {
        int n = nums.length;
        int i = 0, j = n - 1;
        while (i < j){
            int m = (i + j) / 2;
            if(nums[m] >= target)
                j = m;
            else i = m + 1;
        }
        System.out.println(i);
        int p = i;
        i = 0; j = n - 1;
        while (i < j){
            int m = (i + j) / 2;
            if(nums[m] > target)
                j = m;
            else i = m + 1;
        }
        System.out.println(i);
        return i - p;
    }
    public int[] singleNumbers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if(set.contains(nums[i]))
                set.remove(nums[i]);
            else set.add(nums[i]);
        }
        int[] res = new int[2];
        Iterator<Integer> it = set.iterator();
        int i = 0;
        while(it.hasNext()){
            res[i] = it.next();
            i++;
        }
        return res;
    }
    public static void main(String[] args){

        Integer i1 = new Integer(448);
        Integer i2 = 448;
        System.out.println(i1.hashCode());
        System.out.println(i2.hashCode());
    }
    static boolean func(int a, int b){
        for (int i = 2; i <= Math.min(a,b); i++) {
            if(a%i==0 && b%i==0){
                return false; //不沪指
            }
        }
        return true;
    }

}
class Node {
    public int key, val;
    public Node next, prev;

    public Node(int k, int v) {
        this.key = k;
        this.val = v;
    }
}
class LRUCache {
    private Node head, tail;

    private int size, now;



    private HashMap<Integer,Node> hashMap = new HashMap<>();

    public LRUCache(int capacity) {
        this.size = capacity;
        this.now = 0;
    }

    public int get(int key) {
        if(hashMap.get(key) == null)
            return -1;
        else{
            Node node = hashMap.get(key);

            if(head.key != node.key){
                if(node.key == tail.key)
                    tail = tail.prev;


                Node prev = node.prev;
                Node next = node.next; //null
                node.prev = null;
                node.next = null;
                if(prev!=null)
                    prev.next = next;
                if(next!=null)
                    next.prev = prev;

                node.next = head;
                head.prev = node;
                head = node;
            }

            return node.val;
        }
    }

    public void put(int key, int value) {

        if(now == 0){
            Node node = new Node(key,value);
            node.prev = null;
            node.next = null;
            head = node;
            tail = node;
            hashMap.put(key,node);
            now++;
        }else {
            if(hashMap.containsKey(key)){
                hashMap.get(key).val = value;
                this.get(key);
            }else {
                if(now < size){
                    Node node = new Node(key,value);
                    node.prev = null;
                    node.next = head;
                    head.prev = node;
                    head = node;
                    hashMap.put(key,node);
                    now++;
                }else { // now == size
                    Node node = new Node(key,value);
                    node.prev = null;
                    node.next = head;
                    head.prev = node;
                    head = node;
                    int deadKey = tail.key;
                    Node deadNode = tail;
                    Node t = tail.prev;
                    tail.prev = null;
                    tail = t;
                    tail.next = null;
                    hashMap.put(key,node);
                    hashMap.remove(deadKey,deadNode);
                }

            }

        }

    }
}
