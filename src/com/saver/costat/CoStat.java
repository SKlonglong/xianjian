package com.saver.costat;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class CoStat {

	private Image[] images;
	private int x, y;
	private int index;
	private int len;
	private int j;
	private List<String> mesager;
	private int indexMesager;

	public CoStat(int x, int y, Image[] images) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.images = images;
		len = images.length;
		mesager = new ArrayList<String>();
	}

	public Image getImage() {
		return images[index];
	}

	public void next() {
		int k = 80 / len;
		if (j < k) {
			j++;
		} else {
			j = 0;
			if (index < len - 1) {
				index++;
			} else {
				index = 0;
			}
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getMesager() {
		if (indexMesager < mesager.size()) {
			String string = mesager.get(indexMesager);
			indexMesager++;
			return string;
		}
		indexMesager = 0;
		return "";
	}

	public void addMesager(String str) {
		mesager.add(str);
	}

}
