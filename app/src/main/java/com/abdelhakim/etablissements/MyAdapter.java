package com.abdelhakim.etablissements;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.abdelhakim.etablissements.db.Etablissement;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Etablissement> etablissements;
    private Context context;

    public MyAdapter(Context context, List<Etablissement> etablissements) {
        this.context = context;
        this.etablissements = etablissements;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        // Create a new ViewHolder
        ViewHolder viewHolder = new ViewHolder(listItem);

        // Add fade-in animation
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(viewHolder.itemView, "alpha", 0f, 1f);
        fadeIn.setDuration(2000); // Adjust the duration as needed
        fadeIn.start();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Etablissement currentEtablissement = etablissements.get(position);

        //as we do not have the storage of images
        holder.image.setImageResource(R.drawable.education);
        holder.label.setText(currentEtablissement.getLabel());
        holder.name.setText(currentEtablissement.getName());
        holder.type.setText(currentEtablissement.isPublic() ? "Public" : "Privé");
    }

    @Override
    public int getItemCount() {
        return etablissements.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView label;
        public TextView name;
        public TextView type;

        public ViewHolder(View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.img);
            this.label = itemView.findViewById(R.id.label);
            this.name = itemView.findViewById(R.id.name);
            this.type = itemView.findViewById(R.id.type);
        }
    }

    public void updateList(List<Etablissement> filteredList) {
        etablissements.clear();
        etablissements.addAll(filteredList);
        notifyDataSetChanged();
    }
}

