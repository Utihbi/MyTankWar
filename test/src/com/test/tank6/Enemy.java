package com.test.tank6;

import java.util.ConcurrentModificationException;
import java.util.Vector;

class Enemy extends tank implements Runnable {
	int time = 0;// �����ӵ�ʱ��
	int tmpStep = 0;
	// private direction tmpDirect;

	private Hero goal = null;
	private boolean isMovingToGoal = false;

	private Vector<Enemy> enemies;// = new Vector<Enemy>();// û��new
									// һ�ῴ�Ƿ����run//�����������������̹�����ж��Ƿ��������ص���

	public void setEnemies(Vector<Enemy> enemies) {
		this.enemies = enemies;
	}

	public boolean isTouchingOtherEnemyTank() {
		boolean isTouching = false;

		switch (this.getDirect()) {

		case UP:
			for (Enemy enemy : enemies) {
				if (this != enemy) {// �������Լ����Լ��Ƚ� ����ֱ��������
					//System.out.println(this.isTouchingOtherEnemyTank(direction.UP, enemy));
					//return this.isTouchingOtherEnemyTank(/*direction.UP,*/ enemy);
					switch (enemy.getDirect()) {// switch2
					case UP:
					case DOWN:
						// �ж�������������Ƿ�������̹�������ڲ���//��һ����
						if (this.getX() >= enemy.getX()
								&& this.getX() <= enemy.getX() + 20
								&& this.getY() >= enemy.getY()
								&& this.getY() <= enemy.getY() + 30) {
							return true;
						}// endif
							// �ڶ�����
						if (this.getX() + 20 >= enemy.getX()
								&& this.getX() + 20 <= enemy.getX() + 20
								&& this.getY() >= enemy.getY()
								&& this.getY() <= enemy.getY() + 30) {
							return true;
						}// endif
						break;
					case LEFT:
					case RIGHT:
						if (this.getX() >= enemy.getX()
								&& this.getX() <= enemy.getX() + 30
								&& this.getY() >= enemy.getY()
								&& this.getY() <= enemy.getY() + 20) {
							return true;
						}
						if (this.getX() + 20 >= enemy.getX()
								&& this.getX() + 20 <= enemy.getX() + 30
								&& this.getY() >= enemy.getY()
								&& this.getY() <= enemy.getY() + 20) {
							return true;
						}
						break;
					}// endswitch2
				}
			}// endif

			break;
		case DOWN:// �ҵ�̹�˷���
			try {
				for (Enemy enemy : enemies) {
					if (this != enemy) {
						//return this.isTouchingOtherEnemyTank(direction.DOWN, enemy);
						switch (enemy.getDirect()) {// switch2 //��ĵ���̹�˷���
						case UP:
						case DOWN:
							// �ж�������������Ƿ�������̹�������ڲ���//��һ����
							if (this.getX() >= enemy.getX()
									&& this.getX() <= enemy.getX() + 20
									&& this.getY() + 30 >= enemy.getY()
									&& this.getY() + 30 <= enemy.getY() + 30) {
								return true;
							}// endif
								// �ڶ�����
							if (this.getX() + 20 >= enemy.getX()
									&& this.getX() + 20 <= enemy.getX() + 20
									&& this.getY() + 30 >= enemy.getY()
									&& this.getY() + 30 <= enemy.getY() + 30) {
								return true;
							}// endif
							break;
						case LEFT:
						case RIGHT:
							if (this.getX() >= enemy.getX()
									&& this.getX() <= enemy.getX() + 30
									&& this.getY() + 30 >= enemy.getY()
									&& this.getY() + 30 <= enemy.getY() + 20) {
								return true;
							}
							if (this.getX() + 20 >= enemy.getX()
									&& this.getX() + 20 <= enemy.getX() + 30
									&& this.getY() + 30 >= enemy.getY()
									&& this.getY() + 30 <= enemy.getY() + 20) {
								return true;
							}
							break;
						}// endswitch2
					}
				}
			} catch (ConcurrentModificationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		case LEFT:
			for (Enemy enemy : enemies) {
				if (this != enemy) {
					//return this.isTouchingOtherEnemyTank(direction.LEFT, enemy);
					switch (enemy.getDirect()) {// switch2
					case UP:
					case DOWN:
						// �ж�������������Ƿ�������̹�������ڲ���//��һ����
						if (this.getX() >= enemy.getX()
								&& this.getX() <= enemy.getX() + 20
								&& this.getY() >= enemy.getY()
								&& this.getY() <= enemy.getY() + 30) {
							return true;
						}// endif
							// �ڶ�����
						if (this.getX() >= enemy.getX()
								&& this.getX() <= enemy.getX() + 20
								&& this.getY() + 20 >= enemy.getY()
								&& this.getY() + 20 <= enemy.getY() + 30) {
							return true;
						}// endif
						break;
					case LEFT:
					case RIGHT:
						if (this.getX() >= enemy.getX()
								&& this.getX() <= enemy.getX() + 30
								&& this.getY() >= enemy.getY()
								&& this.getY() <= enemy.getY() + 20) {
							return true;
						}
						if (this.getX() >= enemy.getX()
								&& this.getX() <= enemy.getX() + 30
								&& this.getY() + 20 >= enemy.getY()
								&& this.getY() + 20 <= enemy.getY() + 20) {
							return true;
						}
						break;
					}// endswitch2
				}
			}

			break;
		case RIGHT:
			for (Enemy enemy : enemies) {
				if (this != enemy) {
					//System.out.println(this.isTouchingOtherEnemyTank(/*direction.RIGHT,*/ enemy));
					//return this.isTouchingOtherEnemyTank(/*direction.RIGHT,*/ enemy);
					//return true;
					switch (enemy.getDirect()) {// switch2
					case UP:
					case DOWN:
						// �ж�������������Ƿ�������̹�������ڲ���//��һ����
						if (this.getX() + 30 >= enemy.getX()
								&& this.getX() + 30 <= enemy.getX() + 20
								&& this.getY() >= enemy.getY()
								&& this.getY() <= enemy.getY() + 30) {
							return true;
						}// endif
							// �ڶ�����
						if (this.getX() + 30 >= enemy.getX()
								&& this.getX() + 30 <= enemy.getX() + 20
								&& this.getY() + 20 >= enemy.getY()
								&& this.getY() + 20 <= enemy.getY() + 30) {
							return true;
						}// endif
						break;
					case LEFT:
					case RIGHT:
						if (this.getX() + 30 >= enemy.getX()
								&& this.getX() + 30 <= enemy.getX() + 30
								&& this.getY() >= enemy.getY()
								&& this.getY() <= enemy.getY() + 20) {
							return true;
						}
						if (this.getX() + 30 >= enemy.getX()
								&& this.getX() + 30 <= enemy.getX() + 30
								&& this.getY() + 20 >= enemy.getY()
								&& this.getY() + 20 <= enemy.getY() + 20) {
							return true;
						}
						break;
					}// endswitch2
				}
			}

			break;
		}// endswitch

		return isTouching;
	}

	public void start() {
		this.setStep(tmpStep);
	}

	public void setPaused() {
		tmpStep = this.getStep();
		this.setStep(0);

		// tmpDirect = this.getDirect();
		// this.setDirect(direct);
	}

	public Hero getGoal() {
		return goal;
	}

	public void setGoal(Hero goal) {
		this.goal = goal;
	}

	public boolean isMovingToGoal() {
		return isMovingToGoal;
	}

	public void setMovingToGoal(boolean isMovingToGoal) {
		this.isMovingToGoal = isMovingToGoal;
	}

	// ������
	private void moveToGoal() {
		// System.out.println("goal.getY() , this.getY()  :"+goal.getY() +" , "+
		// this.getY());
		if (goal.getX() - this.getX() > 1) {
			this.setDirect(direction.RIGHT);
			System.out.println("goal.getX() , this.getX()  :" + goal.getX()
					+ " , " + this.getX());
		} else if (goal.getX() - this.getX() < -1) {
			this.setDirect(direction.LEFT);
		} else if (goal.getY() - this.getY() > 1) {
			this.setDirect(direction.DOWN);
			// System.out.println("goal.getY() , this.getY()  :"+goal.getY()
			// +" , "+ this.getY());
		} else if (goal.getY() - this.getY() < -1) {
			this.setDirect(direction.UP);
			;
		}

	}

	public void move() {

		if (isMovingToGoal) {// ȷ���Ƿ���Ҫ��Ŀ���ƶ�
			this.moveToGoal();
			return;
		}

		int directionRandom = (int) (Math.random() * 4);
		switch (directionRandom) {
		case 0:
			this.setDirect(direction.UP);
			break;
		case 1:
			this.setDirect(direction.DOWN);
			break;
		case 2:
			this.setDirect(direction.RIGHT);
			break;
		default:
			this.setDirect(direction.LEFT);
			break;
		}
	}

	public void run() {
		while (true) {
			// if (!isPaused()) {
			switch (this.getDirect()) {
			case UP:
				for (int i = 0; i < 100; i++) {

					if (!this.isTouchingOtherEnemyTank()) {
						this.moveUp();
					}

					try {// ������ ƽ���ƶ�
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			case DOWN:
				for (int i = 0; i < 100; i++) {
					if (!this.isTouchingOtherEnemyTank()) {
						this.moveDown();
					}

					try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			case LEFT:
				for (int i = 0; i < 100; i++) {
					if (!this.isTouchingOtherEnemyTank()) {
						this.moveLeft();
					}

					try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			case RIGHT:
				for (int i = 0; i < 100; i++) {
					if (!this.isTouchingOtherEnemyTank()) {
						this.moveRight();
					}

					try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			}

			// �������һ������Ϊ��һ���ƶ�����
			if (0 != this.getStep()) {// step=0 ����ͣ
				this.move();

				if ((time++) % 2 == 0) {
					if (this.getShots().size() < 3) {
						this.ShotEnemy();
					}
				}
			}

			if (!this.isLive()) {
				break;
			}

			// }
		}
	}

	public Enemy(int x, int y) {
		super(x, y);
		this.setDirect(direction.DOWN);
	}
}