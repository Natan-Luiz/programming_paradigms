/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orientadoaobjetos;

import java.util.Scanner;

/**
 *
 * @author André L. Rakowski
 */
public class Cofre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int m = sc.nextInt();
            Dados dados = new Dados(n, m);
            dados.leDados(dados);
            Operacoes op = new Operacoes();
            op.calculaAparicoes(dados);
            dados.imprimeDados(dados);
        } catch (Exception ex) {
            System.out.println("Erro!");
        }
    }

}
