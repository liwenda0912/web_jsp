package tom.jiafei;
import java.io.*;

public class ListFile {
   String extendsName=null;
   String [] allFileName=null; 
   String dir=null;
   public void setDir(String dir) {
      this.dir =dir;
   }
   public String getDir() {
      return dir;
   }
   public void setExtendsName(String s) {
      extendsName=s;
   }
   public String getExtendsName() {
      return extendsName;
   }
   public String [] getAllFileName() {
      if(dir!=null) {
        File mulu=new File(dir);
        FileExtendName help=new FileExtendName(extendsName);
        allFileName=mulu.list(help);
      }
      return allFileName;
   }  
}
