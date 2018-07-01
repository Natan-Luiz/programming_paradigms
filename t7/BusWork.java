
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class BusWork {
    private Boolean Connected;   // Flag de conexão ao server;
    private Boolean Online;      // Flag de dados disponíveis;
    private HttpJSONService http;
    public List<BusWorkData> frota = new ArrayList<>();
    
    BusWork(){
         Connected = false;
         Online = true;
         http = new HttpJSONService();
    }
    
    public List<BusWorkData> getFrota(){
        return frota;
    }
    
    public Boolean isConnected(){
        return Connected;
    }
    
    public Boolean isAble(){
        return Online;
    }
    
    public void able(){
        Online = true;
    }
    
    public void disAble(){
        Online = false;
    }
    
    public void atualizar() throws Exception{
        Reader r = new Reader();
        Map json = null;
        frota.clear();
        try {
            json = http.sendGet("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterTodasPosicoes");
            disAble();
            for(Object line: (List)json.get("DATA")){
                    frota.add(new BusWorkData((List)line));
            }
            Connected = true;         
        } catch (Exception ex) {
            Connected = false;
            json = r.offReader("1.json");
            disAble();
            for(Object line: (List)json.get("DATA")){
                frota.add(new BusWorkData((List)line));
            }
        }
        able();
        return;
    }
    
    public int numDeBus(){
        return frota.size();
    }
    
    public String ultimaData(){
        return frota.get(numDeBus()-1).getDatahora();
    }
    
    public String maisRecente(){
        BusWorkData bus = frota.get(0);
        for(BusWorkData data : frota){
            if(data.isBefore(bus))
                bus = data;
        }   
        return bus.getDatahora();
    }
    
    public String menosRecente(){
        BusWorkData bus = frota.get(0);
        for(BusWorkData data : frota){
            if(bus.isBefore(data))
                bus = data;
        }   
        return bus.getDatahora();
    }
    
    public int moving(){
        int cont = 0;
        for(BusWorkData bus : frota){
            if(Float.valueOf(bus.getVelocidade()) > 0)
                cont++;
        } 
        return cont;
    }
    
    public List BusLines(){
        List<Tupla> lst = new ArrayList<>();
        
        if(frota.size() > 0)
            lst.add(new Tupla(frota.get(0).getLinha(),0));
        else{
            lst.add(new Tupla("",0));
            return lst;
        }
        
        for(int i = 0; i < numDeBus(); i++){
            if(frota.get(i).getLinha().compareTo("") == 0 
                    || frota.get(i).getVelocidade() == "0.0") 
                continue;
            int j;
            for(j = 0; j< lst.size(); j++){
                    if(lst.get(j).linha.compareTo(frota.get(i).getLinha()) == 0){
                        break;
                    }
            }
            if(j < lst.size()){
                lst.get(j).numero++;
            }else{
                lst.add(new Tupla(frota.get(i).getLinha(),1));}
        }
        
        return lst;
    }
    
}



class HttpJSONService {
  
  private final String USER_AGENT = "Mozilla/5.0";
  private JSONParsing engine = new JSONParsing();
  
  // HTTP GET request
  public Map sendGet(String url) throws Exception {
        
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    
    con.setRequestMethod("GET");
    con.setRequestProperty("User-Agent", USER_AGENT);
    
    int responseCode = con.getResponseCode();
    
    BufferedReader in = new BufferedReader(
      new InputStreamReader(con.getInputStream()));
    StringBuffer response = new StringBuffer();
    String inputLine;
    
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();

    // Parse JSON result
    JSONParsing engine = new JSONParsing();
    return engine.parseJSON(response.toString());
  }
  
}


class JSONParsing {
  
  private ScriptEngine engine;
  
  public JSONParsing() {
    ScriptEngineManager sem = new ScriptEngineManager();
    this.engine = sem.getEngineByName("javascript");
  }
  
  public Map parseJSON(String json) throws IOException, ScriptException {
    String script = "Java.asJSONCompatible(" + json + ")";
    Object result = this.engine.eval(script);
    Map contents = (Map) result;
    return contents;
  }
}

class Reader {
    private String dados;
    
    public Reader(){
        dados = "";
    }
    
    public Map offReader(String link) throws Exception{

        File file = new File (link);
        BufferedReader in = new BufferedReader( new InputStreamReader(new FileInputStream(file)));
        StringBuffer response = new StringBuffer();
        String inputLine;
    
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONParsing engine = new JSONParsing();
    return engine.parseJSON(response.toString());
    }
}