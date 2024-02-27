dec2bincomp :: Int -> [Int]
dec2bincomp 1 = [0,1]
dec2bincomp 0 = [0,0]
dec2bincomp x
    | x > 0 = 0 : dec2bin x
    | otherwise = 1 : invertBits (dec2bin ((x*(-1))-1))

-- converte um número decimal positivo para binário
dec2bin :: Int -> [Int]
dec2bin 0 = [0]
dec2bin 1 = [1]
dec2bin n
    | n `mod` 2 == 0 = dec2bin (n `div` 2) ++ [0]
    | otherwise      = dec2bin (n `div` 2) ++ [1]

-- inverte todos os bits do número binário
invertBits :: [Int] -> [Int]
invertBits = map (\ x -> if x == 0 then 1 else 0)

-- adiciona 1 ao número binário
addOne :: [Int] -> [Int]
addOne []     = [1]
addOne (x:xs) = case x of
                  0 -> 1 : xs
                  1 -> 0 : addOne xs
--Conversao de numero binario complemento de dois para decimal
bin2dec :: [Int] -> Int
bin2dec [] = 0
bin2dec (x:xs) = (if x == 1 then 2 ^ length xs else 0) + bin2dec xs


--Subtracao de dois numeros binarios--
subtrairbin :: [Int] -> [Int] -> [Int]
subtrairbin x y = dec2bincomp(bin2dec x - bin2dec y)



--Autores do Trabalho: Dhruv Babani,Anderson Sprenger--
