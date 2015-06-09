package com.test.tank6;

import java.util.ConcurrentModificationException;
import java.util.Vector;

class Enemy extends tank implements Runnable {
	int time = 0;// 控制子弹时间
	int tmpStep = 0;
	// private direction tmpDirect;

	private Hero goal = null;
	private boolean isMovingToGoal = false;

	private Vector<Enemy> enemies;// = new Vector<Enemy>();// 没有new
									// 一会看是否可以run//接收面板上其他敌人坦克来判断是否与他们重叠；

	public void setEnemies(Vector<Enemy> enemies) {
		this.enemies = enemies;
	}

	public boolean isTouchingOtherEnemyTank() {
		boolean isTouching = false;

		switch (this.getDirect()) {

		case UP:
			for (Enemy enemy : enemies) {
				if (this != enemy) {// 若果是自己和自己比较 ，则直接跳过；
					//System.out.println(this.isTouchingOtherEnemyTank(direction.UP, enemy));
					//return this.isTouchingOtherEnemyTank(/*direction.UP,*/ enemy);
					switch (enemy.getDirect()) {// switch2
					case UP:
					case DOWN:
						// 判断两个点的坐标是否进入敌人坦克区域内部；//第一个点
						if (this.getX() >= enemy.getX()
								&& this.getX() <= enemy.getX() + 20
								&& this.getY() >= enemy.getY()
								&& this.getY() <= enemy.getY() + 30) {
							return true;
						}// endif
							// 第二个点
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
		case DOWN:// 我的坦克方向
			try {
				for (Enemy enemy : enemies) {
					if (this != enemy) {
						//return this.isTouchingOtherEnemyTank(direction.DOWN, enemy);
						switch (enemy.getDirect()) {// switch2 //别的敌人坦克方向
						case UP:
						case DOWN:
							// 判断两个点的坐标是否进入敌人坦克区域内部；//第一个点
							if (this.getX() >= enemy.getX()
									&& this.getX() <= enemy.getX() + 20
									&& this.getY() + 30 >= enemy.getY()
									&& this.getY() + 30 <= enemy.getY() + 30) {
								return true;
							}// endif
								// 第二个点
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
						// 判断两个点的坐标是否进入敌人坦克区域内部；//第一个点
						if (this.getX() >= enemy.getX()
								&& this.getX() <= enemy.getX() + 20
								&& this.getY() >= enemy.getY()
								&& this.getY() <= enemy.getY() + 30) {
							return true;
						}// endif
							// 第二个点
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
						// 判断两个点的坐标是否进入敌人坦克区域内部；//第一个点
						if (this.getX() + 30 >= enemy.getX()
								&& this.getX() + 30 <= enemy.getX() + 20
								&& this.getY() >= enemy.getY()
								&& this.getY() <= enemy.getY() + 30) {
							return true;
						}// endif
							// 第二个点
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

	// 有问题
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

		if (isMovingToGoal) {// 确认是否需要向目标移动
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

					try {// 看起来 平滑移动
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

			// 随机生成一个方向，为下一次移动方向
			if (0 != this.getStep()) {// step=0 是暂停
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