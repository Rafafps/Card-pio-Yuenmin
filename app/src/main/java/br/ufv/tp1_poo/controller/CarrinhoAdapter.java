package br.ufv.tp1_poo.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import br.ufv.tp1_poo.R;
import br.ufv.tp1_poo.model.Produto;

import java.util.List;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.CarrinhoViewHolder> {

    private List<Produto> carrinho;
    private OnCarrinhoClickListener onCarrinhoClickListener;

    public CarrinhoAdapter(List<Produto> carrinho, OnCarrinhoClickListener onCarrinhoClickListener) {
        this.carrinho = carrinho;
        this.onCarrinhoClickListener = onCarrinhoClickListener;
    }

    @Override
    public CarrinhoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrinho, parent, false);
        return new CarrinhoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarrinhoViewHolder holder, int position) {
        Produto produto = carrinho.get(position);

        // Configurar dados do produto
        holder.nomeProduto.setText(produto.getNome());
        holder.precoProduto.setText("R$ " + produto.getPreco());
        holder.quantidadeProduto.setText(String.valueOf(produto.getQuantidade()));

        holder.btnAdicionar.setOnClickListener(v -> onCarrinhoClickListener.onAdicionarItemClick(produto));
        holder.btnRemover.setOnClickListener(v -> onCarrinhoClickListener.onRemoverItemClick(produto));
    }

    @Override
    public int getItemCount() {
        return carrinho.size();
    }

    public static class CarrinhoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeProduto, precoProduto, quantidadeProduto;
        ImageView imagemProduto;
        ImageButton btnAdicionar, btnRemover;

        public CarrinhoViewHolder(View itemView) {
            super(itemView);

            nomeProduto = itemView.findViewById(R.id.nomeProduto);
            precoProduto = itemView.findViewById(R.id.precoProduto);
            quantidadeProduto = itemView.findViewById(R.id.quantidadeProduto);
            imagemProduto = itemView.findViewById(R.id.imagemProduto);
            btnRemover = itemView.findViewById(R.id.btnRemoverProduto);
        }
    }

    public interface OnCarrinhoClickListener {
        void onAdicionarItemClick(Produto produto);
        void onRemoverItemClick(Produto produto);
    }
}
