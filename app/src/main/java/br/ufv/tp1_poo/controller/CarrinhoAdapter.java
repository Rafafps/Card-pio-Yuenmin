package br.ufv.tp1_poo.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.ufv.tp1_poo.R;
import br.ufv.tp1_poo.model.Produto;
public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.CarrinhoViewHolder> {
    private final List<Produto> produtos;
    private final OnCarrinhoClickListener listener;
    public interface OnCarrinhoClickListener {
        void onRemoverItemClick(Produto produto);
        void onAdicionarItemClick(Produto produto);
    }
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

        holder.nomeProduto.setText(produto.getNome());
        holder.precoProduto.setText(String.format("R$ %.2f", produto.getPreco() / 100.0));
        holder.quantidadeProduto.setText("Quantidade: " + produto.getQuantidade());

        holder.botaoAdicionar.setOnClickListener(v -> {
            listener.onAdicionarItemClick(produto);
            notifyItemChanged(position); // Atualiza o item no RecyclerView
        });

        holder.botaoRemover.setOnClickListener(v -> {
            listener.onRemoverItemClick(produto);
            notifyItemChanged(position); // Atualiza o item no RecyclerView
        });
    }
    @Override
    public int getItemCount() {
        return produtos.size();
    }
    public static class CarrinhoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeProduto;
        TextView precoProduto;
        TextView quantidadeProduto;
        Button botaoAdicionar;
        Button botaoRemover;
        public CarrinhoViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeProduto = itemView.findViewById(R.id.nomeProduto);
            precoProduto = itemView.findViewById(R.id.precoProduto);
            quantidadeProduto = itemView.findViewById(R.id.quantidadeProduto);
            botaoAdicionar = itemView.findViewById(R.id.botaoAdicionar);
            botaoRemover = itemView.findViewById(R.id.botaoRemover);
        }
    }
}
