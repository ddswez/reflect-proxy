package reflect.noteBook;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

/**
 * Created by lpf on 17/4/25.
 */
public class NoteBookMain {

    public static void main(String[] args) throws Exception{
//        NoteBook book = new NoteBook();
//        book.run();
//        book.useUSB(new MouseByUSB());
        useConfigFile();
     /*   note book run!
           mouse usb open
           mouse usb close*/
        /*
         note book run!
         keyboard usb open
         keyboard usb close */
    }

    private static void useConfigFile() throws Exception{
        NoteBook book = new NoteBook();
        book.run();
        File configFile = new File("usb.properties");
        if (!configFile.exists()) {
            configFile.createNewFile();
        }

        FileReader fr = new FileReader(configFile);

        Properties prop = new Properties();
        prop.load(fr);

        for (int i = 1; i <= prop.size(); i++) {
            String className = prop.getProperty("usb" + i);
            Class clazz = Class.forName(className);
            USB usb = (USB)clazz.newInstance();
            book.useUSB(usb);
        }

        fr.close();
    }
}
