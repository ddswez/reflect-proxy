package reflect.noteBook;

/**
 * Created by lpf on 17/4/25.
 */
public class MouseByUSB implements USB{
    @Override
    public void open() {
        System.out.println("mouse usb open");
    }

    @Override
    public void close() {
        System.out.println("mouse usb close");
    }
}
