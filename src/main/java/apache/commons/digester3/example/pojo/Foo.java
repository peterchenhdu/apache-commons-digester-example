package apache.commons.digester3.example.pojo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author http://www.cnblogs.com/chenpi/
 * @version 2017年6月3日
 */
public class Foo
{
    private String name;
    private List<Bar> barList = new ArrayList<Bar>();

    public void addBar(Bar bar)
    {
        barList.add(bar);
    }

    public Bar findBar(int id)
    {
        for (Bar bar : barList)
        {
            if (bar.getId() == id)
            {
                return bar;
            }
        }
        return null;
    }

    public Iterator<Bar> getBars()
    {
        return barList.iterator();
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the barList
     */
    public List<Bar> getBarList()
    {
        return barList;
    }

    /**
     * @param barList the barList to set
     */
    public void setBarList(List<Bar> barList)
    {
        this.barList = barList;
    }

}
