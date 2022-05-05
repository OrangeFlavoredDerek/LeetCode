package 面试高频题;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 设计LRU(最近最少使用)缓存结构，该结构在构造时确定大小，假设大小为 capacity ，操作次数是 n ，并有如下功能:
 * 1. Solution(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * 2. get(key)：如果关键字 key 存在于缓存中，则返回key对应的value值，否则返回 -1 。
 * 3. set(key, value)：将记录(key, value)插入该结构，如果关键字 key 已经存在，则变更其数据值 value，如果不存在，
 * 则向缓存中插入该组 key-value ，如果key-value的数量超过capacity，弹出最久未使用的key-value
 */

//哈希表+双向链表
public class LRU {
    static class Node {
        int key;
        int val;
        Node pre;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.pre = null;
            this.next = null;
        }
    }

    private Map<Integer, Node> mp = new HashMap<>();
    //设置一个头
    private Node head = new Node(-1, -1);
    //设置一个尾
    private Node tail = new Node(-1, -1);
    private int size = 0;

    public int[] solution (int[][] operators, int capacity) {
        //构建初始化连接
        //链表剩余大小
        this.size = capacity;
        this.head.next = this.tail;
        this.tail.pre = this.head;
        //获取操作数
        int len = (int)Arrays.stream(operators).filter(x -> x[0] == 2).count();
        int[] res = new int[len];
        //遍历所有操作
        for(int i = 0, j = 0; i < operators.length; i++){
            if(operators[i][0] == 1)
                //set操作
                set(operators[i][1], operators[i][2]);
            else
                //get操作
                res[j++] = get(operators[i][1]);
        }
        return res;
    }
     
     //插入函数
    private void set(int key, int val){
        //没有见过这个key，新值加入
        if(!mp.containsKey(key)){
            Node node = new Node(key, val);
            mp.put(key, node);
            //超出大小，移除最后一个
            if(size <= 0)
                removeLast();
            //大小还有剩余
            else
                //大小减1
                size--;
            //加到链表头
            insertFirst(node);
        }
        //哈希表中已经有了，即链表里也已经有了
        else{ 
            mp.get(key).val = val;
            //访问过后，移到表头
            moveToHead(mp.get(key));
        }
    }
     
    //获取数据函数
    private int get(int key){
        int res = -1;
        if(mp.containsKey(key)){
            Node node = mp.get(key);
            res = node.val;
            moveToHead(node);
        }
        return res;
    }
    //移到表头函数
    private void moveToHead(Node node){
        //已经到了表头
        if(node.pre == head) 
            return;
        //将节点断开，取出来
        node.pre.next = node.next;
        node.next.pre = node.pre;
        //插入第一个前面
        insertFirst(node);
    }
     
    //将节点插入表头函数
    private void insertFirst(Node node){
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }
     
    //删去表尾函数，最近最少使用
    private void removeLast(){
        //哈希表去掉key
        mp.remove(tail.pre.key);
        //断连该节点
        tail.pre.pre.next = tail;
        tail.pre = tail.pre.pre;
    }
}
