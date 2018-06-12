package myplanarity;

import java.util.concurrent.ThreadLocalRandom;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
        stage.setTitle("My Planarity - DLC World Conquer");
        stage.show();
      
        gameScene(stage);
    }
   
    //cena do programa
    public void gameScene(Stage stage){
        root = new BorderPane();
        
        newGame();
        
        Scene scene = new Scene(root, 750, 720);
        stage.setResizable(false);
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
               if(   Selected != null 
                     && e.getX()<750 && e.getX()>0 
                     && e.getY()<660 && e.getY()>-10
                 )
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
        level.setTextFill(Color.GAINSBORO);
        level.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 28));
        BorderPane.setMargin(level, new Insets(10,0,0,10));
        return level;
    }
   
    //gera tela de fim de fase
    public void endLvl(){
        Label endGame = new Label("Nível Concluído!!");
        Button b = new Button("Continuar");
        
        BorderPane ending = new BorderPane();
        
        Image img = new Image("File:back.png", true);
        BackgroundImage back = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1000,720,false,false,false,false));
        root.setBackground(new Background(back));
        
        ending.setCenter(endGame);
        ending.setBottom(b);
        
        BorderPane.setAlignment(b, Pos.CENTER);
        BorderPane.setMargin(b, new Insets(12,12,200,12));
        
        b.setMinSize(100, 80);
        b.setFont(Font.font(20));
        
        b.setOnMousePressed((MouseEvent e) -> {
            newGame();
        });
        
        endGame.setTextFill(Color.CORAL);
        endGame.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.EXTRA_BOLD, 60));
        
        root.setCenter(ending);
        
    }
   //gera um novo jogo/nivel
    public void newGame(){
        
        double randomX = ThreadLocalRandom.current().nextInt(1, 11) * -200;
        double randomY = ThreadLocalRandom.current().nextInt(1, 8) * -200;
        
        Image img = new Image("File:back.png", true);
        BackgroundImage back = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT,randomX,false,Side.TOP,randomY,false), new BackgroundSize(3000,2300,false,false,false,false));
        root.setBackground(new Background(back));
        
        ALL.reset();
        
        lvl++;
        root.setCenter(game(lvl+5));
        root.setTop(nivel());
        
    }
    
    //Da uma posicao aleatoria para os vertices do grafo
    public void randGraph(int verts, int m, int n){
        int min = m;
        int max = n;
        int salva_verts_pra_depois = verts;
        verts--;
        while(verts >= 0){  
            int randomX = ThreadLocalRandom.current().nextInt(min, max);
            int randomY = ThreadLocalRandom.current().nextInt(min, max);
            
            Vertice v = ALL.getVertice(verts);
            
            ALL.changePosition(v, randomX, randomY);
            
            verts--;
        }
        
        if(ALL.checkCrosses() == 0){
            randGraph(salva_verts_pra_depois,m,n);
        }
        
    }
    
    public static void main(String[] args) {
        launch(args);
    } 
}
