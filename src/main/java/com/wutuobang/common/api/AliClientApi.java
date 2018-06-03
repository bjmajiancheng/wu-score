package com.wutuobang.common.api;

import com.alibaba.csb.ws.sdk.AxisCallWrapper;
import com.alibaba.fastjson.JSON;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.namespace.QName;
import java.rmi.RemoteException;

/**
 * Created by majiancheng on 2018/6/3.
 */
public class AliClientApi {

    private String apiName;

    private String apiVersion;

    private String ak;

    private String sk;

    private Call call;

    public void init() {
        try{
            Service service = new Service();
            //构造封装Call对象
            this.call = AxisCallWrapper.createCallWrapper(service, ak, sk, apiName, apiVersion);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String execute() {
        //或者使用WSParams进行参数设置
        // 然后，使用封装Call对象进行方法调用
        call.setTargetEndpointAddress("http://localhost:9081/PING/vcsb.ws/ws2ws");
        call.setOperationName(new QName("http://hc.wsprocess.csb.alibaba.com/", "ping"));

        call.addParameter("arg0", // 设置要传递的参数
                org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);

        Object[] args = { "wiseking" };

        Object ret = null;

        try {
            ret = call.invoke(args);
            System.out.println("ret=" + ret);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return JSON.toJSONString(ret);
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
