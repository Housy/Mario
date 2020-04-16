package org.housy.mario;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class StaticValue {
	//�����
	public static List<BufferedImage> allMarioImage = new ArrayList<BufferedImage>();
	
	//����ͼƬ
	public static BufferedImage startImage = null;
	public static BufferedImage endImage = null;
	public static BufferedImage bgImage = null;
	
	//����
	public static List<BufferedImage> allFlowerImage = new ArrayList<BufferedImage>();
	public static List<BufferedImage> allTriangleImage = new ArrayList<BufferedImage>();
	public static List<BufferedImage> allTurtleImage = new ArrayList<BufferedImage>();
	public static List<BufferedImage> allFireImage = new ArrayList<BufferedImage>();
	
	//�ϰ���
	public static List<BufferedImage> allObstructionImage = new ArrayList<BufferedImage>();
	
	public static BufferedImage marioDeadImage = null;
	
	public static String imagePath = System.getProperty("user.dir") + "/mario";
	
	
	
	//��ʼ������ȫ��ͼƬ��ʼ��
	public static void init() {
		System.out.println(imagePath);
		for(int i = 1; i <=10; i ++) {
			//System.getProperty("")������Ŀ��ǰ���ڵ�·��
			try {
				//����������µ�ͼƬ���浽������
				allMarioImage.add(ImageIO.read(new File(imagePath + "/mario" + i + ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//���뱱��ͼƬ
		try {
			startImage = ImageIO.read(new File(imagePath + "/first.jpg"));
			endImage = ImageIO.read(new File(imagePath + "/end.jpg"));
			bgImage = ImageIO.read(new File(imagePath + "/background.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�������ͼƬ
		for(int i = 1; i <= 3; i ++) {
			try {
				if (i <= 2) {
					allFlowerImage.add(ImageIO.read(new File(imagePath + "/flower" + i + ".png")));
					allFireImage.add(ImageIO.read(new File(imagePath + "/fire" + i + ".png")));
				}
				allTriangleImage.add(ImageIO.read(new File(imagePath + "/triangle" + i + ".png")));
				allTurtleImage.add(ImageIO.read(new File(imagePath + "/turtuile" + i + ".png")));
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		//�ϰ���
		for(int i = 1; i <= 25; i ++) {
			try {
				allObstructionImage.add(ImageIO.read(new File(imagePath + "/db" + i + ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//��������ͼƬ
		try {
			marioDeadImage = ImageIO.read(new File(imagePath + "/over.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
