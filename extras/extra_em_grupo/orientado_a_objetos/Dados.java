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
public class Dados {

    int n;
    int m;
    Integer[] barra;
    Integer[] sequencia;
    Integer[] aparicoes = new Integer[10];

    public Dados(Integer val_n, Integer val_m) {
        n = val_n;
        m = val_m;
        barra = new Integer[n];
        sequencia = new Integer[m];
        for (int i = 0; i < 10; i++) {
            aparicoes[i] = 0;
        }
    }

    public void leDados(Dados dados) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            dados.barra[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            dados.sequencia[i] = sc.nextInt();
        }
    }

    public void imprimeDados(Dados dados) {
        for (int i = 0; i < 10; i++) {
            System.out.print(dados.aparicoes[i] + " ");
        }
    }
}
