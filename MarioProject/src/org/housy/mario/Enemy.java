package org.housy.mario;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.swing.text.html.HTML;

public class Enemy implements Runnable {
	private int x;
	private int y;
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	private int startX;
	private int startY;
	
	private int type;
	
	public int getType() {
		return type;
	}
	private BufferedImage showImage;
	
	public BufferedImage getShowImage() {
		return showImage;
	}
	private boolean isLeftOrUp = true;
	
	private int upMax = 0;
	private int downMax = 0;
	private Thread thread = new Thread(this);
	
	private int imageType;
	
	private BackGround bg;
	public void setBg(BackGround bg) {
		this.bg = bg;
	}
	
	private boolean dead = false;
	private boolean isThread = false;
	
	public Enemy(int x, int y, boolean isLeft, int type, BackGround bg) {
		this.x = x;
		this.y = y;
		this.startX = x;
		this.startY = y;
		this.isLeftOrUp = isLeft;
		this.type = type;
		this.bg = bg;
		if (type == 1) {
			this.showImage = StaticValue.allTriangleImage.get(1);
		}
		
		thread.start();
		
	}
	public Enemy(int x, int y, boolean isUp, int type, int upMax, int downMax, BackGround bg) {
		this.x = x;
		this.y = y;
		this.startX = x;
		this.startY = y;
		this.isLeftOrUp = isUp;
		this.type = type;
		this.upMax = upMax;
		this.downMax = downMax;
		this.bg = bg;
		if (type == 2) {
			this.showImage = StaticValue.allFlowerImage.get(0);
		}
		
		thread.start();
	}
	public void dead() {
		dead = true;
		this.showImage = StaticValue.allTriangleImage.get(0);
		try {
			thread.sleep(150);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.bg.getAllEnemy().remove(this);
		this.bg.getRemovedEnemy().add(this);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (!isThread) {
			synchronized (thread) {
				try {
					System.out.println("wait start");
					thread.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("start-----------------------");
		while(true) {
			if (type == 1 && !dead) {
				if (this.isLeftOrUp) {
					this.x -= 4;
				} else {
					this.x += 4;
				}
				if (imageType == 1) {
					imageType = 2;
				} else {
					imageType = 1;
				}
				
				boolean canleft = true;
				boolean canright = true;
				for(int i = 0; i < this.bg.getAllObstruction().size(); i++) {
					Obstruction ob = this.bg.getAllObstruction().get(i);
					
					//不允许继续向前移动
					if (ob.getX() == this.x + 16 && (ob.getY() + 14 > this.y && (ob.getY() - 14 < this.y))) {
						canright = false;
					}
					if (ob.getX() == this.x - 16 && (ob.getY() + 14 > this.y && (ob.getY() - 14 < this.y))) {
						canleft = false;
					}
				}
				if (!canleft && this.isLeftOrUp || this.x == 0) {
					this.isLeftOrUp = false;
				} else if (!canright && !this.isLeftOrUp || this.x == 584) {
					this.isLeftOrUp = true;
				} 
				this.showImage = StaticValue.allTriangleImage.get(imageType);
			}
			if (type == 2) {
				
				if (this.isLeftOrUp) {
					this.y -= 2;
				} else {
					this.y += 2;
				}
				
				if (imageType == 0) {
					imageType = 1;
				} else {
					imageType = 0;
				}
				//System.out.println("--------------this.y = " + this.y + " this.upmax" + this.upMax);
				if (this.isLeftOrUp && this.y == this.upMax) {
					this.isLeftOrUp = false;
				} 
				if (!this.isLeftOrUp && this.y == this.downMax) {
					this.isLeftOrUp = true;
				}
				this.showImage = StaticValue.allFlowerImage.get(imageType);
			
			}
			try {
				Thread.sleep(120);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void startMove() {
		synchronized (thread) {
			thread.notify();
			isThread = true;
		}
	}
	
	public void reset() {
		this.x = this.startX;
		this.y = this.startY;
		if (this.type == 1) {
			this.showImage = StaticValue.allTriangleImage.get(1);
		} else if (this.type == 2) {
			this.showImage = StaticValue.allFlowerImage.get(0);
		}
	}
}
