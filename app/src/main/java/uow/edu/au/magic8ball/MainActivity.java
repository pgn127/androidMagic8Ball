package uow.edu.au.magic8ball;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("info", "Pamela Needle");
        Log.i("info", "" + 20.00);
        String name = "Pamela Needle";
        Log.i("info", name);
        //

        ArrayList<String> initialResponses = new ArrayList<String>();
        initialResponses.add("Obviously.");
        initialResponses.add("Not a chance.");
        MagicEightBallModel model = new MagicEightBallModel(initialResponses);

        Log.i("question","Will I pass this subject?");
        Log.i("answer", model.getRandomResponse());
        Log.i("question", "Is CSCI342 challenging?");
        Log.i("answer",model.getRandomResponse());
        Log.i("question","Will it rain tomorrow?");
        Log.i("answer",model.getRandomResponse());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
