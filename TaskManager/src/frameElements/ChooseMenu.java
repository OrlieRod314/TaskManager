package frameElements;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import main.Main;

/**
 * Class representing the bottom menu of the main window
 * @author Orlando Rodriguez
 *
 */
public class ChooseMenu extends JPanel implements ActionListener{
	
	public Border border = BorderFactory.createTitledBorder(MyFrame.baseBorder, "Interact");
	public JButton add, delete, pop;

	public ChooseMenu() {
		// Lays out panel
		this.setLayout(new GridLayout(3, 1, 10, 10));
		this.setPreferredSize(new Dimension(MyFrame.SQUARE_PIX_DIM, 400));
		this.setBorder(border);
		
		// Creates buttons
		add = new JButton("Add Task");
		delete = new JButton("Delete Task");
		pop = new JButton("Delete Top Task");
		
		// Adds button functionality
		add.addActionListener(this);
		delete.addActionListener(this);
		pop.addActionListener(this);
		
		// Disables them by default
		add.setEnabled(false);
		delete.setEnabled(false);
		pop.setEnabled(false);
		
		// Adds buttons to panel
		this.add(add);
		this.add(delete);
		this.add(pop);
	}

	@Override
	/**
	 * Event handling for panel buttons
	 */
	public void actionPerformed(ActionEvent e) {
		// Adding task
		if (e.getSource() == add) 
			new TaskInteractFrame("Add Task");
		// Removing task
		if (e.getSource() == delete)
			new TaskInteractFrame("Delete Task");
		// Removing highest task
		if (e.getSource() == pop) {
			Main.tasks.poll();
			try {
				Main.chooseTask();
				Main.taskList = new TaskList();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
