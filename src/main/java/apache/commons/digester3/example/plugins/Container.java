package apache.commons.digester3.example.plugins;
import java.util.List;
import java.util.LinkedList;

public class Container
    implements Widget
{
    private LinkedList<Widget> children = new LinkedList<Widget>();

    public Container()
    {
    }

    public void addChild( Widget child )
    {
        children.add( child );
    }

    public List<Widget> getChildren()
    {
        return children;
    }
}