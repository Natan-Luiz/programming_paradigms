/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orientadoaobjetos;

/**
 *
 * @author André L. Rakowski
 */
public class Operacoes {

    private void deslizaControle(Dados dados, Integer inicio, Integer fim) {
        if (inicio <= fim) {
            for (int i = inicio; i < fim; i++) {
                dados.aparicoes[dados.barra[i]] += 1;
            }
        } else {
            for (int i = inicio; i > fim; i--) {
                dados.aparicoes[dados.barra[i]] += 1;
            }
        }
    }

    public void calculaAparicoes(Dados dados) {
        for (int i = 0; i < dados.m - 1; i++) {
            deslizaControle(dados, dados.sequencia[i] - 1, dados.sequencia[i + 1] - 1);
        }
        dados.aparicoes[dados.barra[dados.sequencia[dados.m - 2 + 1] - 1]] += 1;
    }

}
