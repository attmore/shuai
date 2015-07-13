package hu.zhuren.shuai.db.kv;

/**
 * Created by zhuren.hu on 12/7/15.
 * 配置文件
 */
public class KVDBConfig {
    //持久化的存储的地方
    public static final byte PERSISTENT_TYPE_SQLITE = 0x01;
    public static final byte PERSISTENT_TYPE_SHAREPREFERENCE = 0x02;
    public static final byte PERSISTENT_TYPE_SDCARD = 0x03;

    //数据库加密方法
    public static final byte ENCRYPT_TYPE_AES = 0x01;
    public static final byte ENCRYPT_TYPE_XXTEA = 0x02;

    private boolean isNeedPersistent;                   //是否持久化
    private boolean isNeedEncrypt;                      //是否需要加密
    private int mEncryptType;                           //数据库加密方式
    private int mPersistenType;                         //数据库持久化方式
    private String mEncryptKey;                         //数据库加密密钥
    private String mDBName;                             //数据库名称
    private String mDBVersion;                          //数据库版本

    private KVDBConfig(final Builder builder) {
        isNeedPersistent = builder.isNeedPersistent;
        mEncryptKey = builder.encryptKey;
        mDBName = builder.dbName;
        mDBVersion = builder.dbVersion;
        isNeedEncrypt = builder.isNeedEncrypt;
        mEncryptType = builder.encryptType;
        mPersistenType = builder.persistenType;
    }

    /**
     * 得到一个默认的数据库配置文件
     *
     * @return
     */
    public static KVDBConfig newDefaultKVDBConfig() {
        return new Builder().build();
    }

    public boolean isNeedPersistent() {
        return isNeedPersistent;
    }

    public boolean isNeedEncrypt() {
        return isNeedEncrypt;
    }

    public int getEncryptType() {
        return mEncryptType;
    }

    public String getEncryptKey() {
        return mEncryptKey;
    }

    public String getDBName() {
        return mDBName;
    }

    public String getDBVersion() {
        return mDBVersion;
    }

    public int getPersistenType() {
        return mPersistenType;
    }

    public static class Builder {
        private boolean isNeedPersistent = false;
        private boolean isNeedEncrypt = false;
        private int encryptType = ENCRYPT_TYPE_AES;
        private int persistenType = PERSISTENT_TYPE_SHAREPREFERENCE;
        private String encryptKey = "hu.zhuren.shuai";
        private String dbName = "KVDB";
        private String dbVersion = "unknown";

        public Builder() {
        }

        public void setPersistenType(int persistenType) {
            this.persistenType = persistenType;
        }

        public Builder setNeedEncrypt(boolean isNeedEncrypt) {
            this.isNeedEncrypt = isNeedEncrypt;
            return this;
        }

        public Builder setNeedPersistent(boolean isNeedPersistent) {
            this.isNeedPersistent = isNeedPersistent;
            return this;
        }

        public Builder setEncryptType(int encryptType) {
            this.encryptType = encryptType;
            return this;
        }

        public Builder setEncryptKey(String encryptKey) {
            this.encryptKey = encryptKey;
            return this;
        }

        public Builder setDbName(String dbName) {
            this.dbName = dbName;
            return this;
        }

        public Builder setDbVersion(String dbVersion) {
            this.dbVersion = dbVersion;
            return this;
        }


        public KVDBConfig build() {
            return new KVDBConfig(this);
        }
    }
}
