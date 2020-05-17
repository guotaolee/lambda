package com.lambda.learn.lambda;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 * User: gtli
 * Date: 2020-05-16
 * Time: 16:53
 * Description: stream聚合操作
 * 1.聚合操作
 *
 * 2.stream的处理流程
 *  数据源
 *  数据转换
 *  获取结果
 * 3.获取stream对象
 *  1.从集合或者数组中获取
 *      集合中的stream对象：Collection.stream(),如accounts.stream();
 *      并行处理的stream对象：Collection.parallelStream()
 *      数组中的stream对象：Arrays.stream(T t)
 *  2.BufferReader
 *      BUfferReader.lines()->stream()
 *  3.静态工厂
 *      java.util.stream.IntStream.range()
 *      java.nio.file.Files.walk()
 *  4.自定义构建
 *      java.util.Spliterator
 *  5.操作过程中获取
 *      Random.ints()
 *      Pattern.SplitAsStream()
 * 4.中间操作API
 *      操作结果是个stream，中间操作可以有一个或者多个连续的中间操作，需要注意的是，中间操作只记录操作方式，不做具体执行，直到结束操作发生时，才做数据的最终执行
 *      中间操作其实就是业务逻辑处理
 *      中间操作过程：
 *          无状态：数据处理时，不受前置中间操作的影响，如进行操作map/filter/peek/parallel/sequential/unordered
 *          有状态：数据处理时，会受到前置中间操作的影响,如进行操作distinct/sorted/limit/skip
 * 5.终结操作/结束操作{Terminal}
 *      需要注意：一个stream，只能有一个terminal，这个操作一旦发生就会真实处理数据，生成对应的处理结果，并且这个过程是不可逆的
 *      终结操作还分短路操作和非短路操作
 *          短路操作：当前的stream对象必须处理完集合中所有数据，才能得到处理结果
 *              如：forEach/forEachOrdered/toArray/reduce/collect/min/max/count/iterator
 *          非短路操作：当前stream对象在处理过程中，一旦满足某个条件，就可以得到结果
 *              如：anyMatch/allMatch/noMatch/findFirst/findAny等
 *
 *
 *
 */
public class test3 {

    public static void main(String[] args) {
        //获取stream
        //1.通过of获取
        Stream stream = Stream.of("zhangsan", "lisi", "wangwu");

        //2.通过数组
        String[] strArrays = new String[]{"xiaoming", "xiaohua", "xiaobei"};
        Stream stream1 = Arrays.stream(strArrays);

        //3.通过列表
        List<String> strList = new ArrayList<>();
        strList.add("少林");
        strList.add("武当");
        strList.add("峨眉");
        strList.add("倥侗");
        strList.add("华山");
        Stream stream2 = strList.stream();

        //4.集合
        Set<String> set = new HashSet<>();
        set.add("超人");
        set.add("钢铁侠");
        set.add("蜘蛛侠");
        set.add("闪电侠");
        Stream stream3 = set.stream();

        //4.map
        Map<String, Integer> map = new HashMap<>();
        map.put("话费", 100);
        map.put("电费", 90);
        map.put("流量", 1000);
        map.put("水费", 20);
        Stream stream4 = map.entrySet().stream();

        //Stream对于基本数据类型的功能封装（目前只支持int,long,double)
        IntStream.of(new int[]{10,20,30}).forEach(System.out::println);//输出一个10，20，30的数字流
        IntStream.range(1,5).forEach(System.out::println);//返回子序列 [a,b)，左闭右开，意味着不包括 b，即：1234
        IntStream.rangeClosed(1,5).forEach(System.out::println);//返回子序列 [a,b]，左闭右闭，意味着包括 b，即：12345

        //Stream对象->转换得到指定的数据类型
        //1.得到数组
        Object [] objc = stream.toArray(String [] :: new);
        System.out.println(Arrays.asList(objc));//为方便打印，先将数组转换成列表

        //2.得到字符串
        String str = stream.collect(Collectors.joining()).toString();
        System.out.println(str);//打印该字符串的时候，注意先将上面stream转换成数组的注释掉，不然会报错stream已经操作完成，已经被关闭的错误

        //3.列表
        List<String> list = (List<String>) stream.collect(Collectors.toList());
        System.out.println(list);

        //4.集合
        Set<String> stringSet = (Set<String>) stream.collect(Collectors.toSet());
        System.out.println(stringSet);

        //5.map
        Map<String, String> stringMap = (Map<String, String>) stream.collect(Collectors.toMap(x -> x, y -> "value:" + y));//Collectors.toMap方法中内容是（Function<? super T, ? extends K> keyMapper,Function<? super T, ? extends U> valueMapper）所以要先将key和value进行赋值操作
        System.out.println(stringMap);
    }
}
