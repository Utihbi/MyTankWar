package com.test.date;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class foreach {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float f[] = new float[10];
		Random rand = new Random();
		for (int i = 0;i<10;i++) {
			f[i] = rand.nextFloat();
		}
		for (float x : f) {
			System.out.println(x);
		}
		
		for (char c : "an big pig".toCharArray()/*toCharArray()返回一个字符数组*/) {
			System.out.print(c+"  ");
		}
		
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			//bf.readLine();
			System.out.println(bf.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
