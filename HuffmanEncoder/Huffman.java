
package HuffmanEncoder;

/**
 *
 * @author TANIM
 */
public class Huffman {
    public char character;
    public long frequency;
    public Huffman rChild, lChild;
    public String code;
    
    Huffman(){
        ;
    }
    Huffman(char c,int f)
    {
        character=c;
        frequency=f;
        rChild=lChild=null;
    }
    
    public long getFrequency() {
        return frequency;
    }

    public void setFrequency(long frequency) {
        this.frequency = frequency;
    }
    
        public Huffman getrChild() {
        return rChild;
    }

    public void setrChild(Huffman rChild) {
        this.rChild = rChild;
    }

    public Huffman getlChild() {
        return lChild;
    }

    public void setlChild(Huffman lChild) {
        this.lChild = lChild;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public void setCharacter(char character) {
        this.character = character;
    }
    
    public char getCharacter() {
        return character;
    }

}
