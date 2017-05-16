package reflect.noteBook;

/**
 * Created by lpf on 17/4/25.
 */
public class KeyboardByUSB implements USB{
    @Override
    public void open() {
        System.out.println("keyboard usb open");
    }

    @Override
    public void close() {
        System.out.println("keyboard usb close");
    }
}
