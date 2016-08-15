package list.ado.to.simple.simpletodolist.model;

/**
 * Created by mac on 14/08/16.
 */
public class Quote {


    private String    quote_id;
    private String quote_text;
    private String quote_author;


    public Quote(String quote_id, String quote_text, String quote_author){
        this.quote_id     = quote_id;
        this.quote_text   = quote_text;
        this.quote_author = quote_author;
    }

    public Quote(){

    }


    public String getQuote_id() {
        return quote_id;
    }

    public String getQuote_text() {
        return quote_text;
    }

    public String getQuote_author() {
        return quote_author;
    }
}
