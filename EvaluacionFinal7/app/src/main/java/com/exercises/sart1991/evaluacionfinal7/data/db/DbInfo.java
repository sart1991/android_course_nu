package com.exercises.sart1991.evaluacionfinal7.data.db;

/**
 * Created by sart1 on 5/22/2017.
 */

public enum DbInfo {
    DB_NAME("AppDataBase");

    private String dbName;

    DbInfo(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }

    public enum TablesInfo {
        USER_TABLE("user"), DONOR_TABLE("donor");

        private String tableName;

        TablesInfo(String tableName) {
            this.tableName = tableName;
        }

        public String getTableName() {
            return tableName;
        }
    }

    public enum UserInfo {
        USER_NAME("user_name text primary key"),
        PASSWORD("password text");

        private String value;

        UserInfo(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    public enum DonorInfo {
        ID("id int primary key"),
        NAME("name text"),
        LAST_NAME("last_name text"),
        AGE("age int"),
        BLOOD_TYPE("blood_type text"),
        RH("rh varchar(2)"),
        WEIGHT("weight int"),
        HEIGHT("height int"),
        USER_NAME("user_name text, foreign key(user_name) references user(user_name)");

        private String value;

        DonorInfo(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
