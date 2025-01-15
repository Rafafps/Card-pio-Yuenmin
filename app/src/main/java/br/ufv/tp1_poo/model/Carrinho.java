package br.ufv.tp1_poo.model;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    @JsonProperty("listaDeProdutos")
    private static List<Produto> listaDeProdutos = new ArrayList<>();

    public Carrinho() {} // Construtor necessário para serialização/deserialização

    public static boolean adicionaProduto(Produto produto) {
        if (produto == null) {
            Log.d("Carrinho", "Produto é nulo, não adicionando.");
            return false;
        }

        Log.d("Carrinho", "Tentando adicionar: " + produto.getNome() + ", Quantidade: " + produto.getQuantidade());

        for (Produto item : listaDeProdutos) {
            if (item.getNome().equals(produto.getNome())) {
                Log.d("Carrinho", "Produto já existe, atualizando quantidade.");
                item.setQuantidade(item.getQuantidade() + produto.getQuantidade());
                return true;
            }
        }

        Log.d("Carrinho", "Produto novo, adicionando à lista.");
        listaDeProdutos.add(produto);
        return true;
    }

    public static boolean removeProduto(Produto produto) {
        return listaDeProdutos.removeIf(item -> item.getNome().equals(produto.getNome()));
    }

    public static List<Produto> getListaDeProdutos() {
        return listaDeProdutos;
    }

    public static void setListaDeProdutos(List<Produto> novaListaDeProdutos) {
        if (novaListaDeProdutos != null) {
            listaDeProdutos = novaListaDeProdutos;
        }
    }

    @JsonIgnore
    public static double calculaTotal() {
        double total = 0;
        for (Produto item : listaDeProdutos) {
            total += item.calculaPreco() * item.getQuantidade();
        }
        return total;
    }

    public static void limpa() {
        listaDeProdutos.clear();
    }

    public static boolean estaVazio() {
        return listaDeProdutos.isEmpty();
    }
}