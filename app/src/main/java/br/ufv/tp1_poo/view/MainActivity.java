package br.ufv.tp1_poo.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import br.ufv.tp1_poo.R;
import br.ufv.tp1_poo.controller.ProdutoAdapter;
import br.ufv.tp1_poo.model.Produto;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProdutos;

    // Elementos de UI para as abas
    private TextView tabVegetariano, tabVegano, tabCongelado, tabBebida;
    private View barraVegetariano, barraVegano, barraCongelado, barraBebida;

    // Lista completa de produtos
    private List<Produto> listaCompletaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuração inicial
        configurarRecyclerView();
        inicializarTabs();
        configurarTabs();
    }

    // Configura o RecyclerView para exibir a lista de produtos.
    private void configurarRecyclerView() {
        recyclerViewProdutos = findViewById(R.id.recyclerViewProdutos);
        recyclerViewProdutos.setLayoutManager(new LinearLayoutManager(this));

        listaCompletaProdutos = carregarProdutos();

        if (listaCompletaProdutos != null && !listaCompletaProdutos.isEmpty()) {
            ProdutoAdapter produtoAdapter = new ProdutoAdapter(listaCompletaProdutos, produto -> {
                // Clique no item: abre a tela de detalhes do produto
                Intent intent = new Intent(MainActivity.this, DetalhesProdutoActivity.class);
                intent.putExtra("produto", produto); // Passa o objeto Produto
                startActivity(intent);
            });
            recyclerViewProdutos.setAdapter(produtoAdapter);
        } else {
            Log.e("MainActivity", "Lista de produtos está vazia ou não carregou.");
        }
    }

    // Inicializa os componentes das abas e barras.
    private void inicializarTabs() {
        tabVegetariano = findViewById(R.id.tabVegetariano);
        tabVegano = findViewById(R.id.tabVegano);
        tabCongelado = findViewById(R.id.tabCongelado);
        tabBebida = findViewById(R.id.tabBebida);

        barraVegetariano = findViewById(R.id.barraVegetariano);
        barraVegano = findViewById(R.id.barraVegano);
        barraCongelado = findViewById(R.id.barraCongelado);
        barraBebida = findViewById(R.id.barraBebida);

        // Define "Vegetariano" como a aba padrão selecionada
        selecionarAba(tabVegetariano, barraVegetariano);
    }

    // Configura os listeners de clique para alternar entre as abas.
    private void configurarTabs() {
        tabVegetariano.setOnClickListener(v -> selecionarAba(tabVegetariano, barraVegetariano));
        tabVegano.setOnClickListener(v -> selecionarAba(tabVegano, barraVegano));
        tabCongelado.setOnClickListener(v -> selecionarAba(tabCongelado, barraCongelado));
        tabBebida.setOnClickListener(v -> selecionarAba(tabBebida, barraBebida));
    }

    // Altera o estilo da aba selecionada e redefine as outras.
    private void selecionarAba(TextView tabSelecionada, View barraSelecionada) {
        // Redefine o estilo das abas
        resetarEstilos();

        // Estilo para a aba selecionada
        tabSelecionada.setTextColor(getResources().getColor(R.color.laranja));
        barraSelecionada.setVisibility(View.VISIBLE);

        // Filtra os produtos com base na aba selecionada
        String categoria = tabSelecionada.getText().toString();
        List<Produto> produtosFiltrados = filtrarProdutosPorCategoria(categoria);

        // Atualiza o RecyclerView com os produtos filtrados
        ProdutoAdapter produtoAdapter = new ProdutoAdapter(produtosFiltrados, produto -> {
            // Clique no item: abre a tela de detalhes do produto
            Intent intent = new Intent(MainActivity.this, DetalhesProdutoActivity.class);
            intent.putExtra("produto", produto); // Passa o objeto Produto
            startActivity(intent);
        });
        recyclerViewProdutos.setAdapter(produtoAdapter);
    }

    // Filtra os produtos com base na categoria
    private List<Produto> filtrarProdutosPorCategoria(String categoria) {
        List<Produto> produtosFiltrados = new ArrayList<>();
        for (Produto produto : listaCompletaProdutos) {
            if (produto.getCategoria().equalsIgnoreCase(categoria)) {
                produtosFiltrados.add(produto);
            }
        }
        return produtosFiltrados;
    }

    // Redefine o estilo de todas as abas para o estado padrão.
    private void resetarEstilos() {
        tabVegetariano.setTextColor(getResources().getColor(R.color.cinza));
        tabVegano.setTextColor(getResources().getColor(R.color.cinza));
        tabCongelado.setTextColor(getResources().getColor(R.color.cinza));
        tabBebida.setTextColor(getResources().getColor(R.color.cinza));

        barraVegetariano.setVisibility(View.GONE);
        barraVegano.setVisibility(View.GONE);
        barraCongelado.setVisibility(View.GONE);
        barraBebida.setVisibility(View.GONE);
    }

    // Carrega a lista de produtos do arquivo JSON
    private List<Produto> carregarProdutos() {
        try (InputStream inputStream = getResources().openRawResource(R.raw.produtos);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            // Lê o conteúdo do arquivo JSON
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            // Converte o JSON em uma lista de objetos Produto
            Type listType = new TypeToken<List<Produto>>() {}.getType();
            return new Gson().fromJson(jsonBuilder.toString(), listType);
        } catch (Exception e) {
            Log.e("MainActivity", "Erro ao carregar JSON do raw: " + e.getMessage(), e);
            return new ArrayList<>();
        }
    }
}