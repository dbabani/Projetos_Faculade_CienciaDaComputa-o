--Converter um numero binario de complemento de dois para numero inteiro Decimal--
bin2dec :: [Int] -> Int
bin2dec [] = 0
bin2dec (x:xs) = (if x == 1 then 2 ^ length xs else 0) + bin2dec xs

invertBits :: [Int] -> [Int]
invertBits = map (\ x -> if x == 0 then 1 else 0)


bincomp2dec :: [Int] -> Int
bincomp2dec [] = 0
bincomp2dec (x:xs)
    | x == 0 = bin2dec xs
    | x == 1 = (-1) * (bin2dec (invertBits xs)+1)

    


    --Autores do Trabalho: Dhruv Babani,Anderson Sprenger--










