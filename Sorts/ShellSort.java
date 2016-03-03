/* 
	Shell sort - similar to insertion sort, but swaps elements with a certain
	gap between them rather than adjacent elements. Speed varies with different 
	gap sizes, can sometimes run faster than Quicksort and Mergesort with small 
	lists. 
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

public class ShellSort implements Sorter {
	
	private Rectangle[] list;
	private int length;
	private String titleText;
	private int[] gapList = {23, 10, 4, 1};

	
	public ShellSort() {
		list = new Rectangle[0];
		length = 0;
		titleText = "Shell Sort";
		gapList = new int[0];
	}
	
	public ShellSort(Rectangle[] rectList) {
		length = rectList.length;
		list = rectList;
		titleText = "Shell Sort";
	}
	
	public void mainSort() { 
		for(int i : gapList) {
			sort(i);
		}
	}
	
	public void sort(int gap) {
		int index = gap;
		while(index < length) {
			int gapNumber = 1;
			double tempHeight = list[index].getHeight();
			while(index - gapNumber * gap >= 0 && list[index-gapNumber*gap].getHeight() > tempHeight) {
				ScreensaverRunner.incrementComps();
				list[index - ((gapNumber-1)*gap)].setFill(Color.web("#215B95"));
				list[index - (gapNumber*gap)].setFill(Color.web("#215B95"));
				if(list[index - ((gapNumber-1)*gap)].getHeight() < list[index - (gapNumber*gap)].getHeight()) {
					
					swap(index - ((gapNumber-1)*gap),index - (gapNumber*gap));
					
				}
				try {
					Thread.sleep(50);
					list[index - ((gapNumber-1)*gap)].setFill(Color.web("#F0F0F0"));
					list[index - (gapNumber*gap)].setFill(Color.web("#F0F0F0"));
				} catch (Exception e) {
					
				}
				gapNumber++;
			}
			index++;
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