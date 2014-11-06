package com.auditoria.sqlitecrud.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.auditoria.sqlitecrud.R;
import com.auditoria.sqlitecrud.models.Estado;
import com.auditoria.sqlitecrud.models.Person;

import java.util.List;

/**
 * Created by trejo on 11/6/14.
 */
public class EstadoListAdapter extends ArrayAdapter {
    private Context context;
    private List<Estado> estado;

    public EstadoListAdapter(Context context, int resource, List<Estado> estado){
        super(context, resource, estado);
        this.context = context;
        this.estado = estado;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.estado_list_view, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.viewTextEstado = (TextView)convertView.findViewById(R.id.item_estado_list);
            holder.viewTextEstadoId = (TextView)convertView.findViewById(R.id.item_id_estado);
            convertView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder)convertView.getTag();
        Estado estado = (Estado)getItem(position);
        holder.viewTextEstado.setText(estado.toString());
        holder.viewTextEstadoId.setText(String.valueOf(estado.getId()));

        return convertView;
    }

    private static class ViewHolder{
        public TextView viewTextEstado;
        public TextView viewTextEstadoId;
    }
}
