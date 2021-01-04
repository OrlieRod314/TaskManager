package frameElements;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.Border;

import main.Main;

public class MyFrame extends JFrame implements ActionListener{
	public static int SQUARE_PIX_DIM = 1000;
	@SuppressWarnings("unused")
	private static Color DEF_BACKGROUND_COL = new Color(0xFFFFFF);
	private static Color DEF_BORDER_COL = Color.black;
	public static String iconPath = "images/hl.png";
	public static boolean isLight = true;
	
	public JMenuBar menu;
	public JMenu fileMenu;
	public JMenuItem openFile;
	public JMenuItem exit;
	public JMenu otherMenu;
	public JMenuItem help;
	public JMenuItem dark;
	
	public static Border baseBorder = BorderFactory.createLineBorder(DEF_BORDER_COL);
	
	public MyFrame(String title) {
		this.setSize(SQUARE_PIX_DIM, SQUARE_PIX_DIM);
		this.setTitle(title);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon image = new ImageIcon(iconPath);
		this.setIconImage(image.getImage());
				
		menu = new JMenuBar();
		
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		
		openFile = new JMenuItem("Open");
		openFile.addActionListener(this);
		openFile.setMnemonic(KeyEvent.VK_O); // O for open
		
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		exit.setMnemonic(KeyEvent.VK_E);
		
		fileMenu.add(openFile);
		fileMenu.add(exit);

		otherMenu = new JMenu("Other");
		otherMenu.setMnemonic(KeyEvent.VK_O);

		help = new JMenuItem("Help");
		help.addActionListener(this);
		help.setMnemonic(KeyEvent.VK_H);
		
		dark = new JMenuItem("Dark Mode");
		dark.addActionListener(this);
		dark.setMnemonic(KeyEvent.VK_D);
		
		otherMenu.add(help);
		otherMenu.add(dark);
		
		menu.add(fileMenu);
		menu.add(otherMenu);
		this.setJMenuBar(menu);
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == openFile)
			try {
				openFile();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if (e.getSource() == exit)
			System.exit(0);
		if (e.getSource() == help)
			getHelp();
		if (e.getSource() == dark) {
			invertCol();
			isLight ^= true;
		}
	}
	
	private void openFile() throws IOException {
		 JFileChooser fileChooser = new JFileChooser();
		 fileChooser.setCurrentDirectory(new File("./TaskManager-lists/"));
		 int response = fileChooser.showOpenDialog(null);
		 if (response == JFileChooser.APPROVE_OPTION) {
			 File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			 Main.tasks = Main.readTasks(file);
			 System.out.println(Main.tasks);
			 Main.taskList = new TaskList();
			 Main.chooseTask();
			 Main.chooseMenu.add.setEnabled(true);
			 
			 if (!Main.tasks.isEmpty()) {
			 Main.chooseMenu.delete.setEnabled(true);
			 Main.chooseMenu.pop.setEnabled(true);
			 }
		 }
	}
	
	private void getHelp() {
		new HelpFrame("Help");
	}
	
	private void invertCol() {
		if (isLight) {
			dark.setText("Light Mode");
			dark.setMnemonic(KeyEvent.VK_L);
			Main.makeDark();
		}
		if (!isLight) {
			dark.setText("Dark Mode");
			dark.setMnemonic(KeyEvent.VK_D);
			Main.makeLight();
		}
	}
	
	
}
