package br.ufv.tp1_poo.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
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
    private TextView subtotal, taxaServico, total;
    private Button botaoSelecionarPagamento, botaoVoltar, botaoLimparCarrinho;
    private CarrinhoAdapter carrinhoAdapter;
    private List<Produto> produtos;
    private Spinner spinnerFormaPagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrinhoactivity);

        // Inicialização dos componentes
        recyclerViewCarrinho = findViewById(R.id.recyclerCarrinho);
        subtotal = findViewById(R.id.subtotal);
        taxaServico = findViewById(R.id.taxaServico);
        total = findViewById(R.id.total);
        spinnerFormaPagamento = findViewById(R.id.spinnerFormaPagamento);
        botaoSelecionarPagamento = findViewById(R.id.finalizarPedido);  // Alterado para o botão correto
        botaoVoltar = findViewById(R.id.botaoVoltar); // Adicionei a referência ao botão de voltar, caso precise
        botaoLimparCarrinho = findViewById(R.id.botaoLimparCarrinho); // Referência do botão de limpar carrinho, caso precise

        // Inicialização da lista de produtos (simulação para teste)
        produtos = new ArrayList<>();
        produtos.add(new Produto("Mix de tomate", 32.99, 1));
        produtos.add(new Produto("Rolinho primavera", 24.50, 2));

        // Configuração do Adapter
        carrinhoAdapter = new CarrinhoAdapter(produtos, new CarrinhoAdapter.OnCarrinhoClickListener() {
            @Override
            public void onRemoverItemClick(Produto produto) {
                if (produto.getQuantidade() > 1) {
                    produto.setQuantidade(produto.getQuantidade() - 1);
                } else {
                    produtos.remove(produto);
                }
                carrinhoAdapter.notifyDataSetChanged();
                atualizarResumoValores();
            }

            @Override
            public void onAdicionarItemClick(Produto produto) {
                produto.setQuantidade(produto.getQuantidade() + 1);
                carrinhoAdapter.notifyDataSetChanged();
                atualizarResumoValores();
            }
        });

        recyclerViewCarrinho.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCarrinho.setAdapter(carrinhoAdapter);

        // Botão de limpar carrinho
        botaoLimparCarrinho.setOnClickListener(v -> limparCarrinho());

        // Botão de voltar
        botaoVoltar.setOnClickListener(v -> finish());

        // Botão de selecionar pagamento
        botaoSelecionarPagamento.setOnClickListener(v -> {
            if (produtos.isEmpty()) {
                Toast.makeText(CarrinhoActivity.this, "Carrinho vazio! Adicione produtos antes de pagar.", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(CarrinhoActivity.this, PagamentoActivity.class);
                // Passar dados para a próxima tela (se necessário)
                startActivity(intent);
            }
        });

        // Atualizar valores do carrinho
        atualizarResumoValores();
    }

    // Limpar todos os itens do carrinho
    private void limparCarrinho() {
        produtos.clear();
        carrinhoAdapter.notifyDataSetChanged();
        atualizarResumoValores();
        Toast.makeText(this, "Carrinho limpo!", Toast.LENGTH_SHORT).show();
    }

    // Atualizar valores (subtotal, taxa de serviço e total)
    private void atualizarResumoValores() {
        double subtotalValor = 0.0;
        for (Produto produto : produtos) {
            subtotalValor += produto.getPreco() * produto.getQuantidade();
        }

        double taxaServicoValor = subtotalValor * 0.05; // Exemplo: taxa de 5%
        double totalValor = subtotalValor + taxaServicoValor;

        // Atualizando os textos
        subtotal.setText(String.format("Subtotal: R$%.2f", subtotalValor));
        taxaServico.setText(String.format("Taxa de serviço: R$%.2f", taxaServicoValor));
        total.setText(String.format("Total: R$%.2f", totalValor));
    }
}
