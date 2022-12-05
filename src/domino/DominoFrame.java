package domino;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DominoFrame extends JFrame {

    String player1;
    String player2;

    JPanel main_panel = new JPanel();
    JPanel menu_panel = new JPanel();
    static LeftPanelGUI left_panel = new LeftPanelGUI();
    static Background board = new Background();


    public DominoFrame(String player1, String player2) {


        // GENERAL

        this.setTitle("*DoM1No$aM1Gø$*");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // finish process when the window is closed

        this.player1 = player1;
        this.player2 = player2;

        // BUTTONS

        MenuButton exit = new MenuButton("EXIT GAME");
        exit.addActionListener(e -> System.exit(0));

        MenuButton mainmenu = new MenuButton("MAIN MENU");
        mainmenu.addActionListener(e -> {
            new MainMenu();
            this.setVisible(false);
        });


        // MENU
        menu_panel.setBackground(Color.black);
        menu_panel.add(exit);
        menu_panel.add(mainmenu);
        menu_panel.setLayout(new FlowLayout());
        menu_panel.setPreferredSize(new Dimension(800, 60));


        // MAIN -> FRAME
        main_panel.setSize(800, 800);
        main_panel.setLayout(new BorderLayout());
        main_panel.add(menu_panel, BorderLayout.NORTH);
        main_panel.add(left_panel, BorderLayout.WEST);
        main_panel.add(board, BorderLayout.CENTER);


        this.add(main_panel);
        this.setVisible(true);

//        this.pack(); // adjust the window to the size of content (has to be used in the end)

    }


}
