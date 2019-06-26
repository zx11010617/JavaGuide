package com.fs.cons;

public class Second extends First {

    private static String level = "qpp";

    private int clazz = 100;

    static {
        System.out.println("level is : " + level);
    }
    {
        System.out.println("clazz is :" + clazz);
    }

    public Second() {
        System.out.println("-----------");
    }

    public static void main(String[] args) throws Exception {
        Second second = new Second();
    }
}
