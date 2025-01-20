package br.ufv.tp1_poo.controller;

import android.content.Context;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Locale;
import java.util.Objects;

import br.ufv.tp1_poo.R;
import br.ufv.tp1_poo.model.Produto;
import br.ufv.tp1_poo.model.Carrinho;
import br.ufv.tp1_poo.view.DetalhesProdutoActivity;

public class ProdutoController {
    private static Carrinho carrinho;
    private final DetalhesProdutoActivity view;
    private final Produto produto;
    private Produto produtoCarrinho;
    private int quantidade = 1;
    private int valorAdicional = 0;

    public ProdutoController(DetalhesProdutoActivity view, Produto produto) {
        this.view = view;
        this.produto = produto;
    }

    public void carregarProduto() {
        try {
            String categoria = produto.getCategoria();
            System.out.println("Categoria retornada: " + categoria);
            // Carregar imagem do produto
            int resourceId = view.getResources().getIdentifier(produto.getImagem(), "drawable", view.getPackageName());
            if (resourceId != 0) {
                Glide.with(view).load(resourceId).into(view.getImagemProduto());
            } else {
                Glide.with(view).load(R.drawable.imagem_carregando).into(view.getImagemProduto());
            }

            // Configurar texto dos campos
            view.getNomeProduto().setText(produto.getNome());
            view.getDescricaoProduto().setText(produto.getDescricao());
            configurarOpcoesTamanhos(categoria);
            atualizarPreco(valorAdicional, quantidade);
        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(view).load(R.drawable.imagem_carregando).into(view.getImagemProduto());
        }
    }

    private void configurarOpcoesTamanhos(String categoria) {
        // Remove todas as opções anteriores no grupo de botões
        view.getRadioGroupTamanhos().removeAllViews();

        // Verifica a categoria e o tipo do produto
        if (categoria.equalsIgnoreCase("Bebida")) {
            adicionarOpcaoTamanho("300ml                                                                       +R$0,00", 0);
            adicionarOpcaoTamanho("500ml                                                                       +R$2,00", 2);
            adicionarOpcaoTamanho("700ml                                                                       +R$4,00", 4);
        } else {
            adicionarOpcaoTamanho("P                                                                               +R$0,00", 0);
            adicionarOpcaoTamanho("M                                                                               +R$2,00", 2);
            adicionarOpcaoTamanho("G                                                                               +R$4,00", 4);
        }
    }

    private void adicionarOpcaoTamanho(String label, int adicional) {
        RadioButton opcao = new RadioButton(view);
        opcao.setText(label);
        opcao.setTag(adicional);
        view.getRadioGroupTamanhos().addView(opcao);
    }

    public void alterarQuantidade(int delta) {
        quantidade = Math.max(1, quantidade + delta);
        view.getQuantidadeProduto().setText(String.valueOf(quantidade));
        atualizarPreco(valorAdicional, quantidade);
        view.atualizarEstadoBotaoDiminuir();
    }

    public void atualizarValorAdicional() {
        int checkedId = view.getRadioGroupTamanhos().getCheckedRadioButtonId();

        if (checkedId != -1) {
            RadioButton selecionado = view.findViewById(checkedId);
            valorAdicional = (int) selecionado.getTag();
        } else {
            valorAdicional = 0;
        }

        atualizarPreco(valorAdicional, quantidade);
    }
    private void atualizarPreco(int valorAdicional, int quantidade) {
        double precoBase = produto.getPreco();
        double precoTotal = (precoBase + valorAdicional) * quantidade;

        String precoFormatado = String.format(Locale.getDefault(), "%.2f", precoTotal).replace(".", ",");
        view.getBotaoAdicionar().setText("Adicionar R$ " + precoFormatado);
    }


    public int getQuantidade() {
        return quantidade;
    }
    public void adicionarAoCarrinho(CarrinhoController carrinhoController) {
        double precoBase = produto.getPreco();

        double precoComAdicional = precoBase + valorAdicional;
        String observacao = view.getObservacaoProduto().getText().toString();


        Produto produtoParaCarrinho = criarCopiaProduto(produto);
        produtoParaCarrinho.setQuantidade(quantidade);
        produtoParaCarrinho.setTamanho(view.getTamanhoSelecionado());
        produtoParaCarrinho.setObservacao(observacao);
        produtoParaCarrinho.setPreco(precoComAdicional);

        // 4. Verifique se o produto já existe no carrinho (com tamanho e observação)
        boolean produtoAtualizado = false;
        for (Produto produtoNoCarrinho : Carrinho.getListaDeProdutos()) {
            if (produtosIguais(produtoNoCarrinho, produtoParaCarrinho)) {
                produtoNoCarrinho.setQuantidade(produtoNoCarrinho.getQuantidade() + quantidade);
                produtoAtualizado = true;
                break;
            }
        }

        if (!produtoAtualizado) {
            carrinhoController.adicionarProduto(produtoParaCarrinho, null);
        }

        Toast.makeText(view, "Produto adicionado ao carrinho!", Toast.LENGTH_SHORT).show();
    }

    // Método auxiliar para criar uma cópia do produto
    private Produto criarCopiaProduto(Produto produtoOriginal) {
        try {
            String json = Produto.toJson(produtoOriginal);
            return Produto.fromJson(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean produtosIguais(Produto p1, Produto p2) {
        return p1.getNome().equals(p2.getNome()) &&
                Objects.equals(p1.getTamanho(), p2.getTamanho()) &&
                Objects.equals(p1.getObservacao(), p2.getObservacao());
    }

}
