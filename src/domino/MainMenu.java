package domino;

import javax.swing.*;
import java.awt.*;


public class MainMenu extends JFrame {

    Player[] playerList;


    public MainMenu(){

        JFrame main_menu = new JFrame();
        main_menu.setSize(700,700);
        main_menu.setLayout(new GridLayout(5,2));
        main_menu.setVisible(true);

        JTextField name_field = new JTextField();
        name_field.setPreferredSize(new Dimension(300, 100));

        JTextField name_field2 = new JTextField();
        name_field2.setPreferredSize(new Dimension(300, 100));

        MenuButton exit = new MenuButton("EXIT GAME");
        exit.addActionListener(e -> System.exit(0));

        JLabel name = new JLabel("PLAYER 1:");
        JLabel name2 = new JLabel("PLAYER 2");


        MenuButton start = new MenuButton("DOMINO");
        start.addActionListener(e -> {

//            this.playerList = new Player[]{new Player(name_field.getText()),
//                                           new Player(name_field2.getText())};
            Player[] playerList = new Player[]{new Player(name_field.getText()),
                                           new Player(name_field2.getText())};
            new DominoFrame(playerList);
            main_menu.setVisible(false);
        });

        JPanel set_name = new JPanel();
        set_name.setLayout(new GridLayout(2,2));

        set_name.add(name);
        set_name.add(name_field);
        set_name.add(name2);
        set_name.add(name_field2);
        set_name.setLayout(new FlowLayout());

        main_menu.add(set_name);
        main_menu.add(start);
        main_menu.add(exit);

        main_menu.pack();

    }


}
