package uow.edu.au.magic8ball;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by pgn342 on 3/08/15.
 */
public class MagicEightBallModel extends Object {

    private static ArrayList<String> defaultResponseArray;

    private ArrayList<String> responseArray;

    public MagicEightBallModel() {
        //default string response added to the defualtResponseArray
        this.defaultResponseArray = new ArrayList<String>();
        this.defaultResponseArray.add("The answer to your question is no way!");

        //create new default response array object
        this.responseArray = new ArrayList<String>(defaultResponseArray);
    }

    public MagicEightBallModel(ArrayList<String> initialResponseArray) {
        this();
        responseArray.addAll(initialResponseArray);

    }

    public String getRandomResponse() {
        Random rn = new Random();
        int size = responseArray.size();
        return responseArray.get(rn.nextInt(size));
    }

    public String toString() {
        StringBuilder responsestr = new StringBuilder();
        for (String str : this.responseArray)
            responsestr.append(str);
        return responsestr.toString();

    }
}
