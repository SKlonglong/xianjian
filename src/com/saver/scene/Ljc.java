package com.saver.scene;

import java.awt.Image;

import com.saver.costat.CoStat;
import com.saver.role.Role;
import com.saver.tool.ImageUtil;

public class Ljc extends Scene {

	public Ljc(int sw, int sh) {
		super(sw, sh);
		// TODO Auto-generated constructor stub
		Image bg = ImageUtil.getImage("李家村", "0");
		Image redMap = ImageUtil.getImage("李家村", "RedMap");
		Image[] wcsImages = ImageUtil.getImages("旺财嫂", 14);
		Image[] awsImages = ImageUtil.getImages("阿旺婶", 17);
		Image[] azwjImages = ImageUtil.getImages("阿朱喂鸡", 6);
		Image[] mjImages = ImageUtil.getImages("母鸡", 6);
		Image[] xjImages = ImageUtil.getImages("小鸡", 2);
		Image[] xxjImages = ImageUtil.getImages("小小鸡", 2);
		Image[] tsImages = ImageUtil.getImages("跳绳", 4);
		Role role = new Role("李逍遥", 8);
		setBackground(bg, redMap);
		addRole(role);
		addCoStat(new CoStat(660, 550, awsImages));
		CoStat coStat = new CoStat(800, 550, wcsImages);
		coStat.addMesager("你好，我是旺财嫂");
		coStat.addMesager("李大婶病了，你感觉去看看把");
		addCoStat(coStat);
		addCoStat(new CoStat(1550, 1050, azwjImages));
		addCoStat(new CoStat(1520, 1100, mjImages));
		addCoStat(new CoStat(1480, 1130, xjImages));
		addCoStat(new CoStat(1470, 1160, xxjImages));
		addCoStat(new CoStat(1300, 800, tsImages));
		setX(-500);
		setY(-300);
		addChangeRect(1470, 700, 200, 200);
		setRoleLocation(400, 300);

	}
}
