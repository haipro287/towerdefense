package towerdefense.resourcesloader;

import javax.swing.*;
import java.awt.*;

public class ImageLoader {
    public static Image getImage(String name) {
        ImageIcon icon = new ImageIcon(name);
        return icon.getImage();
    }
}
