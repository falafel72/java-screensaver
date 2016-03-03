/* 
	Quick sort - take one element as a "pivot", organise the list such that
	all items less than the pivot are on the left and all items greater than
	the pivot are on the right. Recursively sort two sublists on the left and
	right of the pivot. 
*/ 

package Screensaver.Sorts;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Group;
import javafx.application.Platform; 
import java.lang.Runnable;
import Screensaver.*;


public class QuickSort implements Sorter {
	private Rectangle[] list;
	private int length;
	private String titleText;
	
	public QuickSort() {
		list = new Rectangle[0];
		length = 0;
		titleText = "Quick Sort";
		//comparisons = 0;
	}
	
	public QuickSort(Rectangle[] rectList) {
		length = rectList.length;
		list = rectList;
		titleText = "Quick Sort";
		//comparisons = 0;
	}
	
	
	public void mainSort() {
		sort(0,length-1);
	}
	
	public void sort(int low,int high) {
		int i = low;
		int j = high; 
		
		int pivotIndex = getRandom(low,high);
		double pivotHeight = list[pivotIndex].getHeight();
		
		list[pivotIndex].setFill(Color.web("#215B95"));
		
		while (i <= j) {
			while(list[i].getHeight() < pivotHeight) {
				Rectangle temp = list[i];
				temp.setFill(Color.web("#307174"));
				i++;
				ScreensaverRunner.incrementComps();

				try {
					Thread.sleep(50);
					temp.setFill(Color.web("#F0F0F0"));
				} catch (InterruptedException e) {
					
				}
			}
			while(list[j].getHeight() > pivotHeight) {
				Rectangle temp = list[j];
				temp.setFill(Color.web("#307174"));
				j--;
				ScreensaverRunner.incrementComps();
				
				try {
					Thread.sleep(50);
					temp.setFill(Color.web("#F0F0F0"));
				} catch (InterruptedException e) {
					
				}
			}
			if(i <= j) {
				Rectangle temp1 = list[i];
				Rectangle temp2 = list[j];
				
				temp1.setFill(Color.web("#307174"));
				temp2.setFill(Color.web("#307174"));

				
				
				swap(i,j);
				
				
				i++;
				j--;
				try {
					Thread.sleep(50);
					temp1.setFill(Color.web("#F0F0F0"));
					temp2.setFill(Color.web("#F0F0F0"));
				} catch (InterruptedException e) {
					
				}
			}
		}
		if(low < j)
			sort(low,j);
		if(high > i)
			sort(i,high);
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