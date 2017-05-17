package com.exercises.sart1991.sqlitedrawer.data.db;

/**
 * Created by sart1 on 5/16/2017.
 */

public class DbInfo {

    public enum Info {
        APP_DATABASE, VEHICLE, ID, BRAND, QUANTITY;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    public enum DDL {

        CREATE_TABLE("CREATE TABLE"),
        INTEGER("INTEGER"),
        INT("INT"),
        TEXT("TEXT"),
        PRIMARY_KEY("PRIMARY KEY"),
        AUTOINCREMENT("AUTOINCREMENT");

        private String sentence;

        DDL(String sentence) {
            this.sentence = sentence;
        }

        public String get() {
            return sentence;
        }
    }

    public enum DML {

        DROP_TABLE("DROP TABLE"),
        IF_EXISTS("IF EXISTS"),
        INSERT_INTO("INSERT INTO"),
        DELETE("DELETE");

        private String sentence;

        DML(String sentence) {
            this.sentence = sentence;
        }

        public String get() {
            return sentence;
        }
    }
}
