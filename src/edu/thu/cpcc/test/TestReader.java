package edu.thu.cpcc.test;

import java.util.ArrayList;
import java.util.Hashtable;

import edu.thu.cpcc.util.DataIO;
import edu.thu.cpcc.util.SreachDict;

public class TestReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String[]> dict = new ArrayList<String[]>();
		ArrayList<String[]> patient = new ArrayList<String[]>();
		ArrayList<String[]> careFlow = new ArrayList<String[]>();
		Hashtable<String, String> row = new Hashtable<String, String>();
		String writePath = "/Users/MorrisMo/Workspace/Data/out.txt";
		
		
		DataIO dr = new DataIO();
		DataIO dw = new DataIO();
		dict = dr.Read("/Users/MorrisMo/Workspace/Data/dict.csv");
		patient = dr.Read("/Users/MorrisMo/Workspace/Data/patientID.csv");
		careFlow = dr.Read("/Users/MorrisMo/Workspace/Data/careFlow.csv");
		
		row = Quatized(dict, patient, careFlow);
				
		dw.write(writePath, row);
	}
	
	public static Hashtable<String, String> Quatized(ArrayList<String[]> dict, ArrayList<String[]> patient, ArrayList<String[]> careFlow){
		SreachDict sd = new SreachDict();
		Hashtable<String, String> result = new Hashtable<String, String>();
		
		for(int id = 1; id < patient.size(); id++){
			String target = patient.get(id)[0];
			String cflow = null;
			String pretime = null;
			for(int careRow = 0; careRow < careFlow.size(); careRow++){
				String cid = careFlow.get(careRow)[0];
				String c = careFlow.get(careRow)[1];
				String ctime = careFlow.get(careRow)[2];
				if(cid.equals(target)){
					cflow += " ";
					cflow += sd.SearchDict(c, dict);
					if(!ctime.equals(pretime)){
						cflow += " -1";				
					}
					pretime = ctime;
				}				
			}
			
			cflow += " -1 -2";
			result.put(target, cflow);
		}		
		return result;		
	}
}
