package com.test.tank5;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.JPanel;

//战场类
class mypanel extends JPanel implements KeyListener, Runnable {
	boolean isPaused = false;

	Hero hero = null;
	Vector<Enemy> enemis = new Vector<Enemy>();
	Vector<Enemy> enemisRecord = null;
	int enemySize = 6;

	enum tankType {
		heroTank, enemyTank
	};

	public mypanel(String... conti) {
		isPaused = false;
		this.createHero();

		// if (enemySize > Recorder.getEnemyNum()) {//继续上次游戏是可以恢复上次记录
		// this.enemySize = Recorder.getEnemyNum();
		// //this.enemis = Recorder.getEnemies();
		// }
		if (conti.length > 0/*判断是否继续*//*下面依据判断上次的敌人是否全死光如果hero已死，
		                                                    则Recorder.getEnemies()).size() = 0，改实现在Recorder 
		                     readCurrentRecords()z中实现*/
				&& (this.enemisRecord = Recorder.getEnemies()).size() > 0) {// 继续上次游戏是可以恢复上次记录

			for (Enemy enemy : enemisRecord) {
				this.createEnemy(enemy);
			}
		} 
		else {
			// 创建敌人；
			enemis.clear();
			this.createEnemy(enemySize);
		}

	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, 310, 410);
		// 绘制记录；
		this.drwaRecord(g);

		// 绘制英雄；
		this.drawHero(g);

		// 绘制敌人
		this.drawEnemy(g);

		// for (int i = 0; i < enemis.size(); i++) {
		// Enemy currentEnemy = enemis.get(i);
		// if (currentEnemy.isLive()) {
		// this.drawtank(g, currentEnemy.getX(), currentEnemy.getY(),
		// currentEnemy.getDirect(), tankType.enemyTank);
		// //绘制敌人子弹
		// //System.out.println(currentEnemy.getShots().size());
		// for(int j = 0;j < currentEnemy.getShots().size();j++){
		// Shot currentShot = currentEnemy.getShots().get(j);
		// if (/*null != currentShot &&*/ currentShot.isLive()) {
		// this.drawShot(g, tankType.enemyTank, currentShot);
		// //System.out.println("have"+currentEnemy.getShots().size());
		// }//endif
		// else{// 如果没有这句，发完 5颗子弹就没有子弹了。
		// currentEnemy.getShots().remove(currentShot);
		// //System.out.println(currentEnemy.getShots().size());
		// }//endelse
		// }//endfor
		// }//endif
		// else {
		// this.enemis.remove(currentEnemy);
		// //currentEnemy.setLive(true);
		// }//endelse
		// }//endfor

		// 补充敌人
		if (enemis.size() < enemySize) {// 面板上敌人减少了
			if (Recorder.getEnemyNum() >= enemySize) {// 最后面板上剩余的敌人应该与敌人生命值相同
				this.createEnemy(1);
				// Recorder.reduceEnemyNum();
			}

		}

		// this.createEnemy(enemySize - enemis.size());
		// 给英雄续命
		if (!this.hero.isLive() && Recorder.getHeroLife() >= 1) {
			Recorder.reduceHeroLife();
			// this.hero.setLive(true);
			if (Recorder.getHeroLife() >= 1) {
				this.createHero();
			}
		}

	}

	private void drwaRecord(Graphics g) {
		// 绘制图形指示；
		this.drawtank(g, 80, 420, object.direction.UP, tankType.heroTank);
		this.drawtank(g, 180, 420, object.direction.UP, tankType.enemyTank);

		// 绘制生命值；
		g.setColor(Color.BLACK);
		g.drawString(Recorder.getHeroLife() + "", 120, 440);
		g.drawString(Recorder.getEnemyNum() + "", 220, 440);

		// 绘制玩家打掉敌人记录；
		g.setFont(new Font("楷体", Font.BOLD, 20));
		g.drawString("已打掉敌人数", 330, 50);
		this.drawtank(g, 350, 70, object.direction.UP, tankType.enemyTank);
		g.setColor(Color.BLACK);
		g.drawString(Recorder.getAllDefeatedEnemyNum() + "", 400, 90);
	}

	private void drawHero(Graphics g) {
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
	}

	private void drawEnemy(Graphics g) {
		for (Iterator<Enemy> it = enemis.iterator(); it.hasNext();) {
			Enemy currentEnemy = (Enemy) it.next();
			if (currentEnemy.isLive()) {
				this.drawtank(g, currentEnemy.getX(), currentEnemy.getY(),
						currentEnemy.getDirect(), tankType.enemyTank);
				// 绘制敌人子弹
				// System.out.println(currentEnemy.getShots().size());
				for (int j = 0; j < currentEnemy.getShots().size(); j++) {
					Shot currentShot = currentEnemy.getShots().get(j);
					if (/* null != currentShot && */currentShot.isLive()) {
						this.drawShot(g, tankType.enemyTank, currentShot);
						// System.out.println("have"+currentEnemy.getShots().size());
					}// endif
					else {// 如果没有这句，发完 5颗子弹就没有子弹了。
						currentEnemy.getShots().remove(currentShot);
						// System.out.println(currentEnemy.getShots().size());
					}// endelse
				}// endfor
			}// endif
			else {
				it.remove();
				Recorder.reduceEnemyNum();
				// this.enemis.remove(currentEnemy);
				// currentEnemy.setLive(true);
			}// endelse
		}
	}

	private void createHero() {
		this.hero = new Hero(280, 370);
		this.hero.setStep(5);
		this.hero.setEnemies(this.enemis);
	}

	// 按照指定位置创建坦克；有利于恢复现场
	private void createEnemy(Enemy enemyParam) {
		Enemy enemy = new Enemy(enemyParam.getX(), enemyParam.getY());
		enemy.setDirect(enemyParam.getDirect());
		enemyParam = null;
		enemy.setEnemies(enemis);// 每次创建一个敌人坦克，要将面板上的其他敌人坦克传递过去
		enemis.add(enemy);

		Thread threadEnemy = new Thread(enemy);
		threadEnemy.start();
		// 给敌人添加一颗子弹；
		enemy.ShotEnemy();
	}

	// 默认位置间坦克，初始化游戏
	private void createEnemy(int theNUmberToBeCreated) {
		for (int i = 0; i < theNUmberToBeCreated; i++) {
			Enemy enemy = new Enemy(i * 50, 0);
			this.createEnemy(enemy);
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

	// 检测子弹是否打中坦克，包括自己的子弹是否打中敌人和敌人的子弹是否打中自己
	private void checkAllHittings() {
		// 判断英雄的有效子弹是否打中 有效敌人坦克
		for (int i = 0; i < this.hero.getShots().size(); i++) {
			if (this.hero.getShots().get(i).isLive()) {// 防止死亡的子弹 打掉坦克
				for (int j = 0; j < this.enemis.size(); j++) {
					if (this.enemis.get(j).isLive()) {// 只针对有效坦克
						this.hero.getShots().get(i)
								.isHittingTank(this.enemis.get(j));
					}
				}
			}
		}

		// 判断敌人的有效子弹是否打中 有效英雄坦克
		for (int k = 0; k < this.enemis.size(); k++) {
			for (int i = 0; i < this.enemis.get(k).getShots().size(); i++) {
				if (this.enemis.get(k).getShots().get(i).isLive()) {// 防止死亡的子弹
																	// 打掉坦克

					if (this.hero.isLive()) {// 只针对有效坦克
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
		if (e.getKeyCode() == KeyEvent.VK_E) {
			// isPaused = true;
			for (Enemy enemy : enemis) {
				enemy.setPaused();
				for (Shot curShot : enemy.getShots()) {
					curShot.setPaused();
				}
			}
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			// isPaused = false;
			for (Enemy enemy : enemis) {
				enemy.start();
				for (Shot curShot : enemy.getShots()) {
					curShot.start();
				}
			}
		}

		if (!isPaused) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_DOWN:
				this.hero.setDirect(tank.direction.DOWN);
				if (!this.hero.isTouchingOtherEnemyTank()) {
					this.hero.moveDown();
				}

				break;
			case KeyEvent.VK_UP:
				this.hero.setDirect(tank.direction.UP);
				if (!this.hero.isTouchingOtherEnemyTank()) {
					this.hero.moveUp();
				}

				break;
			case KeyEvent.VK_LEFT:
				this.hero.setDirect(tank.direction.LEFT);
				if (!this.hero.isTouchingOtherEnemyTank()) {
					this.hero.moveLeft();
				}

				break;
			case KeyEvent.VK_RIGHT:
				this.hero.setDirect(tank.direction.RIGHT);
				if (!this.hero.isTouchingOtherEnemyTank()) {
					this.hero.moveRight();
				}

				break;
			}

			if (KeyEvent.VK_J == e.getKeyCode()) {

				if (this.hero.getShots().size() < 5) {// 每次最多发 5 颗子弹
					this.hero.ShotEnemy();
				}
			}

			this.repaint();
		}
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
			// if (!isPaused) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}

			// 检测子弹是否打中坦克，包括自己的子弹是否打中敌人和敌人的子弹是否打中自己
			this.checkAllHittings();

			this.repaint();
			// }
		}
	}
}
