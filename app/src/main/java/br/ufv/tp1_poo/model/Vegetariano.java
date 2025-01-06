package br.ufv.tp1_poo.model;

public class Vegetariano extends Produto {
    public Vegetariano(int preco, String nome, int quantidade, String descricao, String imagem,
                       String categoria, String observacao, String tamanho) {
        super(nome, preco,  quantidade, descricao, imagem, categoria, observacao, tamanho);
    }

    @Override
    public String getTamanhoFormatado() {
        if (getTamanho().equals("P") || getTamanho().equals("M") || getTamanho().equals("G")) {
            return getTamanho();
        } else {
            return "Tamanho inválido";
        }
    }
    @Override
    public int calculaPreco() {
        return ((int)this.getPreco() + this.getAdicionalPorTamanho()) * this.getQuantidade();
    }

    private int getAdicionalPorTamanho() {
        switch (this.getTamanhoFormatado()) {
            case "M": return 2;
            case "G": return 4;
            default: return 0;
        }
    }
}
