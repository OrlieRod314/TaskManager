package frameElements;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import dataStructures.Task;
import main.Main;

public class TaskList extends JPanel{
	
	public Border border = BorderFactory.createTitledBorder(MyFrame.baseBorder, "Task List");
	private List<Task> listOfTasks = new ArrayList<Task>();
	private List<JLabel> labels = new ArrayList<JLabel>();
	
	public TaskList() {
		this.setLayout(new GridLayout(3, 1));
		this.setPreferredSize(new Dimension(MyFrame.SQUARE_PIX_DIM, 500));
		this.setBorder(border);
		
		if (Main.tasks != null) {
			for (Task t : Main.tasks)
				listOfTasks.add(t);
			Collections.sort(listOfTasks);
			for (Task t : listOfTasks) {
				JLabel taskLabel = new JLabel();
				taskLabel.setPreferredSize(new Dimension(MyFrame.SQUARE_PIX_DIM, 50));
				taskLabel.setText(t.toString());
				taskLabel.setHorizontalTextPosition(JLabel.CENTER);
				taskLabel.setVerticalTextPosition(JLabel.CENTER);
				taskLabel.setHorizontalAlignment(JLabel.CENTER);
				taskLabel.setVerticalAlignment(JLabel.TOP);
				labels.add(taskLabel);
			}
			for (JLabel jl : labels)
				this.add(jl);
		}
		Main.window.add(this);
	}
}
