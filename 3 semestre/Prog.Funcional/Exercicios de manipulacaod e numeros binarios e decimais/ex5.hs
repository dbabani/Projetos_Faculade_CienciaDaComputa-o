--Funcao que define o numero de bits e o numero decimal que retorna o numemro binario--

invertBits :: [Int] -> [Int]
invertBits = map (\ x -> if x == 0 then 1 else 0)




dec2bin :: Int -> Int -> [Int]
dec2bin _ 0 = []
dec2bin 0 n = replicate n 0
dec2bin m n = dec2bin (div m 2) (n-1) ++ [mod m 2]


--Funcao que define o numero de bits e o numero decimal que retorna o numemro binario em complemento de dois--
dec2bincomp :: Int -> Int -> [Int]
dec2bincomp _ 0 = []
dec2bincomp 0 n =  replicate n 1
dec2bincomp m n = let complemento = dec2bin (2^n-1-m) n
                  in if length complemento < n
                     then  replicate (n - length complemento)   1 ++  complemento
                     else  complemento
                     
dec2bincompWithInvertedBits :: Int -> Int -> [Int]
dec2bincompWithInvertedBits m n = invertBits (dec2bincomp m n)






  --Autores do Trabalho: Dhruv Babani,Anderson Sprenger--                   