package com.fs.base;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;

public class Son1 extends Parent {

    private String sname = "son";

    private String pri = "pri-son";

    // 此处不能throw Exception, SqlEXCEPTION，异常<=父异常。 子类可以不抛异常。
    // 不能用 default修饰符， 修饰符 >= 父修饰符。 不能继承后，父的方法没了。
    public void doThrow() throws IOException {

    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public static void main(String[] args) throws Exception {

        Son1 son = new Son1();
        String def = son.def; // 同包可访问default
        String pro = son.pro; // 子类可访问protected,在子类内部。如果是外部调用子类，也是没法用protected属性和访法的
        String pub = son.pub; // 任意位置可访问protected



        BeanInfo beanInfo = Introspector.getBeanInfo(Son1.class);
        PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
        Parent parent = new Parent();
        parent.doGen(); // 同包可以访问default(同包跟子包不同), public, protected


        // 子类获取到了父类的private属性和方法，只是不能访问
        Class<?> superclass = son.getClass().getSuperclass();
        Field name1 = superclass.getDeclaredField("pri");
        name1.setAccessible(true);

        System.out.println( name1.get(son).toString());

        Field name = son.getClass().getDeclaredField("pri");
        name.setAccessible(true);
        Object res = name.get(son);
        System.out.println(res);

        System.out.println(son.pri);
    }

    void defMethod() {

    }
}
