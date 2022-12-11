package domino;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile_GUI extends JPanel implements MouseListener {


    public Tile_GUI(Tile t){

        this.setLayout(new GridLayout(5, 5, 0, 0));
        Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        this.setBorder(border);
        this.setSize(40, 40);
        this.addMouseListener(this);


        for (int i = 0; i < t.rows; i++) {
            for (int j = 0; j < t.rows; j++) {
                JLabel element = new JLabel(String.valueOf(t.content[i][j].getChar()), SwingConstants.CENTER); // place number in the center of a case
                element.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 8));
                element.setBorder(border); // might be deleted later
                this.add(element);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        DominoFrame.left_panel.remove(this);
        DominoFrame.left_panel.revalidate();
        this.setPreferredSize(new Dimension(40, 40));
        DominoFrame.board.add(this);
        DominoFrame.board.revalidate();
//        this.setBackground(Color.CYAN);
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
