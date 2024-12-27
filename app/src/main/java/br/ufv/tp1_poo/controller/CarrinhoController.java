package br.ufv.tp1_poo.controller;

import br.ufv.tp1_poo.model.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
// implementando com produto ao inves de carrinho,
// pela logica de não delegar ao carrinho, a aplicação das funções de carrinhoController
// Assim, não chamando carrinho Carrinho, como anets  foi colocado, mas chamando Produtos
public class CarrinhoController {
    private List<Produto> produtos;
    public CarrinhoController() {this.produtos = new ArrayList<>();}
    public void adicionarProdutoCarrinho(Produto produto) {
        for (Produto p : produtos) {
            if (p.getNome().equals(produto.getNome()) && p.getTamanho().equals(produto.getTamanho())) {
                p.setQuantidade(p.getQuantidade() + produto.getQuantidade());
                return;
            }
        }
        produtos.add(produto);
    }
    // Remover produto do carrinho, um unico
    public void removerProdutoDoCarrinho(String nome, String tamanho) {
        for (Iterator<Produto> iterator = produtos.iterator(); iterator.hasNext(); ) {
            Produto produto = iterator.next();
            if (produto.getNome().equals(nome) && produto.getTamanho().equals(tamanho)) {
                iterator.remove();
                break;
            }
        }
    }
    // Limpar carrinho, por completo, todos os itens
    public void limparCarrinho() {produtos.clear();}
    public double calcularTotal() {
        double total = 0;
        for (Produto produto : produtos) {
            total += produto.getPreco() * produto.getQuantidade();
        }
        return total;
    }
    // Obter lista de produtos no carrinho
    public List<Produto> getProdutos() {
        return produtos;
    }
}
