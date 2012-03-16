import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Logic {

	static JFileChooser browse;
	static Clip audioClip;

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

	public static void openFile() throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {
		setter();
		browse = new JFileChooser();
		int ret = browse.showOpenDialog(player);

		if (ret == JFileChooser.APPROVE_OPTION) {
			File file = browse.getSelectedFile();
			System.out.println("Opening: " + file.getName());

			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			DataLine.Info info = new DataLine.Info(Clip.class,
					stream.getFormat());
			audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.open(stream);
			audioClip.start();

		} else {
			System.out.println("Open command cancelled by user.");
		}
	}
}
