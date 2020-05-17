package com.lambda.learn.lambda;

/**
 * Created with IntelliJ IDEA.
 * User: gtli
 * Date: 2020-05-16
 * Time: 13:57
 * Description: No Description
 */
public class expression {

    interface Ilambda {
        void test();
    }

    interface Ilambda2 {
        void test(String name, int age);
    }

    interface Ilambda3 {
        int test(int x, int y);
    }

    public static void main(String[] args) {

        /**
         * 无参的基本lambda表达式
         */
        Ilambda i1 = () -> {
            System.out.println("基本的lambda表达式");
        };
        i1.test();

        Ilambda i2 = () -> System.out.println("简化形式的lambda表达式");
        i2.test();

        /**
         * 带有参数的lambda表达式
         */
        Ilambda2 il1 = (String name, int age) -> {
            System.out.println(name + "说：我已经" + age + "岁了");
        };
        il1.test("小明", 12);

        /**
         * 带有参数的高级lambda表达式
         */
        Ilambda2 il2 = (name, age) -> System.out.println(name + "说：我已经" + age + "岁了");
        il1.test("大明", 13);

        /**
         * 带有参数，带有返回值的lambda表达式
         */
        Ilambda3 ilambda3 = (x, y) -> {
            int z = x + y;
            return z;
        };
        System.out.println(ilambda3.test(2, 4));

        /**
         * 高级写法
         */
        Ilambda3 ilambda31 = (x, y) -> x + y;
        System.out.println("高级写法的值:" +ilambda3.test(7, 4));
    }
}
