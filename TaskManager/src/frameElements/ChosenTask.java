package frameElements;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import dataStructures.Task;

public class ChosenTask extends JLabel{ 
	public static Border border = BorderFactory.createTitledBorder(MyFrame.baseBorder, "Most Urgent Task");

	public ChosenTask() {
		this.setText("No task currently selected");
		this.setHorizontalTextPosition(JLabel.CENTER);
		this.setVerticalTextPosition(JLabel.CENTER);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.TOP);
		this.setBackground(Color.black);
		this.setPreferredSize(new Dimension(MyFrame.SQUARE_PIX_DIM, 50));
		this.setBorder(border);
		
	}
	public ChosenTask(Task task) {
		this.setText(task.toString());
		this.setHorizontalTextPosition(JLabel.CENTER);
		this.setVerticalTextPosition(JLabel.CENTER);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.TOP);
		this.setBackground(Color.black);
		this.setPreferredSize(new Dimension(MyFrame.SQUARE_PIX_DIM, 50));
		this.setBorder(border);
	}
}
