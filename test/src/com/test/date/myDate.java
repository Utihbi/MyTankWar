package com.test.date;

import java.util.Random;

public class myDate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("hello world it's : "+new Date());
//		System.out.print(new Date());
//		System.out.println("**************************************");
//		//System.getProperties().list(System.out);
//		System.out.println(System.getProperty("user.name"));
//		System.out.println(System.getProperty("java.library.path"));
		
		Random rand = new Random();
	    int i = rand.nextInt(100);
	    System.out.println(i);
	    System.out.println(Math.random()<0.99);
	    
	    Value v1 = new Value(); v1.i = 0;
	    Value v2 = new Value(); v2.i = 0;
	    
	    System.out.println(v1.equals(v2));
	    
	    int ii = 0x2;
	    System.out.println(Integer.toBinaryString(ii));
	    
	    int i3 =15;
	    i3 >>>= 1;//无符号右移操作符   无论正负，都在高位插入 “0”
	    System.out.println(i3>>>1);
	    
	    float f = 0.7f;
	    System.out.println("int f: "+(int)f + "   math.round "+Math.round(f));
	    
	}
}

class Value{
	int i;
}
