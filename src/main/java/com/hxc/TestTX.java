package com.hxc;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * Created by IntelliJ IDEA.
 * User: hxc
 * Date: 2021/3/12
 * Time: 21:13
 */
public class TestTX {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","world");
        jsonObject.put("name","hxc");
        // 开启事务
        Transaction multi = jedis.multi();
        String result = jsonObject.toJSONString();
//        jedis.watch(result);
        try{
            multi.set("user1",result);
            multi.set("user2",result);
            int i = 1/0; // 代码异常，执行失败
            multi.exec();
        }catch (Exception e){
            multi.discard();
            e.printStackTrace();
        }finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();
        }
    }
}
