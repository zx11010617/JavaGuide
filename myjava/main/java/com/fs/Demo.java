package com.fs;

import com.fs.base.Parent;
import com.fs.base.Son1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Demo {
    public  static<T> void main(String[] args) throws Exception {
        String[] lines = new String[3];
        Class<? extends String[]> aClass = lines.getClass();
        String name = aClass.getSimpleName();
        System.out.println(name);

        T[] o = (T[])Array.newInstance(Son1.class, 10);
        for (int i = 0; i < 10; i++) {
           // o[i] = new Son1();
        }
        System.out.println(o);
        T[] ts = Arrays.copyOf(o, 20);// 复制后，长度变成20

//        List<Parent> parents = Arrays.asList(new Parent(), new Parent());
//        Parent[] objects = (Parent[])parents.toArray();

        Son1[] parents = {new Son1(), new Son1()};

        // tosons
        // 向上转型,返回父类数组
        Parent[] son1s = Arrays.copyOf(parents, 10, Parent[].class);

        ArrayList<Son1> list = new ArrayList<>(2);
        list.trimToSize();
        list.size();
        list.add(new Son1());
        System.out.println(list.get(0));

        list.ensureCapacity(8); // 有了8个空位
        System.out.println(list.size()); // 实际数据量还是1


        ArrayList<Son1> copiedList = (ArrayList<Son1>)list.clone(); // 有用的方法
        copiedList.remove(0);
        System.out.println(list.get(0));
        copiedList.add(new Son1());

        Parent[] parents1 = copiedList.toArray(new Parent[]{}); // 有用的方法，变成父类数组，同Arrays.copyOf(copiedList.toArray(), parents1.length, Parent[].class)

        Parent[] parents2 = Arrays.copyOf(copiedList.toArray(), parents1.length, Parent[].class);

        System.out.println(parents1[0].pub);

        System.out.println(Color.GREEN.equals(1));

        HashSet<Son1> set = new HashSet<>();
        set.add(new Son1());

        Son1 s1 = new Son1();
        Son1 s2 = new Son1();
        Son1 s3 = new Son1();
        Son1 s4 = new Son1();



        list.clear();
        list.add(s1);
        list.add(s2);
        System.out.println(s2);
        list.add(s3);
        list.add(s4);
        System.out.println(s4);

        set.add(s2);
        set.add(s4);

        boolean b = list.retainAll(set); // 求交
        System.out.println(list.get(0));
        System.out.println(list.get(1));


        list.clear();
        list.add(s1);
        list.add(s2);
        System.out.println(s1);
        list.add(s3);
        list.add(s4);
        System.out.println(s3);
        list.removeAll(set); // list作减法
        System.out.println(list.get(0));
        System.out.println(list.get(1));

    }

    enum Color {
        GREEN(1), BLUE(2);

        private Color(int num) {
            this.num = num;
        }

        private Integer num;

        public Integer val() {
            return num;
        }

        public boolean equals(Integer i) {
            return num.equals(i);
        }
    }
}
