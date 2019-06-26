package com.fs.base;

public class P2 {

    public static void main(String[] args) throws Exception {

        Parent parent = new Parent();
        parent.doGen();
        parent.doThrow(); // 同包可以访问protected, public , default

        new Son1().defMethod(); // 同包可以访问default
    }
}
