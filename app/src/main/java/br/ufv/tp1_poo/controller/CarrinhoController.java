package br.ufv.tp1_poo.controller;

import android.widget.Toast;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import br.ufv.tp1_poo.model.Carrinho;
import br.ufv.tp1_poo.model.Produto;
import br.ufv.tp1_poo.view.CarrinhoActivity;

public class CarrinhoController {

    private final CarrinhoActivity view;
    private final ExecutorService executorService;

    public CarrinhoController(CarrinhoActivity view) {
        this.view = view;
        this.executorService = Executors.newSingleThreadExecutor(); // Gerenciador de threads
    }

    // Adiciona um produto ao carrinho de forma assíncrona
    public void adicionarProduto(Produto produto) {
        if (produto != null) {
            executorService.submit(() -> {
                Carrinho.adicionaProduto(produto);
                view.runOnUiThread(() -> {
                    Toast.makeText(view, "Produto adicionado ao carrinho!", Toast.LENGTH_SHORT).show();
                    view.atualizarCarrinho();
                });
            });
        }
    }

    // Remove um produto do carrinho
    public void removerProduto(Produto produto) {
        if (produto != null) {
            executorService.submit(() -> {
                boolean removido = Carrinho.removeProduto(produto);
                view.runOnUiThread(() -> {
                    if (removido) {
                        Toast.makeText(view, "Produto removido do carrinho!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(view, "Produto não encontrado no carrinho!", Toast.LENGTH_SHORT).show();
                    }
                    view.atualizarCarrinho();
                });
            });
        }
    }

    // Finaliza o carrinho (opcional: exemplo para limpar o carrinho)
    public void finalizarCompra() {
        executorService.submit(() -> {
            Carrinho.limpa();
            view.runOnUiThread(() -> {
                Toast.makeText(view, "Compra finalizada!", Toast.LENGTH_SHORT).show();
                view.atualizarCarrinho();
            });
        });
    }
}
