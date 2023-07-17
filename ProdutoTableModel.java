import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ProdutoTableModel extends AbstractTableModel {
    private List<Produto> produtos = new ArrayList<>();
    private String[] colunas = new String[]{"Id", 
                                            "Nome", 
                                            "Descrição"};

    public ProdutoTableModel(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public int getRowCount() {
        return produtos.size();
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

        Produto produto = produtos.get(rowIdx);

        switch (colIdx) {
        case 0:
            value = Integer.toString(produto.getId());
            break;
        case 1:
            value = produto.getProduto();
            break;
        case 2:
            value = produto.getMarca();
            break;
        case 3:
            value = produto.getValor();
            break;
        default:
            System.err.printf("[ERRO] Indice de coluna invalido: %d%n", 
                              colIdx);
        }

        return value;
    }

    public void carregar(List<Produto> produtos) {
        this.produtos = produtos;
        fireTableDataChanged();
    }

    public Produto getProduto(int rowIdx) {
        Produto produto = null;

        produto = produtos.get(rowIdx);

        return produto;
    }
} // fim da classe ProdutoTableModel
