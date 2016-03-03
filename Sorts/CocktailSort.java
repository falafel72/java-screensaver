/* 
	Cocktail sort - similar to bubble sort, but instead of just checking from left to right, 
	it sorts from both ends, moving both up and down the list, taking the smallest and largest items
	to their respective positions.
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

public class CocktailSort implements Sorter {
	private Rectangle[] list;
	private int length;
	private String titleText;
	
	public CocktailSort() {
		list = new Rectangle[0];
		length = 0;
		titleText = "Cocktail Sort";
		//comparisons = 0;
	}
	
	public CocktailSort(Rectangle[] rectList) {
		length = rectList.length;
		list = rectList;
		titleText = "Cocktail Sort";
		//comparisons = 0;
	}
	
	public void mainSort() {
		sort(0,length-1);
	}
	
	public void sort(int start, int end) {
		boolean swapped = false;
		if(start < end) {
			//System.out.println("" + start + " " + end);
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
				sort(end,start);
		}
		else if (start > end) { 
			//System.out.println("" + start + " " + end);
			for(int i = start; i > end; i--)
			{
				list[i].setFill(Color.web("#215B95"));
				list[i-1].setFill(Color.web("#215B95"));
				ScreensaverRunner.incrementComps();
				
				if(list[i].getHeight() < list[i-1].getHeight())
				{
					swap(i,i-1);
					swapped = true; 
				}
				try {
					Thread.sleep(50);
					list[i].setFill(Color.web("#F0F0F0"));
					list[i-1].setFill(Color.web("#F0F0F0"));
				} catch (Exception e) {
					
				}
			}
			end++;
			if(swapped)
				sort(end,start);
			
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