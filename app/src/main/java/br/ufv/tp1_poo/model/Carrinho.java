package br.ufv.tp1_poo.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    // Lista de produtos específica para cada instância de Carrinho
    private final List<Produto> listaDeProdutos;

    // Construtor para inicializar a lista
    public Carrinho() {
        this.listaDeProdutos = new ArrayList<>();
    }

    public boolean adicionaProduto(Produto produto) {
        for (Produto item : listaDeProdutos) {
            if (item.getNome().equals(produto.getNome())) {
                // Se já existir, atualiza a quantidade
                item.setQuantidade(item.getQuantidade() + 1);
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
        return new ArrayList<>(listaDeProdutos); // Retorna uma cópia para evitar modificações externas
    }

    public int calculaTotal() {
        int total = 0;
        for (Produto item : listaDeProdutos) {
            total += item.calculaPreco();
        }
        return total;
    }

    public void limpa() {
        listaDeProdutos.clear();
    }

    public boolean estaVazio() {
        return listaDeProdutos.isEmpty();
    }
}
