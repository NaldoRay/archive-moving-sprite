import javax.imageio.ImageIO;

import java.awt.Image;
import java.awt.event.KeyEvent;

public class Player
{
	private Image stopSprite [];
	private Image movingSprite[][]; 
	private byte dir;
	private Location loc;
	private int mx, my;
	private Stack direct;
	private boolean isDir[];
	public Player()
	{
		dir = 1;
		stopSprite = new Image [4];
		movingSprite = new Image [4][2];
		isDir = new boolean[4];
		try
		{
			for(int idx = 0; idx < 4; idx++)
				stopSprite[idx] = ImageIO.read(this.getClass().getResource(idx + ".PNG"));
		}
		catch(Exception e)
		{}
		
		direct = new Stack();
		loc = new Location(50,70);
	}
	
	public void move()
	{
		if (direct.peek() == 0)
			my = -2;
		else if (direct.peek() == 1)
			mx = 2;
		else if (direct.peek() == 2)
			my = 2;
		else if (direct.peek() == 3)
			mx = -2;
		else
		{
			if (isDir[0])
			{
				my = -2;
				dir = 0;
			}
			else if (isDir[1])	
			{
				mx = 2;
				dir = 1;
			}
			else if (isDir[2])
			{
				my = 2;
				dir = 2;
			}
			else if (isDir[3])	
			{
				mx = -2;
				dir = 3;
			}
		}
		loc.x += mx;
		loc.y += my;
		//System.out.println(direct.length);
	}
	
	public Location getPos()
	{
		return this.loc;
	}
	
	public void keyPressed(KeyEvent ev)
	{	
		int c = ev.getKeyCode();
		boolean s = true;
		if(c == KeyEvent.VK_UP)
		{
			//my = -2;
			dir = 0;
		}
		else if (c == KeyEvent.VK_DOWN)
		{
			//my = 2;
			dir = 2;
		}
		else if (c == KeyEvent.VK_LEFT)
		{
			//mx = -2;
			dir = 3;
		
		}
		else if (c == KeyEvent.VK_RIGHT)
		{
			//mx = 2;
			dir = 1;
		}
		else
		{
			direct.clear();
			s = false;
		}
		if (s && isDir[dir] == false)
		{
			direct.push(dir);
			isDir[dir] = true;
		}
	
	}
	
	public void keyReleased(KeyEvent ev)
	{
		int c = ev.getKeyCode();
		
		if (c == KeyEvent.VK_UP)
		{
			my = 0;
			isDir[0] = false;
			calcDir(0);
		}
		else if (c == KeyEvent.VK_DOWN)
		{
			my = 0;
			isDir[2] = false;
			calcDir(2);
		}
		else if (c == KeyEvent.VK_LEFT)
		{
			mx = 0;
			isDir[3] = false;
			calcDir(3);
		}
		else if (c == KeyEvent.VK_RIGHT)
		{
			mx = 0;
			isDir[1] = false;
			calcDir(1);
		}
	}
	
	private void calcDir(int b)
	{	
		if (direct.peek() == b)
		{
			direct.pop();
			if (direct.peek() != -1)
				dir = direct.peek();
		}
		else
		{
			direct.clear();
		}
	}
	
	public Image getImage()
	{
		return stopSprite[dir];
	}
}