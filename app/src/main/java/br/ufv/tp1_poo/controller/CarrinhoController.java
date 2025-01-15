package br.ufv.tp1_poo.controller;

import android.content.Context;
import android.widget.Toast;
import java.util.List;
import br.ufv.tp1_poo.model.Carrinho;
import br.ufv.tp1_poo.model.Produto;
import br.ufv.tp1_poo.view.CarrinhoActivity;
import br.ufv.tp1_poo.view.MainActivity;

public class CarrinhoController {

    private final Context context;
    private CarrinhoAdapter adapter;

    public CarrinhoController(Context context) {
        this.context = context;
    }

    // ... (outros métodos: atualizarAdapter, adicionarProduto, removerProduto, finalizarCompra)

    public void verificarEstadoCarrinho(MainActivity mainActivity) {
        if (Carrinho.estaVazio()) { // Usando o método estaVazio()
            mainActivity.abrirCarrinhoVazio();
        } else {
            mainActivity.abrirCarrinhoCheio();
        }
    }

    public void adicionarProduto(Produto produto, CarrinhoActivity carrinhoActivity) {
        if (produto != null) {
            Carrinho.adicionaProduto(produto);
            atualizarCarrinhoNaActivity(carrinhoActivity);
        }
    }

    public void removerProduto(Produto produto, CarrinhoActivity carrinhoActivity) {
        if (produto != null) {
            Carrinho.removeProduto(produto);
            atualizarCarrinhoNaActivity(carrinhoActivity);
            Toast.makeText(context, produto.getNome() + " removido do carrinho!", Toast.LENGTH_SHORT).show();
        }
    }

    public void finalizarCompra(CarrinhoActivity carrinhoActivity) {
        Carrinho.limpa();
        atualizarCarrinhoNaActivity(carrinhoActivity);
        Toast.makeText(context, "Compra finalizada!", Toast.LENGTH_SHORT).show();
    }

    private void atualizarCarrinhoNaActivity(CarrinhoActivity carrinhoActivity) {
        if (carrinhoActivity != null) {
            carrinhoActivity.atualizarCarrinho(Carrinho.getListaDeProdutos());
        }
    }


    public void atualizarAdapter(List<Produto> itensSelecionados, CarrinhoActivity carrinhoActivity) {
        if (adapter == null) {
            adapter = new CarrinhoAdapter(itensSelecionados, position -> {
                Produto produtoRemovido = itensSelecionados.get(position);
                itensSelecionados.remove(position);
                adapter.notifyItemRemoved(position);
                if (carrinhoActivity != null) {
                    carrinhoActivity.updateSubtotal();
                }
                Toast.makeText(context, produtoRemovido.getNome() + " removido do carrinho!", Toast.LENGTH_SHORT).show();
            });
            if (carrinhoActivity != null) {
                carrinhoActivity.recyclerViewItens.setAdapter(adapter);
            }
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}