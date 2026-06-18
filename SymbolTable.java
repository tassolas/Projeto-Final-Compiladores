import java.util.ArrayList;
import java.util.List;

public class SymbolTable {
    private List<Symbol> symbols;

    public SymbolTable() {
        this.symbols = new ArrayList<Symbol>();
    }

    public void addSymbol(String lexeme, String token, int line, int column) {
        for (Symbol s : symbols) {
            if (s.getLexeme().equals(lexeme)) {
                return;
            }
        }

        symbols.add(new Symbol(lexeme, token, line, column));
    }

    public void printTable() {
        System.out.println("\nTABELA DE SÍMBOLOS:");
        for (Symbol s : symbols) {
            s.print();
        }
    }

    public List<Symbol> getSymbols() {
    return symbols;
}
}