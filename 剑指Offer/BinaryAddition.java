package 剑指Offer;

/**
 * 给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 */

public class BinaryAddition {
    public String addBinary(String a, String b) {
        //return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
        StringBuilder sb = new StringBuilder();

        int carry = 0;//上一位置的进位
        int n = Math.max(a.length(), b.length());
        //从最低位开始遍历
        for (int i = 0; i < n; i++) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            sb.append((char)(carry % 2 + '0'));  
            carry /= 2;
        }

        if (carry > 0) {
            sb.append(1);
        }
        sb.reverse();
        return sb.toString();
    }
}
