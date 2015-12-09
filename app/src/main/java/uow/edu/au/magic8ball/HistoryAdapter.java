package uow.edu.au.magic8ball;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpStatus;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Pamneedle on 8/31/15.
 */
public class HistoryAdapter extends ArrayAdapter<QuestionResponseModel> {
    private ArrayList<QuestionResponseModel> qrs;
    private Context contxt;

private static class ViewHolder {
    public final TextView questionView;
    public final TextView responseView;
    public final ImageView userImage;


    public ViewHolder(TextView questionView, TextView responseView, ImageView userImage){
        this.questionView = questionView;
        this.responseView = responseView;
        this.userImage = userImage;
    }
}

    public HistoryAdapter(Context context,ArrayList<QuestionResponseModel> qrs) {
        //super(context,R.layout.listrow, qrs);
        //String stringUrl = urlText;
        super(context,R.layout.listrow, qrs);
        this.qrs = qrs;
        this.contxt = context;
    }

    class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;

        public ImageDownloaderTask(ImageView imageView) {
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            //Log.v("testing","params[0]"+params[0]);
            Bitmap bitmap = null;
            try {
                bitmap = downloadBitmap(params[0]);
            } catch(Exception e){
                Log.v("doinbackgroundexception",e.getLocalizedMessage());
            }
            return bitmap;
        };

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }

            if (imageViewReference != null) {
                ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    } else {
                        //Drawable placeholder = imageView.getContext().getResources().getDrawable(R.drawable.circle1);
                        //imageView.setImageDrawable(placeholder);
                    }
                }
            }
        }
    }
    private Bitmap downloadBitmap(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();
//            int statusCode = urlConnection.getResponseCode();
//            if (statusCode != 200) {
//                return null;
//            }
            if(urlConnection != null) {

                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream != null) {
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                }
                inputStream.close();
            }
        } catch (Exception e) {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
            Log.w("ImageDownloader", "Error downloading image from " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

        }
        return null;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        TextView questionView;
        TextView responseView;
        ImageView userImage;


        //this.convertView = convertView;
        //QuestionResponseModel qr = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            //LayoutInflater inflater = ((Activity) HistoryActivity).getLayoutInflater();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listrow, parent, false);
            questionView = (TextView)convertView.findViewById(R.id.text1);
            responseView = (TextView)convertView.findViewById(R.id.text2);
            userImage = (ImageView)convertView.findViewById(R.id.Image);
            convertView.setTag(new ViewHolder(questionView, responseView, userImage));
        }else{
            ViewHolder viewHolder = (ViewHolder)convertView.getTag();
            questionView = viewHolder.questionView;
            responseView = viewHolder.responseView;
            userImage = viewHolder.userImage;
        }
        QuestionResponseModel qr = qrs.get(position);
//        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnected()) {
//            //new ImageDownloaderTask().execute(stringUrl);
//        } else {
//            Log.v("info","network ocnnection unaavailable");
//            //textView.setText("No network connection available.");
//        }
        //this.qrs = qrs;
        //ConnectivityManager connMgr = (ConnectivityManager)contxt.getSystemService(contxt.CONNECTIVITY_SERVICE);
        //NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnected()) {
            new ImageDownloaderTask(userImage).execute(qr.getImage());
//        } else {
//            Log.v("info","network ocnnection unaavailable");
//            //textView.setText("No network connection available.");
//        }


        //new ImageDownloaderTask(userImage).execute(qr.getImage());
        // Populate the data into the template view using the data object
        //QuestionResponseModel qr = qrs.get(position);
        if(questionView!=null) {
            questionView.setText(qr.getQuestion());
        }
        if(responseView!=null) {
            responseView.setText(qr.getResponse());
        }
        // Return the completed view to render on screen
        return convertView;
    }
}