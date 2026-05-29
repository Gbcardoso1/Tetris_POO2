Tetris Java

Projeto desenvolvido em Java utilizando Swing para implementação do jogo Tetris em modo single-player.

O projeto foi construído de forma incremental, seguindo três versões de desenvolvimento (v0.1, v0.2 e v1.0), adicionando funcionalidades progressivamente até a versão final completa.

Funcionalidades
Mecânicas do jogo
Movimentação de peças
Rotação de peças
Detecção de colisão
Fixação das peças no tabuleiro
Limpeza de linhas
Sistema de Game Over
Sistema de pontuação
Sistema de níveis
Velocidade progressiva
Interface
Interface gráfica utilizando Swing
Preview da próxima peça
Tela inicial
Tela de ranking
Botão de restart
Persistência
Salvamento de ranking em JSON
Salvamento do estado do jogo
Carregamento de jogo salvo
Ranking Top 10
Qualidade do projeto
Testes unitários com JUnit 5
Documentação Javadoc
Organização por pacotes
Arquivos .jar executáveis
Tecnologias Utilizadas
Java
Swing
Gson
JUnit 5
Estrutura do Projeto
tetris_poo2/
│
├── data/
│   ├── scores.json
│   └── savegame.json
│
├── docs/
│
├── lib/
│
├── release/
│   ├── v0.1/
│   ├── v0.2/
│   └── v1.0/
│
├── src/
│   └── tetris/
│
├── tests/
│
└── README.md
Como Executar
Pela IDE

Execute a classe:

Main.java
Pelo terminal
Versão final
java -jar release/v1.0/tetris.jar
Outras versões
java -jar release/v0.1/tetris.jar
java -jar release/v0.2/tetris.jar
Versões do Projeto
v0.1

Primeira versão funcional do jogo.

Implementações
Interface gráfica com Swing
Movimentação das peças
Rotação
Detecção de colisão
Fixação no tabuleiro
Game Over
v0.2

Adição de melhorias de jogabilidade e persistência de ranking.

Implementações
Threads
Sistema de níveis
Velocidade progressiva
Preview da próxima peça
Ranking Top 10
Salvamento em JSON
Restart do jogo
v1.0

Versão final do projeto.

Implementações
Save/Load do jogo
Tela inicial
Tela de ranking
Persistência completa
Nome do jogador
Testes unitários
Documentação Javadoc
Testes

Os testes unitários foram implementados utilizando JUnit 5.

Para executar:

java -cp "bin;lib/*" org.junit.platform.console.ConsoleLauncher --scan-class-path
Javadoc

A documentação do projeto está disponível na pasta:

docs/

Abra:

docs/index.html

em um navegador.
Gabriel Cardoso

