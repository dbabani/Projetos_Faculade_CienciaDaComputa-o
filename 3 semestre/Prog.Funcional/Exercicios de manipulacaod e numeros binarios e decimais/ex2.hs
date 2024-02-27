--1)Funcao recursiva que recebe um valor decimal(que nao seja negativo)
--2)Converta em numero BINARIO
dec2bin :: Int -> [Int]
dec2bin 0 = [0]
dec2bin 1 = [1]
dec2bin n
    | n `mod` 2 == 0 = dec2bin (n `div` 2) ++ [0]
    | otherwise      = dec2bin (n `div` 2) ++ [1]



--Autores do Trabalho: Dhruv Babani,Anderson Sprenger--