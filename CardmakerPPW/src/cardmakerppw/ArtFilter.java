/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmakerppw;
import javax.swing.filechooser.FileFilter;
import java.io.File;
/**
 *
 * @author croni
 */
public class ArtFilter extends FileFilter {
    @Override
    public boolean accept(File f){
        if(f.isDirectory()){
            return true;
        }
        
        String ext = getExtension(f);
        if(ext != null){
            if(ext.equals("png") || ext.equals("jpg") || ext.equals("jpeg")){
                return true;
            }
        }
        
        return false;
    }
    
    private String getExtension(File f){
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        
        if(i > 0 && i < s.length() - 1){
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

    @Override
    public String getDescription() {
        return "Only Images for Art";
    }
}
