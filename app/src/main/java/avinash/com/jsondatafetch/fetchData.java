package avinash.com.jsondatafetch;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask {
    String data="",singleParse="",dataParsed="";

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        MainActivity.data.setText(this.dataParsed);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url=new URL("https://api.myjson.com/bins/17mkwb");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

            String line="";
            while(line !=null){
                line=bufferedReader.readLine();
                data=data+line;

                JSONArray JA=new JSONArray(data);
                for(int i=0;i<JA.length();i++){
                    JSONObject JO= (JSONObject) JA.get(i);
                    singleParse="Name"+" "+JO.get("name")+"\n"+
                    "Password"+" "+JO.get("password")+"\n"+
                    "Country"+" "+JO.get("country")+"\n";
                    dataParsed=dataParsed+singleParse+"\n";
                }

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;


    }
}
