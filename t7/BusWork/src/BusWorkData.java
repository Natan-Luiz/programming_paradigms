
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author natan
 */
public class BusWorkData {
    private final SimpleStringProperty datahora;
    private final SimpleStringProperty ordem;
    private final SimpleStringProperty linha;
    private final SimpleStringProperty latitude;
    private final SimpleStringProperty longitude;
    private final SimpleStringProperty velocidade;
    
    public BusWorkData(List line){
        this.datahora = new SimpleStringProperty((String)line.get(0));
        this.ordem = new SimpleStringProperty(line.get(1).toString());
        this.linha = new SimpleStringProperty(line.get(2).toString());
        this.latitude = new SimpleStringProperty(line.get(3).toString());
        this.longitude = new SimpleStringProperty(line.get(4).toString());
        this.velocidade = new SimpleStringProperty(line.get(5).toString());
    }

    
    public Boolean isBefore(BusWorkData b){
        SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
       
        try {
            Date d1 = data.parse(this.getDatahora());
            Date d2 = data.parse(b.getDatahora());
            if(d1.before(d2))
                return true;
            return false;

        } catch (ParseException ex) {
            Logger.getLogger(BusWorkData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    //PROPERTY GET
    public SimpleStringProperty DataProperty(){
        return datahora;
    }
    
    public SimpleStringProperty OrdemProperty(){
        return ordem;
    }
    
    public SimpleStringProperty LinhaProperty(){
        return linha;
    }
    
    public SimpleStringProperty LatitudeProperty(){
        return latitude;
    }
    
    public SimpleStringProperty LongitudeProperty(){
        return longitude;
    }
    
    public SimpleStringProperty VelocidadeProperty(){
        return velocidade;
    }
    
    
    //GET
    public String getDatahora() {
        return datahora.get();
    }

    public String getOrdem() {
        return ordem.get();
    }

    public String getLinha() {
        return linha.get();
    }

    public String getLatitude() {
        return latitude.get();
    }

    public String getLongitude() {
        return longitude.get();
    }

    public String getVelocidade() {
        return velocidade.get();
    }
    
    
    
    //SET
    public void setDatahora(String s) {
        this.datahora.set(s);
    }

    public void setOrdem(String s) {
        this.ordem.set(s);
    }

    public void setLinha(String s) {
        this.linha.set(s);
    }

    public void setLatitude(String s) {
        this.latitude.set(s);
    }

    public void setLongitude(String s) {
        this.longitude.set(s);
    }

    public void setVelocidade(String s) {
        this.velocidade.set(s);
    }
    
    
}