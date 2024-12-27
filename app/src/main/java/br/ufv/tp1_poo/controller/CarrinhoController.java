package br.ufv.tp1_poo.controller;

import br.ufv.tp1_poo.model.Carrinho;
import br.ufv.tp1_poo.model.Produto;

public class CarrinhoController {
    private Carrinho carrinho;
    // Iniciando o controller
    public CarrinhoController() {this.carrinho = new Carrinho();}
    public void adicionarProdutoCarrinho(Produto produto) {
        // Verifica se o produto já existe no carrinho, caso exista, soma a quantidade
        for (Produto p : carrinho.getProdutos()) {
            if (p.getNome().equals(produto.getNome()) && p.getTamanho().equals(produto.getTamanho())) {
                p.setQuantidade(p.getQuantidade() + produto.getQuantidade());
                return;
            }
        }
        // Se o produto não existir, adiciona um novo ao carrinho
        carrinho.adicionarProduto(produto);
    }
    public void removerProdutoDoCarrinho(String nome, String tamanho) {
        // Remove o produto do carrinho que tem o nome e tamanho correspondentes,pensei nesse modo, para depois implementar um q remove tudo
        carrinho.removerProduto(nome, tamanho);
    }
    //Remove todos os produtos
    public void limparCarrinho() {carrinho.limparCarrinho();}
    public double calcularTotal() {return carrinho.calcularTotal();}
    // get para acessar produtos, na minha cabecinha, faz sentido ter um getter para acessar os produtos no Controller, mas pode n ter
    public List<Produto> getProdutos() {return carrinho.getProdutos();}
}
