package com.fs.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnSafeDemo {
    public static void main(String[] args) throws Exception {

        new UnSafeDemo().doCAS();
    }


    private int age = 0;

    Unsafe unsafe;
    long ageOffset;
    {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void doCAS() throws Exception {


        long newOffset = unsafe.objectFieldOffset
                (this.getClass().getDeclaredField("age"));
        ageOffset = unsafe.objectFieldOffset(this.getClass().getDeclaredField("age"));
        boolean ok = unsafe.compareAndSwapInt(this, ageOffset, age, 22);
        System.out.println(ok);

        System.out.println(newOffset + "  state2");
        System.out.println(age);
    }
}
