package com.saver.scene;

import java.awt.Image;

import com.saver.role.Role;
import com.saver.tool.ImageUtil;

public class Ljcysc extends Scene {

	public Ljcysc(int sw, int sh) {
		super(sw, sh);
		// TODO Auto-generated constructor stub
		Image bg = ImageUtil.getImage("李家村市场", "0");
		Image redMap = ImageUtil.getImage("李家村市场", "RedMap");
		setBackground(bg, redMap);
		setY(-300);
		
		Role role = new Role("李逍遥", 8);
		addRole(role);
		setRoleLocation(100, 400);
	}

}
