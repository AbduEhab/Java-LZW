import java.util.HashMap;

public class Encoder {

    public static String encodeBinary(String encodingSequence) {

        // String temp = "";

        // for (int i = 0; i < encodingSequence.length(); i++) {
        // if (encodingSequence.charAt(i) != ' ')
        // temp += "" + encodingSequence.charAt(i);
        // }

        // encodingSequence = temp;

        HashMap<String, String> directory = new HashMap<String, String>();
        String buffer = "";
        String output = "";
        int storedValues = 256, basevalue = 256;

        int inputBits = encodingSequence.length();
        String extraBits = "0";

        boolean repeatedSequence = false;

        String[] byteString = new String[encodingSequence.length() / 8];

        for (int i = 0; i < byteString.length; i++) {
            byteString[i] = "";
        }

        for (int i = 0, j = 0; i < encodingSequence.length(); i++) {
            if (i != 0 && i % 8 == 0)
                j++;

            byteString[j] += encodingSequence.charAt(i) + "";
        }

        for (int i = 0; i < byteString.length; i++) {

            if (!(storedValues > basevalue * 2)) {

                if (!repeatedSequence)
                    buffer += byteString[i];

                if (i == 0) {
                    buffer += byteString[i + 1];

                    directory.put(buffer, Integer.toBinaryString(storedValues++));
                    // output += directory.get(Integer.toBinaryString(storedValues - 1));
                    output += extraBits + buffer.substring(0, buffer.length() - 8);
                    buffer = "";
                    continue;
                }

                if (i == (byteString.length) - 1) {
                    if (directory.containsKey(buffer)) {
                        output += directory.get(buffer);
                    } else {
                        if (buffer.length() != 8)
                            output += directory.get(buffer.substring(0, buffer.length() - 8)) + extraBits
                                    + buffer.substring(buffer.length() - 9, buffer.length() - 1);
                        else
                            output += extraBits + buffer;
                    }
                    break;
                }

                if (repeatedSequence) {

                    buffer += byteString[i + 1];

                    if (directory.containsKey(buffer)) {
                        continue;
                    } else {

                        directory.put(buffer, Integer.toBinaryString(storedValues++));
                        // output += directory.get(Integer.toBinaryString(storedValues - 1));
                        output += directory.get(buffer.substring(0, buffer.length() - 8));
                        buffer = "";
                        repeatedSequence = false;
                    }
                } else {

                    buffer += byteString[i + 1];

                    if (directory.containsKey(buffer)) {
                        repeatedSequence = true;
                        continue;
                    } else {
                        directory.put(buffer, Integer.toBinaryString(storedValues++));
                        // output += directory.get(Integer.toBinaryString(storedValues - 1));
                        output += extraBits + buffer.substring(0, buffer.length() - 8);
                        buffer = "";
                    }
                }

            } else {
                System.out.println("out of space");
                break;
            }
        }
        System.out.println("Memory State: " + storedValues);
        return output;
    }

    public static String encodeInt(int encodingSequence) {
        return encodeBinary(encodingSequence + "");
    }

}