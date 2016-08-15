package list.ado.to.simple.simpletodolist.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import list.ado.to.simple.simpletodolist.R;

/**
 * Created by mac on 11/08/16.
 */
public class DialogAddNewTodo extends FrameLayout {

    public DialogAddNewTodo(Context context) {
        this(context, null);
    }

    public DialogAddNewTodo(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public DialogAddNewTodo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initializeViews();
        applyListener();
    }


    private void initializeViews() {
        inflate(getContext(), R.layout.view_dialog_add_new_todo, this);

    }

    private void applyListener() {
    }

}
