package com.test.gui;

import java.awt.FlowLayout;

import javax.swing.*;

public class myflowLayout {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		tt mc = new tt();
	}

}

class tt extends JFrame{//Ĭ�ϲ�����borderlayout
	 JButton jbEast = null;
	 JButton jbNorth = null;
	 JButton jbWest = null;
	 JButton jbSouth = null;
	 JButton jbMiddle = null;
	 public tt() {
		// TODO Auto-generated constructor stub
		 this.setTitle("��ʽ����");
		 this.setLocation(100, 200);
		 this.setVisible(true);
		 this.setSize(300, 400);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 //���ò��ֹ�����
		 this.setLayout(new FlowLayout(/*Ĭ���Ǿ��ж���*/FlowLayout.LEFT));
		 
		 //��ֹ�û��ı䴰���С
		 this.setResizable(false);
		 
		 
//		 jbEast = new JButton();
//		 jbEast.setText("East");
		 jbEast = new JButton("��");
		 this.add(jbEast);
		 jbNorth  = new JButton();
		 jbNorth .setText("��");
		 this.add(jbNorth);
		 
		 jbMiddle = new JButton("��");
		 this.add(jbMiddle);
		 
		 jbSouth = new JButton("��");
		 this.add(jbSouth);
		 
		 jbWest = new JButton("��");
		 this.add(jbWest);
	}
};