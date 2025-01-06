package br.ufv.tp1_poo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Vegetariano extends Produto {

    @JsonCreator
    public Vegetariano(
            @JsonProperty("preco") int preco,
            @JsonProperty("nome") String nome,
            @JsonProperty("quantidade") int quantidade,
            @JsonProperty("descricao") String descricao,
            @JsonProperty("imagem") String imagem,
            @JsonProperty("categoria") String categoria,
            @JsonProperty("observacao") String observacao,
            @JsonProperty("tamanho") String tamanho) {
        super(nome, preco, quantidade, descricao, imagem, categoria, observacao, tamanho);
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
