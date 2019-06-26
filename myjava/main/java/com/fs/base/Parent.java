package com.fs.base;

import java.io.IOException;

public class Parent {

    public String pub = "publicField-parent";

    protected String pro = "protectedField-parent";

    private String pri = "privateField-parent";

    String def = "defaultField-parent";


    protected void doThrow() throws IOException {
        System.out.println(pri);
    }

    void doGen() {
        System.out.println(pri);
    }
}
