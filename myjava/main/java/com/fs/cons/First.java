package com.fs.cons;

public class First {
    public First() {
        System.out.println("empty father");
    }

    private static int age = 10;

    static {
        System.out.println("age is :" + age);
        age = 20;
        //System.out.println(name); 报错看不到
    }

    private static String name = "lily";


    private int sex = 1;

    {
        System.out.println("sex is:" + sex);
        // System.out.println(group); 报错看不到
    }

    private String group = "gp";

    private First(String name) {
        System.out.println(group + " ===");
        this.group = name;
        System.out.println(age);
    }

    public static void main(String[] args) throws Exception {

        First first = new First("");
        // 父类静态属性 -> 父类静态代码块 ->
        // 子类静态属性 -> 子类静态代码块 ->
        // 父类非静态属性 -> 父类非静态代码块 -> 父类构造方法
        // 子类非静态属性 -> 子类非静态代码块 -> 父类构造方法

    }
}
