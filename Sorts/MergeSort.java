/*
	Merge sort - recursively split the list up, then recombine the separated
	lists in sorted order. 
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


public class MergeSort implements Sorter{
	private Rectangle[] list;
	private int length;
	private String titleText;
	
	private double[] tempHeightList;
	private double[] tempYList;
	
	public MergeSort() {
		list = new Rectangle[0];
		tempHeightList = new double[0];
		tempYList = new double[length];
		length = 0;
		titleText = "Merge Sort";
		//comparisons = 0;
	}
	
	public MergeSort(Rectangle[] rectList) {
		length = rectList.length;
		tempHeightList = new double[length];
		tempYList = new double[length];
		list = rectList;
		titleText = "Merge Sort";
		//comparisons = 0;
	}
	
	
	public void mainSort() {
		sort(0,length-1);
	}
	
	public void sort(int start, int end) {
		if(start < end) {
			int middle = start + (end-start)/2;
			
			sort(start,middle);
			sort(middle+1,end);
			merge(start,middle,end);
		}
	}
	
	//recombines two sub lists: one from list[low:middle] and another from list[middle+1:high]
	public void merge(int low, int middle, int high) {
		for(int i = low; i <= high; i++) {
			tempHeightList[i] = list[i].getHeight();
			tempYList[i] = list[i].getY();
		}
		
		int i = low;
		int j = middle + 1;
		int k = low;
		
		while(i <= middle && j <= high) {
			if(tempHeightList[i] <= tempHeightList[j]) {
				list[k].setHeight(tempHeightList[i]);
				list[k].setY(tempYList[i]);
				list[k].setFill(Color.web("#215B95"));
				try {
					Thread.sleep(50);
					list[k].setFill(Color.web("#F0F0F0"));
					i++;
				} catch (InterruptedException e) {
					System.out.println(e);
				}
			}
			else {
				list[k].setHeight(tempHeightList[j]);
				list[k].setY(tempYList[j]);
				list[k].setFill(Color.web("#215B95"));
				try {
					Thread.sleep(50);
					list[k].setFill(Color.web("#F0F0F0"));
					j++;
				} catch (InterruptedException e) {
					System.out.println(e);
				}
			}
			ScreensaverRunner.incrementComps();
			k++;
		}
		
		while(i <= middle) {
			list[k].setHeight(tempHeightList[i]);
			list[k].setY(tempYList[i]);
			list[k].setFill(Color.web("#215B95"));
			
			try {
				Thread.sleep(50);
				list[k].setFill(Color.web("#F0F0F0"));
				i++;
				k++;
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
	
	public void swap(int indexA, int indexB) {
		
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