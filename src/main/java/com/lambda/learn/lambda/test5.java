package com.lambda.learn.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gtli
 * Date: 2020-05-17
 * Time: 10:26
 * Description: 串行Stream和并行Steam比较
 */
public class test5 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }

        //串行的stream
        List<Integer> list2 = new ArrayList<>();
        list.stream().forEach(x -> list2.add(x));
        System.out.println(list.size());
        System.out.println(list2.size());

        //并行stream
        List<Integer> list3 = new ArrayList<>();
        list.parallelStream().forEach(x -> list3.add(x));
        System.out.println(list3.size());
    }
}
