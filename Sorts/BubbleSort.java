/*
	Bubble sort - if one item is larger than the one in front, swap them. 
	Go through the list like this once, then repeat, ignoring the last item, 
	since the last one will be the largest. Repeat until sorted. 
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


public class BubbleSort implements Sorter  {
	private Rectangle[] list;
	private int length;
	private String titleText;
	
	public BubbleSort() {
		list = new Rectangle[0];
		length = 0;
		titleText = "Bubble Sort";
	}
	
	public BubbleSort(Rectangle[] rectList) {
		length = rectList.length;
		list = rectList;
		titleText = "Bubble Sort";
	}
	
	public void mainSort() {
		sort(0,length-1);
	}
	
	public void sort(int start, int end) {
		if(start < end) {
			boolean swapped = false;
			for(int i = start; i < end; i++)
			{
				list[i].setFill(Color.web("#215B95"));
				list[i+1].setFill(Color.web("#215B95"));
				ScreensaverRunner.incrementComps();
				
				if(list[i].getHeight() > list[i+1].getHeight())
				{
					swap(i,i+1);
					swapped = true; 
				}
				try {
					Thread.sleep(50);
					list[i].setFill(Color.web("#F0F0F0"));
					list[i+1].setFill(Color.web("#F0F0F0"));
				} catch (Exception e) {
					
				}
			}
			end--;
			if(swapped)
				sort(0,end);
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