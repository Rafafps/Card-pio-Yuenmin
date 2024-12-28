package br.ufv.tp1_poo.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

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
    private List<Produto> carrinho = new ArrayList<>(); // Carrinho de compras
    private Spinner spinnerFormaPagamento; // Spinner para forma de pagamento
    private TextView textVazio; // TextView para mensagem de carrinho vazio

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrinhoactivity);

        recyclerViewCarrinho = findViewById(R.id.recyclerViewCarrinho);
        spinnerFormaPagamento = findViewById(R.id.spinnerFormaPagamento); // Vincula o Spinner do layout


        // Configuração do Spinner
        configurarSpinnerFormaPagamento();

        // Inicialize o adapter
        carrinhoAdapter = new CarrinhoAdapter(carrinho, new CarrinhoAdapter.OnCarrinhoClickListener() {
            @Override
            public void onAdicionarItemClick(Produto produto) {
                // Aumenta a quantidade do produto no carrinho
                adicionarProdutoAoCarrinho(produto);
            }

            @Override
            public void onRemoverItemClick(Produto produto) {
                // Remove o produto do carrinho
                removerProdutoDoCarrinho(produto);
            }
        });

        recyclerViewCarrinho.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCarrinho.setAdapter(carrinhoAdapter);

        // Atualizar a visibilidade do RecyclerView dependendo se o carrinho tem itens
        atualizarVisibilidadeCarrinho();
    }

    // Método para configurar o Spinner
    private void configurarSpinnerFormaPagamento() {
        // Lista de formas de pagamento
        List<String> formasDePagamento = new ArrayList<>();
        formasDePagamento.add("Cartão de Crédito");
        formasDePagamento.add("Cartão de Débito");
        formasDePagamento.add("Pix");
        formasDePagamento.add("Dinheiro");

        // Configura o adapter do Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, formasDePagamento);
        spinnerFormaPagamento.setAdapter(adapter);
    }

    // Método para adicionar um produto ao carrinho
    private void adicionarProdutoAoCarrinho(Produto produto) {
        boolean produtoEncontrado = false;

        // Verifica se o produto já existe no carrinho
        for (Produto p : carrinho) {
            if (p.getNome().equals(produto.getNome())) {
                p.setQuantidade(p.getQuantidade() + 1); // Aumenta a quantidade
                produtoEncontrado = true;
                break;
            }
        }

        // Se não encontrou, adiciona um novo produto
        if (!produtoEncontrado) {
            carrinho.add(produto);
        }

        carrinhoAdapter.notifyDataSetChanged();  // Atualiza o adapter
        atualizarVisibilidadeCarrinho();  // Atualiza a visibilidade do RecyclerView
    }

    // Método para remover um produto do carrinho
    private void removerProdutoDoCarrinho(Produto produto) {
        if (produto.getQuantidade() > 1) {
            produto.setQuantidade(produto.getQuantidade() - 1);  // Diminui a quantidade
        } else {
            carrinho.remove(produto);  // Remove o produto do carrinho
        }

        carrinhoAdapter.notifyDataSetChanged();  // Atualiza o adapter
        atualizarVisibilidadeCarrinho();  // Atualiza a visibilidade do RecyclerView
    }

    // Atualiza a visibilidade do RecyclerView dependendo se o carrinho tem itens
    private void atualizarVisibilidadeCarrinho() {
        if (carrinho.isEmpty()) {
            recyclerViewCarrinho.setVisibility(View.GONE);  // Oculta o RecyclerView
            textVazio.setVisibility(View.VISIBLE);  // Exibe o TextView "Carrinho vazio"
        } else {
            recyclerViewCarrinho.setVisibility(View.VISIBLE);  // Exibe o RecyclerView
            textVazio.setVisibility(View.GONE);  // Oculta o TextView "Carrinho vazio"
        }
    }
}
