package hu.zhuren.shuai.db.kv;

/**
 * Created by zhuren.hu on 12/7/15.
 * KV数据库接口
 */
public interface IKVDatabase {
    public <K, V> boolean put(K k, V v);

    public <K> Object get(K k);

    public <K> boolean containKey(K k);

    public <V> boolean containValue(V v);

    public <K> boolean remove(K k);

    public <K> boolean remove(K[] ks);

    public int size();

    public void clear();

}
