import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import java.io.*;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import java.io.File;

public class ScenariosManager {
    private ScenariosManager(){
        //Not meant to be instanciated
    }

    public static void saveCurrentGame(Board gameboard, Player[] playerList, int playerTurnIndex, Tile[] globalBag, int turnIndex){
        try
        {
            DocumentBuilderFactory XML_Fabrique_Constructeur = DocumentBuilderFactory.newInstance();
            DocumentBuilder XML_Constructeur = XML_Fabrique_Constructeur.newDocumentBuilder();

            Document XML_Document = XML_Constructeur.newDocument();
            Element documentation = XML_Document.createElement(gameboard instanceof KarkasonBoard ? "karkason" : "domino");
            XML_Document.appendChild(documentation);

            Element parametersElement = XML_Document.createElement("Parameters");
            documentation.appendChild(parametersElement);
            Element gameBoardElement = XML_Document.createElement("GameBoard");
            documentation.appendChild(gameBoardElement);
            Element globalBagElement = XML_Document.createElement("GlobalBag");
            documentation.appendChild(globalBagElement);
            Element playersElement = XML_Document.createElement("Players");
            documentation.appendChild(playersElement);

            Attr gameAttribut = XML_Document.createAttribute("Game");
            gameAttribut.setValue(gameboard instanceof KarkasonBoard ? "karkason" : "domino");
            parametersElement.setAttributeNode(gameAttribut);
            Attr gameAttributcopy = XML_Document.createAttribute("Game");
            gameAttributcopy.setValue(gameboard instanceof KarkasonBoard ? "karkason" : "domino");
            gameBoardElement.setAttributeNode(gameAttributcopy);
            Attr gameAttributcopycopy = XML_Document.createAttribute("Game");
            gameAttributcopycopy.setValue(gameboard instanceof KarkasonBoard ? "karkason" : "domino");
            globalBagElement.setAttributeNode(gameAttributcopycopy);
            Attr BoardXAttribut = XML_Document.createAttribute("BoardHeight");
            BoardXAttribut.setValue(String.valueOf(gameboard.tiles.length));
            parametersElement.setAttributeNode(BoardXAttribut);
            Attr BoardYAttribut = XML_Document.createAttribute("BoardWidth");
            BoardYAttribut.setValue(String.valueOf(gameboard.tiles[0].length));
            parametersElement.setAttributeNode(BoardYAttribut);

            Attr turnIndexAttribut = XML_Document.createAttribute("Turns");
            turnIndexAttribut.setValue(String.valueOf(turnIndex));
            parametersElement.setAttributeNode(turnIndexAttribut);

            Attr PlayerTurnIndexAttribut = XML_Document.createAttribute("PlayerTurnIndex");
            PlayerTurnIndexAttribut.setValue(String.valueOf(playerTurnIndex));
            parametersElement.setAttributeNode(PlayerTurnIndexAttribut);
            int counter = 0;
            for(Player player: playerList){
                Attr PlayerAttribut = XML_Document.createAttribute("Player"+counter);
                PlayerAttribut.setValue(player.getName()+","+String.valueOf(player.getPoints()));
                playersElement.setAttributeNode(PlayerAttribut);
                counter++;
            }
            counter =0;
            for(int xBoard = 0; xBoard<gameboard.tiles.length; xBoard++){
                for(int yBoard = 0; yBoard<gameboard.tiles[0].length; yBoard++){
                    Tile tile = gameboard.tiles[xBoard][yBoard];
                    if(!(tile instanceof EmptyTile)){
                        String result = xBoard+","+yBoard;
                        for(int x=0; x<5; x++){
                            for(int y=0; y<5; y++){
                                result += "," + tile.content[x][y].getChar();
                            }
                        }
                        Attr tileAttribut = XML_Document.createAttribute("Tile"+counter);
                        tileAttribut.setValue(result);
                        gameBoardElement.setAttributeNode(tileAttribut);
                        counter++;
                    }
                }
            }

            counter=0;
            for(Tile tile: globalBag){
                String result = "";
                for(int x=0; x<5; x++){
                    for(int y=0; y<5; y++){
                        result += "," + tile.content[x][y].getChar();
                    }
                }
                Attr tileAttribut = XML_Document.createAttribute("Tile"+counter);
                tileAttribut.setValue(result);
                globalBagElement.setAttributeNode(tileAttribut);
                counter++;
            }

            TransformerFactory XML_Fabrique_Transformeur = TransformerFactory.newInstance();
            Transformer XML_Transformeur = XML_Fabrique_Transformeur.newTransformer();
            DOMSource source = new DOMSource(XML_Document);
            StreamResult resultat = new StreamResult(new File("XML_résultat.xml"));
            XML_Transformeur.transform(source, resultat);
            System.out.println("Le fichier XML a été généré !");
        }
        catch (ParserConfigurationException pce)
        {
            pce.printStackTrace();
        }
        catch (TransformerException tfe)
        {
            tfe.printStackTrace();
        }
    }

    public static void readFile(){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse("XML_résultat.xml",
                    new DefaultHandler()
                    {
                        Board gameboard;
                        Player[] playerList;
                        int playerTurnIndex;
                        Tile[] globalBag;
                        int turnIndex;

                        public void endDocument() throws SAXException
                        { updateValues();}
                        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                            System.out.println("Début d'élément : " + qName);
                            if(qName.matches("Parameters")){
                                playerTurnIndex = Integer.valueOf(attributes.getValue("PlayerTurnIndex"));
                                turnIndex = Integer.valueOf(attributes.getValue("Turns"));
                                int width = Integer.valueOf(attributes.getValue("BoardWidth"));
                                int height = Integer.valueOf(attributes.getValue("BoardHeight"));
                                if(attributes.getValue("Game").matches("karkason")){
                                    gameboard = new KarkasonBoard();
                                    gameboard.tiles = new Tile[width][height];
                                } else{
                                    gameboard = new DominoBoard();
                                    gameboard.tiles = new Tile[width][height];
                                }
                                for(int x=0; x<height; x++){
                                    for(int y=0; y<width;y++){
                                        gameboard.tiles[x][y] = new EmptyTile();
                                    }
                                }
                            } else if(qName.matches("Players")){
                                playerList = new Player[attributes.getLength()];
                                for(int index=0; index< attributes.getLength(); index++) {
                                    String[] result = attributes.getValue("Player" + index).split(",");
                                    playerList[index] = new Player(result[0], Integer.parseInt(result[1]));
                                }
                            }
                            else if(qName.matches("GameBoard")){
                                int counter = 0;
                                for(int tileIndex=0; tileIndex< attributes.getLength()-1; tileIndex++){
                                    String[] result = attributes.getValue("Tile"+tileIndex).split(",");
                                    Tile t;
                                    if(attributes.getValue("Game").matches("karkason")){
                                        t = new KarkasonTile();
                                    } else{
                                        t = new DominoTile();
                                    }
                                    int index = 0;
                                    for(int x = 0; x<5; x++){
                                        for(int y=0; y<5;y++){
                                            t.content[x][y] = new Cell(result[index+2].charAt(0));
                                            index++;
                                        }
                                    }
                                    gameboard.tiles[Integer.parseInt(result[0])][Integer.parseInt(result[1])] = t;
                                }
                            }
                            else if(qName.matches("GlobalBag")){
                                globalBag = new Tile[attributes.getLength()-1];
                                for(int tileIndex=0; tileIndex< attributes.getLength()-1; tileIndex++) {
                                    String[] result = attributes.getValue("Tile" + tileIndex).substring(1).split(",");
                                    Tile t;
                                    if (attributes.getValue("Game").matches("karkason")) {
                                        t = new KarkasonTile();
                                    } else {
                                        t = new DominoTile();
                                    }
                                    int index = 0;
                                    for (int x = 0; x < 5; x++) {
                                        for (int y = 0; y < 5; y++) {
                                            t.content[x][y] = new Cell(result[index].charAt(0));
                                            index++;
                                        }
                                    }
                                    globalBag[tileIndex] = t;
                                }
                            }
                        }
                        public void updateValues(){
                            GameFrame frame;
                            if(gameboard instanceof KarkasonBoard){
                                frame = new KarkasonFrame(playerList);
                            } else{
                                frame = new DominoFrame(playerList);
                            }
                            frame.turns = turnIndex;
                            frame.gameBoard = gameboard;
                            frame.globalBag = globalBag;
                            frame.playerTurnIndex = playerTurnIndex;
                            frame.updateScores(0,0);
                            frame.createLeftPanelGUI();
                            frame.tilesGrid.updateTilesGrid();
                        }
                    }
            );
        }
        catch (IOException e)
        {
            System.err.println("Erreur de lecture : "+e);
            System.exit(1);
        }
        catch (SAXException e)
        {
            System.err.println("Erreur d'interprétation : "+e);
            System.exit(1);
        }
        catch (ParserConfigurationException e)
        {
            System.err.println("Pas d'interpréteur SAX pour la configuration demandée : "+e);
            System.exit(1);
        }
    }

}
