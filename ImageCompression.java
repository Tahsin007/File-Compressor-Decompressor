
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TANIM
 */
public class ImageCompression {
    public void compress(File f) throws IOException{
        fileopener ob = new fileopener();
        filesave ob1 = new filesave();
      //File input = new File("C:\\Users\\TANIM\\Desktop\\formal image.jpg");
      File input = new File(f.getAbsoluteFile().getAbsolutePath());
      String name =input.getName();
      int n = name.lastIndexOf(".");
      
      String n1=name.substring(0,n);
      BufferedImage image = ImageIO.read(input);

      File compressedImageFile = new File("C:\\Users\\TANIM\\Desktop\\Compress4.jpg");
      //File compressedImageFile = new File(input.getParent()+"\\"+n1+"compress.jpg");
      //File compressedImageFile = new File(ob1.s1);
      FileOutputStream os =new FileOutputStream(compressedImageFile);

      Iterator<ImageWriter>writers =  ImageIO.getImageWritersByFormatName("jpg");
      ImageWriter writer = (ImageWriter) writers.next();

      ImageOutputStream ios = ImageIO.createImageOutputStream(os);
      writer.setOutput(ios);

      ImageWriteParam param = writer.getDefaultWriteParam();
      
      param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
      param.setCompressionQuality(0.5f);
      writer.write(null, new IIOImage(image, null, null), param);
      
      os.close();
      ios.close();
      writer.dispose();
    }
}
