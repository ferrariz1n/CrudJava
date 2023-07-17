import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class FuncionarioTableModel extends AbstractTableModel {
    private List<Funcionario> funcionarios = new ArrayList<>();
    private String[] colunas = new String[]{"Id", 
                                            "Nome", 
                                            "Descrição"};

    public FuncionarioTableModel(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    public int getRowCount() {
        return funcionarios.size();
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

        Funcionario funcionario = funcionarios.get(rowIdx);

        switch (colIdx) {
        case 0:
            value = Integer.toString(funcionario.getId());
            break;
        case 1:
            value = funcionario.getNome();
            break;
        case 2:
            value = funcionario.getCpf();
            break;
        case 3:
            value = funcionario.getCargo();
            break;  
        case 4:
            value = funcionario.getSalario();
            break;    
        default:
            System.err.printf("[ERRO] Indice de coluna invalido: %d%n", 
                              colIdx);
        }

        return value;
    }

    public void carregar(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
        fireTableDataChanged();
    }

    public Funcionario getFuncionario(int rowIdx) {
        Funcionario funcionario = null;

        funcionario = funcionarios.get(rowIdx);

        return funcionario;
    }
} // fim da classe FuncionarioTableModel
