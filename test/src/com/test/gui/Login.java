package com.test.gui;

import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

public class Login {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mylogin ml =  new mylogin();
	}

}

class mylogin extends JFrame implements ActionListener{
	JPanel jp1,jp2,jp3;
	JLabel usernamel,pwdl;
	
	JTextField userField;
	JPasswordField jPasswordField;
	
	JButton loginButton,cancelbButton;
	
	public mylogin () {
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		usernamel = new JLabel("�û���");
		pwdl      = new JLabel("��     ��");
		
		userField = new JTextField(10/*����*/);
		jPasswordField =new JPasswordField(10);
		
		loginButton = new JButton("��¼");
		cancelbButton = new JButton("ȡ��");
		
		loginButton.addActionListener(this);//�¼���������,ע�����
		loginButton.setActionCommand("loginButton");
		
		jp1.add(usernamel);
		jp1.add(userField);
		
		jp2.add(pwdl);
		jp2.add(jPasswordField);
		
		jp3.add(loginButton);
		jp3.add(cancelbButton);
		
		this.setLayout(new GridLayout(3,1));
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		
		this.setTitle("��¼����");
		 this.setLocation(100, 200);
		 this.setVisible(true);
		 this.setSize(300, 130);
		 this.setResizable(false);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("loginButton")) {
			System.out.println("��¼�ɹ�");
		}
		else {
			System.out.println("��¼ʧ��");
		}
	}
};