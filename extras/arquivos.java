/* Natan Luiz Paetzhold Berwaldt
 * Arquivos
 * 
 * Aldo tem N arquivos em seu computador, cada um com um tamanho em bytes. Ele quer dividir estes arquivos em pastas, porÃ©m o sistema do computador Ã© velho e sÃ³ aceita pastas com as duas seguintes limitaÃ§Ãµes:
 * 
 *     Uma pasta pode ter no mÃ¡ximo dois arquivos
 *    A soma dos tamanhos dos arquivos na pasta nÃ£o pode exceder B bytes
 * 
 * Como ele tem muitos arquivos ele prefere nÃ£o criar muitas pastas. Dado o tamanho dos arquivos, calcule o nÃºmero mÃ­nimo possÃ­vel de pastas.
 * 
 * Vamos supor um exemplo que temos os arquivos de tamanho 1, 2 e 3, e que o limite de bytes seja 3. A soluÃ§Ã£o Ã© colocar os dois primeiros arquivos juntos, totalizando apenas 2 pastas.
 * Entrada
 * A entrada consiste de duas linhas. A primeira linha contÃ©m os nÃºmeros inteiros N e B. A segunda linha contÃ©m N inteiros indicando o tamanho de cada arquivo.
 * SaÃ­da
 * Seu programa deve escrever uma Ãºnica linha na saÃ­da, contendo um Ãºnico nÃºmero inteiro, a quantidade mÃ­nima possÃ­vel de pastas. 
 */

package arquivos;

import java.util.ArrayList;
import java.util.Arrays;


public class Arquivos {

    public static void main(String[] args) {
        Arquivos programa = new Arquivos();
        int a = 5;
        int b = 4;
        ArrayList lst = new ArrayList<>(Arrays.asList(4,3,1,2,2));
        System.out.print(programa.arquivos(a,b,lst));
    }
    public int arquivos(int num, int max, ArrayList arqs)
    {
        if (arqs.size() == num)
            return arqAux(max,max,arqs);
        else
            return -1;
    }

    public int arqAux(int m, int max, ArrayList arqs)
    {
        if(arqs.isEmpty()) return 0;
        if(arqs.contains(max))
            return 1 + arqAux(m,max,removeArq(m,max,arqs));
        else
            return arqAux(m,max-1,arqs);
    }

    public ArrayList removeArq(int m, int max, ArrayList arqs)
    {
	      return myRemove(verif(m-max, arqs),myRemove(max, arqs));
    }

    public int verif(int a, ArrayList arqs)
    {
        if(arqs.isEmpty() || a == 0) return 0;
	      if(arqs.contains(a))
            return a;
	      else
            return verif(a-1,arqs);
    }
        
    public ArrayList myRemove(int val, ArrayList lst)
    {
        if(lst.contains(val))
            return myRemove(val,lst.remove((Object)val)? lst:lst);
        else 
            return lst;
    }
        
}
