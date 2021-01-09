import java.util.HashMap;

public class Decoder {

    // public static String decode(String decodingSequence) {

    // HashMap<String, String> directory = new HashMap<String, String>();
    // String buffer = "";
    // int currIndex = 0;
    // String output = "";
    // int storedValues = 0;

    // boolean repeatedSequence = false;

    // for (int i = currIndex; i < decodingSequence.length(); i++) {
    // buffer += decodingSequence.charAt(i);

    // if (i == 0) {
    // directory.put(Integer.toBinaryString(storedValues++), buffer);
    // // output += directory.get(Integer.toBinaryString(storedValues - 1));
    // output += buffer;
    // buffer = "";
    // continue;
    // }

    // if (i == decodingSequence.length() - 1) {
    // if (directory.containsKey(buffer)) {
    // output += directory.get(buffer);
    // } else {
    // String decodedSegment = directory.get(buffer.substring(0, buffer.length() -
    // 1)) + "";
    // output += decodedSegment + buffer.charAt(buffer.length() - 1);
    // }
    // break;
    // }

    // if (repeatedSequence) {
    // if (directory.containsKey(buffer)) {
    // continue;
    // } else {
    // // output += directory.get(Integer.toBinaryString(storedValues - 1));
    // String decodedSegment = directory.get(buffer.substring(0, buffer.length() -
    // 1)) + ""
    // + buffer.charAt(buffer.length() - 1);
    // directory.put(Integer.toBinaryString(storedValues++), decodedSegment);
    // output += decodedSegment;
    // buffer = "";
    // repeatedSequence = false;
    // }
    // } else {
    // if (directory.containsKey(buffer)) {
    // repeatedSequence = true;
    // continue;
    // } else {
    // directory.put(Integer.toBinaryString(storedValues++), buffer);
    // // output += directory.get(Integer.toBinaryString(storedValues - 1));
    // output += buffer;
    // buffer = "";
    // }
    // }

    // }
    // return output;
    // }

    public static String decodeBinary(String decodingSequence) {
        HashMap<String, String> directory = new HashMap<String, String>();
        String currBuffer = "";
        String nextBuffer = "";

        String output = "";
        int storedValues = 256;
        String extraBits = "0";

        boolean repeatedSequence = false;

        String[] byteString = new String[(decodingSequence.length() * 2) / 8];

        for (int i = 0; i < byteString.length; i++) {
            byteString[i] = "";
        }

        for (int i = 0, j = 0; i < decodingSequence.length(); i++) {
            if (i != 0 && i % 9 == 0)
                j++;

            byteString[j] += decodingSequence.charAt(i) + "";
        }

        for (int i = 0; i < decodingSequence.length() / 9; i++) {

            if (byteString[i].charAt(0) == '1')
                currBuffer += directory.get(byteString[i]);
            else
                currBuffer += byteString[i];

            if (i == 0) {
                nextBuffer += byteString[i + 1];

                directory.put(Integer.toBinaryString(storedValues++),
                        currBuffer.substring(0) + nextBuffer.substring(1));
                // output += directory.get(Integer.toBinaryString(storedValues - 1));
                output += currBuffer.substring(extraBits.length());
                currBuffer = "";
                nextBuffer = "";
                continue;
            }

            if (i == (decodingSequence.length() / 9) - 1) {

                output += currBuffer.substring(extraBits.length());

                break;
            }

            // if (repeatedSequence) {

            // if (byteString[i + 1].charAt(0) == '1')
            // nextBuffer += directory.get(byteString[i + 1]);
            // else
            // nextBuffer += byteString[i];

            // if (directory.containsKey(buffer)) {
            // continue;
            // } else {

            // directory.put(Integer.toBinaryString(storedValues++), buffer);
            // // output += directory.get(Integer.toBinaryString(storedValues - 1));
            // output += directory.get(buffer.substring(0, buffer.length() - 10));
            // buffer = "";
            // repeatedSequence = false;
            // }
            // } else {

            if (byteString[i + 1].charAt(0) == '1')
                nextBuffer += directory.get(byteString[i + 1]);
            else
                nextBuffer += byteString[i + 1];

            // if (directory.containsKey(currBuffer)) {
            // repeatedSequence = true;
            // continue;
            // } else {
            directory.put(Integer.toBinaryString(storedValues++), currBuffer.substring(0) + nextBuffer.substring(1));
            // output += directory.get(Integer.toBinaryString(storedValues - 1));
            output += currBuffer.substring(extraBits.length());
            currBuffer = "";
            nextBuffer = "";

            // }
            // }

        }
        System.out.println("Memory State: " + storedValues);
        return output;

    }

    public static String decodeInt(int decodingSequence) {
        return decodeBinary(decodingSequence + "");
    }
}