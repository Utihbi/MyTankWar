package com.test.gui;

import java.awt.*;

import javax.swing.*;


public class myGridLayout {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		qq mytt = new qq();
	}

}

class qq extends JFrame{//默认布局是borderlayout
	 JButton jbs[] = null;
	 int size = 9;
	 public qq() {
		// TODO Auto-generated constructor stub
		 this.setTitle("网格");
		 this.setLocation(100, 200);
		 this.setVisible(true);
		 this.setSize(300, 400);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 //设置布局管理器
		 this.setLayout(new GridLayout(/*可以设置参数几行几列；水平，垂直，间隔*/3,3,10,10));
		 
		 //禁止用户改变窗体大小
		 this.setResizable(false);
		 
		 jbs = new JButton[size];
		 for(int i = 0;i<size;i++){
			 jbs[i] = new JButton(String.valueOf(i));
			 this.add(jbs[i]);
		 }
		 

	}
};