/*verison  4.0
 * 按 j 键 发射子弹
 * 打中敌人，子弹和敌人消失
 * 防止坦克重叠；
 * I/O
 * 暂停功能
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
		this.addKeyListener(mp);// 事件监听，注册
		this.add(mp);
		this.setSize(325, 450);
		this.setVisible(true);
		this.setLocation(512,150);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		mythread = new Thread(mp);
		mythread.start();
	}
}

