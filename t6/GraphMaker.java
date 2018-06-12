
package myplanarity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

//Classe de criação de grafo planar
public final class GraphMaker {
    Grafo ALL;
    public GraphMaker(int verts, Grafo g){
        ALL = g;
        int cont = 3;
        newTriangle();
        while(verts >= cont){
            makeTriangle(cont);
            cont++;
        }
    }
    
    //para cada vertice apos os tres primeiros, liga com os dois ultimos vertices
    public void makeTriangle(int cont){
        ALL.addVertice(newCircle());
        
        ALL.addAresta( newLine(), ALL.getVertice(cont-1), ALL.getVertice(cont-2));
        ALL.addAresta( newLine(), ALL.getVertice(cont-1), ALL.getVertice(cont-3));
        
    }
    
    //cria os primeiros 3 vertices do grafo e liga eles
    public void newTriangle(){
        ALL.addVertice(newCircle());
        ALL.addVertice(newCircle());
        ALL.addVertice(newCircle());
        
        ALL.addAresta( newLine(), ALL.getVertice(0), ALL.getVertice(1));
        ALL.addAresta( newLine(), ALL.getVertice(1), ALL.getVertice(2));
        ALL.addAresta( newLine(), ALL.getVertice(2), ALL.getVertice(0));
    }
    
    //gera novo circulo
    public Circle newCircle(){
        Circle c = new Circle(0,0,12,Color.DARKRED);
        
        c.setStrokeWidth(5);
        c.setStroke(Color.DARKRED);
        
        return c;
    }
    
    //gera nova linha
    public Line newLine(){
        Line l = new Line(0,0,0,0);
        
        l.setStrokeWidth(3);
        l.setStroke(Color.CORAL);
        
        return l;
    }
}
