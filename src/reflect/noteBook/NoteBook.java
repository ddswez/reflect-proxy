package reflect.noteBook;

/**
 * Created by lpf on 17/4/25.
 */
public class NoteBook {

    public void run() {
        System.out.println("note book run!");
    }

    public void useUSB(USB usb) {
        if (usb != null) {
            usb.open();
            usb.close();
        }
    }
}
