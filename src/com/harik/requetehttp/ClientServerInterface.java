package com.harik.requetehttp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* this class helps to get data from server*/
public class ClientServerInterface {
//input stream deals with bytes
static InputStream is = null;
static JSONObject jobj = null;
static String json = "";
private JSONArray jArray;
//constructor
public ClientServerInterface(){
}
//this method returns json object.
public JSONArray makeHttpRequest(String url){
//http client helps to send and receive data
DefaultHttpClient httpclient = new DefaultHttpClient();
//our request method is post
HttpPost httppost = new HttpPost(url);
try {
//ArrayList<BasicNameValuePair> namesValues=new ArrayList<BasicNameValuePair>();
//namesValues.add(new BasicNameValuePair("adresse", "casablanca"));
//envoie des données
//httppost.setEntity(new UrlEncodedFormEntity(namesValues));
//get the response
HttpResponse httpresponse = httpclient.execute(httppost);
HttpEntity httpentity = httpresponse.getEntity();

// get the content and store it into inputstream object.
is = httpentity.getContent();
} catch (ClientProtocolException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
try {
//convert byte-stream to character-stream.
BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
StringBuilder sb = new StringBuilder();
String line = null;
try {
while((line = reader.readLine())!=null){
sb.append(line+"\n");
}
//close the input stream
is.close();
json = sb.toString();
try {
	  jArray = new JSONArray(json);
//	 for (int i = 0; i < jArray.length(); i++) {
//		  jobj = jArray.getJSONObject(i);
//	}
//	
//jobj = new JSONObject(json);
} catch (JSONException e) {
// TODO Auto-generated catch block
System.out.println("err 1 "+e.getMessage());
}
} catch (IOException e) {
// TODO Auto-generated catch block
	System.out.println("err 2 "+e.getMessage());
}
} catch (UnsupportedEncodingException e) {
// TODO Auto-generated catch block
	System.out.println("err 3 "+e.getMessage());
}
return jArray;
}

//methodes qui retourne un json array et qui recoit un tableau de parametre
//this method returns json object.
public JSONArray makeHttpRequest(String url,ArrayList<BasicNameValuePair> namesValues){
//http client helps to send and receive data
DefaultHttpClient httpclient = new DefaultHttpClient();
//our request method is post
HttpPost httppost = new HttpPost(url);
try {
//ArrayList<BasicNameValuePair> namesValues=new ArrayList<BasicNameValuePair>();
//namesValues.add(new BasicNameValuePair("adresse", "casablanca"));
//envoie des données
httppost.setEntity(new UrlEncodedFormEntity(namesValues));
//get the response
HttpResponse httpresponse = httpclient.execute(httppost);
HttpEntity httpentity = httpresponse.getEntity();

//get the content and store it into inputstream object.
is = httpentity.getContent();
} catch (ClientProtocolException e) {
//TODO Auto-generated catch block
e.printStackTrace();
} catch (IOException e) {
//TODO Auto-generated catch block
e.printStackTrace();
}
try {
//convert byte-stream to character-stream.
BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
StringBuilder sb = new StringBuilder();
String line = null;
try {
while((line = reader.readLine())!=null){
sb.append(line+"\n");
}
//close the input stream
is.close();
json = sb.toString();
try {
	  jArray = new JSONArray(json);
//	 for (int i = 0; i < jArray.length(); i++) {
//		  jobj = jArray.getJSONObject(i);
//	}
//	
//jobj = new JSONObject(json);
} catch (JSONException e) {
//TODO Auto-generated catch block
System.out.println("err 1 "+e.getMessage());
}
} catch (IOException e) {
//TODO Auto-generated catch block
	System.out.println("err 2 "+e.getMessage());
}
} catch (UnsupportedEncodingException e) {
//TODO Auto-generated catch block
	System.out.println("err 3 "+e.getMessage());
}
return jArray;
}

}
