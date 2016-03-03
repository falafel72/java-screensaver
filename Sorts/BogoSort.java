/*
	Bogo sort - shuffle list, then check if it is in order. 
	Usually will never sort in any managable amount of time, so it will
	terminate after 30 iterations. 
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


public class BogoSort implements Sorter  {
	
	private Rectangle[] list;
	private int length;
	private long comparisons;
	private long swaps; 
	private String titleText;
	
	//default constructor
	public BogoSort() {
		list = new Rectangle[0];
		length = 0;
		comparisons = 0;
		titleText = "Bogo Sort";
	}
	
	//constructor that takes a list of rectangles as a parameter.
	public BogoSort(Rectangle[] rectList) {
		length = rectList.length;
		list = rectList;
		comparisons = 0;
		titleText = "Bogo Sort";
	}
	
	//main sorting method - shuffle until sorted
	public void mainSort() {
		Boolean sorted = false;

		int counter = 0;
		while(!sorted && counter < 30) {
			shuffle();
			sorted = (inOrder()) ? true : false;
			counter++;
		}
	}
	
	//shuffles heights
	public void shuffle() {
		for(int i = list.length - 1; i > 0; i--) {
				
			for (Rectangle j : list) {
				j.setFill(Color.web("#F0F0F0"));
			}
			
			int randIndex = getRandom(0,i);
			
			list[i].setFill(Color.web("#307174"));
			list[randIndex].setFill(Color.web("#307174"));

			swap(i,randIndex);
			
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	//checks if heights are in order
	public boolean inOrder() {
		for(int i = 0; i < list.length - 1; i++) {
			for(Rectangle j : list) {
				j.setFill(Color.web("#F0F0F0"));
			}
			list[i].setFill(Color.web("#215B95"));
			list[i+1].setFill(Color.web("#215B95"));
			
			ScreensaverRunner.incrementComps();
			
			if(list[i].getHeight() > list[i+1].getHeight())
				return false;
				
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
			
		}
		return true; 
	}
	
	
	//swaps heights & y values of two rectangles, takes two indexes
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
	
	//returns integer from 1-max, inclusive
	public int getRandom(int min, int max) { 
		Random rand = new Random();
		int randNum = rand.nextInt(max - min + 1) + min;
		return randNum;
	}
	
	//gets the name of the algorithm
	public String getTitleText() {
		return titleText;
	}

}