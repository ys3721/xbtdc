package cn.alip8.publicity;

import cn.alip8.util.Base64;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;


/**
 * @author: Yao Shuai
 * @date: 2021/3/25 16:38
 */
public class PublicityTest {

    public static final String SECRET_KEY = "689a398afe10b6c53d2293801986d90d";

    public static final String appId = "61c62a6dfbb7405aa0483448a3357589";
    public static final String bizId = "1101999999";

    public static final String getOrPost = "POST";


    /**
     * {"ai":"100000000000000001",
     * "name":"某一一",
     * "idNum":"110000190101010001"}
     *
     * @param userId
     * @param name
     * @param idNum
     * @throws Exception
     */
    public void test1(String userId, String name, String idNum) throws Exception {
        String urlPath = new String("https://wlc.nppa.gov.cn/test/collection/loginout/ZTV2ar");
        URL url = new URL(urlPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod(getOrPost);

        connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        connection.setRequestProperty("appId", appId);
        connection.setRequestProperty("bizId", bizId);
        String timeMills = System.currentTimeMillis() + "";
        connection.setRequestProperty("timestamps", timeMills);

        Map<String, String> xitongcanshu = new HashMap<>();
        xitongcanshu.put("appId", appId);
        xitongcanshu.put("bizId", bizId);
        xitongcanshu.put("timestamps", timeMills);

        Map<String, String> urlArgs = new HashMap<>();
        urlArgs.put("ai", "300000000000000001");

        //String encryptedDateBody = encryptDataBody(userId, name, idNum);
        String encryptedDateBody = encryptLoginoutDataBody();

        String sign = this.signIt(xitongcanshu, urlArgs, encryptedDateBody);
        connection.setRequestProperty("sign", sign);

        connection.connect();
        connection.getOutputStream().write(encryptedDateBody.getBytes(UTF_8));
        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    }

    private String encryptLoginoutDataBody() {
        if (getOrPost == "GET") {
            return "";
        }
        String jsonStr =  this.logoutBody();
        String encryptBody = aesGcmEncrypt(jsonStr, hexStringToByte(SECRET_KEY));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", encryptBody);
        return jsonObject.toString();
    }

    private String encryptDataBody(String ai, String name, String idNum) {
        if (getOrPost == "GET") {
            return "";
        }
        JSONObject jo = new JSONObject();
        jo.put("ai", ai);
        jo.put("name", name);
        jo.put("idNum", idNum);
        String jsonStr =  jo.toString();
        String encryptBody = aesGcmEncrypt(jsonStr, hexStringToByte(SECRET_KEY));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", encryptBody);
        return jsonObject.toString();
    }

    private String signIt(Map<String, String> sysArgs, Map<String, String> urlArgs, String encryptedBody) throws NoSuchAlgorithmException {
        HashMap<String, String> map = new HashMap(sysArgs);
        if (getOrPost == "GET") {
            map.putAll(urlArgs);
        }
        List<String> sortKeys = new ArrayList<>(map.keySet());
        Collections.sort(sortKeys);

        StringBuilder materials = new StringBuilder();
        for (String key : sortKeys) {
            materials.append(key).append(map.get(key));
        }
        materials.append(encryptedBody);

        String all = SECRET_KEY + materials.toString();

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(all.getBytes(UTF_8));
        return byteToHexString(messageDigest.digest());
    }

    private String buildReqBodyJson(String ai, String name, String idNum) {
        JSONObject jo = new JSONObject();
        jo.put("ai", ai);
        jo.put("name", name);
        jo.put("idNum", idNum);
        return jo.toString();
    }

    /**
     * <p>@title byteToHexString</p>
     * <p>@description byte数组转化为16进制字符串</p>
     *
     * @param bytes byte数组
     * @return java.lang.String
     */
    private static String byteToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String strHex = Integer.toHexString(aByte);
            if (strHex.length() > 3) {
                sb.append(strHex.substring(6));
            } else {
                if (strHex.length() < 2) {
                    sb.append("0").append(strHex);
                } else {
                    sb.append(strHex);
                }
            }
        }
        return sb.toString();
    }

    /**
     * <p>@title aesGcmDecrypt</p>
     * <p>@description Aes-Gcm解密</p>
     *
     * @param content 带解密文本
     * @param key     密钥
     * @return java.lang.String
     */
    private static String aesGcmDecrypt(String content, byte[] key) {
        try {
            // 根据指定算法ALGORITHM自成密码器
            Cipher decryptCipher = Cipher.getInstance("AES/GCM/PKCS5Padding");
            SecretKeySpec skey = new SecretKeySpec(key, "AES");
            byte[] encodedArrayWithIv = Base64.decode(content);
            GCMParameterSpec decryptSpec = new GCMParameterSpec(128, encodedArrayWithIv, 0, 12);
            decryptCipher.init(Cipher.DECRYPT_MODE, skey, decryptSpec);
            byte[] b = decryptCipher.doFinal(encodedArrayWithIv, 12, encodedArrayWithIv.length - 12);
            return new String(b, UTF_8);
        } catch (Exception e) {
            //建议自行调整为日志输出或抛出异常
            return null;
        }
    }

    /**
     * <p>@title aesGcmEncrypt</p>
     * <p>@description Aes-Gcm加密</p>
     *
     * @param content 待加密文本
     * @param key     密钥
     * @return java.lang.String
     */
    private static String aesGcmEncrypt(String content, byte[] key) {
        try {
            // 根据指定算法ALGORITHM自成密码器
            Cipher cipher = Cipher.getInstance("AES/GCM/PKCS5Padding");
            SecretKeySpec skey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            //获取向量
            byte[] ivb = cipher.getIV();
            byte[] encodedByteArray = cipher.doFinal(content.getBytes(UTF_8));
            byte[] message = new byte[ivb.length + encodedByteArray.length];
            System.arraycopy(ivb, 0, message, 0, ivb.length);
            System.arraycopy(encodedByteArray, 0, message, ivb.length, encodedByteArray.length);
            return Base64.encode(message);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * <p>@title hexStringToByte</p>
     * <p>@description 十六进制string转二进制byte[]</p>
     *
     * @param str 十六进制字符串
     * @return byte[]
     */
    private static byte[] hexStringToByte(String str) {
        byte[] baKeyword = new byte[str.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                //建议自行调整为日志输出或抛出异常
                e.printStackTrace();
            }
        }
        return baKeyword;
    }


    /**
     * 字段	类型	长度	必填	名称	字段说明
     * collections	List		Y	上报对象	游戏用户上下线行为数据上报对象
     * collections[n].no	Int	3	Y	条目编码	在批量模式中标识一条行为数据，取值范围1-128
     * collections[n].si	String	32	Y	游戏内部会话标识	一个会话标识只能对应唯一的实名用户，一个实名用户可以拥有多个会话标识；同一用户单次游戏会话中，上下线动作必须使用同一会话标识上报
     * 备注：会话标识仅标识一次用户会话，生命周期仅为一次上线和与之匹配的一次下线，不会对生命周期之外的任何业务有任何影响
     * collections[n].bt	Int	1	Y	用户行为类型	游戏用户行为类型
     * 0：下线
     * 1：上线
     * collections[n].ot	Long	10	Y	行为发生时间	行为发生时间戳，单位秒
     * collections[n].ct	Int	1	Y	上报类型	用户行为数据上报类型
     * 0：已认证通过用户
     * 2：游客用户
     * collections[n].di	String	32		设备标识	游客模式设备标识，由游戏运营单位生成，游客用户下必填
     * collections[n].pi	String	38		用户唯一标识	已通过实名认证用户的唯一标识，已认证通过用户必填
     * @return
     */
    public String logoutBody() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("no", "1");
        jsonObject.put("si", "youxibeibuhuihub");
        jsonObject.put("bt", 1);
        jsonObject.put("ot", System.currentTimeMillis()/1000+"");
        jsonObject.put("ct", "0");
        jsonObject.put("di", "");
        jsonObject.put("pi", "1fffbjzos82bs9cnyj1dna7d6d29zg4esnh99u");
        JSONObject coj = new JSONObject();
        JSONArray ja = new JSONArray();
        ja.add(jsonObject);
        coj.put("collections", ja.toString());
        return coj.toString();
    }

    public static void main(String[] args) {
        PublicityTest tester = new PublicityTest();
        try {
            tester.test1("2000000000000001", "中宣部", "222401198503180312");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
