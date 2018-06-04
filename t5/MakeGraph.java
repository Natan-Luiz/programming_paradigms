import java.io.File;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;


public class MakeGraph extends Application {

   private Grafo ALL;
   private int tool;
   private int select;
   private Pane pane;
   private Color color;
   private float stroke;
   private double stroke2;
   
   @Override
   public void start(Stage stage) {
      color = Color.RED;
      stroke = 3;
      stroke2 = 0;
      pane = new Pane();
      ALL= new Grafo();
      tool = 1;
      select = -1;
      Interface(stage,pane);
      mousevents(pane);
    }
   
   public void mousevents(Pane pane)
   {
       pane.setOnMousePressed((MouseEvent e) -> {
           if(tool == 1){
              if(ALL.isOk(e.getX(), e.getY(), -1) == -1){
                Circle v = new Circle(e.getX(), e.getY(), 20, color);
                v.setStrokeWidth(stroke);
                v.setStroke(Color.BLACK);
                v.setFill(color);
                ALL.addVertice(v);
                pane.getChildren().add(ALL.verts.get(ALL.verts.size()-1));
              }
           }
           
           else if(select != -1 && 0 <= ALL.isOk(e.getX(),e.getY(),select)){
              Aresta a = ALL.verts.get(select).saidas.get( ALL.verts.get(select).saidas.size() - 1 );
              a.setEnd(ALL.verts.get( ALL.isOk(e.getX(),e.getY(),-1) ));
              a.setEndX(a.getEnd().getCenterX());
              a.setEndY(a.getEnd().getCenterY());
              ALL.verts.get(select).setStroke(Color.BLACK);
              select = -1;
              pane.getChildren().add(a);
          }
           
          else if(select != -1){
              ALL.verts.get(select).saidas.remove( ALL.verts.get(select).saidas.size() - 1 );
              Alert alert = new Alert(AlertType.INFORMATION);
              alert.setTitle("Erro");
              alert.setHeaderText("Você não pode clicar aí.");
              alert.show();
              ALL.verts.get(select).setStroke(Color.BLACK);
              select = -1;
          }
          
          else{
              if(0 <= ALL.isOk(e.getX(),e.getY(),-1)){
                 select = ALL.isOk(e.getX(),e.getY(),-1);
                 Line line = new Line();
                 
                 line.setStartX(e.getX());
                 line.setStartY(e.getY());
                  
                 Aresta a = ALL.addAresta(line);
                 if(a == null) {
                     select = -1;
                     return;
                 }
                 a.setStroke(color);
                 if(stroke2 != 0) a.getStrokeDashArray().add(stroke2);
                 a.setStrokeWidth(stroke);
                 a.setStartX(ALL.verts.get(select).getCenterX());
                 a.setStartY(ALL.verts.get(select).getCenterY());
                 ALL.verts.get(select).setStroke(Color.YELLOW);
              }
          }
      });
   }
   
   
    public void Interface(Stage stage, Pane pane){
       BorderPane root = new BorderPane();
       
       
      //Criação de Telas
      BorderPane toolbar = new BorderPane();
      HBox selector = new HBox();
      VBox preferences = new VBox();
      VBox menubox = new VBox();
      
      
      //Criação de Botoes
      Button vertice = new Button("Vertices");
      Button arresta = new Button("Arestas");
      Button novo = new Button("New Graph");
      Button save = new Button("Save as SVG");
      Button det = new Button("Graph Details");
      Button sair = new Button("Exit");
      ColorPicker cp = new ColorPicker(Color.RED);
      
      
      //Mudar detalhes de sliders 
      Slider slider = new Slider();
      slider.setMin(3);
      slider.setMax(10);
      slider.setValue(3);
      slider.setBlockIncrement(0.2);
      
      Slider dots = new Slider();
      dots.setMin(0);
      dots.setMax(30);
      dots.setValue(0);
      dots.setBlockIncrement(1);


      //Pintura de fundo dos menus      
      pane.setBackground(new Background(new BackgroundFill(Color.rgb(245, 245, 230),null, null)));
      toolbar.setBackground(new Background(new BackgroundFill(Color.rgb(220, 220, 220),null, null)));
      preferences.setBackground(new Background(new BackgroundFill(Color.rgb(210, 210, 210),null, null)));
      menubox.setBackground(new Background(new BackgroundFill(Color.rgb(190, 190, 190),null, null)));
      

      
      //Configuração de tamanho dos menus
      cp.setMaxWidth(50);
      preferences.setMinHeight(110);
      preferences.setMaxHeight(110);
      preferences.setMinWidth(170);
      preferences.setMaxWidth(170);
      toolbar.setMaxWidth(180);
      toolbar.setMinWidth(180);

      toolbar.setPadding(new Insets(5));
      VBox.setMargin(selector,new Insets(4,0,5,15));
      VBox.setMargin(preferences,new Insets(0,0,100,0));
      selector.setSpacing(20);


      //Alinhar botoes do menu
      save.setMinSize(170, 20);
      novo.setMinSize(170, 20);
      det.setMinSize(170, 20);
      sair.setMinSize(170, 20);
      
      //Adição de itens no menu da esquerda
      toolbar.setTop(selector);
      toolbar.setLeft(preferences);
      toolbar.setBottom(menubox);
      

      //Adição de botoes no menu principal
      menubox.getChildren().add(novo);
      menubox.getChildren().add(det);
      menubox.getChildren().add(save);
      menubox.getChildren().add(sair);

      //Adição de botoes no menu de seleção
      selector.getChildren().add(vertice);
      selector.getChildren().add(arresta);     
      
      //Adição dos menus na tela principal 
      root.setCenter(pane);
      root.setLeft(toolbar);
      
      
      //Criação do programa
      Scene scene = new Scene(root, 600, 500);
      stage.setTitle("JavaFX Graph Editor");
      stage.setScene(scene);
      stage.show();
      
      //Ação para os botões
      vertice.setOnAction((ActionEvent event) -> {
          tool = 1;        
          preferences.getChildren().clear();
          preferences.setPadding(new Insets(8));
          preferences.getChildren().add(cp);
          preferences.getChildren().add(new Label("Espessura do contorno"));
          preferences.getChildren().add(slider);
      });

      arresta.setOnAction((ActionEvent event) -> {
          tool = 0;
          preferences.getChildren().clear();
          preferences.setPadding(new Insets(8));
          preferences.getChildren().add(cp);
          preferences.getChildren().add(new Label("Espessura da linha"));
          preferences.getChildren().add(slider);
          preferences.getChildren().add(new Label("Nível de pontilhado"));
          preferences.getChildren().add(dots);
      });
      
      novo.setOnAction((ActionEvent event) -> {
         ALL.reset();
         pane.getChildren().clear();
      });
      
      sair.setOnAction((ActionEvent event) ->{
         stage.close();
      });
      
      save.setOnAction((ActionEvent event) -> {
          
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Graph");
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                try { 
                    ALL.makeSVG(file);
                } catch (FileNotFoundException ex) {
                }
            }
      });
      
      det.setOnAction((ActionEvent event) -> {
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("Graph Details");
          alert.setHeaderText("Esses são os dados do grafo");
          int v = ALL.getGraphVerticesSize();
          int a = ALL.getGraphArestasSize();
          int c = ALL.checkCrosses();
          alert.setContentText(" Vertices: "+v+"\n Arestas: "+a+" \n Arestas Concorrentes: "+c+"\n\n");
          alert.showAndWait();
      });
      
      cp.setOnAction((ActionEvent t) -> {
          color = cp.getValue();
      });
      
      slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
          stroke = new_val.floatValue();
      });
       
      dots.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
          stroke2 = new_val.floatValue();
      });
      
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    
}
