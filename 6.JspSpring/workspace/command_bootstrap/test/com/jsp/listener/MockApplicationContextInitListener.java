package com.jsp.listener;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jsp.context.ApplicationContext;

public class MockApplicationContextInitListener {
	
	public void contextInitialized(String beanConfigXml) {
		
		if(beanConfigXml == null) return;
		
		beanConfigXml = beanConfigXml.replace("classpath:", "res/").replace("/", File.separator);
         
		//System.out.println(beanConfigXml);
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			Document document = documentBuilder.parse(beanConfigXml);
        	
			Element root = document.getDocumentElement();
        	 
        	//System.out.println(root.getTagName());
        	if(root == null || !root.getTagName().equals("beans")) return;
        	 
        	//<beans><bean id="identify" class="class Type"></bean></beans>
        	NodeList beans = root.getElementsByTagName("bean");
        	
        	Map<String, Object> applicationContext = ApplicationContext.getApplicationContext();
        	
        	for(int i = 0; i < beans.getLength(); i++) {
        		//System.out.println(beans.item(i).getNodeName());
        		Node bean = beans.item(i);
        		if(bean.getNodeType() == Node.ELEMENT_NODE) {
        			Element ele = (Element) bean;
        			String id = ele.getAttribute("id");
        			String classType = ele.getAttribute("class");
        			//System.out.printf("id : %s, class = %s\n", id, classType);
        			 
        			//map instance put
        			Class<?> cls = Class.forName(classType);
        			Object targetObj = cls.newInstance();	//single tone
        			applicationContext.put(id, targetObj);
        			//System.out.println("id : " + id + ", class : " + targetObj);
        		}
        	}
        	 
        	//????????? ??????
        	for(int i = 0; i < beans.getLength(); i++) {
        		Node bean = beans.item(i);
    			if(bean.getNodeType() == Node.ELEMENT_NODE) {
    				Element eleBean = (Element) bean;

    				NodeList properties = bean.getChildNodes();
    				for(int j = 0; j < properties.getLength(); j++) {
    					Node property = properties.item(j);
	 					if(!property.getNodeName().equals("property")) continue;
        				 
 						if(property.getNodeType() == Node.ELEMENT_NODE) {
							Element ele = (Element) property;
							String name = ele.getAttribute("name");
        					String ref = ele.getAttribute("ref-value");
        					//System.out.printf("name = %s, ref-value = %s\n", name, ref);
        					
        					String setMethodName = "set" 
        							+ name.substring(0, 1).toUpperCase() 
        							+ name.substring(1);
        					String className = eleBean.getAttribute("class");	//bean??? ?????? eleBean(name)??? class??? ?????????.
        					Class<?> classType = Class.forName(className);
        					
        					Method[] methods = classType.getMethods();
        					for(Method method : methods) {
        						//????????? ?????? ??????
        						if(method.getName().equals(setMethodName)) {
        							method.invoke(applicationContext.get(eleBean.getAttribute("id"))
        									, applicationContext.get(ref));
        							
        							System.out.println("[invoke] " 
        									+ applicationContext.get(eleBean.getAttribute("id")) 
        									+ " : " 
        									+ applicationContext.get(ref));
        						}
        					}
 						}	 
    				}
    			}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
