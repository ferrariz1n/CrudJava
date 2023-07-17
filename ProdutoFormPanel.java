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

public class ProdutoFormPanel extends JPanel {
    private AppFrame frame;

    private Produto produto;

    private GridBagLayout layout;
    private GridBagConstraints constraints;

    private JTextField txtId;
    private JTextField txtProduto;
    private JTextField txtMarca;
    private JTextField txtValor;
    private JButton btnSalvar;
    private JButton btnCancelar;

    public ProdutoFormPanel(AppFrame frame) {
        this.frame = frame;

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        setLayout(layout);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if (produto == null) {
                    txtId.setText("");
                    txtProduto.setText("");
                    txtMarca.setText("");
                    txtValor.setText("");
                } else {
                    txtId.setText(Integer.toString(produto.getId()));
                    txtProduto.setText(produto.getProduto());
                    txtMarca.setText(produto.getMarca());
                    txtValor.setText(produto.getValor());
                }
            }
        });

        criarForm();
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    private void criarForm() {
        JLabel label;

        label = new JLabel("Id");
        adicionarComponente(label, 0, 0);
        txtId = new JTextField(30);
        txtId.setEditable(false);
        adicionarComponente(txtId, 0, 1);

        label = new JLabel("Produto");
        adicionarComponente(label, 1, 0);
        txtProduto = new JTextField(30);
        adicionarComponente(txtProduto, 1, 1);

        label = new JLabel("Marca");
        adicionarComponente(label, 2, 0);
        txtMarca = new JTextField(30);
        adicionarComponente(txtMarca, 2, 1);

        label = new JLabel("Valor");
        adicionarComponente(label, 3, 0);
        txtValor = new JTextField(30);
        adicionarComponente(txtValor, 3, 1);

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
                if (ProdutoFormPanel.this.produto == null) {
                    Produto novoProduto = new Produto();
                    novoProduto.setProduto(txtProduto.getText());
                    novoProduto.setMarca(txtMarca.getText());
                    novoProduto.setValor(txtValor.getText());

                    ProdutoStorage.inserir(novoProduto);
                    JOptionPane.showMessageDialog(ProdutoFormPanel.this, 
                                                  "Produto incluido com sucesso", 
                                                  "Todo App", 
                                                  JOptionPane.INFORMATION_MESSAGE);
                } else {
                    produto.setProduto(txtProduto.getText());
                    produto.setMarca(txtMarca.getText());
                    produto.setValor(txtValor.getText());
                    ProdutoStorage.atualizar(ProdutoFormPanel.this.produto);
                    JOptionPane.showMessageDialog(ProdutoFormPanel.this, 
                                                  "Produto atualizado com sucesso", 
                                                  "Todo App", 
                                                  JOptionPane.INFORMATION_MESSAGE);
                }

                    
                frame.mostrarProdutoListPanel();
            }
        });
        btnPanel.add(btnSalvar);
    }

    private void criarBtnCancelar(JPanel btnPanel) {
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarProdutoListPanel();
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
} // fim da classe ProdutoFormPanel
