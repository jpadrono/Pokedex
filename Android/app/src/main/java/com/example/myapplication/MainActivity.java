package com.example.myapplication;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        public void load(){
            String url = "http://localhost:8080/pokedex";
            String response = makeGetRequest(url);
            Log.d("POKEDEX", response);
        }


    }

    public String makeGetRequest(String url) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            Log.d("POKEDEX", responseString);

            return responseString;
        } catch (Exception e) {
            // throw new RuntimeException(e);
            Log.d("POKEDEX", e.toString());
        }

        return "";
    }

//    public void parseJson(String jsonText) {
//        LinearLayout contentLinearLayout = (LinearLayout) findViewById(R.id.content);
//
//        try {
//            JSONObject json = new JSONObject(jsonText);
//            JSONArray data = json.getJSONArray("data");
//
//            for(int i=0; i<data.length(); i++) {
//                JSONObject el = data.getJSONObject(i);
//
//                String email = el.getString("email");
//                String name = el.getString("first_name");
//
//                TextView newItem = new TextView(this);
//                newItem.setTextSize(20);
//                newItem.setText(name + " (" + email + ")");
//
//                contentLinearLayout.addView(newItem);
//
//                Log.d("MYAPP", email);
//            }
//        } catch (Exception e) {
//            // throw new RuntimeException(e);
//            Log.d("MYAPP", e.toString());
//        }
//    }
}