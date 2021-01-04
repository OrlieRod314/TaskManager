package frameElements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

/**
 * Help window class
 * No functionality as of now
 * @author Orlando Rodriguez
 *
 */
public class HelpFrame extends PopupFrame implements ActionListener{
	String iconPath = "images/help.png";
	
	public HelpFrame(String title) {
		super(title);
		ImageIcon image = new ImageIcon(iconPath);
		this.setIconImage(image.getImage());
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Finish Help Window
		
	}

}
