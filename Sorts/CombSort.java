/*
	Comb sort - similar to bubble sort, but instead of swapping adjacent elements, 
	elements with different gap sizes are swapped, starting from the length of the list
	down to 1. 
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


public class CombSort implements Sorter  {
	private Rectangle[] list;
	private int length;
	private String titleText;
	
	public CombSort() {
		list = new Rectangle[0];
		length = 0;
		titleText = "Comb Sort";
	}
	
	public CombSort(Rectangle[] rectList) {
		length = rectList.length;
		list = rectList;
		titleText = "Comb Sort";
	}
	
	public void mainSort() {
		sort(0,length,length-1);
	}
	
	public void sort(int start, int end, int gap) {
		int i = 0;
		while(i + gap < end) {
			list[i].setFill(Color.web("#215B95"));
			list[i+gap].setFill(Color.web("#215B95"));
			ScreensaverRunner.incrementComps();
			
			if(list[i].getHeight() > list[i+gap].getHeight())
				swap(i,i+gap);

			try {
				Thread.sleep(50);
				list[i].setFill(Color.web("#F0F0F0"));
				list[i+gap].setFill(Color.web("#F0F0F0"));
			} catch (Exception e) {
				
			}
			i++;
		}
		if(gap > 1)
			sort(0,end,gap-1);
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