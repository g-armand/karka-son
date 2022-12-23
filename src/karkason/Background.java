package karkason;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Background extends JPanel {

    private Image background;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this); // image scaled
    }

    public Background(){
        try {
            background = ImageIO.read(Background.class.getResource("/resources/images/wood.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
