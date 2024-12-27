package br.ufv.tp1_poo.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import br.ufv.tp1_poo.R;

public class CarrinhoActivity extends AppCompatActivity {

    private TextView textoCarrinho;
    private Button botaoAdicionarItem;
    private Button botaoRemoverItem;
    private Button botaoSelecionarPagamento;
    private Button botaoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        textoCarrinho = findViewById(R.id.textoCarrinho);
        botaoAdicionarItem = findViewById(R.id.botaoAdicionarItem);
        botaoRemoverItem = findViewById(R.id.botaoRemoverItem);
        botaoSelecionarPagamento = findViewById(R.id.botaoSelecionarPagamento);
        botaoVoltar = findViewById(R.id.botaoVoltar);

        botaoVoltar.setOnClickListener(v -> finish());

        botaoAdicionarItem.setOnClickListener(v -> {
            textoCarrinho.setText("Item adicionado ao carrinho.");
        });

        botaoRemoverItem.setOnClickListener(v -> {
            textoCarrinho.setText("Item removido do carrinho.");
        });

        botaoSelecionarPagamento.setOnClickListener(v -> {
            Intent intent = new Intent(CarrinhoActivity.this, PagamentoActivity.class);
            startActivity(intent);
        });
    }
}
