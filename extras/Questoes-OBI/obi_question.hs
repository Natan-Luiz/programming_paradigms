{-
  Resolucao de 2 questoes da OBI - CARTAS e MONTANHAS
  
  Ambas em:
  
  https://olimpiada.ic.unicamp.br/static/extras/obi2017/provas/ProvaOBI2017_f2pj.pdf
  
-}

-- CARTAS

cartas :: [Int] -> Int
cartas lst
  |head lst == last lst = (lst !! 1)
  |otherwise = cartasComp lst

cartasComp :: [Int] -> Int
cartasComp lst = if head lst == (lst !! 1) then last lst else head lst

-- MONTANHAS

-- Primeira implementacao, com TRUE / FALSE
montanhas :: Int -> [Int] -> Bool
montanhas n lst = length(filter (==(maximum lst)) lst) > 1


-- Segunda impleentacao, com 'S' / 'N'

montanhasAgain :: Int -> [Int] -> Char
montanhasAgain n lst = if peaks lst > 1 then 'S' else 'N'
  where peaks lst = length(filter (==(maximum lst)) lst)
