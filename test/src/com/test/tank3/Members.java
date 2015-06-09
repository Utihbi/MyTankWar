package com.test.tank3;

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

class Shot extends object implements Runnable {

	public void moveDown() {
		if (this.getY() < 410) {
			//this.y += this.step;
			this.setY(this.getY() + this.getStep());
		}
	}
	public void moveRight() {
		if (this.getX() < 310) {
			//this.x += this.step;
			this.setX(this.getX() + this.getStep());
		}
	}
	
	public boolean isOutOfboundary() {
		if (this.getX() <=0 || this.getX() >=310 || this.getY() <=0 || this.getY() >=410) {
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
    
	public boolean isHittingTank(tank currentTank) {//�Ƿ����̹��
		switch (currentTank.getDirect()) {
		case UP:
		case DOWN:
			if (this.getX() > currentTank.getX() && this.getX() < currentTank.getX() + 20 &&
					this.getY() > currentTank.getY() && this.getY() < currentTank.getY() +30) {
				this.setLive(false);
				currentTank.setLive(false);
				return true;
			}
			break;
		case RIGHT:
		case LEFT:
			if (this.getX() > currentTank.getX() && this.getX() < currentTank.getX() + 30 &&
					this.getY() > currentTank.getY() && this.getY() < currentTank.getY() +20) {
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

			// �ж��ӵ��Ƿ�����
			if (isOutOfboundary() || ! this.isLive()
					/* ����̹��ʱ��this.isLive() == false  */)/* �߽� */
			{
				this.setLive(false);;
				break;
			}
		}

	}
}

class tank extends object {
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
			myShot = new Shot(this.getX() + 10, this.getY() + 15, direction.DOWN);
			break;
		case LEFT:
			myShot = new Shot(this.getX() + 15, this.getY() + 10, direction.LEFT);
			break;
		case RIGHT:
			myShot = new Shot(this.getX() + 15, this.getY() + 10, direction.RIGHT);
			break;
		}
		
		Shots.add(myShot);
		// �����ӵ��߳�
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
	public Hero(int x, int y) {
		super(x, y);
	}
}

class Enemy extends tank implements Runnable{
	int time = 0;//�����ӵ�ʱ��
	
	private Hero goal = null;
	private boolean isMovingToGoal = false;
	
	
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
 
	//������
	private void moveToGoal() {
		//System.out.println("goal.getY() , this.getY()  :"+goal.getY() +" , "+ this.getY());
			if (goal.getX() - this.getX() > 1) {
				this.setDirect(direction.RIGHT);
				System.out.println("goal.getX() , this.getX()  :"+goal.getX() +" , "+ this.getX());
			}
			else if (goal.getX() - this.getX() <-1) {
				this.setDirect(direction.LEFT);
			}
			else if (goal.getY() - this.getY() >1) {
				this.setDirect(direction.DOWN);
				//System.out.println("goal.getY() , this.getY()  :"+goal.getY() +" , "+ this.getY());
			}
			else if (goal.getY() - this.getY() <-1) {
				this.setDirect(direction.UP);;
			}

	}
	
	public void move() {
		
		if (isMovingToGoal) {//ȷ���Ƿ���Ҫ��Ŀ���ƶ�
			this.moveToGoal();
			return;
		}
		
		int directionRandom = (int)(Math.random()*4);
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
			switch (this.getDirect()) {
			case UP:
				for(int i = 0;i < 50;i++){
					this.moveUp();
					try {//������ ƽ���ƶ�
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			case DOWN:
				for(int i = 0;i < 50;i++){
					this.moveDown();
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			case LEFT:
				for(int i = 0;i < 50;i++){
					this.moveLeft();
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			case RIGHT:
				for(int i = 0;i < 50;i++){
					this.moveRight();
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			}
			
			//�������һ������Ϊ��һ���ƶ�����
			this.move();
			
			if (! this.isLive()) {
				break;
			}
			
			if( (time++) % 3 == 0){
				if (this.getShots().size() < 20) {
					this.ShotEnemy();
				}
			}
		}
	}

	public Enemy(int x, int y) {
		super(x, y);
		this.setDirect(direction.DOWN);
	}
}
