package list.ado.to.simple.simpletodolist.utils;

import java.util.ArrayList;
import java.util.List;

import list.ado.to.simple.simpletodolist.model.Quote;

/**
 * Created by mac on 14/08/16.
 */
public class AppConstant {

    public static ArrayList<Quote> quotesArray(){
        ArrayList<Quote> quotesList = new ArrayList<>();
        quotesList.add(new Quote("0","“Productivity is being able to do things that you were never able to do before.”","Franz Kafka"));
        quotesList.add(new Quote("1","“Being rich is having money; being wealthy is having time.”","Margaret Bonnano"));
        quotesList.add(new Quote("2", "“Life is too complicated not to be orderly.”", "Martha Stewart"));
        quotesList.add(new Quote("3", "“Amateurs sit and wait for inspiration, the rest of us just get up and go to work.”", "Stephen King"));
        quotesList.add(new Quote("4", "“If you spend too much time thinking about a thing, you’ll never get it done.” ", " Bruce Lee"));
        quotesList.add(new Quote("5", "“It’s not knowing what to do, it’s doing what you know.”", "Tony Robbins"));
        quotesList.add(new Quote("6", "“Why do anything unless it is going to be great?” ", "Peter Block"));
        quotesList.add(new Quote("7", "“It is not enough to be busy… The question is: what are we busy about?”", "Henry David Thoreau"));
        quotesList.add(new Quote("8", "“To think is easy. To act is difficult. To act as one thinks is the most difficult.” ", "Johann Wolfgang Von Goeth"));
        quotesList.add(new Quote("9", "“It is well to be up before daybreak, for such habits contribute to health, wealth, and wisdom”", "Aristotle"));
        return quotesList;
    }
}
