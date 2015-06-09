package com.test.tank5;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.JPanel;

//ս����
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

		// if (enemySize > Recorder.getEnemyNum()) {//�����ϴ���Ϸ�ǿ��Իָ��ϴμ�¼
		// this.enemySize = Recorder.getEnemyNum();
		// //this.enemis = Recorder.getEnemies();
		// }
		if (conti.length > 0/*�ж��Ƿ����*//*���������ж��ϴεĵ����Ƿ�ȫ�������hero������
		                                                    ��Recorder.getEnemies()).size() = 0����ʵ����Recorder 
		                     readCurrentRecords()z��ʵ��*/
				&& (this.enemisRecord = Recorder.getEnemies()).size() > 0) {// �����ϴ���Ϸ�ǿ��Իָ��ϴμ�¼

			for (Enemy enemy : enemisRecord) {
				this.createEnemy(enemy);
			}
		} 
		else {
			// �������ˣ�
			enemis.clear();
			this.createEnemy(enemySize);
		}

	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, 310, 410);
		// ���Ƽ�¼��
		this.drwaRecord(g);

		// ����Ӣ�ۣ�
		this.drawHero(g);

		// ���Ƶ���
		this.drawEnemy(g);

		// for (int i = 0; i < enemis.size(); i++) {
		// Enemy currentEnemy = enemis.get(i);
		// if (currentEnemy.isLive()) {
		// this.drawtank(g, currentEnemy.getX(), currentEnemy.getY(),
		// currentEnemy.getDirect(), tankType.enemyTank);
		// //���Ƶ����ӵ�
		// //System.out.println(currentEnemy.getShots().size());
		// for(int j = 0;j < currentEnemy.getShots().size();j++){
		// Shot currentShot = currentEnemy.getShots().get(j);
		// if (/*null != currentShot &&*/ currentShot.isLive()) {
		// this.drawShot(g, tankType.enemyTank, currentShot);
		// //System.out.println("have"+currentEnemy.getShots().size());
		// }//endif
		// else{// ���û����䣬���� 5���ӵ���û���ӵ��ˡ�
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

		// �������
		if (enemis.size() < enemySize) {// ����ϵ��˼�����
			if (Recorder.getEnemyNum() >= enemySize) {// ��������ʣ��ĵ���Ӧ�����������ֵ��ͬ
				this.createEnemy(1);
				// Recorder.reduceEnemyNum();
			}

		}

		// this.createEnemy(enemySize - enemis.size());
		// ��Ӣ������
		if (!this.hero.isLive() && Recorder.getHeroLife() >= 1) {
			Recorder.reduceHeroLife();
			// this.hero.setLive(true);
			if (Recorder.getHeroLife() >= 1) {
				this.createHero();
			}
		}

	}

	private void drwaRecord(Graphics g) {
		// ����ͼ��ָʾ��
		this.drawtank(g, 80, 420, object.direction.UP, tankType.heroTank);
		this.drawtank(g, 180, 420, object.direction.UP, tankType.enemyTank);

		// ��������ֵ��
		g.setColor(Color.BLACK);
		g.drawString(Recorder.getHeroLife() + "", 120, 440);
		g.drawString(Recorder.getEnemyNum() + "", 220, 440);

		// ������Ҵ�����˼�¼��
		g.setFont(new Font("����", Font.BOLD, 20));
		g.drawString("�Ѵ��������", 330, 50);
		this.drawtank(g, 350, 70, object.direction.UP, tankType.enemyTank);
		g.setColor(Color.BLACK);
		g.drawString(Recorder.getAllDefeatedEnemyNum() + "", 400, 90);
	}

	private void drawHero(Graphics g) {
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
	}

	private void drawEnemy(Graphics g) {
		for (Iterator<Enemy> it = enemis.iterator(); it.hasNext();) {
			Enemy currentEnemy = (Enemy) it.next();
			if (currentEnemy.isLive()) {
				this.drawtank(g, currentEnemy.getX(), currentEnemy.getY(),
						currentEnemy.getDirect(), tankType.enemyTank);
				// ���Ƶ����ӵ�
				// System.out.println(currentEnemy.getShots().size());
				for (int j = 0; j < currentEnemy.getShots().size(); j++) {
					Shot currentShot = currentEnemy.getShots().get(j);
					if (/* null != currentShot && */currentShot.isLive()) {
						this.drawShot(g, tankType.enemyTank, currentShot);
						// System.out.println("have"+currentEnemy.getShots().size());
					}// endif
					else {// ���û����䣬���� 5���ӵ���û���ӵ��ˡ�
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

	// ����ָ��λ�ô���̹�ˣ������ڻָ��ֳ�
	private void createEnemy(Enemy enemyParam) {
		Enemy enemy = new Enemy(enemyParam.getX(), enemyParam.getY());
		enemy.setDirect(enemyParam.getDirect());
		enemyParam = null;
		enemy.setEnemies(enemis);// ÿ�δ���һ������̹�ˣ�Ҫ������ϵ���������̹�˴��ݹ�ȥ
		enemis.add(enemy);

		Thread threadEnemy = new Thread(enemy);
		threadEnemy.start();
		// ���������һ���ӵ���
		enemy.ShotEnemy();
	}

	// Ĭ��λ�ü�̹�ˣ���ʼ����Ϸ
	private void createEnemy(int theNUmberToBeCreated) {
		for (int i = 0; i < theNUmberToBeCreated; i++) {
			Enemy enemy = new Enemy(i * 50, 0);
			this.createEnemy(enemy);
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

	// ����ӵ��Ƿ����̹�ˣ������Լ����ӵ��Ƿ���е��˺͵��˵��ӵ��Ƿ�����Լ�
	private void checkAllHittings() {
		// �ж�Ӣ�۵���Ч�ӵ��Ƿ���� ��Ч����̹��
		for (int i = 0; i < this.hero.getShots().size(); i++) {
			if (this.hero.getShots().get(i).isLive()) {// ��ֹ�������ӵ� ���̹��
				for (int j = 0; j < this.enemis.size(); j++) {
					if (this.enemis.get(j).isLive()) {// ֻ�����Ч̹��
						this.hero.getShots().get(i)
								.isHittingTank(this.enemis.get(j));
					}
				}
			}
		}

		// �жϵ��˵���Ч�ӵ��Ƿ���� ��ЧӢ��̹��
		for (int k = 0; k < this.enemis.size(); k++) {
			for (int i = 0; i < this.enemis.get(k).getShots().size(); i++) {
				if (this.enemis.get(k).getShots().get(i).isLive()) {// ��ֹ�������ӵ�
																	// ���̹��

					if (this.hero.isLive()) {// ֻ�����Ч̹��
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

				if (this.hero.getShots().size() < 5) {// ÿ����෢ 5 ���ӵ�
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

			// ����ӵ��Ƿ����̹�ˣ������Լ����ӵ��Ƿ���е��˺͵��˵��ӵ��Ƿ�����Լ�
			this.checkAllHittings();

			this.repaint();
			// }
		}
	}
}
