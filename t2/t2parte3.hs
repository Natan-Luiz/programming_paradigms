-- Paradigmas da Programação
-- Trabalho 2 Parte 3

import Data.Char


isEanOk :: String -> Bool
isEanOk str = if length str == 13 then verifica str ((length str)-1) else False

verifica :: String -> Int -> Bool
verifica str t = if 10 - ((calcula str t) `mod` 10) == digitToInt(last str) then True else False

calcula :: String -> Int -> Int
calcula "" _ = 0
calcula (x:xs) t
  | t == 0 = 0
  | t `mod` 2 == 0 = (digitToInt x) + calcula xs (t-1)
  | otherwise = (3 * (digitToInt x) + calcula xs (t-1))
