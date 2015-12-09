package uow.edu.au.magic8ball;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import org.apache.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class HistoryActivity extends ListActivity {

    private ArrayList<QuestionResponseModel> qrArray;
    //final ListView listView = (ListView) findViewById(R.id.listView);
    private HistoryAdapter adapter;
    private ListView listView;
    private String urlText = "http://li859-75.members.linode.com/retrieveAllEntries.php";

    private class DownloadFilesTask extends AsyncTask<String, Integer,
                ArrayList<QuestionResponseModel>> {

        protected ArrayList<QuestionResponseModel> downloadURL(String urlString) throws IOException {
            InputStream is = null;
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000); /*milliseconds*/
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();
                int response = connection.getResponseCode(); //If 200, continue 
                if (response == 200) {
                    is = connection.getInputStream();
                    JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
                    try {
                        qrArray = new ArrayList<QuestionResponseModel>();
                        reader.beginArray();
                        while (reader.hasNext()) {
                            String quest = null;
                            String answ = null;
                            String user = null;
                            String img = null;
                            reader.beginObject();
                            while (reader.hasNext()) {
                                String currentkey = reader.nextName();
                                //Log.d("error",currentkey);
                                if (currentkey.equals("question")) {
                                    quest = reader.nextString();
                                } else if (currentkey.equals("answer")) {
                                    answ = reader.nextString();
                                } else if (currentkey.equals("username")) {
                                    user = reader.nextString();
                                } else if (currentkey.equals("imageURL")) {
                                    img = reader.nextString();
                                    //ImageView imgView = new ImageView;
                                    //new ImageDownloaderTask().execute(img);
                                } else {
                                    reader.skipValue();
                                }
                            }
                            //new DownloadFilesTask().execute(stringUrl);
                            reader.endObject();

                            qrArray.add(new QuestionResponseModel(quest, answ, img, user));
                        }
                        reader.endArray();
                        reader.close();

                        return qrArray;
                    } catch (IOException e) {
                        //exception
                    }

                }
            }finally{
                if (is != null) {
                    is.close();
                    //downloadCompleted();
                }
            }
            return null;

        }

        protected ArrayList<QuestionResponseModel> doInBackground(String... urls){
            try {
                return downloadURL(urls[0]);
            } catch (IOException e) {
                return null;
//                return "Unable to retrieve web page. URL may be invalid.";
            }
        } //Executed in background
        protected void onProgressUpdate(Integer... progress){}
        protected void onPostExecute(ArrayList<QuestionResponseModel> result){
            //HANDLE RESULT
            downloadCompleted();
            Log.d("done","donwload complete");

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        listView = (ListView)findViewById(android.R.id.list);
        Intent intent = getIntent();

        String stringUrl = urlText;
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadFilesTask().execute(stringUrl);
        } else {
            Log.v("info","network ocnnection unaavailable");
            //textView.setText("No network connection available.");
        }

        //Bundle extras = intent.getExtras();

        //this.historyArray = new ArrayList<QuestionResponseModel>();
        //if(null!=intent) {
        //this.historyArray = (ArrayList<QuestionResponseModel>)intent.getSerializableExtra("historyArray");
        //}
        //historyArray.
        //Log.v("testing", "thisishistoyarray"+historyArray.size());

//        adapter = new HistoryAdapter(this, android.R.layout.simple_list_item_2, qrArray);
//        listView.setAdapter(adapter);
    }


    public void downloadCompleted(){
        adapter = new HistoryAdapter(this, qrArray);
        listView.setAdapter(adapter);
        listView.refreshDrawableState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
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
