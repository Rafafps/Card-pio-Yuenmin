package br.ufv.tp1_poo.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import br.ufv.tp1_poo.R;
import br.ufv.tp1_poo.model.Produto;

public class DetalhesProdutoActivity extends AppCompatActivity {

    private ImageView imagemProduto;
    private TextView nomeProduto, descricaoProduto, quantidadeProduto, botaoVoltar, botaoAdicionar, botaoAumentar, botaoDiminuir;
    private EditText observacaoProduto;


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
        } else {
            // Tratar caso o produto seja nulo
            nomeProduto.setText("Produto não encontrado");
            botaoAdicionar.setEnabled(false);
        }

        // Configuração inicial do botão "Diminuir"
        atualizarEstadoBotaoDiminuir();

        // Configurar os listeners dos botões
        botaoAumentar.setOnClickListener(v -> alterarQuantidade(1));
        botaoDiminuir.setOnClickListener(v -> alterarQuantidade(-1));
        botaoVoltar.setOnClickListener(v -> finish());

        botaoAdicionar.setOnClickListener(v -> {
            if (produto != null) {
                // Configurar a quantidade do produto
                produto.setQuantidade(quantidade); // Certifique-se que há o método setQuantidade no modelo Produto
                // Criar intent para abrir a tela de Carrinho
                Intent intent = new Intent(DetalhesProdutoActivity.this, CarrinhoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void carregarProduto() {
        try {
            // Carregar imagem do produto
            int resourceId = getResources().getIdentifier(produto.getImagem(), "drawable", getPackageName());
            if (resourceId != 0) {
                Glide.with(this).load(resourceId).into(imagemProduto);
            } else {
                Glide.with(this).load(R.drawable.imagem_carregando).into(imagemProduto); // Imagem padrão
            }

            // Configurar texto dos campos
            nomeProduto.setText(produto.getNome());
            descricaoProduto.setText(produto.getDescricao());

            // Formatar texto manualmente
            String precoFormatado = String.format("%.2f", produto.getPreco() / 100.0);
            String textoHtml = "Adicionar <b><big>R$ " + precoFormatado + "</big></b>";

            // Aplicar formatação usando Html.fromHtml
            Spanned textoFormatado = Html.fromHtml(textoHtml, Html.FROM_HTML_MODE_LEGACY);
            botaoAdicionar.setText(textoFormatado);

        } catch (Exception e) {
            e.printStackTrace();
            Glide.with(this).load(R.drawable.imagem_carregando).into(imagemProduto); // Imagem padrão em caso de erro
        }
    }

    private void alterarQuantidade(int delta) {
        quantidade = Math.max(1, quantidade + delta);
        quantidadeProduto.setText(String.valueOf(quantidade));
        atualizarPreco();
        atualizarEstadoBotaoDiminuir();
    }

    private void atualizarPreco() {
        String precoFormatado = String.format("%.2f", (produto.getPreco() * quantidade) / 100.0);
        botaoAdicionar.setText("Adicionar R$ " + precoFormatado);
    }

    private void atualizarEstadoBotaoDiminuir() {
        if (quantidade == 1) {
            botaoDiminuir.setEnabled(false);
            botaoDiminuir.setAlpha(0.5f);
        } else {
            botaoDiminuir.setEnabled(true);
            botaoDiminuir.setAlpha(1.0f);
        }
    }
}