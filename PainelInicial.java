import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;


public class PainelInicial extends JPanel {

    private AppFrame frame;

    private JButton btnCliente;
    private JButton btnFornecedor;
    private JButton btnFuncionario;
    private JButton btnProduto;


    public PainelInicial(AppFrame frame){
        this.frame = frame;

        setLayout(new BorderLayout());

        criarComandosPanel();
    }

    private void criarComandosPanel(){
        JPanel panel = new JPanel();

        FlowLayout layout = (FlowLayout) panel.getLayout();
        layout.setAlignment(FlowLayout.CENTER);

        criarBtnCliente();
        panel.add(btnCliente);

        criarBtnFornecedor();
        panel.add(btnFornecedor);

        criarBtnFuncionario();
        panel.add(btnFuncionario);

        criarBtnProduto();
        panel.add(btnProduto);

        add(panel, BorderLayout.CENTER);
    }

    private void criarBtnCliente(){//Clientes 
        btnCliente = new JButton("Cliente"); 
        btnCliente.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarClienteListPanel();
            }
        });
    }

    private void criarBtnFornecedor(){//Fornecedor 
        btnFornecedor = new JButton("Fornecedor"); 
        btnFornecedor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarFornecedorListPanel();
            }
        });
    }

    private void criarBtnFuncionario(){//Funcionario 
        btnFuncionario = new JButton("Funcionario"); 
        btnFuncionario.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarFuncionarioListPanel();
            }
        });
    }

    private void criarBtnProduto(){//Produto 
        btnProduto = new JButton("Produto"); 
        btnProduto.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarProdutoListPanel();
            }
        });
    }
}