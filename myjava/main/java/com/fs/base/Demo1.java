package com.fs.base;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Demo1 {
    public static void main(String[] args) throws Exception {
        new Demo1().readKeyBoard2();
    }

    @Test
    public void readKeyBoard() throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String s = input.readLine();

        System.out.println(s);

        input.close();
    }

    @Test
    public void readKeyBoard2() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        System.out.println(line);
    }
}
