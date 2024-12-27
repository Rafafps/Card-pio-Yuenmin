package br.ufv.tp1_poo.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Produto> listaDeProdutos;
    public Carrinho() {
        this.listaDeProdutos = new ArrayList<>();
    }

    public boolean adicionaProduto(Produto produto) {
        for (Produto item : listaDeProdutos) {
            if (item.getNome().equals(produto.getNome())) {
                // Se já existir, atualiza a quantidade
                item.setQuantidade(item.getQuantidade()+1);
                return true;
            }
        }
        // Se não existir, cria um novo item e adiciona
        listaDeProdutos.add(produto);
        return true;
    }

    public boolean removeProduto(Produto produto) {
        return listaDeProdutos.remove(produto);
    }

    public List<Produto> getListaDeProdutos() {
        return listaDeProdutos;
    }
    public void setListaDeProdutos(List<Produto> listaDeProdutos) {
        this.listaDeProdutos = listaDeProdutos;
    }

    public int calculaTotal() {
        int total = 0;
        for (Produto item : listaDeProdutos) {
            total += item.calculaPreco();
        }
        return total;
    }

    public boolean estaVazio() {
        return listaDeProdutos.isEmpty();
    }
}
