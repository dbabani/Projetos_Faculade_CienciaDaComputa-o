10 gosub 50
13 if aux < 100 goto 43
14 input nome
22 for i = 1 to 20
25 print i
30 next i
37 if max > mult then goto 14 else goto 10
39 gosub 50
43 end
50 print aux
55 let aux = aux + 1
60 return