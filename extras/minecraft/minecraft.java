import java.util.ArrayList;

abstract class Bloco {
  public String name;
  public String action(){
    return "Não obteve nada";
  }
  public String action(Pa p){
    return "Não obteve nada";
  }
  public String action(Enxada e){
    return "Não obteve nada";
  }
  public String action(Machado m){
    return "Não obteve nada";
  }
  public String action(Picareta p){
    return "Não obteve nada";
  }
  public String toString(){
    return name;
  }
}

class Terra extends Bloco{
  public Terra()
  {
    name = "Terra";
  }
  public String action(Pa p){
    return "Obteve um pouco de Terra";
  }
}

class Grama extends Bloco{
  public Grama()
  {
    name = "Grama";
  }
  public String action(Enxada e){
    return "Obteve um pouco de Grama";
  }
}

class Madeira extends Bloco{
  public Madeira()
  {
    name = "Madeira";
  }
  public String action(Machado m){
    return "Obteve um pedaço de Madeira";
  }
}

class Pedra extends Bloco{
  public Pedra()
  {
    name = "Pedra";
  }
  public String action(Picareta m){
    return "Obteve um pouco de Pedregulho";
  }
}

abstract class Tool{
    public String name;
    public String toString(){
      return name;
    }
}

class Pa extends Tool{
  public Pa(){
    name = "Pa";
  }
}

class Enxada extends Tool{
  public Enxada(){
    name = "Enxada";
  }
}

class Picareta extends Tool{
  public Picareta(){
    name = "Picareta";
  }
}

class Machado extends Tool{
  public Machado(){
    name = "Machado";
  }
}

class Mao extends Tool{
  public Mao(){
    name = "Mao";
  }
}


class Main {
  public static void main(String[] args) {
    Bloco b[] = {new Terra(), new Terra(), new Madeira(), new Pedra(), new Grama(), new Grama()};

  //  Pa i = new Pa();
    Machado i = new Machado();
  //  Mao i = new Mao();
  //  Picareta i = new Picareta();
  //  Enxada i = new Enxada();

    for (Bloco t : b)
      System.out.println("  "+i+" Usado em "+t+" e "+t.action(i)+".");
    }

}
