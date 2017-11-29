
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class  ejemplo extends Application{
  Label lb_text;
  Button boton;
  
  public static void main(String[] args) {
    
    launch(args);  }
  @Override
  public void start(Stage primaryStage) throws Exception {
    lb_text=new Label("holi");
    boton= new Button("TOCA");
    boton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent arg0) {
        lb_text.setText("CHAO");
      }
    });
    VBox root= new VBox();
    root.getChildren().addAll(lb_text,boton);
    Scene scene= new Scene(root,500,500);
    primaryStage.setScene(scene);
    primaryStage.show();
    
  }
  
}