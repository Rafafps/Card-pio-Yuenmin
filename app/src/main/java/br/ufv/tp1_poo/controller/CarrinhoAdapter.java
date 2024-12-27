package br.ufv.tp1_poo.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.ufv.tp1_poo.R;
import br.ufv.tp1_poo.model.Produto;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.CarrinhoViewHolder> {

    private final List<Produto> produtos;
    private final OnCarrinhoClickListener listener;

    // Interface para tratar eventos de clique
    public interface OnCarrinhoClickListener {
        void onAdicionarItemClick(Produto produto);
        void onRemoverItemClick(Produto produto);
    }

    // Construtor do adapter
    public CarrinhoAdapter(List<Produto> produtos, OnCarrinhoClickListener listener) {
        this.produtos = produtos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarrinhoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_carrinho, parent, false);
        return new CarrinhoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarrinhoViewHolder holder, int position) {
        Produto produto = produtos.get(position);

        // Configura os dados do produto no ViewHolder
        holder.nomeProduto.setText(produto.getNome());
        holder.precoProduto.setText(String.format("R$ %.2f", produto.getPreco() / 100.0));
        holder.quantidadeProduto.setText(String.valueOf(produto.getQuantidade()));

        // Carrega a imagem do produto
        Glide.with(holder.itemView.getContext())
                .load("file:///android_asset/" + produto.getImagem())
                .placeholder(R.drawable.imagem_carregando)
                .error(R.drawable.imagem_carregando)
                .into(holder.imagemProduto);

        // Configura os cliques nos botões de adicionar e remover
        holder.botaoAdicionar.setOnClickListener(v -> listener.onAdicionarItemClick(produto));
        holder.botaoRemover.setOnClickListener(v -> listener.onRemoverItemClick(produto));
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    // ViewHolder para o CarrinhoAdapter
    public static class CarrinhoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeProduto;
        TextView precoProduto;
        TextView quantidadeProduto;
        ImageView imagemProduto;
        Button botaoAdicionar;
        Button botaoRemover;

        public CarrinhoViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeProduto = itemView.findViewById(R.id.nomeProdutoCarrinho);
            precoProduto = itemView.findViewById(R.id.precoProdutoCarrinho);
            quantidadeProduto = itemView.findViewById(R.id.quantidadeProdutoCarrinho);
            imagemProduto = itemView.findViewById(R.id.imagemProdutoCarrinho);
            botaoAdicionar = itemView.findViewById(R.id.botaoAdicionar);
            botaoRemover = itemView.findViewById(R.id.botaoRemover);
        }
    }
}
