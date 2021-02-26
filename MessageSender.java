import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class implements the sender.
 */
public class MessageSender
{
    // maximum transfer unit (frame length limit)
    private final int mtu;

    // Minimum frame length
    private final int META_LEN = "[F~00~~00]".length();

    // Maximum MTU
    private final int MAX_MTU = 109;

    // Source of the messages.
    private final Scanner stdin;

    /**
     * Create and initialize a new MessageSender.
     *
     * @param mtu the maximum transfer unit (MTU) (the length of a frame must
     * not exceed the MTU)
     */
    public MessageSender(int mtu) {
        this.mtu = Math.min(mtu, MAX_MTU);
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
            ArrayList<String> segments;
            if (message.length() + META_LEN > mtu) {
                // TODO: If long, split into segments
                segments = getSegments(message);
            } else {
                // Otherwise just need the one segment
                segments = new ArrayList<>();
                segments.add(message);
            }
            for (int i = 0 ; i < segments.size() ; i++) {
                // Determine frame type (D for data, F for final)
                String frameType = i+1 == segments.size() ? "F" : "D";
                // Add basic frame info
                String frame = "[" + frameType + "~";
                // Add frame length
                if (segments.get(i).length() > 10) {
                    frame += segments.get(i).length();
                } else {
                    frame += "0" + segments.get(i).length();
                }
                // Add message
                frame += "~" + segments.get(i) + "~";
                // TODO: Generate checksum
                frame += "00";
                // Add final frame delimiter
                frame += "]";
                System.out.println(frame);
            }
        }
        else {
            System.err.println("No message received.");
        }

    }

    private ArrayList<String> getSegments(String message) {
        ArrayList<String> segments = new ArrayList<>();

        return segments;
    }
}

