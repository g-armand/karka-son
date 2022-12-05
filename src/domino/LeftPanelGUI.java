package domino;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static domino.DominoFrame.board;
import static domino.MainMenu.player1;

public class LeftPanelGUI extends JPanel {

    public LeftPanelGUI(){

        MenuButton add_button = new MenuButton("PICK A TILE");
        add_button.addActionListener(e -> {
        Tile_GUI tile = new Tile_GUI(new DominoTile());
        this.add(tile);
        this.revalidate();
        });

        this.add(new MenuButton(player1));
        this.setPreferredSize(new Dimension(200, 100));
        this.setBackground(Color.GRAY);
        this.add(add_button);
    }


}
