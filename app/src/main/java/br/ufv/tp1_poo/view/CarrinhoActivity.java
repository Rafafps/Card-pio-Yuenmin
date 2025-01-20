package br.ufv.tp1_poo.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import br.ufv.tp1_poo.controller.CarrinhoController;
import br.ufv.tp1_poo.model.Carrinho;
import br.ufv.tp1_poo.model.Produto;

public class CarrinhoActivity extends AppCompatActivity {

    private Spinner spinnerFormaPagamento;
    public RecyclerView recyclerViewItens;
    private TextView subtotalTextView;
    private String selectedPayment;
    private CarrinhoController carrinhoController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        carrinhoController = new CarrinhoController(this);

        spinnerFormaPagamento = findViewById(R.id.spinnerFormaPagamento);
        recyclerViewItens = findViewById(R.id.recyclerViewItens);
        subtotalTextView = findViewById(R.id.subtotalText);

        recyclerViewItens.setLayoutManager(new LinearLayoutManager(this));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.formas_pagamento, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFormaPagamento.setAdapter(adapter);

        spinnerFormaPagamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedPayment = parentView.getItemAtPosition(position).toString();
                Toast.makeText(CarrinhoActivity.this, "Forma de pagamento: " + selectedPayment, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        atualizarCarrinho(Carrinho.getListaDeProdutos());
        //Volta
        TextView botaoVoltar = findViewById(R.id.botaoVoltar);
        botaoVoltar.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        //Finaliza
        TextView botaoFinalizarCompra = findViewById(R.id.botaoFinalizarCompra);
        TextView botaoLimpaCarrinho = findViewById(R.id.limparCarrinho);

        botaoFinalizarCompra.setOnClickListener(v -> {
            // Iniciar a nova atividade
            Intent intent = new Intent(CarrinhoActivity.this, PedidoFinalizadoActivity.class);
            startActivity(intent);
            Toast.makeText(CarrinhoActivity.this, "Compra finalizada!", Toast.LENGTH_SHORT).show();
            carrinhoController.finalizarCompra(this);
        });
        botaoLimpaCarrinho.setOnClickListener(v -> {
            Intent intent = new Intent(CarrinhoActivity.this, CarrinhoVazioActivity.class);
            startActivity(intent);
            Toast.makeText(CarrinhoActivity.this, "Carrinho limpado!", Toast.LENGTH_SHORT).show();
            carrinhoController.limpaCarrinho();
        });
    }
    @SuppressLint("DefaultLocale")
    public void updateSubtotal() {
        subtotalTextView.setText("R$ " + String.format("%.2f", Carrinho.calculaTotal())); // Usando Carrinho.calculaTotal()
    }

    public void atualizarCarrinho(List<Produto> produtos) {
        Log.d("CarrinhoActivity", "Atualizando carrinho com " + produtos.size() + " produtos.");
        carrinhoController.atualizarAdapter(produtos, this);
        updateSubtotal();
    }
}
