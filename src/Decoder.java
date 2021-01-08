import java.util.HashMap;

public class Decoder {

    public static String decode(String decodingSequence) {

        HashMap<String, String> directory = new HashMap<String, String>();
        String buffer = "";
        int currIndex = 0;
        String output = "";
        int storedValues = 0;

        boolean repeatedSequence = false;

        for (int i = currIndex; i < decodingSequence.length(); i++) {
            buffer += decodingSequence.charAt(i);

            if (i == 0) {
                directory.put(Integer.toBinaryString(storedValues++), buffer);
                // output += directory.get(Integer.toBinaryString(storedValues - 1));
                output += buffer;
                buffer = "";
                continue;
            }

            if (i == decodingSequence.length() - 1) {
                if (directory.containsKey(buffer)) {
                    output += directory.get(buffer);
                } else {
                    String decodedSegment = directory.get(buffer.substring(0, buffer.length() - 1)) + "";
                    output += decodedSegment + buffer.charAt(buffer.length() - 1);
                }
                break;
            }

            if (repeatedSequence) {
                if (directory.containsKey(buffer)) {
                    continue;
                } else {
                    // output += directory.get(Integer.toBinaryString(storedValues - 1));
                    String decodedSegment = directory.get(buffer.substring(0, buffer.length() - 1)) + ""
                            + buffer.charAt(buffer.length() - 1);
                    directory.put(Integer.toBinaryString(storedValues++), decodedSegment);
                    output += decodedSegment;
                    buffer = "";
                    repeatedSequence = false;
                }
            } else {
                if (directory.containsKey(buffer)) {
                    repeatedSequence = true;
                    continue;
                } else {
                    directory.put(Integer.toBinaryString(storedValues++), buffer);
                    // output += directory.get(Integer.toBinaryString(storedValues - 1));
                    output += buffer;
                    buffer = "";
                }
            }

        }
        return output;
    }

    public static String decode(int decodingSequence) {
        return decode(decodingSequence + "");
    }
}