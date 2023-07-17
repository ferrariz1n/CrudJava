import java.util.ArrayList;
import java.util.List;

public class ClienteStorage {
    private static List<Cliente> clientes = new ArrayList<>();
    private static int incremento = 1;

    public static void inserir(Cliente cliente) {
        cliente.setId(incremento++);
        clientes.add(cliente);
    }

    public static void atualizar(Cliente cliente) {
        int idx = clientes.indexOf(cliente);
        if (idx >= 0) {
            clientes.set(idx, cliente);
        }
    }

    public static void remover(Cliente cliente) {
        clientes.remove(cliente);
    }

    public static List<Cliente> listar() {
        return clientes;
    }
} // fim da classe ClienteStorage
