import java.util.HashMap;

public class Decoder {

    public static String decodeBinary(String decodingSequence) { // O(n^2)

        // basic values init

        HashMap<String, String> directory = new HashMap<String, String>(); // main hashmap that will contain the keys
                                                                           // and values to decode the sequence
        String currBuffer = ""; // buffer to store current byte
        String nextBuffer = ""; // buffer to store next value

        String output = ""; // empty string to store output values
        int storedValues = 256; // memory to allocate for the directory's values
                                // (100000000 -> 111111111)

        String extraBits = "0"; // amout of extra bits added during encoding

        String[] byteString = new String[(decodingSequence.length() * 2) / 9]; // String array that will contain all the
                                                                               // bytes to be decoded

        // init byteString array with empty strings
        for (int i = 0; i < byteString.length; i++) {
            byteString[i] = "";
        }

        // add all the bytes to each index of the byteString array
        for (int i = 0, j = 0; i < decodingSequence.length(); i++) {
            if (i != 0 && i % 9 == 0)
                j++;

            byteString[j] += decodingSequence.charAt(i) + "";
        }

        // main decoding loop
        for (int i = 0; i < decodingSequence.length() / 9; i++) {

            /// get next byte and check if it was already in the directory, if it was found
            // in the directory set the value with the value optained from the
            // directory
            if (byteString[i].charAt(0) == '1')
                currBuffer += directory.get(byteString[i]);
            else
                currBuffer += byteString[i];

            // if it is the firstbyte in the array, add the nextbyte to the firstbyte and
            // place them in the directory with the key 'storedValues' (in bites) while
            // removing the extra bit(s) from the secondbyte and inc the storedValues.
            if (i == 0) {
                nextBuffer += byteString[i + 1];

                directory.put(Integer.toBinaryString(storedValues++),
                        currBuffer + nextBuffer.substring(extraBits.length()));

                // add the first byte only to the output
                output += currBuffer.substring(extraBits.length());

                // clear both buffers
                currBuffer = "";
                nextBuffer = "";
                continue;
            }

            // if its the last byte in the array, add it to the output after removing the
            // extra bit(s) and break the loop
            if (i == (decodingSequence.length() / 9) - 1) {

                output += currBuffer.substring(extraBits.length());

                break;
            }

            // get next byte and check if it was already in the directory, if it was found
            // in the directory set the value with the value optained from the
            // directory
            if (byteString[i + 1].charAt(0) == '1')
                nextBuffer += directory.get(byteString[i + 1]);
            else
                nextBuffer += byteString[i + 1];

            // add the nextbyte fo the currbyte and
            // place them in the directory with the key 'storedValues' while removing the
            // extra bit(s) from the nextbyte and inc the storedValues.
            directory.put(Integer.toBinaryString(storedValues++),
                    currBuffer + nextBuffer.substring(extraBits.length()));

            // add the currByte to the output after removing the
            // extra bit(s)
            output += currBuffer.substring(extraBits.length());
            currBuffer = "";
            nextBuffer = "";
        }

        return output;
    }

    // overloaded method to accept an int sequence representing bytes
    public static String decodeInt(int decodingSequence) {
        return decodeBinary(decodingSequence + "");
    }
}