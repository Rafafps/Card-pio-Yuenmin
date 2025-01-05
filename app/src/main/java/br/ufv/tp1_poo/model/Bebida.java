package br.ufv.tp1_poo.model;

public class Bebida extends Produto {
    public Bebida(int preco, String nome, int quantidade, String descricao, String imagem,
                  String categoria, String observacao, String tamanho) {
        super(preco, nome, quantidade, descricao, imagem, categoria, observacao, tamanho);
    }

    @Override
    public String getTamanhoFormatado() {
        if (getTamanho().equals("300ml") || getTamanho().equals("500ml") || getTamanho().equals("700ml")) {
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
            case "500ml": return 2;
            case "700ml": return 4;
            default: return 0;
        }
    }
}
