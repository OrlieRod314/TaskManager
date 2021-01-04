package frameElements;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;

public abstract class PopupFrame extends JFrame{	
	private static int SQUARE_PIX_DIM = 500;
	private static Color DEF_BORDER_COL = Color.black;
	private static Border baseBorder = BorderFactory.createLineBorder(DEF_BORDER_COL);

	public PopupFrame(String title) {
		this.setSize(SQUARE_PIX_DIM, SQUARE_PIX_DIM);
		this.setTitle(title);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}	
}
