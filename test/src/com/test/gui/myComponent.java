/* gui �߽粼��
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
 class my_component extends JFrame{//Ĭ�ϲ�����borderlayout
	 JButton jbEast = null;
	 JButton jbNorth = null;
	 JButton jbWest = null;
	 JButton jbSouth = null;
	 JButton jbMiddle = null;
	 public my_component() {
		// TODO Auto-generated constructor stub
		 this.setTitle("�߽粼�֡���Ĭ��");
		 this.setLocation(100, 200);
		 this.setVisible(true);
		 this.setSize(300, 400);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
//		 jbEast = new JButton();
//		 jbEast.setText("East");
		 jbEast = new JButton("��");
		 this.add(jbEast,BorderLayout.EAST);
		 jbNorth  = new JButton();
		 jbNorth .setText("��");
		 this.add(jbNorth,BorderLayout.NORTH);
		 
		 jbMiddle = new JButton("��");
		 this.add(jbMiddle, BorderLayout.CENTER);
		 
		 jbSouth = new JButton("��");
		 this.add(jbSouth, BorderLayout.SOUTH);
		 
		 jbWest = new JButton("��");
		 this.add(jbWest, BorderLayout.WEST);
	}
 };