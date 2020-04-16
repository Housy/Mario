package org.housy.mario;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
	//当前场景图片
	private BufferedImage bgImage = null;
	public void setBgImage(BufferedImage bgImage) {
		this.bgImage = bgImage;
	}

	private boolean isOver = false;
	private boolean isDown = false;
	
	public boolean isDown() {
		return isDown;
	}

	public void setDown(boolean isDown) {
		this.isDown = isDown;
	}

	public boolean isOver() {
		return isOver;
	}

	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}

	public BufferedImage getBgImage() {
		return bgImage;
	}

	//场景顺序
	private int sort;
	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getSort() {
		return sort;
	}

	//当前尝尽给是否为最后一个场景
	private boolean flag;
	public boolean isFlag() {
		return flag;
	}

	//通过集合来保存
	//全部的敌人
	private List<Enemy> allEnemy = new ArrayList<Enemy>();
	public List<Enemy> getAllEnemy() {
		return allEnemy;
	}

	//全部障碍物
	private List<Obstruction> allObstruction = new ArrayList<Obstruction>();
	public List<Obstruction> getAllObstruction() {
		return allObstruction;
	}

	//被消灭的敌人
	private List<Enemy> removedEnemy = new ArrayList<Enemy>();
	
	public List getRemovedEnemy() {
		return removedEnemy;
	}

	//被消灭的障碍物
	private List<Obstruction> removeObstruction = new ArrayList();
	
	public List<Obstruction> getRemoveObstruction() {
		return removeObstruction;
	}
	
	public void enemyStartMove() {
		for(int i = 0; i < this.allEnemy.size(); i ++) {
			this.allEnemy.get(i).startMove();
		}
	}
	

	//构造方法，初始化一些属性
	public BackGround(int sort, boolean flag) {
		this.sort = sort;
		this.flag = flag;
		if (flag) {
			bgImage = StaticValue.endImage;
		} else {
			bgImage = StaticValue.bgImage;
		}
		if (sort == 1) {
			for(int i = 0; i < 32; i ++) {
				this.allObstruction.add(new Obstruction(i*16, 384, 1,this));
			}
			this.allObstruction.add(new Obstruction(120, 304, 2,this));
			this.allObstruction.add(new Obstruction(136, 304, 2,this));
			this.allObstruction.add(new Obstruction(152, 304, 2,this));
			this.allObstruction.add(new Obstruction(200, 304, 12,this));
			this.allObstruction.add(new Obstruction(216, 304, 2,this));
			this.allObstruction.add(new Obstruction(232, 304, 2,this));
			this.allObstruction.add(new Obstruction(200, 240, 12,this));
			this.allObstruction.add(new Obstruction(248, 304, 2,this));
			this.allObstruction.add(new Obstruction(320, 384, 9,this));
			this.allObstruction.add(new Obstruction(336, 384, 10,this));
			this.allObstruction.add(new Obstruction(320, 368, 9,this));
			this.allObstruction.add(new Obstruction(336, 368, 10,this));
			this.allObstruction.add(new Obstruction(320, 352, 7,this));
			this.allObstruction.add(new Obstruction(336, 352, 8,this));
			this.allEnemy.add(new Enemy(304, 368, true, 1, this));
			this.allEnemy.add(new Enemy(328, 386, true, 2, 332, 360, this));
		}
		
		if (sort == 2) {
			for(int i = 0; i < 32; i ++) {
				this.allObstruction.add(new Obstruction(i*16, 384, 1,this));
			}
			this.allObstruction.add(new Obstruction(80, 384, 9,this));
			this.allObstruction.add(new Obstruction(96, 384, 10,this));
			this.allObstruction.add(new Obstruction(80, 368, 9,this));
			this.allObstruction.add(new Obstruction(96, 368, 10,this));
			this.allObstruction.add(new Obstruction(80, 352, 7,this));
			this.allObstruction.add(new Obstruction(96, 352, 8,this));
			this.allEnemy.add(new Enemy(88, 352, true, 2, 332, 360, this));
			
			this.allObstruction.add(new Obstruction(160, 384, 9,this));
			this.allObstruction.add(new Obstruction(176, 384, 10,this));
			this.allObstruction.add(new Obstruction(160, 368, 9,this));
			this.allObstruction.add(new Obstruction(176, 368, 10,this));
			this.allObstruction.add(new Obstruction(160, 352, 9,this));
			this.allObstruction.add(new Obstruction(176, 352, 10,this));
			this.allObstruction.add(new Obstruction(160, 336, 7,this));
			this.allObstruction.add(new Obstruction(176, 336, 8,this));
			this.allEnemy.add(new Enemy(168, 352, true, 2, 316, 350, this));
			
			this.allObstruction.add(new Obstruction(320, 384, 9,this));
			this.allObstruction.add(new Obstruction(336, 384, 10,this));
			this.allObstruction.add(new Obstruction(320, 368, 9,this));
			this.allObstruction.add(new Obstruction(336, 368, 10,this));
			this.allObstruction.add(new Obstruction(320, 352, 7,this));
			this.allObstruction.add(new Obstruction(336, 352, 8,this));
			this.allEnemy.add(new Enemy(328, 352, true, 2, 332, 360, this));
		}
		if (sort == 3) {
			for(int i = 0; i < 26; i ++) {
				this.allObstruction.add(new Obstruction(i*16, 384, 1,this));
			}
			for(int i = 29; i < 31; i ++) {
				this.allObstruction.add(new Obstruction(i*16, 384, 1,this));
			}
			this.allObstruction.add(new Obstruction(80, 384, 9,this));
			this.allObstruction.add(new Obstruction(96, 384, 10,this));
			this.allObstruction.add(new Obstruction(80, 368, 9,this));
			this.allObstruction.add(new Obstruction(96, 368, 10,this));
			this.allObstruction.add(new Obstruction(80, 352, 7,this));
			this.allObstruction.add(new Obstruction(96, 352, 8,this));
			this.allEnemy.add(new Enemy(88, 352, true, 2, 332, 360, this));
			
			this.allObstruction.add(new Obstruction(160, 384, 9,this));
			this.allObstruction.add(new Obstruction(176, 384, 10,this));
			this.allObstruction.add(new Obstruction(160, 368, 9,this));
			this.allObstruction.add(new Obstruction(176, 368, 10,this));
			this.allObstruction.add(new Obstruction(160, 352, 9,this));
			this.allObstruction.add(new Obstruction(176, 352, 10,this));
			this.allObstruction.add(new Obstruction(160, 336, 7,this));
			this.allObstruction.add(new Obstruction(176, 336, 8,this));
			this.allEnemy.add(new Enemy(168, 352, true, 2, 316, 350, this));
			
			this.allObstruction.add(new Obstruction(224, 304, 9,this));
			this.allObstruction.add(new Obstruction(240, 304, 10,this));
			this.allObstruction.add(new Obstruction(224, 384, 9,this));
			this.allObstruction.add(new Obstruction(240, 384, 10,this));
			this.allObstruction.add(new Obstruction(224, 368, 9,this));
			this.allObstruction.add(new Obstruction(240, 368, 10,this));
			this.allObstruction.add(new Obstruction(224, 352, 9,this));
			this.allObstruction.add(new Obstruction(240, 352, 10,this));
			this.allObstruction.add(new Obstruction(224, 336, 9,this));
			this.allObstruction.add(new Obstruction(240, 336, 10,this));
			this.allObstruction.add(new Obstruction(224, 320, 9,this));
			this.allObstruction.add(new Obstruction(240, 320, 10,this));
			this.allObstruction.add(new Obstruction(224, 288, 7,this));
			this.allObstruction.add(new Obstruction(240, 288, 8,this));
			this.allEnemy.add(new Enemy(232, 302, true, 2, 268, 300, this));
			
			this.allObstruction.add(new Obstruction(280, 288, 9,this));
			this.allObstruction.add(new Obstruction(296, 288, 10,this));
			this.allObstruction.add(new Obstruction(280, 304, 9,this));
			this.allObstruction.add(new Obstruction(296, 304, 10,this));
			this.allObstruction.add(new Obstruction(280, 384, 9,this));
			this.allObstruction.add(new Obstruction(296, 384, 10,this));
			this.allObstruction.add(new Obstruction(280, 368, 9,this));
			this.allObstruction.add(new Obstruction(296, 368, 10,this));
			this.allObstruction.add(new Obstruction(280, 352, 9,this));
			this.allObstruction.add(new Obstruction(296, 352, 10,this));
			this.allObstruction.add(new Obstruction(280, 336, 9,this));
			this.allObstruction.add(new Obstruction(296, 336, 10,this));
			this.allObstruction.add(new Obstruction(280, 320, 9,this));
			this.allObstruction.add(new Obstruction(296, 320, 10,this));
			this.allObstruction.add(new Obstruction(280, 272, 7,this));
			this.allObstruction.add(new Obstruction(296, 272, 8,this));
			this.allEnemy.add(new Enemy(288, 280, true, 2, 252, 280, this));
			
			this.allObstruction.add(new Obstruction(320, 384, 9,this));
			this.allObstruction.add(new Obstruction(336, 384, 10,this));
			this.allObstruction.add(new Obstruction(320, 368, 9,this));
			this.allObstruction.add(new Obstruction(336, 368, 10,this));
			this.allObstruction.add(new Obstruction(320, 352, 9,this));
			this.allObstruction.add(new Obstruction(336, 352, 10,this));
			this.allObstruction.add(new Obstruction(320, 336, 9,this));
			this.allObstruction.add(new Obstruction(336, 336, 10,this));
			this.allObstruction.add(new Obstruction(320, 320, 7,this));
			this.allObstruction.add(new Obstruction(336, 320, 8,this));
			this.allEnemy.add(new Enemy(328, 332, true, 2, 300, 330, this));
			
			this.allObstruction.add(new Obstruction(380, 250, 5,this));
			this.allObstruction.add(new Obstruction(396, 250, 5,this));
			this.allObstruction.add(new Obstruction(408, 250, 5,this));
			this.allObstruction.add(new Obstruction(416, 250, 5,this));
			this.allObstruction.add(new Obstruction(424, 250, 5,this));
//			this.allEnemy.add(new Enemy(440, 332, true, 2, 200, 330, this));
//			this.allEnemy.add(new Enemy(440, 316, true, 2, 200, 330, this));
//			this.allEnemy.add(new Enemy(440, 308, true, 2, 250, 330, this));
//			this.allEnemy.add(new Enemy(440, 300, true, 2, 300, 330, this));
			
			this.allObstruction.add(new Obstruction(400, 384, 9,this));
			this.allObstruction.add(new Obstruction(416, 384, 10,this));
			this.allObstruction.add(new Obstruction(400, 368, 9,this));
			this.allObstruction.add(new Obstruction(416, 368, 10,this));
			this.allObstruction.add(new Obstruction(400, 352, 7,this));
			this.allObstruction.add(new Obstruction(416, 352, 8,this));
			this.allEnemy.add(new Enemy(408, 352, true, 2, 332, 360, this));
		}
		if (sort == 4) {
			for(int i = 0; i < 31; i ++) {
				this.allObstruction.add(new Obstruction(i*16, 384, 1,this));
			}
			this.allObstruction.add(new Obstruction(300, 368, 13,this));
			this.allObstruction.add(new Obstruction(300, 150, 23,this));
			this.allObstruction.add(new Obstruction(309, 235, 21,this));
			this.allObstruction.add(new Obstruction(340, 256, 22,this));
		}
	}
	
	//重置方法,将所有的障碍物和敌人返回到原有坐标，并将其状态也改变
	public void reset() {
		
		this.allEnemy.addAll(this.removedEnemy);
		this.allObstruction.addAll(this.removeObstruction);
		for(int i = 0; i < this.allEnemy.size(); i ++) {
			this.allEnemy.get(i).reset();
		}
		for(int i = 0; i < this.allObstruction.size(); i ++) {
			this.allObstruction.get(i).reset();
		}
	
	}
	
}
