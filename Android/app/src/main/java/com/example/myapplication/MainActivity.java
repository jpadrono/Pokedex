package com.example.myapplication;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Permitir request na mesma Thread da UI
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        load();
    }

    public void load() {
        String url = "http://localhost:8080/pokedex";
        String response = makeGetRequest(url);
        parseJson(response);
    }

    public String makeGetRequest(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            Log.d("POKEDEX-A", responseString);

            return responseString;
        } catch (Exception e) {
            Log.d("POKEDEX-B", e.toString());
        }

        return "";
    }

    public void parseJson(String jsonText) {
        GridLayout contentGridLayout = findViewById(R.id.gridLlayout);
        contentGridLayout.setColumnCount(1);  // Uma coluna apenas

        try {
            JSONArray data = new JSONArray(jsonText);  // Mudando para JSONArray diretamente

            for (int i = 0; i < data.length(); i++) {
                JSONObject el = data.getJSONObject(i);

                String id = el.getString("id");
                String imgUrl = el.getString("img");
                String nome = el.getString("name");
                String status = el.getString(("stats"));
                JSONArray typesArray = el.getJSONArray("types");
                StringBuilder types = new StringBuilder();
                for (int j = 0; j < typesArray.length(); j++) {
                    types.append(typesArray.getString(j)).append(" ");
                }

                // Criando um LinearLayout horizontal para a imagem e os dados
                LinearLayout pokemonLayout = new LinearLayout(this);
                pokemonLayout.setOrientation(LinearLayout.HORIZONTAL);  // Layout horizontal para imagem e dados
                pokemonLayout.setPadding(10, 10, 10, 10); // Adiciona espaçamento
                pokemonLayout.setGravity(android.view.Gravity.CENTER_VERTICAL);  // Alinha os itens verticalmente no centro

                // Criando e configurando a ImageView
                ImageView newImage = new ImageView(this);
                newImage.setLayoutParams(new LinearLayout.LayoutParams(400, 400));  // Tamanho da imagem
                Glide.with(this)
                        .load(imgUrl)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)  // Desabilita o cache
                        .skipMemoryCache(true)  // Desabilita o cache de memória
                        .placeholder(R.drawable.logo)
                        .error(R.drawable.erro)
                        .into(newImage);

                // Criando e configurando o LinearLayout para os dados (ID, Nome e Tipo)
                LinearLayout dataLayout = new LinearLayout(this);
                dataLayout.setOrientation(LinearLayout.VERTICAL);
                dataLayout.setPadding(30, 0, 0, 0); // Espaço entre imagem e dados, move o texto para a direita
                dataLayout.setGravity(android.view.Gravity.CENTER);  // Centraliza os dados dentro do layout

                // Criando e configurando o TextView para o nome
                TextView nameView = new TextView(this);
                nameView.setText(nome);
                nameView.setTextSize(25);  // Aumentado para fonte maior
                nameView.setTextColor(getResources().getColor(android.R.color.black));  // Cor do texto em preto
                nameView.setPadding(0, 10, 0, 5);  // Espaçamento entre o nome e os outros dados
                nameView.setGravity(android.view.Gravity.START);  // Alinha o texto do nome à esquerda dentro do seu layout

                // Criando e configurando o TextView para o ID e Tipo
                TextView typeView = new TextView(this);
                typeView.setText("ID: " + id +"\n" +status +"\nTipo: " + types.toString());
                typeView.setTextSize(25);  // Aumentado para fonte maior
                typeView.setTextColor(getResources().getColor(android.R.color.black));  // Cor do texto em preto
                typeView.setPadding(0, 5, 0, 0);
                typeView.setGravity(android.view.Gravity.START);  // Alinha o texto de ID e Tipo à esquerda

                // Adicionando os TextViews ao dataLayout
                dataLayout.addView(nameView);
                dataLayout.addView(typeView);

                // Adicionando a imagem e os dados ao layout principal
                pokemonLayout.addView(newImage);
                pokemonLayout.addView(dataLayout);

                // Adicionando o LinearLayout no GridLayout (apenas uma coluna)
                contentGridLayout.addView(pokemonLayout);
            }
        } catch (Exception e) {
            Log.d("POKEDEX", e.toString());
        }
    }
}
