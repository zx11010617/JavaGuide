package com.fs.collection;

import com.fs.base.Son1;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {

    interface ISpeakable<T> extends Comparable<T> {
        void doCompare();
    }

    class My implements Comparable {

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    class My2 implements Comparable<My2> {

        @Override
        public int compareTo(My2 o) {
            return 0;
        }
    }

    final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        // 1000100110
        //  1000100110
        // 1111.....
        //

        // 比cap小1的数字，最大位一直右移，从1位开始，则最的是从最高位开始的一串1，再加1后，一定>=cap,然后，又是2 ^ n
        return  n + 1;
    }


    @Test
    public void doListCopyTest() {

        int ee = 15;

        int size = tableSizeFor(17);
        System.out.println(size);

        int a = (ee - 1) >>> 1;

        a |= a >>> 1;
        System.out.println(a);
        a |= a >>> 2;
        System.out.println(a);
        a |= a >>> 4;
        System.out.println(a);
        a |= a >>> 8;
        System.out.println(a);
        a |= a >>> 16;

        System.out.println(a + 1);
        a =  (a < 0) ? 1 : (a >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : a + 1;

        System.out.println(1.0 * ee / a);





        doReflect();

        ArrayList<Object> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        ArrayList<Integer> list2 = (ArrayList<Integer>)list.clone();
        System.out.println(list2.size());

        int f = 3;
        Integer b = 3;
        Integer c = new Integer(3);


        list.remove(new Integer(6));


        list.remove(new Integer(3));
//        list.remove(a);
        list.remove(b);
        list.remove(c);
//        list.remove(getRes());
        list.remove(getRes2());
        list.remove(3);

    // remove的时候，如果是int，就是remove索引，不然就是对象

    }

    private int getRes() {
        return 3;
    }


    private Integer getRes2() {
        return 3;
    }

    public Object doReflect() {


//        ISpeakable<Son1> x = new ISpeakable() {
//
//            @Override
//            public int compareTo(Object o) {
//                return 0;
//            }
//
//            @Override
//         public void doCompare() {
//
//         }
//        };

//        Comparable<Son1> x = new Comparable() {
//
//            @Override
//            public int compareTo(Object o) {
//                return 0;
//            }
//
//        };

//        Comparable x = new Integer(2);
//        Comparable x = new My();


        Comparable x = new My2();



        if (x instanceof Comparable) {
            Class<?> c; Type[] ts, as; Type t; ParameterizedType p;
            if ((c = x.getClass()) == String.class) // bypass checks
                return c;
            // 获取接口
            if ((ts = c.getGenericInterfaces()) != null) {
                for (int i = 0; i < ts.length; ++i) {
                    // 加了泛型的接口
                    if (((t = ts[i]) instanceof ParameterizedType) &&
                            ((p = (ParameterizedType)t).getRawType() ==
                                    Comparable.class) &&
                            (as = p.getActualTypeArguments()) != null &&
                            as.length == 1 && as[0] == c) // type arg is c
                        return c;
                }
            }
        }
        return null;
    }
}
