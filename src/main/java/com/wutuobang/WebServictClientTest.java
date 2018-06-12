package com.wutuobang;

import javax.xml.soap.*;
import java.io.ByteArrayInputStream;
import java.util.*;

/**
 * Created by majiancheng on 2018/6/11.
 */
public class WebServictClientTest {

    public static void main(String[] args) {
        StringBuffer xml = new StringBuffer();
        xml.append("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + "   <soap:Body>\n"
                + "      <ns1:RsResidentJFRDBusinessRevResponse xmlns:ns1=\"http://service.webinterface.yzym.si.sl.neusoft.com/\">\n"
                + "         <return xmlns=\"http://service.webinterface.yzym.si.sl.neusoft.com/\"><![CDATA[<ROOT>\n"
                + "    <appCode>0</appCode>\n" + "    <unitName>转移单位1</unitName>\n" + "</ROOT>]]></return>\n"
                + "      </ns1:RsResidentJFRDBusinessRevResponse>\n" + "   </soap:Body>\n" + "</soap:Envelope>");

        try {
            System.out.println("开始解析 xml.....");

            SOAPMessage msg = formatSoapString(xml.toString());
            SOAPBody body = msg.getSOAPBody();

            Iterator<SOAPElement> iterator = body.getChildElements();
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            List<Map<String, String>> orgList = printBody(iterator, null, list);

            System.out.println(orgList);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String, String>> printBody(Iterator<SOAPElement> iterator, String side,
            List<Map<String, String>> list) {

        while (iterator.hasNext()) {
            SOAPElement element = iterator.next();
            System.out.println(element.getNodeName());

            if ("DATA".equals(element.getNodeName())) {
                Iterator<SOAPElement> it = element.getChildElements();
                Map<String, String> infoMap = new HashMap<String, String>();
                SOAPElement el = null;
                String pk_corp = ""; // 公司主键
                String unitcode = ""; // 公司编码
                String unitname = ""; // 公司名称
                String fathercorp = ""; // 上级公司ID
                String ts = ""; // 最新更新时间
                String corplevel = ""; // 公司层级
                String isseal = ""; // 是否封存
                while (it.hasNext()) {
                    el = it.next();
                    if (el.getNodeName().equals("pk_corp")) {
                        pk_corp = el.getValue();
                        infoMap.put("pk_corp", pk_corp);
                    }
                    if (el.getNodeName().equals("unitcode")) {
                        unitcode = el.getValue();
                        infoMap.put("unitcode", unitcode);
                    }
                    if (el.getNodeName().equals("unitname")) {
                        unitname = el.getValue();
                        infoMap.put("unitname", unitname);
                    }
                    if (el.getNodeName().equals("fathercorp")) {
                        fathercorp = el.getValue();
                        infoMap.put("fathercorp", fathercorp);
                    }
                    if (el.getNodeName().equals("ts")) {
                        ts = el.getValue();
                        infoMap.put("ts", ts);
                    }
                    if (el.getNodeName().equals("corplevel")) {
                        corplevel = el.getValue();
                        infoMap.put("corplevel", corplevel);
                    }
                    if (el.getNodeName().equals("isseal")) {
                        isseal = el.getValue();
                        infoMap.put("isseal", isseal);
                    }
                }
                list.add(infoMap);
            }
            if (null == element.getValue() && element.getChildElements().hasNext()) {
                printBody(element.getChildElements(), side + "-----", list);
            }
        }
        return list;
    }

    /**
     * 把soap字符串格式化为SOAPMessage
     *
     * @param soapString
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static SOAPMessage formatSoapString(String soapString) {
        MessageFactory msgFactory;
        try {
            msgFactory = MessageFactory.newInstance();
            SOAPMessage reqMsg = msgFactory
                    .createMessage(new MimeHeaders(), new ByteArrayInputStream(soapString.getBytes("UTF-8")));
            reqMsg.saveChanges();
            return reqMsg;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

