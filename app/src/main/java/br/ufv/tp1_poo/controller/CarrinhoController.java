package br.ufv.tp1_poo.controller;

import android.widget.Toast;
import java.util.List;
import br.ufv.tp1_poo.model.Carrinho;
import br.ufv.tp1_poo.model.Produto;
import br.ufv.tp1_poo.view.CarrinhoActivity;

public class CarrinhoController {

    private final CarrinhoActivity view;
    private CarrinhoAdapter adapter;

    public CarrinhoController(CarrinhoActivity view) {
        this.view = view;
    }

    // Método para atualizar o adapter do RecyclerView
    public void atualizarAdapter(List<Produto> itensSelecionados) {
        if (adapter == null) {
            adapter = new CarrinhoAdapter(itensSelecionados, position -> {
                // Remove o item selecionado
                Produto produtoRemovido = itensSelecionados.get(position);
                itensSelecionados.remove(position);

                // Atualiza a interface
                adapter.notifyItemRemoved(position);
                view.updateSubtotal();

                // Mensagem ao remover produto
                Toast.makeText(view, produtoRemovido.getNome() + " removido do carrinho!", Toast.LENGTH_SHORT).show();
            });

            // Define o adapter no RecyclerView
            view.recyclerViewItens.setAdapter(adapter);
        } else {
            // Atualiza os dados no adapter existente
            adapter.notifyDataSetChanged();
        }
    }

    // Adiciona produto ao carrinho
    public void adicionarProduto(Produto produto) {
        if (produto != null) {
            Carrinho.adicionaProduto(produto);
            view.atualizarCarrinho(Carrinho.getListaDeProdutos());
        }
    }

    // Remove produto do carrinho
    public void removerProduto(Produto produto) {
        if (produto != null) {
            boolean removido = Carrinho.removeProduto(produto);
            view.atualizarCarrinho(Carrinho.getListaDeProdutos());
            String mensagem = removido ? "Produto removido do carrinho!" : "Produto não encontrado no carrinho!";
            Toast.makeText(view, mensagem, Toast.LENGTH_SHORT).show();
        }
    }

    // Finaliza a compra
    public void finalizarCompra() {
        Carrinho.limpa();
        view.atualizarCarrinho(Carrinho.getListaDeProdutos());
        Toast.makeText(view, "Compra finalizada!", Toast.LENGTH_SHORT).show();
    }
}
