--Autores do Trabalho:Dhruv Babani e Anderson Sprenger
import System.IO
import System.Random

-- Função para limpar a tela
limparTela :: IO ()
limparTela = do
  putStr "\ESC[2J" -- Limpa o terminal
  hFlush stdout   -- Limpa o buffer de saída

-- Função para verificar se uma letra está presente na palavra
verificarLetra :: String -> Char -> Bool
verificarLetra palavra letra = letra `elem` palavra

-- Função para exibir a palavra com as letras corretas adivinhadas
exibirPalavra :: String -> [Char] -> String
exibirPalavra palavra letras =
  [if c `elem` letras then c else '_' | c <- palavra]

-- Função para ler palavras de um arquivo texto
lerPalavras :: FilePath -> IO [String]
lerPalavras arquivo = do
  conteudo <- readFile arquivo
  return (lines conteudo)

-- Função para selecionar uma palavra aleatória
selecionarPalavra :: [String] -> IO String
selecionarPalavra palavras = do
  indice <- randomRIO (0, length palavras - 1)
  return (palavras !! indice)

-- Função para verificar se o jogador está próximo da vitória
proximoDaVitoria :: String -> [Char] -> Bool
proximoDaVitoria palavra letras =
  let palavraAtual = exibirPalavra palavra letras
      letrasRestantes = length (filter (=='_') palavraAtual)
  in letrasRestantes <= 2

-- Função principal do jogo
jogoForca :: String -> [Char] -> Int -> IO ()
jogoForca palavra letras tentativas = do
  limparTela
  putStrLn $ "Palavra: " ++ exibirPalavra palavra letras
  putStrLn $ "Tentativas restantes: " ++ show tentativas
  putStrLn "Digite uma letra: "
  hFlush stdout
  letra <- getChar
  let novaLetras = letra : letras
  if verificarLetra palavra letra 
    then do
      putStrLn "Letra correta!"
      if all (\c -> c `elem` novaLetras) palavra
        then do
          putStrLn "Parabéns, você ganhou!"
          putStrLn $ "A palavra correta é: " ++ palavra
          continuar <- jogarNovamente
          if continuar
            then reiniciarJogo
            else return ()
        else jogoForca palavra novaLetras tentativas 
    else do
      putStrLn "Letra incorreta!"
      if tentativas == 1 
        then do
          putStrLn "Você perdeu!"
          putStrLn $ "A palavra era: " ++ palavra
          continuar <- jogarNovamente
          if continuar
            then reiniciarJogo
            else return ()
        else jogoForca palavra novaLetras (tentativas - 1) 

-- Função para atualizar a palavra com base nas letras corretas da palavra anterior
atualizarPalavra :: String -> String -> [Char] -> String
atualizarPalavra palavraAtual novaPalavra letras =
  zipWith atualizar letraAtual letraNova
  where
    atualizar '_' novaLetra = novaLetra
    atualizar letra _ = letra
    letraAtual = palavraAtual
    letraNova = exibirPalavra novaPalavra letras

-- Função para perguntar ao jogador se ele deseja jogar novamente
jogarNovamente :: IO Bool
jogarNovamente = do
  putStrLn "Deseja jogar novamente? (s/n)"
  hFlush stdout
  resposta <- getChar
  case resposta of
    's' -> return True
    'n' -> return False
    _ -> jogarNovamente

-- Função para reiniciar o jogo
reiniciarJogo :: IO ()
reiniciarJogo = do
  palavras <- lerPalavras "dicionario.txt" 
  palavra <- selecionarPalavra palavras
  jogoForca palavra [] 10

-- Função principal
main :: IO ()
main = do
  palavras <- lerPalavras "dicionario.txt"
  palavra <- selecionarPalavra palavras  -- Defina aqui a palavra atual
  let letras = []  -- Defina aqui as letras já adivinhadas
  let tentativas = 10  -- Defina aqui o número de tentativas
  let novaPalavra = "NovaPalavra" -- Defina aqui a nova palavra sorteada (caso necessário)
  let novaPalavraAtualizada = atualizarPalavra (exibirPalavra palavra letras) novaPalavra letras

  if proximoDaVitoria palavra letras
    then do
      chance <- randomRIO (1 :: Int, 10)
      if chance <= 3
        then jogoForca novaPalavraAtualizada letras tentativas
        else jogoForca palavra letras tentativas
    else jogoForca palavra letras tentativas
