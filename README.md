# apache-commons-digester-example
apache commons digester example

# 外部依赖
Digester2.0组件依赖于以下标准库:
•	XML Parser compatible with the JAXP/1.3 specification. Compatible implementations are included in JDKs 1.5 and above.
It is also dependent on a compatible set of Apache Commons library components. The recommended dependency set is: 
Recommended Dependency Set
Digester	+Logging 1.1.1	+BeanUtils 1.8.3
It is also possible to use Logging 1.0.x or BeanUtils 1.8.0 instead.
# 前言
	在许多处理XML格式数据的应用环境中, 如果能够以“事件驱动”的方式来处理XML文档，比如，当识别出特定的XML元素时，创建特定的对象（或者调用现有对象的方法），这对于应用程序开发来说，是非常有用的；
熟悉以SAX(Simple API for XML Parsing)方式来处理XML文档的开发人员会认识到，Digester为SAX事件提供了更高层次，对开发者更加友好的接口，它隐藏了大部分导航XML元素层次结构的细节，以便于开发者更加专注于要执行的处理操作；
在使用Digester的时候, 会涉及以下几个基本步骤:
•	创建一个 org.apache.commons.digester3.Digester 类的实例对象. 只要我们已经完成之前的XML解析操作并且不在多个线程中同时使用同一个Digester对象，那么就可以安全的重复使用我们预先创建的Digester实例；不过重用Digester实例并不是非常推荐，最好每个XML解析对应一个单独的Digester实例；
•	为Digester实例配置属性值，通过配置属性值，我们可以改变Digester 的解析行为，具体有哪些属性值可以配置，待会会介绍；
•	可选的, 可以将我们的一些初始对象push到Digester栈里；.
•	在输入的XML文档中，给所有需要触发规则（rule）处理的元素匹配模式（pattern）注册规则;针对任何一个模式，你可以注册任意数量的规则；如果一个模式对应多个规则，begin和body事件方法会按照它们注册的顺序依次执行，而end事件方法是倒序执行的；
•	调用digester.parse()方法，该方法需要传入XML文件的引用作为参数，该参数支持多种格式的文件流；另外需要注意的是，该方法会抛出IOException or SAXException异常，以及各种可能的在规则解析处理时遇到的异常，如NoSuchMethodException、IllegalAccessException…
Alternatively a Digester may be used as a sax event hander, as follows:
•	Create an instance of a sax parser (using the JAXP APIs or otherwise).
•	Set any desired configuration properties on that parser object.
•	Create an instance of org.apache.commons.digester3.Digester.
•	Optionally, push any desired initial object(s) onto the Digester's object stack.
•	Register patterns and rules with the digester instance.
•	Call parser.parse(inputSource, digester).
For example code, see the usage examples, and the FAQ . 


