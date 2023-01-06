import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class MainMenu extends JFrame {

    public MainMenu(){

        JFrame main_menu = new JFrame();
        main_menu.setSize(700,700);
        main_menu.setLayout(new GridLayout(6,1));
        main_menu.setVisible(true);

        JTextField name_field = new JTextField("player 1");
        name_field.setPreferredSize(new Dimension(300, 200));

        JTextField name_field2 = new JTextField("player 2");
        name_field2.setPreferredSize(new Dimension(300, 200));

        MenuButton exit = new MenuButton("EXIT GAME");
        exit.addActionListener(e -> System.exit(0));

        JLabel name = new JLabel("<html>\n" + "<font size=+1>PLAYER 1</font>\n");
        JLabel name2 = new JLabel("<html>\n" + "<font size=+1>PLAYER 2</font>\n");

        JCheckBox isHuman1 = new JCheckBox();
        JCheckBox isHuman2 = new JCheckBox();

        JPanel set_name = new JPanel();
        set_name.setLayout(new GridLayout(2,3));

        set_name.add(name,0);
        set_name.add(name_field,1);
        set_name.add(isHuman1,2);
        set_name.add(name2,3);
        set_name.add(name_field2,4);
        set_name.add(isHuman2,5);
        set_name.setPreferredSize(new Dimension(700, 200));

        MenuButton addplayer = new MenuButton("ADD PLAYER");
        addplayer.addActionListener( e -> {
            set_name.setLayout(new GridLayout(4,3));
            int indexOfAdditionalPlayer = set_name.getComponentCount()==6? 3: 4;
            JLabel name3 = new JLabel("<html>\n" + "<font size=+1>PLAYER "+(indexOfAdditionalPlayer)+"</font>\n");
            JTextField name_field3 = new JTextField("player "+ (indexOfAdditionalPlayer));
            JCheckBox isHuman3 = new JCheckBox();
            name_field3.setPreferredSize(new Dimension(700, 300));
            set_name.add(name3);
            set_name.add(name_field3);
            set_name.add(isHuman3);
            set_name.revalidate();
            main_menu.revalidate();

            //we only want 2 additional players
            if(set_name.getComponentCount()==12){
                addplayer.setEnabled(false);
            }
        });

        MenuButton start = new MenuButton("DOMINO");
        start.addActionListener(e -> {
            Player[] newPlayerList = new Player[2];
            newPlayerList[0] = new Player(name_field.getText());
            newPlayerList[0].setIsHuman(!isHuman1.isSelected());
            newPlayerList[1] = new Player(name_field2.getText());
            newPlayerList[1].setIsHuman(!isHuman2.isSelected());

            //check for third player
            try{
                JTextField additionalNameField = (JTextField) set_name.getComponent(7);
                JCheckBox additionalCheckBox = (JCheckBox) set_name.getComponent(8);
                newPlayerList = Arrays.copyOf(newPlayerList, 3);
                newPlayerList[2] = new Player(additionalNameField.getText());
                newPlayerList[2].setIsHuman(!additionalCheckBox.isSelected());
            } catch(ArrayIndexOutOfBoundsException exception){
                System.out.println("no third player found");
            }

            //check for fourth player
            try{
                JTextField additionalNameField = (JTextField) set_name.getComponent(10);
                JCheckBox additionalCheckBox = (JCheckBox) set_name.getComponent(11);
                newPlayerList = Arrays.copyOf(newPlayerList, 4);
                newPlayerList[3] = new Player(additionalNameField.getText());
                newPlayerList[3].setIsHuman(!additionalCheckBox.isSelected());
            } catch(ArrayIndexOutOfBoundsException exception){
                System.out.println("no fourth player found");
            }
            new DominoFrame(newPlayerList);
            main_menu.setVisible(false);
        });

        MenuButton karkason = new MenuButton("KARKASSON");
        karkason.addActionListener(e -> {
            Player[] newPlayerList = new Player[2];
            newPlayerList[0] = new Player(name_field.getText());
            newPlayerList[0].setIsHuman(!isHuman1.isSelected());
            newPlayerList[1] = new Player(name_field2.getText());
            newPlayerList[1].setIsHuman(!isHuman2.isSelected());

            //check for third player
            try{
                JTextField additionalNameField = (JTextField) set_name.getComponent(7);
                JCheckBox additionalCheckBox = (JCheckBox) set_name.getComponent(8);
                newPlayerList = Arrays.copyOf(newPlayerList, 3);
                newPlayerList[2] = new Player(additionalNameField.getText());
                newPlayerList[2].setIsHuman(!additionalCheckBox.isSelected());
            } catch(ArrayIndexOutOfBoundsException exception){
                System.out.println("no third player found");
            }

            //check for fourth player
            try{
                JTextField additionalNameField = (JTextField) set_name.getComponent(10);
                JCheckBox additionalCheckBox = (JCheckBox) set_name.getComponent(11);
                newPlayerList = Arrays.copyOf(newPlayerList, 4);
                newPlayerList[3] = new Player(additionalNameField.getText());
                newPlayerList[3].setIsHuman(!additionalCheckBox.isSelected());
            } catch(ArrayIndexOutOfBoundsException exception){
                System.out.println("no fourth player found");
            }
            new KarkasonFrame(newPlayerList);
            main_menu.setVisible(false);
        });

        MenuButton loadScenario = new MenuButton("LOAD");
        loadScenario.addActionListener(e -> {
            main_menu.setVisible(false);
            ScenariosManager.readFile();
        });

        // display the JFrame to center position of a screen
        main_menu.setLocationRelativeTo(null);

        main_menu.add(addplayer);
        main_menu.add(set_name);
        main_menu.add(start);
        main_menu.add(karkason);
        main_menu.add(exit);
        main_menu.add(loadScenario);

        main_menu.pack();

    }
}
