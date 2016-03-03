/*
	Interface for sorting class. Has a mainSort method which the main runner uses to sort the list.
	Sorts by height, though y values need to be changed as well.
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


public interface Sorter {
	
	//All Sorters should have a list to sort, a string for the name, and a list length. 
	
	public void mainSort();
	
	public void swap(int indexA, int indexB);
	
	public String getTitleText();
}