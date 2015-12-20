package edu.thu.cpcc.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import com.csvreader.CsvReader;

public class DataIO {
	
	public ArrayList<String[]> Read(String FilePath) {
		ArrayList<String[]> result = new ArrayList<String[]>();
		try{ 			
			CsvReader Reader = new CsvReader(FilePath,',',Charset.forName("UTF-8"));	
			
			while(Reader.readRecord()){
				result.add(Reader.getValues());
			}
			Reader.close();
			
		}catch(Exception e){  
			e.printStackTrace();  
        }
		return result;
	}
	
	public void write(String writePath, Hashtable<String, String> text) {
	try {
		File f = new File(writePath);
		if (!f.exists()) {
			f.createNewFile();
			}
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");
		BufferedWriter writer = new BufferedWriter(write);
		for(Iterator<String> itr = text.keySet().iterator(); itr.hasNext();){
			String key = (String) itr.next();
			String value = (String) text.get(key);
			int i = value.indexOf("-1");
			value = value.substring(5, i) + value.substring(i+4);
			writer.write(value);
			writer.newLine();
		}		
		writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
}
