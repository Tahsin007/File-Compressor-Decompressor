
package HuffmanEncoder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Tahsin
 */
public class HuffmanDecoder {

    public static HashMap<Character,String> encoder;
    public static HashMap<String,Character> decoder;
    public static int frequencyHolder[];
    public static int totalFrequency = 0;//No. of characters in original file
    public static int totalEncoded = 0;//No. of characters in encoded file
    public static int totalDecoded = 0;//No. of characters in decoded file
    private final String sourcePath = "C:\\Users\\TANIM\\Desktop\\seo 2.txt";
    private final String destinationPath = "C:\\Users\\TANIM\\Desktop\\3rd Semister\\Networking";
    
    public static void main(String[] args) {
        HuffmanDecoder dc = new HuffmanDecoder();  
        //dc.countFrequency();
        Huffman root = dc.encode();
        //dc.decode(root);
    }
    
    /*
    Count the frequency of various ASCII characters
    */
    public void countFrequency(String sourcePath)
    {
        frequencyHolder = new int[256];        
        try(FileReader fr = new FileReader(sourcePath))
        {
            int c;           
            while((c = fr.read())!= -1)
                if(c<256)
                {
                    frequencyHolder[c]++;
                    totalFrequency++;
                }            
        }
        catch(IOException e)
        {
            System.out.println("IO error :"+e);
        }
    }
    /*
        Sets up the min-Heap
    */
    public Huffman encode()
    {
        int n = frequencyHolder.length;
        
        PriorityQueue <Huffman> minheap= new PriorityQueue<>(n,FREQUENCY_COMPARATOR);
        
        char c;int a;
        for(int i=0;i<n;i++)
        {
            if(frequencyHolder[i]!=0)
                minheap.add(new Huffman((char)i,frequencyHolder[i]));
        }
        
        Huffman z=null;
        while(minheap.size()>1)
        {
            //System.out.println(hnode.poll().getFrequency());
            Huffman x=minheap.peek();
            minheap.poll();
            Huffman y=minheap.peek();
            minheap.poll();
            x.setCode("0");
            y.setCode("1");
            z=new Huffman();
            //z=z.create(x,y);
            z.setCharacter('\u09B8');//Unicode of my initial in Bengali
            z.setFrequency(x.getFrequency()+y.getFrequency());
            z.setlChild(x);
            z.setrChild(y);
            //System.out.println(z.getFrequency());
            minheap.add(z);            
        }
        encoder = new HashMap<>();
        Huffman root=z;
        traverse(root,"");       
        System.out.println("");      
        for(int i=0;i<n;i++)
           if(frequencyHolder[i]!=0)
            System.out.println((char)i+"\t"+frequencyHolder[i]+"\t"+encoder.get((char)i));
        return root;
    }
    /*
     Comparator to check the char with lowest frequency
    */        
    public static final Comparator<Huffman> FREQUENCY_COMPARATOR = (Huffman o1, Huffman o2) -> (int) (o1.getFrequency()-o2.getFrequency());
    /*
    recursively set up the codeword for each character
    */
    public void traverse(Huffman root,String s)throws NullPointerException
    {   
        if(root.getCode()!=null)
            s+=root.getCode();
        if(root.getlChild()==null && root.getrChild()==null && root.getCharacter()!='\u09B8')
            {
                //System.out.println(root.getCharacter()+":"+s);
                encoder.put(root.getCharacter(), s);
                return;
            }
        
         traverse(root.getlChild(), s);
         traverse(root.getrChild(), s);
    }
    /*
    Decodes the encoded binary message
    */
    public void decode(Huffman root,String sourcePath,String saveFile)
    {
        Huffman hRoot = root;
        StringBuffer contents = new StringBuffer();
        //FileReader fr = new FileReader(destinationPath+"\\encoded.txt");
        try(FileReader fr = new FileReader(sourcePath);)
        {
            int c;
            while((c = fr.read())!= -1)
                if(c<256)
                {
                    contents.append((char)c);
                }   
        }
        catch(IOException e)
        {
            System.out.println("IO error :"+e);
        }
        StringBuffer output = new StringBuffer();
        for(int i=0;i<contents.length();i++)
        {
            char ch = contents.charAt(i);
            if(ch=='0')
                root =  root.getlChild();
            else if(ch=='1')
                root = root.getrChild();
            if(root.getrChild()==null && root.getlChild()==null)
            {
                //System.out.print(root.getCharacter());
                output.append(root.getCharacter());
                root=hRoot;
            }
        }
        totalDecoded = output.length();
        try(FileWriter fw = new FileWriter(saveFile);)
        {
            fw.write(output.toString());
        } catch (IOException ex) {
            Logger.getLogger(HuffmanDecoder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
