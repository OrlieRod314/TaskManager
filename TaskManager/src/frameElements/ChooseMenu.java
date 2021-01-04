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

public class ChooseMenu extends JPanel implements ActionListener{
	
	public Border border = BorderFactory.createTitledBorder(MyFrame.baseBorder, "Interact");
	public JButton add, delete, pop;

	public ChooseMenu() {
		this.setLayout(new GridLayout(3, 1, 10, 10));
		this.setPreferredSize(new Dimension(MyFrame.SQUARE_PIX_DIM, 400));
		this.setBorder(border);
		
		add = new JButton("Add Task");
		delete = new JButton("Delete Task");
		pop = new JButton("Delete Top Task");
		
		add.addActionListener(this);
		delete.addActionListener(this);
		pop.addActionListener(this);
		
		add.setEnabled(false);
		delete.setEnabled(false);
		pop.setEnabled(false);
		
		this.add(add);
		this.add(delete);
		this.add(pop);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == add) 
			new TaskInteractFrame("Add Task");
		if (e.getSource() == delete)
			new TaskInteractFrame("Delete Task");
		if (e.getSource() == pop) {
			Main.tasks.poll();
			try {
				Main.chooseTask();
				Main.taskList = new TaskList();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
