package karkason;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class GameFrame extends JFrame{

    JPanel main_panel;
    JPanel menu_panel;
    LeftPanelGUI left_panel;
    Background backgroundImage;
    int playerTurnIndex;
    Player[] playerList;
    GridBoardPanel tilesGrid;
    Board gameBoard;

    Tile[] globalBag;

    int turns;
    public GameFrame(Player[] playerList) {
        this.turns = 0;
        this.playerTurnIndex = 0;
        this.playerList = playerList;
        this.main_panel = new JPanel();
        this.menu_panel = new JPanel();
        this.backgroundImage = new Background();
        this.gameBoard = createBoard();
        this.left_panel = createLeftPanelGUI();

        this.tilesGrid = new GridBoardPanel(gameBoard.tiles);
        this.backgroundImage.add(tilesGrid);

        // GENERAL
        this.setTitle("*DoM1No$aM1Gø$*");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  // set frame size to screen size
        this.setSize(screenSize.width, screenSize.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // finish process when the window is closed

        // BUTTONS
        MenuButton exit = new MenuButton("EXIT GAME");
        exit.addActionListener(e -> {
            ScenariosManager.saveCurrentGame(gameBoard, playerList, playerTurnIndex, globalBag, turns);
            System.exit(0);
        });
        MenuButton mainmenu = new MenuButton("MAIN MENU");
        mainmenu.addActionListener(e -> {
            new MainMenu();
            this.dispose();
        });

        // MENU
        menu_panel.setBackground(new Color(255, 255, 255, 200));
        menu_panel.setLayout(new BorderLayout());
        menu_panel.setPreferredSize(new Dimension(800, 70));
        menu_panel.add(exit, BorderLayout.WEST);
        menu_panel.add(mainmenu, BorderLayout.EAST);

        //needed for displaying scores on first turn
        playerTurnIndex=-1;
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

    //meant to be overridden
    public Board createBoard(){
        return new Board();
    }

    //meant to be overridden by karkassonFrame
    public LeftPanelGUI createLeftPanelGUI(){
        return new LeftPanelGUI();
    }

    public void updateScores(int x, int y) {
        JPanel scoresPanel = new JPanel();
        scoresPanel.setLayout(new GridLayout(playerList.length+1, 1, 0, 0));
        scoresPanel.setLayout(new GridLayout(playerList.length+1, 1, 0, 0));
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
        JLabel name = new JLabel("IT'S "+ this.playerList[playerTurnIndex].name+"'S TURN !");
        scoresPanel.add(name);
        revalidate();
        endGame();
    }

    public void endGame(){
        var ref = new Object() {
            int i;
        };
        // FINISH GAME WHEN EVERY PLAYER HAS HAD allTiles number of TILES
        int allTiles = 10;

        ArrayList<Integer> points = new ArrayList<>();
        Map<Integer, String> allpoints = new HashMap<>();

        if(turns == playerList.length*allTiles) {
            for (ref.i = 0; ref.i < playerList.length; ref.i++) {
                allpoints.put(playerList[ref.i].points, playerList[ref.i].name);
                points.add(playerList[ref.i].points);
            }

            Integer winner = Collections.max(points);
            String winnerName = allpoints.get(winner);

            Object[] options = {"RESTART", "MAIN MENU"};
            int n = JOptionPane.showOptionDialog(this,
                    "GAME OVER. " + winnerName + " WINS!",
                    "GAME OVER",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
            if (n == 0) {
                this.dispose();
                restart(playerList);
            }
            if (n == 1) {
                new MainMenu();
                this.dispose();
            }
        }
    }

    //meant to be overridden
    public void restart(Player[] playerList){
        //
    }

    public int[] playTurn() {
        turns++;
        while(!gameBoard.isUsable(globalBag[turns])){
            turns++;
        }
        left_panel.add_button.doClick();
        Random picker = new Random();
        int x = 1;
        int y = 1;
        while(!gameBoard.isUsable(globalBag[turns], Board.increaseSizeOfBoard(gameBoard.tiles), x+1, y+1)){
            x = picker.nextInt(gameBoard.tiles.length);
            y = picker.nextInt(gameBoard.tiles[0].length);
            int spin = picker.nextInt(2);
            if(spin == 1){
                left_panel.tileContainedOnLeftPanel.spin("droite");
            }
        }
        gameBoard.addTile(left_panel.tileContainedOnLeftPanel, x, y);
        left_panel.removeLastTile();
        createLeftPanelGUI();
        tilesGrid.updateTilesGrid();
        left_panel.add_button.setEnabled(true);
        return new int[]{x+1, y+1};
    }

    public class LeftPanelGUI extends JPanel {

        public Tile tileContainedOnLeftPanel;
        public MenuButton add_button;
        public JButton spinButton;
        public int fullCapacity;
        public LeftPanelGUI(){
            this.fullCapacity = 2;
            add_button = new MenuButton("PICK A TILE");
            add_button.setPreferredSize(new Dimension(200, 100));
            add_button.addActionListener(e -> {
                add_button.setEnabled(false);
                GameFrame.this.turns += 1;
                this.tileContainedOnLeftPanel = pickNewTile();
                Tile_GUI tile = new Tile_GUI(this.tileContainedOnLeftPanel, -1, -1);
                removeLastTile();
                this.add(tile);
                tile.setPreferredSize(new Dimension(200, 200));
                this.revalidate();
                if (!gameBoard.isUsable(tileContainedOnLeftPanel)) {
                    JOptionPane.showMessageDialog(this, "Oops, this tile is not joinable anywhere, try another one");
                    add_button.setEnabled(true);
                    this.revalidate();
                }
            });

            spinButton = new MenuButton("SPIN");
            spinButton.setPreferredSize(new Dimension(200, 100));
            spinButton.addActionListener(e -> {
                if(this.getComponentCount()>this.fullCapacity){
                    this.tileContainedOnLeftPanel.spin("droite");
                    Tile_GUI tile = new Tile_GUI(this.tileContainedOnLeftPanel, -1, -1);
                    this.remove(this.fullCapacity);
                    this.add(tile);
                    tile.setPreferredSize(new Dimension(200, 200));
                    this.revalidate();
                }
            });

            setPreferredSize(new Dimension(250, 700));
            setBackground(new Color(255, 255, 250));
            add(add_button);
            add(spinButton);
        }

        //is meant to be overridden
        public Tile pickNewTile(){
            return GameFrame.this.globalBag[GameFrame.this.turns];
        }

        //updates scores for Domino Only, displays buttons for KarkasonLeftPanelGUI
        public void wannaPlaceMinion(int x, int y){
            if(playerList[playerTurnIndex].isHuman){
                if(this instanceof KarkasonFrame.KarkasonLeftPanelGUI){
                    ((KarkasonFrame.KarkasonLeftPanelGUI) this).displayButtons();
                } else {
                    updateScores(x, y);
                    while(!playerList[playerTurnIndex].isHuman){
                        int[] coords = playTurn();
                        updateScores(coords[0], coords[1]);
                    }
                }
            } else{
                playTurn();
                updateScores(0,0);
            }
        };
        public void removeLastTile(){
            if(this.getComponentCount()>this.fullCapacity){
                this.remove(this.fullCapacity);
                this.repaint();
            }
        }
    }

    public class GridBoardPanel extends JPanel {
        private Tile[][] trimmedTilesGrid;
        int intRows;
        int intCols;
        public GridBoardPanel(Tile[][] tab) {
            this.trimmedTilesGrid = Board.trimBoard(tab);
            this.setBackground(new Color(0,0,0,0));
            this.intRows = trimmedTilesGrid.length;
            this.intCols = trimmedTilesGrid[0].length;
            setLayout(new GridLayout(intRows, intCols, 0,0));
            for (int row = 0; row < intRows; row++) {
                for (int col = 0; col < intCols; col++) {
                    this.add(new Tile_GUI(trimmedTilesGrid[row][col], row, col));
                }
            }
            setButtonsToValue(false);
            //            this.setPreferredSize(new Dimension(600, 600));
            this.setPreferredSize(new Dimension(100/getComponentCount()+800, 100/getComponentCount()+800));
            // need to be adjusted (causes conflict with tuile gui size)
        }

        public void updateTilesGrid(){
            tilesGrid = new GridBoardPanel(GameFrame.this.gameBoard.tiles);
            tilesGrid.revalidate();
            backgroundImage.remove(0);
            backgroundImage.add(tilesGrid);
            backgroundImage.revalidate();
        }

        public void setButtonsToValue(boolean value){
            int componentIndex = 0;
            for(int x = 0; x<intRows; x++){
                for(int y=0; y<intCols; y++){
                    if(!(trimmedTilesGrid[x][y] instanceof EmptyTile)) {
                        int buttonIndex = 0;
                        Tile_GUI tile = (Tile_GUI) this.getComponent(componentIndex);
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 5; j++) {
                                tile.getComponent(buttonIndex).setEnabled(value);
                                buttonIndex++;
                            }
                        }
                    }
                    componentIndex++;
                }
            }
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
            this.addMouseListener(this);
            this.setBackground(new Color(200,200,200,240));
            setSize(new Dimension(75, 75));
            if(!(t instanceof EmptyTile)){
                Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED);
                this.setBorder(border);
                for (int i = 0; i < t.rows; i++) {
                    for (int j = 0; j < t.rows; j++) {
                        CellGUI element = new CellGUI(String.valueOf(t.content[i][j].getChar()), (x*5)+i, (y*5)+j); // place number in the center of a case
                        element.setFont(new Font(Font.SERIF, Font.BOLD, 25));
                        element.setForeground(new Color(43,10,7,250));
                        this.add(element);
                    }
                }
            } else {
                this.setBackground(new Color(0,0,0,0));
            }

        }

        @Override
        public Component getComponent(int n) {
            return super.getComponent(n);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(this.y!=-1 || this.x!=-1){
                int verticalOffset = Board.findBoundary(gameBoard.tiles, "north") -1;
                int horizontalOffset = Board.findBoundary(gameBoard.tiles,  "west") -1;
                if( gameBoard.addTile(left_panel.tileContainedOnLeftPanel, x+verticalOffset, y+horizontalOffset)){
//                    verticalOffset = Board.findBoundary(gameBoard.tiles, "north") -1;
//                    horizontalOffset = Board.findBoundary(gameBoard.tiles,  "west") -1;
                    left_panel.removeLastTile();
                    tilesGrid.updateTilesGrid();
                    left_panel.add_button.setEnabled(true);
                    left_panel.wannaPlaceMinion(x+verticalOffset+1, y+horizontalOffset+1);
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

        public class CellGUI extends JButton{
            int flattenedX;
            int flattenedY;
            public CellGUI(String text, int x, int y){
                super("");
                setPreferredSize(new Dimension(5, 5));
                setColor(text);
                flattenedX = x;
                flattenedY = y;
            }

            public void setColor(String text){
                if(text.matches("c|C")){
                    setBackground(new Color(236, 179, 36));
                }
                else if(text.matches("t")){
                    setBackground(new Color(218, 121, 14));
                }else if(text.matches("f")){
                    setBackground(new Color(160, 210, 77));
                }else if(text.matches("a")){
                    setBackground(new Color(77, 99, 210));
                }else if(text.matches("S")){
                    setBackground(new Color(64, 222, 204));
                }else if(text.matches("1")){
                    setBackground(new Color(87, 85, 85));
                } else if(text.matches("0")){
                    setBackground(new Color(197, 197, 203));
                } else if(text.matches("\\s")){
                    setBackground(new Color(197, 197, 203));
                }else{
                    setText(text);
                }
            }

            public void setEnabled(boolean value){
                if(!value){
                    for( ActionListener al : this.getActionListeners() ) {
                        this.removeActionListener( al );
                    }
                } else {
                    this.addActionListener(e ->{
                        updateScores(flattenedX, flattenedY);
                        tilesGrid.setButtonsToValue(false);
                        while(!playerList[playerTurnIndex].isHuman){
                            playTurn();
                            updateScores(0,0);
                        }
                    });
                }
            }
        }
    }
}
