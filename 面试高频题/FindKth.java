package 面试高频题;

import java.util.PriorityQueue;

public class FindKth {
    public int findKth(int[] a, int n, int K) {
        int result = 0;

        if (n == 0 || K == 0) {
            return result;
        }
        PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));

        for (int i = 0; i < n; i++) {
            q.offer(a[i]);
        }

        for (int i = 0; i < K-1; i++) {
            q.poll();
        }

        return q.peek();
    }

    public static void main(String[] args) {
        FindKth FK = new FindKth();
        int[] a = new int[] {10,10,9,9,8,7,5,6,4,3,4,2};
        System.out.println(FK.findKth(a, 12, 9));
    }
}
