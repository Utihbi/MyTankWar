package com.test.tank2;


class shot implements Runnable{
	private int x = 0 ;
	private int y = 0 ;
	private tank.direction direct = tank.direction.UP ;
	private boolean isLive = false;
	private int step = 5;
	
	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	
	
	public shot(int x,int y,tank.direction direct){
		this.x = x;
		this.y = y;
		this.direct = direct;
		this.isLive = true;
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
	public tank.direction getDirect() {
		return direct;
	}
	public void setDirect(tank.direction direct) {
		this.direct = direct;
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
			switch (direct) {
			case UP:
				this.y = this.y - step;
				break;
	        case DOWN:
				this.y = this.y + step;
				break;
	        case LEFT:
		        this.x = this.x - step;
		        break;
	        case RIGHT:
		        this.x = this.x + step;
		        break;
			}
			
			//判断子弹是否死亡
			if ( x < 0 || x > 300 || y < 0 || y > 400)/*边界*/ {
				this.isLive = false;
				break;
			}
		}
		
	}
}

class tank{
	private shot myshot = null;
	
	private int x = 0;//坦克位置坐标
	private int y = 0;
	public enum direction {UP,DOWN,LEFT,RIGHT};
	private direction direct = direction.UP ;
	private int step = 5;
	
	public void shotEnemy() {
		switch (direct) {
		case UP:
			myshot = new shot(x+10, y+15 , direction.UP);
			break;
        case DOWN:
        	myshot = new shot(x+10, y+15 , direction.DOWN);
			break;
        case LEFT:
        	myshot = new shot(x+15, y+10 , direction.LEFT);
	        break;
        case RIGHT:
        	myshot = new shot(x+15, y+10 , direction.RIGHT);
	        break;
		}
		
		//启动子弹线程
		Thread threadShot =  new Thread(myshot);
		threadShot.start();
	}

	public tank(int x,int y){
		this.x = x;
		this.y = y;
	}

	public  boolean isOutOfboundary() {
		if ( x < 0 || x > 300 || y < 0 || y > 400) {
			return true;
		}
		return false;
	}
	public shot getMyshot() {
		return myshot;
	}

	public void setMyshot(shot myshot) {
		this.myshot = myshot;
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
}

class Hero extends tank{
	public Hero(int x,int y){
		super(x, y);
	}
	
	
	public void moveUp() {
			this.setY(this.getY() - this.getStep());	
	}
	public void moveDown() {
			this.setY(this.getY() + this.getStep());
	}
	public void moveLeft() {
			this.setX(this.getX() - this.getStep());		
	}
	public void moveRight() {
			this.setX(this.getX() + this.getStep());		
	}
}

class Enemy extends tank {
	public Enemy(int x,int y) {
		// TODO Auto-generated constructor stub
		super(x, y);
		this.setDirect(direction.DOWN);
	}
}
