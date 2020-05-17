package com.lambda.learn.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gtli
 * Date: 2020-05-16
 * Time: 14:59
 * Description: No Description
 */
public class test {

    public static void main(String[] args) {
        //构建存贮Person对象的列表
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("tom", "男", 15));
        personList.add(new Person("jerry", "女", 16));
        personList.add(new Person("舒克", "男", 12));
        personList.add(new Person("贝塔", "男", 13));
        personList.add(new Person("蓝皮鼠", "男", 7));
        personList.add(new Person("大脸猫", "男", 8));

        //对列表进行排序的需求
        //1.匿名内部类实现方式：
        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println(personList);


        //2.lambda表达式的实现方式：
        Collections.sort(personList, (p1, p2) -> p1.getAge() - p2.getAge());
        System.out.println(personList);


        //3.静态方法的引用：
        Collections.sort(personList, Person::compareByAge);
        System.out.println(personList);

        //4.实例方法引用：
        PersonUtil pu = new PersonUtil();
        Collections.sort(personList, pu::compareByName);
        System.out.println(personList);

        //5.构造方法引用：绑定函数式接口
        IPerson ip = Person::new;
        Person person = ip.initPerson("jerry", "男", 22);
        System.out.println(person);

    }

}

@Data
@AllArgsConstructor //所有属性的构造方法
@NoArgsConstructor
        //空构造方法
class Person {
    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private int age;

    public static int compareByAge(Person p1, Person p2) {
        return p1.getAge() - p2.getAge();
    }
}

class PersonUtil {
    //增加一个实例方法
    public int compareByName(Person p1, Person p2) {
        return p1.getName().hashCode() - p2.getName().hashCode();
    }
}

interface IPerson {
    //抽象方法：通过指定类型的构造方法初始化对象数据
    Person initPerson(String name, String sex, int age);
}