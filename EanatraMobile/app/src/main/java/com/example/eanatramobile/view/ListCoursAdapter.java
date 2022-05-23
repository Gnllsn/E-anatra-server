package com.example.eanatramobile.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eanatramobile.R;
import com.example.eanatramobile.modele.Users;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListCoursAdapter extends BaseAdapter {

    private ArrayList<Object> objects ;
    private LayoutInflater inflater ;

    public void setList(Context context,ArrayList<Object> list){
        this.objects = list ;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * nombre de item dans la liste
     * @return
     */
    @Override
    public int getCount() {
        return objects.size();
    }

    /**
     * return l'item de la ligne actuelle
     * @param i
     * @return
     */

    @Override
    public Object getItem(int i) {
        return this.objects.get(i);
    }

    /**
     * return un indice par rap ligne actuel
     * @param i
     * @return
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     *  return la ligne formater avec tout les elements avec gestion evenement
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Cours cours;
        if(view == null){
            cours = new Cours();
            view = inflater.inflate(R.layout.cours,null);
            cours.sary = view.findViewById(R.id.sary);
            cours.titre = view.findViewById(R.id.titlecours);
            cours.description = view.findViewById(R.id.descri);
            view.setTag(cours);
        }else {
            cours = (Cours) view.getTag();
        }
        cours.sary.setText("sary be ") ;
        cours.titre.setText(" title kely") ;
        cours.description.setText("description kely") ;
//        cours.s
        return view;
    }

    private class Cours{
        TextView sary ;
        TextView titre ;
        TextView description ;
    }
}
