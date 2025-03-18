package com.example.listview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int currentLayoutIndex = 0;
    private final int[] layouts = {R.layout.layout1, R.layout.layout2, R.layout.layout3, R.layout.layout4};

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

        ListView listView = findViewById(R.id.listView);
        Button btnPrevious = findViewById(R.id.btnPrevious);
        Button btnNext = findViewById(R.id.btnNext);

        String[] nomes = {"João Gomes", "Maria Bueno", "Carlos Gugel", "Albert Eisten", "Jorge Filho", "Camila Oliveira", "Jeferson Suarez"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, layouts[currentLayoutIndex], R.id.textItem, nomes);
        listView.setAdapter(adapter);

        updateButtonVisibility(btnPrevious, btnNext);

        btnPrevious.setOnClickListener(v -> {
            currentLayoutIndex = (currentLayoutIndex - 1 + layouts.length) % layouts.length;
            updateLayout(listView, nomes);
            updateButtonVisibility(btnPrevious, btnNext);
        });

        btnNext.setOnClickListener(v -> {
            currentLayoutIndex = (currentLayoutIndex + 1) % layouts.length;
            updateLayout(listView, nomes);
            updateButtonVisibility(btnPrevious, btnNext);
        });
    }
    private void updateLayout (ListView listView, String[]nomes){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, layouts[currentLayoutIndex], R.id.textItem, nomes);
        listView.setAdapter(adapter);
    }

    private void updateButtonVisibility(Button btnPrevious, Button btnNext) {
        if (currentLayoutIndex == 0) {
            btnPrevious.setVisibility(Button.GONE);
            btnNext.setVisibility(Button.VISIBLE);
        } else if (currentLayoutIndex == 3) {
            btnPrevious.setVisibility(Button.VISIBLE);
            btnNext.setVisibility(Button.GONE);
        } else {
            btnPrevious.setVisibility(Button.VISIBLE);
            btnNext.setVisibility(Button.VISIBLE);
        }
    }
}