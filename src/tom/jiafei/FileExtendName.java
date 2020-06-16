package tom.jiafei;

import java.io.File;
import java.io.FilenameFilter;

class FileExtendName implements FilenameFilter {
   String str=null;
   FileExtendName (String s) {
      str="."+s;
   }
   public  boolean accept(File dir, String name) {
      return name.endsWith(str);
   }
}
