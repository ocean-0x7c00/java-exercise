package ocean.parsejson;

import com.google.common.collect.Lists;
import com.google.gson.*;
import com.google.gson.stream.JsonToken;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
//        String jsonPath = "menu.popup.menuitem.value";
        String jsonPath = "object.a";
//        String jsonPath = "array.a";
//        String jsonString = "{\"menu\":{\"id\":\"file\",\"value\":\"File\",\"popup\":{\"menuitem\":[{\"value\":\"New\",\"onclick\":\"CreateNewDoc()\"},{\"value\":\"Open\",\"onclick\":\"OpenDoc()\"},{\"value\":\"Close\",\"onclick\":\"CloseDoc()\"}],\"count\":10,\"key\":\"Read Doc()\",\"list\":[1,2,3]}},\"menu2\":{\"id\":\"file\",\"value\":\"File\",\"popup\":{\"menuitem\":[{\"value\":\"New\",\"onclick\":\"CreateNewDoc()\"},{\"value\":\"Open\",\"onclick\":\"OpenDoc()\"},{\"value\":\"Close\",\"onclick\":\"CloseDoc()\"}],\"count\":10,\"key\":\"Read Doc()\",\"list\":[1,2,3]}}}";
        String jsonString = "[{\"value\":\"New\",\"onclick\":\"CreateNewDoc()\"},{\"value\":\"Open\",\"onclick\":\"OpenDoc()\"},{\"value\":\"Close\",\"onclick\":\"CloseDoc()\"}]";
//        String string = "{\"name\":\"test\",\"array\":[{\"a\":1},{\"b\":1}]}";
//        String string = "{\"object\":{\"a\":\"name is a\"},\"name\":\"test\",\"array\":[{\"a\":1},{\"b\":1},{\"a\":2}]}";

        //arrayString
//        String string = "[{\"object\":{\"a\":\"name is a\",\"b\":{\"a\":\"\"}},\"name\":\"test\",\"array\":[{\"a\":1},{\"b\":1},{\"a\":2}]}]";


        //1.全是对象 {"object":{"a":"name is a","b":{"a":""}}}
//        String string = "{\"object\":{\"a\":\"name is a\",\"b\":{\"a\":\"\"}}}";
//        JsonElement jsonTree = new JsonParser().parse(string);
////        JsonElement subElement = getJsonElement(jsonTree, jsonPath);
//
//        System.out.println();
////        createJsonElement();
//        getSubJsonTree(jsonTree, jsonPath);
//        System.out.println();


//        String[] paths = {"object.a=\"name is a\"", "object.b.a=\"b name\""};
        String[] paths = {
                "num[1].data1.p[9].a=\"1\"",
                "num[0].data1.p[4].a=\"2\"",
                "num[3].data1.p[0].a=\"3\"",
                "num[2].data1.p[5].a=\"4\"",
                "num[3].data1.p[1].a=\"5\"",
                "num[1].data1.p[8].a=\"6\""

        };
        buildJosnTree(Arrays.asList(paths));
    }

    /**
     * 使用正则预编译，加快匹配速度
     */
    static Pattern pattern = Pattern.compile("(.*)\\[\\d+\\]");
    static Pattern equalPattern = Pattern.compile("\\S*\\s*=\\s*\"(.*?)\"");
    static Random random = new Random();

    public static void buildJosnTree(List<String> paths) {
        //最终结果
        JsonArray result = new JsonArray();
        List<JsonElement> list = new ArrayList<>();

        //1.根据路径用栈来构建json


        //2.合并结果多个JSON

        //简单合并，算法实现merge
        ArrayDeque<PathBean> merge = new ArrayDeque<PathBean>();

        for (int i = 0; i < paths.size(); i++) {
            int j = i + 1;
            if (j < paths.size()) {
                ArrayDeque<PathBean> q1 = enQueueing(paths.get(i).split("\\."));
                ArrayDeque<PathBean> q2 = enQueueing(paths.get(j).split("\\."));
                merge = mergeQueue(merge, mergeQueue(q1, q2));
            } else {
                ArrayDeque<PathBean> q1 = enQueueing(paths.get(i).split("\\."));
                merge = mergeQueue(merge, mergeQueue(q1, null));
            }
        }
        System.out.println();


        //获得最终的stack
        Stack<PathBean> stack = new Stack();

        while (true) {
            //出队、入栈
            PathBean pathBean = merge.pollFirst();
            if (pathBean == null) {
                break;
            } else {
                stack.push(pathBean);
            }
        }

        System.out.println(transformPathToJson(stack));
        System.out.println();
    }


    /**
     * 解析路径为json
     *
     * @param stack
     * @return
     */
    public static JsonElement transformPathToJson(Stack<PathBean> stack) {
        ArrayDeque<PathElement> subElementQueue = new ArrayDeque<>();
        List<PathElement> subList = new ArrayList<>();
        Gson gson = new Gson();
        int len = stack.size();
        for (int i = 0; i < len; i++) {
            //取出栈顶元素
            PathBean node = stack.pop();
            List<PathElement> pathElements = node.getBeans();

            //为栈顶元素构建对象
            for (PathElement element : pathElements) {
                String nodePath = element.getPath();
                Boolean flag = equalPattern.matcher(nodePath).matches();
                if (flag) {
                    //叶子节点 (=)
                    String[] nodeValues = nodePath.split("\\=");
                    JsonObject o = new JsonObject();
                    o.add(nodeValues[0], new JsonPrimitive(nodeValues[1].replaceAll("\"", "")));

                    //处理过的节点放到新的stack中
                    element.setJsonElement(o);
                    //处理过的节点放到新的stack中
                    subElementQueue.add(element);

                    element.setElemetnKey(nodeValues[0] + random.nextInt(100));
                    //key id+"."+level
                    subList.add(element);

                } else if (pattern.matcher(nodePath).matches()) {
                    //数组 ([])
                    JsonArray array = new JsonArray();
                    //从subElementStack中获取所有子节点处理子层级所有的元素

                    PathElement subBean1 = subList.stream().filter(o -> o.getParentPath().equalsIgnoreCase(nodePath) && o.getJsonElement() != null).findAny().orElse(null);
                    if (subBean1 != null) {
                        array.add(subBean1.getJsonElement());
                        subList.remove(subBean1);

                    }
                    int subLen = subElementQueue.size();
                    for (int j = 0; j < subLen; j++) {
                        PathElement subBean = subElementQueue.pollFirst();
                        if (subBean.getParentPath().equalsIgnoreCase(nodePath) && subBean.getJsonElement() != null) {
//                            array.add(subBean.getJsonElement());
                        } else {
                            subElementQueue.addLast(subBean);
                        }
                    }

                    if ("root".equalsIgnoreCase(nodePath.replaceAll("\\[\\d*\\]", ""))) {
//                        subElementQueue.push(array);
                    } else {
                        JsonObject o = new JsonObject();
                        if (nodePath == null) {
                            System.out.println();
                        }
                        String na = nodePath.replaceAll("\\[\\d*\\]", "");
                        o.add(na, array);
                        element.setJsonElement(o);
                        subElementQueue.add(element);
                        subList.add(element);
                    }
                } else {
                    //对象 (.)
                    JsonObject o = new JsonObject();
                    Map map = new LinkedHashMap();
                    //从subElementStack中获取所有子节点

                    PathElement subBean1 = subList.stream().filter(bean -> bean.getParentPath().equalsIgnoreCase(nodePath) && bean.getJsonElement() != null).findAny().orElse(null);
                    if (subBean1 != null) {
                        map.putAll(gson.fromJson(subBean1.getJsonElement(), Map.class));
                        subList.remove(subBean1);

                    }


                    int subLen = subElementQueue.size();
                    for (int j = 0; j < subLen; j++) {
                        PathElement subBean = subElementQueue.pollFirst();
                        if (subBean.getParentPath().equalsIgnoreCase(nodePath) && subBean.getJsonElement() != null) {
//                            map.putAll(gson.fromJson(subBean.getJsonElement(), Map.class));
                        } else {
                            subElementQueue.addLast(subBean);
                        }
                    }
                    o.add(nodePath, new Gson().toJsonTree(map));
                    element.setJsonElement(o);
                    subElementQueue.add(element);
                    subList.add(element);
                }

            }//处理同level的元素

        }

        List<PathElement> list = Lists.newArrayList();
        int length = subElementQueue.size();
        if (length == 1) {
            PathElement element = subElementQueue.pollFirst();
            return element.getJsonElement();
        } else {
            for (int i = 0; i < length; i++) {
                PathElement subBean = subElementQueue.pollFirst();
                //如有数组，则全部都是
                if (pattern.matcher(subBean.getPath()).matches()) {
                    list.add(subBean);
                } else {
                    subElementQueue.addLast(subBean);
                }
            }
            Map<String, List<PathElement>> map = list.stream().collect(Collectors.groupingBy(o -> o.getPath().replaceAll("\\[\\d+\\]", "")));
            JsonObject resultMap = new JsonObject();
            for (Map.Entry<String, List<PathElement>> entry : map.entrySet()) {
                JsonArray temp = new JsonArray();
                entry.getValue().stream().
                        map(o -> {
                                    JsonObject asJsonObject = o.getJsonElement().getAsJsonObject();
                                    JsonElement element = asJsonObject.get(o.getPath().replaceAll("\\[\\d*\\]",""));
                                    return element;

                                }


                        ).
                        forEach(o -> temp.addAll(o.getAsJsonArray()));
                resultMap.add(entry.getKey(), temp);
            }
            return resultMap;
        }


    }


    /**
     * 二、合并两个queue到stack
     *
     * @param queue1
     * @param queue2
     * @return
     */
    public static ArrayDeque<PathBean> mergeQueue(ArrayDeque<PathBean> queue1, ArrayDeque<PathBean> queue2) {
        if (queue2 == null) {
            return queue1;
        }
        ArrayDeque<PathBean> arrayDeque = new ArrayDeque<PathBean>();
        while (true) {
            PathBean e1 = queue1.pollFirst();
            PathBean e2 = queue2.pollFirst();
            if (e1 != null && e2 != null) {
                if (e1.getLevel().equalsIgnoreCase(e2.getLevel())) {
                    //同级且名称相同

                    //对象比较的问题：重写equal和hashcode
//                    CollectionUtils.union(Lists.newArrayList("object"),Lists.newArrayList("object"));
                    List unionList = (ArrayList) CollectionUtils.union(e1.getBeans(), e2.getBeans());
                    e1.getBeans().clear();
                    e1.setBeans(unionList);
                    arrayDeque.add(e1);
                } else {
                    arrayDeque.add(e1);
                    arrayDeque.add(e2);
                }
            } else if (e1 != null && e2 == null) {
                arrayDeque.add(e1);
            } else if (e1 == null && e2 != null) {
                arrayDeque.add(e2);
            } else {
                //e1 == null && e2 == null
                break;
            }
        }
        return arrayDeque;
    }

    /**
     * 一、路径封装成PathBean，入队
     *
     * @param array
     * @return
     */
    public static ArrayDeque<PathBean> enQueueing(String[] array) {
        ArrayDeque<PathBean> queue = new ArrayDeque<PathBean>();
        //array
        for (int i = 0; i < array.length; i++) {
            String level = String.format("level%s", i);
            String path = array[i];
            String parentPath = (i - 1) < 0 ? array[i] : array[i - 1];
            String childPath = (i + 1) < array.length ? array[i + 1] : null;
            JsonToken parentType = pattern.matcher(parentPath).matches() ? JsonToken.BEGIN_ARRAY : JsonToken.BEGIN_OBJECT;
            PathElement ele =
                    PathElement.builder().level(level).path(path).childPath(childPath).parentPath(parentPath).parentType(parentType).build();
            queue.add(PathBean.builder().level(level).beans(Lists.newArrayList(ele)).build());
        }
        return queue;
    }


    public static void getSubJsonTree(JsonElement jsonTree, String jsonPath) {
        String[] pathArray = jsonPath.split("\\.");
        Set<String> strings = new HashSet<>();
        JsonElement subJsonTree = jsonTree;
        Deque<String> deque = new LinkedList();
        Map<String, List<String>> paths = new HashMap<>();
        JsonElement sourceTree = jsonTree;
        for (int i = 0; i < pathArray.length; i++) {
            String path = pathArray[i];
            subJsonTree = recursiveJsonTree(subJsonTree, path, deque, paths, sourceTree);
            if (subJsonTree == null) {
                System.out.println("不存在");
                break;
            } else {

            }
        }
        System.out.println(new Gson().toJson(sourceTree));
        System.out.println();
    }

    public static JsonElement recursiveJsonTree(JsonElement jsonTree, String path, Deque<String> deque, Map<String, List<String>> paths, JsonElement sourceTree) {
        if (jsonTree.isJsonPrimitive()) {
        }
        if (jsonTree.isJsonNull()) {
            return jsonTree;
        }
        if (jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            Set<String> set = jsonObject.keySet();
            Set<String> removeSet = new HashSet<>();
            removeSet.add(path);
            deque.add(path);


            //查看path是否存在,并将他的子树赋给subJsonTree
            //1.获取同级的key
            Set<String> sameLevel = jsonObject.keySet();
            //2.查看同级是否包含path,
            jsonTree = jsonObject.get(path);
            if (jsonTree == null) {
                for (String key : sameLevel) {
                    jsonObject.get(path);
                }
//                jsonTree = recursiveJsonTree();
                deque.pollLast();
                return jsonTree;
            } else {
//                //如果jsonTree!=null，说明path在jsonTree有对应的key，删除多余的key
//                List<String> names = (ArrayList) CollectionUtils.subtract(set, removeSet);
//                names.stream().forEach(name -> jsonObject.remove(name));
//                sourceTree = jsonObject;
            }

            if (jsonTree.isJsonObject()) {
                jsonTree = recursiveJsonTree(jsonTree.getAsJsonObject(), path, deque, paths, sourceTree);
                return jsonTree;
            } else if (jsonTree.isJsonArray()) {
                //删除多余的
                return jsonTree;
            } else if (jsonTree.isJsonPrimitive()) {
                //删除多余的
                System.out.println("找到值\t");
                return jsonTree;
            }
        } else if (jsonTree.isJsonArray()) {
            JsonArray asJsonArray = jsonTree.getAsJsonArray();
            String arrayName = deque.pollLast();
            if (arrayName == null) {
                arrayName = "root";
            }
            List<Integer> removeEle = new ArrayList<>();

            for (int j = 0; j < asJsonArray.size(); j++) {
                String realPath = arrayName.replaceAll("\\[\\d*\\]", "") + "[" + j + "]";
                deque.addLast(realPath);
                JsonElement element = asJsonArray.get(j);
                if (element.isJsonObject()) {
                    JsonElement exitObject = element.getAsJsonObject().get(path);


                    if (exitObject != null) {
                        //找到元素
                        jsonTree = exitObject;
                        List<String> list = new ArrayList();
                        StringBuffer buffer = new StringBuffer();
                        for (String p : deque) {
                            list.add(p);
                            buffer.append(p).append(".");
                        }
                        paths.put(String.format("path%s", j), list);
                        System.out.println("路径" + deque);

                        deque.pollLast();
                        deque.pollLast();


                    } else {
                        removeEle.add(j);
                        deque.pollLast();
                        JsonElement result = recursiveJsonTree(element, path, deque, paths, sourceTree);
                        jsonTree = result;
                    }
                } else if (element.isJsonArray()) {
                    jsonTree = recursiveJsonTree(element, path, deque, paths, sourceTree);
                } else if (element.isJsonPrimitive()) {
                    System.out.println("te");
                    return jsonTree;
                }
            }

            removeEle.stream().forEach(i -> asJsonArray.remove(i));
            sourceTree = asJsonArray;
        }//end
        return jsonTree;
    }
}
