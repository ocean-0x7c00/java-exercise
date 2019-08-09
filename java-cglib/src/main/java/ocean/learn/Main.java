package ocean.learn;

import com.google.gson.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * https://blog.csdn.net/zhangxin09/article/details/76566021
 * https://blog.csdn.net/zhangxin09/article/details/76598762
 *
 * @author ocean
 * @date 2019/8/9
 */
public class Main {
    HashMap map = new HashMap();

    public static void main(String[] args) {
//        String str = "{\"valueName1\":{\"firstname\":\"timi\",\"lastname\":\"James\"},\"age\":16,\"edu\":\"SDU\"}";
        String str = "{\"num\":[12,32]}";
//        String str = "{\"valueName1\":{\"firstname\":{\"oldname\":\"timi\",\"newname\":\"tim\"},\"lastname\":\"James\"},\"age\":\"33\",\"edu\":\"tim\",\"num\":[{\"valueName1\":{\"firstname\":{\"oldname\":\"timi\",\"newname\":\"tim\"},\"lastname\":\"James\",\"list\":[1,23,4]},\"age\":33,\"edu\":\"tim\"},1]}";
//        String str="{\"data\":{\"gatewayId\":\"asd\",\"records\":[{\"ms\":123,\"points\":[{\"sensorId\":\"asdasd\",\"sensorType\":\"asdasd\",\"batt\":12,\"kw\":2},{\"sensorId\":\"123\",\"sensorType\":\"as123dasd\",\"batt\":12,\"kw\":2}]},{\"ms\":123123,\"points\":[{\"sensorId\":\"asdasd\",\"sensorType\":\"asdasd\",\"batt\":12,\"kw\":2},{\"sensorId\":\"123\",\"sensorType\":\"as123dasd\",\"batt\":12,\"kw\":2}]}]},\"gatewayType\":\"Asdasd\"}";
        new Main().resolve2Layer(str);
        System.out.println();

    }

    /**
     * 通过Gson将json字符串转为json树
     * 根据json树进行扁平化
     */
    public void resolve2Layer(String str) {
        HashMap result = new HashMap();
        String path = "";
        JsonElement parseTree = new JsonParser().parse(str);
        Map map = new Main().recursiveJsonElement(parseTree, "root");
        for (Object key : map.keySet()) {
            System.out.println(key );
        }
        System.out.println();
        System.out.println(map.get("num[0].valueName1.firstname.newname"));
    }


    public Map recursiveJsonElement(JsonElement parseTree, String rootName) {
        if (parseTree.isJsonObject()) {
            Set<Map.Entry<String, JsonElement>> object = parseTree.getAsJsonObject().entrySet();
            for (Map.Entry<String, JsonElement> entry : object) {
                JsonElement value = entry.getValue();
                String precursorName = rootName;
                String currentKey = entry.getKey();
                String currentName = "".equals(precursorName) ? currentKey : precursorName + "." + currentKey;

                if (value.isJsonArray()) {
                    map.putAll(recursiveJsonElement(value, currentName));
                } else if (value.isJsonObject()) {
                    map.putAll(recursiveJsonElement(value, currentName));
                } else {
                    map.put(currentName, entry.getValue());
                }
            }
        }
        if (parseTree.isJsonArray()) {
            JsonArray array = parseTree.getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                JsonElement element = array.get(i);
                String precursorName = rootName;
                String currentName = "".equals(precursorName) ? "" : String.format("%s[%s]", rootName, i);
                if (element.isJsonObject()) {
                    map.putAll(recursiveJsonElement(element, currentName));
                } else if (element.isJsonArray()) {
                    map.putAll(recursiveJsonElement(element, currentName));
                } else if (element.isJsonPrimitive()) {
                    map.put(currentName, element);
                }
            }
        }

        return map;
    }


}
