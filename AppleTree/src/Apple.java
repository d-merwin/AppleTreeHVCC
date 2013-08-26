import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Apple extends JLabel 
{
	// Apple class is a custom JLabel with the picture of an apple
	// grow- randomly generates a new location for the apple on the tree within limits,
	//		with a random but high chance the apple _isAlive = true;
	// Collect-  collecting the apple "kills" the apple and returns 1 for counting.  
	//			Already dead apples can't be collected
	private static ImageIcon image;
	private boolean _isAlive;
	public Apple()
	{
		super(image);
		_isAlive = false;
		this.setSize(30,30);
		this.setVisible(false);
	}
	public static void loadApple()
	{
		if(image != null)
		{
			// makes sure it only loads once
		}
		else
		{
			//Loads the apple image
			try 
			{
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				InputStream input = classLoader.getResourceAsStream("nicubunu_Apple.png");
				Image i;
				i = ImageIO.read(input);
				image = new ImageIcon(i);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
		}
	}
	public boolean isAlive()
	{
		return _isAlive;
	}
	public int collect()
	{
		if(_isAlive==true)
		{
			_isAlive = false;
			this.setVisible(false);
			return 1;
		}
		return 0;
	}
	public void grow()
	{
		Random generator = new Random();
		if(generator.nextInt(100)<=35)
		{
			_isAlive = false;
			this.setVisible(false);
			
		}
		else
		{
			this.setVisible(true);
			this.setLocation(90 + generator.nextInt(340), 100 + generator.nextInt(200));
			_isAlive = true;
		}
	}

}
