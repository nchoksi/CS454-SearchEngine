package index;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

public class Rank {

    int i = 1;

    @SuppressWarnings("unchecked")
    public void rank( String rank ) throws IOException
    {

        final InputStream in = new FileInputStream( "D:/data/index.json" );

        // Map<String, Integer> words = new HashMap<String, Integer>();
        Integer tr;

        try
        {
            for( @SuppressWarnings("rawtypes")
            Iterator it = new ObjectMapper().readValues(
                new JsonFactory().createJsonParser( in ), Map.class ); it.hasNext(); )
            {
                LinkedHashMap<String, Object> keyValue = (LinkedHashMap<String, Object>) it.next();
                String a = (String) keyValue.get( "word" );

                // if(word.toLowerCase().equals(a.toLowerCase())){

                String local = (String) keyValue.get( "link" );
                Integer frq = (Integer) keyValue.get( "frequency" );

                // float r2 = Float.parseFloat(frq)/100;

                String t = (String) keyValue.get( "title" );
                if( t != null )
                {
                    // System.out.println(t);
                    String[] tarray = t.split( "\\s+" );
                    for( int i = 0; i < tarray.length; ++i )
                    {
                        tarray[i] = tarray[i].toLowerCase();
                    }
                    List<String> tList = Arrays.asList( tarray );

                    tr = Collections.frequency( tList, a.toLowerCase() );
                    // System.out.println(tr);
                    Integer v = tr * 100;
                    Integer fr = frq + v;
                    Float rnk = (float) fr / 100;

                    JSONObject obj = new JSONObject();
                    obj.put( "Link", local );
                    obj.put( "Rank", rnk );
                    obj.put( "word", a );
                    obj.put( "url", keyValue.get( "url" ) );

                    File f4 = new File( "D:/data/rank.json" );
                    BufferedWriter file2 = new BufferedWriter( new FileWriter(
                        f4, true ) );
                    try
                    {

                        ObjectMapper mapper = new ObjectMapper();
                        file2.write( mapper.writerWithDefaultPrettyPrinter()
                            .writeValueAsString( obj ) );
                        System.out.println( mapper.writerWithDefaultPrettyPrinter()
                            .writeValueAsString( obj ) );
                        file2.newLine();
                        file2.newLine();
                        file2.newLine();
                        file2.newLine();

                    }
                    catch( IOException e1 )
                    {
                        e1.printStackTrace();

                    }
                    finally
                    {
                        file2.flush();
                        file2.close();
                    }

                }
            }
        }
        finally
        {
            in.close();
        }

    }

}
