import java.util.HashMap;

public class Encoder {

    public static String encodeBinary(String encodingSequence) { // O(n^2)

        // a loop to make sure there are no spaces between bytes in the input sequence
        String temp = "";

        for (int i = 0; i < encodingSequence.length(); i++) {
            if (encodingSequence.charAt(i) != ' ')
                temp += "" + encodingSequence.charAt(i);
        }

        encodingSequence = temp;

        HashMap<String, String> directory = new HashMap<String, String>(); // main hashmap that will contain the keys
                                                                           // and values to decode the sequence
        String buffer = ""; // buffer to store current value
        String output = ""; // empty string to store output values
        int storedValues = 256, basevalue = 256; // memory to allocate for the directory's values
                                                 // (100000000 -> 111111111)

        String extraBits = "0"; // amout of extra bits to added during encoding

        boolean repeatedSequence = false; // flag to state if byte was found in directory

        String[] byteString = new String[encodingSequence.length() / 8]; // String array that will contain all the
                                                                         // bytes to be encoded

        // init byteString array with empty strings
        for (int i = 0; i < byteString.length; i++) {
            byteString[i] = "";
        }

        // add all the bytes to each index of the byteString array
        for (int i = 0, j = 0; i < encodingSequence.length(); i++) {
            if (i != 0 && i % 8 == 0)
                j++;

            byteString[j] += encodingSequence.charAt(i) + "";
        }

        // main encoding loop
        for (int i = 0; i < byteString.length; i++) {

            // checks if we ran out of memory
            if (!(storedValues > basevalue * 2)) {

                // checks if the byte sequence we are currently have is NOT repeated, if true,
                // add the byte to the buffer
                if (!repeatedSequence)
                    buffer += byteString[i];

                // if it is the first byte of the sequence, add the next byte to the sequence
                if (i == 0) {
                    buffer += byteString[i + 1];

                    // add the current byte sequence as the key to the 'storedaValues' (in bites)
                    directory.put(buffer, Integer.toBinaryString(storedValues++));

                    // add the sequence minus the last byte to the sequence
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

                    // checks if the directory contains the sequence as a key and will skip the
                    // iteration accordingly
                    if (directory.containsKey(buffer)) {
                        continue;
                    } else {

                        directory.put(buffer, Integer.toBinaryString(storedValues++));
                        output += directory.get(buffer.substring(0, buffer.length() - 8));
                        buffer = "";

                        // after clearing the buffer we must also clear the flag
                        repeatedSequence = false;
                    }
                } else {

                    buffer += byteString[i + 1];

                    if (directory.containsKey(buffer)) {
                        repeatedSequence = true;
                        continue;
                    } else {
                        directory.put(buffer, Integer.toBinaryString(storedValues++));
                        output += extraBits + buffer.substring(0, buffer.length() - 8);
                        buffer = "";
                    }
                }

            } else {
                // the code outputs "out of space" when the assigned memory is consumed
                System.out.println("out of space");
                break;
            }
        }
        // amount of memoy used by the directory, I will leave this in as to help other
        // who might want to increase the memory allocated by their own.
        // Note: you also need to change the bits in the decoder.
        System.out.println("Memory State: " + storedValues);
        return output;
    }

    // overloaded method to accept an int sequence representing bytes
    public static String encodeInt(int encodingSequence) {
        return encodeBinary(encodingSequence + "");
    }

}