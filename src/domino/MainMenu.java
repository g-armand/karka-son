package domino;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class MainMenu extends JFrame {

    Player[] playerList = new Player[0];


    public MainMenu(){

        JFrame main_menu = new JFrame();
        main_menu.setSize(700,700);
        main_menu.setLayout(new GridLayout(5,2));
        main_menu.setVisible(true);

        JTextField name_field = new JTextField("player 1");
        name_field.setPreferredSize(new Dimension(300, 200));

        JTextField name_field2 = new JTextField("player 2");
        name_field2.setPreferredSize(new Dimension(300, 200));

        MenuButton exit = new MenuButton("EXIT GAME");
        exit.addActionListener(e -> System.exit(0));

        JLabel name = new JLabel("PLAYER 1");
        JLabel name2 = new JLabel("PLAYER 2");

        JPanel set_name = new JPanel();
        set_name.setLayout(new GridLayout(2,2));

        set_name.add(name);
        set_name.add(name_field);
        set_name.add(name2);
        set_name.add(name_field2);
        set_name.setLayout(new BoxLayout(set_name, BoxLayout.PAGE_AXIS));


        MenuButton addplayer = new MenuButton("ADD PLAYER");
        addplayer.addActionListener( e -> {
            JLabel name3 = new JLabel("PLAYER "+ (this.playerList.length+3));
            JTextField name_field3 = new JTextField("player "+ (this.playerList.length+3));
            name_field3.setPreferredSize(new Dimension(300, 200));
            set_name.add(name3);
            set_name.add(name_field3);
            set_name.revalidate();
            main_menu.revalidate();
            Player third = new Player(name_field3.getText());
            this.playerList = Arrays.copyOf(this.playerList, this.playerList.length+1);
            this.playerList[this.playerList.length-1] = third;
            //we only want 2 additional players
            if(this.playerList.length==2){
                addplayer.setEnabled(false);
            }
        });

        MenuButton start = new MenuButton("DOMINO");
        start.addActionListener(e -> {
            Player[] playerList = new Player[this.playerList.length+2];
            playerList[0] = new Player(name_field.getText());
            playerList[1] = new Player(name_field2.getText());
            for(int index=0; index<this.playerList.length; index++){
                playerList[index+2] = this.playerList[index];
            }
            new DominoFrame(playerList);
            main_menu.setVisible(false);
        });

        MenuButton karkason = new MenuButton("KARKASSON");
        karkason.addActionListener(e -> {
            Player[] playerList = new Player[]{new Player(name_field.getText()+" 1st player"),
                                               new Player(name_field2.getText()+" 2nd player")};
            new KarkasonFrame(playerList);
            main_menu.setVisible(false);
        });




        main_menu.add(addplayer);
        main_menu.add(set_name);
        main_menu.add(start);
        main_menu.add(karkason);
        main_menu.add(exit);

        main_menu.pack();

    }


}
