import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame
{
	private static MainWindow wind = new MainWindow();
	
	private Board board;
	java.awt.GraphicsDevice gd;
	private MainWindow()
	{
		java.awt.GraphicsEnvironment ge = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
		//System.out.println(ge.getMaximumWindowBounds().height);
		 gd = ge.getDefaultScreenDevice();
		 System.out.println(gd.getDefaultConfiguration().getBounds().height);
		board = new Board();
		init();
		this.setIgnoreRepaint(true);
		//this.setUndecorated(true);
	}
	
	private void init()
	{
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing (WindowEvent e)
			{
				closing();
			}
		});
			
		this.add(board);
		
		this.setSize(800,600);
		//this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	public static MainWindow getInstance()
	{
		return wind;
	}
	
	public void play()
	{
		this.setVisible(true);
		//gd.setFullScreenWindow(this);
	}
	
	private void closing()
	{
		if (JOptionPane.showConfirmDialog(this, "Are you sure ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)	
			System.exit(0);
	}
}