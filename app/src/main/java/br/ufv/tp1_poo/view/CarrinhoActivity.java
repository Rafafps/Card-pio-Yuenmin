package br.ufv.tp1_poo.view;

import android.content.Intent;
import android.os.Bundle;
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
    private List<Produto> carrinho = new ArrayList<>();
    private Spinner spinnerFormaPagamento;
    private TextView textViewTotal, botaoVoltar, botaoFinalizarPedido;

    private static final double TAXA_SERVICO = 2.80; // Taxa fixa de serviço

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        // Inicializando as Views
        recyclerViewCarrinho = findViewById(R.id.recyclerViewItens);
        spinnerFormaPagamento = findViewById(R.id.spinnerFormaPagamento);
        textViewTotal = findViewById(R.id.textTotal);
        botaoVoltar = findViewById(R.id.botaoVoltar);
        botaoFinalizarPedido = findViewById(R.id.botaoFinalizarPedido);

        // Verifica se o carrinho está vazio no início
        if (carrinho.isEmpty()) {
            // Adiciona produtos de exemplo ao carrinho
            adicionarProdutoExemplo();

            // Redireciona para a CarrinhoVazioActivity se não houver produtos
            if (carrinho.isEmpty()) {
                Intent intent = new Intent(CarrinhoActivity.this, CarrinhoVazioActivity.class);
                startActivity(intent);
                finish(); // Fecha a CarrinhoActivity
                return; // Impede que o restante do código execute
            }
        }

        // Configuração do Spinner
        configurarSpinnerFormaPagamento();

        // Inicializa o adapter
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

        // Atualiza o total
        atualizarTotal();

        // Lógica do botão Voltar
        botaoVoltar.setOnClickListener(v -> finish());

        // Lógica do botão Finalizar Pedido
        botaoFinalizarPedido.setOnClickListener(v -> {
            // Redireciona para a tela de Pedido Finalizado
            Intent intent = new Intent(CarrinhoActivity.this, PedidoFinalizadoActivity.class);
            startActivity(intent);
            finish(); // Finaliza a CarrinhoActivity após o redirecionamento
        });
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

    private void adicionarProdutoExemplo() {
        // Criando dois produtos de exemplo com base no JSON fornecido
        Produto produto1 = new Produto(2850/100, "Guacamole", 1);
        Produto produto2 = new Produto(2000/100,"Mix com queijo azul", 1);

        // Adicionando ao carrinho
        carrinho.add(produto1);
        carrinho.add(produto2);
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

        // Atualizar o RecyclerView
        carrinhoAdapter.notifyDataSetChanged();

        // Atualiza o total
        atualizarTotal();
    }

    private void removerProdutoDoCarrinho(Produto produto) {
        if (produto.getQuantidade() > 1) {
            produto.setQuantidade(produto.getQuantidade() - 1);
        } else {
            carrinho.remove(produto);
        }

        // Atualizar o RecyclerView
        carrinhoAdapter.notifyDataSetChanged();

        // Atualiza o total
        atualizarTotal();
    }

    private void atualizarTotal() {
        double total = 0;

        // Calcula o total
        for (Produto produto : carrinho) {
            total += produto.getPreco() * produto.getQuantidade();
        }

        // Adiciona a taxa de serviço
        total += TAXA_SERVICO;

        // Atualiza o TextView do total
        textViewTotal.setText("Total: R$ " + String.format("%.2f", total));
    }

}
