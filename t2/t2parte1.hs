-- Paradigmas da Programação
-- Trabalho 2 parte 1 -- Lista 4

--Q1

geraTabela :: Int -> [(Int,Int)]
geraTabela n = if n > 0
  then (n,n^2):geraTabela(n-1)
  else []



--Q2

contido :: String -> Char -> Bool
contido "" c = False
contido (h:t) c = if c == h
  then True
  else contido t c


--Q3

translate :: [(Float,Float)] -> [(Float, Float)]
translate [] = []
translate (x:xs) = soma x : translate xs
  where soma (a,b) = (a+2,b+2) 


--Q4

geracao :: Int -> Int -> [(Int,Int)]
geracao m n = if n >= m
  then (m,m^2):geracao (m+1) n
  else []

geraTabela2 :: Int -> [(Int,Int)]
geraTabela2 0 = []
geraTabela2 n = geracao 1 n
