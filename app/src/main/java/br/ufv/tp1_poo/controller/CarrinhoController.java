package br.ufv.tp1_poo.model;
public class CarrinhoController {
    private Carrinho carrinho; // Atributo do tipo Carrinho
    public CarrinhoController() {
        this.carrinho = new Carrinho();
    }
    public void adicionarProdutoAoCarrinho(Produto produto) {
        carrinho.adicionarProduto(produto);
    }
    public void removerProdutoDoCarrinho(Produto produto) {
        carrinho.removerProduto(produto);
    }

    public void limparCarrinho() {
        carrinho.limpar();
    }
    public void calculaTotal() {
        double total = carrinho.calcularTotal();
        System.out.println("Total do carrinho: R$ " + total);
    }
}
