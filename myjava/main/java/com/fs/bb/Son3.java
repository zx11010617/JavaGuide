package com.fs.bb;

import com.fs.base.Parent;

public class Son3 extends Parent {

    public static void main(String[] args) throws Exception {
        Son3 son2 = new Son3();
       // String pname = son2.pname;
        son2.doThrow(); // 子类可以访问protected, public
    }
}
