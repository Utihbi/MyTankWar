package com.test.gui;

import java.awt.*;

import javax.swing.*;


public class myGridLayout {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		qq mytt = new qq();
	}

}

class qq extends JFrame{//Ĭ�ϲ�����borderlayout
	 JButton jbs[] = null;
	 int size = 9;
	 public qq() {
		// TODO Auto-generated constructor stub
		 this.setTitle("����");
		 this.setLocation(100, 200);
		 this.setVisible(true);
		 this.setSize(300, 400);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 //���ò��ֹ�����
		 this.setLayout(new GridLayout(/*�������ò������м��У�ˮƽ����ֱ�����*/3,3,10,10));
		 
		 //��ֹ�û��ı䴰���С
		 this.setResizable(false);
		 
		 jbs = new JButton[size];
		 for(int i = 0;i<size;i++){
			 jbs[i] = new JButton(String.valueOf(i));
			 this.add(jbs[i]);
		 }
		 

	}
};