

import org.json.JSONObject;
import java.io.Serializable;

public class Produto implements Serializable {
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

    // Getters e Setters
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

    // Metodo estático para criar um Produto a partir de um JSONObject
    public static Produto fromJson(JSONObject jsonObject) {
        try {
            int preco = jsonObject.optInt("preco", 0);
            String nome = jsonObject.optString("nome", "Sem nome");
            int quantidade = jsonObject.optInt("quantidade", 1); // Padrão: 1
            String descricao = jsonObject.optString("descricao", "Sem descrição");
            String imagem = jsonObject.optString("imagem", ""); // URL ou vazio
            String categoria = jsonObject.optString("categoria", "Sem categoria");
            String observacao = jsonObject.optString("observacao", "Sem observação");
            String tamanho = jsonObject.optString("tamanho", "Único");

            return new Produto(preco, nome, quantidade, descricao, imagem, categoria, observacao, tamanho);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Retorna null em caso de erro
        }
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