
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;



public class BusWorkView extends Application {
    private final HBox bp = new HBox();
    private final ObservableList<BusWorkData> data = FXCollections.observableArrayList();
    private final BusWork control = new BusWork();

   public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage){
        background();
        stage.setScene(new Scene(bp,1000,650));
        stage.setResizable(false);
        
        stage.show();
    }
    
    public void background(){
        TableView tb = table();
        VBox vb = new VBox();
        HBox btn = bottons();

        vb.getChildren().add(tb);        
        vb.getChildren().add(btn);
        
        bp.getChildren().add(vb);
        bp.getChildren().add(lateralMenu());
    }
    
    public VBox lateralMenu(){
        VBox vb = new VBox();
        
        Label l = new Label("Detalhes:");
        l.setTranslateX(30);
        l.setMinHeight(40);
        l.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 16));
        vb.getChildren().add(l);
        
        Label info = new Label(" Numero de Veículos:\n Ultima leitura de dados:\n Leitura mais recente em: \n Leitura mais antiga em: ");
        vb.getChildren().add(info);
        info.setTranslateX(30);
        PieChart pie = new PieChart();
        
        Label voide = new Label("   ");
        
        vb.getChildren().add(pie);
        vb.getChildren().add(voide);
        
        vb.setMinSize(440,600);
        
        return vb;
    }
    
    public BarChart makeGraph(){
        CategoryAxis bus = new CategoryAxis();
        NumberAxis num = new NumberAxis();
        BarChart<String,Number> b = new BarChart<String,Number>(bus,num);
        
        List<Tupla> l = control.BusLines();
        
        XYChart.Series series = new XYChart.Series();
       
        
        for(int i = 0; i < l.size(); i++){
            series.getData().add(new XYChart.Data(l.get(i).linha,l.get(i).numero));
        }
        
        b.setTitle("Onibus por Linha");
        bus.setLabel("Linha");       
        
        series.setName("Numero de Onibus");
        
        b.getData().add(series);
        
        b.setTranslateX(-10);
        
        return b;
    }
    
    public void refresh(){
        VBox vb = (VBox) bp.getChildren().get(1);
        
        Label info = new Label(" Numero de Veículos:        "+ control.numDeBus() +"\n Ultima leitura de dados:   "+ control.ultimaData() +"\n Leitura mais recente em: "+ control.maisRecente() +" \n Leitura mais antiga em:   "+ control.menosRecente() +" ");

        info.setTranslateX(30);
        
                ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                    new PieChart.Data("Veículos em Movimento " + control.moving()* 100 / control.numDeBus() +"%", control.moving()),
                    new PieChart.Data("Veículos Parados "+ (100-control.moving()* 100 / control.numDeBus()) + "%", control.numDeBus() - control.moving()));
                
        vb.getChildren().set(1, info);
                
        PieChart pie = new PieChart(pieChartData);
        pie.setLabelsVisible(false);
        
        pie.setMaxHeight(150);
        pie.setLegendSide(Side.LEFT);
        
        vb.getChildren().set(2, pie);
        
        
        BarChart b = makeGraph();      
        vb.getChildren().set(3, b);
    }
    
    public TableView table(){
        TableView<BusWorkData> tb = new TableView<BusWorkData>();
        
        TableColumn<BusWorkData,String> col1 = new TableColumn<BusWorkData,String>("DataHora");
        TableColumn<BusWorkData,String> col2 = new TableColumn<BusWorkData,String>("Ordem");
        TableColumn<BusWorkData,String> col3 = new TableColumn<BusWorkData,String>("Linha");
        TableColumn<BusWorkData,String> col4 = new TableColumn<BusWorkData,String>("Latitude");
        TableColumn<BusWorkData,String> col5 = new TableColumn<BusWorkData,String>("Longitude");
        TableColumn<BusWorkData,String> col6 = new TableColumn<BusWorkData,String>("Velocidade");

            col1.setCellValueFactory(cellData -> cellData.getValue().DataProperty());
            col2.setCellValueFactory(cellData -> cellData.getValue().OrdemProperty());
            col3.setCellValueFactory(cellData -> cellData.getValue().LinhaProperty());
            col4.setCellValueFactory(cellData -> cellData.getValue().LatitudeProperty());
            col5.setCellValueFactory(cellData -> cellData.getValue().LongitudeProperty());
            col6.setCellValueFactory(cellData -> cellData.getValue().VelocidadeProperty());

            col1.setMinWidth(148);
            col2.setMaxWidth(75);
            col3.setMaxWidth(75);
            col4.setMinWidth(92);
            col5.setMinWidth(92);
            col6.setMinWidth(100);
           
            tb.getColumns().add(col1);
            tb.getColumns().add(col2);
            tb.getColumns().add(col3);
            tb.getColumns().add(col4);
            tb.getColumns().add(col5);
            tb.getColumns().add(col6);
            tb.setItems(data);
            
        tb.setMinSize(585, 600);
        tb.setMaxSize(585, 600);
            
        return tb;
    }
    
    public HBox bottons(){
         HBox hb = new HBox();
         Button btn = new Button("Buscar Dados");
         
         btn.setOnAction((ActionEvent event) -> {
             try {                                    
                data.clear();
                control.atualizar();   
                if(control.isAble()){
                    for(BusWorkData line : control.getFrota())
                        data.add(line);
                        
                    if(!control.isConnected()){
                        semConexão();
                    }
                    refresh();
                }
             } catch (Exception ex) {
                 alertador();
             }
         });
         
         
         hb.getChildren().add(btn);
         hb.setMinHeight(50);
         hb.setTranslateY(5);
         hb.setTranslateX(5);
         hb.setAlignment(Pos.TOP_LEFT);
         return hb;
    }
    
    public void alertador(){
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("VISH");
          alert.setHeaderText("VISHHH");
          alert.setContentText("Vishh, algo deu ruim!");
          alert.showAndWait();
    }
    
    public void semConexão(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setTitle("Falha na Conexão");
          alert.setHeaderText("FALHA NA CONEXÃO");
          alert.setContentText("Não foi possivel conectar-se com o servidor \nos dados serão buscados no banco local.");
          alert.show();
    }
}