package com.test.gui;

import javax.swing.*;

public class jinshan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		myjinshan mj = new myjinshan();
	}

}

class myjinshan extends JFrame{
	JSplitPane jsp ;//拆分版
	JLabel jlabel;
	JList<String> jList;
	
	public myjinshan(){
		
		String words[] = {"string","hello","world","extend"};
		jList = new JList<String>(words);
		jlabel = new JLabel(new ImageIcon("images/hua.JPG"));
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT/*水平拆分*/,jList/*左边参数*/,jlabel/*右边参数*/);
		jsp.setOneTouchExpandable(true);//可收缩；
		
		this.add(jsp);
		
		this.setIconImage(new ImageIcon("images/qq.gif").getImage());//设置图标
		this.setTitle("腾讯qq");
		 this.setLocation(100, 200);
		 this.setVisible(true);
		 this.setSize(500, 500);
		 this.setResizable(false);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}