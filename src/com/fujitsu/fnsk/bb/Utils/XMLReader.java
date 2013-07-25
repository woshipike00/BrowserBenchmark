package com.fujitsu.fnsk.bb.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import android.content.Context;

public class XMLReader {
	private Document document;
	
	public XMLReader(Context context,String filename) throws DocumentException, IOException{
		SAXReader reader=new SAXReader();
		document=reader.read(context.getAssets().open(filename));
	}
	
	public ArrayList<String> parse(){
		ArrayList<String> list=new ArrayList<String>();
		Element root=document.getRootElement();
		List<Element> rounds=root.elements("round");
		for(Iterator<Element> itr=rounds.iterator();itr.hasNext();){
			Element round=(Element)itr.next();
			String temp="";
			temp+=round.element("S").getStringValue()+",";
			temp+=round.element("T").getStringValue()+",";
			temp+=round.element("V").getStringValue();
			list.add(temp);
		}
		return list;
	}

}
