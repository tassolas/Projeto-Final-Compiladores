public class Symbol {
    private String lexeme;
    private String token;
    private int line;
    private int column;

    public Symbol(String lexeme, String token, int line, int column) {
        this.lexeme = lexeme;
        this.token = token;
        this.line = line;
        this.column = column;
    }

    public void print() {
        System.out.println(
                "Lexema: " + lexeme +
                        " | Token: " + token +
                        " | Linha: " + line +
                        " | Coluna: " + column);
    }

    public String getLexeme() {
        return lexeme;
    }

    public String getToken() {
        return token;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

}