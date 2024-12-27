package br.ufv.tp1_poo.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView textoCarrinho;
    private Button botaoSelecionarPagamento;
    private Button botaoVoltar;
    private CarrinhoAdapter carrinhoAdapter;
    private List<Produto> produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        recyclerViewCarrinho = findViewById(R.id.recyclerViewCarrinho);
        textoCarrinho = findViewById(R.id.textoCarrinho);
        botaoSelecionarPagamento = findViewById(R.id.botaoSelecionarPagamento);
        botaoVoltar = findViewById(R.id.botaoVoltar);

        //olhar como vamos exibir os produtos do carrinho, se vamos colocar manualmente ou pegar de um banco

        carrinhoAdapter = new CarrinhoAdapter(produtos, new CarrinhoAdapter.OnCarrinhoClickListener() {
            @Override
            public void onRemoverItemClick(Produto produto) {
                if (produto.getQuantidade() > 1) {
                    produto.setQuantidade(produto.getQuantidade() - 1);
                } else {
                    produtos.remove(produto);
                }
                carrinhoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAdicionarItemClick(Produto produto) {
                produto.setQuantidade(produto.getQuantidade() + 1);
                carrinhoAdapter.notifyDataSetChanged();
            }
        });

        recyclerViewCarrinho.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCarrinho.setAdapter(carrinhoAdapter);

        botaoVoltar.setOnClickListener(v -> finish());

        botaoSelecionarPagamento.setOnClickListener(v -> {
            if (produtos.isEmpty()) {
                Toast.makeText(CarrinhoActivity.this, "Carrinho vazio! Adicione produtos antes de pagar.", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(CarrinhoActivity.this, PagamentoActivity.class);
                startActivity(intent);
            }
        });
    }
}
