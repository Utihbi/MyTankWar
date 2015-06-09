package com.test.gui;

import java.awt.FlowLayout;

import javax.swing.*;

public class myflowLayout {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		tt mc = new tt();
	}

}

class tt extends JFrame{//默认布局是borderlayout
	 JButton jbEast = null;
	 JButton jbNorth = null;
	 JButton jbWest = null;
	 JButton jbSouth = null;
	 JButton jbMiddle = null;
	 public tt() {
		// TODO Auto-generated constructor stub
		 this.setTitle("流式布局");
		 this.setLocation(100, 200);
		 this.setVisible(true);
		 this.setSize(300, 400);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 //设置布局管理器
		 this.setLayout(new FlowLayout(/*默认是居中对齐*/FlowLayout.LEFT));
		 
		 //禁止用户改变窗体大小
		 this.setResizable(false);
		 
		 
//		 jbEast = new JButton();
//		 jbEast.setText("East");
		 jbEast = new JButton("东");
		 this.add(jbEast);
		 jbNorth  = new JButton();
		 jbNorth .setText("北");
		 this.add(jbNorth);
		 
		 jbMiddle = new JButton("中");
		 this.add(jbMiddle);
		 
		 jbSouth = new JButton("南");
		 this.add(jbSouth);
		 
		 jbWest = new JButton("西");
		 this.add(jbWest);
	}
};