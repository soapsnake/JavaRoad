package com.soapsnake.lab.totp;

/**
 *
 * Created on 2020-06-03
 */
import java.lang.reflect.UndeclaredThrowableException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Hex;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.soapsnake.lab.commonutils.ContactInfo;
import com.soapsnake.lab.commonutils.HttpUtils;


/**
 * This is an example implementation of the OATH
 * TOTP algorithm.
 * Visit www.openauthentication.org for more information.
 *
 * @author Johan Rydell, PortWise, Inc.
 */

public class TOTP {

    public TOTP() {
    }

    /**
     * This method uses the JCE to provide the crypto algorithm.
     * HMAC computes a Hashed Message Authentication Code with the
     * crypto hash algorithm as a parameter.
     *
     * @param crypto: the crypto algorithm (HmacSHA1, HmacSHA256,
     * HmacSHA512)
     * @param keyBytes: the bytes to use for the HMAC key
     * @param text: the message or text to be authenticated
     */

    private static byte[] hmac_sha(String crypto, byte[] keyBytes,
            byte[] text) {
        try {
            Mac hmac;
            hmac = Mac.getInstance(crypto);
            SecretKeySpec macKey =
                    new SecretKeySpec(keyBytes, "RAW");
            hmac.init(macKey);
            return hmac.doFinal(text);
        } catch (GeneralSecurityException gse) {
            throw new UndeclaredThrowableException(gse);
        }
    }


    /**
     * This method converts a HEX string to Byte[]
     *
     * @param hex: the HEX string
     * @return: a byte array
     */

    private static byte[] hexStr2Bytes(String hex) {
        // Adding one byte to get the right conversion
        // Values starting with "0" can be converted
        byte[] bArray = new BigInteger("10" + hex, 16).toByteArray();

        // Copy all the REAL bytes, not the "first"
        byte[] ret = new byte[bArray.length - 1];
        for (int i = 0; i < ret.length; i++)
            ret[i] = bArray[i + 1];
        return ret;
    }

    private static final long[] DIGITS_POWER
            // 0 1  2   3    4     5      6       7        8           9           10
            = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, 10000000000L};


    /**
     * This method generates a TOTP value for the given
     * set of parameters.
     *
     * @param key: the shared secret, HEX encoded
     * @param time: a value that reflects a time
     * @param returnDigits: number of digits to return
     * @return: a numeric String in base 10 that includes
     * {@link truncationDigits} digits
     */

    public static String generateTOTP(String key,
            String time,
            String returnDigits) {
        return generateTOTP(key, time, returnDigits, "HmacSHA1");
    }


    /**
     * This method generates a TOTP value for the given
     * set of parameters.
     *
     * @param key: the shared secret, HEX encoded
     * @param time: a value that reflects a time
     * @param returnDigits: number of digits to return
     * @return: a numeric String in base 10 that includes
     * {@link truncationDigits} digits
     */

    public static String generateTOTP256(String key,
            String time,
            String returnDigits) {
        return generateTOTP(key, time, returnDigits, "HmacSHA256");
    }

    /**
     * This method generates a TOTP value for the given
     * set of parameters.
     *
     * @param key: the shared secret, HEX encoded
     * @param time: a value that reflects a time
     * @param returnDigits: number of digits to return
     * @return: a numeric String in base 10 that includes
     * {@link truncationDigits} digits
     */

    public static String generateTOTP512(String key,
            String time,
            String returnDigits) {
        return generateTOTP(key, time, returnDigits, "HmacSHA512");
    }


    /**
     * This method generates a TOTP value for the given
     * set of parameters.
     *
     * @param key: the shared secret, HEX encoded, 16进制编码的秘钥,我这边也需要???
     * @param time: a value that reflects a time    unix当前时间戳,时区就有讲究了感觉
     * @param returnDigits: number of digits to return    返回的数字长度,比如我这里要求是10
     * @param crypto: the crypto function to use   哈希算法 hmac512算法写死
     * @return: a numeric String in base 10 that includes
     * {@link truncationDigits} digits
     */

    public static String generateTOTP(String key,
            String time,
            String returnDigits,
            String crypto) {
        int codeDigits = Integer.decode(returnDigits).intValue();
        String result = null;

        // Using the counter
        // First 8 bytes are for the movingFactor
        // Compliant with base RFC 4226 (HOTP)
        while (time.length() < 16)
            time = "0" + time;

        // Get the HEX in a Byte[]
        byte[] msg = hexStr2Bytes(time);
        byte[] k = hexStr2Bytes(key);

        byte[] hash = hmac_sha(crypto, k, msg);

        // put selected bytes into result int
        int offset = hash[hash.length - 1] & 0xf;

        int binary =
                ((hash[offset] & 0x7f) << 24) |
                        ((hash[offset + 1] & 0xff) << 16) |
                        ((hash[offset + 2] & 0xff) << 8) |
                        (hash[offset + 3] & 0xff);

        long otp = (long) (binary % DIGITS_POWER[codeDigits]);

        result = Long.toString(otp);
        while (result.length() < codeDigits) {
            result = "0" + result;
        }
        return result;
    }



    public static void main(String[] args) {
        // Seed for HMAC-SHA1 - 20 bytes
        String seed = "3132333435363738393031323334353637383930";
        // Seed for HMAC-SHA256 - 32 bytes
        String seed32 = "3132333435363738393031323334353637383930" +
                "313233343536373839303132";
        // Seed for HMAC-SHA512 - 64 bytes
        String seed64 = "6e696e6a61406578616d706c652e636f6d4844454348414c4c454e4745303033" +
                "6e696e6a61406578616d706c652e636f6d4844454348414c4c454e4745303033" +
                "6e696e6a61406578616d706c652e636f6d4844454348414c4c454e4745303033" +
                "6e696e6a61406578616d706c652e636f6d4844454348414c4c454e4745303033";
        long T0 = 0;
        long X = 30;
        long testTime[] = {59L, 1111111109L, 1111111111L,
                1234567890L, 2000000000L, 20000000000L};

        String steps = "0";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));


        try {
            System.out.println(
                    "+---------------+-----------------------+" +
                            "------------------+--------+--------+");
            System.out.println(
                    "|  Time(sec)    |   Time (UTC format)   " +
                            "| Value of T(Hex)  |  TOTP  | Mode   |");
            System.out.println(
                    "+---------------+-----------------------+" +
                            "------------------+--------+--------+");

            for (int i = 0; i < testTime.length; i++) {
                long T = (testTime[i] - T0) / X;
                steps = Long.toHexString(T).toUpperCase();
                while (steps.length() < 16)
                    steps = "0" + steps;
                String fmtTime = String.format("%1$-11s", testTime[i]);
                String utcTime = df.format(new Date(testTime[i] * 1000));
                System.out.print("|  " + fmtTime + "  |  " + utcTime +
                        "  | " + steps + " |");
                System.out.println(generateTOTP(seed, steps, "8",
                        "HmacSHA1") + "| SHA1   |");
                System.out.print("|  " + fmtTime + "  |  " + utcTime +
                        "  | " + steps + " |");
                System.out.println(generateTOTP(seed32, steps, "8",
                        "HmacSHA256") + "| SHA256 |");
                System.out.print("|  " + fmtTime + "  |  " + utcTime +
                        "  | " + steps + " |");


                //这一个
                System.out.println(generateTOTP(seed64, steps, "10",
                        "HmacSHA512") + "| SHA512 |" + "steps = " + steps);

                System.out.println(
                        "+---------------+-----------------------+" +
                                "------------------+--------+--------+");
            }
        } catch (final Exception e) {
            System.out.println("Error : " + e);
        }

        String str = "12345678901234567890";
        String str2 = "ninja@example.comHENNGECHALLENGE003";
        System.out.println("base64 = " + Hex.encodeHexString(str2.getBytes()));

        String myKey = "ninja@example.comHENNGECHALLENGE003";
        String key = Hex.encodeHexString(myKey.getBytes());
        String time = System.currentTimeMillis() / 1000 / 30 + "";
        String digits = "10";
        String hashalgo = "HmacSHA512";
        System.out.println("here is my key : " + generateTOTP(key, time, digits, hashalgo));
    }


    @Test
    public void sendHttp(){
        String userid = "51721198@qq.com";
        String myKey = userid + "HENNGECHALLENGE003";
        String rowkey = Hex.encodeHexString(myKey.getBytes());
        System.out.println("rowkey = " + rowkey + " rowkeylenth = " + rowkey.length());
        String key = rowkey;
        System.out.println("key = " + key + " lenth = " + key.length());

        long T0 = 0;
        long X = 30;
        long T = (System.currentTimeMillis() / 1000 - T0) / X;
        String steps = Long.toHexString(T).toUpperCase();
        while (steps.length() < 16)
            steps = "0" + steps;
        System.out.println("steps = " + steps);
        String digits = "10";
        String hashalgo = "HmacSHA512";
        String totp = generateTOTP(key, steps, digits, hashalgo);
        System.out.println("here is my key : " + totp);

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("contact_email", userid));
        urlParameters.add(new BasicNameValuePair("github_url", "https://gist.github.com/hennge/b859bd12e7a7fb418141"));
        String url = "https://api.challenge.hennge.com/challenges/003";
        String finalCode = userid + ":" + totp;
        System.out.println("finalcode = " + finalCode);
        ContactInfo conta = new ContactInfo(userid, "https://gist.github.com/smithjson/b859bd13e7a7fb419141");

        System.out.println(JSON.toJSONString(conta));
        HttpUtils.httpPost(urlParameters, url, finalCode, conta);

        String OS = System.getProperty("os.name").toLowerCase();
        System.out.println("os = " + OS);

    }
}
