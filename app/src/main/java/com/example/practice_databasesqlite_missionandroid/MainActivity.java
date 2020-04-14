package com.example.practice_databasesqlite_missionandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.practice_databasesqlite_missionandroid.Data.DatabaseHandler;
import com.example.practice_databasesqlite_missionandroid.Model.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler databaseHandler = new DatabaseHandler(MainActivity.this);

        Book whatToSayWhenYouTalkToYourSelf = new Book();
        whatToSayWhenYouTalkToYourSelf.setTitle("What To Say When You Talk To Your Self");
        whatToSayWhenYouTalkToYourSelf.setAuthor("Shad Helmstetter, Ph.D");
        whatToSayWhenYouTalkToYourSelf.setPrice("699");

        Book finish = new Book();
        finish.setTitle("Finish");
        finish.setAuthor("Jon Acuff");
        finish.setPrice("899");

        Book theOldManAndTheSea= new Book();
        theOldManAndTheSea.setTitle("The Old Man And The Sea");
        theOldManAndTheSea.setAuthor("Ernest Hemingway");
        theOldManAndTheSea.setPrice("99");

        //NOTE1--> once you added these books, comment them...

//        databaseHandler.addBook(whatToSayWhenYouTalkToYourSelf);
//        databaseHandler.addBook(finish);
//        databaseHandler.addBook(theOldManAndTheSea);



        //NOTE 3--> to get single entry from a db, or to get a single book from a db

//        Book singleEntryBook = databaseHandler.getBook(2);
//        Log.d("SingleEntryBook", "onCreate: "+"Id: "+singleEntryBook.getId()+
//                ", Title: "+singleEntryBook.getTitle()+
//                ", Author: "+singleEntryBook.getAuthor()+
//                ", Price: "+singleEntryBook.getPrice());

        //NOTE 4--> to update comment NOTE 3  section, because i am updating row 2

//        singleEntryBook.setTitle("NewBook, Finish Book");
//        singleEntryBook.setAuthor("NewBook, Jon Acuff");
//        singleEntryBook.setPrice("NewBook, 999");
//        int updatedBook = databaseHandler.updateBook(singleEntryBook);
//        Log.d("UPDATEDBOOK", "onCreate: "+updatedBook);


        //NOTE 5 --> lets Delete row 2, once this is deleted make sure to comment it out & NOTE 4 as well.
//        Book deleteBook = databaseHandler.getBook(2);
//        databaseHandler.deleteBook(deleteBook);


        //NOTE2--> get all books from database , which were added above

        List<Book> bookList = databaseHandler.getAllBook();
        for (Book book : bookList)
            Log.d("BooksInDB", "onCreate: "+"Id: "+book.getId()+
                    ", Title: "+book.getTitle()+
                    ", Author: "+book.getAuthor()+
                    ", Price: "+book.getPrice());

        //NOTE 6 --> count total entries from database
        Log.d("COUNT", "onCreate: "+databaseHandler.countBook());
    }



}
