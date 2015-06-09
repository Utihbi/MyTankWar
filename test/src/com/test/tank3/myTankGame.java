/*verison  3.0
 * 按 j 键 发射子弹
 * 打中敌人，子弹和敌人消失
 * */

package com.test.tank3;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;


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

class mypanel extends JPanel implements KeyListener, Runnable {
	Hero hero = null;
	Vector<Enemy> enemis = new Vector<Enemy>();
	int enemySize = 4;

	enum tankType {
		heroTank, enemyTank
	};

	public mypanel() {
		
		this.createHero();

		//创建敌人；
		this.createEnemy(enemySize);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, 310, 410);
		// 绘制英雄；
		if (this.hero.isLive()) {
			this.drawtank(g, hero.getX(), hero.getY(), hero.getDirect(),
					tankType.heroTank);
		}
		
		// 绘制英雄子弹
		for (int i = 0; i < this.hero.getShots().size(); i++) {
			Shot currentShot = this.hero.getShots().get(i);
			if (null != currentShot && currentShot.isLive()) {
				this.drawShot(g, tankType.heroTank, currentShot);
			}
			if (!currentShot.isLive()) {// 如果没有这句，发完 5颗子弹就没有子弹了。
				this.hero.getShots().remove(currentShot);
			}
		}

		//绘制敌人
		for (int i = 0; i < enemis.size(); i++) {
			Enemy currentEnemy = enemis.get(i);
			if (currentEnemy.isLive()) {
				this.drawtank(g, currentEnemy.getX(), currentEnemy.getY(),
						currentEnemy.getDirect(), tankType.enemyTank);
				//绘制敌人子弹
				//System.out.println(currentEnemy.getShots().size());
				for(int j = 0;j < currentEnemy.getShots().size();j++){
					Shot currentShot = currentEnemy.getShots().get(j);
					if (/*null != currentShot &&*/ currentShot.isLive()) {
						this.drawShot(g, tankType.enemyTank, currentShot);
						//System.out.println("have"+currentEnemy.getShots().size());
					}//endif
					else{// 如果没有这句，发完 5颗子弹就没有子弹了。
						currentEnemy.getShots().remove(currentShot);
						//System.out.println(currentEnemy.getShots().size());
					}//endelse
				}//endfor
			}//endif 
			else {
				this.enemis.remove(currentEnemy);
				//currentEnemy.setLive(true);
			}//endelse
		}//endif
		
		//补充敌人
		this.createEnemy(enemySize - enemis.size());
		//给英雄续命
		if (! this.hero.isLive()) {
			this.createHero();
		}
		
	}

	private void createHero(){
		this.hero = new Hero(250, 300);
		this.hero.setStep(5);
	}
	private void createEnemy(int theNUmberToBeCreated){
		for (int i = 0; i < theNUmberToBeCreated; i++) {
			Enemy enemy = new Enemy(i * 50, 0);
			enemy.setStep((i+1));
//			enemy.setMovingToGoal(true);
//			if (null != this.hero) {
//				enemy.setGoal(this.hero);
//			}
			
			enemis.add(enemy);
			Thread threadEnemy= new Thread(enemy);
			threadEnemy.start();
			//给敌人添加一颗子弹；
			enemy.ShotEnemy();
		}
	}
	
	private void drawShot(Graphics g, tankType type, Shot currentShot) {
		switch (type) {
		case heroTank:// 敌人坦克
			g.setColor(Color.CYAN);
			break;
		case enemyTank:
			g.setColor(Color.ORANGE);
			break;
		}

		g.fill3DRect(currentShot.getX(), currentShot.getY(), 2, 2, true);
	}

	private void drawtank(Graphics g, int x, int y, object.direction direct,
			tankType type) {
		switch (type) {
		case heroTank:// 敌人坦克
			g.setColor(Color.CYAN);
			break;
		case enemyTank:
			g.setColor(Color.ORANGE);
			break;
		}

		switch (direct) {
		case UP:// 向上
			g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x + 15, y, 5, 30, false);
			g.fill3DRect(x + 5, y + 5, 10, 20, true);
			g.drawLine(x + 10, y + 15, x + 10, y - 5);
			break;
		case DOWN:// 向下
			g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x + 15, y, 5, 30, false);
			g.fill3DRect(x + 5, y + 5, 10, 20, true);
			g.drawLine(x + 10, y + 15, x + 10, y + 35);
			break;
		case LEFT:// 向左
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x, y + 15, 30, 5, false);
			g.fill3DRect(x + 5, y + 5, 20, 10, true);
			g.drawLine(x + 15, y + 10, x - 5, y + 10);
			break;
		case RIGHT:// 向右
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x, y + 15, 30, 5, false);
			g.fill3DRect(x + 5, y + 5, 20, 10, true);
			g.drawLine(x + 15, y + 10, x + 35, y + 10);
			break;
		}
	}

	//检测子弹是否打中坦克，包括自己的子弹是否打中敌人和敌人的子弹是否打中自己
	private void checkAllHittings(){
		//判断英雄的有效子弹是否打中 有效敌人坦克
		for (int i = 0; i < this.hero.getShots().size(); i++) {
			if (this.hero.getShots().get(i).isLive()) {// 防止死亡的子弹 打掉坦克
				for (int j = 0; j < this.enemis.size(); j++) {
					if (this.enemis.get(j).isLive()) {//只针对有效坦克
						this.hero.getShots().get(i)
						.isHittingTank(this.enemis.get(j));
					}
				}
			}
		}
		
		//判断敌人的有效子弹是否打中 有效英雄坦克
		for(int k = 0;k<this.enemis.size();k++){
			for (int i = 0; i < this.enemis.get(k).getShots().size(); i++) {
				if (this.enemis.get(k).getShots().get(i).isLive()) {// 防止死亡的子弹 打掉坦克
					
						if (this.hero.isLive()) {//只针对有效坦克
							this.enemis.get(k).getShots().get(i)
							.isHittingTank(this.hero);
						}
					}
				}
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

			if (this.hero.getShots().size() < 5) {// 每次最多发 5 颗子弹
				this.hero.ShotEnemy();
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

			//检测子弹是否打中坦克，包括自己的子弹是否打中敌人和敌人的子弹是否打中自己
			this.checkAllHittings();
					
			this.repaint();
		}
	}
}