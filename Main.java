import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {
    public static ListError listError = new ListError();
    public static SymbolTable symbolTable = new SymbolTable();
    public static Node derivationTree = null;

    public static void main(String[] args) {
        String arquivoEntrada = "input/exemplo.pix";
        Database.criarTabelas();
        int codeinfoId = Database.salvarCodeInfo(arquivoEntrada);
        System.out.println("Iniciando análise do arquivo: " + arquivoEntrada);

        try {
            // 1ª passagem: análise léxica (busca de erro léxico)
            Lexer lexerLexico = new Lexer(
                    new InputStreamReader(
                            new FileInputStream(arquivoEntrada),
                            StandardCharsets.UTF_8));

            while (lexerLexico.next_token().sym != sym.EOF) {
            }

            // 2ª PASSAGEM: análise sintática (busca de erro sintático)
            Lexer lexerParser = new Lexer(
                    new InputStreamReader(
                            new FileInputStream(arquivoEntrada),
                            StandardCharsets.UTF_8));

            Parser parser = new Parser(lexerParser);

            parser.parse();

            if (Main.derivationTree != null) {
                System.out.println("\nÁRVORE DE DERIVAÇÃO:");
                Main.derivationTree.print("");
            }

            if (Main.derivationTree != null) {
                System.out.println("\nÁRVORE DE DERIVAÇÃO:");
                Main.derivationTree.print("");

                GraphvizGenerator.generate(
                        Main.derivationTree,
                        "arvore.dot");
            }

        } catch (Exception e) {
            if (!listError.hasErrorType("SINTATICO")) {
                Main.listError.defineError(
                        0,
                        0,
                        "SINTATICO",
                        e.getMessage());
            }
        }

        if (listError.hasErrors()) {
            System.out.println("Código PIX Script inválido!");
            listError.logErrors();
        } else {
            System.out.println("Código PIX Script válido!");
        }
        Main.symbolTable.printTable();

        Database.salvarSimbolos(Main.symbolTable.getSymbols(), codeinfoId);

        Database.salvarErros(Main.listError.getErrors(), codeinfoId);

    }
}