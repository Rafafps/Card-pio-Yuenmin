package br.ufv.tp1_poo.controller;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.ufv.tp1_poo.model.Carrinho;
import br.ufv.tp1_poo.model.Produto;
import br.ufv.tp1_poo.view.CarrinhoActivity;

public class CarrinhoController {

    private final CarrinhoActivity view;
    private final Carrinho carrinho;

    public CarrinhoController(CarrinhoActivity view, Carrinho carrinho) {
        this.view = view;
        this.carrinho = carrinho;
    }

    public void adicionarProduto(Produto produto) {
        if (produto != null) {
            carrinho.adicionaProduto(produto);
            Toast.makeText((Context) view, "Produto adicionado ao carrinho!", Toast.LENGTH_SHORT).show();
            atualizarCarrinho();
        }
    }

    public void removerProduto(Produto produto) {
        if (produto != null && carrinho.removeProduto(produto)) {
            Toast.makeText((Context) view, "Produto removido do carrinho!", Toast.LENGTH_SHORT).show();
            atualizarCarrinho();
        } else {
            Toast.makeText((Context) view, "Produto não encontrado no carrinho!", Toast.LENGTH_SHORT).show();
        }
    }

    public void limparCarrinho() {
        carrinho.limpa();
        Toast.makeText((Context) view, "Carrinho limpo!", Toast.LENGTH_SHORT).show();
        atualizarCarrinho();
    }

    public void calcularPrecoTotal() {
        double precoTotal = carrinho.calculaTotal();
        String precoFormatado = String.format("R$ %.2f", precoTotal).replace(".", ",");
        view.atualizarPrecoTotal(precoFormatado);
    }

    private void atualizarCarrinho() {
        List<Produto> produtos = carrinho.getProdutos();
        view.atualizarListaProdutos(new ArrayList<>(produtos)); // Atualiza a lista exibida
        calcularPrecoTotal(); // Atualiza o preço total exibido
    }
}
