package br.ufv.tp1_poo.model;

public class Vegano extends Produto {
    public Vegano(int preco, String nome, int quantidade, String descricao, String imagem,
                  String categoria, String observacao, String tamanho) {
        super(preco, nome, quantidade, descricao, imagem, categoria, observacao, tamanho);
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
        return (this.getPreco() + this.getAdicionalPorTamanho()) * this.getQuantidade();
    }

    private int getAdicionalPorTamanho() {
        switch (this.getTamanhoFormatado()) {
            case "M": return 2;
            case "G": return 4;
            default: return 0;
        }
    }
}

