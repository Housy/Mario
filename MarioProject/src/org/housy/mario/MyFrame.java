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
     * ͨ����������һЩ��Ϣ
     */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	/**
	 * ����������ϵ�ĳһ����ʱ���ø÷���
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
	 * ̧�����
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

	//�������
    public MyFrame() {
    	
		// TODO Auto-generated constructor stub
    	this.setTitle("�������Ϸ����");
    	this.setSize(500,400);
    	//ȡ����Ļ�ĸ߶ȿ��
    	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    	int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    	//��ʾ��ǰ���ڵ�Ĭ����ʾλ��
    	this.setLocation((width - 500) / 2, (height - 400) / 2);
    	//�޷��ı䴰���С
    	this.setResizable(false);
    	
    	
    	//��ʼ��ȫ��ͼƬ
    	StaticValue.init();
    	
    	//����ȫ������
    	for(int i = 1; i <= 4; i ++) {  
    		this.allBG.add(new BackGround(i, i == 4 ? true : false));
    	}
    	//����һ����������Ϊ��ǰ����
    	this.nowBG = this.allBG.get(0);
    	
    	this.mario = new Mario(0, 352);
		
    	
    	this.mario.setBg(nowBG);
		
    	this.repaint();
    	
    	
    	this.addKeyListener(this);
    	thread.start();
    	
    	//�رմ���ʱͬʱ������������
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setVisible(true);
	}
    
    @Override
    public void paint(Graphics g) {
    	// TODO Auto-generated method stub
    	//������ʱ����ͼƬ
    	BufferedImage image = new BufferedImage(500, 400, BufferedImage.TYPE_3BYTE_BGR);
    	//�ѵ�ǰ�������Ƶ���Ļ��
    	Graphics g2 = image.getGraphics();
    	if (this.isStart) {
    		
    		g2.drawImage(this.nowBG.getBgImage(), 0, 0, this);
    		g2.drawString("������" + this.mario.getLife(), 400, 70);

        	Iterator<Enemy> iteratorEnemy = this.nowBG.getAllEnemy().iterator();
        	while(iteratorEnemy.hasNext()) {
        		Enemy enemy = iteratorEnemy.next();
        		g2.drawImage(enemy.getShowImage(), enemy.getX(), enemy.getY(), this);
        	}
        	
        	//�����ϰ���
        	Iterator<Obstruction> iterator =  this.nowBG.getAllObstruction().iterator();
        	while(iterator.hasNext()) {
        		Obstruction ob = iterator.next();
        		g2.drawImage(ob.getShowImage(), ob.getX(), ob.getY(), this);
        	}
        	g2.drawImage(this.mario.getShowImage(), this.mario.getX(), this.mario.getY(), this);
        	
		} else {
			g2.drawImage(StaticValue.startImage, 0, 0, this);
		}
    	
    	
    	//�ѻ���ͼƬ���Ƶ�������
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
			
					JOptionPane.showMessageDialog(this, "��Ϸ���������������");
					break;
				} 
				if (this.mario.isClear()) {
					JOptionPane.showMessageDialog(this, "��ϲ��Ϸͨ�أ����ڴ��Ժ�Ĺؿ�");
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
