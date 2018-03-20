-- Trabalho 1 Paradigmas da Programação
-- Natan Luiz Paetzhold Berwaldt
-- (14/03) 17/03

import Data.Char



--Q1

isVowel :: Char -> Bool
isVowel c = c `elem` "aeiouAEIOU"



--Q2

addComma :: [String] -> [String]
addComma strlist = map(++ ",") strlist



--Q3

htmlListItems :: [String] -> [String]
htmlListItems str = map p str
  where p x = "<LI>"++ x ++"</LI>"

htmlListItems2 :: [String] -> [String]
htmlListItems2 str = map(\n -> "<LI>"++ n ++"</LI>") str
  
  
  
--Q4
semVogais :: String -> String
semVogais str = filter(not.isVowel) str

semVogais2 :: String -> String
semVogais2 str = filter(\n ->not( n `elem` "aeiou")) str



--Q5

codifica :: String -> String
codifica str = map(\n ->  if n == ' ' then ' ' else '-')str

espaco :: Char -> Char
espaco x = if x == ' ' then ' ' else '-'

codifica2 :: String -> String
codifica2 str = map espaco str

--Q6

firstName :: String -> String
firstName str = takeWhile(/= ' ') str



--Q7

isInt  :: String -> Bool
isInt lst = length(filter(\n ->not (elem n "1234567890")) lst) <= 0

isInt2 :: String -> Bool
isInt2 lst = (length (filter (not.isDigit) lst)) <= 0

isInt3 :: String -> Bool
isInt3 lst = (length (filter isAlpha lst)) <= 0



--Q8

lastName :: String -> String
lastName str = last (words str)



--Q9

nickName :: String -> String
nickName str = [toLower(head(str))] ++ map toLower (lastName str)


--Q10
converter :: Char -> Char
converter c 
  | c == 'a' = '4'
  | c == 'e' = '3'
  | c == 'i' = '2'
  | c == 'o' = '1'
  | c == 'u' = '0'
  |otherwise = c

encodeName :: String -> String
encodeName str = map converter str



--Q11

betterConverter :: Char -> String
betterConverter c 
  | c == 'a' = ['4']
  | c == 'e' = ['3']
  | c == 'i' = ['1']
  | c == 'o' = ['0']
  | c == 'u' = "00"
  |otherwise = [c]


betterEncodeName :: String -> String
betterEncodeName str=  concatMap (betterConverter) str



--Q12

corrigeString :: [String] -> [String]
corrigeString lst = map (\x -> take(10) (x++".........")) lst



