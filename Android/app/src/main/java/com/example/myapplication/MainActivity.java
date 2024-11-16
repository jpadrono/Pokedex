package com.example.myapplication;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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

    private GridLayout contentGridLayout;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        contentGridLayout = findViewById(R.id.gridLlayout);
        scrollView = findViewById(R.id.scrollView);
        EditText searchEditText = findViewById(R.id.searchEditText);
        Button searchButton = findViewById(R.id.searchButton);

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

        // Configura o clique do botão de busca
        searchButton.setOnClickListener(v -> {
            String searchQuery = searchEditText.getText().toString().trim();
            scrollToPokemon(searchQuery);
        });
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
        contentGridLayout.setColumnCount(1);  // Uma coluna apenas

        try {
            JSONArray data = new JSONArray(jsonText);

            for (int i = 0; i < data.length(); i++) {
                JSONObject el = data.getJSONObject(i);

                String id = el.getString("id");
                String imgUrl = el.getString("img");
                String nome = el.getString("name");
                JSONArray typesArray = el.getJSONArray("types");
                StringBuilder types = new StringBuilder();
                for (int j = 0; j < typesArray.length(); j++) {
                    types.append(typesArray.getString(j)).append(" ");
                }

                // Criando um LinearLayout horizontal para a imagem e os dados
                LinearLayout pokemonLayout = new LinearLayout(this);
                pokemonLayout.setOrientation(LinearLayout.HORIZONTAL);
                pokemonLayout.setPadding(10, 10, 10, 10);
                pokemonLayout.setGravity(android.view.Gravity.CENTER_VERTICAL);
                pokemonLayout.setTag(id);  // Tag para rolagem baseada no ID

                // Criando e configurando a ImageView
                ImageView newImage = new ImageView(this);
                newImage.setLayoutParams(new LinearLayout.LayoutParams(400, 400));
                Glide.with(this)
                        .load(imgUrl)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .placeholder(R.drawable.logo)
                        .error(R.drawable.erro)
                        .into(newImage);

                // Criando e configurando o LinearLayout para os dados
                LinearLayout dataLayout = new LinearLayout(this);
                dataLayout.setOrientation(LinearLayout.VERTICAL);
                dataLayout.setPadding(30, 0, 0, 0);
                dataLayout.setGravity(android.view.Gravity.CENTER);

                // Nome do Pokémon
                TextView nameView = new TextView(this);
                nameView.setText(nome);
                nameView.setTextSize(25);
                nameView.setTextColor(getResources().getColor(android.R.color.black));
                nameView.setPadding(0, 10, 0, 5);
                nameView.setGravity(android.view.Gravity.START);
                nameView.setTag("pokemon_name");  // Tag para identificar o nome do Pokémon

                // ID e Tipo
                TextView typeView = new TextView(this);
                typeView.setText("ID: " + id + "\n" + "Tipo: " + types.toString());
                typeView.setTextSize(25);
                typeView.setTextColor(getResources().getColor(android.R.color.black));
                typeView.setPadding(0, 5, 0, 0);
                typeView.setGravity(android.view.Gravity.START);

                // Adicionando os TextViews ao dataLayout
                dataLayout.addView(nameView);
                dataLayout.addView(typeView);

                // Adicionando a imagem e os dados ao layout principal
                pokemonLayout.addView(newImage);
                pokemonLayout.addView(dataLayout);

                // Adicionando o LinearLayout no GridLayout
                contentGridLayout.addView(pokemonLayout);
            }
        } catch (Exception e) {
            Log.d("POKEDEX", e.toString());
        }
    }

    // Método para rolar até o Pokémon encontrado
    private void scrollToPokemon(String searchQuery) {
        for (int i = 0; i < contentGridLayout.getChildCount(); i++) {
            LinearLayout pokemonLayout = (LinearLayout) contentGridLayout.getChildAt(i);
            TextView nameView = pokemonLayout.findViewWithTag("pokemon_name");
            String pokemonId = (String) pokemonLayout.getTag();

            if (nameView != null &&
                    (nameView.getText().toString().equalsIgnoreCase(searchQuery) || pokemonId.equals(searchQuery))) {
                scrollView.smoothScrollTo(0, pokemonLayout.getTop());
                return;
            }
        }
        Log.d("POKEDEX", "Pokémon não encontrado: " + searchQuery);
    }
}
