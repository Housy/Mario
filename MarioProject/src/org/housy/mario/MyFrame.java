package org.housy.mario;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.x500.X500Principal;
import javax.sound.midi.Track;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MyFrame extends JFrame implements KeyListener,Runnable {
	
	private List<BackGround> allBG = new ArrayList<BackGround>();
	
	private BackGround nowBG = null;
	
	private Mario mario = null;
	
	private Thread thread = new Thread(this);
	
	private boolean isStart;
	
	private boolean isEnd = false;
	
	/**
     * 通过键盘输入一些信息
     */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	/**
	 * 当点击键盘上的某一个键时调用该方法
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(e.getKeyCode());
		if (isStart) {
			if (e.getKeyCode() == 37) {
				this.mario.leftMove();
			}
			if (e.getKeyCode() == 39) {
				this.mario.rightMove();
			}
			
			if (e.getKeyCode() == 40) {
				this.mario.sit();
			}
			if (e.getKeyCode() == 32) {
				this.mario.jump();
			}
		} else {
			if (e.getKeyCode() ==32) {
				this.isStart = true;
				this.nowBG.enemyStartMove();
				this.mario.setScore(0);
				this.mario.setLife(3);
			}
		}
		
	}

	/**
	 * 抬起键盘
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (isStart) {
			if (e.getKeyCode() == 37) {
				this.mario.leftStop();
			}
			if (e.getKeyCode() == 39) {
				this.mario.rightStop();
			}
			
			if (e.getKeyCode() == 40) {
				this.mario.up();
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		System.out.println(StaticValue.imagePath + "/db" + 2 + ".png");
		new MyFrame();
	}

	//窗体标题
    public MyFrame() {
    	
		// TODO Auto-generated constructor stub
    	this.setTitle("马里奥游戏程序");
    	this.setSize(500,400);
    	//取得屏幕的高度宽度
    	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    	int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    	//显示当前窗口的默认显示位置
    	this.setLocation((width - 500) / 2, (height - 400) / 2);
    	//无法改变窗体大小
    	this.setResizable(false);
    	
    	
    	//初始化全部图片
    	StaticValue.init();
    	
    	//创建全部场景
    	for(int i = 1; i <= 4; i ++) {  
    		this.allBG.add(new BackGround(i, i == 4 ? true : false));
    	}
    	//将第一个场景设置为当前场景
    	this.nowBG = this.allBG.get(0);
    	
    	this.mario = new Mario(0, 352);
		
    	
    	this.mario.setBg(nowBG);
		
    	this.repaint();
    	
    	
    	this.addKeyListener(this);
    	thread.start();
    	
    	//关闭窗体时同时结束程序运行
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setVisible(true);
	}
    
    @Override
    public void paint(Graphics g) {
    	// TODO Auto-generated method stub
    	//建立临时缓存图片
    	BufferedImage image = new BufferedImage(500, 400, BufferedImage.TYPE_3BYTE_BGR);
    	//把当前北京绘制到屏幕上
    	Graphics g2 = image.getGraphics();
    	if (this.isStart) {
    		
    		g2.drawImage(this.nowBG.getBgImage(), 0, 0, this);
    		g2.drawString("生命：" + this.mario.getLife(), 400, 70);

        	Iterator<Enemy> iteratorEnemy = this.nowBG.getAllEnemy().iterator();
        	while(iteratorEnemy.hasNext()) {
        		Enemy enemy = iteratorEnemy.next();
        		g2.drawImage(enemy.getShowImage(), enemy.getX(), enemy.getY(), this);
        	}
        	
        	//绘制障碍物
        	Iterator<Obstruction> iterator =  this.nowBG.getAllObstruction().iterator();
        	while(iterator.hasNext()) {
        		Obstruction ob = iterator.next();
        		g2.drawImage(ob.getShowImage(), ob.getX(), ob.getY(), this);
        	}
        	g2.drawImage(this.mario.getShowImage(), this.mario.getX(), this.mario.getY(), this);
        	
		} else {
			g2.drawImage(StaticValue.startImage, 0, 0, this);
		}
    	
    	
    	//把缓存图片绘制到窗体中
    	g.drawImage(image, 0, 0, this);
    }
	
   
	public void run() {
		
		while(true) {
			this.repaint();
			try {
				Thread.sleep(50);
				if (this.mario.getX() >= 486) {
					this.nowBG = this.allBG.get(this.nowBG.getSort());
					this.mario.setBg(this.nowBG);
					this.nowBG.enemyStartMove();
					this.mario.setX(0);
				}
				if (this.mario.isDead()) {
			
					JOptionPane.showMessageDialog(this, "游戏结束，马里奥死亡");
					break;
				} 
				if (this.mario.isClear()) {
					JOptionPane.showMessageDialog(this, "恭喜游戏通关，请期待以后的关卡");
					break;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		main(null);
	}
}
