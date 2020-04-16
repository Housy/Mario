package org.housy.mario;

import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

public class Mario implements Runnable {
	private int x;
	private int y;
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	private Thread thread = null;
	private boolean isClear = false;
	
	public boolean isClear() {
		return isClear;
	}

	public void setClear(boolean isClear) {
		this.isClear = isClear;
	}
	//����һ����������
	private BackGround bg;
	
	public void setBg(BackGround bg) {
		this.bg = bg;
	}
	//�ٶ�
	private int xmove;
	//��ֱ����
	private int ymove;
	//״̬
	private String status;
	//��ʾ��ͼƬ
	private BufferedImage showImage;
	//�������ͷ���
	private int score;
	private int life;
	
	public int getLife() {
		return life;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setLife(int life) {
		this.life = life;
	}
	//��ǰ�ƶ�����ʾ��ͼƬ����
	private int moving = 0;
	
	//����ʱ��
	private int upTime = 80;
	private boolean isSit;
	
	private boolean isDead;
	private boolean onLand;
	private boolean reduce;
	
	

	public boolean isDead() {
		return isDead;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public BufferedImage getShowImage() {
		return showImage;
	}

	public Mario(int x, int y) {
		this.x = x;
		this.y = y;
		this.showImage = StaticValue.allMarioImage.get(5);
		this.score = 0;
		this.life = 3;
		
		thread = new Thread(this);
		thread.start();	
		
		
		this.status = "right-standing";
	}
	
	public void leftMove() {
		this.xmove = -8;
		//�����ǰ�Ѿ�����Ծ״̬��Ӧ�ñ���ԭ��״̬���������޸�Ϊ�ƶ�״̬
		if (this.status.indexOf("jumping") != -1) {
			this.status = "left-jumping";
		} else {
			this.status = "left-moving";
		}
		
	}
	
	public void rightMove() {
		this.xmove = 8;
		if (this.status.indexOf("jumping") != -1) {
			this.status = "right-jumping";
		} else {
			this.status = "right-moving";
		}
	}
	
	public void leftStop() {
		this.xmove = 0;
		this.status = "left-standing";
	}
	
	public void rightStop() {
		this.xmove = 0;
		this.status = "right-standing";
	}
	public void jump() {
		if(this.status.indexOf("jumping") == -1) {
			if(this.status.indexOf("left") != -1) {
				this.status = "left-jumping";
			} else {
				this.status = "right-jumping"; 
			}
			ymove = -8;
			upTime = 10;
			
		}
	}
	//�������䷽��
	public void down() {
		
		if(this.status.indexOf("left") != -1) {
			this.status = "left-jumping";
		} else {
			this.status = "right-jumping"; 
		}
		
	
			ymove = 8;
		
		
	}
	public void sit() {
		if(this.status.indexOf("standing") != -1) {
			
			if(this.status.indexOf("left") != -1) {
				this.status = "left-sitting"; 
			} else {
				
				this.status = "right-sitting"; 
			}
		}
		isSit = true;
		//System.out.println("1___________________" + this.status);
	}
	public void up() {

		if(this.status.indexOf("left") != -1) {
			this.status = "left-standing"; 
		} else {
			
			this.status = "right-standing"; 
		}
		isSit = false;
	}
	
	public void dead() {
		this.life --;
		reduce = true;
		
		if (this.life == 0) {
			this.isDead = true;
			
		} else {
			
			this.bg.reset();
			
			this.x = 0;
			this.y = 352;
			
		}
	}
	public void run() {
		while(true) {
			
			if (this.bg.isFlag() && this.x >= 290) {
				this.bg.setOver(true);
				if (this.bg.isDown()) {
					this.status = "right-moving";
					if (this.x >= 306) {
						if (y < 352) {
							y += 4;
						}
						
						if (x >= 392) {
							this.isClear = true;
						}
					} 
					x += 4;
					System.out.println(this.x);
				} else {
					if (this.y < 336) {
						this.y += 4;
					}
					if (this.y >= 336) {
						this.y = 336;
						this.status = "right-standing";
					}
				}
				
			} else {

				//�жϵ�ǰ��Mario�Ƿ����ϰ�����ײ
				boolean canleft = true;
				boolean canright = true;
				onLand = false;
				
				for(int i = 0; i < this.bg.getAllObstruction().size(); i++) {
					Obstruction ob = this.bg.getAllObstruction().get(i);
					
					
					//�����������ǰ�ƶ�
					if (ob.getX() == this.x + 16 && (ob.getY() + 14 > this.y && (ob.getY() - 14 < this.y))) {
						canright = false;
					}
					if (ob.getX() == this.x - 16 && (ob.getY() + 14 > this.y && (ob.getY() - 14 < this.y))) {
						canleft = false;
					}
					
					//����һ���ϰ�����
					if (ob.getY() == this.y + 32 && (ob.getX() + 16 > this.x && (ob.getX() - 16 < this.x))) {
						onLand = true;
					}
					//�ж�Mario�Ƿ�����Ծ�ж���һ���ϰ���
					
//					System.out.println("---------tempy= " + tempy + "\nthis.y = " + this.y
//							+ " oby = " + ob.getY() + "  onland = " + onLand);
					if (ob.getY() == this.y && ob.getX() + 14 > this.x && ob.getX() - 14 < this.x) {
						
						if (ob.getType() == 2) {
							this.bg.getAllObstruction().remove(ob);
							this.bg.getRemoveObstruction().add(ob);
							
						}
						//���ڣ�
						if (ob.getType() == 12) {
							ob.setType(13);
							ob.setImage();
						}
						this.upTime = 0;
//						try {
//							Thread.sleep(1000);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
						
					}
				}
				
				//�Ե��˵��ж�
				for(int i = 0; i < this.bg.getAllEnemy().size(); i ++) {
					Enemy enemy = this.bg.getAllEnemy().get(i);
					
					if ((enemy.getX() + 16 > this.x && enemy.getX() - 16 < this.x) &&
							(enemy.getY() - 16 <= this.y)) {
						System.out.println("this.y=" + this.y + "   gety = " + enemy.getY());
						
						this.dead();
						
					}
					if (enemy.getY() == this.y + 32
							&& (enemy.getX() + 14 > this.x && enemy.getX() - 14 < this.x)) {
						if (enemy.getType() == 1) {
							enemy.dead();
							this.upTime = 8;
							this.ymove = -4;
						} else if(enemy.getType() == 2) {
							this.dead();
							
						}
					}
				}
				
				//System.out.println(onLand + " " +  zhangai);
				//System.out.println("2___________________" + this.status + "time==" + upTime + "onland  " +onLand);
			
				if(!isSit) {
					if (onLand && upTime == 0) {
						
						if(this.status.indexOf("left") != -1) {
							if (xmove != 0) {
								this.status = "left-moving";
							} else {
								this.status = "left-standing";
							}
							
						} else {
							if (xmove != 0) {
								this.status = "right-moving";
							} else {
								this.status = "right-standing"; 
							}
						}
					} else {
						
						if (upTime != 0) {
							upTime --;
						} else {
							this.down();
							
							//System.out.println(this.getY());
						}
						y += ymove;
						
					}
				}
				
				if (this.y > 400) {
					this.dead();
				}
				
				if (canright && xmove > 0 ||(canleft && xmove < 0)) {
					x += xmove;
					if (x < 0) {
						x = 0;
					}
				}
				
				
			}
			//����һ��ͼƬȡ�ó�ʼ������
			int temp = 0;
			//System.out.println("4--------------------" + this.status);
			//��ǮΪ������
			if (this.status.indexOf("left") != -1) {
				temp += 5;
			}
			
			//�жϵ�ǰ�Ƿ��ƶ�
			if (this.status.indexOf("moving") != -1) {
				temp += this.moving;
				moving ++;
				if(moving == 3) {
					moving = 0;
				}
			}
			if (this.status.indexOf("sitting") != -1) {
				temp += 4;
			} 
			//�ж��Ƿ���Ծ
			if (this.status.indexOf("jumping") != -1) {
				temp += 3;
			}
			//System.out.println("----------temp " + temp);
			//�ı���ʾͼƬ
			this.showImage = StaticValue.allMarioImage.get(temp);
			try {
				Thread.sleep(50);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
