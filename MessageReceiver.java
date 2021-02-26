import java.util.Scanner;

/**
 * This class implements the receiver.
 * 
 */
public class MessageReceiver
{
    // maximum transfer unit (frame length limit)
    private final int mtu;
    // Source of the frames.
    private final Scanner stdin;

    /**
     * Create and initialize new MessageReceiver.
     *
     * @param mtu the maximum transfer unit (MTU)
     */
    public MessageReceiver(int mtu)
    {
        this.mtu = mtu;
        this.stdin = new Scanner(System.in);
    }

    /**
     * Receive a single message on stdin, one frame per line
     * and output the recreated message on stdout.
     * Report any errors on stderr.
     */
    public void receiveMessage()
    {
        boolean endOfMessage = false;
        String message = "";
        do {
            String frame = stdin.nextLine();
            endOfMessage = true;
        } while (!endOfMessage);

        System.out.println(message);

    }
}

