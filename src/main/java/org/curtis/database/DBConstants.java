package org.curtis.database;

import java.util.Arrays;
import java.util.List;

public class DBConstants {
    public static final String DATABASE_MYSQL = "mysql";
    public static final String DATABASE_POSTGRES = "postgresql";
    public static final String DATABASE_ORACLE = "oracle";

    public static final List<String> DATABASE_TYPES = Arrays.asList(DATABASE_MYSQL, DATABASE_POSTGRES, DATABASE_ORACLE);

    private DBConstants() {

    }
}
