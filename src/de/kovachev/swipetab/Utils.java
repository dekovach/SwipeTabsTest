package de.kovachev.swipetab;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Helper class used for network communication and parsing JSON results
 *
 */
public class Utils{
	
	/**
	 * Performs HTTP GET request to a given url and returns the result as String 
	 * @param url Web service endpoint
	 * @return String JSON response of the response
	 */
	public static String getJson(String url) {
		DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
		HttpGet httpget = new HttpGet(url);
		// Depends on your web service
		httpget.setHeader("Content-type", "application/json");

		InputStream inputStream = null;
		String result = null;
		try {
		    HttpResponse response = httpclient.execute(httpget);           
		    HttpEntity entity = response.getEntity();

		    inputStream = entity.getContent();
		    // json is UTF-8 by default
		    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
		    StringBuilder sb = new StringBuilder();

		    String line = null;
		    while ((line = reader.readLine()) != null)
		    {
		        sb.append(line + "\n");
		    }
		    result = sb.toString();
		    
		    
		} catch (Exception e) { 
		    // Oops
			e.printStackTrace();
			return "";
		}
		finally {
		    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
		}
		return result;
	}
	
	/**
	 * Parses the response string into a JSON object and extracts the paths of the images, putting them into a set
	 * @param result JSON as a string
	 * @return HashSet of image paths
	 */
	public static HashSet<String> parseResult(String result) {
	    JSONObject jObject;
	    HashSet<String> path = new HashSet<String>();
		try {
			jObject = new JSONObject(result);
			JSONObject metadata = jObject.getJSONObject("metadata");
			JSONArray results = metadata.getJSONArray("results");
			for (int i=0; i<results.length(); i++) {

			       	JSONArray images = results.getJSONObject(i).getJSONArray("images");
		        	for (int j=0; j< images.length(); j++) {
		        		JSONObject oneImage = images.getJSONObject(j);
		        		String oneImagePath = oneImage.getString("path");
		        		path.add(oneImagePath);
		        	}
			}
		} catch (JSONException e) {
            // Oops
			e.printStackTrace();
        }
		return path;
	}
}