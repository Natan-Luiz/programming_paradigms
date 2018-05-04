-- Questao Postes-OBI
-- https://olimpiada.ic.unicamp.br/static/extras/obi2017/provas/ProvaOBI2017_f3pu.pdf

{--
Postes
Nome do arquivo: postes.c, postes.cpp, postes.pas, postes.java, postes.js, postes.py2 ou
postes.py3
Seu João é proprietário de uma enorme fazenda, protegida por uma cerca formada por postes de
madeira e arame farpado. Cada poste da cerca tem 1 metro de altura. Os postes são colocados
separados dois metros um dos outros, ao redor de toda a fazendo, e portanto muitos postes são
utilizados.
Infelizmente um incêndio destruiu uma grande parte dos postes da cerca. Alguns postes, mesmo
um pouco queimados, ainda podem ser utilizados, desde que sejam reforçados. Outros estão irremediavelmente
inutilizados e devem ser substituídos por postes novos.
O engenheiro que trabalha para o Seu João percorreu toda a cerca e fez uma lista dos tamanhos de
cada poste depois do incêndio. O engenheiro determinou que, se o poste tem menos do que 50 cm,
ele deve ser substituído. Se o poste tem ao menos 50 cm, mas menos do que 85 cm, ele deve ser
consertado. Se o poste tem 85 cm ou mais, ele não necessita conserto e pode ser usado normalmente.
Dada a lista com os tamanhos de cada poste, você deve escrever um programa para determinar o
número de postes que devem ser substituídos e o número de postes que devem ser reforçados para
consertar a cerca da fazenda do Seu João.
Entrada
A primeira linha da entrada contém um inteiro N, indicando o número de postes da cerca. A
segunda linha contém N números inteiros Xi
, indicando os tamanhos dos postes após o incêndio.
Saída
Seu programa deve produzir uma única linha, contendo dois inteiros: o número de postes que devem
ser substituídos, seguido do número de postes que devem ser consertados.
-}

postes :: Int -> [Int] -> [Int]
postes n lst = subs lst: [cons lst]

subs :: [Int] -> Int
subs lst = length(filter (<50) lst)

cons :: [Int] -> Int
cons lst = length(filter (<85)(filter (>=50) lst))
