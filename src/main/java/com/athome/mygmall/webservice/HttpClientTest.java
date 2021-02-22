package com.athome.mygmall.webservice;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class HttpClientTest {
    /**
     * 访问服务
     *
     * @param wsdl   wsdl地址
     * @param ns     命名空间
     * @param method 方法名
     * @param list   参数
     * @return
     * @throws Exception
     */
    public synchronized static String accessService(String wsdl, String ns, String method, List<String> list, String result) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();

        //拼接参数
        for (int i = 0; i < list.size(); i++) {
            stringBuffer.append("<arg" + i + ">" + list.get(i) + "</arg" + i + ">");
        }

        String soapResponseData = "";
        //拼接SOAP
        StringBuffer soapRequestData = new StringBuffer("");

//        String arg0="{\"system\": \"BDMSigner\", \"keyCode\": \"SVP26FTvzwMTMWDVDP9jZw==\",\"change\": \"2018-05-12 18:17:05\"}";
//        String arg1="TB_ORG_POSITION";
//        String arg2="2018-05-12 18:17:05";
//        String arg3="2018-05-12 18:17:05";
//        String arg4="1";


        soapRequestData.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.info.employee.staffing.talentbase.neusoft.com/\">");
        soapRequestData.append("<soapenv:Header/>");
        soapRequestData.append("<soapenv:Body>");
        soapRequestData.append("<ser:" + method + ">");
        soapRequestData.append(stringBuffer);
//        soapRequestData.append("<arg0>"+arg0+"</arg0>");
//        soapRequestData.append("<arg1>"+arg1+"</arg1>");
//        soapRequestData.append("<arg2>"+arg2+"</arg2>");
//        soapRequestData.append("<arg3>"+arg3+"</arg3>");
//        soapRequestData.append("<arg4>"+arg4+"</arg4>");
        soapRequestData.append("</ser:" + method + ">");
        soapRequestData.append("</soapenv:Body>" + "</soapenv:Envelope>");
        PostMethod postMethod = new PostMethod(wsdl);
        // 然后把Soap请求数据添加到PostMethod中
        byte[] b = null;
        InputStream is = null;
        try {
            b = soapRequestData.toString().getBytes("utf-8");
            is = new ByteArrayInputStream(b, 0, b.length);
            RequestEntity re = new InputStreamRequestEntity(is, b.length, "text/xml; charset=UTF-8");
            postMethod.setRequestEntity(re);
            HttpClient httpClient = new HttpClient();
            int status = httpClient.executeMethod(postMethod);

            if (status == 200) {
                soapResponseData = getMesage(postMethod.getResponseBodyAsString(), result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return soapResponseData;
    }

    public static String getParam(Map<String, String> params) {
        String param = "";
        if (params != null) {
            Iterator<String> it = params.keySet().iterator();
            while (it.hasNext()) {
                String str = it.next();
                param += "<" + str + ">";
                param += params.get(str);
                param += "</" + str + ">";
            }
        }
        return param;
    }

    public static String getMesage(String soapAttachment, String result) {
        if (result == null) {
            return null;
        }
        if (soapAttachment != null && soapAttachment.length() > 0) {
            int begin = soapAttachment.indexOf("<return>");
            begin = soapAttachment.indexOf(">", begin);
            int end = soapAttachment.indexOf("</return>");
            String str = soapAttachment.substring(begin + 1, end);
            str = str.replaceAll("<", "<");
            str = str.replaceAll(">", ">");
            return str;
        } else {
            return "";
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {

            Date useDate = new Date();
            SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String beginDate = format0.format(useDate);
            String endDate = format0.format(useDate);
            List list = new ArrayList();
//            list.add(SecurityUtil.getHcmKey("BDMSigner", "yhn@BDM123", "mix@CODE"));
            list.add("TB_ORG_POSITION");
            list.add(beginDate);
            list.add(endDate);
            list.add("1");
            String wsdl = "http://testhcm.ycgwl.com:8070/hcm/service/MDMWebService";
            String ns = "http://service.info.employee.staffing.talentbase.neusoft.com/";
            String method = "getHrInfo";
            String response = accessService(wsdl, ns, method, list, "result");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
