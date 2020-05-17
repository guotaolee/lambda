package com.lambda.learn.lambda;

import java.util.UUID;
import java.util.function.*;

/**
 * Created with IntelliJ IDEA.
 * User: gtli
 * Date: 2020-05-16
 * Time: 10:26
 * Description: No Description
 */
public class app {

    public static void main(String[] args) {

        /**
         * Predicate 接收参数T对象，返回一个boolean类型结果
         */
        Predicate<String> pre = (String username) -> {
            return "admin".equals(username);
        };
        System.out.println(pre.test("admin"));
        System.out.println(pre.test("root"));


        /**
         * Consumer 接收参数T对象，没有返回值
         */
        Consumer<String> con = (String message) -> {
            System.out.println("要发送的消息：" + message);
            System.out.println("消息返送完成");
        };
        con.accept("哈喽，艾瑞宝得~");
        con.accept("哈喽~");


        /**
         * Function 接收参数T对象，返回R对象
         */
        Function<String,Integer> fun = (String gender) -> {
            return "male".equals(gender)?1:0;
        };
        System.out.println(fun.apply("male"));
        System.out.println(fun.apply("female"));

        /**
         * Supplier 不接受任何参数，直接通过get()获取指定类型的对象
         */
        Supplier<String> sup = () -> {
            return UUID.randomUUID().toString();
        };
        System.out.println("获取系统的给出的UUID：" + sup.get());
        System.out.println("获取系统的给出的UUID：" + sup.get());
        System.out.println("获取系统的给出的UUID：" + sup.get());


        /**
         * UnaryOperator 接收参数T对象，执行业务处理后，返回更新后的T对象
         */
        UnaryOperator<String> una = (String img) -> {
            img += "像素大小为xxx";
            return img;
        };
        System.out.println(una.apply("图片"));

        /**
         * BinaryOperator 接收两个T对象，返回一个T对象结果
         */
        BinaryOperator<Integer> bin = (Integer i1, Integer i2) -> {
            return i1 > i2 ? i1 : i2;
        };
        System.out.println(bin.apply(12,13));
    }




}
