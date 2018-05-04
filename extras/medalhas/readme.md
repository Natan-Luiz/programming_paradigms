# EXTRA 3

Este extra consiste na implementação de 2 algoritmos para solucionar um mesmo problema [MEDALHAS-OBI](
https://olimpiada.ic.unicamp.br/pratique/p1/2016/f2/medalhas/), usando os 2 paradigmas estudados, possibilitando notar assim algumas similaridades entre eles.

Para começar tentei utilizar da mesma entrada e saída para ambos, entrando 3 valores separadamente e retornando a ordem em uma lista.

As implementações usaram de certa forma os mesmos passos para a solução:

* Ordenar os dados de entrada em uma lista
* Verificar a posição de um dos dados de entrada na lista ordenada
* Retornar a lista resultante no passo anterior

Nota-se que a operação *nth1* em prolog simplificou uma parte que pode ser considerada mais feia em haskell,

> nth1(H,SORTED,H1),

Parte na qual tive que usar *length* e *takeWhile* para efetuar a mesma operação.

>getPos x lst = length (takeWhile (/= x) lst) + 1

Uma grande semelhança entre esses dois algoritmos simples se da por conta de que em ambos é encontrada a operação *sort*, um bom símbolo de linguagens alto-nível, evitando que tenhamos que implementar mais esta operação que talvez pudesse tornar-se um pouco trabalhosa com a falta de conhecimento/experiencia no uso da linguagem.
