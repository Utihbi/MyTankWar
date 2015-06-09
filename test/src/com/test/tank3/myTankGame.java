/*verison  3.0
 * �� j �� �����ӵ�
 * ���е��ˣ��ӵ��͵�����ʧ
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

class mypanel extends JPanel implements KeyListener, Runnable {
	Hero hero = null;
	Vector<Enemy> enemis = new Vector<Enemy>();
	int enemySize = 4;

	enum tankType {
		heroTank, enemyTank
	};

	public mypanel() {
		
		this.createHero();

		//�������ˣ�
		this.createEnemy(enemySize);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, 310, 410);
		// ����Ӣ�ۣ�
		if (this.hero.isLive()) {
			this.drawtank(g, hero.getX(), hero.getY(), hero.getDirect(),
					tankType.heroTank);
		}
		
		// ����Ӣ���ӵ�
		for (int i = 0; i < this.hero.getShots().size(); i++) {
			Shot currentShot = this.hero.getShots().get(i);
			if (null != currentShot && currentShot.isLive()) {
				this.drawShot(g, tankType.heroTank, currentShot);
			}
			if (!currentShot.isLive()) {// ���û����䣬���� 5���ӵ���û���ӵ��ˡ�
				this.hero.getShots().remove(currentShot);
			}
		}

		//���Ƶ���
		for (int i = 0; i < enemis.size(); i++) {
			Enemy currentEnemy = enemis.get(i);
			if (currentEnemy.isLive()) {
				this.drawtank(g, currentEnemy.getX(), currentEnemy.getY(),
						currentEnemy.getDirect(), tankType.enemyTank);
				//���Ƶ����ӵ�
				//System.out.println(currentEnemy.getShots().size());
				for(int j = 0;j < currentEnemy.getShots().size();j++){
					Shot currentShot = currentEnemy.getShots().get(j);
					if (/*null != currentShot &&*/ currentShot.isLive()) {
						this.drawShot(g, tankType.enemyTank, currentShot);
						//System.out.println("have"+currentEnemy.getShots().size());
					}//endif
					else{// ���û����䣬���� 5���ӵ���û���ӵ��ˡ�
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
		
		//�������
		this.createEnemy(enemySize - enemis.size());
		//��Ӣ������
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
			//���������һ���ӵ���
			enemy.ShotEnemy();
		}
	}
	
	private void drawShot(Graphics g, tankType type, Shot currentShot) {
		switch (type) {
		case heroTank:// ����̹��
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
		case heroTank:// ����̹��
			g.setColor(Color.CYAN);
			break;
		case enemyTank:
			g.setColor(Color.ORANGE);
			break;
		}

		switch (direct) {
		case UP:// ����
			g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x + 15, y, 5, 30, false);
			g.fill3DRect(x + 5, y + 5, 10, 20, true);
			g.drawLine(x + 10, y + 15, x + 10, y - 5);
			break;
		case DOWN:// ����
			g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x + 15, y, 5, 30, false);
			g.fill3DRect(x + 5, y + 5, 10, 20, true);
			g.drawLine(x + 10, y + 15, x + 10, y + 35);
			break;
		case LEFT:// ����
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x, y + 15, 30, 5, false);
			g.fill3DRect(x + 5, y + 5, 20, 10, true);
			g.drawLine(x + 15, y + 10, x - 5, y + 10);
			break;
		case RIGHT:// ����
			g.fill3DRect(x, y, 30, 5, false);
			g.fill3DRect(x, y + 15, 30, 5, false);
			g.fill3DRect(x + 5, y + 5, 20, 10, true);
			g.drawLine(x + 15, y + 10, x + 35, y + 10);
			break;
		}
	}

	//����ӵ��Ƿ����̹�ˣ������Լ����ӵ��Ƿ���е��˺͵��˵��ӵ��Ƿ�����Լ�
	private void checkAllHittings(){
		//�ж�Ӣ�۵���Ч�ӵ��Ƿ���� ��Ч����̹��
		for (int i = 0; i < this.hero.getShots().size(); i++) {
			if (this.hero.getShots().get(i).isLive()) {// ��ֹ�������ӵ� ���̹��
				for (int j = 0; j < this.enemis.size(); j++) {
					if (this.enemis.get(j).isLive()) {//ֻ�����Ч̹��
						this.hero.getShots().get(i)
						.isHittingTank(this.enemis.get(j));
					}
				}
			}
		}
		
		//�жϵ��˵���Ч�ӵ��Ƿ���� ��ЧӢ��̹��
		for(int k = 0;k<this.enemis.size();k++){
			for (int i = 0; i < this.enemis.get(k).getShots().size(); i++) {
				if (this.enemis.get(k).getShots().get(i).isLive()) {// ��ֹ�������ӵ� ���̹��
					
						if (this.hero.isLive()) {//ֻ�����Ч̹��
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

			if (this.hero.getShots().size() < 5) {// ÿ����෢ 5 ���ӵ�
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

			//����ӵ��Ƿ����̹�ˣ������Լ����ӵ��Ƿ���е��˺͵��˵��ӵ��Ƿ�����Լ�
			this.checkAllHittings();
					
			this.repaint();
		}
	}
}