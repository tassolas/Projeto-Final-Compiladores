import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.ResultSet;

public class Database {
    private static final String URL = "jdbc:sqlite:pixscript.db";

    public static Connection conectar() throws Exception {
        return DriverManager.getConnection(URL);
    }

    public static void criarTabelas() {
        try (Connection conn = conectar();
                Statement stmt = conn.createStatement()) {

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS codeinfo (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "filename VARCHAR(45)," +
                            "date DATE," +
                            "time TIME)");

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS symbols (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "lexeme VARCHAR(45)," +
                            "token VARCHAR(45)," +
                            "line INT," +
                            "column INT," +
                            "codeinfo_id INT)");

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS errorlog (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "description TEXT," +
                            "line INT," +
                            "column INT," +
                            "type VARCHAR(45)," +
                            "codeinfo_id INT)");

        } catch (Exception e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }

    public static int salvarCodeInfo(String filename) {
        int id = -1;

        try (Connection conn = conectar()) {
            String sql = "INSERT INTO codeinfo(filename, date, time) VALUES (?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, filename);
            stmt.setString(2, LocalDate.now().toString());
            stmt.setString(3, LocalTime.now().toString());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Erro ao salvar codeinfo: " + e.getMessage());
        }

        return id;
    }

    public static void salvarSimbolos(java.util.List<Symbol> symbols, int codeinfoId) {
        try (Connection conn = conectar()) {
            String sql = "INSERT INTO symbols(lexeme, token, line, column, codeinfo_id) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            for (Symbol s : symbols) {
                stmt.setString(1, s.getLexeme());
                stmt.setString(2, s.getToken());
                stmt.setInt(3, s.getLine());
                stmt.setInt(4, s.getColumn());
                stmt.setInt(5, codeinfoId);

                stmt.executeUpdate();
            }

            System.out.println("Tabela de símbolos salva no banco!");

        } catch (Exception e) {
            System.out.println("Erro ao salvar símbolos: " + e.getMessage());
        }
    }

public static void salvarErros(java.util.List<Erro> errors, int codeinfoId) {
    try (Connection conn = conectar()) {
        String sql = "INSERT INTO errorlog(description, line, column, type, codeinfo_id) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);

        for (Erro e : errors) {
            stmt.setString(1, e.getText());
            stmt.setInt(2, e.getLine());
            stmt.setInt(3, e.getColumn());
            stmt.setString(4, e.getType());
            stmt.setInt(5, codeinfoId);

            stmt.executeUpdate();
        }

        System.out.println("Log de erros salvo no banco!");

    } catch (Exception e) {
        System.out.println("Erro ao salvar log de erros: " + e.getMessage());
    }
}

}