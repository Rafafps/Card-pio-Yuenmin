package br.ufv.tp1_poo.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    // Lista de produtos compartilhada por toda a classe
    private static List<Produto> listaDeProdutos = new ArrayList<>();

    // Construtor não é mais necessário, já que a lista é estática
    private Carrinho() {}

    public static boolean adicionaProduto(Produto produto) {
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

    public static boolean removeProduto(Produto produto) {
        return listaDeProdutos.remove(produto);
    }

    public static List<Produto> getListaDeProdutos() {
        return listaDeProdutos;
    }

    public static void setListaDeProdutos(List<Produto> novaListaDeProdutos) {
        listaDeProdutos = novaListaDeProdutos;
    }

    public static int calculaTotal() {
        int total = 0;
        for (Produto item : listaDeProdutos) {
            total += item.calculaPreco();
        }
        return total;
    }
    public static void limpa(){
        listaDeProdutos.clear();
    }
    public static boolean estaVazio() {
        return listaDeProdutos.isEmpty();
    }
}
