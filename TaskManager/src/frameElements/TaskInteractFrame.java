package frameElements;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dataStructures.Task;
import main.Main;

public class TaskInteractFrame extends PopupFrame implements ActionListener{
	String iconPath;
	JTextField name, description, priority;
	JButton submit, remove;

	public TaskInteractFrame(String title) {
		super(title);
		if (title.equals("Add Task")) {
			iconPath = "images/add.png";
			makeAddInput();
		}
		if (title.equals("Delete Task")) {
			iconPath = "images/delete.png";
			makeDeleteInput();
		}
		ImageIcon image = new ImageIcon(iconPath);
		this.setIconImage(image.getImage());
		this.setVisible(true);
	}

	private void makeAddInput() {
		this.setLayout(new GridLayout(4, 2));

		submit = new JButton("Submit");
		submit.addActionListener(this);
		
		name = new JTextField();
		description = new JTextField();
		priority = new JTextField();
		
		name.setPreferredSize(new Dimension(250, 40));
		description.setPreferredSize(new Dimension(250, 40));
		priority.setPreferredSize(new Dimension(250, 40));
		
		JLabel nameIndicator = new JLabel("Name: ");
		// nameIndicator.setHorizontalTextPosition(JLabel.CENTER);
		// nameIndicator.setVerticalTextPosition(JLabel.CENTER);
		
		JLabel descIndicator = new JLabel("Description: ");
		// descIndicator.setHorizontalTextPosition(JLabel.CENTER);
		// descIndicator.setVerticalTextPosition(JLabel.CENTER);
		
		JLabel priIndicator = new JLabel("Priority: ");
		// priIndicator.setHorizontalTextPosition(JLabel.CENTER);
		// priIndicator.setVerticalTextPosition(JLabel.CENTER);

		this.add(nameIndicator);
		this.add(name);
		this.add(descIndicator);
		this.add(description);
		this.add(priIndicator);
		this.add(priority);
		this.add(submit);
		this.pack();
	}
	
	private void makeDeleteInput() {
		this.setLayout(new GridLayout(2, 2));
		
		remove = new JButton("Remove");
		remove.addActionListener(this);
		
		name = new JTextField();
		name.setPreferredSize(new Dimension(250, 40));
		
		JLabel nameIndicator = new JLabel("Name: ");
		nameIndicator.setHorizontalTextPosition(JLabel.CENTER);
		nameIndicator.setVerticalTextPosition(JLabel.CENTER);
		
		this.add(nameIndicator);
		this.add(name);
		this.add(remove);
		this.pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			String taskName = name.getText();
			String taskDesc = description.getText();
			int taskPriority = Integer.valueOf(priority.getText());
			try {
				Main.addTask(new Task(taskName, taskDesc, taskPriority));
				Main.chooseTask();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			this.dispose();
		}
		if (e.getSource() == remove) {
			String taskName = name.getText();
			try {
				Main.deleteTask(taskName);
				Main.chooseTask();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			this.dispose();
		}
	}
}
