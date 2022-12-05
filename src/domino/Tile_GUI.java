package domino;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile_GUI extends JLabel implements MouseListener {

    public Tile_GUI(Tile t){

        JPanel tile_panel = new JPanel(new GridLayout(5, 5, 0, 0));
        Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        tile_panel.setBorder(border);
        tile_panel.setSize(50, 50);
        tile_panel.addMouseListener(this);


        for (int i = 0; i < t.rows; i++) {
            for (int j = 0; j < t.rows; j++) {
                JLabel element = new JLabel(String.valueOf(t.content[i][j]), SwingConstants.CENTER); // place number in the center of a case
                element.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 8));
                element.setBorder(border); // might be deleted later
                tile_panel.add(element);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        left_panel.remove(this);
        DominoFrame.board.add(this, BorderLayout.CENTER);
        this.setBackground(Color.CYAN);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
