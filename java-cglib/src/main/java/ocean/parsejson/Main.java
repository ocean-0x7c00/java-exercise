package ocean.parsejson;

import com.google.common.collect.Lists;
import com.google.gson.*;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.annotation.ElementType;
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
                "homepage[1].data.poilist[9].third_category=\"饺子\"",
                "homepage[0].data.poilist[4].third_category=\"中式简餐\"",
                "homepage[3].data.poilist[0].third_category=\"凉皮\"",
                "homepage[2].data.poilist[5].third_category=\"中式简餐\"",
                "homepage[3].data.poilist[1].third_category=\"米粉米线\"",
                "homepage[1].data2.poilist[8].third_category=\"西北菜\"",
                "name.data.poilist[8].third_category=\"西北菜\""

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

        int pathSize = paths.size();
        for (int i = 0; i < pathSize; i = i + 2) {
            if (i > pathSize) {
                break;
            }
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

        //subElementQueue中的元素都是同一层级的
        ArrayDeque<PathElement> subElementQueue = new ArrayDeque<>();
        Gson gson = new Gson();
        int len = stack.size();
        for (int i = 0; i < len; i++) {
            //取出栈顶元素
            PathBean node = stack.pop();
            List<PathElement> pathElements = node.getBeans();
            Object[] objects = subElementQueue.toArray();
            subElementQueue.clear();

            //为栈顶元素构建对象
            for (PathElement element : pathElements) {
                String nodePath = element.getPath();
                if (ElementTypeEnum.value.name().equals(element.getPathType())) {
                    //叶子节点 (=)
                    String[] nodeValues = nodePath.split("\\=");
                    JsonObject o = new JsonObject();
                    o.add(nodeValues[0], new JsonPrimitive(nodeValues[1].replaceAll("\"", "")));
                    //处理过的节点放到新的stack中
                    element.setJsonElement(o);
                    subElementQueue.add(element);
                } else if (ElementTypeEnum.array.name().equals(element.getPathType())) {
                    //数组 ([])
                    JsonArray array = new JsonArray();
                    //从subElementStack中获取所有子节点处理子层级所有的元素
                    for (int j = 0; j < objects.length; j++) {
                        PathElement p = (PathElement) objects[j];
                        if (p != null && p.getParentPath().equalsIgnoreCase(nodePath) && p.getJsonElement() != null) {
                            array.add(p.getJsonElement());
                            objects[j] = null;
                        }
                    }
                    element.setJsonElement(array);
                    subElementQueue.add(element);
                } else {
                    //对象 (.)
                    JsonObject o = new JsonObject();
                    Map map = new LinkedHashMap();
                    //从subElementStack中获取所有子节点
                    for (int j = 0; j < objects.length; j++) {
                        PathElement p = (PathElement) objects[j];
                        if (p == null) {
                            continue;
                        }
                        if (element.getPath().equals("data2")) {
                            System.out.println();
                        }
                        if ((element.getChildPath().equalsIgnoreCase(p.getPath()) && p.getParentPath().equals(element.getPath())) && p.getJsonElement() != null) {
                            if (ElementTypeEnum.array.name().equals(p.getPathType())) {
                                map.put(p.getPath().replaceAll("\\[\\d*\\]", ""), gson.fromJson(p.getJsonElement(), List.class));
                            } else if (ElementTypeEnum.object.name().equals(p.getPathType())) {
                                map.putAll(gson.fromJson(p.getJsonElement(), Map.class));
                            }
                            objects[j] = null;
                        }
                    }
                    o.add(nodePath.replaceAll("\\[\\d*\\]", ""), new Gson().toJsonTree(map));
                    element.setJsonElement(o);
                    subElementQueue.add(element);
                }

            }//end-for处理同level的元素

        }

        //最后处理栈中元素，生成返回对象
        List<PathElement> list = Arrays.asList(subElementQueue.toArray(new PathElement[subElementQueue.size()]));
        subElementQueue.clear();
        Map<String, List<PathElement>> collect = list.stream().collect(Collectors.groupingBy(o -> o.getPath().replaceAll("\\[\\d+\\]", "")));

        Map result = new HashMap();
        for (Map.Entry<String, List<PathElement>> entry : collect.entrySet()) {
            JsonArray array = new JsonArray();
            Map map = new HashMap();
            entry.getValue().stream().forEach(pathElement -> {
                JsonElement jsonElement = pathElement.getJsonElement();
                if (jsonElement.isJsonArray()) {
                    array.addAll(jsonElement.getAsJsonArray());
                } else if (jsonElement.isJsonObject()) {
                    Map hashMap = new Gson().fromJson(jsonElement.getAsJsonObject(), Map.class);
                    map.putAll(hashMap);
                }
            });
            if (array.size() != 0) {
                result.put(entry.getKey(), array);
            }
            if (map.size() != 0) {
                result.putAll(map);
            }

        }
        return gson.toJsonTree(result);


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
//                    List unionList = (ArrayList) CollectionUtils.union(e1.getBeans(), e2.getBeans());
//                    e1.getBeans().clear();
//                    e1.setBeans(unionList);
                    e1.getBeans().addAll(e2.getBeans());
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
            String parentType = pattern.matcher(parentPath).matches() ? "array" : equalPattern.matcher(parentPath).matches() ? "value" : "object";
            String pathType = pattern.matcher(path).matches() ? "array" : equalPattern.matcher(path).matches() ? "value" : "object";
            PathElement ele =
                    PathElement.builder().level(level).path(path).childPath(childPath).parentPath(parentPath).parentType(parentType).pathType(pathType).build();
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
