package br.imd.fic.gerenciadordelivros.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import br.imd.fic.gerenciadordelivros.R;
import br.imd.fic.gerenciadordelivros.dominio.Livro;

import java.util.List;

public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.LivroHolder> {
    //Variáveis
    private List<Livro> livros;
    private Context context;

    private OnLivroListener onLivroListener;

    public LivroAdapter(List<Livro> livros, Context context, OnLivroListener onLivroListener) {
        this.livros = livros;
        this.context = context;
        this.onLivroListener = onLivroListener;
    }

    @NonNull
    @Override
    public LivroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Contrução do objeto livroHolder
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_livro, parent, false);
        LivroHolder livroHolder = new LivroHolder(view, onLivroListener);
        return livroHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LivroHolder holder, int position) {
        //Preenchimento da posição da lista
        Livro livro = livros.get(position);

        holder.txtTitulo.setText(livro.getTitulo());
        holder.txtAutor.setText(livro.getAutor());
        holder.txtEditora.setText(livro.getEditora());

        //Cor para livros indisponíveis
        if (livro.getEmprestado() == 1){
            holder.ic_livro.setColorFilter(Color.GRAY); //Cor
            holder.ic_star.setVisibility(View.VISIBLE); //Estrela
        }else{
            holder.ic_livro.setColorFilter(Color.parseColor("#0455BF"));
            holder.ic_star.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() { return livros.size();}

    public void setItems(List<Livro> livros){ this.livros = livros;}

    public Livro getItem(int posicao){ return livros.get(posicao);}


    //Classe representa o ViewHolder
    public class LivroHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        //Variáveis
        public TextView txtTitulo;
        public TextView txtAutor;
        public TextView txtEditora;
        public ImageView ic_livro;
        public ImageView ic_star;

        public OnLivroListener onLivroListener;

        //Contrutor da Classe
        public LivroHolder(View view, OnLivroListener onLivroListener) {
            super(view);
        //Linkar Views
            txtTitulo = view.findViewById(R.id.txtTitulo);
            txtAutor = view.findViewById(R.id.txtAutor);
            txtEditora = view.findViewById(R.id.txtEditora);
            ic_livro = view.findViewById(R.id.ic_livro);
            ic_star = view.findViewById(R.id.ic_star);

            this.onLivroListener = onLivroListener;

            //Implementação de comportamento
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onLivroListener.OnLivroClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            onLivroListener.onLivroLongClick(getAdapterPosition());
            return true;
        }

    }

    public interface OnLivroListener{
        void OnLivroClick(int posicao);
        void onLivroLongClick(int posicao);
    }
}
