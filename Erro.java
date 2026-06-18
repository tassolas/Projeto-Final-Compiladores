public class Erro {
    private int line;
    private int column;
    private String type;
    private String text;

    public Erro(int line, int column, String type, String text) {
        this.line = line;
        this.column = column;
        this.type = type;
        this.text = text;
    }

    public void print() {
        String aux = "[" + type + "] Erro na linha " + line + " e coluna " + column + ": ";

        if (this.text == null)
            aux += "Erro desconhecido";
        else
            aux += this.text;

        System.out.println(aux);
    }

    public String getType() {
        return type;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String getText() {
        return text;
    }

}