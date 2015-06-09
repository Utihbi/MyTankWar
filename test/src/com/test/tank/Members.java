package com.test.tank;

class tank{
	private int x = 0;//Ì¹¿ËÎ»ÖÃ×ø±ê
	private int y = 0;
	public enum direction {UP,DOWN,LEFT,RIGHT};
	private direction direct = direction.UP ;
	private int step = 5;
	

	public tank(int x,int y){
		this.x = x;
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
