package list.ado.to.simple.simpletodolist.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import list.ado.to.simple.simpletodolist.model.Quote;
import list.ado.to.simple.simpletodolist.model.Task;
import nl.qbusict.cupboard.QueryResultIterable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;


public class quotesNotificationsDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Quotes.db";
    private static final int DATABASE_VERSION = 1;
    private ArrayList<Quote> QuotesList;

    public quotesNotificationsDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static {
        cupboard().register(Quote.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cupboard().withDatabase(db).upgradeTables();
    }

    public void createQuote(Quote quote, SQLiteDatabase db){
        cupboard().withDatabase(db).put(quote);
    }


    public Boolean quotesIsEmpty(SQLiteDatabase db){
        Boolean isEmpty = false;
        try {
            Quote quote = cupboard().withDatabase(db).query(Quote.class).get();
            if (quote == null){
                isEmpty = true;
            }
        }catch (Exception e){

        }
        return isEmpty;
    }


    public ArrayList getAllTodo(SQLiteDatabase db){
        QuotesList = new ArrayList<>();

        Quote quote = cupboard().withDatabase(db).query(Quote.class).get();

        Cursor cursor = cupboard().withDatabase(db).query(Task.class).getCursor();
        try {
            QueryResultIterable<Quote> itr = cupboard().withCursor(cursor).iterate(Quote.class);
            for (Quote quot : itr) {
                QuotesList.add(quot);
            }
        } finally {
            cursor.close();
        }

        return QuotesList;
    }


    public Quote getQuote(SQLiteDatabase db, String id){
        return cupboard().withDatabase(db).query(Quote.class).withSelection( "quote_id = ?", id).get();
    }
}
