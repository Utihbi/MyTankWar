/*verison  4.0
 * �� j �� �����ӵ�
 * ���е��ˣ��ӵ��͵�����ʧ
 * ��ֹ̹���ص���
 * I/O
 * ��ͣ����
 * */

package com.test.tank4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class myTankGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		myFrame mf = new myFrame();
	}

}

class myFrame extends JFrame {
	private mypanel mp = null;
	private Thread mythread = null;

	public myFrame() {
		mp = new mypanel();
		this.addKeyListener(mp);// �¼�������ע��
		this.add(mp);
		this.setSize(325, 450);
		this.setVisible(true);
		this.setLocation(512,150);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		mythread = new Thread(mp);
		mythread.start();
	}
}

