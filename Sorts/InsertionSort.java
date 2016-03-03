/* 
	Insertion sort - takes an item and swaps it with previous items until
	an item is found which is smaller than it. Repeat for all subsequent items
	until the list is sorted.
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

public class InsertionSort implements Sorter{
	private Rectangle[] list;
	private int length;
	private String titleText;
	
	public InsertionSort() {
		list = new Rectangle[0];
		length = 0;
		titleText = "Insertion Sort";
	}
	
	public InsertionSort(Rectangle[] rectList) {
		length = rectList.length;
		list = rectList;
		titleText = "Insertion Sort";
	}
	
	public void mainSort() {
		sort(0,1);
	}
	
	public void sort(int start,int end) {
		if(end < length) {
			double insertHeight = list[end].getHeight();
			double insertY = list[end].getY();
			int i = end;
			while (i > start && insertHeight < list[i-1].getHeight()) {
				ScreensaverRunner.incrementComps();
				list[i-1].setFill(Color.web("#215B95"));
				swap(i,i-1);
				try {
					Thread.sleep(50);
					list[i-1].setFill(Color.web("#F0F0F0"));
					i--;
				} catch (Exception e) {
					
				}
			}
			list[i].setHeight(insertHeight);
			list[i].setY(insertY);
			
			sort(0,end+1);
		}
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
	
	public String getTitleText() {
		return titleText;
	}
	

}