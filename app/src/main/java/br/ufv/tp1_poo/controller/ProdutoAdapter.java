package br.ufv.tp1_poo.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.ufv.tp1_poo.R;
import br.ufv.tp1_poo.model.Produto;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

    private final List<Produto> produtos;
    private final OnProdutoClickListener listener;

    // Interface para tratar cliques nos itens
    public interface OnProdutoClickListener {
        void onProdutoClick(Produto produto);
    }

    // Construtor para receber a lista de produtos e o listener
    public ProdutoAdapter(List<Produto> produtos, OnProdutoClickListener listener) {
        this.produtos = produtos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_produto, parent, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produto produto = produtos.get(position);

        // Configura os dados no ViewHolder
        holder.nomeProduto.setText(produto.getNome());
        holder.descricaoProduto.setText(produto.getDescricao());
        holder.precoProduto.setText(String.format("R$ %.2f", produto.getPreco() / 100.0));

        // Carregar imagem do produto com Glide
        Glide.with(holder.itemView.getContext())
                .load("file:///android_asset/" + produto.getImagem())
                .placeholder(R.drawable.imagem_carregando)
                .error(R.drawable.imagem_carregando)
                .into(holder.imagemProduto);

        // Configura o clique no item
        holder.itemView.setOnClickListener(v -> listener.onProdutoClick(produto));
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeProduto;
        TextView precoProduto;
        TextView descricaoProduto;
        ImageView imagemProduto;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeProduto = itemView.findViewById(R.id.nomeProduto);
            precoProduto = itemView.findViewById(R.id.precoProduto);
            descricaoProduto = itemView.findViewById(R.id.descricaoProduto);
            imagemProduto = itemView.findViewById(R.id.imagemProduto);
        }
    }
}