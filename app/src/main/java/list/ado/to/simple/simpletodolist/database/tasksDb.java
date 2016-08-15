package list.ado.to.simple.simpletodolist.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import list.ado.to.simple.simpletodolist.model.Task;
import nl.qbusict.cupboard.QueryResultIterable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;


public class tasksDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "todo.db";
    private static final int DATABASE_VERSION = 1;
    private ArrayList<String> ToDoList;

    public tasksDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static {
        cupboard().register(Task.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cupboard().withDatabase(db).upgradeTables();
    }


    public void createTodo(Task task, SQLiteDatabase db){
        cupboard().withDatabase(db).put(task);
    }

    public void updateTodo(String title, int favorite , SQLiteDatabase db, int todoId){
        cupboard().withDatabase(db).delete(Task.class, "todo_id = ?", String.valueOf(todoId));
        Task task = new Task(todoId, title, favorite);
        createTodo(task, db);
    }


    public void deleteTodo(String title, SQLiteDatabase db){
        cupboard().withDatabase(db).delete(Task.class, "todo_text = ?", ""+title);
    }

    public ArrayList getAllTodo(SQLiteDatabase db){
        ToDoList = new ArrayList<>();
        Task task = cupboard().withDatabase(db).query(Task.class).get();
        Cursor cursor = cupboard().withDatabase(db).query(Task.class).getCursor();
        try {
            QueryResultIterable<Task> itr = cupboard().withCursor(cursor).iterate(Task.class);
            for (Task tod : itr) {
                ToDoList.add(tod.getText());
            }
        } finally {
            cursor.close();
        }

        return ToDoList;
    }
}
