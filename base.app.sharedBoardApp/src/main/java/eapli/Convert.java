package eapli;

public class Convert {
    public static String convertBytesToString(byte[] data){
        StringBuilder stringBuilder = new StringBuilder();
        for (byte value : data){
            stringBuilder.append((char) value);
        }
        return stringBuilder.toString();
    }}
