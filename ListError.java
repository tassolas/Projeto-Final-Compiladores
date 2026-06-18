import java.util.ArrayList;
import java.util.List;

public class ListError {
    private List<Erro> errors;

    public ListError() {
        this.errors = new ArrayList<Erro>();
    }

    public void defineError(int line, int column, String type, String text) {
        this.errors.add(new Erro(line, column, type, text));
    }

    public void logErrors() {
        for (Erro e : this.errors) {
            e.print();
        }
    }

    public boolean hasErrors() {
        return this.errors.size() > 0;
    }

    public boolean hasErrorType(String type) {
        for (Erro e : this.errors) {
            if (e.getType().equals(type)) {
                return true;
            }
        }

        return false;
    }

    public List<Erro> getErrors() {
        return errors;
    }

}