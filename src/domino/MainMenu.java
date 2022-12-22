package domino;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class MainMenu extends JFrame {

    Player[] playerList = new Player[0];


    public MainMenu(){

        JFrame main_menu = new JFrame();
        main_menu.setSize(700,700);
        main_menu.setLayout(new GridLayout(5,1));
        main_menu.setVisible(true);

        JTextField name_field = new JTextField("player 1");
        name_field.setPreferredSize(new Dimension(300, 200));

        JTextField name_field2 = new JTextField("player 2");
        name_field2.setPreferredSize(new Dimension(300, 200));

        MenuButton exit = new MenuButton("EXIT GAME");
        exit.addActionListener(e -> System.exit(0));

        JLabel name = new JLabel("<html>\n" + "<font size=+1>PLAYER 1</font>\n");
        JLabel name2 = new JLabel("<html>\n" + "<font size=+1>PLAYER 2</font>\n");

        JPanel set_name = new JPanel();
        set_name.setLayout(new GridLayout(4,2));

        set_name.add(name);
        set_name.add(name_field);
        set_name.add(name2);
        set_name.add(name_field2);
        set_name.setPreferredSize(new Dimension(700, 200));


        MenuButton addplayer = new MenuButton("ADD PLAYER");
        addplayer.addActionListener( e -> {
            JLabel name3 = new JLabel("<html>\n" + "<font size=+1>PLAYER</font>\n"+ (this.playerList.length+3));
            JTextField name_field3 = new JTextField("player "+ (this.playerList.length+3));
            set_name.setPreferredSize(new Dimension(700,300));
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
//            new KarkasonFrame(playerList);
            main_menu.setVisible(false);
        });


        main_menu.setLocationRelativeTo(null); // display the JFrame to center position of a screen


        main_menu.add(addplayer);
        main_menu.add(set_name);
        main_menu.add(start);
        main_menu.add(karkason);
        main_menu.add(exit);

        main_menu.pack();

    }


}
