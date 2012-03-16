import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Logic {
	static JFileChooser browse;

	static JFrame window;
	static JPanel player;
	static JButton play;
	static JButton stop;
	static JButton pause;
	static JButton open;

	private static void setter() {
		window = GraphicalInterface.window;
		player = GraphicalInterface.player;
		play = GraphicalInterface.play;
		stop = GraphicalInterface.stop;
		pause = GraphicalInterface.pause;
		open = GraphicalInterface.open;
	}

	public static void openFile() {
		setter();
		browse = new JFileChooser();
		int ret = browse.showOpenDialog(player);

		if (ret == JFileChooser.APPROVE_OPTION) {
			File file = browse.getSelectedFile();

			System.out.println("Opening: " + file.getName());

		} else {
			System.out.println("Open command cancelled by user.");
		}
	}
}
