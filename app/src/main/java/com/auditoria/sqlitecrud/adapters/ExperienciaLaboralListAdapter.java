package com.auditoria.sqlitecrud.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.auditoria.sqlitecrud.R;
import com.auditoria.sqlitecrud.models.ExperienciaLaboral;

import java.util.List;

/**
 * Created by trejo on 11/11/14.
 */
public class ExperienciaLaboralListAdapter extends ArrayAdapter {
    private Context context;
    private List<ExperienciaLaboral> experienciaLaborals;

    public ExperienciaLaboralListAdapter(Context context, int resource, List<ExperienciaLaboral> experienciaLaborals){
        super(context, resource, experienciaLaborals);
        this.context = context;
        this.experienciaLaborals = experienciaLaborals;
    }

    public View getView(int position, View convertViewer, ViewGroup parent){
        if (convertViewer == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertViewer = inflater.inflate(R.layout.list_view_form, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.viewTextItemText = (TextView) convertViewer.findViewById(R.id.item_descriptor);
            holder.viewTextItemID = (TextView) convertViewer.findViewById(R.id.item_id);
            convertViewer.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) convertViewer.getTag();
        ExperienciaLaboral experienciaLaboral = (ExperienciaLaboral)getItem(position);
        holder.viewTextItemText.setText(experienciaLaboral.toString());
        holder.viewTextItemID.setText(String.valueOf(experienciaLaboral.getId()));
        return convertViewer;

    }

    private static class ViewHolder{
        public TextView viewTextItemID;
        public TextView viewTextItemText;
    }
}
