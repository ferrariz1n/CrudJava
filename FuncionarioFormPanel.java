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

public class FuncionarioFormPanel extends JPanel {
    private AppFrame frame;

    private Funcionario funcionario;

    private GridBagLayout layout;
    private GridBagConstraints constraints;

    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtCargo;
    private JTextField txtSalario;
    private JButton btnSalvar;
    private JButton btnCancelar;

    public FuncionarioFormPanel(AppFrame frame) {
        this.frame = frame;

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        setLayout(layout);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if (funcionario == null) {
                    txtId.setText("");
                    txtNome.setText("");
                    txtCpf.setText("");
                    txtCargo.setText("");
                    txtSalario.setText("");
                } else {
                    txtId.setText(Integer.toString(funcionario.getId()));
                    txtNome.setText(funcionario.getNome());
                    txtCpf.setText(funcionario.getCpf());
                    txtCargo.setText(funcionario.getCargo());
                    txtSalario.setText(funcionario.getSalario());
                }
            }
        });

        criarForm();
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    private void criarForm() {
        JLabel label;

        label = new JLabel("Id");
        adicionarComponente(label, 0, 0);
        txtId = new JTextField(30);
        txtId.setEditable(false);
        adicionarComponente(txtId, 0, 1);

        label = new JLabel("Nome");
        adicionarComponente(label, 1, 0);
        txtNome = new JTextField(30);
        adicionarComponente(txtNome, 1, 1);

        label = new JLabel("CPF");
        adicionarComponente(label, 2, 0);
        txtCpf = new JTextField(30);
        adicionarComponente(txtCpf, 2, 1);

        label = new JLabel("Cargo");
        adicionarComponente(label, 3, 0);
        txtCargo = new JTextField(30);
        adicionarComponente(txtCargo, 3, 1);

        label = new JLabel("Salario");
        adicionarComponente(label, 4, 0);
        txtSalario = new JTextField(30);
        adicionarComponente(txtSalario, 4, 1);

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
                if (FuncionarioFormPanel.this.funcionario == null) {
                    Funcionario novoFuncionario = new Funcionario();
                    novoFuncionario.setNome(txtNome.getText());
                    novoFuncionario.setCpf(txtCpf.getText());
                    novoFuncionario.setCargo(txtCargo.getText());
                    novoFuncionario.setSalario(txtSalario.getText());

                    FuncionarioStorage.inserir(novoFuncionario);
                    JOptionPane.showMessageDialog(FuncionarioFormPanel.this, 
                                                  "Funcionario incluido com sucesso", 
                                                  "Todo App", 
                                                  JOptionPane.INFORMATION_MESSAGE);
                } else {
                    funcionario.setNome(txtNome.getText());
                    funcionario.setCpf(txtCpf.getText());
                    FuncionarioStorage.atualizar(FuncionarioFormPanel.this.funcionario);
                    JOptionPane.showMessageDialog(FuncionarioFormPanel.this, 
                                                  "Funcionario atualizado com sucesso", 
                                                  "Todo App", 
                                                  JOptionPane.INFORMATION_MESSAGE);
                }

                    
                frame.mostrarFuncionarioListPanel();
            }
        });
        btnPanel.add(btnSalvar);
    }

    private void criarBtnCancelar(JPanel btnPanel) {
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarFuncionarioListPanel();
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
} // fim da classe FuncionarioFormPanel
