# apache-commons-digester-example
apache commons digester example

外部依赖
Digester2.0组件依赖于以下标准库:
•	XML Parser compatible with the JAXP/1.3 specification. Compatible implementations are included in JDKs 1.5 and above.
It is also dependent on a compatible set of Apache Commons library components. The recommended dependency set is: 
Recommended Dependency Set
Digester	+Logging 1.1.1	+BeanUtils 1.8.3
It is also possible to use Logging 1.0.x or BeanUtils 1.8.0 instead.
前言
在许多处理XML格式数据的应用环境中, 如果能够以“事件驱动”的方式来处理XML文档，比如，当识别出特定的XML元素时，创建特定的对象（或者调用现有对象的方法），这对于应用程序开发来说，是非常有用的；
熟悉以SAX(Simple API for XML Parsing)方式来处理XML文档的开发人员会认识到，Digester为SAX事件提供了更高层次，对开发者更加友好的接口，它隐藏了大部分导航XML元素层次结构的细节，以便于开发者更加专注于要执行的处理操作；
在使用Digester的时候, 会涉及以下几个基本步骤:
•	创建一个 org.apache.commons.digester3.Digester 类的实例对象. 只要我们已经完成之前的XML解析操作并且不在多个线程中同时使用同一个Digester对象，那么就可以安全的重复使用我们预先创建的Digester实例；不过重用Digester实例并不是非常推荐，最好每个XML解析对应一个单独的Digester实例；
•	为Digester实例配置属性值，通过配置属性值，我们可以改变Digester 的解析行为，具体有哪些属性值可以配置，待会会介绍；
•	可选的, 可以将我们的一些初始对象push到Digester栈里；.
•	在输入的XML文档中，给所有需要触发规则（rule）处理的元素匹配模式（pattern）注册规则;针对任何一个模式，你可以注册任意数量的规则；如果一个模式对应多个规则，begin和body事件方法会按照它们注册的顺序依次执行，而end事件方法是倒序执行的；
•	 调用digester.parse()方法，该方法需要传入XML文件的引用作为参数，该参数支持多种格式的文件流；另外需要注意的是，该方法会抛出IOException or SAXException异常，以及各种可能的在解析时的异常，如NoSuchMethodException、IllegalAccessException…
Alternatively a Digester may be used as a sax event hander, as follows:
•	Create an instance of a sax parser (using the JAXP APIs or otherwise).
•	Set any desired configuration properties on that parser object.
•	Create an instance of org.apache.commons.digester3.Digester.
•	Optionally, push any desired initial object(s) onto the Digester's object stack.
•	Register patterns and rules with the digester instance.
•	Call parser.parse(inputSource, digester).
For example code, see the usage examples, and the FAQ . 


XML命名空间解析
对于没有使用命名空间的xml来说，Digester默认的处理机制已经足够满足我们的需求了。但是当XML文档使用命名空间的时候，对于不同命名空间的元素来说，有时候我们希望使用不同的规则去解析它。 
Digester 没有提供对命名空间的完全支持, 但已经足够完成大多数任务了. 开启Digester的命名空间支持只需要以下几个步骤即可:
1.	通过配置以下属性值，告诉 Digester，需要开启命名空间解析: 
    digester.setNamespaceAware( true );
    
2.	声明接下来的规则关联的命名空间，注意我们这里没有指明任何前缀，XML文档的作者是可以使用任何他们喜欢的前缀的
   digester.setRuleNamespaceURI( "http://www.mycompany.com/MyNamespace" );
    
3.	添加该命名空间下的规则, 通常会调用 addObjectCreate() or addSetProperties()这类方法. 注意，这里匹配的模式是不需要加上前缀的: 
    digester.addObjectCreate( "foo/bar", "com.mycompany.MyFoo" );
    digester.addSetProperties( "foo/bar");
    
4.	重复2、3步骤，解析其它命名空间的元素.
如下，是一个示例，使用以上步骤即可完成解析:
<m:foo
   xmlns:m="http://www.mycompany.com/MyNamespace"
   xmlns:y="http://www.yourcompany.com/YourNamespace">

  <m:bar name="My Name" value="My Value"/>

  <y:bar id="123" product="Product Description"/>

</m:foo>
由于我们给Digester指定的命名空间为http://www.mycompany.com/MyNamespace，所以以上xml只有第一个bar会被解析出来。
使用命名空间前缀用于模式匹配
当一个命名空间下的规则集合与另一个命名空间的规则集合相互独立的话，使用带命名空间的规则非常有用，但是当我们的规则逻辑需要使用到不同命名空间下的元素时，那么使用带命名空间前缀的模式将会是一个更好的策略；
很简单，我们只需要，设置NamespaceAware 属性为false，然后在模式前面带上命名空间前缀即可。
举个例子, (将 NamespaceAware 设为false), 模式 m:bar' 只会匹配命名空间前缀为m的bar元素.
Limitations of Digester Namespace support
Digester does not provide general "xpath-compliant" matching; only the namespace attached to the last element in the match path is involved in the matching process. Namespaces attached to parent elements are ignored for matching purposes.
错误排查
调试异常
Digester 是基于 SAX 开发的. Digestion 会抛出两种类型的 Exception: 
•	java.io.IOException
•	org.xml.sax.SAXException
第一个异常很少会抛出，且该异常众所周知。 通常我们遇到最多的是第二个异常，当SAX解析无法完成的时候会抛出该异常，所以熟悉SAX的错误处理方式对诊断SAXException很有帮助。 
诊断 SAX Exceptions
当一个SAX 解析器 遇到xml问题时 (有时候，会在遇到xml问题之后)，会抛出SAXParseException异常. 该异常是SAXException 的子类，并且包含了一些额外信息（哪里出错，出了什么错误），如果我们捕获了这类异常，那么就可以明确知道问题是XML引起的，而不是Digester或者我们的解析规则。通常来说，捕获该异常并记录详细信息到日志对诊断错误非常有帮助。
一般情况下 SAXException 异常内部会组合另一个异常，换句话说，就是当Digester遇到异常的时候，会首先将该异常封装成一个SAXException异常，然后将该SAXException重新抛出 。所以，捕获SAXException异常，并仔细检查被封装的内部异常，有助于排查错误；
错误示例：
org.xml.sax.SAXParseException; lineNumber: 4; columnNumber: 41; 元素类型 "bar" 必须后跟属性规范 ">" 或 "/>"。
	at com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.createSAXParseException(ErrorHandlerWrapper.java:198)
	at com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.fatalError(ErrorHandlerWrapper.java:177)




