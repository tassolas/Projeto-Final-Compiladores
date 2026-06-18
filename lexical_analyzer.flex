import java_cup.runtime.Symbol;

%%

%class Lexer
%unicode
%cup
%line
%column

%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline + 1, yycolumn + 1);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline + 1, yycolumn + 1, value);
    }

    private void addTabela(String token) {
        Main.symbolTable.addSymbol(
            yytext(),
            token,
            yyline + 1,
            yycolumn + 1
        );
    }
%}

LETRA = [a-zA-Z_]
DIGITO = [0-9]
ID = {LETRA}({LETRA}|{DIGITO})*
NUMERO = {DIGITO}+("."{DIGITO}+)?
STRING = \'([^\'\\]|\\.)*\'|\"([^\"\\]|\\.)*\"
ESPACO = [ \t\r\n]+

%%

{ESPACO}               { }

"LEDGER"               { addTabela("LEDGER"); return symbol(sym.LEDGER, yytext()); }
"CLOSE"                { addTabela("CLOSE"); return symbol(sym.CLOSE, yytext()); }
"LET"                  { addTabela("LET"); return symbol(sym.LET, yytext()); }
"IF"                   { addTabela("IF"); return symbol(sym.IF, yytext()); }
"TRUE"                 { addTabela("TRUE"); return symbol(sym.TRUE, yytext()); }
"FALSE"                { addTabela("FALSE"); return symbol(sym.FALSE, yytext()); }

"$>"                   { addTabela("SAIDA_TEXTO"); return symbol(sym.SAIDA_TEXTO, yytext()); }

"<-"                   { addTabela("ATRIBUICAO"); return symbol(sym.ATRIBUICAO, yytext()); }

"++"                   { addTabela("SOMA"); return symbol(sym.SOMA, yytext()); }
"--"                   { addTabela("SUBTRACAO"); return symbol(sym.SUBTRACAO, yytext()); }
"**"                   { addTabela("MULTIPLICACAO"); return symbol(sym.MULTIPLICACAO, yytext()); }
"//"                   { addTabela("DIVISAO"); return symbol(sym.DIVISAO, yytext()); }
"%%"                   { addTabela("RESTO"); return symbol(sym.RESTO, yytext()); }

"=="                   { addTabela("IGUAL"); return symbol(sym.IGUAL, yytext()); }
"!="                   { addTabela("DIFERENTE"); return symbol(sym.DIFERENTE, yytext()); }
">>"                   { addTabela("MAIOR"); return symbol(sym.MAIOR, yytext()); }
"<<"                   { addTabela("MENOR"); return symbol(sym.MENOR, yytext()); }
">="                   { addTabela("MAIOR_IGUAL"); return symbol(sym.MAIOR_IGUAL, yytext()); }
"<="                   { addTabela("MENOR_IGUAL"); return symbol(sym.MENOR_IGUAL, yytext()); }

"&&"                   { addTabela("AND"); return symbol(sym.AND, yytext()); }
"||"                   { addTabela("OR"); return symbol(sym.OR, yytext()); }
"!!"                   { addTabela("NOT"); return symbol(sym.NOT, yytext()); }

"::"                   { addTabela("ELSE"); return symbol(sym.ELSE, yytext()); }

"{"                    { addTabela("DELIMITADOR"); return symbol(sym.ABRE_CHAVE, yytext()); }
"}"                    { addTabela("DELIMITADOR"); return symbol(sym.FECHA_CHAVE, yytext()); }
"("                    { addTabela("DELIMITADOR"); return symbol(sym.ABRE_PARENTESE, yytext()); }
")"                    { addTabela("DELIMITADOR"); return symbol(sym.FECHA_PARENTESE, yytext()); }

"$"{ID}                { addTabela("VAR_DECIMAL"); return symbol(sym.VAR_DECIMAL, yytext()); }
"#"{ID}                { addTabela("VAR_INTEIRO"); return symbol(sym.VAR_INTEIRO, yytext()); }
"@"{ID}                { addTabela("VAR_TEXTO"); return symbol(sym.VAR_TEXTO, yytext()); }
"?"{ID}                { addTabela("VAR_BOOLEANO"); return symbol(sym.VAR_BOOLEANO, yytext()); }
"!"{ID}                { addTabela("VAR_CHAVE_PIX"); return symbol(sym.VAR_CHAVE_PIX, yytext()); }
"~"{ID}                { addTabela("VAR_NULO"); return symbol(sym.VAR_NULO, yytext()); }

{NUMERO}               { addTabela("NUMERO"); return symbol(sym.NUMERO, yytext()); }
{STRING}               { addTabela("STRING"); return symbol(sym.STRING, yytext()); }
{ID}                   { addTabela("IDENTIFICADOR"); return symbol(sym.IDENTIFICADOR, yytext()); }

. {
    Main.listError.defineError(
        yyline + 1,
        yycolumn + 1,
        "LEXICO",
        "Símbolo inválido: " + yytext()
    );
}