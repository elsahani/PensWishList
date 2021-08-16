package sg.edu.rp.c346.id20023841.penswishlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "simplestationery.db";
    private static final int DATABSE_VERSION = 2;
    private static final String TABLE_PEN = "pen";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_COLOUR = "colour";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_NIBSIZE = "nibsize";
    private static final String COLUMN_STARS = "star";

    public DBhelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSql = "CREATE TABLE " + TABLE_PEN + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_COLOUR + " TEXT,"
                + COLUMN_PRICE + " DOUBLE,"
                + COLUMN_NIBSIZE + " DOUBLE,"
                + COLUMN_STARS + " INTEGER ) ";
        db.execSQL(createSongTableSql);
        Log.i("info", "created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEN);
        onCreate(db);
    }

    public long insertPens(String name, String colour, Double price, Double nibsize, Integer star) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_COLOUR, colour);
        values.put(COLUMN_PRICE, price);
        values.put(COLUMN_NIBSIZE, nibsize);
        values.put(COLUMN_STARS, star);
        long result = db.insert(TABLE_PEN, null, values);
        db.close();
        Log.d("SQL Insert", "ID:" + result); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<Pens> getAllPens() {
        ArrayList<Pens> songs = new ArrayList<Pens>();

        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_NAME + ", "
                + COLUMN_COLOUR + ", "
                + COLUMN_PRICE + ", "
                + COLUMN_NIBSIZE + ", "
                + COLUMN_STARS
                + " FROM " + TABLE_PEN;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String colour = cursor.getString(2);
                double price = cursor.getDouble(3);
                double nibsize = cursor.getDouble(4);
                int stars = cursor.getInt(5);

                Pens pens = new Pens(id,name, colour, price, nibsize, stars);
                songs.add(pens);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public ArrayList<Pens> getAllPensByStars(int starsFilter) {
        ArrayList<Pens> songs = new ArrayList<Pens>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_COLOUR, COLUMN_PRICE, COLUMN_NIBSIZE, COLUMN_STARS};
        String condition = COLUMN_STARS + ">= ?";
        String[] args = {String.valueOf(starsFilter)};

        Cursor cursor;
        cursor = db.query(TABLE_PEN, columns, condition, args, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String colour = cursor.getString(2);
                double price = cursor.getDouble(3);
                double nibsize = cursor.getDouble(4);
                int stars = cursor.getInt(5);

                Pens newPens = new Pens(id,name, colour, price, nibsize, stars);
                songs.add(newPens);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;

    }

    public int deletePens(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};

        int result = db.delete(TABLE_PEN, condition, args);
        db.close();
        return result;
    }

    public int updatePens(Pens data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, data.getName());
        values.put(COLUMN_COLOUR, data.getColour());
        values.put(COLUMN_PRICE, data.getPrice());
        values.put(COLUMN_NIBSIZE, data.getNibsize());
        values.put(COLUMN_STARS, data.getStars());

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.get_id())};

        int result = db.update(TABLE_PEN, values, condition, args);
        db.close();
        return result;
    }

    public ArrayList<String> getColour() {
        ArrayList<String> codes = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_COLOUR};

        Cursor cursor;
        cursor = db.query(true, TABLE_PEN, columns, null, null, null, null, null, null);
        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                codes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return codes;
    }

    public ArrayList<Pens> getAllPensByColour(String colourFilter) {
        ArrayList<Pens> pens = new ArrayList<Pens>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_NAME, COLUMN_COLOUR, COLUMN_PRICE, COLUMN_NIBSIZE, COLUMN_STARS};
        String condition = COLUMN_COLOUR + "= ?";
        String[] args = {String.valueOf(colourFilter)};

        Cursor cursor;
        cursor = db.query(TABLE_PEN, columns, condition, args, null, null, null, null);

        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String colour = cursor.getString(2);
                double price = cursor.getDouble(3);
                double nibsize = cursor.getDouble(4);
                int stars = cursor.getInt(5);

                Pens newPens = new Pens(id,name, colour, price, nibsize, stars);
                pens.add(newPens);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return pens;
    }
}
