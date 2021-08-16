package sg.edu.rp.c346.id20023841.penswishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnShop1,btnShop2,btnShop3,btnShop4,btnAddtoWS, btnSeeWS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShop1 = findViewById(R.id.buttonUniBall);
        btnShop2 = findViewById(R.id.buttonArtline);
        btnShop3 = findViewById(R.id.buttonSarasa);
        btnShop4 = findViewById(R.id.buttonZebra);
        btnAddtoWS = findViewById(R.id.buttonAddToWishList);
        btnSeeWS = findViewById(R.id.buttonShowList);

        btnAddtoWS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        btnSeeWS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });

        btnShop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.shop1)));
                startActivity(intentweb);
            }
        });

        btnShop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.shop2)));
                startActivity(intentweb);
            }
        });

        btnShop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.shop3)));
                startActivity(intentweb);
            }
        });

        btnShop4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.shop4)));
                startActivity(intentweb);
            }
        });

    }
}