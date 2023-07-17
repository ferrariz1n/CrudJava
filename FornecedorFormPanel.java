import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FornecedorFormPanel extends JPanel {
    private AppFrame frame;

    private Fornecedor fornecedor;

    private GridBagLayout layout;
    private GridBagConstraints constraints;

    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtCnpj;
    private JButton btnSalvar;
    private JButton btnCancelar;

    public FornecedorFormPanel(AppFrame frame) {
        this.frame = frame;

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        setLayout(layout);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if (fornecedor == null) {
                    txtId.setText("");
                    txtNome.setText("");
                    txtCnpj.setText("");
                } else {
                    txtId.setText(Integer.toString(fornecedor.getId()));
                    txtNome.setText(fornecedor.getNome());
                    txtCnpj.setText(fornecedor.getCnpj());
                }
            }
        });

        criarForm();
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    private void criarForm() {
        JLabel label;

        label = new JLabel("Id");
        adicionarComponente(label, 0, 0);
        txtId = new JTextField(5);
        txtId.setEditable(false);
        adicionarComponente(txtId, 0, 1);

        label = new JLabel("Nome");
        adicionarComponente(label, 1, 0);
        txtNome = new JTextField(30);
        adicionarComponente(txtNome, 1, 1);

        label = new JLabel("CNPJ");
        adicionarComponente(label, 2, 0);
        txtCnpj = new JTextField(30);
        adicionarComponente(txtCnpj, 2, 1, 1, 5);

        criarBotoes();
    }

    private void criarBotoes() {
        JPanel btnPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);

        criarBtnSalvar(btnPanel);
        criarBtnCancelar(btnPanel);

        adicionarComponente(btnPanel, 7, 1, 2, 1);
    }

    private void criarBtnSalvar(JPanel btnPanel) {
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (FornecedorFormPanel.this.fornecedor == null) {
                    Fornecedor novoFornecedor = new Fornecedor();
                    novoFornecedor.setNome(txtNome.getText());
                    novoFornecedor.setCnpj(txtCnpj.getText());

                    FornecedorStorage.inserir(novoFornecedor);
                    JOptionPane.showMessageDialog(FornecedorFormPanel.this, 
                                                  "Fornecedor incluido com sucesso", 
                                                  "Todo App", 
                                                  JOptionPane.INFORMATION_MESSAGE);
                } else {
                    fornecedor.setNome(txtNome.getText());
                    fornecedor.setCnpj(txtCnpj.getText());
                    FornecedorStorage.atualizar(FornecedorFormPanel.this.fornecedor);
                    JOptionPane.showMessageDialog(FornecedorFormPanel.this, 
                                                  "Fornecedor atualizado com sucesso", 
                                                  "Todo App", 
                                                  JOptionPane.INFORMATION_MESSAGE);
                }

                    
                frame.mostrarFornecedorListPanel();
            }
        });
        btnPanel.add(btnSalvar);
    }

    private void criarBtnCancelar(JPanel btnPanel) {
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarFornecedorListPanel();
            }
        });
        btnPanel.add(btnCancelar);
    }

    private void adicionarComponente(JComponent componente, 
                                    int linha, int coluna) {
        adicionarComponente(componente, linha, coluna, 1, 1);
    }                                    

    private void adicionarComponente(JComponent componente, 
                                    int linha, int coluna, 
                                    int largura, int altura) {
        constraints.gridx = coluna;
        constraints.gridy = linha;
        constraints.gridwidth = largura;
        constraints.gridheight = altura;

        constraints.fill = GridBagConstraints.HORIZONTAL;

        layout.setConstraints(componente, constraints);
        add(componente);
    }
} // fim da classe FornecedorFormPanel
