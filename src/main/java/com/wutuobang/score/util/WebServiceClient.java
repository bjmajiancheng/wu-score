package com.wutuobang.score.util;

import com.alibaba.csb.ws.sdk.DumpSoapUtil;
import com.alibaba.csb.ws.sdk.WSClientSDK;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by majiancheng on 2018/6/11.
 */
public class WebServiceClient {

    private String apiName;

    private String apiVersion;

    private String ak;

    private String sk;

    public void init() throws Exception {
        String ns = "http://service.webinterface.yzym.si.sl.neusoft.com/";
        QName serviceName = new QName(ns, "NeuWebService");
        QName portName = new QName(ns, "NeuWebServicePortType");
        Service service = Service.create(serviceName);
        service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, apiName);
        Dispatch<SOAPMessage> dispatch = service.createDispatch(portName, SOAPMessage.class, Service.Mode.MESSAGE);
        String req = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.webinterface.yzym.si.sl.neusoft.com/\">\n"
                + "   <soapenv:Header/>\n" + "   <soapenv:Body>\n" + "      <ser:RsResidentJFRDBusinessRev>\n"
                + "         <!--ticket:-->\n" + "         <ser:arg0>NEUSERVICE_GGFW_TICKET_12</ser:arg0>\n"
                + "         <!--buzzNumb:-->\n" + "         <ser:arg1>TJZSYL_JFRDXT_001</ser:arg1>\n"
                + "         <!--sender:-->\n" + "         <ser:arg2>JFRDXT</ser:arg2>\n" + "         <!--reciver:-->\n"
                + "         <ser:arg3>TJZSYL</ser:arg3>\n" + "         <!--operatorName:-->\n"
                + "         <ser:arg4>经办人校验测试操作员</ser:arg4>\n" + "         <!--content:-->\n"
                + "         <ser:arg5><![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ROOT><QUERY_PRAMS><idNumber>12010719660201662X</idNumber><busType>1</busType></QUERY_PRAMS></ROOT>]]></ser:arg5>\n"
                + "      </ser:RsResidentJFRDBusinessRev>\n" + "   </soapenv:Body>\n" + "</soapenv:Envelope>";

        InputStream is = new ByteArrayInputStream(req.getBytes());
        SOAPMessage request = MessageFactory.newInstance().createMessage(null, is);
        dispatch = WSClientSDK.bind(dispatch, ak, sk, "juZhuZhengJiFen", apiVersion);
        System.out.println("Send out the request: " + req);
        SOAPMessage reply = dispatch.invoke(request);
        if (reply != null)
            System.out.println("Response from invoke:" + DumpSoapUtil.dumpSoapMessage("response", reply));
        else System.out.println("Response from invoke is null");
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }
}
