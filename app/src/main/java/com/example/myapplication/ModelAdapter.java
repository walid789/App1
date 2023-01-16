package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ModelAdapter extends ArrayAdapter<Model> {
    public ModelAdapter(Context context, int resource, List<Model> shapeList)
    {
        super(context,resource,shapeList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Model shape = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.model_celule, parent, false);
        }
        TextView adress = (TextView) convertView.findViewById(R.id.adress);
        TextView prix_nom = (TextView) convertView.findViewById(R.id.prix_nom);
        TextView prix_valeur = (TextView) convertView.findViewById(R.id.prix_valeur);
        TextView ville = (TextView) convertView.findViewById(R.id.ville);

        adress.setText(shape.getAdresse());
        prix_nom.setText(shape.getPrix_nom());
        prix_valeur.setText(shape.getPrix_valeur());
        ville.setText(shape.getVille());

        return convertView;
    }
}
