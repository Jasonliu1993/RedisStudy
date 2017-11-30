package com.redis;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
//        assertTrue( true );
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("name", "JasonTest");
        String value = jedis.get("name");
        System.out.println(value);
        jedis.close();
    }

    public void testPoolDemo() {
        //设置连接池对象
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接池
        config.setMaxTotal(30);
        //设置最大空闲连接数
        config.setMaxIdle(10);

        //获取连接池
        JedisPool jedisPool = new JedisPool(config, "localhost", 6379);

        //获取核心对象
        Jedis jedis = null;

        try {
            //通过连接池回的连接
            jedis = jedisPool.getResource();

            //设置数据
            jedis.set("name","JasonTest");

            //获取数据
            String value = jedis.get("name");

            System.out.println(value);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
            if (jedisPool!= null) {
                jedisPool.destroy();
            }
        }

    }
}
