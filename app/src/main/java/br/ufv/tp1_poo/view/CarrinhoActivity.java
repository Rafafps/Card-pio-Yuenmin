package br.ufv.tp1_poo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.ufv.tp1_poo.R;
import br.ufv.tp1_poo.controller.CarrinhoAdapter;
import br.ufv.tp1_poo.model.Produto;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCarrinho;
    private CarrinhoAdapter carrinhoAdapter;
    private List<Produto> carrinho = new ArrayList<>();
    private Spinner spinnerFormaPagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        // Inicializando as Views
        recyclerViewCarrinho = findViewById(R.id.recyclerViewItens);
        spinnerFormaPagamento = findViewById(R.id.spinnerFormaPagamento);

        // Verifica se o carrinho está vazio no início
        if (carrinho.isEmpty()) {
            // Redireciona para a CarrinhoVazioActivity
            Intent intent = new Intent(CarrinhoActivity.this, CarrinhoVazioActivity.class);
            startActivity(intent);
            finish(); // Fecha a CarrinhoActivity
            return; // Impede que o restante do código execute
        }

        // Configuração do Spinner
        configurarSpinnerFormaPagamento();

        // Inicialize o adapter
        carrinhoAdapter = new CarrinhoAdapter(carrinho, new CarrinhoAdapter.OnCarrinhoClickListener() {
            @Override
            public void onAdicionarItemClick(Produto produto) {
                adicionarProdutoAoCarrinho(produto);
            }

            @Override
            public void onRemoverItemClick(Produto produto) {
                removerProdutoDoCarrinho(produto);
            }
        });

        recyclerViewCarrinho.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCarrinho.setAdapter(carrinhoAdapter);
    }

    private void configurarSpinnerFormaPagamento() {
        List<String> formasDePagamento = new ArrayList<>();
        formasDePagamento.add("Cartão de Crédito");
        formasDePagamento.add("Cartão de Débito");
        formasDePagamento.add("Pix");
        formasDePagamento.add("Dinheiro");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, formasDePagamento);
        spinnerFormaPagamento.setAdapter(adapter);
    }

    private void adicionarProdutoAoCarrinho(Produto produto) {
        boolean produtoEncontrado = false;
        for (Produto p : carrinho) {
            if (p.getNome().equals(produto.getNome())) {
                p.setQuantidade(p.getQuantidade() + 1);
                produtoEncontrado = true;
                break;
            }
        }

        if (!produtoEncontrado) {
            carrinho.add(produto);
        }

        carrinhoAdapter.notifyDataSetChanged();
    }

    private void removerProdutoDoCarrinho(Produto produto) {
        if (produto.getQuantidade() > 1) {
            produto.setQuantidade(produto.getQuantidade() - 1);
        } else {
            carrinho.remove(produto);
        }

        carrinhoAdapter.notifyDataSetChanged();
    }
}
