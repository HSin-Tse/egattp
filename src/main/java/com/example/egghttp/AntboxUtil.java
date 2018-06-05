//package com.fruitday.citybox.utils;
//
//import com.fruitday.citybox.dto.*;
//import com.fruitday.citybox.helper.ConstantHelper;
//import com.fruitday.citybox.helper.RetrofitHelper;
//import io.netty.buffer.ByteBuf;
//import retrofit2.Response;
//
//import java.io.*;
//import java.security.NoSuchAlgorithmException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.security.MessageDigest;
//
//import static com.fruitday.citybox.helper.ConstantHelper.HISTORY_PATH;
//
///**
// * Antbox Utilities.例如：计算AntboxObject的CRC值。
// *
// * @author yong.ma
// * @since 0.0.1
// */
//public abstract class AntboxUtil {
//
//    /**
//     * CRC: Polynomial: POLYNOMIAL=0x8408;
//     */
//    static final int CRC_POLYNOMIAL = 0x8408;
//
//    /**
//     * CRC: Start Value: PRESET_VALUE=0xffff;
//     */
//    static final int CRC_START_VALUE = 0xffff;
//
//    /**
//     * 循环冗余码校验（CRC）<br>
//     * 长度为2个字节的CRC-16效验和,低字节在前.<br>
//     * 计算包括了从MSB-LEN16开始到Data[]的全部数据，得到的CRC在传送时低字节在前。
//     */
//    public static int calcCrc(ByteBuf buf, int offset) {
//        int i, j;
//        int crc = CRC_START_VALUE;
//        final int LEN = buf.readableBytes();
//        for (i = offset; i < LEN; i++) {
//            crc = crc ^ (buf.getUnsignedByte(i));
//            for (j = 0; j < 8; j++) {
//                if (notZero(crc & 0x0001)) {
//                    crc = (crc >> 1) ^ CRC_POLYNOMIAL;
//                } else {
//                    crc = (crc >> 1);
//                }
//            }
//        }
//        int lsb = crc & 0x00ff;
//        int msb = (crc >> 8) & 0x00ff;
//        return (lsb << 8) | msb;
//    }
//
//    /**
//     * 写日期至ByteBuf<br>
//     * 从左到右，每个字节依次为年、月、日、时、分、秒，以16进制表示。
//     */
//    public static void writeDate(Date date, ByteBuf dataBuf) {
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        int y = c.get(Calendar.YEAR);
//        int m = c.get(Calendar.MONTH) + 1;
//        int d = c.get(Calendar.DAY_OF_MONTH);
//        int h = c.get(Calendar.HOUR_OF_DAY);
//        int i = c.get(Calendar.MINUTE);
//        int s = c.get(Calendar.SECOND);
//        dataBuf.writeByte(toByteForDate(y % 100));
//        dataBuf.writeByte(toByteForDate(m));
//        dataBuf.writeByte(toByteForDate(d));
//        dataBuf.writeByte(toByteForDate(h));
//        dataBuf.writeByte(toByteForDate(i));
//        dataBuf.writeByte(toByteForDate(s));
//    }
//
//    /**
//     * TODO 从ByteBuf读取日期
//     *
//     * @param dataBuf [Time(6)，SN(5)，State(1)，Msgok(1)，Reserve(19 bytes)]
//     */
//    public static Date readDate(ByteBuf dataBuf) {
//        byte[] time = new byte[6];
//        dataBuf.readBytes(time, 0, time.length);
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.YEAR, 100 * (c.get(Calendar.YEAR) / 100) + time[0]);
//        c.set(Calendar.MONTH, time[1] - 1);
//        c.set(Calendar.DAY_OF_MONTH, time[2]);
//        c.set(Calendar.HOUR_OF_DAY, time[3]);
//        c.set(Calendar.MINUTE, time[4]);
//        c.set(Calendar.SECOND, time[5]);
//        return c.getTime();
//    }
//
//    /**
//     * 取第pos个字节
//     *
//     * @param x   长整数
//     * @param pos 数字大的表示高位。从0开始。
//     * @return pos位置上的字节
//     */
//    public static byte byteAt(long x, int pos) {
//        return (byte) ((x >>> (8 * pos)) & 0xff);
//    }
//
//    /**
//     * 位运算-向左移动<tt>8*byteOffset</tt>位
//     *
//     * @param b          the digit
//     * @param byteOffset 向左移动字节数(<tt>8*byteOffset</tt>位)
//     * @return 位移后的整数
//     */
//    public static long shiftLeft(byte b, int byteOffset) {
//        return (0xff & b) << (8 * byteOffset);
//    }
//
//    private static short toByteForDate(int i) {
//        return (short) i;
//    }
//
//    public static boolean notZero(int k) {
//        return k != 0L;
//    }
//
//    public static String hexDump(byte[] buffer) {
//        StringBuilder sb = new StringBuilder();
//        for (byte b : buffer) {
//            sb.append(' ');
//            String x = Integer.toHexString(0xff & b);
//            if (x.length() < 2) {
//                sb.append('0');
//            }
//            sb.append(x);
//        }
//        return sb.substring(1);
//    }
//
//    public static String leftPad(String str, int len, char pad) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < len - str.length(); i++) {
//            sb.append(pad);
//        }
//        sb.append(str);
//        return sb.toString();
//    }
//
//    public static String stringMD5(String input) {
//
//        try {
//
//
//            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
//
//            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//
//            // 输入的字符串转换成字节数组
//
//            byte[] inputByteArray = input.getBytes("UTF-8");
//
//            // inputByteArray是输入字符串转换得到的字节数组
//
//            messageDigest.update(inputByteArray);
//
//            // 转换并返回结果，也是字节数组，包含16个元素
//
//            byte[] resultByteArray = messageDigest.digest();
//
//            // 字符数组转换成字符串返回
//            return byteArrayToHex(resultByteArray);
//
//        } catch (NoSuchAlgorithmException e) {
//
//            return null;
//        } catch (UnsupportedEncodingException e) {
//            return null;
//        }
//    }
//
//    public static String byteArrayToHex(byte[] byteArray) {
//
//        // 首先初始化一个字符数组，用来存放每个16进制字符
//
//        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
//
//
//        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
//
//        char[] resultCharArray = new char[byteArray.length * 2];
//
//
//        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
//
//        int index = 0;
//
//        for (byte b : byteArray) {
//
//            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
//
//            resultCharArray[index++] = hexDigits[b & 0xf];
//
//        }
//
//
//        // 字符数组组合成字符串返回
//
//        return new String(resultCharArray);
//
//    }
//
//    public static String signForAPI(Map<String, String> map) {
//
//        String secret = ConstantHelper.SECERT;
//
//        String query = "";
//        Map<String, String> resultMap = sortMapByKey(map);    //按Key进行排序
//
//        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
//            query += entry.getKey() + '=' + entry.getValue() + "&";
//        }
//        System.out.println(query);
//        System.out.println(query + secret);
//        query = AntboxUtil.stringMD5(query + secret).substring(0, 31);
//        query = query.toLowerCase();
//        System.out.println(query);
//        System.out.println(query + "w");
//        System.out.println(AntboxUtil.stringMD5(query + "w").toLowerCase());
//        return AntboxUtil.stringMD5(query + "w").toLowerCase();
//    }
//
//    public static Map<String, String> sortMapByKey(Map<String, String> map) {
//        if (map == null || map.isEmpty()) {
//            return null;
//        }
//        Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
//
//        sortMap.putAll(map);
//        return sortMap;
//    }
//
//    public static LabelProductDto label2product(RestResult restResult, String version) {
//
//        LabelProductDto labelProductDto = new LabelProductDto(0);
//
//        int v = restResult.getUidList() == null ? 0 : restResult.getUidList().size();
//        labelProductDto.setView_count(v);
//
//        List<Product_label> rows = new ArrayList<Product_label>();
//        if (restResult.getCode().equals(ConstantHelper.ERROR_CODE)) {
//            labelProductDto.setTotal(1);
//            Product_label product_label = new Product_label(restResult.getMessage(), "xxx", restResult.getMessage());
//            rows.add(product_label);
//            labelProductDto.setRows(rows);
//        } else {
//            List<String> uidList = restResult.getUidList();
//            if (uidList != null && uidList.size() > 0) {
//                Map<String, Integer> rssiMap = new HashMap<>();
//                String labelBody = restResult.getBody();
//                if (version.equals("2")) {
//                    //截取RSSI的值
//                    List<String> newList = new ArrayList<String>();
//
//                    for (String s : uidList) {
//                        int length = s.length();
//                        String rfid = s.substring(0, length - 2);
//                        ;
//                        String rssi = s.substring(length - 2, length);
//                        int parseInt = Integer.parseInt(rssi, 16);
//                        rssiMap.put(rfid, parseInt);
//                        newList.add(rfid);
//                    }
//                    labelBody = newList.toString();
//                    uidList = newList;
//                }
//
//                Map<String, String> headerMap = new HashMap<>();
//                headerMap.put("platform", ConstantHelper.PLATFORM);
//                Map<String, String> paraMap = new HashMap<>();
//                paraMap.put("labels", labelBody);
//                headerMap.put("sign", signForAPI(paraMap));
//                Response<ApiResult> retResult = null;
//                try {
//                    retResult = RetrofitHelper.getLocalService().productLableByPost(headerMap, labelBody).execute();
//                    if (retResult.errorBody() != null) {
//                        labelProductDto.setTotal(1);
//                        Product_label product_label = new Product_label(retResult.errorBody().string(), "xxx", retResult.errorBody().toString());
//                        rows.add(product_label);
//                        labelProductDto.setRows(rows);
//                    } else {
//                        List<Product_label> ret = retResult.body().getRows();
//                        List<Product_label> newRet = new ArrayList<Product_label>();
//
//                        for (Product_label p : ret) {
//                            Product_label p1 = new Product_label();
//                            p1.setLabel(p.getLabel());
//                            p1.setProduct_id(p.getProduct_id());
//                            p1.setProduct_name(p.getProduct_name());
//                            p1.setExpire_time(p.getExpire_time());
//                            if (version.equals("2")) {
//                                p1.setRssi(findLabelRssi(p.getLabel(), rssiMap));
//                            } else {
//                                p1.setRssi("-");
//                            }
//                            newRet.add(p1);
//                        }
//                        labelProductDto.setTotal(Integer.parseInt(retResult.body().getTotal()));
//                        labelProductDto.setRows(newRet);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                labelProductDto.setTotal(1);
//                Product_label product_label = new Product_label("没有识别到标签", "xxx", "没有识别到标签");
//                rows.add(product_label);
//                labelProductDto.setRows(rows);
//            }
//        }
//        return labelProductDto;
//    }
//
//    private static String findLabelRssi(String label, Map<String, Integer> rssiMap) {
//        String rssi = "";
//        for (Map.Entry<String, Integer> entry : rssiMap.entrySet()) {
//            if (entry.getKey().equals(label)) {
//                Integer value = entry.getValue();
//                rssi = value.toString();
//                if (value <= 110) {
//                    rssi = "<span style='color:red'>" + value.toString() + "[建议不使用]</span>";
//                }
//            }
//        }
//        return rssi;
//    }
//
//    public static String bindLabel(String label_product,String warehouse,String expire_time) {
//        Map<String, String> headerMap = new HashMap<>();
//        headerMap.put("platform", "client");
//        Map<String, String> paraMap = new HashMap<>();
//        paraMap.put("label_product", label_product);
//        paraMap.put("warehose", warehouse);
//        paraMap.put("expire_time", expire_time);
//        headerMap.put("sign", signForAPI(paraMap));
//        Response<ApiResult> retResult = null;
//        try {
//            retResult = RetrofitHelper.getLocalService().bindLableToproductByPost(headerMap, label_product,warehouse,expire_time).execute();
//            if (retResult.errorBody() != null) {
//                return "fail";
//            } else {
//                createDir(HISTORY_PATH);
//                String fileName  = HISTORY_PATH + "/" +new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(new Date());;
//                writeFile(fileName,label_product);
//                return "succ";
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "succ";
//    }
//
//    public static List<ProductDto> findProByLabel(String name, String platform) {
//        System.out.println("商品名称" + name);
//        List<ProductDto> productDtos = new ArrayList<ProductDto>();
//        Map<String, String> headerMap = new HashMap<>();
//        headerMap.put("platform", "client");
//        Map<String, String> paraMap = new HashMap<>();
////        name = URLEncoder.encode(name);
//        System.out.println("商品名称 encode" + name);
//        paraMap.put("name", name);
//        paraMap.put("platform", platform);
//        headerMap.put("sign", signForAPI(paraMap));
//        Response<List<ProductDto>> retResult = null;
//        try {
//
//            retResult = RetrofitHelper.getLocalService().productInfo(headerMap, name, platform).execute();
//            System.out.println(retResult.toString());
//            if (retResult.errorBody() != null) {
//
//            } else {
//                productDtos = retResult.body();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return productDtos;
//    }
//
//    public static List<WarehoseDto> listWarehouse( String platform) {
//        List<WarehoseDto> warehoseDtos = new ArrayList<WarehoseDto>();
//        Map<String, String> headerMap = new HashMap<>();
//        headerMap.put("platform", "client");
//        Map<String, String> paraMap = new HashMap<>();
//        paraMap.put("platform", platform);
//        headerMap.put("sign", signForAPI(paraMap));
//        Response<List<WarehoseDto>> retResult = null;
//        try {
//            retResult = RetrofitHelper.getLocalService().ajax_warehouse(headerMap, platform).execute();
//            System.out.println(retResult.toString());
//            if (retResult.errorBody() != null) {
//            } else {
//                warehoseDtos = retResult.body();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return warehoseDtos;
//    }
//
//
//    public static void writeFile(String fileName, String content) {
//        try {
//            FileWriter writer = new FileWriter(fileName);
//            writer.write(content);
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static String readFile(String fileName) {
//        int c = 0;
//        String content = "";
//        try {
//            FileReader reader = new FileReader(fileName);
//            c = reader.read();
//            while (c != -1) {
//                content += (char) c;
////                System.out.print((char) c);
//                c = reader.read();
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return content;
//    }
//
//    public static boolean createDir(String destDirName) {
//        File dir = new File(destDirName);
//        if (dir.exists()) {
//            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
//            return false;
//        }
//        if (!destDirName.endsWith(File.separator)) {
//            destDirName = destDirName + File.separator;
//        }
//        //创建目录
//        if (dir.mkdirs()) {
//            System.out.println("创建目录" + destDirName + "成功！");
//            return true;
//        } else {
//            System.out.println("创建目录" + destDirName + "失败！");
//            return false;
//        }
//    }
//    public static List<String> listDir(String path){
//        List<String> list = new ArrayList<String>();
//        File file = new File(path);
//        if(file != null){
//            File[] array = file.listFiles();
//            if(array.length >0){
//                for(int i=0;i<array.length;i++){
//                    if(array[i].isFile()){
//                        list.add(array[i].getName());
//                    }
//                }
//                Collections.reverse(list);
//            }
//        }
//        return list;
//    }
//    /*
//    rfid反序
//     */
//    public static String reverseRfid(String rfid){
//        if(rfid.length() == 16){
//            char[] chars =rfid.toCharArray();
//            char[] new_chars = new char[16];
//            int j = 0;
//            for(int i = chars.length-1;i > 0;){
//                int k = i - 1;
//                new_chars[j++] = chars[k];
//                new_chars[j++] = chars[i];
//                i = i-2;
//            }
//            return new String(new_chars);
//        }
//        return "";
//    }
//}
