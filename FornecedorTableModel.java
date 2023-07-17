import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class FornecedorTableModel extends AbstractTableModel {
    private List<Fornecedor> fornecedores = new ArrayList<>();
    private String[] colunas = new String[]{"Id", 
                                            "Nome", 
                                            "Cnpj"};

    public FornecedorTableModel(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    @Override
    public int getRowCount() {
        return fornecedores.size();
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

        Fornecedor fornecedor = fornecedores.get(rowIdx);

        switch (colIdx) {
        case 0:
            value = Integer.toString(fornecedor.getId());
            break;
        case 1:
            value = fornecedor.getNome();
            break;
        case 2:
            value = fornecedor.getCnpj();
            break;   
        default:
            System.err.printf("[ERRO] Indice de coluna invalido: %d%n", 
                              colIdx);
        }

        return value;
    }

    public void carregar(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
        fireTableDataChanged();
    }

    public Fornecedor getFornecedor(int rowIdx) {
        Fornecedor fornecedor = null;

        fornecedor = fornecedores.get(rowIdx);

        return fornecedor;
    }
} // fim da classe FornecedorTableModel