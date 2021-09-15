/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmakerppw;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import javax.imageio.ImageIO;
import java.awt.Desktop;
/**
 *
 * @author croni
 */
public class FileManagement {
    
    public static class FileManager{
        private static JFileChooser fc;
        private static boolean fileLock  = false;
        
        public static boolean getFileLock(){
            return fileLock;
        }
        
        public static void SaveCard(Card c){
            fileLock = true;
            if(fc == null){
                fc = new JFileChooser();
                fc.setCurrentDirectory(new File(System.getProperty("user.home")));
            }
            fc.setDialogTitle("Save a Card");
            fc.setFileFilter(new CardFilter());
            fc.setAcceptAllFileFilterUsed(false);

            int selection = fc.showSaveDialog(fc);

            if(selection == JFileChooser.APPROVE_OPTION){
                if(DeserializeCard() != null){
                    //File Exists Here, you wanna overwrite?
                    
                    if(saveCheck() == 0){
                        //Don't Overwrite
                        JOptionPane.showMessageDialog(null, "Save Cancelled");
                        fileLock = false;
                        return;
                    } 
                }
                
                SerializeCard(c);
                JOptionPane.showMessageDialog(null, "Card Saved!!");
            } else if( selection == JFileChooser.CANCEL_OPTION){
                JOptionPane.showMessageDialog(null, "Save Cancelled");
            }
            
            fileLock = false;
        }

        public static Card LoadCard(){
            fileLock = true;
            if(fc == null){
                fc = new JFileChooser();
                fc.setCurrentDirectory(new File(System.getProperty("user.home")));
            }
            
            fc.setDialogTitle("Load a Card");
            fc.setFileFilter(new CardFilter());
            fc.setAcceptAllFileFilterUsed(false);

            int selection = fc.showOpenDialog(fc);

            if(selection == JFileChooser.APPROVE_OPTION){
                fileLock = false;
                return DeserializeCard();
            } 
            fileLock = false;

            return null;
        }

        public static ImageIcon LoadImage(){
            fileLock = true;
            if(fc == null){
                fc = new JFileChooser();
                fc.setCurrentDirectory(new File(System.getProperty("user.home")));
            }
            
            fc.setDialogTitle("Load Card Art");
            fc.setFileFilter(new ArtFilter());
            fc.setAcceptAllFileFilterUsed(false);

            int result = fc.showOpenDialog(fc);

            File file = fc.getSelectedFile();

            ImageIcon img;

            if(file != null && result == JFileChooser.APPROVE_OPTION){
                try{
                    if(ImageIO.read(new File(file.getAbsolutePath())) != null){
                    
                        img = new ImageIcon(fc.getSelectedFile().getAbsolutePath());
                        System.out.println(fc.getSelectedFile().getAbsolutePath());
                        //img = ImageIO.read(new File(file.getAbsolutePath()));
                        //Desktop.getDesktop().open(file);
                        fileLock = false;
                        return img;
                    }
                }catch (IOException e){
                     JOptionPane.showMessageDialog(null, "Error getting image");
                }
            }
            JOptionPane.showMessageDialog(null, "Image not found");
            fileLock = false;
            return null;
        }
        
        public static void ExportCardImage(BufferedImage bi){
            fileLock = true;
            if(fc == null){
                fc = new JFileChooser();
                fc.setCurrentDirectory(new File(System.getProperty("user.home")));
            }
            fc.setDialogTitle("Save Card Image");
            fc.setFileFilter(new ArtFilter());
            fc.setAcceptAllFileFilterUsed(false);

            int selection = fc.showSaveDialog(fc);
            
            String path = fc.getSelectedFile().getAbsolutePath();
            
            if(!path.contains( ".png")){
                
                if(path.contains(".")){
                    int dot = path.lastIndexOf('.');
                    path = path.substring(0, dot);
                }
                
                if(path.charAt(path.length() -1) != '.'){
                    path = path + ".";
                }
                
                path = path + "png";
            }
            
            if(selection == JFileChooser.APPROVE_OPTION){
                try{
                    if(ImageIO.read(new File(path)) != null){
                        //Popup confirming overwrite
                        if(exportCheck() == 0){
                            JOptionPane.showMessageDialog(null, "Export Cancelled");
                            fileLock = false;
                            return;
                        }
                    }
                }catch (IOException e){
                     
                }

                try {
                    // retrieve image
                    File outputfile = new File(path);
                    ImageIO.write(bi, "png", outputfile);
                    
                } catch (IOException e) {

                }
                fileLock = false;
            }
        }

        private static int saveCheck(){
            
            String[] options = new String[]{"No", "Yes"};

            int choice = JOptionPane.showOptionDialog( null,
                    "There is already a file with the name " + fc.getSelectedFile().getName()
                    + "\nOverwrite the card?",
                    "Card Creator Overwrite", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null,
                    options, options[0]);

            return choice;
        }
        
        private static int exportCheck(){
            String[] options = new String[]{"No", "Yes"};

            int choice = JOptionPane.showOptionDialog( null,
                    "There is already a file with the name " + fc.getSelectedFile().getName()
                    + "\nOverwrite the image?",
                    "Card Creator Overwrite Export?", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null,
                    options, options[0]);

            return choice;
        }

        private static void SerializeCard(Card c){
            if(fc.getSelectedFile() == null){
                JOptionPane.showMessageDialog(null, "Error saving card!\n Card Not Saved");
                return;
            }
            
            //Fix Writepath
            String path = FixPath();
            
            try{
                FileOutputStream stream = new FileOutputStream(path);
                ObjectOutputStream out = new ObjectOutputStream(stream);

                out.writeObject(c);

                out.close();
                stream.close();

                System.out.println( "FileManager SerializeCard: " + c.getTitle() + " has been serialized");
            } catch(IOException e){
                System.out.println("Error in SerializeCard");
                //JOptionPane.showMessageDialog(null, "Error saving card!");
            }
        }

        private static Card DeserializeCard(){
            try{
                Card card;
                try (FileInputStream stream = new FileInputStream(fc.getSelectedFile().getAbsolutePath())) {
                    ObjectInputStream in = new ObjectInputStream(stream);
                    card = (Card)in.readObject();
                    in.close();
                }

                return card;

            } catch(IOException e){
                JOptionPane.showMessageDialog(null, "Error getting card\n" + e.toString());
                return null;
            } catch (ClassNotFoundException e){
                //JOptionPane.showMessageDialog(null, "Error Card not in file\n" + e.toString());
                return null;
            }
        }
        
        private static String FixPath(){
            String path = fc.getSelectedFile().getAbsolutePath();
            
            //fixExtension
            if(!path.contains( "." + Card.EXTENSION)){
                
                if(path.contains(".")){
                    int dot = path.lastIndexOf('.');
                    path = path.substring(0, dot);
                }
                
                if(path.charAt(path.length() -1) != '.'){
                    path = path + ".";
                }
                
                path = path + Card.EXTENSION;
            }
            
            return path;
            
        }
    }
    
    
}
