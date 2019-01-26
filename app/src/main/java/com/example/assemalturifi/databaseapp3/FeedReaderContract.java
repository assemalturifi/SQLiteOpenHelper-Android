package com.example.assemalturifi.databaseapp3;

import android.provider.BaseColumns;

//step7
public class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.

    //step8
    public static final String NAME="Person.db";
    public static final int VERSION=1;

    //step10
    public static final String CREATE_TABLE="CREATE TABLE "+
            FeedEntry.TABLE_NAME+" ("+
            FeedEntry.COL_ID+" Text,"+
            FeedEntry.COL_DESK+" Text,"+
            FeedEntry.COL_PHOTO+" Text)";

    //step11
    public static final String GET_ALL="SELECT * FROM "+ FeedEntry.TABLE_NAME;



    //step9
    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME="image";
        public static final String COL_ID="id";
        public static final String COL_DESK="desk";
        public static final String COL_PHOTO="photo";
    }
}
