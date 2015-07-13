package hu.zhuren.shuai.db.kv;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuren.hu on 12/7/15.
 * KV数据库实现
 */
public class KVDatabase implements IKVDatabase {
    private KVDBConfig mConfig;
    //KV保存在内存中
    private Map<Object, Object> mKVDB;

    /**
     * 自定义配置数据库
     *
     * @param config
     */
    public KVDatabase(KVDBConfig config) {
        mConfig = config;
        mKVDB = new HashMap<Object, Object>();
    }

    /**
     * 默认配置的KV数据库
     */
    public KVDatabase() {
        mConfig = KVDBConfig.newDefaultKVDBConfig();
        mKVDB = new HashMap<Object, Object>();
    }


    @Override
    public <K extends Object, V extends Object> boolean put(K k, V v) {
        if (null == k || null == v) {
            return false;
        }
        mKVDB.put(k, v);
        return true;
    }

    @Override
    public <K extends Object> Object get(K k) {
        if (null == k) {
            return null;
        }
        return mKVDB.get(k);
    }

    @Override
    public <K extends Object> boolean containKey(K k) {
        if (null == k) {
            return false;
        }
        return mKVDB.containsKey(k);
    }

    @Override
    public <V extends Object> boolean containValue(V v) {
        if (v == null) {
            return false;
        }
        return mKVDB.containsValue(v);
    }

    @Override
    public <K extends Object> boolean remove(K k) {
        if (k == null) {
            return false;
        }
        mKVDB.remove(k);
        return true;
    }

    @Override
    public <K extends Object> boolean remove(K[] ks) {
        if (null == ks || ks.length == 0) {
            return false;
        }
        int i = 0;
        for (K k : ks) {
            mKVDB.remove(k);
            i++;
        }
        return ks.length == i ? true : false;
    }

    @Override
    public int size() {
        return mKVDB.size();
    }

    @Override
    public void clear() {
        mKVDB.clear();
    }

    /**
     * TODO 持久化的实现方法
     * 可以持久化到文件里
     * 可以持久化到Sqlite中
     * 可以持久化到SharedPerference
     */
    public void persist() {

        mConfig.getPersistenType();
    }
}
