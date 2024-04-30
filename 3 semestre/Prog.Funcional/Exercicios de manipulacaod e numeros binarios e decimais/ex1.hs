--1)Funcao recursiva que recebe um valor binario(que nao seja negativo--
--2)Converta em numero decimal
bin2dec :: [Int] -> Int 
bin2dec [] = 0
bin2dec (x:xs) = (if x == 1 then 2 ^ length xs else 0) + bin2dec xs
--Autores do Trabalho: Dhruv Babani,Anderson Sprenger--



