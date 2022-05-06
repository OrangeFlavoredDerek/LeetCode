package 面试高频题;

import java.util.*;

public class MinimumNumOfK {
    //堆排序，优先队列
    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> reuslt = new ArrayList<>();

        if (input.length == 0 || k == 0) {
            return reuslt;
        }
        //创建一个大根堆
        PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));
        //构建一个k大小的堆
        for (int i = 0; i < k; i++) {
            q.offer(input[i]);
        }

        for (int i = k; i < input.length; i++) {
            //较小元素入堆
            if (q.peek() > input[i]) {
                q.poll();
                q.offer(input[i]);
            }
        }

        for (int i = 0; i < k; i++) {
            reuslt.add(q.poll());
        }

        return reuslt;
    }

    public static void main(String[] args) {
        int[] input = new int[] {4,5,1,6,2,7,3,8};
        GetLeastNumbers_Solution(input, 4);
    }
}
