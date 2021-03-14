package com.hxc;

import redis.clients.jedis.Jedis;

/**
 * Created by IntelliJ IDEA.
 * User: hxc
 * Date: 2021/3/12
 * Time: 21:04
 */
public class TestPing {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.println(jedis.ping());
    }
}
