/* 
	Screensaver runner - generates differently sized rectangles and puts them in order
	using different algorithms. Repeat indefinitely. 
*/ 

package Screensaver;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import java.util.Random;
import javafx.geometry.Rectangle2D;
import javafx.scene.text.*;
import java.util.*;
import java.lang.Runnable;

import java.util.Timer;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import Screensaver.Sorts.*;

public class ScreensaverRunner extends Application {
	
	//stores comparisons, swaps, and current algorithm name
	private static int comparisons; 
	private static int swapNum;
	private static String algorName;
	
	//green base rectangle
	private Rectangle base;
	
	//list of rectangles being sorted
	private Rectangle[] sortRects;
	
	//text fields for display
	private Text algor;
	private Text comps;
	private Text swaps;
	
	//different algorithms use different classes in order to store them in an array
	private BogoSort bogo;
	private QuickSort quick;
	private BubbleSort bubble;
	private GnomeSort gnome;
	private SelectionSort select;
	private CocktailSort cocktail;
	private MergeSort merge;
	private HeapSort heap;
	private RadixSort radix;
	private CombSort comb;
	private InsertionSort insert;
	private ShellSort shell;
	private CycleSort cycle;
	
	//the array of algorithm objects
	private Sorter[] sortList = new Sorter[13];
	
	//current algorithm being used
	private Sorter currentAlgor;
	
	private int sequenceIndex;
	int[] sequence = {0,1,2,3,4,5,6,7,8,9,10,11,12};


	public static void main(String[] args) {
		launch(args);
	}
	
	//run starts here in Java Application
	public void start(Stage primaryStage) {
		//initialise main group for all nodes
		Group root = new Group();
		
		//get window dimensions
		Rectangle2D screenDimen = Screen.getPrimary().getVisualBounds();
		
		//initialise variables
		comparisons = 0;
		swapNum	= 0;
	
		//initialise window, set root as main display	
		primaryStage.setTitle("Test");
		Scene scene = new Scene(root,screenDimen.getWidth(),screenDimen.getHeight(),Color.web("#15161E"));
		primaryStage.setScene(scene);
		
		//base rectangle
		base = new Rectangle(77, 700, 1123, 40);
		base.setFill(Color.web("#71D186"));
		root.getChildren().add(base);

		//initialise group for sorting rectangles
	 	Group rectGroup = new Group();
		root.getChildren().add(rectGroup);
		
		
		//initialise group for text
		Group text = new Group();
		algor = new Text("Current Algorithm: ");
		algor.setFont(new Font("Avenir Book",24));
		algor.setX(10);
		algor.setY(0+algor.getLayoutBounds().getHeight());
		algor.setFill(Color.web("#F0F0F0"));
		
		comps = new Text("Comparisons: " + comparisons);
		comps.setFont(new Font("Avenir Book",24));
		comps.setX(10);
		comps.setY(algor.getY()+comps.getLayoutBounds().getHeight());
		comps.setFill(Color.web("#F0F0F0"));
		
		swaps = new Text("Swaps: " + swapNum);
		swaps.setFont(new Font("Avenir Book",24));
		swaps.setX(10);
		swaps.setY(comps.getY()+swaps.getLayoutBounds().getHeight());
		swaps.setFill(Color.web("#F0F0F0"));
		
		text.getChildren().addAll(algor,comps,swaps);
		root.getChildren().add(text);
		
		//timers for task
		Timer mainTimer = new Timer();
		Timer textTimer = new Timer();
		
		sequenceIndex = 0;
		shuffleNums(sequence);
		//task for sorting
		TimerTask iteration = new TimerTask(){
			@Override public void run() {
				sortRects = new Rectangle[45];
				comparisons = 0;
				swapNum = 0;
				
				//adds rectangles to rectGroup
				for(int i = 0; i < sortRects.length; i++) {
					int rectHeight = getRandom(50,500);
					Rectangle rect = new Rectangle(77 + 25*i,700 - rectHeight - 2,23,rectHeight);
					rect.setFill(Color.web("#15161E"));
					Platform.runLater(new Runnable(){
						@Override public void run() {
							try {
								rectGroup.getChildren().add(rect);
							} catch (Exception e) {
								
							}
							
						}
					});
					
					sortRects[i] = rect;
				}
				
				//initialises list of algorithms
				initSortList();
				
				
				//goes through shuffled sequence, current algorithm is that index in sortList
				currentAlgor = sortList[sequence[sequenceIndex]];
				sequenceIndex++; 
				
				//if sequenceIndex > 12, shuffle the list again and reset sequenceIndex
				if(sequenceIndex >= sequence.length) {
					shuffleNums(sequence);
					sequenceIndex = 0;
				}
				
				//sets algorithm name
				algorName = currentAlgor.getTitleText();
				
				//colors the rectangles white
				for(Rectangle i : sortRects) {
					i.setFill(Color.web("#F0F0F0"));
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						System.out.println(e);
					}
				}
				
				//sorts them
				currentAlgor.mainSort();
				
				//fades out
				fadeOut();
			}
			
			//fade out animation
			public void fadeOut() {
				//turns all the rectangles gray
				for(Rectangle i : sortRects) {
					i.setFill(Color.web("#727272"));
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						
					}
				}
				//pause for 1 second
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					
				}
				//turns all the rectangles to the same color as the background
				for(Rectangle i : sortRects) {
					i.setFill(Color.web("#15161E"));
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						
					}
				}
				
				//clears rectGroup
				try {
					Platform.runLater(new Runnable() { //anything editing groups needs to be run on the main application thread
						@Override public void run() {
							try {
								rectGroup.getChildren().clear();
							} catch (Exception e) {
								System.out.println(e);
							}
							
						}
					});
				} catch (Exception e) {
					System.out.println(e);
				}

			}
		};
		
		//updates text
		TimerTask updateText = new TimerTask(){
			@Override public void run() {
				Platform.runLater(new Runnable() {
					@Override public void run() {
						algor.setText("Current Algorithm: " + algorName);
						comps.setText("Comparisions: " + comparisons);
						swaps.setText("Swaps: " + swapNum);	
					}
				});
				
			}
		};
		
		EventHandler<KeyEvent> keyEventHandler = 
				new EventHandler<KeyEvent>() {
					public void handle(KeyEvent keyEvent) {
						System.exit(0);
					}
				}; 
		scene.setOnKeyPressed(keyEventHandler);
		
		//start timers
		mainTimer.schedule(iteration, 0, 50);
		textTimer.schedule(updateText, 0, 20);
		
		//show the window
		primaryStage.setFullScreen(true);
		primaryStage.show();
	}
	
	//intitialises the list of algorithms
	public void initSortList() {
		bogo = new BogoSort(sortRects);
		sortList[0] = bogo;
		
		quick = new QuickSort(sortRects);
		sortList[1] = quick;
		
		bubble = new BubbleSort(sortRects);
		sortList[2] = bubble;
		
		gnome = new GnomeSort(sortRects);
		sortList[3] = gnome;
		
		select = new SelectionSort(sortRects);
		sortList[4] = select;
		
		cocktail = new CocktailSort(sortRects);
		sortList[5] = cocktail;
		
		merge = new MergeSort(sortRects);
		sortList[6] = merge;
		
		heap = new HeapSort(sortRects);
		sortList[7] = heap;
		
		radix = new RadixSort(sortRects);
		sortList[8] = radix;
		
		comb = new CombSort(sortRects);
		sortList[9] = comb;
		
		insert = new InsertionSort(sortRects);
		sortList[10] = insert;
		
		shell = new ShellSort(sortRects);
		sortList[11] = shell;
		
		cycle = new CycleSort(sortRects);
		sortList[12] = cycle;

	}
	
	
	public int getRandom(int min, int max) { //returns integer from 1-max, inclusive
		Random rand = new Random();
		int randNum = rand.nextInt(max - min + 1) + min;
		return randNum;
	}
	
	public void swapNums(int[] list, int indexA, int indexB) { //swaps two numbers in a given list with indexA and indexB
		int temp = list[indexA];
		list[indexA] = list[indexB];
		list[indexB] = temp;
	}

	//increments comparisons and swaps
	public static void incrementComps() {
		comparisons++; 
	}
	
	public static void incrementSwaps() {
		swapNum++; 
	}
	
	//shuffles given list
	public void shuffleNums(int[] list) {
		for(int i = list.length - 1; i > 0; i--) {
			int randIndex = getRandom(0,i);
			swapNums(list, i, randIndex);
		}
	}
	
	//GETTERS
	public Rectangle[] getSortRects() {
		return sortRects;
	}
	
	public static int getComps() {
		return comparisons;
	}
	
	public static int getSwaps() {
		return swapNum;
	}
	
	//SETTERS
	public static void setComps(int c) {
		comparisons = c;
	}
	
	public static void setSwaps(int s) {
		swapNum = s;
	}

	
}