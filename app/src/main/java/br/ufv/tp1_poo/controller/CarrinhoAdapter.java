package br.ufv.tp1_poo.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import br.ufv.tp1_poo.R;
import br.ufv.tp1_poo.model.Produto;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.CarrinhoViewHolder> {

    private final List<Produto> produtos;

    public CarrinhoAdapter(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public CarrinhoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        return new CarrinhoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CarrinhoViewHolder holder, int position) {
        Produto produto = produtos.get(position);
        holder.nomeProduto.setText(produto.getNome());
        holder.quantidade.setText("Qtd: " + produto.getQuantidade());
        holder.preco.setText("Preço: R$ " + String.format("%.2f", produto.calculaPreco()));
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public static class CarrinhoViewHolder extends RecyclerView.ViewHolder {

        TextView nomeProduto, quantidade, preco;

        public CarrinhoViewHolder(View itemView) {
            super(itemView);
            nomeProduto = itemView.findViewById(R.id.nomeProduto);
            quantidade = itemView.findViewById(R.id.quantidadeProduto);
            preco = itemView.findViewById(R.id.precoProduto);
        }
    }
}
