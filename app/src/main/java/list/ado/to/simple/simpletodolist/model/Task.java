package list.ado.to.simple.simpletodolist.model;

/**
 * Created by mac on 11/08/16.
 */
public class Task {

    private int    todo_id;
    private String todo_text;
    private int todo_favorite;


    public Task(int mId, String mText, int mFavorite) {
        this.todo_id        = mId;
        this.todo_text      = mText;
        this.todo_favorite  = mFavorite;
    }

    public Task(){
        todo_id         = 0;
        todo_text       = "No Text";
        todo_favorite   = 0;
    }


    public int getId() {
        return todo_id;
    }

    public String getText() {
        return todo_text;
    }

    public int getFavorite() {
        return todo_favorite;
    }
}
