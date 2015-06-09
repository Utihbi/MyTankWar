package com.test.tank6;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.sql.Time;

import javax.swing.JPanel;

class StartOrWinOrLosePanel extends JPanel implements Runnable{
	private int time = 0;
	private String title = "";
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, 310, 410);
		g.setColor(Color.YELLOW);
		g.setFont(new Font("������κ",Font.BOLD,30));
		if (time%2 == 0) {//����ÿ�ζ�д ���ɳ�������Ч��
			g.drawString(this.title, 70, 200);
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			time ++;
			this.repaint();	
		}
		
	}
}


class StartPanel extends StartOrWinOrLosePanel {
	
	public StartPanel () {
		this.setTitle("TANK WAR");
	}
}


class WinOrLose extends StartOrWinOrLosePanel{
	public WinOrLose (String title) {
		this.setTitle(title);
	}
}

class Win extends WinOrLose {
	public Win () {
		super("YOU WIN !");
	}
}

class Lose extends WinOrLose {
	public Lose () {
		super("YOU LOSE !");
	}
}