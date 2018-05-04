{-  
  Medalhas

https://olimpiada.ic.unicamp.br/pratique/p1/2016/f2/medalhas/

A natação foi um dos esportes mais emocionantes das Olimpíadas do Rio. Houve até uma prova na qual três atletas chegaram empatados, cada um recebendo uma medalha de prata! Normalmente, porém, os três primeiros colocados terminam a prova em tempos distintos e, portanto, temos a distribuição mais comum de medalhas: o nadador que terminou no menor tempo recebe medalha de ouro; o nadador que terminou com o segundo menor tempo recebe medalha de prata; e o que terminou com o terceiro menor tempo recebe medalha de bronze. Neste problema, dados os três tempos distintos de finalização da prova, dos três nadadores que ganharam medalhas, seu programa deve dizer quem ganhou medalha de ouro, quem ganhou prata e quem ganhou bronze.
Entrada
A primeira linha da entrada contém um inteiro T1, indicando o tempo em que o nadador 1 terminou a prova. A segunda linha da entrada contém um inteiro T2, indicando o tempo de finalização do nadador 2. Por fim, a terceira linha da entrada contém um inteiro T3, indicando o tempo em que o nadador 3 terminou a prova.
Saída
Seu programa deve imprimir três linhas na saída. A primeira linha deve conter o número do nadador que ganhou medalha de ouro; a segunda linha, o número do nadador que ganhou prata; e a terceira linha, o número do nadador que levou bronze.
-}


import Data.List

medalhas :: Int -> Int -> Int -> [Int]
medalhas x1 x2 x3 = a [x1,x2,x3] (sort [x1,x2,x3])


a :: [Int] -> [Int] -> [Int]
a [] _ = []
a (x:xs) lstord = (b x lstord):(a xs lstord)

b :: Int -> [Int] -> Int
b x lst = length (takeWhile (/= x) lst) + 1
