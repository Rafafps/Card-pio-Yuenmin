package br.ufv.tp1_poo.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    // Lista de produtos compartilhada por toda a classe
    @JsonProperty("listaDeProdutos") // Para serializar e deserializar corretamente
    private static List<Produto> listaDeProdutos = new ArrayList<>();

    // Construtor não é necessário porque a lista é estática
    public Carrinho() {}

    public static boolean adicionaProduto(Produto produto) {
        if (produto == null) return false;

        for (Produto item : listaDeProdutos) {
            if (item.getNome().equals(produto.getNome())) {
                // Se já existir, atualiza a quantidade
                item.setQuantidade(item.getQuantidade() + produto.getQuantidade());
                return true;
            }
        }
        // Se não existir, adiciona um novo item
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

    // Metodo para obter os produtos (já existe getListaDeProdutos, mas o nome foi alterado conforme sua solicitação)
    public static List<Produto> getProdutos() {
        return listaDeProdutos;
    }
}
