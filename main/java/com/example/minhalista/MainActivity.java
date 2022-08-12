package com.example.minhalista;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static int NOVO_ITEM_REQUEST = 1;
    private MeuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("MINHA LISTA APP", "Método onCreate() foi chamado");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // recuperamos o id do floatingButton
        FloatingActionButton fabAdicionar = findViewById(R.id.fabAdicionar);
        // ao receber o evento de click, executa a função
        fabAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // cria um intent que levará ate a activity NovoItemActivity
                Intent i = new Intent(MainActivity.this, NovoItemActivity.class);
                startActivityForResult(i, NOVO_ITEM_REQUEST);
            }
        });

        // a linha abaixo vai recuperar a viewModel se ela já existir.
        MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // depois recuperamos os items da lista que estão salvos na viewModel
        List<MeuItem> items = vm.getItems();

        // resgata o id do recyclerView
        RecyclerView rvItems = findViewById(R.id.rvItems);

        // define tamanho específico para os itens
        rvItems.setHasFixedSize(true);

        // layoutManager definirá como os itens serão alinhados, nesse caso, uma lista vertical
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rvItems.setLayoutManager(layoutManager);

        // adapter que realmente cria os itens
        this.adapter = new MeuAdapter(MainActivity.this, items);
        rvItems.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // fazemos uma requisição, se ela corresponder ao parametro estático, 1, seguimos com a lógica
        if (requestCode == NOVO_ITEM_REQUEST) {
            //resgatamos a URI da imagem selecionada, assim como o texto digitado nas caixas de título e descrição
            Uri uriFoto = data.getData();
            String titulo = data.getStringExtra("titulo");
            String descricao = data.getStringExtra("descricao");
            try {
                // adequamos o bitmap para manter um tamanho padrão em qualquer tela
                Bitmap bitmapFoto = Utils.getBitmap(MainActivity.this, uriFoto, 3000, 100);

                // criamos o novo item da lista, no padrão criado na classe MeuItem
                MeuItem newItem = new MeuItem(bitmapFoto, titulo, descricao);

                // recupera o ViewModel
                MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
                // recupera os items salvos na viewModel
                List<MeuItem> items = vm.getItems();
                items.add(newItem);

                // notificando ao adapter que um novo item foi criado, assim ele atualiza a activity
                this.adapter.notifyDataSetChanged();
            } catch (FileNotFoundException e) {
                // se não entrar no condicional, printa o erro ocorrido
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MINHA LISTA APP", "Método onStart() foi chamado");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MINHA LISTA APP", "Método onStop() foi chamado");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MINHA LISTA APP", "Método onDestroy() foi chamado");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MINHA LISTA APP", "Método onResume() foi chamado");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MINHA LISTA APP", "Método onResume() foi chamado");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MINHA LISTA APP", "Método onPause() foi chamado");
    }
}