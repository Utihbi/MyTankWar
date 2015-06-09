package com.test.gui;

import javax.swing.JFrame;

public class myJframe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		my_frame mf = new my_frame("hello world!");
	}

}

class my_frame extends JFrame{
	public my_frame(String title) {
		// TODO Auto-generated constructor stub
		setTitle(title);
		setLocation(500, 500);
		setVisible(true);
		setSize(200, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
};