/*
 * File Name: Main.java
 * Description: 
 * Author: http://www.cnblogs.com/chenpi/
 * Create Date: 2017年6月3日
 */
package apache.commons.digester3.example.simpletest;

import java.io.IOException;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.Rule;
import org.apache.commons.digester3.SetNextRule;
import org.xml.sax.SAXException;

import apache.commons.digester3.example.pojo.Bar;
import apache.commons.digester3.example.pojo.Foo;

/**
 * 
 * @author http://www.cnblogs.com/chenpi/
 * @version 2017年6月3日
 */

public class ExampleMain
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
            // digester.push(new Foo());

            // 4、设置匹配模式、规则
            digester.addObjectCreate("foo", Foo.class);
            digester.addSetProperties("foo");
            digester.addObjectCreate("foo/bar", Bar.class);
            digester.addSetProperties("foo/bar");
            
            Rule rule = new SetNextRule("addBar",Bar.class);
            digester.addRule("foo/bar", rule );
            
            //digester.addSetNext("foo/bar", "addBar", Bar.class.getName());

            
            // 5、开始解析
            Foo foo = digester
                .parse(ExampleMain.class.getClassLoader().getResourceAsStream("example.xml"));

            // 6、打印解析结果
            System.out.println(foo.getName());
            for (Bar bar : foo.getBarList())
            {
                System.out.println(bar.getId() + "," + bar.getTitle());
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
