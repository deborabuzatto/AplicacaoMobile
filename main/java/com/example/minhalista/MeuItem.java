package com.example.minhalista;

import android.graphics.Bitmap;

public class MeuItem {
    // a class MeuItem, retorna o bitmap e os dados escritos
    private Bitmap img;
    private String titulo, descricao;

    public MeuItem(Bitmap img, String titulo, String descricao) {
        this.img = img;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Bitmap getImg() {
        return img;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
