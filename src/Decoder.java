import java.util.HashMap;

public class Decoder {

    private int bitCount = 9; // amout of extra bits to added during encoding

    public Decoder() {
    }

    public Decoder(int bitValueCount) {
        bitCount = bitValueCount;
    }

    public String decodeBinary(String decodingSequence, String orig) { // O(n^2)

        // basic values init

        String o = decodingSequence;

        decodingSequence = decodingSequence.replace(" ", "");

        HashMap<String, String> directory = new HashMap<String, String>(); // main hashmap that will contain the keys
                                                                           // and values to decode the sequence
        String currBuffer = ""; // buffer to store current byte
        String nextBuffer = ""; // buffer to store next value

        String output = ""; // empty string to store output values
        int storedValues = (int) Math.pow(2, bitCount) / 2; // counter/pointer to the current value key to add

        String[] byteString = new String[(decodingSequence.length() * 2) / bitCount]; // String array that will contain
                                                                                      // all the bytes to be decoded

        // init byteString array with empty strings
        for (int i = 0; i < byteString.length; i++) {
            byteString[i] = "";
        }

        // add all the bytes to each index of the byteString array
        for (int i = 0, j = 0; i < decodingSequence.length(); i++) {
            if (i != 0 && i % bitCount == 0)
                j++;

            byteString[j] += decodingSequence.charAt(i) + "";
        }

        // main decoding loop
        for (int i = 0; i < decodingSequence.length() / bitCount; i++) {

            // get current byte and check if it was already in the directory, if it was
            // found in the directory set the value with the value optained from the
            // directory
            if (byteString[i].charAt(0) == '1')
                currBuffer += directory.get(byteString[i]);
            else
                currBuffer += byteString[i].substring(bitCount - 8);

            // if it is the first byte in the array, add the nextbyte to the firstbyte and
            // place them in the directory with the key 'storedValues' (in bites) while
            // removing the extra bit(s) from the secondbyte and inc the storedValues.
            if (i == 0) {
                nextBuffer += byteString[i + 1].substring(bitCount - 8);

                directory.put(Integer.toBinaryString(storedValues++), currBuffer + nextBuffer);

                // add the first byte only to the output
                output += currBuffer;

                // clear both buffers
                currBuffer = "";
                nextBuffer = "";
                continue;
            }

            // if its the last byte in the array, add it to the output after removing the
            // extra bit(s) and break the loop
            if (i == (decodingSequence.length() / bitCount) - 1) {

                output += currBuffer;

                break;
            }

            // get next byte and check if it was already in the directory, if it was found
            // in the directory set the value with the value optained from the
            // directory
            if (byteString[i + 1].charAt(0) == '1')
                nextBuffer += directory.get(byteString[i + 1]);
            else
                nextBuffer += byteString[i + 1].substring(bitCount - 8);

            // add the nextbyte fo the currbyte and
            // place them in the directory with the key 'storedValues' while removing the
            // extra bit(s) from the nextbyte and inc the storedValues.
            directory.put(Integer.toBinaryString(storedValues++), currBuffer + nextBuffer.substring(0, 8));

            // add the currByte to the output after removing the
            // extra bit(s)
            output += currBuffer;
            currBuffer = "";
            nextBuffer = "";
        }

        return output;
    }
}