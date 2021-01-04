package main;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

import dataStructures.Task;
import frameElements.*;

/**
 * Main class for task manager program
 * @author Orlando Rodriguez
 * @date 1/3/2021
 *
 */
public class Main {
	
	public static Queue<Task> tasks;
	public static String filePath;
	
	public static MyFrame window = null;
	public static ChosenTask chosenTask = null;
	public static TaskList taskList = null;
	public static ChooseMenu chooseMenu = null;
	
	public static void main(String[] args) throws IOException{
		setUpWindow();
	}
	
	/**
	 * Sets up the window for the program
	 */
	private static void setUpWindow() {
		// Creates main JFrame
		window = new MyFrame("Orlando's Task Manager");
		window.setLayout(new BorderLayout());
		
		// Creates sections of main window
		chosenTask = new ChosenTask();
		taskList = new TaskList();
		chooseMenu = new ChooseMenu();
		
		// Adds elements into window
		window.add(chosenTask, BorderLayout.NORTH);
		window.add(taskList, BorderLayout.CENTER);
		window.add(chooseMenu, BorderLayout.SOUTH);
		window.revalidate();
	}
	
	/**
	 * Creates a queue of tasks using the data file
	 * 
	 * @param file
	 * @return Queue containing all tasks in file
	 * @throws FileNotFoundException
	 */
	public static Queue<Task> readTasks(File file) throws FileNotFoundException{
		filePath = file.getPath();
		Queue<Task> fileTasks = new PriorityQueue<Task>();
		Scanner fileReader = new Scanner(file);
		
		// Reads through whole file
		while (fileReader.hasNextLine()) {
			Task currTask = new Task();
			
			// Creates task to be added and adds it
			currTask.name = fileReader.nextLine();
			currTask.description = fileReader.nextLine();
			currTask.priority = Integer.parseInt(fileReader.nextLine());	
			fileTasks.offer(currTask);
		}
		fileReader.close();
		return fileTasks;
	}
	
	/**
	 * Deletes a task with the given name from the Queue and removes it from the data file
	 * @param taskName
	 * @throws IOException
	 */
	public static void deleteTask(String taskName) throws IOException {
		// Finds task and removes it from PQ
		for (Task t : tasks) {
			if (t.name.equals(taskName)) {
				tasks.remove(t);
				break;
			}
		}
		
		// Updates data file and window
		writeTasks();
		taskList = new TaskList();
		chooseTask();
	}
	
	/**
	 * Adds a task into the Queue and to the data file
	 * @param task
	 * @throws IOException
	 */
	public static void addTask(Task task) throws IOException {
		// Update PQ
		tasks.add(task);
		
		// Update data file and window
		writeTasks();
		taskList = new TaskList();
		chooseTask();
	}
	
	/**
	 * Adds most important task into the top section
	 * @throws IOException
	 */
	public static void chooseTask() throws IOException {
		// Updates top section and changes available buttons
		if (!tasks.isEmpty()) {
			chosenTask.setText(tasks.peek().toString());
			chooseMenu.delete.setEnabled(true);
			chooseMenu.pop.setEnabled(true);
		}
		// If there are no tasks left
		else {
			chosenTask.setText("No tasks left!");
			chooseMenu.delete.setEnabled(false);
			chooseMenu.pop.setEnabled(false);
		}
		
		// Update data file and window
		writeTasks();
		window.revalidate();
	}
	
	/**
	 * Mostly for debugging purposes
	 * Prints all tasks in Queue
	 */
	public static void printTasks() {
		for (Task task : tasks)
			System.out.println(task);
	}
	
	/**
	 * Wipes data file and writes data
	 * @throws IOException
	 */
	private static void writeTasks() throws IOException{
		wipeFile();
		FileWriter writer = new FileWriter(filePath);
		
		// Appends each task to data file
		for (Task task : tasks) {
			writer.write(task.name + "\n");
			writer.write(task.description + "\n");
			writer.write(task.priority + "\n");
		}
		writer.close();
	}
	
	/**
	 * Replaces data file with blank copy
	 * @throws IOException
	 */
	private static void wipeFile() throws IOException {
		File file = new File(filePath);
		if (file.delete())
			file.createNewFile();
	}

	public static void makeDark() {
		// TODO Finish feature
		window.revalidate();
	}

	public static void makeLight() {
		// TODO Finish feature
		window.revalidate();
	}

}
