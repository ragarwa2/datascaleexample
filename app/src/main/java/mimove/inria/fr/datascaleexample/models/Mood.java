package mimove.inria.fr.datascaleexample.models;

import com.ambientic.crowdsource.data.GoFlowValue;

import java.util.Calendar;

/**
 * Created by frebhi on 23/06/2015.
 */
public class Mood extends GoFlowValue {

    private String mood;
    private Calendar time;

    public Mood() {
        super();
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

}
