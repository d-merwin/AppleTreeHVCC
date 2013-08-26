
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class AppleTree extends JFrame {
	/**	Java Sample containing material for HVCC CISS 110
	 * 	Contains variables, custom methods/functions, 
	 * conditional control structures if/else/switch
	 * iterative control - for-loops
	 * SWING based GUI
	 * custom classes(AppleTree & Apple)
	 * Array (Apple[])
	 */
	private static final long serialVersionUID = 775696941264279635L;
	Apple[] appleLabels;
	JLabel resultsLabel1;
	JLabel resultsLabel2;
	final static String s1 = "Apples on Tree: ";
	final static String s2 = "Total collected: ";
	final static int MAX_NUMBER_OF_APPLES = 12;
	int totalApples;
    public AppleTree() {
        
        super("Apple Tree HVCC Sample");
        setSize(600, 700);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JLayeredPane lp = getLayeredPane();
        totalApples =0;
  
        JLabel background = null;
		try 
		{	
			//Load in picture of tree
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("PeterMTree.png");
			Image i= ImageIO.read(input);
			background = new JLabel(new ImageIcon(i));
			background.setVisible(true);
			background.setBounds(0, 0, 570, 640);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		 Apple.loadApple();  //Sets the apple image once as a static variable for all apples
		 appleLabels = new Apple[MAX_NUMBER_OF_APPLES];
		 for(int i=0;i<appleLabels.length;i++)
		 {
			 appleLabels[i] = new Apple();
		 }
        
        //Setup Button 1- Collection
        JButton collectB = new JButton("Collect Apples");
        collectB.setBounds(0, 550, 200, 100);
        collectB.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                collectApples();
            }
        });
        
        //SetupButton 2: Grow
        JButton growB = new JButton("Grow Apples");
        growB.setBounds(0, 450, 200, 100);
        growB.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                growApples();
            }
        });      

        resultsLabel1 = new JLabel(s1 + "0"); //Label: current apples on tree
        resultsLabel1.setLocation(400, 400);
        resultsLabel1.setSize(200,100);
        resultsLabel1.setVisible(true);
        resultsLabel2 = new JLabel(s2 + "0"); //Label Total apples collected
        resultsLabel2.setSize(200,100);
        resultsLabel2.setVisible(true);
        resultsLabel2.setLocation(400, 440);

        
        //Add all the Apples to the panel
        for(int i=0; i<appleLabels.length;i++){
        	lp.add(appleLabels[i], new Integer(6+i));
        }
        //Add buttons and labels to the panel
        lp.add(collectB, new Integer(3));
        lp.add(growB, new Integer(5));
        lp.add(resultsLabel1, new Integer(2));
        lp.add(resultsLabel2, new Integer(3));
        lp.add(background, new Integer(1));
    }
    public void growApples()
    {	//Resets each apples and counts how many total are alive
    	int aliveApples=0;
    	for(int i=0; i<appleLabels.length;i++)
    	{
    		appleLabels[i].grow();
    		if(appleLabels[i].isAlive())
    		{
    			aliveApples++;
    		}
    	}
    	resultsLabel1.setText(s1+aliveApples);
    	this.repaint();
    }
    public void collectApples()
    {	// Collects and totals the apples
    	int collection = 0;
    	for(int i=0; i<appleLabels.length; i++)
    	{
    		collection += appleLabels[i].collect();
    	}
    	totalApples += collection;
    	resultsLabel1.setText(s1 + "0");
    	resultsLabel2.setText(s2 + totalApples);
    	this.repaint();
    }
    public static void main(String[] args) 
    {
        AppleTree sl = new AppleTree();
        sl.setVisible(true);
    }
}
