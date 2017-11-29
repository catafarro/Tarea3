
package view;
import java.awt.Font;
import java.awt.Insets;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observer;


import controller.ConsoleController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.IGameLogic;
import model.UnoLogic;
import model.card.ICardPile;
import model.card.deck.NormalUnoDeck;
import model.card.type.BasicCard;
import model.card.type.Color;
import model.card.type.ICard;
import model.card.type.Symbol;
import model.player.IPlayerListBuilder;
import model.player.UnoPlayerListBuilder;
import model.player.type.HumanPlayer;
import model.player.type.IPlayer;
import model.player.type.RandomPlayer;

public class GUIview extends Application {
  private ImageView cartaView;
  GridPane gridpane;
  Map<String,String> cartasImages = new HashMap<String,String>();
  ArrayList<ICard> mano;  
  IGameLogic Game;
  ICard lastplayed;
  String direccion;
  
  public void guardarImages() {
    NormalUnoDeck mazo=new NormalUnoDeck();
    ICardPile cartas=mazo.createDeck();
    while(!cartas.isEmpty()) {
      ICard aux= cartas.popCard();
      cartasImages.put(aux.toString(), "file:assets/UnoCards/"+aux.getColor().getName()+"/"+aux.getSymbol().getName()+".png");
    }
   
    
  }
  public static void main(String args[]) {
    launch(args);
  }


  @Override
  public void start(Stage primaryStage)  {
    this.guardarImages();
    primaryStage.setTitle("UNO");
    ImageView backcartaView = new ImageView(new Image("file:assets/UnoCards/unoback.jpg",126,189, false, false));
    gridpane = new GridPane();
    gridpane.setHgap(10);
    gridpane.setVgap(10);
    IPlayerListBuilder playerBuilder = new UnoPlayerListBuilder();
    IPlayer p1 = new HumanPlayer("Jugador 1");
    IPlayer p2 = new RandomPlayer("CPU 1");
    IPlayer p3 = new RandomPlayer("CPU 2");
    IPlayer p4 = new RandomPlayer("CPU 3");
    playerBuilder.addPlayer(p1);
    playerBuilder.addPlayer(p2);
    playerBuilder.addPlayer(p3);
    playerBuilder.addPlayer(p4);
   
    this.Game = new UnoLogic(playerBuilder, new NormalUnoDeck());
    ConsoleView view = new ConsoleView(this.Game);
    ConsoleController ctrl = new ConsoleController(this.Game, view);
    ArrayList<Label> jugadores =new ArrayList<Label>();
    ArrayList<Label> marcador=new ArrayList<Label>();
    Label actual= new Label("Jugador Actual");
    marcador.add(actual);
    Label lb_text=new Label(p1.toString()+"\nTiene "+p2.getHandSize()+"Cartas");
    jugadores.add(lb_text);
    lb_text=new Label(p2.toString()+"\nTiene "+p2.getHandSize()+"Cartas");
    jugadores.add(lb_text);
    marcador.add(new Label(""));
    lb_text=new Label(p3.toString()+"\nTiene "+p2.getHandSize()+"Cartas");
    jugadores.add(lb_text);
    marcador.add(new Label(""));
    lb_text=new Label(p4.toString()+"\nTiene "+p4.getHandSize()+"Cartas");
    jugadores.add(lb_text);
    marcador.add(new Label(""));
    this.direccion="Dirrecion : ->";
    lb_text=new Label(direccion);
    jugadores.add(lb_text);
    int i = 0;
    for(Label label : jugadores) {
      gridpane.add(label,i+1,2);
      i++;
    }
    i=0;
    for(Label label : marcador) {
      gridpane.add(label,i+1,1);
      i++;
    }
    this.showPlayerHand(p1);
    gridpane.add(makeButton("<"),0,5);
    gridpane.add(makeButton(">"),6,5);
    gridpane.add(backcartaView,4,4);
    gridpane.add(makeCardView(this.Game.getCurrentPlayedCard()),2,4);
    primaryStage.setScene(new Scene(gridpane, 800,500));
    primaryStage.show();
    
    
    this.Game.announceWinner(ctrl);
  }
    

  public void showPlayerHand(IPlayer player) {
    ArrayList<ICard> mano=player.getHand();
    int i;
    for(i=0; i<5;i++) {
      gridpane.add(makeButtonCard(makeCardView(mano.get(i))),i+1,5);
    }
   }

private Node makeCardView(ICard carta) {
  String path=cartasImages.get(carta.toString());
  Image Carta = new Image(path,126,189, false, false);
  cartaView = new ImageView(Carta);
  cartaView.setSmooth(true);
  cartaView.setCache(true);
  return cartaView;
}

private Node makeButtonCard(Node node) {
  Button again = new Button("");
  again.setGraphic(node);
  again.setMinSize(30,189); 
  return again;
  }



private Node makeButton(String str) {
  Button again = new Button(str);
  again.setMinSize(30,189);
  return again;
  }


public void updatePlayedCard() {
  this.gridpane.setConstraints(makeCardView(this.Game.getCurrentPlayedCard()), 2, 4);
}



}