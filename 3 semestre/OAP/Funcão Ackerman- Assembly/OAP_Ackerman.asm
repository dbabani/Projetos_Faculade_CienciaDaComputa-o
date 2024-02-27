.data
strInicio: 	.asciiz "Programa Ackermann\nDigite os parametros m e n para calcular A(m, n) ou -1 para abortar a execucao\n"
strResAbre: 	.asciiz  "A("
strResVirg: 	.asciiz ","
strResFec: 	.asciiz ") = "
strEncerra:	.asciiz "\n\nExecucao encerrada"
valorM:		.word 0
valorN:		.word 0
resultado:	.word 0

# -----------------------------------------------------------------#

.text 
	.globl	main			# void main()
	
main:
	
	la		$s0, valorM			# carrega endereco valorM
	la		$s1, valorN			# carrega endereco valorN
	la		$s2, resultado			# carrega endereco resultado
	addiu		$s3, $s3, 1			# $s3 = 1 (valor 1 auxiliar para o caso m > 0 && n == 0)
	
	la 		$a0, strInicio			# $a0 <- &strIicio
	li		$v0, 4				# $v0 <- printString
	syscall						# chama SO
	
	li		$v0, 5				# $v0 <- ler int
	syscall						# chama SO para ler m
	
	move		$t0, $v0			# $t0 <- $v0 (valorM)
	blt		$t0, $zero, fimExecucao		# if (m < 0) fim execucao
	sw		$t0, 0($s0)			# 0($s0) <-  $t0 (valorM)
	li		$v0, 5				# $v0 <- ler int
	syscall						# chama SO para ler N
	
	move		$t1, $v0			# $t1 <- $v0 (valorN)
	sw 		$t1, 0($s1)			# 0($s1) <- $t1 (valorN)
	
	addiu		$sp, $sp, -12			# libera espaco na pilha
	sw		$t0, 8($sp)			# 8($sp) <- $t0 (valorM)
	sw		$t1, 4($sp)			# 4($sp) <- $t1 (valorN)
	sw		$ra, 0($sp)			# 0($sp) <- $ra 
	jal		Ackermann			# vai para funcao
	
	#apos retorno
	lw		$ra, 0($sp)			# recupera $ra
	lw		$t1, 4($sp)			# recupera ValorN
	lw		$t0, 8($sp)			# recupera ValorM
	addiu		$sp, $sp, 12			# recupera espaco da pilha
	j		printResultado			# imprime resultado

# Verifica se valorM == 0
Ackermann:
	lw		$a0, 8($sp)			# $a0 = valorM
	bne		$a0, $zero, AckermannRec	# if(m != 0) proximo label
	lw		$t0, 4($sp)			# $t0 = valorN
	addiu		$a0, $t0, 1			# $t0 = valorN +1
	sw		$a0, 0($s2)			# resultado = valorN +1
	jr		$ra 				# retorna

		
# Verifica se valorN == 0	
AckermannRec:	
	lw		$a0, 4($sp)			# $a0 = valorN armazenado
	bne		$a0, $zero, AckermannDuplo	# if(n != 0) proximo label
	
	lw		$t0, 8($sp)			# $t0 <- valorM armazenado
	addiu		$sp, $sp, -12			# libera espaco na pilha
	
	addiu		$t0, $t0, -1			# $t0 = $t0 -1
	sw		$t0, 8($sp)			# 8($sp) <- $t0 (valorM -1)
	sw		$s3, 4($sp)			# 4($sp) <- 1
	sw		$ra, 0($sp)			# 0($sp) <- $ra 
	jal		Ackermann			# vai para funcao

	lw		$ra, 0($sp)			# recupera $ra
	lw		$t1, 4($sp)			# recupera valorN
	lw		$t0, 8($sp)			# recupera valorM
	addiu		$sp, $sp, 12 			# recupera espaco da pilha
	
	jr		$ra				# retorna
	

# valorM > 0 e valorN > 0, (realiza recursao dentro da recursao)
AckermannDuplo:
	lw		$t0, 8($sp)			# $t0 = valorM armazenado
	lw		$t1, 4($sp)			# $t1 = valorN armazenado
	addiu		$sp, $sp, -12			# libera espaco na pilha
	addiu   	$t1, $t1, -1			# $t1 = $t1 - 1 (valorN -1)
	sw		$t0, 8($sp)			# 8($sp) <- $t0 (valorM)
	sw		$t1, 4($sp)			# 4($sp) <- $t1 (valorN -1)
	sw		$ra, 0($sp)			# 0($sp) <- $ra	
	jal		Ackermann			# vai para funcao
	
	# apos retorno
	lw		$ra, 0($sp)			# recupera $ra
	lw		$t1, 4($sp)			# recupera valorN -1
	lw		$t0, 8($sp)			# recupera valorM
	addiu		$sp, $sp, 12 			# recupera espaco da pilha
	
	addiu		$t0, $t0, -1			# $t0 = valorM -1
	lw		$t1, 0($s2)			# $t1 = resultado atual
	addiu		$sp, $sp, -12			# libera espaco na pilha
	sw		$t0, 8($sp)			# 8($sp) <- $t0 (valorM -1)
	sw		$t1, 4($sp)			# 4($sp) <- $t1 (resultado)
	sw		$ra, 0($sp)			# 0($sp) <- $ra
	
	jal		Ackermann			# vai para funcao
	
	lw		$ra, 0($sp)			# recupera $ra
	lw		$t1, 4($sp)			# recupera resultado anterior
	lw		$t0, 8($sp)			# recupera valorM -1
	addiu		$sp, $sp, 12 			# recupera espaco da pilha
	
	jr		$ra				# retorna


printResultado:
	la 		$a0, strResAbre			# $a0 <- &strResAbre	
	li		$v0, 4				# $v0 <- 4 (print string)
	syscall						# chama SO
	
	lw		$a0, 0($s0)			# $a0 <- &valorM
	li		$v0, 1				# $v0 <- 1 (print int)
	syscall						# chama SO
	
	la 		$a0, strResVirg			# $a0 <- &strResVirg
	li		$v0, 4				# $v0 <- 4 (print string)
	syscall						# chama SO
	
	lw 		$a0, 0($s1)			# $a0 <- &valorN
	li		$v0, 1				# $v0 <- 1 (print int)
	syscall						# chama SO
	
	la 		$a0, strResFec			# $a0 <- &strResFec
	li		$v0, 4				# $v0 <- 4 (print string)	
	syscall						# chama SO
	
	lw 		$a0, 0($s2)			# $a0 <- &resultado
	li		$v0, 1				# $v0 <- 1 (print int)
	syscall						# chama SO

fimExecucao:
	la 		$a0, strEncerra			# $a0 <- &strEncerra
	li		$v0, 4				# $v0 <- 4 (print string)
	syscall						# chama SO
	
	li		$v0, 10				# $vo <- 10 (encerra execucao)
	syscall						# chama SO
	
	
