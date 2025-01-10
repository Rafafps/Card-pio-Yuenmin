package br.ufv.tp1_poo.controller;

import android.widget.Toast;
import java.util.List;
import br.ufv.tp1_poo.model.Carrinho;
import br.ufv.tp1_poo.model.Produto;
import br.ufv.tp1_poo.view.CarrinhoActivity;
import br.ufv.tp1_poo.controller.CarrinhoAdapter;

public class CarrinhoController {

    private final CarrinhoActivity view;

    public CarrinhoController(CarrinhoActivity view) {
        this.view = view;
    }

    // Método para atualizar o adapter do RecyclerView
    public void atualizarAdapter(List<Produto> itensSelecionados) {
        // Cria o adapter e configura o RecyclerView
        CarrinhoAdapter adapter = new CarrinhoAdapter(itensSelecionados);
        view.recyclerViewItens.setAdapter(adapter);
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
