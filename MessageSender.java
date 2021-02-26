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

    // Source of the messages.
    private final Scanner stdin;

    /**
     * Create and initialize a new MessageSender.
     *
     * @param mtu the maximum transfer unit (MTU) (the length of a frame must
     * not exceed the MTU)
     */
    public MessageSender(int mtu) {
        // TODO: Check for too short MTU!
        this.mtu = Math.min(mtu, 99 + META_LEN);
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
            // If long, split into segments
            segments = getSegments(message);
            for (int i = 0 ; i < segments.size() ; i++) {
                // Determine frame type (D for data, F for final)
                String frameType = i+1 == segments.size() ? "F" : "D";
                // Add basic frame info
                String frame = "[" + frameType + "~";
                // Add frame length
                if (segments.get(i).length() >= 10) {
                    frame += segments.get(i).length();
                } else {
                    frame += "0" + segments.get(i).length();
                }
                // Add message
                frame += "~" + segments.get(i) + "~";
                // Generate checksum
                frame += generateChecksum(frame);
                // Add final frame delimiter
                frame += "]";
                if (frame.length() > mtu) {
                    System.err.println("Frame too long.");
                } else {
                    System.out.println(frame);
                }
            }
        }
        else {
            System.err.println("No message received.");
        }

    }

    private ArrayList<String> getSegments(String message) {
        final int messageLen = mtu - META_LEN;
        ArrayList<String> segments = new ArrayList<>();
        // Do a check to see if it's short enough for one frame
        if (message.length() < messageLen) {
            segments.add(message);
            return segments;
        }
        while(message.length() > messageLen) {
            String next = message.substring(0, messageLen);
            String remain = message.substring(messageLen);
            segments.add(next);
            message = remain;
        }
        segments.add(message);
        return segments;
    }

    private String generateChecksum(String frameSection) {
        // Excludes starting frame delimiter
        frameSection = frameSection.substring(1);
        int sum = 0;
        for(char c : frameSection.toCharArray()) {
            sum += c;
        }
        String hex = Integer.toHexString(sum);
        if (hex.length() < 2) {
            return "0" + hex;
        } else {
            return hex.substring(hex.length()-2);
        }
    }
}

