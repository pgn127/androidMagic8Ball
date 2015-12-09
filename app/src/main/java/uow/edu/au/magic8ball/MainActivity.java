package uow.edu.au.magic8ball;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import static android.widget.ImageView.ScaleType.CENTER_INSIDE;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {


    TextView responseText;
    EditText questionText;
    TextToSpeech ttobj;
    Button historyButton;
    ImageView circle;
    //ArrayList<NameValuePair> postParameters;
    String user;
    //String question;
    //private ArrayList<QuestionResponseModel> qrArray;
    private String urlText = "http://li859-75.members.linode.com/addEntry.php";

    //MediaPlayer mp = new MediaPlayer();
    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {
        //InputStream stream = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected Boolean doInBackground(String... urls) {
            //OutputStream is = null;
            try {
                Log.v("entered","try entered");

                URL url = new URL(urlText);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                //Map<String, String> postParameters = new HashMap<String,String>();
//                postParameters.put("user", "pgn342");
//                postParameters.put("question", questionText.getText().toString());
//                postParameters.put("response", responseText.getText().toString());
                //StringBuilder postData = new StringBuilder();

//                for (Map.Entry<String, String> param : postParameters.entrySet()) {
//                    Log.v("crahs","entered for loop");
//                    if (postData.length() != 0) {
//                        postData.append("&");
//                        postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
//                        postData.append("=");
//                        postData.append(URLEncoder.encode(param.getValue(), "UTF-8"));
//                    }
//                }
                String postString = "username=pgn342&question="+questionText.getText()+"&answer="+responseText.getText();
                Log.v("TESTING", postString);
                //String encodedData = URLEncoder.encode(postString);
                //byte[] postDataBytes = postData.toString().getBytes("UTF-8");
                byte[] postDataBytes = postString.getBytes("UTF-8");
                Log.v("TESTING", "postdatabytescompleted");
                //connection.setReadTimeout(10000);
                //connection.setConnectTimeout(15000); /*milliseconds*/
//                connection.setRequestMethod("POST");
//                connection.setDoOutput(true);
//
//                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                //connection.setRequestProperty("Accept", "*/*");
                //connection.setRequestProperty ("Authorization", encodedCredentials);
                //connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                Log.v("databyteslength", ""+postDataBytes.length);
                //connection.setRequestProperty( "Content-Length", String.valueOf(encodedData.length()));
                connection.setChunkedStreamingMode(0);

//                connection.setDoInput(true);
                connection.connect();
                Log.v("TESTING", "connection.connect worked");
                OutputStream os = connection.getOutputStream();
                os.write(postString.getBytes("UTF-8"));
                os.close();

                int response = connection.getResponseCode(); //If 200, continue 
                //Log.v("response code", ""+response);
                if (response == 200) {
                    //Log.v("testing2", "data posted??");
                    //OutputStream os = connection.getOutputStream();
                    //OutputStream out = new BufferedOutputStream()
                    //OutputStream dos = connection.getOutputStream();
                    //connection.getOutputStream().write(postString.getBytes("UTF-8"));
                    //Log.v("tefwefwefa3", "data posted??");
                    //os.write(encodedData.getBytes());
                    //Writer writer = new OutputStreamWriter(os);
                    //os.write(postString.getBytes());
                    //writer.flush();
                    //os.write(postDataBytes);
                    //dos.flush();
                    //dos.close();

                    //Log.v("tefwefwefa3", "data posted??");
                    //os.close();

                    //return true;
                    //return true;
//                    DataOutputStream wr = new DataOutputStream (
//                            connection.getOutputStream ());
//                    wr.writeBytes (postDataBytes);
//                    wr.flush();
//                    wr.close ();
//                    String line;
//                    BufferedReader reader = new BufferedReader(new
//                            InputStreamReader(connection.getInputStream()));
//                    while ((line = reader.readLine()) != null) {
//                        Log.v("aweifwjef",line.toString());
//                    }

                }
                connection.disconnect();
            } catch (IOException e) {
                //exception
                Log.v("exception",e.getLocalizedMessage());

            }


            return true;
        }

        protected void onPostExecute(Boolean result) {

        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ttobj=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    ttobj.setLanguage(Locale.getDefault());
                }
            }
        }
        );
        ArrayList<String> initialResponses = new ArrayList<String>();
        //String fosho = getResources.getString()
       initialResponses.add(getResources().getString(R.string.forsure));
        initialResponses.add(getResources().getString(R.string.mayb));
        initialResponses.add(getResources().getString(R.string.mostdef));
        initialResponses.add(getResources().getString(R.string.notachance));
        initialResponses.add(getResources().getString(R.string.yeahright));
        //initialResponses.add("No.");
        final MagicEightBallModel model = new MagicEightBallModel(initialResponses);

        //check for network conneciton


//        ArrayList<QuestionResponseModel> loaded = loadQA();
//        if (loaded != null) {
//            this.qrArray = loaded;
//        } else {
//            this.qrArray = new ArrayList<QuestionResponseModel>();
//        }

        responseText = (TextView)findViewById(R.id.responseView);
        circle = (ImageView)findViewById(R.id.circleimg);
        questionText = (EditText)findViewById(R.id.question);
        historyButton = (Button)findViewById(R.id.history);

        //historyButton.setOnClickListener(this);

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //check for network connection
//                String stringUrl = urlText;
//                ConnectivityManager connMgr = (ConnectivityManager)
//                        getSystemService(Context.CONNECTIVITY_SERVICE);
//                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//                if (networkInfo != null && networkInfo.isConnected()) {
//                    new DownloadFilesTask().execute(stringUrl);
//                } else {
//                    Log.v("info","network ocnnection unaavailable");
//                    //textView.setText("No network connection available.");
//                }
            //displayResponse(model);
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            //intent.putExtra("historyArray", qrArray);
            startActivity(intent);

        }
        });

        questionText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    mgr.hideSoftInputFromWindow(questionText.getWindowToken(), 0);
                    displayResponse(model);
                    handled = true;
                    ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        Log.v("test","connection is present");
                        new JSONAsyncTask().execute(urlText);
                    } else {
                        //connection not available
                        Log.v("test","connection not avail");
                    }
                }
                return handled;
            }
        });

    }


    public void displayResponse(MagicEightBallModel ball) {

        //need to change circles image
        //set text on ball
        ObjectAnimator fadeAltAnim = ObjectAnimator.ofFloat(responseText, View.ALPHA, 0, 1);
        fadeAltAnim.start();
        fadeAltAnim.setDuration(1000);

        responseText.setText(ball.getRandomResponse());
        String toSpeak = responseText.getText().toString();
        Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
        ttobj.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);


//        mp.reset();
//        if (mp.isPlaying()) {
//            mp.stop();
//            //mp.reset();
//        }
//        try {
//            AssetFileDescriptor file = getAssets().openFd(ball.getCurrentSoundString());
//            mp.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
//            file.close();
//            mp.prepare();
//            mp.start();
//        }catch(IOException e) {
//            e.printStackTrace();
//        }


//        QuestionResponseModel qa = new QuestionResponseModel(questionText.getText().toString(), responseText.getText().toString());
//        //save question response
//        qrArray.add(qa);
//        saveQA(qrArray);
        //SAVE HERE


        Random rand = new Random();
        int x = rand.nextInt(6);
        switch (x) {
            case 0:
                circle.setImageResource(R.drawable.circle1);
                break;
            case 1:
                circle.setImageResource(R.drawable.circle2);
                break;
            case 2:
                circle.setImageResource(R.drawable.circle3);
                break;
            case 3:
                circle.setImageResource(R.drawable.circle4);
                break;
            case 4:
                circle.setImageResource(R.drawable.circle5);
                break;
            case 5:
                circle.setImageResource(R.drawable.circle6);
                break;
            default:
                break;
        }
    }

//    public void saveQA(ArrayList<QuestionResponseModel> qrArray) {
//        try {
//
//            String f = "qrHistory.bin";
//            //String string =
//            FileOutputStream fos = openFileOutput(f,Context.MODE_PRIVATE);
//            ObjectOutputStream oos = new ObjectOutputStream(fos); //Select where you wish to save the file...
//            oos.writeObject(qrArray); // write the class as an 'object'
//            oos.flush(); // flush the stream to insure all of the information was written to 'qrHistory.bin'
//            oos.close();// close the stream
//        } catch (Exception ex) {
//            Log.v("Question Response History Save", ex.getMessage());
//            ex.printStackTrace();
//        }
//    }
    public void onPause(){
        if(ttobj !=null){
            ttobj.stop();
            ttobj.shutdown();
        }
        super.onPause();
    }

//    public ArrayList<QuestionResponseModel> loadQA() {
//        try {
//            FileInputStream fis = openFileInput("qrHistory.bin");
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            ArrayList<QuestionResponseModel> o = (ArrayList<QuestionResponseModel>)ois.readObject();
//            return o;
//        } catch (Exception ex) {
//            Log.v("Question Response History Load", ex.getMessage());
//            ex.printStackTrace();
//        }
//        return null;
//    }

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
