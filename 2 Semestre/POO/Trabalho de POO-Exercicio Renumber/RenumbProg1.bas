10 gosub 100 
20 if aux < 100 goto 90 
30 input nome 
40 for i = 1 to 20 
50 print i 
60 next i 
70 if max > mult then goto 30 else goto 10 
80 gosub 100 
90 end 
100 print aux 
110 let aux = aux + 1 
120 return 
