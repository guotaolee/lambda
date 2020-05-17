package com.lambda.learn.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: gtli
 * Date: 2020-05-16
 * Time: 20:52
 * Description: Stream中常见的API操作
 */
public class Api_operate {

    public static void main(String[] args) {
        List<String> accountList = new ArrayList<>();
        accountList.add("宋江");
        accountList.add("鲁智深");
        accountList.add("武松");
        accountList.add("李逵");
        accountList.add("杨志");

        //map()中间操作，map方法接收一个Functional接口
        accountList = accountList.stream().map(x -> "梁山好汉:" + x).collect(Collectors.toList());
        accountList.forEach(System.out::println);

        //filter(),添加过滤条件，过滤符合条件的数据
        accountList =accountList.stream().filter(x -> !x.equals("宋江")).collect(Collectors.toList());//删选出没有宋江的梁山好汉
        accountList.forEach(System.out::println);

        //peek(),中间操作，迭代数据，完成数据的依次处理
        accountList.stream()
                .peek(x -> System.out.println("peek1迭代:" + x))
                .peek(x -> System.out.println("peek2迭代:" + x))
                .forEach(System.out::println);


        List<Integer> intList = new ArrayList<>();
        intList.add(20);
        intList.add(31);
        intList.add(34);
        intList.add(14120);
        intList.add(564);
        intList.add(20);
        intList.add(43);

        //skip(),中间操作，有状态，跳过部分数据
        intList.stream().skip(3).forEach(System.out::println);//skip()中的数据类型是long类型，表示跳过几个数据

        //limit(),中间操作，有状态，限制输出数据数量
        intList.stream().skip(3).limit(2).forEach(System.out::println);//跳过3个数据，限制只输出最多2个数据

        //distinct(),中间操作，有状态，剔除重复数据
        intList.stream().distinct().forEach(System.out::println);

        //sorted()，中间操作，有状态，排序
        intList.stream().sorted().forEach(System.out::println);

        //max()获取最大值
        Optional optional = intList.stream().max((x, y) -> x - y);//stream().max()是Optional类型的，所以要用Optional来接
        System.out.println(optional.get());

        //min()获取最小值
        Optional optional2 = intList.stream().min((x, y) -> x - y);//stream().max()是Optional类型的，所以要用Optional来接
        System.out.println(optional2.get());

        //reduce（）合并处理数据
        Optional optional3 = intList.stream().reduce((sum, x) -> sum + x);
        System.out.println(optional3.get());
    }
}
