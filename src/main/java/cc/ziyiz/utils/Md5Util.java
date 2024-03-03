package cc.ziyiz.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    /**
     * Default combination of characters used to convert bytes to hexadecimal representation,
     * Apache verifies the correctness of downloaded files using this default combination.
     */
    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    protected static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            System.err.println(Md5Util.class.getName() + "initialization failed, MessageDigest does not support MD5Util.");
            nsaex.printStackTrace();
        }
    }

    /**
     * Generate the MD5 checksum value for a string.
     *
     * @param s
     * @return
     */
    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    /**
     * Check if the MD5 checksum of a string matches a known MD5 code.
     *
     * @param password  String to be validated
     * @param md5PwdStr Known MD5 checksum
     * @return
     */
    public static boolean checkPassword(String password, String md5PwdStr) {
        String s = getMD5String(password);
        return s.equals(md5PwdStr);
    }


    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4]; // Extract the high 4 bits of the byte and convert to a digit
        // Logical right shift (>>>)，将符号位一起右移,此处未发现两种符号有何不同
        char c1 = hexDigits[bt & 0xf]; // Extract the low 4 bits of the byte and convert to a digit
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

}
