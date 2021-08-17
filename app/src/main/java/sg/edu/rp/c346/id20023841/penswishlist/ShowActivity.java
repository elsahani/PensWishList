package sg.edu.rp.c346.id20023841.penswishlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    Button btnFilter;
    ArrayList<Pens> al;
    ArrayList<String> Colour;
    ListView lv;
    Spinner spinner;
    ArrayAdapter<String> spinnerAdapter;
    Pens pens;

    CustomAdapter caToDo;

    @Override
    protected void onResume() {
        super.onResume();
        DBhelper dbh = new DBhelper(this);
        al.clear();
        al.addAll(dbh.getAllPens());
        caToDo.notifyDataSetChanged();

        Colour.clear();
        Colour.addAll(dbh.getColour());
        spinnerAdapter.notifyDataSetChanged();
    }

    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Intent i = getIntent();
        pens = (Pens) i.getSerializableExtra("pen");

        DBhelper dbh = new DBhelper(ShowActivity.this);

        btnFilter = findViewById(R.id.buttonFilter);
        lv = findViewById(R.id.lvPens);
        spinner = findViewById(R.id.spinnerColour);

        al = dbh.getAllPens();
        Colour = dbh.getColour();
        dbh.close();

        caToDo = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(caToDo);

        caToDo.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ShowActivity.this, EditActivity.class);
                i.putExtra("pen", al.get(position));
                startActivity(i);
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBhelper dbh = new DBhelper(ShowActivity.this);
                al.clear();
                al.addAll(dbh.getAllPensByStars(5));
                caToDo.notifyDataSetChanged();
            }
        });

        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Colour);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DBhelper dbh = new DBhelper(ShowActivity.this);
                al.clear();
                al.addAll(dbh.getAllPensByColour(Colour.get(position)));
                caToDo.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    }