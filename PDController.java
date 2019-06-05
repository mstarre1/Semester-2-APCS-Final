import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

import javax.swing.Timer;

// Interprets user input and manipulates model based on that input
// "Creamy white oreo middle" 
public class PDController implements ActionListener, MouseListener  {

	private PDModel model;
	private PDView view;
	private Timer timer;
	
	public PDController(PDModel model, PDView view) {
		this.model = model;
		this.view = view;
		timer = new Timer(1000, this);
	}

	/**
     * Respond to user's click on one of the two buttons.
     */
	// View has Swing components, so we had to add getter methods to determine which was source.
    public void actionPerformed(ActionEvent evt) { //since JButton is in View, add getter in view to check Text
        String command = evt.getActionCommand();
        if (evt.getSource() == timer) {
        	advanceWave();
        } else if (command.equals(view.getNewGameButtonText()))
            doNewGame();
        else if (command.equals(view.getUpgradeButtonText())) 
        	doUpgrade();
        else if (command.equals(view.getNextWaveButtonText()))
        	doNextWave();
        
    }

    /**
     * Start a new game
     */
    public void doNewGame() {
        view.setMessageText("The game has started. Use your cash to build defenders." 
        					+ " You will get more cash every invader you defeat. When"
        					+ " you are ready for the next wave, press next wave.");
        view.getNewGameButton().setEnabled(false);
        view.getNextWaveButton().setEnabled(true);
        view.repaint();
    }

    public void doUpgrade() {
        try {
        	model.upgrade(model.getSelectedDefender());
        } catch(Exception e) {
           	view.setMessageText("Not enough money or no tower selected!!!");
        }
        view.repaint();
    }
    
    public void doNextWave() {
    	model.setupNextWave();
    	timer.start();
    }
    
    public void advanceWave() {
    	int waveStatus = model.advanceWave();
    	if (waveStatus == 2) {
        	view.setMessageText("You passed wave " + model.getWave() + ". Press next wave to move on.");
        	timer.stop();
    	} if (waveStatus == 1) {
        	view.setMessageText("Game over! You lasted " + model.getWave() + " rounds. Press new game to play again.");
    		timer.stop();
    	}	
        view.repaint();
    }

    /**
     * The game ends.  The parameter, str, is displayed as a message
     * to the user.  The states of the buttons are adjusted so players
     * can start a new game.  This method is called when the game
     * ends at any point in this class.
     */
    public void gameOver(String str) {
    	view.setMessageText(str);
    	view.getNewGameButton().setEnabled(true);
        view.getNewTowerButton().setEnabled(false);
        view.getNextWaveButton().setEnabled(false);
        view.repaint();
    }

	
	
    /**
     * Respond to a user click on the board.  If no game is in progress, show 
     * an error message.  Otherwise, find the row and column that the user 
     * clicked and call doClickSquare() to handle it.
     */
    public void mousePressed(MouseEvent evt) {
        try {
    		model.getDefender(evt.getX(), evt.getY());
        } catch(Exception e) {
        	view.setMessageText("Not enough money!!!");
        }
    	view.repaint();
    }

    public void mouseReleased(MouseEvent evt) { }
    public void mouseClicked(MouseEvent evt) { }
    public void mouseEntered(MouseEvent evt) { }
    public void mouseExited(MouseEvent evt) { }

	
}
