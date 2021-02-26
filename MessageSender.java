import java.util.Scanner;

/**
 * This class implements the sender.
 */
public class MessageSender
{
    // maximum transfer unit (frame length limit)
    private final int mtu;
    // Source of the messages.
    private final Scanner stdin;

    /**
     * Create and initialize a new MessageSender.
     *
     * @param mtu the maximum transfer unit (MTU) (the length of a frame must
     * not exceed the MTU)
     */
    public MessageSender(int mtu) {
        this.mtu = mtu;
        this.stdin = new Scanner(System.in);
    }

    /**
     * Read a line from standard input layer and break it into frames
     * that are output on standard output, one frame per line.
     * Report any errors on standard error.
     */
    public void sendMessage() {
        String message = stdin.nextLine();
        if(message != null) {
            // This part needs completing.
            System.out.println(message);
        }
        else {
            System.err.println("No message received.");
        }

    }
}

