package edu.syr.ischool.mafudge.ensemblelib;

public class HexStringConverter {
	
    public byte[] ToByteArray(String hexString) 
    {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                                 + Character.digit(hexString.charAt(i+1), 16));
        }
        return data;
    }

    public String ToHexString(byte[] byteArray)
    {
        StringBuilder sb = new StringBuilder();
        for (byte b : byteArray) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString().toLowerCase();
    }


}
