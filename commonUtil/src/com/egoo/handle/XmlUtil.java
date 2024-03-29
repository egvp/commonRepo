package com.egoo.handle;

import com.alibaba.fastjson.JSONObject;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


public class XmlUtil {
    
	public static Logger logger = Logger.getLogger(XmlUtil.class);
    
	public static String getEnv() {
        String env = "";
        try {
            env = System.getProperty("gvp.config.path");
            if( env == null){
            	env = "E:\\GIT_HOME\\commonRepo\\commonUtil\\";
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            env = "";
        }
        
        return env;
    }
	
	public static JSONObject parseCommonXML(String filename) {
		String eleName = null;
        String eleValue = null;
        JSONObject jsonObj = new JSONObject();
        
        try {
        	File f = new File(filename); 
        	SAXReader reader = new SAXReader();
            Document doc = reader.read(f);
            Element root = doc.getRootElement();
            List<Element> elementList = root.elements();
            Iterator<Element> it = elementList.iterator();
            while (it.hasNext()) {
                Element element = it.next();
                eleName = element.getName();
                eleValue = element.getStringValue();
                jsonObj.put(eleName, eleValue);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println(jsonObj.isEmpty());
            logger.error(e.toString());
        }
        
        return jsonObj;
    }

	public static String getUUID(){
	    UUID uuid=UUID.randomUUID();
	    String str = uuid.toString(); 
	    String uuidStr=str.replace("-", "");
	    return uuidStr;
	}

    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	String configPath = "E:\\GIT_HOME\\commonRepo\\commonUtil\\";
    	PropertyConfigurator.configure(getEnv() + "log4j.properties");
    	String fileName =getEnv() + "config.xml";
        JSONObject result = parseCommonXML(fileName);
        logger.info(result);
        String uuid = getUUID();
        System.out.println("uuid is : " + uuid);
    }

}
