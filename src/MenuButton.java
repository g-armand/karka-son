import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton {

    public MenuButton(String etiquette){
        this.setText(etiquette);
        this.setFocusable(false);
        this.setSize(350, 100);
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setAlignmentY(CENTER_ALIGNMENT);
        this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        this.setBorder(BorderFactory.createEtchedBorder());

    }

}
