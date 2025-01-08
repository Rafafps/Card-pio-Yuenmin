package br.ufv.tp1_poo.view;

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
import br.ufv.tp1_poo.R;

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
        subtotalTextView = findViewById(R.id.subtotalTextView);

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

        updateSubtotal();
    }

    private void updateSubtotal() {
        totalValue = 100.00;
        subtotalTextView.setText("R$ " + totalValue);
    }
}
