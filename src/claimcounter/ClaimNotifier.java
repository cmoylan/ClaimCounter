/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package claimcounter;

import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 *
 * @author chrismoylan
 */
public class ClaimNotifier {

    private static String soundDir = "/resources/sounds";

    public static void play(String sound) {
        String filePath = String.format("%s/%s", soundDir, sound);
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;
        Clip clip;

        try {
            InputStream audioStream = ClaimNotifier.class.getResourceAsStream(filePath);
            InputStream bufferedIn = new BufferedInputStream(audioStream);

            stream = AudioSystem.getAudioInputStream(bufferedIn);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            // handle it
            e.printStackTrace();
        }
    }
}
