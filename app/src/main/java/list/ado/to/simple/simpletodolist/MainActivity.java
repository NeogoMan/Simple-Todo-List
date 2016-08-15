package list.ado.to.simple.simpletodolist;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;


import com.afollestad.materialdialogs.MaterialDialog;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.github.clans.fab.FloatingActionButton;
import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import list.ado.to.simple.simpletodolist.database.quotesNotificationsDb;
import list.ado.to.simple.simpletodolist.database.tasksDb;
import list.ado.to.simple.simpletodolist.model.Quote;
import list.ado.to.simple.simpletodolist.model.Task;
import list.ado.to.simple.simpletodolist.utils.AppConstant;

public class MainActivity extends AppCompatActivity implements SwipeMenuListView.OnMenuItemClickListener, View.OnClickListener {


    private SwipeMenuListView mListView;
    private FloatingActionButton mFloatingButton;

    private ArrayAdapter<String> adapter;

    private tasksDb dbHelper ;
    private quotesNotificationsDb dbQuotes;
    private SQLiteDatabase db ;
    private SQLiteDatabase dbq ;

    private List<String> arrayList;
    private List<String> deleteList;

    private Random random;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initializeObjects();
        initializeViews();
        aboutQuotes();
        applyListener();
        getRandomQuoteNotofication();

    }

    public void initializeObjects() {
        arrayList = new ArrayList<>();
        deleteList= new ArrayList<>();
        dbHelper  = new tasksDb(this);
        dbQuotes  = new quotesNotificationsDb(this);

        db        = dbHelper.getWritableDatabase();
        dbq       = dbQuotes.getWritableDatabase();

    }

    private void initializeViews() {
        mListView       = (SwipeMenuListView)    findViewById(R.id.listView);
        mFloatingButton = (FloatingActionButton) findViewById(R.id.fab);

        setDataIntoListView();
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem mDeleteItem = new SwipeMenuItem(getApplicationContext());
                createItemMenu(mDeleteItem, new ColorDrawable(Color.RED), 90, getResources().getDrawable(R.drawable.ic_delete_forever_white_18dp));
                menu.addMenuItem(mDeleteItem);
            }
        };

        mListView.setMenuCreator(creator);

    }

    public void setDataIntoListView(){
        arrayList = dbHelper.getAllTodo(db);
        if (arrayList != null ) {
            adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, arrayList);
            mListView.setAdapter(adapter);
        }
    }

    public void aboutQuotes(){
        if (dbQuotes.quotesIsEmpty(dbq)){
            for (Quote q: AppConstant.quotesArray()){
                dbQuotes.createQuote(q, dbq);
            }
        }
    }

    public void applyListener() {
        mListView.setOnMenuItemClickListener(this);
        mFloatingButton.setOnClickListener(this);
    }

    public void createItemMenu(SwipeMenuItem swipeMenuItem, ColorDrawable colorDrawable,
                               int width, Drawable drawable) {
        swipeMenuItem.setBackground(colorDrawable);
        swipeMenuItem.setWidth(width);
        swipeMenuItem.setTitleColor(Color.WHITE);
        swipeMenuItem.setIcon(drawable);
    }

    @Override
    public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
        switch (index) {
            case 0:
                deleteList.add(arrayList.get(position));
                arrayList.remove(position);
                dbHelper.deleteTodo(deleteList.remove(0), db);
                adapter.notifyDataSetChanged();
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        new MaterialDialog.Builder(this)
                .title(R.string.what_do_you)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(" ", " ", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        if (!input.toString().equals(" ")){
                            Task task = new Task(0,""+input.toString(),0);
                            arrayList.add(task.getText());
                            dbHelper.createTodo(task,db);
                            adapter.notifyDataSetChanged();
                        }else {
                            new BottomDialog.Builder(MainActivity.this)
                                    .setTitle(R.string.notice)
                                    .setContent(R.string.no_data_entered)
                                    .show();
                        }
                    }
                }).show();
    }


    private void createNotification(int iconRes, String title, Quote quote) {

        Intent intent = new Intent(this, QuoteActivity.class);
        intent.putExtra("quote",quote.getQuote_text());
        intent.putExtra("quote_author", quote.getQuote_author());


        int requestID = (int) System.currentTimeMillis(); //unique requestID to differentiate between various notification with same NotifId
        int flags = PendingIntent.FLAG_CANCEL_CURRENT; // cancel old intent and create new one
        PendingIntent pIntent = PendingIntent.getActivity(this, requestID, intent, flags);

        Notification noti = new NotificationCompat.Builder(this)
                .setSmallIcon(iconRes)
                .setContentTitle(title)
                .setContentText(quote.getQuote_text())
                .setContentIntent(pIntent)
                .setAutoCancel(true) // Hides the notification after its been selected
                .build();

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(requestID, noti);
    }

    public void getRandomQuoteNotofication() {
        if (getIntent().getStringExtra("from") == null){
            random = new Random();
            Quote q = dbQuotes.getQuote(dbq, random.nextInt(9)+"");
            createNotification(R.drawable.ic_icon, "Quote time", q);
        }
    }
}
