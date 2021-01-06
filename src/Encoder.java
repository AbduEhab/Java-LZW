import java.util.HashMap;

public class Encoder {

    public static String encode(String encodingSequence) {

        HashMap directory = new HashMap<String, String>();
        String buffer = "";
        int currIndex = 0;
        String output = "";
        int storedValues = 0;

        boolean repeatedSequence = false;

        for (int i = currIndex; i < encodingSequence.length(); i++) {
            buffer += encodingSequence.charAt(i);

            if (i == 0) {
                directory.put(buffer, Integer.toBinaryString(storedValues++));
                // output += directory.get(Integer.toBinaryString(storedValues - 1));
                output += buffer;
                buffer = "";
                continue;
            }

            if (i == encodingSequence.length() - 1) {
                if (directory.containsKey(buffer)) {
                    output += directory.get(buffer);
                } else {
                    output += directory.get(buffer.substring(0, buffer.length() - 1))
                            + (buffer.charAt(buffer.length() - 1) + "");
                }
                break;
            }

            if (repeatedSequence) {
                if (directory.containsKey(buffer)) {
                    continue;
                } else {
                    directory.put(buffer, Integer.toBinaryString(storedValues++));
                    // output += directory.get(Integer.toBinaryString(storedValues - 1));
                    output += directory.get(buffer.substring(0, buffer.length() - 1))
                            + (buffer.charAt(buffer.length() - 1) + "");
                    buffer = "";
                    repeatedSequence = false;
                }
            } else {
                if (directory.containsKey(buffer)) {
                    repeatedSequence = true;
                    continue;
                } else {
                    directory.put(buffer, Integer.toBinaryString(storedValues++));
                    // output += directory.get(Integer.toBinaryString(storedValues - 1));
                    output += buffer;
                    buffer = "";
                }
            }

        }
        return output;
    }

}
