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

public class ClienteFormPanel extends JPanel {
    private AppFrame frame;

    private Cliente cliente;

    private GridBagLayout layout;
    private GridBagConstraints constraints;

    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtEndereco;
    private JButton btnSalvar;
    private JButton btnCancelar;

    public ClienteFormPanel(AppFrame frame) {
        this.frame = frame;

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        setLayout(layout);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if (cliente == null) {
                    txtId.setText("");
                    txtNome.setText("");
                    txtCpf.setText("");
                    txtEndereco.setText("");
                } else {
                    txtId.setText(Integer.toString(cliente.getId()));
                    txtNome.setText(cliente.getNome());
                    txtCpf.setText(cliente.getCpf());
                    txtEndereco.setText(cliente.getEndereco());
                }
            }
        });

        criarForm();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

        label = new JLabel("Endereco");
        adicionarComponente(label, 3, 0);
        txtEndereco = new JTextField(30);
        adicionarComponente(txtEndereco, 3, 1);

        criarBotoes();
    }

    private void criarBotoes() {
        JPanel btnPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);

        criarBtnSalvar(btnPanel);
        criarBtnCancelar(btnPanel);

        adicionarComponente(btnPanel, 10, 1, 2, 1);
    }

    private void criarBtnSalvar(JPanel btnPanel) {
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ClienteFormPanel.this.cliente == null) {
                    Cliente novoCliente = new Cliente();
                    novoCliente.setNome(txtNome.getText());
                    novoCliente.setCpf(txtCpf.getText());
                    novoCliente.setEndereco(txtEndereco.getText());

                    ClienteStorage.inserir(novoCliente);
                    JOptionPane.showMessageDialog(ClienteFormPanel.this, 
                                                  "Cliente incluido com sucesso", 
                                                  "Todo App", 
                                                  JOptionPane.INFORMATION_MESSAGE);
                } else {
                    cliente.setNome(txtNome.getText());
                    cliente.setCpf(txtCpf.getText());
                    ClienteStorage.atualizar(ClienteFormPanel.this.cliente);
                    JOptionPane.showMessageDialog(ClienteFormPanel.this, 
                                                  "Cliente atualizado com sucesso", 
                                                  "Todo App", 
                                                  JOptionPane.INFORMATION_MESSAGE);
                }

                    
                frame.mostrarClienteListPanel();
            }
        });
        btnPanel.add(btnSalvar);
    }

    private void criarBtnCancelar(JPanel btnPanel) {
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarClienteListPanel();
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
} // fim da classe ClienteFormPanel
