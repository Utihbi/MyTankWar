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
	ButtonGroup bg;//һ��Ҫ�У�Ҫ�� JRadioButton ȫ���������У�
	
	JButton resignButton,cancelbButton;
	
	//�������б��
	JComboBox<String> jcb;
	JList<String> jl;
	JScrollPane jsp;//���������
	
	public myresign(){
		jp0 = new JPanel();
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		hobby = new JLabel("����");
		sex   = new JLabel("�Ա�");
		
		hobby1 = new JCheckBox("����");
		hobby2 = new JCheckBox("����");
		hobby3 = new JCheckBox("ƹ��");
		
		male = new JRadioButton("��");
		female = new JRadioButton("Ů");
		bg = new ButtonGroup();
		 
		bg.add(male);
		bg.add(female);
		
		
		resignButton = new JButton("ע��");
		cancelbButton = new JButton("ȡ��");
		
		String [] address = {"����","���","����","����"};
		jcb = new JComboBox<String>(address);
		
		jl = new JList<String>(address);
		jl.setVisibleRowCount(1);//һ����ʾ��������
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
		
		this.setTitle("ע�����");
		 this.setLocation(100, 200);
		 this.setVisible(true);
		 this.setSize(300, 200);
		 this.setResizable(false);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}