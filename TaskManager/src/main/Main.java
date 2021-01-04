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
//		System.out.println("INITIAL POSITION----------------------------------------------------------");
//		File file = new File("TaskManager-lists/tasks.txt");
//		tasks = readTasks(file);	
//		printTasks();
//		System.out.println("ADDED TASK----------------------------------------------------------");
//		Task newTask1 = new Task("Play", "Play Minecraft", 5);
//		addTask(newTask1);
//		printTasks();
//		
//		
		setUpWindow();
	}
	
	private static void setUpWindow() {
		window = new MyFrame("Orlando's Task Manager");
		window.setLayout(new BorderLayout());
		chosenTask = new ChosenTask();
		taskList = new TaskList();
		chooseMenu = new ChooseMenu();
		window.add(chosenTask, BorderLayout.NORTH);
		window.add(taskList, BorderLayout.CENTER);
		window.add(chooseMenu, BorderLayout.SOUTH);
		window.revalidate();
	}
	
	public static Queue<Task> readTasks(File file) throws FileNotFoundException{
		filePath = file.getPath();
		Queue<Task> fileTasks = new PriorityQueue<Task>();
		Scanner fileReader = new Scanner(file);
		while (fileReader.hasNextLine()) {
			Task currTask = new Task();
			currTask.name = fileReader.nextLine();
			currTask.description = fileReader.nextLine();
			currTask.priority = Integer.parseInt(fileReader.nextLine());	
			fileTasks.offer(currTask);
		}
		fileReader.close();
		return fileTasks;
	}
	
	public static void deleteTask(String taskName) throws IOException {
		for (Task t : tasks) {
			if (t.name.equals(taskName)) {
				tasks.remove(t);
				break;
			}
		}
		writeTasks();
		taskList = new TaskList();
		chooseTask();
	}
	
	public static void addTask(Task task) throws IOException {
		tasks.add(task);
		writeTasks();
		taskList = new TaskList();
		chooseTask();
	}
	
	public static void chooseTask() throws IOException {
		if (!tasks.isEmpty()) {
			chosenTask.setText(tasks.peek().toString());
			chooseMenu.delete.setEnabled(true);
			chooseMenu.pop.setEnabled(true);
		}
		else {
			chosenTask.setText("No tasks left!");
			chooseMenu.delete.setEnabled(false);
			chooseMenu.pop.setEnabled(false);
		}
		writeTasks();
		window.revalidate();
	}
	
	public static void printTasks() {
		for (Task task : tasks)
			System.out.println(task);
	}
	
	private static void writeTasks() throws IOException{
		wipeFile();
		FileWriter writer = new FileWriter(filePath);
		for (Task task : tasks) {
			writer.write(task.name + "\n");
			writer.write(task.description + "\n");
			writer.write(task.priority + "\n");
		}
		writer.close();
	}
	
	private static void wipeFile() throws IOException {
		File file = new File(filePath);
		if (file.delete())
			file.createNewFile();
	}

	public static void makeDark() {
		// TODO Auto-generated method stub
		window.revalidate();
	}

	public static void makeLight() {
		// TODO Auto-generated method stub
		window.revalidate();
	}

}
