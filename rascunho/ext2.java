//ext2

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofre;

import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.*;
import java.util.stream.IntStream;
import java.util.List;
import java.util.OptionalInt;

/**
 *
 * @author Rafael Vales
 */
public class Cofre {

     interface triFunction <A, B, C, R> { 
        public R apply (A a, B b, C c);
    }
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in); 
        int n = sc.nextInt();
        int m = sc.nextInt();
        int iterator = 0;
        
        IntStream.Builder b = IntStream.builder();
        IntStream.Builder s = IntStream.builder();
        
        while(iterator != n){
            int temp = sc.nextInt();
            b.accept(temp);
            iterator++;
        }  
        
        IntStream barra = b.build();        
        iterator = 0;
         
        while(iterator != m){
            int temp = sc.nextInt();
            s.accept(temp);
            iterator++;
        }

        IntStream sec = s.build();
        iterator = 0;
        
        while(iterator < 10){
            s.accept(0);
            iterator++;
        }
        
        IntStream ret = s.build();
        
    }
    
    
    public static IntStream combinacao(int vI, int n, IntStream barra, IntStream seq, IntStream ret)
    {
        
        if(n >= vI){return combinacao(vI,n-1,barra,seq.skip(1), fazMove(0,mai(seq),barra),ret )}
            
        else if(n > 1){}
                
        else{return ret;}
        
        Function<IntStream,IntStream>poeNoRet = (lst) -> lst;
        
        triFunction<Integer,Integer,IntStream,IntStream>somas = (pos1,pos2,lst) -> poeNoRet.apply((lst.limit(pos2).skip(pos1)));
        
        BiFunction<IntStream,IntStream,IntStream>mySoma = (s1,s2) -> s1;
        
        return lst;
    }
    
    public static IntStream myZipWithJaQueNessaPorraDeJavaNaoTem(IntStream s1, IntStream s2)
    {
        if ((s1.findFirst()).isPresent()){
            return (((s1.findFirst()).getAsInt()) + (s2.findFirst()).getAsInt());
        }
        else return s1;
    }
}
