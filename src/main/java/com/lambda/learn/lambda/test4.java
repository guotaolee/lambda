package com.lambda.learn.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: gtli
 * Date: 2020-05-17
 * Time: 09:25
 * Description: stream性能测试
 */
public class test4 {

    public static void main(String[] args) {
        Random random = new Random();

        //一.基本数据类型的测试
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            integerList.add(random.nextInt(Integer.MAX_VALUE));//随机数的上限取Integer的最大值
        }

        //1.stream对象，注意：stream本身就是串行的，所以天然线程安全
        testStream(integerList);

        //2.parallelStream对象,注意：并行的方式线程不安全，可能会出现数据丢失
        parallelStream(integerList);

        //3.普通for循环测试
        testFor(integerList);

        //4.增强型for循环测试
        testStrongFor(integerList);

        //5.iterator迭代器
        testIterator(integerList);


        //二.复杂数据类型测试
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            productList.add(new Product("pro" + i, i, random.nextInt(Integer.MAX_VALUE)));
        }

        //1.复杂对象Stream
        testProductStream(productList);

        //2.复杂对象parallelStream
        testProductParallelStream(productList);

        //3.复杂对象For
        testProductFor(productList);

        //4.复杂对象StrongFor
        testProductStrongFor(productList);

        //5.复杂对象迭代器
        testProductIterator(productList);


    }

    public static void testStream(List<Integer> list) {
        long start = System.currentTimeMillis();
        Optional optional = list.stream().max(Integer::compare);
        System.out.println("最大值:" + optional.get());
        long end = System.currentTimeMillis();
        System.out.println("testStream消耗的时间：" + (end - start));
    }

    public static void parallelStream(List<Integer> list) {
        long start = System.currentTimeMillis();
        Optional optional = list.parallelStream().max(Integer::compare);
        System.out.println("最大值:" + optional.get());
        long end = System.currentTimeMillis();
        System.out.println("parallelStream消耗的时间：" + (end - start));
    }

    public static void testFor(List<Integer> list) {
        long start = System.currentTimeMillis();

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); i++) {
            int current = list.get(i);
            if (current > max) {
                max = current;
            }
        }
        System.out.println("最大值：" + max);
        long end = System.currentTimeMillis();
        System.out.println("testFor消耗的时间：" + (end - start));
    }

    public static void testStrongFor(List<Integer> list) {
        long start = System.currentTimeMillis();

        int max = Integer.MIN_VALUE;
        for (Integer interger : list) {
            if (interger > max) {
                max = interger;
            }
        }
        System.out.println("最大值：" + max);
        long end = System.currentTimeMillis();
        System.out.println("testStrongFor消耗的时间：" + (end - start));
    }

    public static void testIterator(List<Integer> list) {
        long start = System.currentTimeMillis();

        Iterator<Integer> iterator = list.iterator();
        int max = iterator.next();
        while (iterator.hasNext()) {
            int current = iterator.next();
            if (current > max) {
                max = current;
            }
        }
        System.out.println("最大值：" + max);
        long end = System.currentTimeMillis();
        System.out.println("testIterator消耗的时间：" + (end - start));
    }

    public static void testProductStream(List<Product> list) {

        long start = System.currentTimeMillis();
        Optional optional = list.stream().max((p1,p2) -> p1.getHot() - p2.getHot());
        System.out.println("最大值:" + optional.get());
        long end = System.currentTimeMillis();
        System.out.println("testProductStream消耗的时间：" + (end - start));
    }

    public static void testProductParallelStream(List<Product> list) {

        long start = System.currentTimeMillis();
        Optional optional = list.stream().max((p1,p2) -> p1.getHot() - p2.getHot());
        System.out.println("最大值:" + optional.get());
        long end = System.currentTimeMillis();
        System.out.println("testProductParallelStream消耗的时间：" + (end - start));
    }

    public static void testProductFor(List<Product> list) {

        long start = System.currentTimeMillis();
        Product maxHot = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            Product currentPrd = list.get(i);
            if (currentPrd.getHot() > maxHot.getHot()) {
                maxHot = currentPrd;
            }
        }
        System.out.println("最大值:" + maxHot.getHot());
        long end = System.currentTimeMillis();
        System.out.println("testProductFor消耗的时间：" + (end - start));
    }

    public static void testProductStrongFor(List<Product> list) {

        long start = System.currentTimeMillis();
        Product maxHot = list.get(0);
        for (Product current: list) {
            if (current.getHot() > maxHot.getHot()) {
                maxHot = current;
            }
        }
        System.out.println("最大值:" + maxHot.getHot());
        long end = System.currentTimeMillis();
        System.out.println("testProductStrongFor消耗的时间：" + (end - start));
    }

    public static void testProductIterator(List<Product> list) {

        long start = System.currentTimeMillis();
        Iterator<Product> it = list.iterator();
        Product maxHot = it.next();
        while (it.hasNext()) {
            Product current = it.next();
            if (current.getHot() > maxHot.getHot()) {
                maxHot = current;
            }
        }
        System.out.println("最大值:" + maxHot.getHot());
        long end = System.currentTimeMillis();
        System.out.println("testProductIterator消耗的时间：" + (end - start));
    }
}

@Data
@AllArgsConstructor
class Product {
    /**
     * 名称
     */
    private String name;

    /**
     * 库存
     */
    private Integer stock;

    /**
      * 热度
      */
    private Integer hot;
}
