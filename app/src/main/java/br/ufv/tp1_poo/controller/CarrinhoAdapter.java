package br.ufv.tp1_poo.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import br.ufv.tp1_poo.R;
import br.ufv.tp1_poo.model.Produto;
import java.util.List;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.CarrinhoViewHolder> {

    private List<Produto> produtos;

    public CarrinhoAdapter(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public CarrinhoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_produto, parent, false); //não tem esse atributo, mas eu não achei o q o substitui
        return new CarrinhoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CarrinhoViewHolder holder, int position) {
        Produto produto = produtos.get(position);
        holder.nomeProduto.setText(produto.getNome());
        holder.quantidadeProduto.setText("Quantidade: " + produto.getQuantidade());
        holder.precoProduto.setText("R$ " + String.format("%.2f", (double) produto.calculaPreco()));
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public void atualizarLista(List<Produto> novosProdutos) {
        this.produtos = novosProdutos;
        notifyDataSetChanged();
    }

    public static class CarrinhoViewHolder extends RecyclerView.ViewHolder {
        public TextView nomeProduto, quantidadeProduto, precoProduto;

        public CarrinhoViewHolder(View itemView) {
            super(itemView);
            nomeProduto = itemView.findViewById(R.id.nomeProduto);
            quantidadeProduto = itemView.findViewById(R.id.quantidadeProduto);
            precoProduto = itemView.findViewById(R.id.precoProduto);
        }
    }
}
