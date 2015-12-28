package com.fa7.estagio3.podcastmanager.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.http.protocol.HTTP;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

import com.fa7.estagio3.podcastmanager.entities.Episodio;
import com.fa7.estagio3.podcastmanager.entities.Podcast;

public class XmlUtil {
	
	public XmlPullParser getParser(String xmlString) throws XmlPullParserException, UnsupportedEncodingException{
		
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        
        XmlPullParser parser = factory.newPullParser();    
        
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
        
        InputStream input = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
        parser.setInput(input, HTTP.UTF_8); 
        
        return parser;
	}
	
	public String readTag(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, parser.getNamespace(), parser.getName());
	    String textTag = parser.nextText();	    
	    parser.require(XmlPullParser.END_TAG, parser.getNamespace(), parser.getName());
	    return textTag;
	}
	
	public String readTagAttr(XmlPullParser parser, String attrName) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, parser.getNamespace(), parser.getName());
	    String textTag = parser.getAttributeValue(null, attrName);	    
	    return textTag;
	}
	
	public Episodio readItems(XmlPullParser parser) throws XmlPullParserException, IOException{		
		
		Episodio episodio = new Episodio();
        episodio.setPosition(0);
        episodio.setListened(0);
        episodio.setPath("");
		
		while (parser.next() != XmlPullParser.END_TAG) {			
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }           
                     
            String name = parser.getName().trim();
            String prefix = parser.getPrefix();
            if(prefix != null){
            	Log.e("PREFIX",prefix);
            }
            
            if (name.equals("title")) {
            	episodio.setTitle(this.readTag(parser));
            	Log.e("TÍTULO",episodio.getTitle());
            } else if(name.equals("link")){	
            	episodio.setPostUrl(this.readTag(parser));
            } else if(name.equals("description")){
            	episodio.setDescription(this.readTag(parser));            	
            } else if(name.equals("pubDate")){
            	String pubDate = this.readTag(parser);            	
				Date d = new Date(pubDate);    	
            	episodio.setPubDate(String.valueOf(d.getTime()));            	
            } else if(name.equals("enclosure")){            	
            	episodio.setFileUrl(this.readTagAttr(parser, "url"));
            	episodio.setFileLength(this.readTagAttr(parser, "length"));
//            } else if(name.equals("duration")){
            }else if(parser.getText() != null && "itunes:duration".equals(name)){	
//            	String duration = this.readTag(parser);          	
            	episodio.setDuration(DateTimeUtil.stringTimeToIntegerSeconds(parser.getText()));
            }else {
                this.skip(parser);
            }            
            
        }
		
		return episodio;
		
	}
	
	public Podcast readChannel(XmlPullParser parser) throws XmlPullParserException, IOException{
		
		Podcast podcast = new Podcast();
		
		while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }           
                     
            String name = parser.getName();            
            if (name.equals("title")) {            	
            	if(podcast.getTitle() == null){
            		podcast.setTitle(this.readTag(parser));
            	}            	            	
            } else if (name.equals("description")) {            	
            	podcast.setDescription(this.readTag(parser));
            } else if (name.equals("link")) {                
            	podcast.setSiteUrl(this.readTag(parser));           	
            }else { 
//            	if(!name.equals("item")){
//            		this.skip(parser);
//            	}
            } 
        }
		
		return podcast;
		
	}
	
	public void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
	    if (parser.getEventType() != XmlPullParser.START_TAG) {
	        throw new IllegalStateException();
	    }
	    int depth = 1;
	    while (depth != 0) {
	        switch (parser.next()) {
	        case XmlPullParser.END_TAG:
	            depth--;
	            break;
	        case XmlPullParser.START_TAG:
	            depth++;
	            break;
	        }
	    }
	 }

}
