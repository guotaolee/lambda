package com.lambda.learn.lambda;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: gtli
 * Date: 2020-05-16
 * Time: 16:27
 * Description: No Description
 */
public class test2 {

    public static void main(String[] args) {
        List<String> accounts = new ArrayList<>();
        accounts.add("tom");
        accounts.add("jerry");
        accounts.add("shuke");
        accounts.add("beita");
        accounts.add("jack");

        //获取长度大于等于5的有效账号
        //1.传统方式
        for (String account : accounts) {
            if (account.length() >= 5) {
                System.out.println("传统方式下获取的有效账号：" + account);
            }
        }

        //2.迭代器
        Iterator<String> it = accounts.iterator();
        while (it.hasNext()) {
            String account = it.next();
            if (account.length() >= 5) {
                System.out.println("迭代器下获取的有效账号：" + account);
            }
        }

        //3.stream+lambda表达式
        List validAccount = accounts.stream().filter(c -> c.length() >= 5).collect(Collectors.toList());
        System.out.println("stream+lambda表达式获取的有效账号" + validAccount);
    }
}
