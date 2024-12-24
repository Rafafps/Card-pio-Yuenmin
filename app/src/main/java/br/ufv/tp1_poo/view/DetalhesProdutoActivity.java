package br.ufv.tp1_poo.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import br.ufv.tp1_poo.R;
import br.ufv.tp1_poo.model.Produto;

public class DetalhesProdutoActivity extends AppCompatActivity {

    private ImageView imagemProduto;
    private TextView nomeProduto, descricaoProduto, quantidadeProduto, botaoVoltar, botaoAdicionar;
    private EditText observacaoProduto;
    private Button botaoAumentar, botaoDiminuir;

    private Produto produto;
    private int quantidade = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);

        // Inicialização das Views
        imagemProduto = findViewById(R.id.imagemProduto);
        nomeProduto = findViewById(R.id.nomeProduto);
        descricaoProduto = findViewById(R.id.descricaoProduto);
        quantidadeProduto = findViewById(R.id.quantidadeProduto);
        observacaoProduto = findViewById(R.id.observacaoProduto);
        botaoAdicionar = findViewById(R.id.botaoAdicionar);
        botaoAumentar = findViewById(R.id.botaoAumentar);
        botaoDiminuir = findViewById(R.id.botaoDiminuir);
        botaoVoltar = findViewById(R.id.botaoVoltar);

        // Obter o produto passado via Intent
        produto = (Produto) getIntent().getSerializableExtra("produto");

        if (produto != null) {
            carregarProduto();
        }

        // Configuração inicial do botão "Diminuir"
        botaoDiminuir.setEnabled(false);
        botaoDiminuir.setAlpha(0.5f);

        // Configurar os listeners dos botões
        botaoAumentar.setOnClickListener(v -> alterarQuantidade(1));
        botaoDiminuir.setOnClickListener(v -> alterarQuantidade(-1));
        botaoVoltar.setOnClickListener(v -> finish());
    }

    private void carregarProduto() {
        Glide.with(this).load(produto.getImagem()).into(imagemProduto);
        nomeProduto.setText(produto.getNome());
        descricaoProduto.setText(produto.getDescricao());
        botaoAdicionar.setText("Adicionar R$ " + String.format("%.2f", produto.getPreco() / 100.0));
    }

    private void alterarQuantidade(int delta) {
        quantidade = Math.max(1, quantidade + delta);
        quantidadeProduto.setText(String.valueOf(quantidade));
        atualizarPreco();

        // Atualizar estado do botão "Diminuir"
        if (quantidade == 1) {
            botaoDiminuir.setEnabled(false);
            botaoDiminuir.setAlpha(0.5f);
        } else {
            botaoDiminuir.setEnabled(true);
            botaoDiminuir.setAlpha(1.0f);
        }
    }

    private void atualizarPreco() {
        String precoFormatado = String.format("%.2f", (produto.getPreco() * quantidade) / 100.0);
        botaoAdicionar.setText("Adicionar R$ " + precoFormatado);
    }
}
