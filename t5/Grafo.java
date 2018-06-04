import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

class Grafo{
   private int crosses;
   public List<Vertice> verts;
   public Grafo(){
      crosses = 0;
      verts = new ArrayList();
   }
   
   public Vertice addVertice(Circle c){
        Vertice v = new Vertice(c);
        this.verts.add(v);
        return v;
   }
   
   public Aresta addAresta(Line l){
       Vertice v = this.isVertice(l.getStartX(), l.getStartY());
       if(v==null) {
           return null;
       }
       Aresta a = new Aresta(v,l);
       v.saidas.add(a);
       return a;
   }
   
   public int checkCrosses(){
       crosses = 0;
        this.verts.forEach((v) -> {
          v.saidas.forEach((a) -> {
             this.verts.forEach((w) -> {
               if(v != w){ 
                  w.saidas.forEach((ar) -> {
                      if (a != ar){
                         Shape intersect = Shape.intersect(a, ar);
                         if (intersect.getBoundsInLocal().getWidth() != -1)
                            if(ar.getEnd() != v && a.getEnd() != w && ar.getEnd() != a.getEnd())
                               crosses++;
                      }
                  });
               }
             });
           });
        });
        return crosses/2;
   }
   
   public Vertice isVertice(double x, double y){
       if (this.isOk(x, y, -1) != -1)
          return(this.verts.get(this.isOk(x, y, -1)));
       else return null;
   }
   
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
       for(Aresta a: this.verts.get(ini).saidas){        
           if(a.getEnd() == this.verts.get(end))
               return -1;
       }
       if(this.verts.get(end).saidas.isEmpty()) return ini;
       
       for(Aresta a: this.verts.get(end).saidas){        
           if(a.getEnd() == this.verts.get(ini))
               return -1;
       }
       return ini;
   }
   
   public int makeSVG(File file) throws FileNotFoundException{
      PrintWriter pw = new PrintWriter(file);
            
            pw.println("<svg height=\"" + 500 + "\" width=\"" + 600 + "\">");
            this.verts.forEach((v) -> {
                v.saidas.forEach((a) -> {
                    if(a.getStrokeDashArray().isEmpty())
                        pw.println("<line x1=\""+a.getStartX()+"\" y1=\""+a.getStartY()+"\" x2=\""+a.getEndX()+"\" y2=\""+a.getEndY()+"\" stroke-dasharray=\" 0 \" style=\"stroke:"+a.getColorHex()+";stroke-width:"+(a.getStrokeWidth()*1.3)+"\" />");
                    else
                        pw.println("<line x1=\""+a.getStartX()+"\" y1=\""+a.getStartY()+"\" x2=\""+a.getEndX()+"\" y2=\""+a.getEndY()+"\" stroke-dasharray=\" "+ a.getStrokeDashArray().get(0) +" "+ a.getStrokeDashArray().get(0) +"\" style=\"stroke:"+a.getColorHex()+";stroke-width:"+(a.getStrokeWidth()*1.3)+"\" />");
                });
           });
            
            this.verts.forEach((v) -> {
                pw.println("  <circle cx=\"" + v.getCenterX() + "\" cy=\"" + v.getCenterY()  + "\" r=\"" + v.getRadius() + "\" stroke=\"black\" stroke-width=\""+v.getStrokeWidth()+"\" fill=\"" + v.getColorHex() + "\" />");
            });
            pw.println("</svg>");
                        
            pw.close();
       return 0;
   }
   
   public void reset(){
       this.verts.forEach((v) -> {
           v.saidas.clear();
       });
       this.verts.clear();
   }
   
   public int getGraphVerticesSize(){
       return this.verts.size();
   }
   public int getGraphArestasSize(){
       return this.verts.stream().map((v) -> v.saidas.size()).reduce(0, Integer::sum);
    }
}


class Vertice extends Circle{
   public List<Aresta> saidas;
   public Vertice(Circle c){
      this.setCenterX(c.getCenterX());
      this.setCenterY(c.getCenterY());
      this.setRadius(c.getRadius());
      this.setFill(c.getFill());
      this.setStroke(c.getStroke());
      this.setStrokeWidth(c.getStrokeWidth());
      saidas = new ArrayList();
   }
   
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
}
