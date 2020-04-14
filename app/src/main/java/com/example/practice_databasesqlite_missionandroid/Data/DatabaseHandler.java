package com.example.practice_databasesqlite_missionandroid.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.practice_databasesqlite_missionandroid.Model.Book;
import com.example.practice_databasesqlite_missionandroid.R;
import com.example.practice_databasesqlite_missionandroid.Util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*
        create table _name(id,title,author,price)
         */

        String CREATE_BOOK_TABLE = "CREATE TABLE "+Util.TABLE_NAME + "("
                + Util.KEY_ID+" INTEGER PRIMARY KEY,"
                +Util.KEY_TITLE+" TEXT,"
                +Util.KEY_AUTHOR+" TEXT,"
                +Util.KEY_PRICE+" TEXT"+")";

        //execute database
        sqLiteDatabase.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE = String.valueOf(R.string.drop_table);
        sqLiteDatabase.execSQL(DROP_TABLE);

        //create table again
        onCreate(sqLiteDatabase);
    }

    /*
    CRUD - create, read, update, delete
     */

    // create--add
    public void addBook(Book book){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_TITLE,book.getTitle());
        contentValues.put(Util.KEY_AUTHOR,book.getAuthor());
        contentValues.put(Util.KEY_PRICE,book.getPrice());

        //insert to row
        sqLiteDatabase.insert(Util.TABLE_NAME,null,contentValues);
        Log.d("AddBook", "addBook: item added");

        //close connection
        sqLiteDatabase.close();
    }

    // read--single row
    public Book getBook(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(Util.TABLE_NAME,
                new String[]{Util.KEY_ID,Util.KEY_TITLE,Util.KEY_AUTHOR,Util.KEY_PRICE},
                Util.KEY_ID+"=?",
                new String[]{String.valueOf(id)},
                null,null,null);

        if (cursor != null)
            cursor.moveToNext();
        Book book = new Book();
        book.setId(Integer.parseInt(cursor.getString(0)));
        book.setTitle(cursor.getString(1));
        book.setAuthor(cursor.getString(2));
        book.setPrice(cursor.getString(3));
        return book;
    }

    // read --all rows/all entries in db
    public List<Book> getAllBook(){
        List<Book> bookList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        //select database
        String selectAllBook = "SELECT * FROM " +Util.TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(selectAllBook,null);
        if (cursor.moveToFirst()){
            do {

                Book  book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setPrice(cursor.getString(3));
                bookList.add(book);
            } while (cursor.moveToNext());
        }

        return bookList;
    }

    //update table
    public int updateBook(Book book){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_TITLE,book.getPrice());
        contentValues.put(Util.KEY_AUTHOR,book.getAuthor());
        contentValues.put(Util.KEY_PRICE,book.getPrice());

        //updating
        return sqLiteDatabase.update(Util.TABLE_NAME,contentValues,
                Util.KEY_ID+"=?",
                new String[]{String.valueOf(book.getId())});

    }

    //delete--single book/single entry
    public void deleteBook(Book book){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(Util.TABLE_NAME,Util.KEY_ID+"=?",
                new String[]{String.valueOf(book.getId())});

        //close db
        sqLiteDatabase.close();
    }

    public int countBook(){
        String countQuery = "SELECT * FROM "+Util.TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery,null);
        return cursor.getCount();
    }
}
