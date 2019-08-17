package ocean.parsejson;

import com.google.gson.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 有一个JSON Tree，希望能实现如下操作
 * 1.根据路径模糊匹配子树或最小子树
 * 2.根据路径精确匹配子树或最小子树
 * 3.扁平化一个树
 * 4.求叶子节点的个数
 * <p>
 * <p>
 * http://cn.voidcc.com/question/p-ytrqmphp-cv.html
 *
 * @author ocean
 * @date 2019/8/16
 */
public class Main {
    /**
     * 两个思路：
     * 思路一：想将jsonString转为jsonTree，在将jsonTree扁平化，计算两个路径的最大化子序列，将
     * 扁平化的json在转为Json
     * <p>
     * 思路二：
     * 求JSON Tree的子树,最小子树
     * <p>
     * 想将jsonString转为jsonTree，从jsonTree获得满足路径要求的json（原json的子集）
     * <p>
     * 如果路径名称中没有下标，则解析成对象中的属性
     * 如果路径名称有下标，则解析成数组中的对象中的属性
     * <p>
     * Returns a JSON sub-element from the given JsonElement and the given path
     *
     * @param json - a Gson JsonElement
     * @param path - a JSON path, e.g. a.b.c[2].d
     * @return - a sub-element of json according to the given path
     */
    public static JsonElement getJsonElement(JsonElement json, String path) {
        String[] parts = path.split("\\.|\\[|\\]");
        JsonElement result = json;

        for (String key : parts) {

            key = key.trim();
            if (key.isEmpty()) {
                continue;
            }

            if (result == null) {
                result = JsonNull.INSTANCE;
                break;
            }

            if (result.isJsonObject()) {
                result = ((JsonObject) result).get(key);
            } else if (result.isJsonArray()) {
                int ix = Integer.valueOf(key) - 1;
                result = ((JsonArray) result).get(ix);
            } else {
                break;
            }
        }

        return result;
    }

    public static void createJsonElement() {
//        LinkedTreeMap
        JsonObject object = new JsonObject();
        object.add("a", new JsonPrimitive(1));

        System.out.println(new Gson().toJson(object));
        System.out.println();
    }

    public static void main(String[] args) {
//        String jsonPath = "menu.popup.menuitem[2].value";
        String jsonPath = "menu.popup.menuitem.value";
//        String jsonString = "{\"menu\":{\"id\":\"file\",\"value\":\"File\",\"popup\":{\"menuitem\":[{\"value\":\"New\",\"onclick\":\"CreateNewDoc()\"},{\"value\":\"Open\",\"onclick\":\"OpenDoc()\"},{\"value\":\"Close\",\"onclick\":\"CloseDoc()\"}],\"count\":10,\"key\":\"Read Doc()\",\"list\":[1,2,3]}},\"menu2\":{\"id\":\"file\",\"value\":\"File\",\"popup\":{\"menuitem\":[{\"value\":\"New\",\"onclick\":\"CreateNewDoc()\"},{\"value\":\"Open\",\"onclick\":\"OpenDoc()\"},{\"value\":\"Close\",\"onclick\":\"CloseDoc()\"}],\"count\":10,\"key\":\"Read Doc()\",\"list\":[1,2,3]}}}";
        String jsonString = "[{\"value\":\"New\",\"onclick\":\"CreateNewDoc()\"},{\"value\":\"Open\",\"onclick\":\"OpenDoc()\"},{\"value\":\"Close\",\"onclick\":\"CloseDoc()\"}]";


        JsonElement jsonTree = new JsonParser().parse(jsonString);
//        JsonElement subElement = getJsonElement(jsonTree, jsonPath);

        System.out.println();
//        createJsonElement();
        getSubJsonTree(jsonTree, jsonPath);
        System.out.println(

        );
    }

    public static void getSubJsonTree(JsonElement jsonTree, String jsonPath) {
        String[] pathArray = jsonPath.split("\\.");
        Set<String> strings = new HashSet<>();
        JsonElement subJsonTree = jsonTree;
        for (String path : pathArray) {
            if (jsonTree.isJsonPrimitive()) {

            }
            if (jsonTree.isJsonNull()) {

            }
            if (jsonTree.isJsonObject()) {
                JsonObject jsonObject = subJsonTree.getAsJsonObject();
                //查看path是否存在,并将他的子树赋给subJsonTree
                subJsonTree = jsonObject.get(path);
            }
            if (jsonTree.isJsonArray()) {
                JsonArray asJsonArray = subJsonTree.getAsJsonArray();
                System.out.println();
            }
        }

        System.out.println();
    }


}
