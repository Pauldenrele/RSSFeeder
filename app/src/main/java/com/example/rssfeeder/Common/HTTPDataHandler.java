package com.example.rssfeeder.Common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;

public class HTTPDataHandler {
static  String stream = "";
public HTTPDataHandler (){
}
public  String getHTTPDataHandler(String urlString){
    //function that get data from a web API
    try{
        URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
             if(urlConnection.getResponseCode()==HTTP_OK){
                 InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                 BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
                         StringBuilder sb = new StringBuilder();
                         String line = "";
                         while((line = r.readLine())!=null)
                             sb.append(line);
                         stream = sb.toString();
                         urlConnection.disconnect();
             }



    }catch (Exception ex){
        return  null;
    }
    return  stream;
}
}
