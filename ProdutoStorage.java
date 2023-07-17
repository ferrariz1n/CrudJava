import java.util.ArrayList;
import java.util.List;

public class ProdutoStorage {
    private static List<Produto> produtos = new ArrayList<>();
    private static int incremento = 1;

    public static void inserir(Produto produto) {
        produto.setId(incremento++);
        produto.add(produto);
    }

    public static void atualizar(Produto produto) {
        int idx = produtos.indexOf(produto);
        if (idx >= 0) {
            produtos.set(idx, produto);
        }
    }

    public static void remover(Produto produto) {
        produtos.remove(produto);
    }

    public static List<Produto> listar() {
        return produtos;
    }
} // fim da classe ProdutoStorage
