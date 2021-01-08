public class Main {
    public static void main(String[] args) throws Exception {

        String code = "001011011101111011111011111101111111011111111";
        System.out.println("\nCode: " + code + " - " + code.length() + " Bits\n");

        String encodedCode = Encoder.encode(code);
        System.out.println("Encoded Code: " + encodedCode + " - " + encodedCode.length() + " Bits\n");

        String decodedCode = Decoder.decode(encodedCode);
        System.out.println("Decoded Code: " + decodedCode + " - " + decodedCode.length() + " Bits\n");

        System.out.println("Is the Decoded value valid?: " + (decodedCode.equals(code)? "Yes": "No"));
    }
}
