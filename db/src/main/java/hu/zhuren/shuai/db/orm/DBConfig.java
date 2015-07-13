package hu.zhuren.shuai.db.orm;

/**
 * Created by zhuren.hu on 13/7/15.
 * 数据库配置
 */
public class DBConfig {
    //列名字加密方式
    public static final byte COLUMN_ENCRYPT_TYPE_MD5 = 0x01;
    public static final byte COLUMN_ENCRYPT_TYPE_SHA = 0x02;
    //数据加密方法
    public static final byte DATA_ENCRYPT_TYPE_AES = 0x01;
    public static final byte DATA_ENCRYPT_TYPE_XXTEA = 0x02;
    private boolean enableColumnEncrypt;
    private boolean enableDataEncrypt;
    private int mColumnEncryptType;
    private int mDataEncryptType;


    private String mEncryptKey;                         //数据库加密密钥
    private String mDBName;                             //数据库名称
    private int mDBVersion;                             //数据库版本
    private String mDBVersionCode;                      //数据库版本代号


    private DBConfig(final Builder builder) {

    }

    public boolean isEnableColumnEncrypt() {
        return enableColumnEncrypt;
    }

    public void setEnableColumnEncrypt(boolean enableColumnEncrypt) {
        this.enableColumnEncrypt = enableColumnEncrypt;
    }

    public boolean isEnableDataEncrypt() {
        return enableDataEncrypt;
    }

    public void setEnableDataEncrypt(boolean enableDataEncrypt) {
        this.enableDataEncrypt = enableDataEncrypt;
    }

    public int getColumnEncryptType() {
        return mColumnEncryptType;
    }

    public void setColumnEncryptType(int mColumnEncryptType) {
        this.mColumnEncryptType = mColumnEncryptType;
    }

    public int getDataEncryptType() {
        return mDataEncryptType;
    }

    public void setDataEncryptType(int mDataEncryptType) {
        this.mDataEncryptType = mDataEncryptType;
    }

    public String getEncryptKey() {
        return mEncryptKey;
    }

    public void setEncryptKey(String mEncryptKey) {
        this.mEncryptKey = mEncryptKey;
    }

    public String getDBName() {
        return mDBName;
    }

    public void setDBName(String mDBName) {
        this.mDBName = mDBName;
    }

    public int getDBVersion() {
        return mDBVersion;
    }

    public void setDBVersion(int mDBVersion) {
        this.mDBVersion = mDBVersion;
    }

    public String getDBVersionCode() {
        return mDBVersionCode;
    }

    public void setDBVersionCode(String mDBVersionCode) {
        this.mDBVersionCode = mDBVersionCode;
    }

    public static class Builder {
        private boolean enableColumnEncrypt = false;
        private boolean enableDataEncrypt = false;
        private int mColumnEncryptType = 0;
        private int mDataEncryptType = 0;


        private String mEncryptKey = "hu.zhuren.shuai";     //数据库加密密钥
        private String mDBName = "shuai.db";                //数据库名称
        private int mDBVersion = 1;                         //数据库版本
        private String mDBVersionCode = "default";          //数据库版本代号


        public Builder setEnableColumnEncrypt(boolean enableColumnEncrypt) {
            this.enableColumnEncrypt = enableColumnEncrypt;
            return this;
        }

        public Builder setEnableDataEncrypt(boolean enableDataEncrypt) {
            this.enableDataEncrypt = enableDataEncrypt;
            return this;
        }

        public Builder setColumnEncryptType(int mColumnEncryptType) {
            this.mColumnEncryptType = mColumnEncryptType;
            return this;
        }

        public Builder setDataEncryptType(int mDataEncryptType) {
            this.mDataEncryptType = mDataEncryptType;
            return this;
        }

        public Builder setEncryptKey(String mEncryptKey) {
            this.mEncryptKey = mEncryptKey;
            return this;
        }

        public Builder setDBName(String mDBName) {
            this.mDBName = mDBName;
            return this;
        }

        public Builder setDBVersion(int mDBVersion) {
            this.mDBVersion = mDBVersion;
            return this;
        }

        public Builder setDBVersionCode(String mDBVersionCode) {
            this.mDBVersionCode = mDBVersionCode;
            return this;
        }

        public DBConfig build() {
            return new DBConfig(this);
        }
    }
}
