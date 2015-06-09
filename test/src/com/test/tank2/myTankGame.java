/*verison  2.0
 * �� j �� �����ӵ�
 * */

package com.test.tank2;


import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;


public class myTankGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		myFrame mf =  new myFrame();
	}

}

class myFrame extends JFrame{
	private mypanel mp = null;
	private Thread mythread= null;
	public myFrame(){
		mp = new mypanel();
		this.addKeyListener(mp);//�¼�������ע��
		this.add(mp);
		this.setSize(300,400);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		mythread = new Thread(mp);
		mythread.start();
	}
}

class mypanel extends JPanel implements KeyListener,Runnable{
	Hero hero = null;
	Vector<Enemy> enemis = new Vector<Enemy>();
	int enemySize = 4;
	
	enum tankType {heroTank,enemyTank};

	public mypanel(){
		hero = new Hero(200, 100);
		
		for(int i = 0; i < enemySize;i++){
			Enemy enemy = new Enemy((i+1) * 50, 0);
			enemis.add(enemy);
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, 300, 400);
		//����Ӣ�ۣ�
		this.drawtank(g, hero.getX(), hero.getY(), hero.getDirect(), tankType.heroTank);
		//����Ӣ���ӵ�
		if (null != hero.getMyshot() && hero.getMyshot().isLive()) {
			this.drawShot(g, tankType.heroTank, hero);
		}
		for(int i = 0; i < enemis.size();i++){
			this.drawtank(g, enemis.get(i).getX(), enemis.get(i).getY(), enemis.get(i).getDirect(), tankType.enemyTank);
			if (null != enemis.get(i).getMyshot() && enemis.get(i).getMyshot().isLive()) 
			this.drawShot(g, tankType.enemyTank, enemis.get(i));
		}
	}
	
	private void drawShot(Graphics g,tankType type,tank mytank){
		switch (type) {
		case heroTank://����̹��
			g.setColor(Color.BLUE);
			break;
		case enemyTank:
			g.setColor(Color.yellow);
			break;
		}
		
		g.fill3DRect(mytank.getMyshot().getX(), mytank.getMyshot().getY(), 2, 2, true);
	}
	
	private void drawtank(Graphics g,int x,int y,tank.direction direct,tankType type){
		switch (type) {
		case heroTank://����̹��
			g.setColor(Color.BLUE);
			break;
		case enemyTank:
			g.setColor(Color.yellow);
			break;
		}
		
		switch (direct) {
		case UP://����
			g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x+15, y, 5, 30, false);
			g.fill3DRect(x+5, y+5, 10, 20, true);
			g.drawLine(x+10, y+15, x+10,y-5);
			break;
        case DOWN://����
        	g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x+15, y, 5, 30, false);
			g.fill3DRect(x+5, y+5, 10, 20, true);
			g.drawLine(x+10, y+15, x+10,y+35);
			break;
		case LEFT://����
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, true);
			g.drawLine(x+15, y+10, x-5,y+10);
			break;
		case RIGHT://����
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, true);
			g.drawLine(x+15, y+10, x+35,y+10);
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			this.hero.setDirect(tank.direction.DOWN);
		    this.hero.moveDown();
			break;
		case KeyEvent.VK_UP:
			this.hero.setDirect(tank.direction.UP);
			this.hero.moveUp();
			break;
		case KeyEvent.VK_LEFT:
			this.hero.setDirect(tank.direction.LEFT);
			this.hero.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			this.hero.setDirect(tank.direction.RIGHT);
			this.hero.moveRight();
			break;
		}
		
		if (KeyEvent.VK_J == e.getKeyCode()) {
			this.hero.shotEnemy();
			for(int i =0;i<enemis.size();i++){
				this.enemis.get(i).shotEnemy();
			}
			
		}
		
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}
			this.repaint();
		}		
	}
}

