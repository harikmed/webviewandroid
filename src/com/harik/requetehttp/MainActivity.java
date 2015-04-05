package com.harik.requetehttp;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	JSONArray jsonArray = null;
	ClientServerInterface clientServerInterface = new ClientServerInterface();
	TextView textView;
	String ab;
	private ListView l;
	private SimpleAdapter sa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		l=(ListView)findViewById(R.id.listView1);
		//textView=(TextView) findViewById(R.id.textView1);
		new RetreiveData().execute();


		
	}
	
	
	
	class RetreiveData extends AsyncTask<String,SimpleAdapter,JSONArray>{
	
	@Override
	protected void onPreExecute() {
		Toast.makeText(getApplicationContext(), "chargement en cours ....", Toast.LENGTH_LONG).show();
	}
		@Override
		protected JSONArray doInBackground(String... arg0) {
		// TODO Auto-generated method stub
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
	//	jsonArray = clientServerInterface.makeHttpRequest("http://192.168.1.7/androideclipse/infos.php");
			ArrayList<BasicNameValuePair> nv=new ArrayList<BasicNameValuePair>();
			nv.add(new BasicNameValuePair("libelle", ""));
		jsonArray = clientServerInterface.makeHttpRequest("http://192.168.1.7/androideclipse/infos.php",nv);
			
	System.out.println("jobj"+jsonArray);
//		try {
////		ab = jsonArray.getJSONObject(0).getString("libelle");
//		} catch (JSONException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		}
	return jsonArray;
		}
		protected void onPostExecute(JSONArray jarray){
			Toast.makeText(getApplicationContext(), "fin chargement", Toast.LENGTH_LONG).show();
			ArrayList<HashMap<String, Object>> data=new ArrayList<HashMap<String,Object>>();
			for (int i = 0; i < jarray.length(); i++) {
				HashMap<String, Object> h=new HashMap<String, Object>();
				try {
					h.put("libelle", jarray.getJSONObject(i).getString("libelle"));
					h.put("prix", jarray.getJSONObject(i).getString("prix"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
					
				data.add(h);
			}
			String [] from =new String[]{"libelle","prix"};
			int [] to=new int[]{R.id.libelle,R.id.prix};
			
			//il est important de creer la vue ici et on pas dans oncreate
			 sa= new SimpleAdapter(getApplicationContext(), data, R.layout.list_item, from, to);
			 l.setAdapter(sa);
		}
		}
}
