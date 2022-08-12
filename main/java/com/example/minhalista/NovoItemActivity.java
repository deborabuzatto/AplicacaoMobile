package com.example.minhalista;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.net.URI;

public class NovoItemActivity extends AppCompatActivity {

    private static int GALERIA_REQUEST = 1;
    private Uri uriFotoSelecionada = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // começamos criando o layout da activity_novo_item
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_item);

        // resgatamos o id do botão que será responsável por abrir a galeria
        ImageButton imbGaleria = findViewById(R.id.imbGaleria);
        imbGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            // Ao receber o evento de click...
            public void onClick(View view) {
                // criamos intent que só receberá apenas o tipo 'image'
                Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                i.setType("image/*");

                // inicia uma activity que aguardará a conclusão da requisição
                startActivityForResult(i, GALERIA_REQUEST);
            }
        });

        // resgatamos o id do botão adicionar
        Button btnAdicionar = findViewById(R.id.btnAdicionar);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            // receber o evento de click, precisamos fazer uma série de verificações
            public void onClick(View view) {
                // (Toast é popup com mensagem para o usuário)

                // verifica se a URI é nula, caso seja, exibimos um toast ao usuário
                if(uriFotoSelecionada == null) {
                    Toast.makeText(NovoItemActivity.this, "Foto não selecionada", Toast.LENGTH_SHORT).show();
                    return;
                }

                // resgatamos o id do titulo e logo após, seu conteúdo
                EditText etTitulo = findViewById(R.id.etTitulo);
                String titulo = etTitulo.getText().toString();
                // se vazio, novamente exibimos um o toast
                if(titulo.isEmpty()) {
                    Toast.makeText(NovoItemActivity.this, "Título vazio", Toast.LENGTH_SHORT).show();
                    return;
                }

                // resgatamos o id da descrição e logo após, seu conteúdo
                EditText etDescricao = findViewById(R.id.etDescricao);
                String descricao = etDescricao.getText().toString();
                // se vazio, exibimos o toast para o usuário
                if(descricao.isEmpty()) {
                    Toast.makeText(NovoItemActivity.this, "Descrição vazia", Toast.LENGTH_SHORT).show();
                    return;
                }

                // cria um intent que enviará os dados validados para a activity main, assim como o result_ok que dará continuação a aplicação no main
                Intent i = new Intent();
                i.setData(uriFotoSelecionada);
                i.putExtra("titulo", titulo);
                i.putExtra("descricao", descricao);
                setResult(Activity.RESULT_OK, i);
                // encerramos a ação da activity
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // função que irá lidar com a requisição da galeria, feita na linha  44
        if(requestCode == GALERIA_REQUEST) {
            if(resultCode == Activity.RESULT_OK) {
                // URI que referencia a imagem devolvida pela intent
                uriFotoSelecionada = data.getData();

                //resgatamos o id para renderizarmos a URI do intent
                ImageView imvFotoPreview = findViewById(R.id.imvFotoPreview);
                imvFotoPreview.setImageURI(uriFotoSelecionada);
            }
        }
    }
}