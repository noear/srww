package apidemo3.dso;

import org.noear.snack.ONode;
import org.noear.solon.cloud.utils.http.HttpUtils;
import org.noear.water.utils.EncryptUtils;

import java.util.*;


public class TestClient {

//    private static final String C_ID = "11019";
//    private static final String V_ID = "103";

    private static Map<String, String> buildQueryParam(String cId, String vId, String key, String cmd, String json) throws Exception {
        json = ONode.loadStr(json).toJson();

        Map<String, String> query = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append(cmd).append("#").append(json).append("#").append(key);
        String sign = EncryptUtils.sha256(sb.toString());
        String p64 = EncryptUtils.aesEncrypt(json, key, null);

        String p65 = EncryptUtils.aesDecrypt(p64, key, null);

        query.put("p", p64);
        query.put("k", cId + "." + vId + "." + sign);
        return query;
    }

    //测试用发送请求数据, 不加密, 用于API接口
    public static ONode doGet(String url, Map<String, String> input) throws Exception {
        Iterator<Map.Entry<String, String>> entries = input.entrySet().iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            String key = entry.getKey();
            String value = entry.getValue();
            stringBuilder.append(key + "=" + value + "&");
        }
        String result = HttpUtils.http(url + "?" + stringBuilder).get();
        System.err.println(url + "接口返回: " + result);
        return ONode.load(result);
    }

    //测试用发送请求数据, 自动加密, 用于测试CMD接口
    public static ONode doPost(String cId, String vId, String key, String url, String cmd, String json) throws Exception {
        Map<String, String> map = buildQueryParam(cId, vId, key, cmd, json);
        String result = HttpUtils.http(url).data(map).post();

        return ONode.load(result);
    }
}