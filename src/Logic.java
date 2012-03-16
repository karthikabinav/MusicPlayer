/*
 * Class contains the logic for opening,playing and stopping a .wav file
 * Author : Karthik Abinav S; CS10B057
 */
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Logic {

	static JFileChooser browse;
	static SourceDataLine audioSource;
	static int BUFFER_SIZE;

	static JFrame window;
	static JPanel player;
	static JButton play;
	static JButton stop;
	static JButton pause;
	static JButton open;

	/*
	 * Function to sync the variables between GUI and logic
	 * 
	 * @params: void
	 * 
	 * @return : void
	 */
	private static void setter() {
		window = GraphicalInterface.window;
		player = GraphicalInterface.player;
		play = GraphicalInterface.play;
		stop = GraphicalInterface.stop;
		pause = GraphicalInterface.pause;
		open = GraphicalInterface.open;

	}

	/*
	 * Function to stop the currently playing song
	 * 
	 * @params:void
	 * 
	 * @return:void
	 */
	public static void stopSong() {
		if (audioSource.isOpen()) {
			System.out.println("File has been stopped");
			audioSource.stop();
		}
	}

	/*
	 * Function to open the .wav file and start playing it
	 * 
	 * @params:void
	 * 
	 * @return : void
	 */
	public static void openFile() throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {
		setter();
		browse = new JFileChooser();
		int ret = browse.showOpenDialog(player);

		if (ret == JFileChooser.APPROVE_OPTION) {
			// File browser to get the file
			File file = browse.getSelectedFile();
			System.out.println("Opening: " + file.getName());

			// Creates an audio stream
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			AudioFormat audioFormat = stream.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class,
					audioFormat);

			// Adds the stream to the source data line

			audioSource = (SourceDataLine) AudioSystem.getLine(info);
			audioSource.open(audioFormat);

			audioSource.start();

			// Reads the dataline in buffer of length BUFFER_SIZE
			int readBytes = 0;
			BUFFER_SIZE = audioSource.getBufferSize();
			byte[] audioBuffer = new byte[BUFFER_SIZE];

			while (readBytes != -1) {
				readBytes = stream.read(audioBuffer, 0, audioBuffer.length);
				if (readBytes >= 0) {
					audioSource.write(audioBuffer, 0, readBytes);
				}
			}

			audioSource.drain();
			audioSource.stop();
			audioSource.close();

			System.out.println("Finished playing : " + file.getName());
		} else {
			System.out.println("Open command cancelled by user.");
		}
	}
}
