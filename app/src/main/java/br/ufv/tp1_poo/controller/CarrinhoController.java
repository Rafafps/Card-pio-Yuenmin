package br.ufv.tp1_poo.controller;
import br.ufv.tp1_poo.model.Carrinho;
import br.ufv.tp1_poo.model.Produto;
public class CarrinhoController {
    private Carrinho carrinho;
    public CarrinhoController(Carrinho carrinho) {this.carrinho = carrinho;}
    public void removeProdutoDoCarrinho(Produto produto) {
        if (!carrinho.removeProduto(produto)) {
            System.out.println("Produto não encontrado no carrinho.");
        }
    }
    public void adicionaProdutoAoCarrinho(Produto produto) {carrinho.adicionaProduto(produto);}
    public void limparCarrinho() {carrinho.getListaDeProdutos().clear();}
    public int calculaTotal() {return carrinho.calculaTotal();}
    public boolean estaVazio() {return carrinho.estaVazio();}
}
