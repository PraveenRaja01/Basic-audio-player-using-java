import java.io.File;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.IOException;

public class Audio {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String s = JOptionPane.showInputDialog("Enter a song name with Extension .wav:");
        File file = new File(s);

        if (file.exists()) {
            if (s.endsWith(".mp3")) {
                try {
                    MP3Player mp3Player = new MP3Player(file);
                    handleMP3PlayerControls(mp3Player);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error playing MP3 file: " + e.getMessage());
                }
            } else if (s.endsWith(".wav")) {
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    handleClipControls(clip);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error playing WAV file: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Unsupported file format!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "File not found: " + s);
        }
    }

    private static void handleMP3PlayerControls(MP3Player mp3Player) {
        String response = "";
        while (!response.equals("Q")) {
            response = JOptionPane.showInputDialog("P=Play, S=Stop, Q=Quit");
            response = response.toUpperCase();

            switch (response) {
                case "P":
                    mp3Player.play();
                    break;
                case "S":
                    mp3Player.stop();
                    break;
                case "Q":
                    mp3Player.close();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Enter a valid response");
            }
        }
    }

    private static void handleClipControls(Clip clip) {
        String response = "";
        while (!response.equals("Q")) {
            response = JOptionPane.showInputDialog("P=Play, S=Stop, Q=Quit");
            response = response.toUpperCase();

            switch (response) {
                case "P":
                    clip.start();
                    break;
                case "S":
                    clip.stop();
                    break;
                case "Q":
                    clip.close();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Enter a valid response");
            }
        }
    }
}

