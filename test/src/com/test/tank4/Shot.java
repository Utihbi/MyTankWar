package com.test.tank4;

class Shot extends object implements Runnable {

	private int tmpStep = 0;

	// public boolean isPaused() {
	// return isPaused;
	// }

	public void start() {
		this.setStep(tmpStep);
	}

	public void setPaused() {
		tmpStep = this.getStep();
		this.setStep(0);
	}

	public void moveDown() {
		if (this.getY() < 410) {
			// this.y += this.step;
			this.setY(this.getY() + this.getStep());
		}
	}

	public void moveRight() {
		if (this.getX() < 310) {
			// this.x += this.step;
			this.setX(this.getX() + this.getStep());
		}
	}

	public boolean isOutOfboundary() {
		if (this.getX() <= 0 || this.getX() >= 310 || this.getY() <= 0
				|| this.getY() >= 410) {
			return true;
		}
		return false;
	}

	public Shot(int x, int y, object.direction direct) {
		this.setX(x);
		this.setY(y);
		this.setDirect(direct);
		this.setLive(true);
		this.setStep(5);
	}

	public boolean isHittingTank(tank currentTank) {// 是否击中坦克
		switch (currentTank.getDirect()) {
		case UP:
		case DOWN:
			if (this.getX() > currentTank.getX()
					&& this.getX() < currentTank.getX() + 20
					&& this.getY() > currentTank.getY()
					&& this.getY() < currentTank.getY() + 30) {
				this.setLive(false);
				currentTank.setLive(false);
				return true;
			}
			break;
		case RIGHT:
		case LEFT:
			if (this.getX() > currentTank.getX()
					&& this.getX() < currentTank.getX() + 30
					&& this.getY() > currentTank.getY()
					&& this.getY() < currentTank.getY() + 20) {
				this.setLive(false);
				currentTank.setLive(false);
				return true;
			}
			break;
		}
		return false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {

			// if (!isPaused()) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}
			switch (this.getDirect()) {
			case UP:
				this.moveUp();
				break;
			case DOWN:
				this.moveDown();
				break;
			case LEFT:
				this.moveLeft();
				break;
			case RIGHT:
				this.moveRight();
				break;
			}

			// 判断子弹是否死亡
			if (isOutOfboundary() || !this.isLive()
			/* 击中坦克时，this.isLive() == false */)/* 边界 */
			{
				this.setLive(false);
				break;
			}
			// }

		}

	}
}