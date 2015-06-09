/*
 * author :xiaoling
 * @a
 * 
 * 
 * 
 * */
package test;

import javax.swing.JFrame;

//public class test {
//	public static void main(String[] args) {
//		// int b = 3;
//		// System.out.println(b);
//		try {
//			InputStreamReader is = new InputStreamReader(System.in);
//			BufferedReader br = new BufferedReader(is);
//			String al = br.readLine();
//			System.out.println("please input the fisrt number");
//			float num1 = Float.parseFloat(al);
//			System.out.println(num1);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//
//	}
//}
//
//class Dog {
//}

class Parent {
	static String name = "hello";
	{
		System.out.println("parent block");
	}
	static {
		System.out.println("parent static block");
	}

	public Parent() {
		System.out.println("parent constructor");
	}
}

class Child extends Parent {
	static String childName = "hello";
	{
		System.out.println("child block");
	}
	static {
		System.out.println("child static block");
	}

	public Child() {
		System.out.println("child constructor");
	}
}

public class test extends JFrame{

	public static void main(String[] args) {
		//new Child();// Óï¾ä(*)
		new test();
	}
	
	public test() {
		// TODO Auto-generated constructor stub
		this.setVisible(true);
		this.setSize(3000,200);
	}
}


class animal{
	private String name;
	int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}