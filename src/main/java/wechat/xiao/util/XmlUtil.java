package com.chehaha.api.wechat.xiao.util;


import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Created by DK on 2017/11/15.
 */
public class XmlUtil {


    /**
     * 解析xml
     * @param file
     * @param c
     * @param <T>
     * @return
     */
//    public static <T>  T toObject(File file, Class<T> c){
//        //这样创建XStream实例时，上面那3个jar包必须都有
//        XStream xstream = new XStream();
//        xstream.processAnnotations(c);//应用注解
//        xstream.autodetectAnnotations(true);//自动检测注解
//       return (T) xstream.fromXML(file);
//    }



    /**
     * JavaBean转换成xml
     * 默认编码UTF-8
     *
     * @param obj
     * @return
     */
    public static String parse(Object obj) {
        return parse(obj, "UTF-8");
    }

    /**
     * JavaBean转换成xml
     *
     * @param obj
     * @param encoding
     * @return
     */
    public static String parse(Object obj, String encoding) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * xml转换成JavaBean
     *
     * @param xml
     * @param c
     * @return
     * @throws JAXBException 
     */
    @SuppressWarnings("unchecked")
    public static <T> T toObject(String xml, Class<T> c) throws JAXBException {
    	JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        T t = (T) unmarshaller.unmarshal(new StringReader(xml));
        return t;

    }

}
