//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wutuobang.common.client;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;

public class ShardJedisClient implements JedisCommands {
    private static final Logger logger = LoggerFactory.getLogger(ShardJedisClient.class);
    private ShardedJedisPool jedisPool;
    private long lockTimeOut = 200L;

    public ShardJedisClient(ShardedJedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public ShardJedisClient(JedisPoolConfig config, List<JedisShardInfo> shards) {
        this.jedisPool = new ShardedJedisPool(config, shards);
    }

    public String set(String key, String value) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.set(key, value);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public String setex(String key, String value, int seconds) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.setex(key, seconds, value);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public String get(String key) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.get(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public Boolean exists(String key) {
        Boolean result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.exists(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public String type(String key) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.type(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public Long expire(String key, int seconds) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.expire(key, seconds);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Long expireAt(String key, long unixTime) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.expireAt(key, unixTime);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Long ttl(String key) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.ttl(key);
        } catch (JedisConnectionException var15) {
            logger.error(var15.getMessage(), var15);
            logger.error(var15.getMessage(), var15);
        } catch (Exception var16) {
            logger.error(var16.getMessage(), var16);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Boolean setbit(String key, long offset, boolean value) {
        Boolean result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.setbit(key, offset, value);
        } catch (Exception var16) {
            logger.error(var16.getMessage(), var16);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var15) {
                    logger.error(var15.getMessage(), var15);
                }
            }

        }

        return result;
    }

    public Boolean getbit(String key, long offset) {
        Boolean result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.getbit(key, offset);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Long setrange(String key, long offset, String value) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.setrange(key, offset, value);
        } catch (Exception var16) {
            logger.error(var16.getMessage(), var16);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var15) {
                    logger.error(var15.getMessage(), var15);
                }
            }

        }

        return result;
    }

    public String getrange(String key, long startOffset, long endOffset) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.getrange(key, startOffset, endOffset);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public String getSet(String key, String value) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.getSet(key, value);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Long setnx(String key, String value) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.setnx(key, value);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public String setex(String key, int seconds, String value) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.setex(key, seconds, value);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Long decrBy(String key, long integer) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.decrBy(key, integer);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Long decr(String key) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.decr(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public Long incrBy(String key, long integer) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.incrBy(key, integer);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Long incr(String key) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.incr(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public Long append(String key, String value) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.append(key, value);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public String substr(String key, int start, int end) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.substr(key, start, end);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Long hset(String key, String field, String value) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.hset(key, field, value);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public String hget(String key, String field) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.hget(key, field);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Long hsetnx(String key, String field, String value) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.hsetnx(key, field, value);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public String hmset(String key, Map<String, String> hash) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.hmset(key, hash);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public List<String> hmget(String key, String... fields) {
        List result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.hmget(key, fields);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Long hincrBy(String key, String field, long value) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.hincrBy(key, field, value);
        } catch (Exception var16) {
            logger.error(var16.getMessage(), var16);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var15) {
                    logger.error(var15.getMessage(), var15);
                }
            }

        }

        return result;
    }

    public Boolean hexists(String key, String field) {
        Boolean result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.hexists(key, field);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Long hdel(String key, String... field) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.hdel(key, field);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Long hlen(String key) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.hlen(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public Set<String> hkeys(String key) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.hkeys(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public List<String> hvals(String key) {
        List result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.hvals(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public Map<String, String> hgetAll(String key) {
        Map result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.hgetAll(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public Long rpush(String key, String... string) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.rpush(key, string);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Long lpush(String key, String... string) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.lpush(key, string);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Long llen(String key) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.llen(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public List<String> lrange(String key, long start, long end) {
        List result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.lrange(key, start, end);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public String ltrim(String key, long start, long end) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.ltrim(key, start, end);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public String lindex(String key, long index) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.lindex(key, index);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public String lset(String key, long index, String value) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.lset(key, index, value);
        } catch (Exception var16) {
            logger.error(var16.getMessage(), var16);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var15) {
                    logger.error(var15.getMessage(), var15);
                }
            }

        }

        return result;
    }

    public Long lrem(String key, long count, String value) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.lrem(key, count, value);
        } catch (Exception var16) {
            logger.error(var16.getMessage(), var16);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var15) {
                    logger.error(var15.getMessage(), var15);
                }
            }

        }

        return result;
    }

    public String lpop(String key) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.lpop(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public String rpop(String key) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.rpop(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public Long sadd(String key, String... member) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.sadd(key, member);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Set<String> smembers(String key) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.smembers(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public Long srem(String key, String... member) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.srem(key, member);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public String spop(String key) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.spop(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public Long scard(String key) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.scard(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public Boolean sismember(String key, String member) {
        Boolean result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.sismember(key, member);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public String srandmember(String key) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.srandmember(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public Long zadd(String key, double score, String member) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zadd(key, score, member);
        } catch (Exception var16) {
            logger.error(var16.getMessage(), var16);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var15) {
                    logger.error(var15.getMessage(), var15);
                }
            }

        }

        return result;
    }

    public Long zadd(String key, Map<String, Double> scoreMembers) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zadd(key, scoreMembers);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Set<String> zrange(String key, long start, long end) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrange(key, start, end);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Long zrem(String key, String... member) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrem(key, member);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Double zincrby(String key, double score, String member) {
        Double result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zincrby(key, score, member);
        } catch (Exception var16) {
            logger.error(var16.getMessage(), var16);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var15) {
                    logger.error(var15.getMessage(), var15);
                }
            }

        }

        return result;
    }

    public Long zrank(String key, String member) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrank(key, member);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Long zrevrank(String key, String member) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrevrank(key, member);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Set<String> zrevrange(String key, long start, long end) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrevrange(key, start, end);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrangeWithScores(key, start, end);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrevrangeWithScores(key, start, end);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Long zcard(String key) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zcard(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public Double zscore(String key, String member) {
        Double result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zscore(key, member);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public List<String> sort(String key) {
        List result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.sort(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public List<String> sort(String key, SortingParams sortingParameters) {
        List result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.sort(key, sortingParameters);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Long zcount(String key, double min, double max) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zcount(key, min, max);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Long zcount(String key, String min, String max) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zcount(key, min, max);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Set<String> zrangeByScore(String key, double min, double max) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrangeByScore(key, min, max);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Set<String> zrangeByScore(String key, String min, String max) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrangeByScore(key, min, max);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Set<String> zrevrangeByScore(String key, double max, double min) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrevrangeByScore(key, max, min);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrangeByScore(key, min, max, offset, count);
        } catch (Exception var19) {
            logger.error(var19.getMessage(), var19);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var18) {
                    logger.error(var18.getMessage(), var18);
                }
            }

        }

        return result;
    }

    public Set<String> zrevrangeByScore(String key, String max, String min) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrevrangeByScore(key, max, min);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrangeByScore(key, min, max, offset, count);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrevrangeByScore(key, max, min, offset, count);
        } catch (Exception var19) {
            logger.error(var19.getMessage(), var19);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var18) {
                    logger.error(var18.getMessage(), var18);
                }
            }

        }

        return result;
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrangeByScoreWithScores(key, min, max);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrangeByScoreWithScores(key, min, max);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        } catch (Exception var19) {
            logger.error(var19.getMessage(), var19);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var18) {
                    logger.error(var18.getMessage(), var18);
                }
            }

        }

        return result;
    }

    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrevrangeByScore(key, max, min, offset, count);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrangeByScoreWithScores(key, max, min);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrangeByScoreWithScores(key, max, min);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrangeByScoreWithScores(key, max, min, offset, count);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrangeByScoreWithScores(key, max, min, offset, count);
        } catch (Exception var19) {
            logger.error(var19.getMessage(), var19);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var18) {
                    logger.error(var18.getMessage(), var18);
                }
            }

        }

        return result;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrangeByScoreWithScores(key, max, min, offset, count);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Long zremrangeByRank(String key, long start, long end) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zremrangeByRank(key, start, end);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Long zremrangeByScore(String key, double start, double end) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zremrangeByScore(key, start, end);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Long zremrangeByScore(String key, String start, String end) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zremrangeByScore(key, start, end);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.linsert(key, where, pivot, value);
        } catch (Exception var16) {
            logger.error(var16.getMessage(), var16);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var15) {
                    logger.error(var15.getMessage(), var15);
                }
            }

        }

        return result;
    }

    public Long lpushx(String key, String string) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.lpushx(key, new String[]{string});
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Long rpushx(String key, String string) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.rpushx(key, new String[]{string});
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Long del(String key) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.del(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public String watch(String key) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = ((Jedis)jedis.getShard(key)).watch(new String[]{key});
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public Transaction multi(String key) {
        Transaction result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = ((Jedis)jedis.getShard(key)).multi();
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public String set(String key, String value, String nxxx, String expx, long time) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.set(key, value, nxxx, expx, time);
        } catch (Exception var18) {
            logger.error(var18.getMessage(), var18);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var17) {
                    logger.error(var17.getMessage(), var17);
                }
            }

        }

        return result;
    }

    public String set(String key, String value, String nxxx) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.set(key, value, nxxx);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Long persist(String key) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.persist(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public Long pexpire(String key, long milliseconds) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.pexpire(key, milliseconds);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Long pexpireAt(String key, long millisecondsTimestamp) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.pexpireAt(key, millisecondsTimestamp);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Long pttl(String key) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.pttl(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public Boolean setbit(String key, long offset, String value) {
        Boolean result = Boolean.valueOf(false);
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.setbit(key, offset, value);
        } catch (Exception var16) {
            logger.error(var16.getMessage(), var16);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var15) {
                    logger.error(var15.getMessage(), var15);
                }
            }

        }

        return result;
    }

    public String psetex(String key, long milliseconds, String value) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.psetex(key, milliseconds, value);
        } catch (Exception var16) {
            logger.error(var16.getMessage(), var16);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var15) {
                    logger.error(var15.getMessage(), var15);
                }
            }

        }

        return result;
    }

    public Double incrByFloat(String key, double value) {
        Double result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.incrByFloat(key, value);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Double hincrByFloat(String key, String field, double value) {
        Double result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.hincrByFloat(key, field, value);
        } catch (Exception var16) {
            logger.error(var16.getMessage(), var16);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var15) {
                    logger.error(var15.getMessage(), var15);
                }
            }

        }

        return result;
    }

    public Set<String> spop(String key, long count) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.spop(key, count);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public List<String> srandmember(String key, int count) {
        List result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.srandmember(key, count);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Long strlen(String key) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.strlen(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public Long zadd(String key, double score, String member, ZAddParams params) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zadd(key, score, member, params);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zadd(key, scoreMembers, params);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Double zincrby(String key, double score, String member, ZIncrByParams params) {
        Double result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zincrby(key, score, member, params);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Long zlexcount(String key, String min, String max) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zlexcount(key, min, max);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Set<String> zrangeByLex(String key, String min, String max) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrangeByLex(key, min, max);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrangeByLex(key, min, max, offset, count);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Set<String> zrevrangeByLex(String key, String max, String min) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrangeByLex(key, max, min);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
        Set result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zrevrangeByLex(key, max, min, offset, count);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Long zremrangeByLex(String key, String min, String max) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zremrangeByLex(key, min, max);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Long lpushx(String key, String... string) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.lpushx(key, string);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Long rpushx(String key, String... string) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.rpushx(key, string);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    /** @deprecated */
    public List<String> blpop(String arg) {
        List result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.blpop(arg);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public List<String> blpop(int timeout, String key) {
        List result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.blpop(timeout, key);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    /** @deprecated */
    public List<String> brpop(String arg) {
        List result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.brpop(arg);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public List<String> brpop(int timeout, String key) {
        List result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.brpop(timeout, key);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public String echo(String string) {
        String result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.echo(string);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public Long move(String key, int dbIndex) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.move(key, dbIndex);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Long bitcount(String key) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.bitcount(key);
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result;
    }

    public Long bitcount(String key, long start, long end) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.bitcount(key, start, end);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public Long bitpos(String key, boolean value) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.bitpos(key, value);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Long bitpos(String key, boolean value, BitPosParams params) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.bitpos(key, value, params);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    /** @deprecated */
    public ScanResult<Entry<String, String>> hscan(String key, int cursor) {
        ScanResult result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.hscan(key, cursor);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    /** @deprecated */
    public ScanResult<String> sscan(String key, int cursor) {
        ScanResult result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.sscan(key, cursor);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    /** @deprecated */
    public ScanResult<Tuple> zscan(String key, int cursor) {
        ScanResult result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zscan(key, cursor);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public ScanResult<Entry<String, String>> hscan(String key, String cursor) {
        ScanResult result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.hscan(key, cursor);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public ScanResult<Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
        ScanResult result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.hscan(key, cursor, params);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public ScanResult<String> sscan(String key, String cursor) {
        ScanResult result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.sscan(key, cursor);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public ScanResult<String> sscan(String key, String cursor, ScanParams params) {
        ScanResult result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.sscan(key, cursor, params);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public ScanResult<Tuple> zscan(String key, String cursor) {
        ScanResult result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zscan(key, cursor);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
        ScanResult result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.zscan(key, cursor, params);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Long pfadd(String key, String... elements) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.pfadd(key, elements);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public long pfcount(String key) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = Long.valueOf(jedis.pfcount(key));
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var12) {
                    logger.error(var12.getMessage(), var12);
                }
            }

        }

        return result.longValue();
    }

    public Long geoadd(String key, double longitude, double latitude, String member) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.geoadd(key, longitude, latitude, member);
        } catch (Exception var18) {
            logger.error(var18.getMessage(), var18);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var17) {
                    logger.error(var17.getMessage(), var17);
                }
            }

        }

        return result;
    }

    public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        Long result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.geoadd(key, memberCoordinateMap);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public Double geodist(String key, String member1, String member2) {
        Double result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.geodist(key, member1, member2);
        } catch (Exception var15) {
            logger.error(var15.getMessage(), var15);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }

        }

        return result;
    }

    public Double geodist(String key, String member1, String member2, GeoUnit unit) {
        Double result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.geodist(key, member1, member2, unit);
        } catch (Exception var16) {
            logger.error(var16.getMessage(), var16);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var15) {
                    logger.error(var15.getMessage(), var15);
                }
            }

        }

        return result;
    }

    public List<String> geohash(String key, String... members) {
        List result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.geohash(key, members);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public List<GeoCoordinate> geopos(String key, String... members) {
        List result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.geopos(key, members);
        } catch (Exception var14) {
            logger.error(var14.getMessage(), var14);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var13) {
                    logger.error(var13.getMessage(), var13);
                }
            }

        }

        return result;
    }

    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        List result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.georadius(key, longitude, latitude, radius, unit);
        } catch (Exception var20) {
            logger.error(var20.getMessage(), var20);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var19) {
                    logger.error(var19.getMessage(), var19);
                }
            }

        }

        return result;
    }

    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        List result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.georadius(key, longitude, latitude, radius, unit, param);
        } catch (Exception var21) {
            logger.error(var21.getMessage(), var21);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var20) {
                    logger.error(var20.getMessage(), var20);
                }
            }

        }

        return result;
    }

    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
        List result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.georadiusByMember(key, member, radius, unit);
        } catch (Exception var17) {
            logger.error(var17.getMessage(), var17);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var16) {
                    logger.error(var16.getMessage(), var16);
                }
            }

        }

        return result;
    }

    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        List result = null;
        ShardedJedis jedis = null;

        try {
            jedis = this.jedisPool.getResource();
            result = jedis.georadiusByMember(key, member, radius, unit, param);
        } catch (Exception var18) {
            logger.error(var18.getMessage(), var18);
        } finally {
            if(null != jedis) {
                try {
                    jedis.close();
                } catch (Exception var17) {
                    logger.error(var17.getMessage(), var17);
                }
            }

        }

        return result;
    }

    @Override
    public List<Long> bitfield(String s, String... strings) {
        return null;
    }

    public long tryLock(String key) {
        try {
            long e = 0L;
            e = System.currentTimeMillis() + this.lockTimeOut + 1L;
            long result = this.setnx(key, String.valueOf(e)).longValue();
            if(result > 0L) {
                this.expire(key, 5);
                return e;
            } else {
                String curLockTimeStr = this.get(key);
                if(!StringUtils.isBlank(curLockTimeStr) && System.currentTimeMillis() <= Long.valueOf(curLockTimeStr).longValue()) {
                    return 0L;
                } else {
                    e = System.currentTimeMillis() + this.lockTimeOut + 1L;
                    curLockTimeStr = this.getSet(key, String.valueOf(e));
                    return !StringUtils.isBlank(curLockTimeStr) && System.currentTimeMillis() <= Long.valueOf(curLockTimeStr).longValue()?0L:e;
                }
            }
        } catch (Exception var7) {
            var7.printStackTrace();
            return 0L;
        }
    }

    public void unlock(String key, long expireTime) {
        try {
            if(System.currentTimeMillis() - expireTime > 0L) {
                return;
            }

            String e = this.get(key);
            if(StringUtils.isNotBlank(e) && Long.valueOf(e).longValue() > System.currentTimeMillis()) {
                this.del(key);
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }
}
