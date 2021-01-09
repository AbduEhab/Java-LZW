public class Main {
    public static void main(String[] args) throws Exception {

        String code = "011101000110100001101001011100110110100101110011011101000110100001100101";

        System.out.println("\nCode:\t\t" + code + " - " + code.length() + " Bits\n");

        String encodedCode = Encoder.encodeBinary(code);
        System.out.println("Encoded Code:\t" + encodedCode + " - " + encodedCode.length() + " Bits\n");

        String decodedCode = Decoder.decodeBinary(encodedCode);
        System.out.println("Decoded Code:\t" + decodedCode + " - " + decodedCode.length() + " Bits\n");

        System.out.println("Is the Decoded value valid?: " + (decodedCode.equals(code) ? "Yes" : "No"));
    }
}
