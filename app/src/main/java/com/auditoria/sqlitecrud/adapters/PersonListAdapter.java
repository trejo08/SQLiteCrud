package com.auditoria.sqlitecrud.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.auditoria.sqlitecrud.R;
import com.auditoria.sqlitecrud.models.Person;

import java.util.List;

/**
 * Created by trejo on 11/6/14.
 */
public class PersonListAdapter extends ArrayAdapter {
    private Context context;
    private List<Person> person;

    public PersonListAdapter(Context context, int resource, List<Person> person){
        super(context, resource, person);
        this.context = context;
        this.person = person;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        if(convertView == null){
//            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.person_list_view, parent, false);
//            ViewHolder holder = new ViewHolder();
//            holder.viewTextUser = (TextView)convertView.findViewById(R.id.item_person_list);
//            holder.viewTextUserId = (TextView)convertView.findViewById(R.id.item_id_person);
//            convertView.setTag(holder);
//        }
//        ViewHolder holder = (ViewHolder)convertView.getTag();
//        Person user = (Person)getItem(position);
//        holder.viewTextUser.setText(user.toString());
//        holder.viewTextUserId.setText(String.valueOf(user.getId()));

        return convertView;
    }

    private static class ViewHolder{
        public TextView viewTextUser;
        public TextView viewTextUserId;
    }


}
