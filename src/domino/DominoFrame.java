package domino;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DominoFrame extends JFrame {

    JPanel main_panel;
    JPanel menu_panel;
    LeftPanelGUI left_panel;
    Background backgroundImage;
    int playerTurnIndex;
    Player[] playerList;
    GridBoardPanel tilesGrid;
    DominoBoard dominoBoard;
    public DominoFrame(Player[] playerList) {

        this.playerTurnIndex = 0;
        this.playerList = playerList;

        this.main_panel = new JPanel();
        this.menu_panel = new JPanel();
        this.left_panel = new LeftPanelGUI();
        this.backgroundImage = new Background();
        this.dominoBoard = new DominoBoard();
        this.tilesGrid = new GridBoardPanel(dominoBoard.tiles);
        this.backgroundImage.add(tilesGrid);


        // GENERAL
        this.setTitle("*DoM1No$aM1Gø$*");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  // set frame size to screen size
        this.setSize(screenSize.width, screenSize.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // finish process when the window is closed


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
        menu_panel.setLayout(new BorderLayout());
        menu_panel.setPreferredSize(new Dimension(800, 60));
        menu_panel.add(exit, BorderLayout.WEST);
        menu_panel.add(mainmenu, BorderLayout.EAST);
        updateScores(0,0);

        // MAIN -> FRAME
        main_panel.setSize(800, 800);
        main_panel.setLayout(new BorderLayout());
        main_panel.add(menu_panel, BorderLayout.NORTH);
        main_panel.add(left_panel, BorderLayout.WEST);
        main_panel.add(backgroundImage, BorderLayout.CENTER);

        this.add(main_panel);
        this.setVisible(true);
    }

    public void updateScores(int x, int y){
        JPanel scoresPanel = new JPanel();
        scoresPanel.setLayout(new GridLayout(playerList.length+1, 1, 0, 0));
        playerList[playerTurnIndex].points = playerList[playerTurnIndex].points + dominoBoard.countPoints(x,y);
        for(Player player: playerList){
            scoresPanel.add(new JLabel(player.name+" score: " +player.points +"\n"));
        }
        if(menu_panel.getComponentCount()>2){
            menu_panel.remove(2);
        }
        menu_panel.add(scoresPanel, BorderLayout.CENTER);
        playerTurnIndex++;
        if(playerTurnIndex%playerList.length==0){
            playerTurnIndex=0;
        }

        JLabel name = new JLabel("IT'S "+DominoFrame.this.playerList[playerTurnIndex].name+"'S TURN !");
        name.setBackground(Color.cyan);
        scoresPanel.add(name);
    }

    public class LeftPanelGUI extends JPanel {

        public Tile tileContainedOnLeftPanel;
        public MenuButton add_button;

        public LeftPanelGUI(){
            var ref = new Object() {
                int i;
            };

            add_button = new MenuButton("PICK A TILE");
            add_button.addActionListener(e -> {
                this.tileContainedOnLeftPanel = new DominoTile();
                Tile_GUI tile = new Tile_GUI(this.tileContainedOnLeftPanel, -1, -1);
                this.add(tile);
                tile.setPreferredSize(new Dimension(200, 200));
                this.revalidate();
                if(!dominoBoard.isUsable(tileContainedOnLeftPanel)){
                    JOptionPane.showMessageDialog(this, "Oops, this tile is not joinable anywhere, try another one");
                    playerList[playerTurnIndex].tiles.add(tile);
                    this.revalidate();
                }
//                if(this.getComponentCount()>2){
//                    add_button.setEnabled(false);}
                if(playerList[playerTurnIndex].tiles != null){
                    for (ref.i =0; ref.i <playerList[playerTurnIndex].tiles.size(); ref.i++) {
                        add(playerList[playerTurnIndex].tiles.get(ref.i));
                        this.revalidate();
                    }
                }
            });

            JButton spinButton = new JButton("SPIN");
            spinButton.addActionListener(e -> {
                if(this.getComponentCount()>2){
                    this.tileContainedOnLeftPanel.spin("droite");
                    Tile_GUI tile = new Tile_GUI(this.tileContainedOnLeftPanel, -1, -1);
                    this.remove(2);
                    this.add(tile);
                    tile.setPreferredSize(new Dimension(200, 200));
                    this.revalidate();
                }
            });

            setPreferredSize(new Dimension(250, 100));
            setBackground(Color.GRAY);
            add(add_button);
            add(spinButton);
        }


        public void removeLastTile(){
            if(this.getComponentCount()>2){
                this.remove(2);
                this.repaint();
            }
        }

    }

    public class GridBoardPanel extends JPanel {
        public GridBoardPanel(Tile[][] tab) {
            this.setBackground(new Color(0,0,0,0));
            Tile[][] trimmedBoard = Board.trimBoard(tab);
            int intRows = trimmedBoard.length;
            int intCols = trimmedBoard[0].length;
            setLayout(new GridLayout(intRows, intCols, 0,0));
            for (int row = 0; row < intRows; row++) {
                for (int col = 0; col < intCols; col++) {
                    this.add(new Tile_GUI(trimmedBoard[row][col], row, col));
                }
            }

//            this.setPreferredSize(new Dimension(400+getComponentCount()*10, 400+getComponentCount()*10));
        }
    }


    public class Tile_GUI extends JPanel implements MouseListener {

        int x;
        int y;
        Tile tileContained;

        public Tile_GUI(Tile t, int x, int y){
            this.tileContained = t;
            this.x = x;
            this.y = y;
            this.setLayout(new GridLayout(5, 5, 0, 0));
            this.setPreferredSize(new Dimension(75, 75));
            this.addMouseListener(this);
            this.setBackground(new Color(200,200,200,240));


            if(!(t instanceof EmptyTile)){
                Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED);
                this.setBorder(border);
                for (int i = 0; i < t.rows; i++) {
                    for (int j = 0; j < t.rows; j++) {
                        JLabel element = new JLabel(String.valueOf(t.content[i][j].getChar()), SwingConstants.CENTER); // place number in the center of a case
                        element.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 8));
                        element.setBorder(border); // might be deleted later
                        this.add(element);
                    }
                }
            } else {
                this.setBackground(new Color(0,0,0,0));
            }

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(this.y!=-1 || this.x!=-1){

                var ref = new Object() {
                    int i;
                };

                int horizontalOffset = Board.findBoundary(DominoFrame.this.dominoBoard.tiles,  "west") -1;
                int verticalOffset = Board.findBoundary(DominoFrame.this.dominoBoard.tiles, "north") -1;
                if( dominoBoard.addTile(left_panel.tileContainedOnLeftPanel, x+verticalOffset, y+horizontalOffset)){
                    left_panel.removeLastTile();
                    tilesGrid = new GridBoardPanel(DominoFrame.this.dominoBoard.tiles);
                    tilesGrid.revalidate();
                    backgroundImage.remove(0);
                    backgroundImage.add(tilesGrid);
                    backgroundImage.revalidate();
                    updateScores(x+verticalOffset, y+horizontalOffset);
                }
            }
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


}
