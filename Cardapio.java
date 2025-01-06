package br.ufv.tp1_poo.model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class Cardapio {
    List<Produto>listaDeProdutos;
    public Cardapio(List<Produto> listaDeProdutos) {
        this.listaDeProdutos = new ArrayList<Produto>();

    }
    public List<Produto> getListaDeProdutos() {
        return listaDeProdutos;
    }
    public void carregarCardapio(String caminhoJson){
        try{
            String conteudoJSON = new String(Files.readAllBytes(Paths.get(caminhoJson)));
            JSONArray jsonArray = new JSONArray(conteudoJSON);

            for (int i = 0; i < jsonArray.length(); i++) {
                Produto aux = Produto.fromJson(jsonArray.getJSONObject(i));
                listaDeProdutos.add(aux);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
