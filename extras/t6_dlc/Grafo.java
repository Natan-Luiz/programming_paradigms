package myplanarity;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

class Grafo{
   private int crosses;
   private List<Vertice> verts;
   public Grafo(){
      crosses = 0;
      verts = new ArrayList();
   }
   
   //obtem um vertice da lista de vertices
   public Vertice getVertice(int index){
       return verts.get(index);
   }
   
   //adiciona um vertice ao grafo
   public void addVertice(Circle c){
        Vertice v = new Vertice(c);
        this.verts.add(v);
   }
   
   //adiciona uma aresta ao grafo
   public void addAresta(Line l,Vertice v1, Vertice v2){
       Aresta a = new Aresta(v1,l);
       a.setEnd(v2);
       v1.getSaidas().add(a);
   }
   
   //muda a posição de um vertice e todas as arestas ligadas a ele
   public void changePosition(Vertice v, double x, double y){
       //muda posicao do vertice
        v.changePos(x, y);        
        //muda posicao das arestas que saem do vertice
        for(int i=0;i<v.NumArestas();i++)
            v.getAresta(i).changeStart(x,y);  
        
        //muda posicao das arestas que chegam ao vertice
        for(Vertice w: this.verts){
            for(int i=0;i < w.NumArestas();i++){
                if(w.getAresta(i).getEnd() == v){
                    w.getAresta(i).changeEnd(x,y);
                }
            }
        }
   }
   
   //checa o numero de intersecções do grafo
   public int checkCrosses(){
        crosses = 0;
        this.verts.forEach((v) -> {
             v.getSaidas().forEach((a) -> {
                 this.verts.forEach((w) -> {
                      w.getSaidas().forEach((ar) -> {
                            if(ar.getEnd() != v  &&  a.getEnd() != w  &&  ar.getEnd() != a.getEnd() && v != w){
                                if (Line2D.linesIntersect(a.getStartX(),a.getStartY(),a.getEndX(),a.getEndY(),ar.getStartX(),ar.getStartY(),ar.getEndX(),ar.getEndY())){
                                    crosses++;
                                }
                            }
                      });
                 });
             });
        });
        return crosses/2;
   }
   
   //verifica se ponto da tela representa um vertice
   public Vertice isVertice(double x, double y){
       if (this.isOk(x, y, -1) != -1)
           return(this.verts.get(this.isOk(x, y, -1)));
       else return null;
   }
   
   //verifica qual vertice representa aquela posição da tela
   public int isOk(double x, double y, int vert){
      if(verts.isEmpty()) return -1;
      int curr = 0;
      while(verts.size() != curr)
      {
          if(0 == verts.get(curr).isInto(x,y) || curr == vert)
            curr++;
          else return isReallyOk(curr,vert);
      }
      return -1;
   }
  
   private int isReallyOk(int ini, int end){
       if(end == -1) return ini;
       for(Aresta a: this.verts.get(ini).getSaidas()){        
           if(a.getEnd() == this.verts.get(end))
               return -1;
       }
       if(this.verts.get(end).getSaidas().isEmpty()) return ini;
       
       for(Aresta a: this.verts.get(end).getSaidas()){        
           if(a.getEnd() == this.verts.get(ini))
               return -1;
       }
       return ini;
   }
   
   
  //reseta o grafo
   public void reset(){
       this.verts.forEach((v) -> {
           v.getSaidas().clear();
       });
       this.verts.clear();
   }
   
   public int getGraphVerticesSize(){
       return this.verts.size();
   }
   public int getGraphArestasSize(){
       return this.verts.stream().map((v) -> v.getSaidas().size()).reduce(0, Integer::sum);
    }
}


class Vertice extends Circle{
   private List<Aresta> saidas;
   public Vertice(Circle c){
      this.setCenterX(c.getCenterX());
      this.setCenterY(c.getCenterY());
      this.setRadius(c.getRadius());
      this.setFill(c.getFill());
      this.setStroke(c.getStroke());
      this.setStrokeWidth(c.getStrokeWidth());
      saidas = new ArrayList();
   }
   
   //verifica se ponto esta dentro do circulo do vertice
   public int isInto(double x, double y){
      if(getCenterX() + getRadius() >= x && getCenterX() - getRadius() <= x)
         if(getCenterY() + getRadius() >= y && getCenterY() - getRadius() <= y)
            return 1;
      return 0;
   }
   
   
    public String getColorHex() {
        String a = this.getFill().toString();
        return "#"+ a.substring(2, 8);
    }
    
    //muda posicao do vertice
    public void changePos(double x, double y){
        setCenterX(x);
        setCenterY(y);
    }
    
    //obtem aresta especifica de um vertice
    public Aresta getAresta(int idx){
        return saidas.get(idx);
    }
    
    //obtem a lista de arestas de um vertice
    public List<Aresta> getSaidas(){
        return saidas;
    }
    
    public int NumArestas(){
        return saidas.size();
    }
}


class Aresta extends Line{
   private Vertice v2;
   public Aresta(Vertice ver, Line l){
      v2 = null;
      this.setStartX(ver.getCenterX());
      this.setStartY(ver.getCenterY());
      this.setStroke(l.getStroke());
      this.setStrokeWidth(l.getStrokeWidth());
      this.setStyle(l.getStyle());
   }
   
   public void changeEnd(Vertice ver){
      v2 = ver;
      this.setEndX(ver.getCenterX());
      this.setEndY(ver.getCenterY());
   }
   
   public Boolean isCompleted(){
       return v2 != null;
   }
   
   public String getColorHex() {
        String a = this.getStroke().toString();
        return "#"+ a.substring(2, 8);
   }
   
   public void setEnd(Vertice v){
       this.v2 = v;
   }
   
   public Vertice getEnd(){
       return this.v2;
   }
   
   public void changeStart(double x, double y){
       this.setStartX(x);
       this.setStartY(y);
   }
   
   public void changeEnd(double x, double y){
       this.setEndX(x);
       this.setEndY(y);
   }
}
