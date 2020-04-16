package org.housy.mario;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class StaticValue {
	//马里奥
	public static List<BufferedImage> allMarioImage = new ArrayList<BufferedImage>();
	
	//背景图片
	public static BufferedImage startImage = null;
	public static BufferedImage endImage = null;
	public static BufferedImage bgImage = null;
	
	//敌人
	public static List<BufferedImage> allFlowerImage = new ArrayList<BufferedImage>();
	public static List<BufferedImage> allTriangleImage = new ArrayList<BufferedImage>();
	public static List<BufferedImage> allTurtleImage = new ArrayList<BufferedImage>();
	public static List<BufferedImage> allFireImage = new ArrayList<BufferedImage>();
	
	//障碍物
	public static List<BufferedImage> allObstructionImage = new ArrayList<BufferedImage>();
	
	public static BufferedImage marioDeadImage = null;
	
	public static String imagePath = System.getProperty("user.dir") + "/mario";
	
	
	
	//初始化，将全部图片初始化
	public static void init() {
		System.out.println(imagePath);
		for(int i = 1; i <=10; i ++) {
			//System.getProperty("")返回项目当前所在的路径
			try {
				//将所有马里奥的图片保存到对象中
				allMarioImage.add(ImageIO.read(new File(imagePath + "/mario" + i + ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//导入北京图片
		try {
			startImage = ImageIO.read(new File(imagePath + "/first.jpg"));
			endImage = ImageIO.read(new File(imagePath + "/end.jpg"));
			bgImage = ImageIO.read(new File(imagePath + "/background.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//导入敌人图片
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
		//障碍物
		for(int i = 1; i <= 25; i ++) {
			try {
				allObstructionImage.add(ImageIO.read(new File(imagePath + "/db" + i + ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//导入死亡图片
		try {
			marioDeadImage = ImageIO.read(new File(imagePath + "/over.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
