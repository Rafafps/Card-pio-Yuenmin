package br.ufv.tp1_poo.view;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.ufv.tp1_poo.R;
import br.ufv.tp1_poo.controller.CarrinhoAdapter;
import br.ufv.tp1_poo.model.Carrinho;
import br.ufv.tp1_poo.model.Produto;

public class CarrinhoActivity extends AppCompatActivity {

    private Spinner spinnerFormaPagamento;
    private RecyclerView recyclerViewItens;
    private TextView subtotalTextView;
    private String selectedPayment;
    private double totalValue = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        // Inicializando os componentes da interface
        spinnerFormaPagamento = findViewById(R.id.spinnerFormaPagamento);
        recyclerViewItens = findViewById(R.id.recyclerViewItens);
        subtotalTextView = findViewById(R.id.subtotalText);

        // Configuração do RecyclerView
        recyclerViewItens.setLayoutManager(new LinearLayoutManager(this));

        // Criando o adapter para o Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.formas_pagamento, // Referência ao array no strings.xml
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFormaPagamento.setAdapter(adapter);

        // Listener do Spinner
        spinnerFormaPagamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedPayment = parentView.getItemAtPosition(position).toString();
                Toast.makeText(CarrinhoActivity.this, "Forma de pagamento: " + selectedPayment, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Caso nada seja selecionado
            }
        });

        // Exibindo o subtotal de valores
        updateSubtotal();
    }

    // Metodo para atualizar o subtotal com base nos itens do carrinho
    private void updateSubtotal() {
        totalValue = 0.0;  // Zera o valor total

        // Itera sobre todos os produtos do carrinho
        List<Produto> produtos = Carrinho.getListaDeProdutos(); // Obtém a lista de produtos do carrinho

        // Calcula o total com base nos produtos e suas quantidades
        for (Produto produto : produtos) {
            totalValue += produto.calculaPreco() * produto.getQuantidade();  // Calcula o preço total de cada item
        }

        // Atualiza o texto do subtotal
        subtotalTextView.setText("R$ " + String.format("%.2f", totalValue));  // Exibe o total com 2 casas decimais
    }

    // Metodo atualizarCarrinho para receber uma lista de produtos
    public void atualizarCarrinho(List<Produto> itensSelecionados) {
        if (itensSelecionados == null || itensSelecionados.isEmpty()) {
            subtotalTextView.setText("R$ 0,00");
            recyclerViewItens.setAdapter(null);
            Toast.makeText(this, "Carrinho vazio!", Toast.LENGTH_SHORT).show();
            return;
        }

        totalValue = 0.0;
        for (Produto item : itensSelecionados) {
            totalValue += item.calculaPreco() * item.getQuantidade();
        }

        subtotalTextView.setText("R$ " + String.format("%.2f", totalValue));

        CarrinhoAdapter adapter = new CarrinhoAdapter(itensSelecionados);
        recyclerViewItens.setAdapter(adapter);
    }
}

