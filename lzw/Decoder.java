
package lzw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author TANIM
 */
public class Decoder {
        private static String File_Input = null;
	private static double MAX_TABLE_SIZE;
	private static String LZWfilename;

	public static void Decode_String(String file_Input2, double bit_Length,String saveFile) throws IOException {
            MAX_TABLE_SIZE = Math.pow(2, bit_Length);
		
            List<Integer> get_compress_values = new ArrayList<Integer>();
            int table_Size = 255;
		
		
            BufferedReader br = null;
            InputStream inputStream  = new FileInputStream(file_Input2);
            Reader inputStreamReader = new InputStreamReader(inputStream, "UTF-16BE"); // The Charset UTF-16BE is used to read the 16-bit compressed file.

            br = new BufferedReader(inputStreamReader);
		  
		double value=0;
		
            while((value = br.read()) != -1)
            {
                    get_compress_values.add((int) value);
            }

            br.close();
         			
            Map<Integer, String> TABLE = new HashMap<Integer, String>();
            for (int i = 0; i < 255; i++)
                    TABLE.put(i, "" + (char) i);

            String Encode_values = "" + (char) (int) get_compress_values.remove(0);

            StringBuffer decoded_values = new StringBuffer(Encode_values);

            String get_value_from_table = null;
            for (int check_key : get_compress_values) {

                    if (TABLE.containsKey(check_key))
                            get_value_from_table = TABLE.get(check_key);
                    else if (check_key == table_Size)
                            get_value_from_table = Encode_values + Encode_values.charAt(0);

                    decoded_values.append(get_value_from_table);

                    if(table_Size < MAX_TABLE_SIZE )
                            TABLE.put(table_Size++, Encode_values + get_value_from_table.charAt(0));

                    Encode_values = get_value_from_table;
            }
	
            Create_decoded_file(decoded_values.toString(),saveFile);
	
	
	
	}

	private static void Create_decoded_file(String decoded_values,String saveFile) throws IOException {
		
            //LZWfilename = File_Input.substring(0,File_Input.indexOf(".")) + "_decoded.txt";

             FileWriter writer = new FileWriter(saveFile, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer);
             //System.out.println(decoded_values);


            try {

                    bufferedWriter.write(decoded_values);
                    bufferedWriter.newLine();

                    }
             catch (IOException e) {
                    e.printStackTrace(); 
            }
            bufferedWriter.flush();

            bufferedWriter.close();	
	}

	public static void main(String[] args) throws IOException {
		
            File_Input = "C:\\Users\\TANIM\\Desktop\\project.lzw";
            int Bit_Length = 16;

            //Decode_String(File_Input,Bit_Length);

}
}
