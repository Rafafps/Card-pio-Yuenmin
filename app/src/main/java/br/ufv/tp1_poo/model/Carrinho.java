package br.ufv.tp1_poo.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private static List<Produto> listaDeProdutos = new ArrayList<>();

    private Carrinho() {}

    public static boolean adicionaProduto(Produto produto) {
        for (Produto item : listaDeProdutos) {
            if (item.getNome().equals(produto.getNome()) &&
                    item.getTamanho().equals(produto.getTamanho()) &&
                    item.getObservacao().equals(produto.getObservacao())) {

                item.setQuantidade(item.getQuantidade() + produto.getQuantidade());
                item.setPreco(produto.getPreco());
                return true;
            }
        }
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

    public static float calculaTotal() {
        float total = 0;
        for (Produto item : listaDeProdutos) {
            total += item.calculaPreco();
        }
        return total;
    }
    public static void limpaCarrinho(){
        listaDeProdutos.clear();
    }
    public static boolean estaVazio() {
        return listaDeProdutos.isEmpty();
    }
}