/* gui 边界布局
 * 
 * */

package com.test.gui;

import java.awt.BorderLayout;

import javax.swing.*;

public class myComponent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		my_component mc = new my_component();
	}

}
 class my_component extends JFrame{//默认布局是borderlayout
	 JButton jbEast = null;
	 JButton jbNorth = null;
	 JButton jbWest = null;
	 JButton jbSouth = null;
	 JButton jbMiddle = null;
	 public my_component() {
		// TODO Auto-generated constructor stub
		 this.setTitle("边界布局——默认");
		 this.setLocation(100, 200);
		 this.setVisible(true);
		 this.setSize(300, 400);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
//		 jbEast = new JButton();
//		 jbEast.setText("East");
		 jbEast = new JButton("东");
		 this.add(jbEast,BorderLayout.EAST);
		 jbNorth  = new JButton();
		 jbNorth .setText("北");
		 this.add(jbNorth,BorderLayout.NORTH);
		 
		 jbMiddle = new JButton("中");
		 this.add(jbMiddle, BorderLayout.CENTER);
		 
		 jbSouth = new JButton("南");
		 this.add(jbSouth, BorderLayout.SOUTH);
		 
		 jbWest = new JButton("西");
		 this.add(jbWest, BorderLayout.WEST);
	}
 };