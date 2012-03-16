/*
 * Class which builds the GUI
 * Author : Karthik Abinav S ; CS10B057
 */

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicalInterface {
	static JFrame window;
	static JPanel player;
	static JButton play;
	static JButton stop;
	static JButton pause;
	static JButton open;

	/*
	 * Main method to initiate the GUI
	 * 
	 * @params : String []
	 * 
	 * @return : void
	 */
	public static void main(String args[]) {
		GUI();
	}

	/*
	 * Function which creates the open file and stop buttons
	 * 
	 * @params : void
	 * 
	 * @return : void
	 */
	public static void createButtons() {

		open = new JButton();
		open.setText("Open");
		Listener.openListener();
		player.add(open);

		stop = new JButton();
		stop.setText("Stop");
		Listener.stopListener();
		player.add(stop);

		window.pack();
	}

	/*
	 * Initialises the basic GUI
	 * 
	 * @params: void
	 * 
	 * @return : void
	 */
	public static void GUI() {
		window = new JFrame("Wave Player");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setMinimumSize(new Dimension(400, 200));

		player = new JPanel();
		window.add(player);
		window.pack();
		window.setVisible(true);

		createButtons();
	}

}
