package sg.edu.rp.c346.id20023841.penswishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    Button btnInsert;
    EditText etname, etprice, etcolour, etnibsize;
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnInsert = findViewById(R.id.buttonInsert);
        etname = findViewById(R.id.editTextName);
        etprice = findViewById(R.id.editTextPrice);
        etcolour = findViewById(R.id.editTextColour);
        etnibsize = findViewById(R.id.editTextNibSize);
        rb = findViewById(R.id.ratingBarStars);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etname.getText().toString().trim();
                String colour = etcolour.getText().toString().trim();
                double price = Double.parseDouble(etprice.getText().toString().trim());
                double nibsize = Double.parseDouble(etnibsize.getText().toString().trim());
                if (name.length() == 0 || colour.length() == 0 ) {
                    Toast.makeText(AddActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }


                DBhelper dbh = new DBhelper(AddActivity.this);

                int stars = getStars();
                dbh.insertPens(name, colour, price, nibsize, stars);
                dbh.close();
                Toast.makeText(AddActivity.this, "Inserted", Toast.LENGTH_LONG).show();

                etname.setText("");
                etcolour.setText("");
                etprice.setText("");
                etnibsize.setText("");

                Intent intent = new Intent(AddActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });
    }
    private int getStars() {
        int stars = 1;
        stars = (int)rb.getRating();
        return stars;
    }
}