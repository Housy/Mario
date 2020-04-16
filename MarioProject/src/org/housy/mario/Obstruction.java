package org.housy.mario;

import java.awt.image.BufferedImage;

import javax.transaction.TransactionRequiredException;

public class Obstruction implements Runnable {
	//坐标
	private int y;
	private int x;
	
	private Thread thread = new Thread(this);
	
	private BackGround bg;
	
	//类型
	private int type;
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
	//初始的类型
	private int startType;
	//显示图片
	private BufferedImage showImage = null;
	
	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public BufferedImage getShowImage() {
		return showImage;
	}

	//构造方法
	public Obstruction(int x, int y, int type, BackGround bg) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.startType = type;
		this.bg = bg;
		setImage();
		if (this.type == 21) {
			thread.start();
		}
	}
	
	//重置方法
	public void reset() {
		//修改类型为初始类型
		this.type = startType;
		//改变显示图片
		this.setImage();
	}
	//根据类型改变显示图片
	public void setImage() {
		showImage = StaticValue.allObstructionImage.get(type);
		//System.out.println(type);
	}
	
	public void run() {
		while(true) {
			if (this.bg.isOver()) {
				if (this.y < 352) {
					this.y += 4;
				} else {
					this.bg.setDown(true);
				}
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
