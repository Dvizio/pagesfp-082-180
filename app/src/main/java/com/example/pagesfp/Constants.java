package com.example.pagesfp;

public class Constants {
    // init db
    public static final String DATABASE_NAME = "wink_db";
    public static final int DATABASE_VERSION = 1;

    // table
    public static final String TABLE_SUBJECT_NAME = "SUBJECT_TABLE";
    public static final String TABLE_LOG_NAME = "LOG_TABLE";

    // atrribute
    public static final String S_ID = "ID";
    public static final String S_NAME = "NAME";
    public static final String S_CODE = "CODE";
    public static final String S_CLASS = "CLASS";
    public static final String S_TIME = "TIME";

    public static final String L_ID = "ID";
    public static final String L_NAME = "NAME";
    public static final String L_TIMESTAMP = "TIMESTAMP";

    // create table
    public static final String CREATE_SUBJECT_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_SUBJECT_NAME + "( "
            + S_ID + " INTEGER PRIMARY KEY, "
            + S_NAME + " TEXT,"
            + S_CODE + " TEXT,"
            + S_CLASS + " TEXT,"
            + S_TIME + " TEXT"
            + " );";

    public static final String CREATE_LOG_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_LOG_NAME + "( "
            + L_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + L_NAME + " TEXT,"
            + L_TIMESTAMP + " TEXT"
            + " );";

    public static final String INSERT_SUBJECT_TABLE = "INSERT INTO " + TABLE_SUBJECT_NAME
        + " (" +S_ID+","+S_NAME+","+S_CODE+","+ S_CLASS+","+S_TIME+") " +
            "VALUES (1, 'Basic Programming G', 'IF184101', 'IF104', 'Friday 09.00-11.30'), " +
            "(2, 'Android Programming A', 'IF184901', 'IF101', 'Thursday 10.00-11.50'), " +
            "(3, 'Database System B', 'IW184301', 'IF101', 'Monday 10.00-11.50'), " +
            "(4, 'Web Programming E', 'IF234301', 'IF103', 'Monday 10.00-11.50'), " +
            "(5, 'Graph Theory A', 'IF234304', 'IF102', 'Wednesday 07.00-08.50'), " +
            "(6, 'Computer Graphics D', 'IF234504', 'IF103', 'Tuesday 10.00-11.50'), " +
            "(7, 'Machine Learning C', 'IF234401', 'IF102', 'Tuesday 13.00-14.50');";

}
