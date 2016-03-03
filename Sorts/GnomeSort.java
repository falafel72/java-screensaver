/* 
	Gnome sort - similar to insertion sort, though it crawls through the entire list continuously
	rather than jumping back to the next item.
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

public class GnomeSort implements Sorter{
	private Rectangle[] list;
	private int length;
	private String titleText;
	
	public GnomeSort() {
		list = new Rectangle[0];
		length = 0;
		titleText = "Gnome Sort";
	}
	
	public GnomeSort(Rectangle[] rectList) {
		length = rectList.length;
		list = rectList;
		titleText = "Gnome Sort";
	}
	
	public void mainSort() {
		sort();
	}
	
	public void sort() {
		int i = 1;
		while (i < length) {
			Rectangle temp1 = list[i];
			Rectangle temp2 = list[i-1];
			if(temp1.getHeight() >= temp2.getHeight()) {
				temp1.setFill(Color.web("#215B95"));
				temp2.setFill(Color.web("#215B95"));
				i++;
				try {
					Thread.sleep(50);
				} catch (Exception e) {
			
				}
			}
			else {
				temp1.setFill(Color.web("#215B95"));
				temp2.setFill(Color.web("#215B95"));
				try {
					Thread.sleep(50);
					swap(i,i-1);

					//
				} catch (InterruptedException e) {
					
				}
				if(i == 1)
					i++;
				else 
					i--;
			
			
			}
			ScreensaverRunner.incrementComps();
			temp1.setFill(Color.web("#F0F0F0"));
			temp2.setFill(Color.web("#F0F0F0"));

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