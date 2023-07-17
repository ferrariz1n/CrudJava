import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FuncionarioListPanel extends JPanel {
    private AppFrame frame;

    private JButton btnCriar;
    private JButton btnEditar;
    private JButton btnRemover;
    private JButton btnVoltar;
    private JTable tabela;
    private FuncionarioTableModel tableModel;

    public FuncionarioListPanel(AppFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());

        criarComandosPanel();
        criarTabelaPanel();
    }

    private void criarComandosPanel() {
        JPanel panel = new JPanel();
        FlowLayout layout = (FlowLayout) panel.getLayout();
        layout.setAlignment(FlowLayout.RIGHT);

        criarBtnCriar();
        panel.add(btnCriar);

        criarBtnEditar();
        panel.add(btnEditar);

        criarBtnRemover();
        panel.add(btnRemover);

        criarBtnVoltar();
        panel.add(btnVoltar);

        add(panel, BorderLayout.NORTH);

        desabilitarBtns();
    }

    private void criarBtnCriar() {
        btnCriar = new JButton("Criar");
        btnCriar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarFuncionarioFormPanel(null);
            }
        });
    }

    private void criarBtnEditar() {
        btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarFuncionarioFormPanel(tableModel.getFuncionario(tabela.getSelectedRow()));
            }
        });
    }

    private void criarBtnRemover() {
        btnRemover = new JButton("Remover");
        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Funcionario funcionario = tableModel.getFuncionario(tabela.getSelectedRow());
                int resposta = JOptionPane.showConfirmDialog(FuncionarioListPanel.this, "Deseja realmente remover?", "Todo App", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (resposta == JOptionPane.YES_OPTION) {
                    FuncionarioStorage.remover(funcionario);
                    recarregar();
                }
            }
        });
    }
    private void criarBtnVoltar() {
        btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarPainelInicial();    
            }
        });
    }

    private void criarTabelaPanel() {
        JPanel panel = new JPanel();

        tableModel = new FuncionarioTableModel(FuncionarioStorage.listar());
        tabela = new JTable(tableModel);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (tabela.getSelectedRow() >= 0) {
                        habilitarBtns();
                    } else {
                        desabilitarBtns();
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(tabela);
        panel.add(scrollPane);

        add(panel, BorderLayout.CENTER);
    }

    private void habilitarBtns() {
        btnEditar.setEnabled(true);
        btnRemover.setEnabled(true);
    }

    private void desabilitarBtns() {
        btnEditar.setEnabled(false);
        btnRemover.setEnabled(false);
    }

    public void recarregar() {
        tableModel.carregar(FuncionarioStorage.listar());
    }
} // fim da classe FuncionarioListPanel
