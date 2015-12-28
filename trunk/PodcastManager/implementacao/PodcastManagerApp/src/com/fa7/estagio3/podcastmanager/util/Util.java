package com.fa7.estagio3.podcastmanager.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Util {

	public static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}

	public static String mapParamsToString(Map<String, String> params) {

		Integer paramsSize = params.size();
		Iterator<Entry<String, String>> it = params.entrySet().iterator();
		String finalParams = "";

		int i = 0;
		while (it.hasNext()) {
			Entry<String, String> param = it.next();
			System.out.println(param.getKey() + " = " + param.getValue());

			finalParams += param.getKey() + "=" + param.getValue();

			if ((i + 1) < paramsSize) {
				finalParams += "&";
			}

			it.remove();
			i++;
		}

		return finalParams;
	}

	public boolean appIsConnected(Context context){
    	ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
    	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
    	    if (networkInfo != null && networkInfo.isConnected()) 
    	    	return true;
    	    else
    	    	return false;	
    }

	public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
              int count=is.read(bytes, 0, buffer_size);
              if(count==-1)
                  break;
              os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
	
	public static String convertBytesToMegaBytes(String bytes){
		long fileSizeBytes = Long.parseLong(bytes); 
		
		long fileSizeKbytes = fileSizeBytes/1024; 
		
		long fileSizeMbytes = fileSizeKbytes/1024;
		
		return String.valueOf(fileSizeMbytes);
	}
	
}
