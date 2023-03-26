package com.turing.doc;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * TODO 设计哈希集合
 *
 * @Description
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 *
 * 实现 MyHashSet 类：
 *
 * void add(key) 向哈希集合中插入值 key 。
 * bool contains(key) 返回哈希集合中是否存在这个值 key 。
 * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 *  
 * 示例：
 *
 * 输入：
 * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * 输出：
 * [null, null, null, true, false, null, true, null, false]
 *
 * 解释：
 * MyHashSet myHashSet = new MyHashSet();
 * myHashSet.add(1);      // set = [1]
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(1); // 返回 True
 * myHashSet.contains(3); // 返回 False ，（未找到）
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(2); // 返回 True
 * myHashSet.remove(2);   // set = [1]
 * myHashSet.contains(2); // 返回 False ，（已移除）
 *
 *
 * @Author ll.lv
 * @Date 2023/3/25 17:08
 **/
public class DesignHashSet {
    
    /**
     * TODO 链地址法
     * 
     * @Description
     * 方法一：链地址法
     * 设哈希表的大小为
     * base
     * base，则可以设计一个简单的哈希函数：hash(x)=xmodbase
     * 我们开辟一个大小为 base 的数组，数组的每个位置是一个链表。当计算出哈希值之后，就插入到对应位置的链表当中
     * 由于我们使用整数除法作为哈希函数，为了尽可能避免冲突，应当将 base 取为一个质数。在这里，我们取 base = 769
     *
     *
     * @Author ll.lv
     * @Date 2023/3/25 17:34     
     **/
    
    private static final int BASE = 769;
    private LinkedList[] data;

    /** Initialize your data structure here. */
    public DesignHashSet() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; ++i) {
            data[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return;
            }
        }
        data[h].offerLast(key);
    }

    public void remove(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                data[h].remove(element);
                return;
            }
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return true;
            }
        }
        return false;
    }

    private static int hash(int key) {
        return key % BASE;
    }


    public static void main(String[] args) {
        DesignHashSet hashSet = new DesignHashSet();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.contains(1);
        hashSet.contains(3);
        hashSet.add(2);
        hashSet.contains(2);
        hashSet.remove(2);
        hashSet.contains(2);

    }
}
