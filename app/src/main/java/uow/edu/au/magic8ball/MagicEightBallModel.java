package uow.edu.au.magic8ball;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by pgn342 on 3/08/15.
 */
public class MagicEightBallModel extends Object {

    private static ArrayList<String> defaultResponseArray;
    private ArrayList<String> responseArray;
    private String[] soundStrings = {"maforsure.mp3","mamaybe.mp3","mamostdefinitely.mp3","manotachance.mp3","mayeahright.mp3"};
    private int currentSoundIndex;



    public MagicEightBallModel() {
        //default string response added to the defualtResponseArray
        ;
//        this.defaultResponseArray = new ArrayList<String>();
//        this.defaultResponseArray.add(getResources().getString(android.R.string.forsure));
//        this.defaultResponseArray.add("Maybe");
//        this.defaultResponseArray.add("Most definitely");
//        this.defaultResponseArray.add("Not a chance");
        //this.defaultResponseArray.add("No");


        //create new default response array object
        this.responseArray = new ArrayList<String>();

    }

    public MagicEightBallModel(ArrayList<String> initialResponseArray) {
        this();
        responseArray.addAll(initialResponseArray);

    }

    public String getRandomResponse() {
        Random rn = new Random();
        int size = responseArray.size();
        currentSoundIndex = rn.nextInt(size);
        return responseArray.get(currentSoundIndex);
    }

    public String getCurrentSoundString(){
        return soundStrings[currentSoundIndex];
    }

    public String toString() {
        StringBuilder responsestr = new StringBuilder();
        for (String str : this.responseArray)
            responsestr.append(str);
        return responsestr.toString();

    }
}
