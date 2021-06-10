package restaurant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class SaveFiles {
    
    public SaveFiles(){
        
    }
    
    private File getProjectFolder() throws URISyntaxException {
        String classFile = "";

        try{
            classFile = getClass().getName();
            classFile = classFile.substring(classFile.lastIndexOf(".")+1, classFile.length());
            classFile += ".class";
            URL url = getClass().getResource(classFile);
            assert url != null;
            return new File(url.toURI()).getParentFile();
        }catch (NullPointerException e){
            System.out.println(e);
            throw new URISyntaxException("Erro", classFile);
        }
    }
    
    public File makeAbsoluteFilename(String filename) throws IOException {
        try {
            File file = new File(filename);
            if(!file.isAbsolute())
                file = new File(getProjectFolder(), filename);
            
            return file;
        }
        catch(URISyntaxException e) {
            throw new IOException("Erro ao criar um ficheiro");
        }
    }
    
    public void saveFile(Management management, String filename) {
        try {
            File destination = makeAbsoluteFilename(filename);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(destination));
            oos.writeObject(management);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Management readSerializedFile(String filename) {
        Management management;

        try {
            File destination = makeAbsoluteFilename(filename);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(destination));

            //é nesta linha que está a ocorrer o erro
            management = (Management) ois.readObject();

            ois.close();
            return management;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
    
}
