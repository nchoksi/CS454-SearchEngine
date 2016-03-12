package index;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.xml.sax.ContentHandler;

public class FindDF {
	
	
	public static void main(String[] args) {
		
		FindDF f=new FindDF();
		try {
			f.findDF("D:/data/index.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public LinkedHashMap<String, Integer> findDF(String IndexFilePath) throws IOException{
		
		//final InputStream in = new FileInputStream("D:/data/index.json");
		final InputStream in = new FileInputStream(IndexFilePath);
		LinkedHashMap<String, Integer> wordAndcount=new LinkedHashMap<String, Integer>();
		//List<String,String> wordAndDoc=new ArrayList<String,String>();
		Set<String> wordsSet=new HashSet<String>();

		//Map<String, Integer> words = new HashMap<String, Integer>();
		Integer tr;
		Set<String> DocCount=new  HashSet<String>();
		
		try {
			for (Iterator it = new ObjectMapper().readValues(
					new JsonFactory().createJsonParser(in), Map.class); it
					.hasNext();) {
				@SuppressWarnings("unchecked")
				LinkedHashMap<String, Object> keyValue = (LinkedHashMap<String,Object>) it.next();
			    String word = (String) keyValue.get("word");
				
			//	if(word.toLowerCase().equals(a.toLowerCase())){
		
				String local = (String) keyValue.get("link");
				DocCount.add(local);
				
				
				
				Integer frq = (Integer) keyValue.get("frequency");
				
			
				
				//Set<String> CountOfDOc
				
			//	float r2 = Float.parseFloat(frq)/100;
					
		
					String t = (String) keyValue.get("title");
					
					
				
				int count=1;
				
				
				if(wordAndcount.keySet().contains(word))
				{
					wordAndcount.put(word,wordAndcount.get(word)+1);
				}
				else{
					wordAndcount.put(word, count);
				}
				
				
				
					
					
					//wordsSet.add(a);
					
					
					/*if(t!=null)
					{
					//System.out.println(t);
					String[] tarray = t.split("\\s+");
					for (int i=0; i<tarray.length; ++i)
				    {
				        tarray[i] = tarray[i].toLowerCase();
				    }
					List<String> tList = Arrays.asList(tarray);
					
					
						
						    tr = Collections.frequency(tList, a.toLowerCase());
						   // System.out.println(tr);
						    Integer v = tr*100;
						     Integer fr = frq + v;
						   Float rnk = (float) fr/100;
						     
						     */
						     
							
							
							
						
						  /*   JSONObject obj = new JSONObject();
						     obj.put("Link", local);
						     obj.put("Rank", rnk);
						     obj.put("word", a);
						     obj.put("url", keyValue.get("url"));
						     */
						
				
				
				
			
			
			
			
		    
		        
		      /*  File f4 = new File("D:/data/rank.json");
		          BufferedWriter file2 = new BufferedWriter(new FileWriter(f4,true)); 
		            try {
		            	
		            	ObjectMapper mapper = new ObjectMapper();
		               file2.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
		            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
		                file2.newLine();
		                file2.newLine();
		                file2.newLine();
		                file2.newLine();
		               
		     
		            } catch (IOException e1) {
		                e1.printStackTrace();
		     
		            } finally {
		                file2.flush();
		                file2.close();
		            }*/
		    
			
			
			
	
	
			}
			
			
			wordAndcount.put("TotalDocCount", DocCount.size());
			
			for(Map.Entry m:wordAndcount.entrySet()){  
				   System.out.println("["+m.getKey()+" : "+m.getValue()+"]");  
				  }  
			
			
			
		}
	finally {
		in.close();
	}
		return wordAndcount;
	

		
	}
}
