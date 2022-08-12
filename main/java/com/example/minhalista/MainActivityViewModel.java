package com.example.minhalista;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    // Ao mudar a rotação da tela, a activity é destruída, para que a aplicação não se perca, criamos uma viewModel
    // Ela continuará existindo e comportando a lista.

    private List<MeuItem> items = new ArrayList<>();

    public List<MeuItem> getItems() {
        return items;
    }
}
