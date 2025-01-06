package br.ufv.tp1_poo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "categoria"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Vegano.class, name = "Vegano"),
        @JsonSubTypes.Type(value = Vegetariano.class, name = "Vegetariano"),
        @JsonSubTypes.Type(value = Bebida.class, name = "Bebida"),
        @JsonSubTypes.Type(value = Congelado.class, name = "Congelado")
})
public abstract class Produto implements Serializable {

    @JsonProperty("preco")
    private double preco;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("quantidade")
    private int quantidade;
    @JsonProperty("descricao")
    private String descricao;
    @JsonProperty("imagem")
    private String imagem;
    @JsonProperty("categoria")
    private String categoria;
    @JsonProperty("observacao")
    private String observacao;
    @JsonProperty("tamanho")
    private String tamanho;

    public Produto() {}

    public Produto(String nome, double preco, int quantidade, String descricao, String imagem,
                   String categoria, String observacao, String tamanho) {
        this.preco = preco;
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.imagem = imagem;
        this.categoria = categoria;
        this.observacao = observacao;
        this.tamanho = tamanho;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getTamanhoFormatado() {
        if (tamanho == null || tamanho.isEmpty()) {
            return "Não especificado";
        }
        return "Tamanho: " + tamanho;
    }

    public int calculaPreco() { return (int)this.preco * this.quantidade;}

    // Metodo estático para deserializar JSON em um objeto Produto
    public static Produto fromJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, Produto.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Metodo estático para serializar um objeto Produto em JSON
    public static String toJson(Produto produto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(produto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome=" + nome +
                ", preco='" + preco + '\'' +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", imagem='" + imagem + '\'' +
                ", categoria='" + categoria + '\'' +
                ", observacao='" + observacao + '\'' +
                ", tamanho='" + tamanho + '\'' +
                '}';
    }
}
