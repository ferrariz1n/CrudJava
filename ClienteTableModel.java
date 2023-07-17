import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ClienteTableModel extends AbstractTableModel {
    private List<Cliente> clientes = new ArrayList<>();
    private String[] colunas = new String[]{"Id", 
                                            "Nome", 
                                            "Descrição"};

    public ClienteTableModel(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int colIdx) {
        String colName = null;

        colName = colunas[colIdx];

        return colName;
    }

    @Override
    public Object getValueAt(int rowIdx, int colIdx) {
        String value = null;

        Cliente cliente = clientes.get(rowIdx);

        switch (colIdx) {
        case 0:
            value = Integer.toString(cliente.getId());
            break;
        case 1:
            value = cliente.getNome();
            break;
        case 2:
            value = cliente.getCpf();
            break;
        case 3:
            value = cliente.getEndereco();
            break;    
        default:
            System.err.printf("[ERRO] Indice de coluna invalido: %d%n", 
                              colIdx);
        }

        return value;
    }

    public void carregar(List<Cliente> clientes) {
        this.clientes = clientes;
        fireTableDataChanged();
    }

    public Cliente getCliente(int rowIdx) {
        Cliente cliente = null;

        cliente = clientes.get(rowIdx);

        return cliente;
    }
} // fim da classe ClienteTableModel
