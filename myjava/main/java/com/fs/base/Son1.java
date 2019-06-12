package com.fs.base;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;

public class Son extends Parent {

    private String sname = "son";

    // 此处不能throw Exception, SqlEXCEPTION，异常<=父异常。 子类可以不抛异常。
    // 不能用 default修饰符， 修饰符 >= 父修饰符。 不能继承后，父的方法没了。
    public void doThrow() throws IOException {

    }


    public static void main(String[] args) throws Exception {

        BeanInfo beanInfo = Introspector.getBeanInfo(Son.class);
        PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();

        Son son = new Son();

        Class<?> superclass = son.getClass().getSuperclass();
        Field name1 = superclass.getField("pname");
        name1.setAccessible(true);

        System.out.println( name1.get(son).toString());

        Field name = son.getClass().getField("sname");
        name.setAccessible(true);
        Object res = name.get(son);
        System.out.println(res);

//        Arrays.stream(props).forEach(p -> {
//            try {
//
//                Object iies = p.getReadMethod().invoke(son);
//                System.out.println(iies.toString());
//
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//        });

    }
}
