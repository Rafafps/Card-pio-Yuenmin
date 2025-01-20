package br.ufv.tp1_poo.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import br.ufv.tp1_poo.R;
import br.ufv.tp1_poo.model.Carrinho;

public class CarrinhoVazioActivity extends AppCompatActivity {
    private TextView botaoVoltar;
    private TextView botaoVoltarInicial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho_vazio);

        botaoVoltar = findViewById(R.id.botaoVoltar);
        botaoVoltarInicial = findViewById(R.id.botaoVoltarInicial);

        TextView botaoVoltar = findViewById(R.id.botaoVoltar);
        botaoVoltar.setOnClickListener(view -> {
            if (Carrinho.estaVazio()) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                finish();
            }
        });
    }
}