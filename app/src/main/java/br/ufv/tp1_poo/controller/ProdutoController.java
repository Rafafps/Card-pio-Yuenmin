package br.ufv.tp1_poo.controller;

import android.content.Context;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import br.ufv.tp1_poo.R;
import br.ufv.tp1_poo.model.Produto;
import br.ufv.tp1_poo.view.DetalhesProdutoActivity;

public class ProdutoController {

    private final DetalhesProdutoActivity view;
    private final Produto produto;
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
            atualizarPreco();
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
        atualizarPreco();
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
        atualizarPreco();
    }

    private void atualizarPreco() {
        double precoBase = produto.getPreco(); // Valor já em double, sem necessidade de divisão
        double precoTotal = (precoBase + valorAdicional) * quantidade;
        String precoFormatado = String.format("%.2f", precoTotal).replace(".", ","); // Formato brasileiro
        view.getBotaoAdicionar().setText("Adicionar R$ " + precoFormatado);
    }


    public void adicionarProdutoCarrinho() {
        if (produto != null) {
            produto.setQuantidade(quantidade);
            produto.setObservacao(view.getObservacaoProduto().getText().toString());

            int checkedId = view.getRadioGroupTamanhos().getCheckedRadioButtonId();
            if (checkedId != -1) {
                RadioButton selecionado = view.findViewById(checkedId);
                produto.setTamanho(selecionado.getText().toString());
            }

            Toast.makeText((Context) view, "Produto adicionado ao carrinho!", Toast.LENGTH_SHORT).show();
        }
    }

    public int getQuantidade() {
        return quantidade;
    }
}
