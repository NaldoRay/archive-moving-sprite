import javax.swing.JPanel;
import javax.imageio.ImageIO;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.*;

public class Board extends JPanel implements Runnable
{
	private Player player;
	private Image bg;
	//private java.awt.Rectangle bound;
	
	public Board()
	{
		//java.awt.GraphicsEnvironment ge = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
		//System.out.println(ge.getMaximumWindowBounds().height);
		//java.awt.GraphicsDevice gd = ge.getDefaultScreenDevice();
		// bound = gd.getDefaultConfiguration().getBounds();
		this.setBackground(java.awt.Color.BLACK);
		//System.out.println(ev.getKeyChar());
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ev)
			{
				player.keyPressed(ev);
			}
			
			public void keyReleased(KeyEvent ev)
			{
				player.keyReleased(ev);
			}
		});
		try
		{
			bg = ImageIO.read(this.getClass().getResource("lom-tree.jpg"));
		}
		catch(Exception e){}
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		player = new Player();
		//this.setIgnoreRepaint(true);
		new Thread(this).start();
	}
	
	public void run ()
	{
		while (true)
		{
			player.move();
			repaint();
			try
			{
				Thread.sleep(20);
			}
			catch (InterruptedException e)
			{}
		}
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		Location p = player.getPos();
		g.drawImage(bg,0,0,800,600, this);// bound.width, bound.height, this);
		g.drawImage(player.getImage(), p.x, p.y, this);
		g.dispose();
	}
}