package br.ufv.tp1_poo.model;

public class Congelado extends Produto {
    public Congelado(int preco, String nome, int quantidade, String descricao, String imagem,
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
}
