package towerdefense.resourcesloader;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.applet.AudioClip;
import java.io.File;
import java.util.HashMap;

public class SoundLoader {

    private static Clip clip;
    public static final File BGSFX = new File("src/resources/SFX/BG.wav");
    public static final File inGameSFX = new File("src/resources/SFX/welcome.wav");
    public static final File gameOverSFX = new File("src/resources/SFX/gameOver.wav");
    public static final File normalTowerSFX = new File("src/resources/SFX/normal.wav");
    public static final File machineTowerSFX = new File("src/resources/SFX/machine.wav");
    public static final File sniperTowerSFX = new File("src/resources/SFX/sniper.wav");
    public static final File dieSFX = new File("src/resources/SFX/dieEnemy.wav");
    public static final File enemyAttackSFX = new File("src/resources/SFX/addEnemy.wav");

    public static void play(File f) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(f);
            clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        clip.stop();
    }

}
