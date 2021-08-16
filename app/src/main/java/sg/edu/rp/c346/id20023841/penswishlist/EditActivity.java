package sg.edu.rp.c346.id20023841.penswishlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class EditActivity extends AppCompatActivity {

    EditText etEName, etEPrice, etPensID, etEColour, etENibSize ;
    Button btnUpdate, btnDelete, btnCancel;
    RatingBar rbStars2;
    Pens pens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etPensID = findViewById(R.id.editTextEID);
        etEPrice = findViewById(R.id.editTextEditPrice);
        etEName = findViewById(R.id.editTextEditName);
        etEColour = findViewById(R.id.editTextEditColour);
        etENibSize = findViewById(R.id.editTextEditNibSize);
        btnCancel = findViewById(R.id.buttonCancel);
        btnDelete = findViewById(R.id.buttonDelete);
        btnUpdate = findViewById(R.id.buttonUpdate);
        rbStars2 = findViewById(R.id.ratingBarStars3);


        Intent i = getIntent();
        pens = (Pens) i.getSerializableExtra("pen");

        etPensID.setText(pens.get_id() + "");
        etEName.setText(pens.getName());
        etEColour.setText(pens.getColour());
        etEPrice.setText(pens.getPrice() + "");
        etENibSize.setText(pens.getNibsize() + "");
        rbStars2.setRating(pens.getStars());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);
                myBuilder.setTitle("Warning");
                myBuilder.setMessage("Are you sure you want to delete the edited pen \n\n" + pens.getName());
                myBuilder.setCancelable(false);

                //Configure the 'positive' button
                myBuilder.setPositiveButton("Cancel", null);

                //Configure the 'negative' button
                myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBhelper dbh = new DBhelper(EditActivity.this);
                        dbh.deletePens(pens.get_id());
                        finish();
                    }
                });
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBhelper dbh = new DBhelper(EditActivity.this);
                pens.setName(etEName.getText().toString().trim());
                pens.setColour(etEColour.getText().toString().trim());
                pens.setPrice(Double.parseDouble(etEPrice.getText().toString()));
                pens.setNibsize(Double.parseDouble(etENibSize.getText().toString()));
                pens.setStars((int) rbStars2.getRating());
                dbh.updatePens(pens);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);
                myBuilder.setTitle("Warning");
                myBuilder.setMessage("Are you sure you want to discard the changes made?");
                myBuilder.setCancelable(false);

                //Configure the 'positive' button
                myBuilder.setPositiveButton("Do Not Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                //Configure the 'negative' button
                myBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(EditActivity.this, ShowActivity.class);
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
    }
}