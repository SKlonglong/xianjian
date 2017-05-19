package com.saver.scene;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.saver.costat.CoStat;
import com.saver.role.Role;

public class Scene {

	private int speed = 4;
	private Image background;
	private Image redMap;
	private int width, height;
	private int x, y;
	private List<Role> roles;
	private List<CoStat> coStats;
	private BufferedImage sceneImage;
	private int screenW, screenH;
	private Graphics graphics;
	private BufferedImage redCostat;
	private boolean isTalking;
	private int costartIndex;
	private List<Rectangle> changeRect;

	public Scene(int sw, int sh) {
		// TODO Auto-generated constructor stub
		roles = new ArrayList<Role>();
		coStats = new ArrayList<CoStat>();
		screenW = sw;
		screenH = sh;
		sceneImage = new BufferedImage(sw, sh, BufferedImage.TYPE_INT_RGB);
		changeRect = new ArrayList<Rectangle>();
	}

	public Image getBackground() {
		return background;
	}

	// 设置背景
	public void setBackground(Image background, Image redMap) {
		if (background != null && redMap != null) {
			this.background = background;
			this.redMap = redMap;
			this.width = background.getWidth(null);
			this.height = background.getHeight(null);
			redCostat = new BufferedImage(redMap.getWidth(null),
					redMap.getHeight(null), BufferedImage.TYPE_INT_RGB);
		}
	}

	// 背景宽度
	public int getWidth() {
		return width;
	}

	// 背景高度
	public int getHeight() {
		return height;
	}

	// 场景坐标
	public int getX() {
		return x;
	}

	// 场景左标
	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	// 添加角色
	public void addRole(Role role) {
		roles.add(role);
	}

	// 移除角色
	public void removeRole(Role role) {
		roles.remove(role);
	}

	// 左相应
	public void goLeft() {
		setDirction(Role.LEFT);
		if (isCanGoLeft()) {
			if (x <= 0) {
				// 如果每到左边界就向左移
				moveIndex();
				if (roles.get(0).isHCenter()) {
					x += speed;

				} else {
					moveRoleToLeft();
				}
				if (x > 0) {
					// 如果超过边界就回退一部
					x -= speed;
					moveRoleToLeft();
				}
				// repaint();
			}
		}

	}

	// 右相应
	public void goRight() {
		setDirction(Role.RIGHT);
		if (isCanGoRight()) {
			// 移动背景
			if (x >= screenW - width) {
				moveIndex();
				if (roles.get(0).isHCenter()) {
					x -= speed;
				} else {
					moveRoleToRight();
				}
				// 到边界
				if (x < screenW - width) {
					x += speed;
					moveRoleToRight();
				}
			}
		}

	}

	// 上相应
	public void goUp() {
		setDirction(Role.UP);
		if (isCanGoUp()) {
			if (y <= 0) {
				// 如果每到左边界就向左移
				moveIndex();
				if (roles.get(0).isVCenter()) {
					y += speed;

				} else {
					moveRoleToUp();
				}
				if (y > 0) {
					// 如果超过边界就回退一部
					y -= speed;
					moveRoleToUp();
				}

				// repaint();
			}
		}

	}

	// 下相应
	public void goDown() {
		setDirction(Role.DOWN);
		if (isCanGoDown()) {
			// 移动背景
			if (y >= screenH - height) {
				moveIndex();
				if (roles.get(0).isVCenter()) {
					y -= speed;
				} else {
					moveRoleToDown();
				}
				// 到边界
				if (y < screenH - height) {
					y += speed;
					moveRoleToDown();
				}
			}
			// repaint();

		}
	}

	// 整个场景
	public BufferedImage getSceneImage() {
		graphics = sceneImage.getGraphics();
		graphics.clearRect(0, 0, 800, 600);
		graphics.drawImage(background, x, y, null);
		drawCostats();
		for (int i = 0; i < roles.size(); i++) {
			Image image = roles.get(i).getImage();
			int x2 = roles.get(i).getX();
			int y2 = roles.get(i).getY();
			graphics.drawImage(image, x2, y2, null);
		}
		return sceneImage;
	}

	// 切换场景
	public void startScence(Scene scene, int x, int y) {
		background = scene.getBackground();
		redMap = scene.getRedMap();
		this.width = background.getWidth(null);
		this.height = background.getHeight(null);
		coStats = scene.getCoStats();

		for (int i = 0; i < roles.size(); i++) {
			Role role = roles.get(i);
			role.setX(x - 20 * i);
			role.setY(y);
		}
		x = scene.getX();
		y = scene.getY();
	}

	// 角色方向
	public void setDirction(int dir) {
		for (int i = 0; i < roles.size(); i++) {
			roles.get(i).setDirection(dir);
		}
	}

	// 角色图片切换
	public void moveIndex() {
		for (int i = 0; i < roles.size(); i++) {
			roles.get(i).moveToNext();
		}
	}

	// 角色左移动
	public void moveRoleToLeft() {
		for (int i = 0; i < roles.size(); i++) {
			Role role = roles.get(i);
			if (role.getX() >= 0) {
				role.addX(-speed);
				if (role.getX() < 0) {
					role.addX(speed);
					role.backToBefore();
				}
			}
		}
	}

	// 角色右移动
	public void moveRoleToRight() {
		for (int i = 0; i < roles.size(); i++) {
			Role role = roles.get(i);
			if (role.getX() + role.getWidth() <= screenW) {
				role.addX(speed);
				if (role.getX() + role.getWidth() > screenW) {
					role.addX(-speed);
					role.backToBefore();
				}
			}

		}
	}

	// 角色上移动
	public void moveRoleToUp() {
		for (int i = 0; i < roles.size(); i++) {
			Role role = roles.get(i);
			if (role.getY() >= 0) {
				role.addY(-speed);
				if (role.getY() < 0) {
					role.addY(speed);
					role.backToBefore();
				}
			}
		}
	}

	// 角色下移动
	public void moveRoleToDown() {
		for (int i = 0; i < roles.size(); i++) {
			Role role = roles.get(i);
			if (role.getY() + role.getHeight() + 30 <= screenH) {
				role.addY(speed);
				if (role.getY() + role.getHeight() + 30 > screenH) {
					role.addY(-speed);
					role.backToBefore();
				}
			}
		}
	}

	public Image getRedMap() {
		return redMap;
	}

	// 可以向左走
	public boolean isCanGoLeft() {
		int x2 = roles.get(0).getX() - x + roles.get(0).getWidth() / 2 - 15;
		int y2 = roles.get(0).getY() + roles.get(0).getHeight() - y;
		BufferedImage bufferedImage = new BufferedImage(redMap.getWidth(null),
				redMap.getHeight(null), BufferedImage.TYPE_INT_RGB);
		bufferedImage.getGraphics().drawImage(redMap, 0, 0, null);
		int rgb = bufferedImage.getRGB(x2, y2);
		if (rgb == -16777216 || rgb == -1) {
			int rgb2 = redCostat.getRGB(x2, y2);
			if (rgb2 == -16777216) {
				return true;
			}
		}

		return false;
	}

	// 可以向右走
	public boolean isCanGoRight() {
		int x2 = roles.get(0).getX() - x + roles.get(0).getWidth() / 2 + 15;
		int y2 = roles.get(0).getY() + roles.get(0).getHeight() - y;
		BufferedImage bufferedImage = new BufferedImage(redMap.getWidth(null),
				redMap.getHeight(null), BufferedImage.TYPE_INT_RGB);
		bufferedImage.getGraphics().drawImage(redMap, 0, 0, null);
		int rgb = bufferedImage.getRGB(x2, y2);
		if (rgb == -16777216 || rgb == -1) {
			int rgb2 = redCostat.getRGB(x2, y2);
			if (rgb2 == -16777216) {
				return true;
			}
		}

		return false;
	}

	// 可以向上走
	public boolean isCanGoUp() {
		int width2 = roles.get(0).getWidth();
		int height2 = roles.get(0).getHeight();
		int x2 = roles.get(0).getX() - x + width2 / 2;
		int y2 = roles.get(0).getY() + height2 - y - 15;
		BufferedImage bufferedImage = new BufferedImage(redMap.getWidth(null),
				redMap.getHeight(null), BufferedImage.TYPE_INT_RGB);
		bufferedImage.getGraphics().drawImage(redMap, 0, 0, null);
		int rgb = bufferedImage.getRGB(x2, y2);
		if (rgb == -16777216 || rgb == -1) {
			int rgb2 = redCostat.getRGB(x2, y2);
			if (rgb2 == -16777216) {
				if (!canChangeScenne()) {
					return true;
				}

			}
		}

		return false;
	}

	// 可以向下走
	public boolean isCanGoDown() {
		int x2 = roles.get(0).getX() - x + roles.get(0).getWidth() / 2;
		int y2 = roles.get(0).getY() + roles.get(0).getHeight() - y;
		BufferedImage bufferedImage = new BufferedImage(redMap.getWidth(null),
				redMap.getHeight(null), BufferedImage.TYPE_INT_RGB);
		bufferedImage.getGraphics().drawImage(redMap, 0, 0, null);
		int rgb = bufferedImage.getRGB(x2, y2);
		if (rgb == -16777216 || rgb == -1) {
			int rgb2 = redCostat.getRGB(x2, y2);
			if (rgb2 == -16777216) {
				return true;
			}
		}
		return false;
	}

	public void stop() {
		for (int i = 0; i < roles.size(); i++) {
			roles.get(i).backToBefore();
		}
	}

	public List<CoStat> getCoStats() {
		return coStats;
	}

	public void setCoStats(List<CoStat> coStats) {
		this.coStats = coStats;
	}

	public void addCoStat(CoStat coStat) {
		coStats.add(coStat);
		redCostat.getGraphics().drawImage(coStat.getImage(), coStat.getX(),
				coStat.getY(), null);
	}

	private void drawCostats() {
		Graphics graphics2 = sceneImage.getGraphics();
		Iterator<CoStat> iterator = coStats.iterator();
		while (iterator.hasNext()) {
			CoStat next = iterator.next();
			graphics2.drawImage(next.getImage(), next.getX() + x, next.getY()
					+ y, null);
			next.next();
		}

	}

	public boolean getCanTalking() {
		int x2 = roles.get(0).getX() - x;
		int y2 = roles.get(0).getY() - y;
		for (int i = 0; i < coStats.size(); i++) {
			int x3 = coStats.get(i).getX();
			int y3 = coStats.get(i).getY();
			int abs = Math.abs(y2 - y3);
			int abs2 = Math.abs(x2 - x3);
			if (abs < 50 && abs2 < 50) {
				costartIndex = i;
				return true;
			}
		}
		return false;
	}

	public String getMesager() {
		if (coStats.size() > 0) {
			return coStats.get(costartIndex).getMesager();
		}
		return "";
	}

	public boolean isTalking() {
		return isTalking;
	}

	public void setTalking(boolean isTalking) {
		this.isTalking = isTalking;
	}

	public boolean canChangeScenne() {
		int w = roles.get(0).getX() - x + roles.get(0).getWidth() / 2;
		int h = roles.get(0).getY() - y + roles.get(0).getHeight();
		System.out.println(w + ":" + h);
		for (int o = 0; o < changeRect.size(); o++) {
			Rectangle rectangle = changeRect.get(o);
			if (rectangle.contains(w, h)) {
				return true;
			}
		}
		return false;
	}

	public void addChangeRect(int x, int y, int width, int height) {
		Rectangle rect = new Rectangle(x, y, width, height);
		changeRect.add(rect);
	}

	public void setRoleLocation(int x, int y) {
		roles.get(0).setX(x);
		roles.get(0).setY(y);
	}
}
