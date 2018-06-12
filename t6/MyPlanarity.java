package myplanarity;

import java.util.concurrent.ThreadLocalRandom;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class MyPlanarity extends Application {
    private Grafo ALL;
    private Vertice Selected;
    private int lvl;
    private BorderPane root;
   @Override
    public void start(Stage stage){  
        lvl = 0;
        ALL= new Grafo();
        stage.setTitle("My Planarity");
        stage.show();
      
        gameScene(stage);
    }
   
    //cena do programa
    public void gameScene(Stage stage){
        root = new BorderPane();
         
        newGame();
        
        Scene scene = new Scene(root, 700, 700);
        stage.setScene(scene);
    }
   
    //painel do jogo
    public Pane game(int verts){
        Pane pane = new Pane();
        
        GraphMaker gm = new GraphMaker(verts,ALL);
        randGraph(verts,50,650);
        
        mouseEvents(pane);
        
        for(int i=0; i<= verts; i++){
            for(int j=0; j< ALL.getVertice(i).NumArestas();j++)
                pane.getChildren().add(ALL.getVertice(i).getAresta(j));
        }
        
        for(int i=0; i< verts; i++){
            pane.getChildren().add(ALL.getVertice(i));
        }
        
        return pane;
    }
    //eventos do mouse
    public void mouseEvents(Pane pane){
           pane.setOnMousePressed((MouseEvent e) -> {
               Selected = ALL.isVertice(e.getX(), e.getY());
           });

           pane.setOnMouseDragged((MouseEvent e) -> {
               if(Selected != null)
                   ALL.changePosition(Selected, e.getX(), e.getY());
           });

            pane.setOnMouseReleased((MouseEvent e) -> { 
                if(ALL.checkCrosses() == 0)
                    endLvl();
            });
    }
    
    //nivel atual
    public Label nivel(){
       
        String str = "Level "+ lvl;
        Label level = new Label(str);
        level.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 28));
        BorderPane.setMargin(level, new Insets(10,0,0,10));
        return level;
    }
   
    //gera tela de fim de fase
    public void endLvl(){
        Label endGame = new Label("Nível Concluído!!");
        Button b = new Button("Continuar");
        
        BorderPane ending = new BorderPane();
        
        
        ending.setCenter(endGame);
        ending.setBottom(b);
        
        BorderPane.setAlignment(b, Pos.CENTER);
        BorderPane.setMargin(b, new Insets(12,12,200,12));
        
        b.setMinSize(100, 80);
        b.setFont(Font.font(20));
        
        b.setOnMousePressed((MouseEvent e) -> {
            newGame();
        });
        
        endGame.setTextFill(Color.RED);
        endGame.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.EXTRA_BOLD, 60));
        
        root.setCenter(ending);
        
    }
   //gera um novo jogo/nivel
    public void newGame(){
        ALL.reset();
        
        lvl++;
        root.setCenter(game(lvl+5));
        root.setTop(nivel());
        
    }
    
    //Da uma posicao aleatoria para os vertices do grafo
    public void randGraph(int verts, int m, int n){
        int min = m;
        int max = n;
        verts--;
        while(verts >= 0){  
            int randomX = ThreadLocalRandom.current().nextInt(min, max);
            int randomY = ThreadLocalRandom.current().nextInt(min, max);
            
            Vertice v = ALL.getVertice(verts);
            
            ALL.changePosition(v, randomX, randomY);
            
            verts--;
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    } 
}
