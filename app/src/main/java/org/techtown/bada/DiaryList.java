package org.techtown.bada;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by LG on 2018-01-20.
 */

public class DiaryList extends ArrayAdapter<Diary> {
    private Activity context;
    List<Diary> diaryList;

    public DiaryList(Activity context, List<Diary> diaryList) {
        super(context, R.layout.list_layout, diaryList);
        this.context = context;
        this.diaryList = diaryList;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewYear = (TextView) listViewItem.findViewById(R.id.year);
        TextView textViewMonth = (TextView) listViewItem.findViewById(R.id.month);

        Diary phone = diaryList.get(position);
        textViewYear.setText(phone.getYear());
        textViewMonth.setText(phone.getMonth());

        return listViewItem;
    }
}
