import java.util.ArrayList;
import java.util.List;

public class FornecedorStorage {
    private static List<Fornecedor> fornecedores = new ArrayList<>();
    private static int incremento = 1;

    public static void inserir(Fornecedor fornecedor) {
        fornecedor.setId(incremento++);
        fornecedores.add(fornecedor);
    }

    public static void atualizar(Fornecedor fornecedor) {
        int idx = fornecedores.indexOf(fornecedor);
        if (idx >= 0) {
            fornecedores.set(idx, fornecedor);
        }
    }

    public static void remover(Fornecedor fornecedor) {
        fornecedores.remove(fornecedor);
    }

    public static List<Fornecedor> listar() {
        return fornecedores;
    }
} // fim da classe FornecedorStorage
