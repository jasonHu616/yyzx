package com.hy.yyzx.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.dom4j.DocumentHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.*;

public class XmlUtil {

    public static String objToXml(Object obj) throws Exception {
        StringWriter sw = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                Boolean.TRUE);
        // 将对象转换成输出流形式的xml
        marshaller.marshal(obj, sw);
        String xml = sw.toString();
        if(xml == null) return null;
        xml = xml.replaceAll("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
        sw.close();
        return xml;
    }

    /**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, String> objectToMap(Object obj, String exclude) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if(fieldName.indexOf(exclude) > -1) continue;
            String value = String.valueOf(field.get(obj));
            map.put(fieldName, value);
        }
        return map;
    }

    public static List<String> objectToList(Object obj, String exclude) throws IllegalAccessException {
        List<String> list = new ArrayList<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if(fieldName.indexOf(exclude) > -1) continue;
            String value = String.valueOf(field.get(obj));
            list.add(fieldName + "=" + value + "&");
        }
        return list;
    }

    public static String mapToxml(Map<String, String> map){
        StringBuilder builder = new StringBuilder();
        builder.append("<xml>");
        if(map != null && map.size() > 0){
            Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
            while (iter.hasNext()){
                Map.Entry<String, String> entry = iter.next();
                builder.append("<" + entry.getKey() + ">" + entry.getValue() + "</" + entry.getKey() + ">");
            }
        }
        builder.append("</xml>");
        return builder.toString();
    }

    public static Map<String, String> xmlToMap(String xml) throws Exception{
        Map<String, String> map = new HashMap();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        Document document = builder.parse(is);
        NodeList allNodes = document.getFirstChild().getChildNodes();

        for(int i = 0; i < allNodes.getLength(); ++i) {
            Node node = allNodes.item(i);
            if (node instanceof Element) {
                map.put(node.getNodeName(), node.getTextContent());
            }
        }

        return map;
    }

    public static JSONObject xmlToJson(String xml) {
        JSONObject js = new JSONObject();
        org.dom4j.Document doc = null;
        try {
            // 将字符串转为XML
            doc = DocumentHelper.parseText(xml);
            // 获取根节点
            org.dom4j.Element rootElt = doc.getRootElement();
            // 拿到根节点的名称
            String resultCode = rootElt.elementTextTrim("resultCode");
            js.put("resultCode", resultCode);
            // 获取根节点下的子节点head
            Iterator iter = rootElt.elementIterator("orderInfo");
            // 遍历head节点
            while (iter.hasNext()) {
                org.dom4j.Element recordEle = (org.dom4j.Element) iter.next();
                String reg_num = recordEle.elementTextTrim("reg_num");
                String prescriptionIds = recordEle.elementTextTrim("prescriptionIds");
                String orderid = recordEle.elementTextTrim("orderid");
                String message = recordEle.elementTextTrim("message");

                js.put("reg_num", reg_num);
                js.put("prescriptionIds", prescriptionIds);
                js.put("orderid", orderid);
                js.put("message", message);
            }

        } catch (Exception e) {
            return null;
        }
        return js;
    }

}
