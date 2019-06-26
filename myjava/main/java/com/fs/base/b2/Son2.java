package com.fs.base.b2;

import com.fs.base.Parent;
import com.fs.base.Son1;

public class Son2 extends Parent {


    private int age;
    public  void main(String[] args) throws Exception {

        Son2 son = new Son2();
        // String def = son.def; // 同包可访问default,所以这里访问不了
        String pro = son.pro; // 子类可访问protected
        String pub = son.pub; // 任意位置可访问protected


        Son1 son1 = new Son1();
        String pub1 = son1.pub;
        int i;
       // System.out.println(i); // 有问题，局部变量没有初始化，要手动初始化
        System.out.println(age); // 成员变量自动初始化
        // son1.pro
        // son1.def 在类外部，就只能调用其public方法
        // son1.defaultMethod， 不在同包，没法访问

    }
}
