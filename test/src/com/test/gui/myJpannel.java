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
		
		jb1 = new JButton("����");
		jb2 = new JButton("����");
		jb3 = new JButton("����");
		jb4 = new JButton("����");
		jb5 = new JButton("����");
		jb6 = new JButton("����");
		
		jp1.add(jb1);//JPanel Ĭ����ʽ���֣�
		jp1.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		jp2.add(jb5);
		
		this.add(jp1,BorderLayout.NORTH);
		this.add(jb6);
		this.add(jp2,BorderLayout.SOUTH);
		
		this.setTitle("Jpannel����");
		 this.setLocation(100, 200);
		 this.setVisible(true);
		 this.setSize(300, 200);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
};