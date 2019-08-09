package ocean.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Map;

/**
 * @author yancy
 * @date 2019/8/9
 */

public class TreeModel {
    public static String path = TreeModel.class.getClassLoader().getResource("string.json").getPath();
    String jsonString = "{\"name\":\"Maxsu\", \"age\":26,\"verified\":false,\"marks\": [100,90,85]}";
    String json1 = "[{\"data\":{\"order\":[{\"money\":\"15\",\"orders\":[{\"b1\":[{\"bc1\":\"1\",\"bc2\":\"2\"},{\"bc1\":\"2\",\"bc2\":\"3\"}],\"b2\":\"2\"},{\"b1\":[{\"bc1\":\"1\",\"bc2\":\"2\"},{\"bc1\":\"2\",\"bc2\":\"3\"}],\"b2\":\"3\"}]},{\"money\":\"16\",\"orders\":[{\"b1\":[{\"bc1\":\"1\",\"bc2\":\"2\"},{\"bc1\":\"2\",\"bc2\":\"3\"}],\"b2\":\"2\"},{\"b1\":[{\"bc1\":\"1\",\"bc2\":\"2\"},{\"bc1\":\"2\",\"bc2\":\"3\"}],\"b2\":\"3\"}]},{\"money\":\"17\",\"orders\":[{\"b1\":[{\"bc1\":\"1\",\"bc2\":\"2\"},{\"bc1\":\"2\",\"bc2\":\"3\"}],\"b2\":\"2\"},{\"b1\":[{\"bc1\":\"1\",\"bc2\":\"2\"},{\"bc1\":\"2\",\"bc2\":\"3\"}],\"b2\":\"3\"}]}],\"info\":{\"record\":[{\"a1\":\"1\",\"a2\":\"2\"},{\"a1\":\"1\",\"a2\":\"2\"},{\"a1\":\"1\",\"a2\":\"2\"}]}},\"time\":{\"addr\":\"123\",\"num\":{\"a\":\"111\",\"b\":\"222\"}}}]";
    String json = "{\"data\":{\"order\":[{\"money\":\"15\",\"orders\":[{\"b1\":[{\"bc1\":\"1\",\"bc2\":\"2\"},{\"bc1\":\"2\",\"bc2\":\"3\"}],\"b2\":\"2\"},{\"b1\":[{\"bc1\":\"1\",\"bc2\":\"2\"},{\"bc1\":\"2\",\"bc2\":\"3\"}],\"b2\":\"3\"}]},{\"money\":\"16\",\"orders\":[{\"b1\":[{\"bc1\":\"1\",\"bc2\":\"2\"},{\"bc1\":\"2\",\"bc2\":\"3\"}],\"b2\":\"2\"},{\"b1\":[{\"bc1\":\"1\",\"bc2\":\"2\"},{\"bc1\":\"2\",\"bc2\":\"3\"}],\"b2\":\"3\"}]},{\"money\":\"17\",\"orders\":[{\"b1\":[{\"bc1\":\"1\",\"bc2\":\"2\"},{\"bc1\":\"2\",\"bc2\":\"3\"}],\"b2\":\"2\"},{\"b1\":[{\"bc1\":\"1\",\"bc2\":\"2\"},{\"bc1\":\"2\",\"bc2\":\"3\"}],\"b2\":\"3\"}]}],\"info\":{\"record\":[{\"a1\":\"1\",\"a2\":\"2\"},{\"a1\":\"1\",\"a2\":\"2\"},{\"a1\":\"1\",\"a2\":\"2\"}]}},\"time\":{\"addr\":\"123\",\"num\":{\"a\":\"111\",\"b\":\"222\"}}}";

    public static void main(String[] args) throws IOException {
        String jsonString = "[{\"homepage\":[{\"code\":0,\"data\":{\"judasData\":{\"rankTraceId\":\"8A727BD5C59A392D83095F126D2D2949\"},\"nextStartIndex\":1,\"poiHasNextPage\":false,\"poiTotalNum\":4,\"shopList\":[{\"averagePriceTip\":\"\",\"deliveryTimeTip\":\"\",\"deliveryType\":0,\"discounts2\":[{\"activityId\":0,\"iconUrl\":\"https://p1.meituan.net/xianfu/9c997ecce6150671b8459738a26f8bd9767.png\",\"info\":\"折扣商品5折起\",\"promotionType\":2},{\"activityId\":0,\"iconUrl\":\"https://p0.meituan.net/xianfu/476ba65ee80b6385bab292c085baed17940.png\",\"info\":\"本店支持开发票，开票金额0元起\",\"promotionType\":2}],\"distance\":\"\",\"minPriceTip\":\"起送 ¥15\",\"monthSalesTip\":\"月售863\",\"mtWmPoiId\":\"913776946467795\",\"picUrl\":\"https://p0.meituan.net/waimaipoi/c41c1c57d71737650ca18628ad7cc20623293.jpg@180w_132h_1e_1c\",\"poiTypeIcon\":\"https://p1.meituan.net/aichequan/a88918ba8699e15a5d16d5d7e09ad0022192.png\",\"shippingFeeTip\":\"配送 ¥0\",\"shippingTimeInfo\":{\"descContent\":\"需提前2~6天预订\",\"reservationStatus\":1,\"statusContent\":\"仅预订\"},\"shopName\":\"世纪奥桥花卉绿植店\",\"status\":1,\"statusDesc\":\"\",\"wmPoiScore\":43,\"baseMtWmPoiId\":913775767081939,\"page\":0,\"poiTotalNum\":4,\"lat\":39986617,\"lng\":117267959,\"city\":\"天津市\",\"citycode\":22,\"cluster\":\"47\",\"spider_time\":1554883810215,\"from_type\":\"homepage\"},{\"averagePriceTip\":\"\",\"deliveryTimeTip\":\"\",\"deliveryType\":0,\"discounts2\":[{\"activityId\":0,\"iconUrl\":\"https://p0.meituan.net/xianfu/f8bc8dffdbc805878aa3801a33f563cd1001.png\",\"info\":\"满59减6;满99减10\",\"promotionType\":2},{\"activityId\":0,\"iconUrl\":\"https://p1.meituan.net/xianfu/9c997ecce6150671b8459738a26f8bd9767.png\",\"info\":\"折扣商品5.53折起\",\"promotionType\":2}],\"distance\":\"\",\"minPriceTip\":\"起送 ¥19.9\",\"monthSalesTip\":\"月售578\",\"mtWmPoiId\":\"968580729186260\",\"picUrl\":\"https://p0.meituan.net/waimaipoi/b34f857cfc8299d90ebb89038cd8491726624.jpeg@180w_132h_1e_1c\",\"poiTypeIcon\":\"https://p1.meituan.net/aichequan/a88918ba8699e15a5d16d5d7e09ad0022192.png\",\"shippingFeeTip\":\"配送 ¥0\",\"shippingTimeInfo\":{\"descContent\":\"需提前2~6天预订\",\"reservationStatus\":1,\"statusContent\":\"仅预订\"},\"shopName\":\"花七休花卉绿植生活馆\",\"status\":1,\"statusDesc\":\"\",\"wmPoiScore\":41,\"baseMtWmPoiId\":968579549800404,\"page\":0,\"poiTotalNum\":4,\"lat\":39986617,\"lng\":117267959,\"city\":\"天津市\",\"citycode\":22,\"cluster\":\"47\",\"spider_time\":1554883810215,\"from_type\":\"homepage\"},{\"averagePriceTip\":\"\",\"deliveryTimeTip\":\"\",\"deliveryType\":0,\"discounts2\":[{\"activityId\":0,\"iconUrl\":\"https://p1.meituan.net/xianfu/9c997ecce6150671b8459738a26f8bd9767.png\",\"info\":\"折扣商品2.41折起\",\"promotionType\":2},{\"activityId\":0,\"iconUrl\":\"https://p0.meituan.net/xianfu/c2c0f31d0ebf0f60af115d058169c492992.png\",\"info\":\"领4元券;领6元券\",\"promotionType\":2}],\"distance\":\"\",\"minPriceTip\":\"起送 ¥9.9\",\"monthSalesTip\":\"月售933\",\"mtWmPoiId\":\"1025858413034432\",\"picUrl\":\"https://p1.meituan.net/waimaipoi/d0e826319e54fc33e66bedbc0571ee8584860.jpg@180w_132h_1e_1c\",\"poiTypeIcon\":\"https://p1.meituan.net/aichequan/a88918ba8699e15a5d16d5d7e09ad0022192.png\",\"shippingFeeTip\":\"配送 ¥0\",\"shippingTimeInfo\":{\"descContent\":\"需提前2~6天预订\",\"reservationStatus\":1,\"statusContent\":\"仅预订\"},\"shopName\":\"萌肉居多肉绿植店\",\"status\":1,\"statusDesc\":\"\",\"wmPoiScore\":38,\"baseMtWmPoiId\":1025857233648576,\"page\":0,\"poiTotalNum\":4,\"lat\":39986617,\"lng\":117267959,\"city\":\"天津市\",\"citycode\":22,\"cluster\":\"47\",\"spider_time\":1554883810215,\"from_type\":\"homepage\"},{\"averagePriceTip\":\"\",\"deliveryTimeTip\":\"2.5小时\",\"deliveryType\":0,\"distance\":\"22.5km\",\"minPriceTip\":\"起送 ¥100\",\"monthSalesTip\":\"月售3\",\"mtWmPoiId\":\"1052882347239586\",\"picUrl\":\"https://p0.meituan.net/waimaipoi/6deac1e539d81716101342cdb8b8b54f51200.jpg@180w_132h_1e_1c\",\"poiTypeIcon\":\"\",\"shippingFeeTip\":\"配送 ¥20\",\"shopName\":\"锦秀缘花坊\",\"status\":1,\"statusDesc\":\"\",\"wmPoiScore\":0,\"baseMtWmPoiId\":1052881167853730,\"page\":0,\"poiTotalNum\":4,\"lat\":39986617,\"lng\":117267959,\"city\":\"天津市\",\"citycode\":22,\"cluster\":\"47\",\"spider_time\":1554883810215,\"from_type\":\"homepage\"}]},\"subcode\":0}],\"taskInfo\":{\"utp_name\":\"静海东城_30201290\",\"cluster\":\"47\",\"city\":\"22\",\"utp_id\":\"40201290\",\"type\":\"fw\",\"version\":\"6th_100_1800\"}}]";

        /***                Gson resolve               ***/
        //Create an JsonParser instance
//        JsonParser parser = new JsonParser();

        //create tree from JSON
//        JsonElement rootNode1 = parser.parse(jsonString);

        /***               Jascksons resolve               ***/

        JsonFactory jsonFactory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(jsonFactory);
        JsonNode rootNode = mapper.readTree(jsonString);
        Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();

        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
        }

    }

    public static String readNIO() {
        FileInputStream inputStream = null;
        Charset charset = Charset.forName("GBK");
        CharsetDecoder decoder = charset.newDecoder();
        try {
            inputStream = new FileInputStream(new File(path));
            FileChannel fileChannel = inputStream.getChannel();
            int capacity = 4096;
            ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);
            CharBuffer charBuffer = CharBuffer.allocate(capacity);

            String result = null;
            int length = -1;
            while ((length = fileChannel.read(byteBuffer)) != -1) {
                byteBuffer.flip();
                decoder.decode(byteBuffer, charBuffer, false);
                charBuffer.flip();

                byteBuffer.clear();
                charBuffer.clear();
                byte[] array = byteBuffer.array();
                result = new String(array, 0, length);
            }

            fileChannel.close();
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }





}
