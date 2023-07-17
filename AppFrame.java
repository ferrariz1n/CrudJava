import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class AppFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private PainelInicial painelinicial;
    private ClienteListPanel clienteListPanel;
    private ClienteFormPanel clienteFormPanel;
    private FornecedorListPanel fornecedorListPanel;
    private FornecedorFormPanel fornecedorFormPanel;
    private FuncionarioListPanel funcionarioListPanel;
    private FuncionarioFormPanel funcionarioFormPanel;
    private ProdutoListPanel produtoListPanel;
    private ProdutoFormPanel produtoFormPanel;


    public AppFrame() {
        super("Todo App");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        add(cardPanel);

        criarCards();
    }

    public void mostrar() {
        pack();
		setLocationRelativeTo(null);
		setVisible(true);
    }

    private void criarCards() {

        painelinicial = new PainelInicial(this);
        cardPanel.add(painelinicial, PainelInicial.class.getName());

        /////Cliente/////

        clienteListPanel = new ClienteListPanel(this);
        cardPanel.add(clienteListPanel, ClienteListPanel.class.getName());

        clienteFormPanel = new ClienteFormPanel(this);
        cardPanel.add(clienteFormPanel, ClienteFormPanel.class.getName());

       /////Fornecedor/////

        fornecedorListPanel = new FornecedorListPanel(this);
        cardPanel.add(fornecedorListPanel, FornecedorListPanel.class.getName());

        fornecedorFormPanel = new FornecedorFormPanel(this);
        cardPanel.add(fornecedorFormPanel, FornecedorFormPanel.class.getName());

        /////Funcionario///// 

        funcionarioListPanel = new FuncionarioListPanel(this);
        cardPanel.add(funcionarioListPanel, FuncionarioListPanel.class.getName());

        funcionarioFormPanel = new FuncionarioFormPanel(this);
        cardPanel.add(funcionarioFormPanel, FuncionarioFormPanel.class.getName());

        /////Produto///// 

        produtoListPanel = new ProdutoListPanel(this);
        cardPanel.add(produtoListPanel, ProdutoListPanel.class.getName());

        produtoFormPanel = new ProdutoFormPanel(this);
        cardPanel.add(produtoFormPanel, ProdutoFormPanel.class.getName());

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/////Cliente/////

    public void mostrarClienteFormPanel(Cliente Cliente) {
        clienteFormPanel.setCliente(Cliente);
        cardLayout.show(cardPanel, ClienteFormPanel.class.getName());
    }

    public void mostrarClienteListPanel() {
        clienteListPanel.recarregar();
        cardLayout.show(cardPanel, ClienteListPanel.class.getName());
    }

/////Fornecedor/////  

    public void mostrarFornecedorFormPanel(Fornecedor Fornecedor) {
        fornecedorFormPanel.setFornecedor(Fornecedor);
        cardLayout.show(cardPanel, FornecedorFormPanel.class.getName());
    }

    public void mostrarFornecedorListPanel() {
        fornecedorListPanel.recarregar();
        cardLayout.show(cardPanel, FornecedorListPanel.class.getName());
    }

 /////Funcionario/////    

 public void mostrarFuncionarioFormPanel(Funcionario Funcionario) {
    funcionarioFormPanel.setFuncionario(Funcionario);
    cardLayout.show(cardPanel, FuncionarioFormPanel.class.getName());
}

public void mostrarFuncionarioListPanel() {
    funcionarioListPanel.recarregar();
    cardLayout.show(cardPanel, FuncionarioListPanel.class.getName());
}

/////Produto/////  

public void mostrarProdutoFormPanel(Produto Produto) {
    produtoFormPanel.setProduto(Produto);
    cardLayout.show(cardPanel, ProdutoFormPanel.class.getName());
}

public void mostrarProdutoListPanel() {
    produtoListPanel.recarregar();
    cardLayout.show(cardPanel, ProdutoListPanel.class.getName());
}

/////Painel/////

    public void mostrarPainelInicial() {
        cardLayout.show(cardPanel, PainelInicial.class.getName());
    }
}
 // fim da classe AppFrame
