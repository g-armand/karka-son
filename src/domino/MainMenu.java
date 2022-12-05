package domino;

import javax.swing.*;
import java.awt.*;


public class MainMenu extends JFrame {

    static String player1;
    static String player2;
//    static String players_set;

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

        MenuButton submit = new MenuButton("SUBMIT");
        submit.addActionListener(e -> {
            player1 = name_field.getText();
            player2 = name_field.getText();
            submit.setEnabled(false);
        });

        MenuButton start = new MenuButton("START");
        start.addActionListener(e -> {
            new DominoFrame(player1, player2);
            main_menu.setVisible(false);
        });

//        MenuButton players = new MenuButton("PLAYERS:");
//        Choice players_num = new Choice();
//        players_num.setSize(300,100);
//        players_num.add("one");
//        players_num.add("two");
//        players.addActionListener(e -> players_set = players_num.getItem(players_num.getSelectedIndex()));

        JPanel set_name = new JPanel();
        set_name.setLayout(new GridLayout(2,2));
//        JPanel set_num = new JPanel();

        set_name.add(name);
        set_name.add(name_field);
        set_name.add(name2);
        set_name.add(name_field2);

        set_name.setLayout(new FlowLayout());

//        set_num.add(players);
//        set_num.add(players_num);
//        set_num.setLayout(new FlowLayout());

        main_menu.add(set_name);
//        main_menu.add(set_num);
        main_menu.add(submit);
        main_menu.add(start);
        main_menu.add(exit);

        main_menu.pack();

    }


}
