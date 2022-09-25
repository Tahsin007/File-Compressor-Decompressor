
package lzw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author TAHSIN
 */

public class Encoder {
        public static String File_Input = null;
	public static double MAX_TABLE_SIZE;
	public static String LZWfilename;
	public void Encode_string(String input_string, double Bit_Length,String File_Input,String File_Output) throws IOException{
	
            MAX_TABLE_SIZE = Math.pow(2, Bit_Length);	
            double table_Size =  255;

            Map<String, Integer> TABLE = new HashMap<String, Integer>();

            for (int i = 0; i < 255 ; i++)
                    TABLE.put("" + (char) i, i);

            String initString = "";

            List<Integer> encoded_values = new ArrayList<Integer>();

            for (char symbol : input_string.toCharArray()) {
                    String Str_Symbol = initString + symbol;
                    if (TABLE.containsKey(Str_Symbol))
                        initString = Str_Symbol;
                    else {
                        encoded_values.add(TABLE.get(initString));

                        if(table_Size < MAX_TABLE_SIZE)
                                TABLE.put(Str_Symbol, (int) table_Size++);
                        initString = "" + symbol;
                    }
            }

            if (!initString.equals(""))
                    encoded_values.add(TABLE.get(initString));
            for(int i=0;i<encoded_values.size();i++){
                //System.out.println(encoded_values.get(i));
            }

            CreateLZWfile(encoded_values,File_Input,File_Output); 
		
	}

	public void CreateLZWfile(List<Integer> encoded_values,String File_Input,String File_Output) throws UnsupportedEncodingException, IOException{
		
		BufferedWriter out = null;
		
		//LZWfilename = File_Input.substring(0,File_Input.indexOf(".")) + ".lzw";
		
		try {
	            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(File_Output),"UTF_16BE")); //The Charset UTF-16BE is used to write as 16-bit compressed file
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Iterator<Integer> Itr = encoded_values.iterator();
			while (Itr.hasNext()) {
				out.write(Itr.next());
			}
		} catch (IOException e) {
			e.printStackTrace(); 
		}
		
		out.flush();
		out.close();	
	}


	public static void main(String[] args) throws IOException {
				
//		File_Input = "C:\\Users\\TANIM\\Desktop\\project.txt";
//                int Bit_Length = 16;
//		StringBuffer input_string1 = new StringBuffer();
//		try (BufferedReader br = Files.newBufferedReader(Paths.get(File_Input), StandardCharsets.UTF_8)) {
//		    for (String line = null; (line = br.readLine()) != null;) {		        
//		    	input_string1 = input_string1.append(line);
//                        input_string1.append(System.lineSeparator());
//		    }
////                    int line = 0;
////                    while((line = br.read()) != -1){
////                        input_string1 = input_string1.append((char)line);
////                    }
//		}	
//		//Encode_string(input_string1.toString(),Bit_Length);
//			
//	}
    
}
}
