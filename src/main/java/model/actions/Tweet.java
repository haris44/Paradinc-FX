package model.actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Created by nathan on 21/01/17.
 */
public class Tweet {
    public Date getDate() {
        return date;
    }


    public String getMessage() {
        return message;
    }


    private Date date;
    private String message;

    public Tweet(Date date, String message){
        this.date  = date;
        this.message = message;

    }
    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM hh:mm");
        return dateFormat.format(date)

                +" : "
                + this.message;

    }

}
