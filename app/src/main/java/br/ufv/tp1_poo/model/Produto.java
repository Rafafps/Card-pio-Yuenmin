package br.ufv.tp1_poo.model;

public class Produto {
    private int preco;
    private String nome;
    private int quantidade;
    private String descricao;
    private String imagem;
    private String categoria;
    private String observacao;
    private String tamanho;

    public Produto(int preco, String nome, int quantidade, String descricao, String imagem,
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

    public int getPreco() {
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

    // Metodo para calcular o preço total do produto, considerando a quantidade
    public int calculaPreco() {
        return this.preco * this.quantidade;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "preco=" + preco +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", imagem='" + imagem + '\'' +
                ", categoria='" + categoria + '\'' +
                ", observacao='" + observacao + '\'' +
                ", tamanho='" + tamanho + '\'' +
                '}';
    }
}