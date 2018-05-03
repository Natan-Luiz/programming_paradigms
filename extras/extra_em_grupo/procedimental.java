/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procedimental;

import java.util.*;

/**
 *
 * @author Rafael Vales
 */
public class Cofre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        Integer[] aparicoes = new Integer[10];
        for (int i = 0; i < 10; i++) {
            aparicoes[i] = 0;
        }

        Integer[] barra = new Integer[n];
        for (int i = 0; i < n; i++) {
            barra[i] = in.nextInt();
        }

        Integer[] sequencia = new Integer[m];
        for (int i = 0; i < m; i++) {
            sequencia[i] = in.nextInt();
        }

        calculaAparicoes(aparicoes, barra, sequencia, m);

        for (int i = 0; i < 10; i++) {
            System.out.print(aparicoes[i] + " ");
        }
        System.out.println();
    }

    private static void calculaAparicoes(Integer[] aparicoes, Integer[] barra, Integer[] sequencia, int m) {
        for (int i = 0; i < m - 1; i++) {
            deslizaControle(aparicoes, barra, sequencia[i] - 1, sequencia[i + 1] - 1);
        }
        aparicoes[barra[sequencia[m - 2 + 1] - 1]] += 1;
    }

    private static void deslizaControle(Integer[] aparicoes, Integer[] barra, int ini, int fim) {
        if (ini <= fim) {
            for (int i = ini; i < fim; i++) {
                aparicoes[barra[i]] += 1;
            }
        } else {
            for (int i = ini; i > fim; i--) {
                aparicoes[barra[i]] += 1;
            }
        }
    }

}
