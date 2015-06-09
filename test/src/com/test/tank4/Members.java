package com.test.tank4;

import java.util.Vector;

class object {
	private int x = 0;
	private int y = 0;
	private int step = 1;
	private boolean isLive = true;

	public enum direction {
		UP, DOWN, LEFT, RIGHT
	};

	private direction direct = direction.UP;

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public void moveUp() {
		if (this.y > 0) {
			this.y -= this.step;
		}
	}

	public void moveDown() {
		if (this.y < 380) {
			this.y += this.step;
		}
	}

	public void moveLeft() {
		if (this.x > 0) {
			this.x -= this.step;
		}
	}

	public void moveRight() {
		if (this.x < 280) {
			this.x += this.step;
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public direction getDirect() {
		return direct;
	}

	public void setDirect(direction direct) {
		this.direct = direct;
	}
}



class tank extends object {
	// private boolean isPaused = false;

	private Shot myShot = null;
	private Vector<Shot> Shots = new Vector<Shot>();

	public Vector<Shot> getShots() {
		return Shots;
	}

	public void setShots(Vector<Shot> Shots) {
		this.Shots = Shots;
	}

	public void ShotEnemy() {
		switch (this.getDirect()) {
		case UP:
			myShot = new Shot(this.getX() + 10, this.getY() + 15, direction.UP);
			break;
		case DOWN:
			myShot = new Shot(this.getX() + 10, this.getY() + 15,
					direction.DOWN);
			break;
		case LEFT:
			myShot = new Shot(this.getX() + 15, this.getY() + 10,
					direction.LEFT);
			break;
		case RIGHT:
			myShot = new Shot(this.getX() + 15, this.getY() + 10,
					direction.RIGHT);
			break;
		}

		Shots.add(myShot);
		// 启动子弹线程
		Thread threadShot = new Thread(myShot);
		threadShot.start();
	}

	public tank(int x, int y) {
		this.setX(x);
		this.setY(y);
		this.setLive(true);
	}

	private Shot getMyShot() {
		return myShot;
	}

	private void setMyShot(Shot myShot) {
		this.myShot = myShot;
	}
}

class Hero extends tank {
	private Vector<Enemy> enemies;
	
	public void setEnemies(Vector<Enemy> enemies) {
		this.enemies = enemies;
	}

	public boolean isTouchingOtherEnemyTank() {
		boolean isTouching = false;

		switch (this.getDirect()) {

		case UP:
			for (Enemy enemy : enemies) {
				//if (this != enemy) {// 若果是自己和自己比较 ，则直接跳过；
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
				//}
			}// endif

			break;
		case DOWN:// 我的坦克方向
			for (Enemy enemy : enemies) {
				//if (this != enemy) {
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
				//}
			}

			break;
		case LEFT:
			for (Enemy enemy : enemies) {
				//if (this != enemy) {
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
				//}
			}

			break;
		case RIGHT:
			for (Enemy enemy : enemies) {
				//if (this != enemy) {
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
				//}
			}

			break;
		}// endswitch

		return isTouching;
	}

	
	public Hero(int x, int y) {
		super(x, y);
	}
}


