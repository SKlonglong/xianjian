package com.saver.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.saver.scene.Ljc;
import com.saver.scene.Ljcysc;
import com.saver.scene.Scene;
import com.saver.tool.ImageUtil;

public class MainPanel extends JPanel implements KeyListener, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Scene scene;
	private boolean canTalking;
	private String mesager = "";

	public MainPanel() {
		// TODO Auto-generated constructor stub
		scene = new Ljc(800, 600);
	}

	@Override
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paint(arg0);
		BufferedImage sceneImage = scene.getSceneImage();
		if (canTalking) {
			drawTalkingDialog(sceneImage.getGraphics());
		}
		arg0.drawImage(sceneImage, 0, 0, this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

		int keyCode = arg0.getKeyCode();
		if (keyCode == KeyEvent.VK_SPACE) {

			canTalking = scene.getCanTalking();
			mesager = scene.getMesager();
			repaint();
		}
		if (scene.isTalking()) {
			return;
		}
		if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
			scene.goUp();
			repaint();
		}
		if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
			scene.goDown();
			repaint();
		}
		if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
			scene.goLeft();
			repaint();
		}
		if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
			scene.goRight();
			repaint();
		}
		if (keyCode == KeyEvent.VK_ENTER) {
			if (scene.canChangeScenne()) {
				scene = new Ljcysc(800, 600);
				repaint();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		scene.stop();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
		}
	}

	public void drawTalkingDialog(Graphics graphics) {
		Image image = ImageUtil.getImage("对话框", "0");
		int w = (800 - image.getWidth(null)) / 2;
		int h = 600 - image.getHeight(null);
		if (!mesager.equals("")) {
			graphics.drawImage(image, w, h, null);
			graphics.drawString(mesager, w + 40, h + 50);
			scene.setTalking(true);
		} else {
			scene.setTalking(false);
		}
	}

}
