package com.test.gui;

import java.awt.BorderLayout;

import javax.swing.*;

public class myJpannel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		my_Jpannel mj = new my_Jpannel();
	}

}

class my_Jpannel extends JFrame{
	JPanel jp1,jp2;
	JButton jb1,jb2,jb3,jb4,jb5,jb6;
	
	public my_Jpannel() {
		// TODO Auto-generated constructor stub
		jp1 = new JPanel();
		jp2 = new JPanel();
		
		jb1 = new JButton("西瓜");
		jb2 = new JButton("橘子");
		jb3 = new JButton("栗子");
		jb4 = new JButton("冬瓜");
		jb5 = new JButton("桃子");
		jb6 = new JButton("果子");
		
		jp1.add(jb1);//JPanel 默认流式布局；
		jp1.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		jp2.add(jb5);
		
		this.add(jp1,BorderLayout.NORTH);
		this.add(jb6);
		this.add(jp2,BorderLayout.SOUTH);
		
		this.setTitle("Jpannel案例");
		 this.setLocation(100, 200);
		 this.setVisible(true);
		 this.setSize(300, 200);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
};