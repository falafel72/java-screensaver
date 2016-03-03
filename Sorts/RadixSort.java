/* 
	Radix sort (from least significant digit) - sort by digits, 
	make a new list each time with different digits in order and copy 
	values to the original array. No comparisons or swaps are needed. 
*/ 

package Screensaver.Sorts;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.application.Platform; 
import java.lang.Runnable;
import Screensaver.*;

public class RadixSort implements Sorter {
	private Rectangle[] list;
	private int length;
	private String titleText;
	private int base;

	
	public RadixSort() {
		list = new Rectangle[0];
		length = 0;
		base = 0;
		titleText = "Radix Sort";
	}
	
	public RadixSort(Rectangle[] rectList) {
		length = rectList.length;
		list = rectList;
		base = getRandom(2,10);
		titleText = "Radix Sort (base " + base + ")";
	}
	
	public void mainSort() {
		for(int i = 0; i < Integer.toString(Integer.parseInt("500", 10),base).length(); i++) // maximum height is 500 - convert to different base and get the length of that string
			sort(i);
	}
	
	public void sort(int digitPlace) {
		double[] tempHeightList = new double[length];
		double[] tempYList = new double[length];
		
		int index = 0;
		
		for(int i = 0; i < base; i++) {
			for(Rectangle j : list) {
				double rectHeight = j.getHeight();
				String baseConvert = Integer.toString(Integer.parseInt("" + (int)rectHeight,10),base);
				int key;
				if(baseConvert.length() - 1 - digitPlace < 0)
					key = 0;
				else 
					key = Integer.parseInt(Character.toString(baseConvert.charAt(baseConvert.length() - 1 - digitPlace)));
				
				
				if(key == i) {
					tempHeightList[index] = rectHeight;
					tempYList[index] = j.getY();
					index++;
				}	
			}
		}	
		
		for(int i = 0; i < length; i++) {
			list[i].setFill(Color.web("#215B95"));
			list[i].setHeight(tempHeightList[i]);
			list[i].setY(tempYList[i]);
			
			try {
				Thread.sleep(25);
				list[i].setFill(Color.web("#F0F0F0"));
			} catch (InterruptedException e) {
				
			}
		}
		
			
	}
	
	public void updateBase() {
		base = getRandom(2,10);
		titleText = "Radix Sort (base " + base + ")";
	}
	
	public void swap(int indexA, int indexB) {
		ScreensaverRunner.incrementSwaps();

		Rectangle rect1 = list[indexA];
		Rectangle rect2 = list[indexB];
		
		double tempHeight = rect1.getHeight();
		double tempY = rect1.getY();
		
		rect1.setHeight(rect2.getHeight());
		rect1.setY(rect2.getY());
		
		rect2.setHeight(tempHeight);
		rect2.setY(tempY);

	}
	
	public int getRandom(int min, int max) { //returns integer from 1-max, inclusive
		Random rand = new Random();
		int randNum = rand.nextInt(max - min + 1) + min;
		return randNum;
	}
	
	public String getTitleText() {
		return titleText;
	}
	
}