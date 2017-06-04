/*
 * File Name: ServletBean.java
 * Description: 
 * Author: http://www.cnblogs.com/chenpi/
 * Create Date: 2017年6月4日
 */
package apache.commons.digester3.example.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author    http://www.cnblogs.com/chenpi/
 * @version   2017年6月4日
 */

public class ServletBean
{

    private String servletName;
    private String servletClass;
    private Map<String, String> initParams = new HashMap<String, String>();
    
    
    public void addInitParam(String paramName, String paramValue){
        initParams.put(paramName, paramValue);
    }
    /**
     * @return the servletName
     */
    public String getServletName()
    {
        return servletName;
    }
    /**
     * @param servletName the servletName to set
     */
    public void setServletName(String servletName)
    {
        this.servletName = servletName;
    }
    /**
     * @return the servletClass
     */
    public String getServletClass()
    {
        return servletClass;
    }
    /**
     * @param servletClass the servletClass to set
     */
    public void setServletClass(String servletClass)
    {
        this.servletClass = servletClass;
    }
    /**
     * @return the initParams
     */
    public Map<String, String> getInitParams()
    {
        return initParams;
    }
    /**
     * @param initParams the initParams to set
     */
    public void setInitParams(Map<String, String> initParams)
    {
        this.initParams = initParams;
    }
}
