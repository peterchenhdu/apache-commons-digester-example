/*
 * File Name: Main2.java
 * Description: 
 * Author: http://www.cnblogs.com/chenpi/
 * Create Date: 2017年6月4日
 */
package apache.commons.digester3.example.simpletest;

import java.io.IOException;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import apache.commons.digester3.example.pojo.ServletBean;

/**
 * 
 * @author http://www.cnblogs.com/chenpi/
 * @version 2017年6月4日
 */

public class WebMain
{

    public static void main(String[] args)
    {
        try
        {
            // 1、创建Digester对象实例
            Digester digester = new Digester();

            // 2、配置属性值
            digester.setValidating(false);

            // 3、push对象到对象栈

            // 4、设置匹配模式、规则
            digester.addObjectCreate("web-app/servlet", "apache.commons.digester3.example.pojo.ServletBean");
            digester.addCallMethod("web-app/servlet/servlet-name", "setServletName", 0);
            digester.addCallMethod("web-app/servlet/servlet-class", "setServletClass", 0);
            digester.addCallMethod("web-app/servlet/init-param", "addInitParam", 2);
            digester.addCallParam("web-app/servlet/init-param/param-name", 0);
            digester.addCallParam("web-app/servlet/init-param/param-value", 1);

            // 5、开始解析
            ServletBean servletBean = digester
                .parse(ExampleMain.class.getClassLoader().getResourceAsStream("web.xml"));

            // 6、打印解析结果
            System.out.println(servletBean.getServletName());
            System.out.println(servletBean.getServletClass());
            for(String key : servletBean.getInitParams().keySet()){
                System.out.println(key + ": " + servletBean.getInitParams().get(key));
            }

        }
        catch (IOException e)
        {

            e.printStackTrace();
        }
        catch (SAXException e)
        {

            e.printStackTrace();
        }

    }
}
