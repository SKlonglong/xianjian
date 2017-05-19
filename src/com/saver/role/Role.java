package com.saver.role;

import java.awt.Image;

import com.saver.tool.ImageUtil;

public class Role {
	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;

	private int width, height;
	// 角色坐标
	private int x, y;
	// 角色图片索引
	private int index = 0;
	// 主角方向
	private int direction = DOWN;
	// 主角的四个方向角色
	private Image[] left, right, up, down;

	public Role() {

	}

	public Role(String name, int number) {
		left = ImageUtil.getImages(name + "左", number);
		right = ImageUtil.getImages(name + "右", number);
		up = ImageUtil.getImages(name + "上", number);
		down = ImageUtil.getImages(name + "下", number);
		width = left[0].getWidth(null);
		height = left[0].getHeight(null);
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void addX(int dValue) {
		x = x + dValue;
	}

	public void addY(int dValue) {
		y = y + dValue;

	}

	public void moveToNext() {
		if (index < left.length - 1) {
			index++;
		} else {
			index = 0;
		}
	}

	public Image getImage() {
		Image image = null;
		switch (direction) {
		case UP:
			image = up[index];
			break;
		case DOWN:
			image = down[index];
			break;
		case LEFT:
			image = left[index];
			break;
		case RIGHT:
			image = right[index];
			break;

		}
		return image;
	}

	public void backToBefore() {
		index = 0;
	}

	public int getDirection() {
		return direction;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isHCenter() {
		if (getX() == 400) {
			return true;
		}
		return false;
	}

	public boolean isVCenter() {
		if (getY() == 300) {
			return true;
		}
		return false;
	}

}
