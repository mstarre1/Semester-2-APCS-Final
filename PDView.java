import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.imageio.*;
import javax.swing.*;

public class PDView extends JPanel {
	private JButton newGameButton;  
	private JButton newTowerButton;
	private JButton upgradeButton;
	private JButton nextWaveButton;
    private JLabel message;  
    private JTextField input;
    private PDModel model;
    private PDController controller;
    
	public PDView(PDModel model) {
		this.model = model;
		controller = new PDController(model, this);
	
	    setLayout(null);  // I will do the layout myself.
	    setPreferredSize( new Dimension(350,250) );
	
	    setBackground(new Color(255,255,255));  // Dark green background.
	
	    /* Create theresignButton components and add them to the panel. */
	    upgradeButton = new JButton("Upgrade!");
	    upgradeButton.addActionListener(controller); //**now that we have reference to controller, we can do this 
	    newGameButton = new JButton("New Game");
	    newGameButton.addActionListener(controller); //**now that we have reference to controller, we can do this
	    nextWaveButton = new JButton("Next wave");
	    nextWaveButton.addActionListener(controller);
	    
	    message = new JLabel("",JLabel.CENTER);
	    message.setFont(new  Font("Serif", Font.BOLD, 14));
	    message.setForeground(Color.black);
	    
	    PDBoard board = new PDBoard();
	    board.addMouseListener(controller);
	    board.setBounds(20,20,400,400); // Note:  size MUST be 164-by-164 !
	    newGameButton.setBounds(500, 60, 120, 30);
	    nextWaveButton.setBounds(500, 120, 120, 30);
	    upgradeButton.setBounds(500, 180, 120, 30);
	    message.setBounds(385, 300, 350, 30);
	    
	    this.add(board);
	    this.add(newGameButton); 
	    this.add(nextWaveButton);
	    this.add(upgradeButton);
	    this.add(message);
	
	}
	
	private class PDBoard extends JPanel {
    	
    	PDBoard() {
                setBackground(Color.BLACK);
    	}

        /**
         * Draw a checkerboard pattern in gray and lightGray.  Draw the
         * checkers.  If a game is in progress, hilite the legal moves.
         */
        public void paintComponent(Graphics g) {
            
            /* Turn on antialiasing to get nicer ovals. */
            
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g.setColor(Color.black);
            g.fillRect(0,0,getSize().width, getSize().height);
            g.setColor(Color.lightGray);
            /* Draw the squares of the hero, enemies, tree, and castle. */
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    g.drawRect(50*col, 50*row, 50, 50);
                }
            }
            g.setColor(Color.green);
            g.fillOval(this.getWidth() - 100, this.getHeight() - 100, 200, 200);
            ArrayList<Defender> defenders = model.getDefenders();
            for (int i = 0; i < defenders.size(); i++) {
            	g.drawImage(defenders.get(i).getSprite(), defenders.get(i).getX() - 25, defenders.get(i).getY() - 25, 50, 50, null);
            }
            /* If a game is in progress, hilite the legal moves.   Note that legalMoves
             is never null while a game is in progress. */      
            
            //****switched from controller to model b/c it's like "I need your state information"
            ArrayList<Enemy> enemies = model.getEnemies();
            for (int i = 0; i < enemies.size(); i++) {
            	g.drawImage(enemies.get(i).getSprite(), enemies.get(i).getX() - 25, enemies.get(i).getY() - 25, 50, 50, null);
            }            //TODO draw the enemies and lasers!
            
        }// end paintComponent()

    } //End Class
	
	public String getNewGameButtonText() {
 		return newGameButton.getText();
 	}
    
    public JButton getNewGameButton() {
    	return newGameButton;
    }

 	public String getUpgradeButtonText() {
 		return upgradeButton.getText();
 	}
 	
    public JButton getUpgradeButton() {
    	return upgradeButton;
    }
    
    public String getNewTowerButtonText() {
 		return newTowerButton.getText();
 	}
 	
    public JButton getNewTowerButton() {
    	return newTowerButton;
    }
    
    public String getNextWaveButtonText() {
    	return nextWaveButton.getText();
    }
    
    public JButton getNextWaveButton() {
    	return nextWaveButton;
    }
    
    public void setMessageText(String newText) {
    		this.message.setText(newText);
    }
}