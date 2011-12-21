package edu.syr.ischool.mafudge.ensemblelib;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMacMD5Encoder {
	public static String Encode(String secretKey, String plainText) throws Exception
	{
		String result;
		HexStringConverter hsc = new HexStringConverter();
		byte[] keyAsBytes = hsc.ToByteArray(secretKey);
		// get an hmac_sha1 key from the raw key bytes
		SecretKeySpec signingKey = new SecretKeySpec(keyAsBytes, "HmacMD5");

		// get an hmac_md5 Mac instance and initialize with the signing key
		Mac mac = Mac.getInstance("HmacMD5");
		mac.init(signingKey);

		// compute the hmac on input data bytes
		byte[] rawHmac = mac.doFinal(plainText.getBytes("UTF-8"));
		
		result = hsc.ToHexString(rawHmac);

		return result;
	}

}
