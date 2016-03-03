/* 
	Selection sort - takes an item and swaps it with the item directly
	below it until there is an item smaller than it. Repeat for each successive item. 
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


public class SelectionSort implements Sorter {
	private Rectangle[] list;
	private int length;
	private String titleText;
	
	public SelectionSort() {
		list = new Rectangle[0];
		length = 0;
		titleText = "Selection Sort";
	}
	
	public SelectionSort(Rectangle[] rectList) {
		length = rectList.length;
		list = rectList;
		titleText = "Selection Sort";
	}
	
	public void mainSort() {
		sort(0,length);
	}
	
	public void sort(int start, int end) {
		if(start < end) {
			int minHeightIndex = start;
			list[start].setFill(Color.web("#307174"));
			for(int i = start; i < end; i++) {
				list[i].setFill(Color.web("#215B95"));
				ScreensaverRunner.incrementComps();
				
				if(list[i].getHeight() < list[minHeightIndex].getHeight()) {
					list[minHeightIndex].setFill(Color.web("#F0F0F0"));
					list[start].setFill(Color.web("#307174"));

					
					minHeightIndex = i;
					
				}
				try {
					Thread.sleep(50);
					if(minHeightIndex == i || start == i)
						list[i].setFill(Color.web("#307174"));
					else
						list[i].setFill(Color.web("#F0F0F0"));
				} catch (InterruptedException e) {
					
				}
				
			}
			swap(start,minHeightIndex);
			try {
				Thread.sleep(50);
				list[minHeightIndex].setFill(Color.web("#F0F0F0"));
				list[start].setFill(Color.web("#F0F0F0"));
			} catch (InterruptedException e) {
				
			}
			
			start++;
			sort(start,end);
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