/* 
	Heap sort - puts the items of the list in a heap format (max heap),
	then takes the first item, which must be the largest one, and swap
	it with the last item currently in the list. 
	Reorganise everything before the last item into a heap, and repeat. 
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


public class HeapSort implements Sorter{
	private Rectangle[] list;
	private int length;
	private String titleText;
	
	public HeapSort() {
		length = 0;
		list = new Rectangle[0];
		titleText = "Heap Sort";
	}
	
	public HeapSort(Rectangle[] rectList) {
		length = rectList.length;
		list = rectList;
		titleText = "Heap Sort";
	}
	
	public void mainSort() {
		sort();
	}
	
	public void sort() {
		int end = length - 1; 
		heapify();
		
		while(end > 0) {
			swap(0,end);
			list[0].setFill(Color.web("#215B95"));
			list[end].setFill(Color.web("#215B95"));
			try {
				Thread.sleep(50);
				list[0].setFill(Color.web("#F0F0F0"));
				list[end].setFill(Color.web("#F0F0F0"));
			} catch (Exception e) {
				
			}
			end--;
			siftDown(0,end);
		}
	}
	
	//puts the list in a heap structure - every child is smaller than it's parent
	public void heapify() {
		int start = (length - 2)/2;
		
		while(start >= 0) {
			siftDown(start,length-1);
			start--;
			
			try{
				Thread.sleep(50);
			} catch (Exception e) {
				
			}
		}
	}
	
	//puts the list in a heap structure assuming that the two heaps rooted at the direct children of the root node are valid heaps
	public void siftDown(int start, int end) {
		int rootIndex = start; 
		
		while(rootIndex * 2 + 1 <= end) { //while the root of the heap has at least one child
			int childIndex = rootIndex * 2 + 1; 
			int swapIndex = rootIndex;
			
			Rectangle temp1 = list[rootIndex];
			Rectangle temp2 = list[swapIndex];
			Rectangle temp3 = list[swapIndex];
			
			if(list[swapIndex].getHeight() < list[childIndex].getHeight()) {
				ScreensaverRunner.incrementComps();
				swapIndex = childIndex;
				temp2 = list[swapIndex];
				temp2.setFill(Color.web("#215B95"));
			} 
			
			//if there is a right child node
			if(childIndex + 1 <= end && list[swapIndex].getHeight() < list[childIndex + 1].getHeight()) {
				ScreensaverRunner.incrementComps();
				swapIndex = childIndex + 1;
				temp3 = list[swapIndex];
				temp3.setFill(Color.web("#215B95"));
			}
			
			if(swapIndex == rootIndex)
				break;
			else {
				temp1.setFill(Color.web("#215B95"));
				swap(rootIndex,swapIndex);
				rootIndex = swapIndex;
			}
			
			
			try {
				Thread.sleep(50);
				temp1.setFill(Color.web("#F0F0F0"));
				temp2.setFill(Color.web("#F0F0F0"));
				temp3.setFill(Color.web("#F0F0F0"));
			} catch (Exception e) {
				System.out.println(e);
			}
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