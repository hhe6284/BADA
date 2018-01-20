package org.techtown.bada;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editYear, editMonth, editDay, editText;

    DatabaseReference databaseDiaries;

    ListView listViewDiaries;
    ScrollView scrollview;


    List<Diary> diaryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFullScreen);
        setContentView(R.layout.activity_main);

        databaseDiaries = FirebaseDatabase.getInstance().getReference("Diary");
        editYear = (EditText) findViewById(R.id.year);
        editMonth = (EditText) findViewById(R.id.month);
        editDay = (EditText) findViewById(R.id.day);
        editText = (EditText) findViewById(R.id.text);

        listViewDiaries = (ListView) findViewById(R.id.listViewDiaries);
        scrollview = (ScrollView) findViewById(R.id.scrollview);

        diaryList = new ArrayList<>();

        Button enter = (Button) findViewById(R.id.입력);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPhone();
            }
        });
        listViewDiaries.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Diary diary = diaryList.get(i);
                return true;
            }
        });
    }

    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseDiaries.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                diaryList.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    Diary diary = postSnapshot.getValue(Diary.class);
                    //adding artist to the list
                    diaryList.add(diary);
                }

                //creating adapter
                DiaryList artistAdapter = new DiaryList(MainActivity.this, diaryList);
                //attaching adapter to the listview
                listViewDiaries.setAdapter(artistAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void addPhone() {
        //getting the values to save
        String year = editYear.getText().toString().trim();
        String month = editMonth.getText().toString().trim();
        String day = editDay.getText().toString().trim();
        String text = editText.getText().toString().trim();


        //checking if the value is provided
        if (!TextUtils.isEmpty(text)) {
            String id = databaseDiaries.push().getKey();
            Diary p = new Diary(id, year, month, day, text);
            databaseDiaries.child(id).setValue(p);

            //setting edittext to blank again
            editYear.setText("");
            editMonth.setText("");
            editDay.setText("");
            editText.setText("");

            //displaying a success toast
            Toast.makeText(this, "added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }

        FirebaseDatabase.getInstance().getReference("new Group").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String year = (String) dataSnapshot.child("year").getValue();
                String month = (String) dataSnapshot.child("month").getValue();
                String day = (String) dataSnapshot.child("day").getValue();
                String text = (String) dataSnapshot.child("text").getValue();

                TextView year_1 = (TextView) findViewById(R.id.year);
                TextView month_1 = (TextView) findViewById(R.id.month);
                TextView day_1 = (TextView) findViewById(R.id.day);
                TextView text_1 = (TextView) findViewById(R.id.text);

                year_1.setText(year);
                month_1.setText(month);
                day_1.setText(day);
                text_1.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}