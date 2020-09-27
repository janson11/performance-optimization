package com.janson.performance.optimization.list;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Description: ArrayList示例
 * @Author: Janson
 * @Date: 2020/9/27 18:37
 **/
public class ArrayListTest {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("c");
        System.out.println(list);
        // 删除指定的"b"元素
        remove(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("element：" + list.get(i));
        }
    }

    private static void remove(ArrayList<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String str = it.next();
            if (str.equals("b")) {
                it.remove();
            }
        }
    }


    /**
     * 会报Exception in thread "main" java.util.ConcurrentModificationException
     *
     * @param list
     */
    private static void remove1(ArrayList<String> list) {
        for (String s : list) {
            if (s.equals("b")) {
                list.remove(s);
            }
        }

    }

}
