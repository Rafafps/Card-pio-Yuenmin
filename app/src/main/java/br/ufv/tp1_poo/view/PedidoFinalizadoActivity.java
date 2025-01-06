package br.ufv.tp1_poo.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import br.ufv.tp1_poo.R;

public class PedidoFinalizadoActivity extends AppCompatActivity {

    private TextView botaoVoltarInicial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_finalizado);

        botaoVoltarInicial = findViewById(R.id.botaoVoltarInicial);

        botaoVoltarInicial.setOnClickListener(v -> {
            // Redireciona para a MainActivity
            Intent intent = new Intent(PedidoFinalizadoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
