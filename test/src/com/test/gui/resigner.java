package com.test.gui;

import java.awt.GridLayout;

import javax.swing.*;

public class resigner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		myresign mre = new myresign();
	}

}

class myresign extends JFrame{
	
	JPanel jp0,jp1,jp2,jp3;
	JLabel hobby,sex;
	
	JCheckBox hobby1,hobby2,hobby3;
	JRadioButton male,female;
	ButtonGroup bg;//一点要有，要将 JRadioButton 全部放入其中；
	
	JButton resignButton,cancelbButton;
	
	//下拉框，列表框
	JComboBox<String> jcb;
	JList<String> jl;
	JScrollPane jsp;//滚动条组件
	
	public myresign(){
		jp0 = new JPanel();
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		hobby = new JLabel("爱好");
		sex   = new JLabel("性别");
		
		hobby1 = new JCheckBox("足球");
		hobby2 = new JCheckBox("篮球");
		hobby3 = new JCheckBox("乒乓");
		
		male = new JRadioButton("男");
		female = new JRadioButton("女");
		bg = new ButtonGroup();
		 
		bg.add(male);
		bg.add(female);
		
		
		resignButton = new JButton("注册");
		cancelbButton = new JButton("取消");
		
		String [] address = {"北京","天津","杭州","大连"};
		jcb = new JComboBox<String>(address);
		
		jl = new JList<String>(address);
		jl.setVisibleRowCount(1);//一次显示几个数据
		jsp = new JScrollPane(jl);
		
		
		jp0.add(jcb);
		jp0.add(jsp);
		
		jp1.add(hobby);
		jp1.add(hobby1);
		jp1.add(hobby2);
		jp1.add(hobby3);

		jp2.add(sex);
		jp2.add(male);
		jp2.add(female);
		
		jp3.add(resignButton);
		jp3.add(cancelbButton);
		
		this.setLayout(new GridLayout(4,1));
		this.add(jp0);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		
		this.setTitle("注册界面");
		 this.setLocation(100, 200);
		 this.setVisible(true);
		 this.setSize(300, 200);
		 this.setResizable(false);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}