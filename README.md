# PROJETO FINAL – COMPILADORES

## PIX Script Compiler

Lucas Gabriel da Silva
Rafael Gomes de Menezes
Tassio Silva de Moraes

Compiladores - Engenharia da Computação

### Professor

Rodrigo de Sousa Gomide

# 1. Objetivo do Projeto

Este projeto tem como objetivo implementar um compilador para a linguagem PIX Script utilizando as ferramentas JFlex e JCup.

O compilador é capaz de:

* Realizar análise léxica;
* Realizar análise sintática;
* Detectar erros léxicos;
* Detectar erros sintáticos;
* Construir uma tabela de símbolos;
* Persistir informações em banco de dados SQLite;
* Construir uma árvore de derivação;
* Gerar representação gráfica da árvore através do Graphviz.

---

# 2. Tecnologias Utilizadas

| Tecnologia  | Finalidade                  |
| ----------- | --------------------------- |
| Java 8      | Linguagem principal         |
| JFlex 1.9.1 | Analisador Léxico           |
| JCup 11b    | Analisador Sintático        |
| SQLite      | Persistência dos dados      |
| Graphviz    | Geração da árvore sintática |
| VS Code     | Ambiente de desenvolvimento |

---

# 3. Estrutura do Projeto

Projeto Final - Compiladores

├── bin

├── input

│   └── exemplo.pix

├── lib

│   ├── jflex-full-1.9.1.jar

│   ├── java-cup-11b.jar

│   └── sqlite-jdbc.jar

├── src

│   ├── lexical_analyzer.flex

│   ├── glc.cup

│   ├── Main.java

│   ├── Database.java

│   ├── Symbol.java

│   ├── SymbolTable.java

│   ├── Node.java

│   ├── GraphvizGenerator.java

│   ├── Erro.java

│   ├── ListError.java

│   ├── Parser.java

│   ├── sym.java

│   └── Lexer.java

├── pixscript.db

├── arvore.dot

└── arvore.png

---

# 4. Linguagem PIX Script

Exemplo de código utilizado para testes:

LEDGER transferencia

LET @nome <- 'Denecley Alvim'
LET @produto <- 'Placa Nvidia 5070RTX'
LET $valor <- 4999.99
LET !pix <- "soinformatica@gmail.com"

IF (!pix != "") {
   $> 'Realizar transferência'
}
:: {
   $> 'Aguardando chave PIX'
}

CLOSE

## Recursos da Linguagem

- Declaração de variáveis (`LET`);
- Variáveis de texto, decimal, inteiro, booleano, chave PIX e nulo;
- Estruturas condicionais (`IF` e `::`);
- Operadores aritméticos;
- Operadores relacionais;
- Operadores lógicos;
- Comando de saída (`$>`);
- Delimitação de início (`LEDGER`) e fim (`CLOSE`) do programa.

---

# 5. Análise Léxica

A análise léxica foi implementada utilizando JFlex.

O arquivo responsável é:

src/lexical_analyzer.flex

Funções principais:

* Reconhecimento de palavras reservadas;
* Reconhecimento de variáveis;
* Reconhecimento de números;
* Reconhecimento de strings;
* Reconhecimento de operadores;
* Registro de erros léxicos.

Exemplo de erro:

[LEXICO] Erro na linha 5 e coluna 2:
Símbolo inválido: %

---

# 6. Análise Sintática

A análise sintática foi implementada utilizando JCup.

Arquivo responsável:

src/glc.cup

A gramática define:

* Programa
* Comandos
* Declarações
* Atribuições
* Expressões
* Condições
* Estruturas IF/ELSE

Exemplo de erro:

[SINTATICO] Erro na linha 6 e coluna 23:
Token inesperado

---

# 7. Tabela de Símbolos

Durante a análise léxica, todos os tokens identificados são armazenados em uma tabela de símbolos.

Informações registradas:

* Lexema
* Token
* Linha
* Coluna

Exemplo:

Lexema: @nome

Token: VAR_TEXTO

Linha: 2

Coluna: 6

---

# 8. Banco de Dados SQLite

O sistema utiliza SQLite para persistência dos dados.

Banco:

pixscript.db

Tabelas:

## codeinfo

Armazena cada execução do compilador.

## symbols

Armazena os símbolos identificados.

## errorlog

Armazena os erros encontrados.

---

# 9. Árvore de Derivação

A árvore sintática é construída durante a análise sintática.

Classe responsável:

Node.java

Após o processamento:

Main.derivationTree

A árvore é exibida em formato textual no terminal.

---

# 10. Graphviz

Após a geração da árvore sintática, é criado:

arvore.dot

Em seguida:

arvore.png

Classe responsável:

GraphvizGenerator.java

O Graphviz converte o arquivo DOT para imagem.

---

# 11. Como Compilar

## Passo 1 – Gerar o Lexer

java -jar lib\jflex-full-1.9.1.jar src\lexical_analyzer.flex

## Passo 2 – Gerar o Parser

java -cp "lib\java-cup-11b.jar" java_cup.Main -parser Parser -symbols sym -destdir src src\glc.cup

## Passo 3 – Compilar o Projeto

javac -cp "lib\java-cup-11b.jar;lib\sqlite-jdbc.jar" -d bin src\*.java

## Passo 4 – Executar

java -cp "bin;lib\java-cup-11b.jar;lib\sqlite-jdbc.jar" Main

## Passo 5 – Gerar Árvore PNG

dot -Tpng arvore.dot -o arvore.png

---

# 12. Como Testar

Editar o arquivo:

input/exemplo.pix

Inserir um código PIX Script válido ou inválido.

Executar novamente o compilador.

O sistema irá:

* Validar o código;
* Mostrar erros encontrados;
* Gerar tabela de símbolos;
* Salvar informações no banco;
* Gerar árvore sintática.

---

# 13. Resultados Obtidos

O compilador desenvolvido foi capaz de:

✓ Realizar análise léxica;

✓ Realizar análise sintática;

✓ Identificar erros léxicos;

✓ Identificar erros sintáticos;

✓ Gerar tabela de símbolos;

✓ Persistir informações em SQLite;

✓ Construir árvore sintática;

✓ Gerar visualização gráfica utilizando Graphviz.

---

# 14. Conclusão

O projeto permitiu aplicar os conceitos fundamentais de compiladores, incluindo análise léxica, análise sintática, gramáticas livres de contexto, tabelas de símbolos, tratamento de erros e geração de árvores sintáticas.

A integração entre JFlex e JCup possibilitou a construção de um compilador funcional para a linguagem PIX Script, demonstrando na prática os conceitos estudados na disciplina.
