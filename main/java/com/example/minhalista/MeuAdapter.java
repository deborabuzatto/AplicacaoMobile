package com.example.minhalista;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MeuAdapter extends RecyclerView.Adapter{
    private MainActivity mainActivity;
    private List<MeuItem> items;

    public MeuAdapter(MainActivity mainActivity, List<MeuItem> items) {
        this.mainActivity = mainActivity;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        // criamos um item vazio
        View v = inflater.inflate(R.layout.item, parent, false);

        // retornamos o viewHolder com essa estrutura
        return new MeuViewHolder(v);
    }

    @Override
    // recebemos como parâmetro o retorno da função anterior
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // pega o próximo item do ArrayList
        MeuItem item = this.items.get(position);
        View v = holder.itemView;

        // preenche o item com os dados escritos pelo usuário
        TextView tvTitulo = v.findViewById(R.id.tvTitulo);
        tvTitulo.setText(item.getTitulo());

        TextView tvDescricao = v.findViewById(R.id.tvDescricao);
        tvDescricao.setText(item.getDescricao());

        ImageView imvFoto = v.findViewById(R.id.imvFotoPreview);
        imvFoto.setImageBitmap(item.getImg());
    }

    @Override
    // função que retorna o tamanho do item
    public int getItemCount() {
        return this.items.size();
    }
}
