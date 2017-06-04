/*
 * File Name: AppTest.java
 * Description: 
 * Author: http://www.cnblogs.com/chenpi/
 * Create Date: 2017年5月8日
 */
package apache.commons.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author http://www.cnblogs.com/chenpi/
 * @version 2017年5月8日
 */

public class AppTest
{

    /**
     * 
     * 
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        System.out.println("setUpBeforeClass");
    }

    /**
     * 
     * 
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
        System.out.println("tearDownAfterClass");
    }

    /**
     * 
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        System.out.println("setUp");
    }

    /**
     * 
     * 
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception
    {
        System.out.println("tearDown");
    }

    /**
     * Test method for
     * {@link apache.commons.digester3.example.App#add(int, int)}.
     */
    @Test
    public void testAdd()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link apache.commons.digester3.example.App#sub(int, int)}.
     */
    @Test
    public void testSub()
    {
        fail("Not yet implemented");
    }

}
