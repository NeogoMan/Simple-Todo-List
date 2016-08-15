package list.ado.to.simple.simpletodolist;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;

public class QuoteActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView mQuotesTv;
    private TextView mQuotesAuthorTv;

    private RippleView mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        init();
    }

    private void init() {
        initializeViews();
        initializeObject();
        applyListener();
    }

    private void initializeViews() {
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/bienetresocialb.ttf");
        mQuotesTv = (TextView) findViewById(R.id.quote_text);
        mQuotesAuthorTv = (TextView) findViewById(R.id.quote_author);
        mQuotesTv.setTypeface(custom_font);
        mQuotesAuthorTv.setTypeface(custom_font);

        mQuotesTv.setText(getIntent().getStringExtra("quote"));
        mQuotesAuthorTv.setText(getIntent().getStringExtra("quote_author"));

        mButton = (RippleView) findViewById(R.id.lets_go);
    }

    private void initializeObject() {
    }

    private void applyListener() {
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("from","quoteActivity");
        startActivity(intent);
        finish();
    }
}
