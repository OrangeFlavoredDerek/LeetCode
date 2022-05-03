package 剑指Offer;

import java.util.ArrayList;

/**
 * 给定一个非负整数 n ，请计算 0 到 n 之间的每个数字的二进制表示中 1 的个数，并输出一个数组。
 */

public class TheNumberOf1InTheFirstNBinaryDigits {
    public int[] countBits(int n) {
        if (n == 0) {
            return new int[] {0};
        }
        ArrayList<Integer> resultList = new ArrayList<>();
        int count;
        for (int i = 0; i < n+1; i++) {
            ArrayList<Integer> list = toBinary(i);
            int size = list.size();
            count = 0;
            for (int k = 0; k < size; k++) {
                if (list.get(k) == 1) {
                    count += 1;
                }
            }
            resultList.add(count);
        }
        int s = resultList.size();
        int[] result = new int[s];
        for (int i = 0; i < s; i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
    //十进制数转换二进制数
    public ArrayList<Integer> toBinary(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        while (n != 0) {
            list.add(n % 2);
            n /= 2;
        }
        return list;
    }
}
