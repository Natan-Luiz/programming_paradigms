-- Paradigmas da Programação
-- Trabalho 2 Parte 2

--Q1

isBin :: String -> Bool
isBin "" = False
isBin (x:xs)
  | (x == '1' || x == '0') && xs == "" = True
  | x == '1' || x == '0' = isBin xs
  |otherwise = False
  
  
--Q2
  
isBin2 :: String -> Bool
isBin2 "" = False
isBin2 str = length(filter(\x -> not( elem x "01")) str) <= 0

--Q3

auxBin2Dec :: [Int] -> Int -> Int
auxBin2Dec [] _ = 0
auxBin2Dec (x:xs) exp  
  | x == 0 = (auxBin2Dec xs (exp-1))
  | otherwise = 2^exp + (auxBin2Dec xs (exp-1))


bin2dec :: [Int] -> Int
bin2dec [] = undefined
bin2dec bits = auxBin2Dec bits ((length bits)-1)


--Q4

bin2dec' :: [Int] -> Int
bin2dec' [] = undefined
bin2dec' bits = auxBin2Dec' bits (length bits)
  
auxBin2Dec' :: [Int] -> Int -> Int
auxBin2Dec' [] _ = 0
auxBin2Dec' lst exp = sum (zipWith (calc) lst [exp-1, exp-2..0])
  where calc x y = x*(2^y)


--Q5

dec2bin :: Int -> [Int]
dec2bin 0 = []
dec2bin num = reverse (dec num)
  
dec :: Int -> [Int]
dec 0 = []
dec x = (if x `mod` 2 == 0 then 0 else 1): dec (div x 2)

--Q6

isHex :: String -> Bool
isHex "" = False
isHex str = length(filter hex str) <= 0
  where hex x = not( elem x "ABCDEFabcdef1234567890")
 
