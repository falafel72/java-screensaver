/* 
	Cycle sort - checks the position that each element should go in by checking
	the number of elements in the list that are less than it. If no elements ahead
	of it are smaller, then the list behind it is sorted, and it moves onto the next
	element. 
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
import Screensaver.*;

public class CycleSort implements Sorter {
	private Rectangle[] list;
	private int length;
	private String titleText;
	
	public CycleSort() {
		list = new Rectangle[0];
		length = 0;
		titleText = "Cycle Sort";
	}
	
	public CycleSort(Rectangle[] rectList) {
		length = rectList.length;
		list = rectList;
		titleText = "Cycle Sort";
	}
	
	public void mainSort() {
		sort();
	}
	
	public void sort() {
		//for each index in the list
		for(int cycleStart = 0; cycleStart < length-1; cycleStart++) {
			if(cycleStart > 0)
				list[cycleStart-1].setFill(Color.web("#F0F0F0")); 
			list[cycleStart].setFill(Color.web("#307174"));
			int pos = cycleStart;
			
			//goes through the list checking for rectangles shorter than list[cycleStart]
			for(int i = cycleStart + 1; i < length; i++) {
				list[i].setFill(Color.web("#215B95"));
				ScreensaverRunner.incrementComps();
				if(list[i].getHeight() < list[cycleStart].getHeight()) {
					pos++;
					list[pos].setFill(Color.web("#307174"));
					if(pos > 0)
						if(pos-1 != cycleStart)
							list[pos-1].setFill(Color.web("#F0F0F0"));
						else 
							list[pos-1].setFill(Color.web("#307174"));
					
				}
				try{
					Thread.sleep(25);
					if(i != cycleStart)
						list[i].setFill(Color.web("#F0F0F0"));
					else 
						list[i].setFill(Color.web("#307174"));
				} catch (Exception e) {
					
				}
			}
			
			if(pos == cycleStart) //if no heights ahead of list[pos] are greater, list[pos] is in the correct place
				continue;
				
			while(list[pos].getHeight() == list[cycleStart].getHeight()) { //accomodates for duplicates
				ScreensaverRunner.incrementComps();
				list[pos].setFill(Color.web("#215B95"));
				
				try{
					Thread.sleep(25);
					if(pos != cycleStart)
						list[pos].setFill(Color.web("#F0F0F0"));
					else 
						list[pos].setFill(Color.web("#307174"));
					
				} catch (Exception e) {
					
				}
				pos++;
			}
			swap(cycleStart,pos);
			
			while(pos != cycleStart) { //rotates the rest of the cycle
			
				pos = cycleStart;
				for(int i = cycleStart + 1; i < length; i++) {
					ScreensaverRunner.incrementComps();
					list[i].setFill(Color.web("#215B95"));
					if(list[i].getHeight() < list[cycleStart].getHeight()) {
						pos++;
						list[pos].setFill(Color.web("#307174"));
						if(pos > 0)
							if(pos-1 != cycleStart)
								list[pos-1].setFill(Color.web("#F0F0F0"));
							else 
								list[pos-1].setFill(Color.web("#307174"));
						
						
					}
					try{
						Thread.sleep(25);
						if(i != cycleStart)
							list[i].setFill(Color.web("#F0F0F0"));
						else 
							list[i].setFill(Color.web("#307174"));
					} catch (Exception e) {
						
					}
				}
				
				while(list[pos].getHeight() == list[cycleStart].getHeight() && pos != cycleStart) { //accomodates for duplicates
					ScreensaverRunner.incrementComps();
					
					list[pos].setFill(Color.web("#215B95"));
					try{
						Thread.sleep(25);
						if(pos != cycleStart)
							list[pos].setFill(Color.web("#F0F0F0"));
						else 
							list[pos].setFill(Color.web("#307174"));
						
					} catch (Exception e) {
						
					}
					pos++;
				}
				swap(cycleStart,pos);
			}
			
		}
		for (Rectangle i : list) 
			i.setFill(Color.web("#F0F0F0"));
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