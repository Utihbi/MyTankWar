package com.test.gui;

import javax.swing.*;

public class jinshan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		myjinshan mj = new myjinshan();
	}

}

class myjinshan extends JFrame{
	JSplitPane jsp ;//��ְ�
	JLabel jlabel;
	JList<String> jList;
	
	public myjinshan(){
		
		String words[] = {"string","hello","world","extend"};
		jList = new JList<String>(words);
		jlabel = new JLabel(new ImageIcon("images/hua.JPG"));
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT/*ˮƽ���*/,jList/*��߲���*/,jlabel/*�ұ߲���*/);
		jsp.setOneTouchExpandable(true);//��������
		
		this.add(jsp);
		
		this.setIconImage(new ImageIcon("images/qq.gif").getImage());//����ͼ��
		this.setTitle("��Ѷqq");
		 this.setLocation(100, 200);
		 this.setVisible(true);
		 this.setSize(500, 500);
		 this.setResizable(false);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}