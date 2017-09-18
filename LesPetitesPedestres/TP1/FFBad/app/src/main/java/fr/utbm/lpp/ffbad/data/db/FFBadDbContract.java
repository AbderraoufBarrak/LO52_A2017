package fr.utbm.lpp.ffbad.data.db;

import android.provider.BaseColumns;

public class FFBadDbContract {
    private FFBadDbContract() {}

    /* Inner class that defines the table contents */
    public static class Shuttlecock implements BaseColumns {
        public static final String TABLE_NAME = "shuttlecock";
        public static final String COLUMN_NAME_BRAND = "brand";
        public static final String COLUMN_NAME_REFERENCE = "reference";
        public static final String COLUMN_NAME_RATING = "rating";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_STOCK = "stock";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + "INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_BRAND + " TEXT, " +
                        COLUMN_NAME_REFERENCE + "TEXT, " +
                        COLUMN_NAME_RATING + "INTEGER, " +
                        COLUMN_NAME_PRICE + "REAL, " +
                        COLUMN_NAME_STOCK + "INTEGER)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class Trader implements BaseColumns {
        public static final String TABLE_NAME = "trader";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_CONTACT = "contact";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_MAIL = "mail";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + "INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_NAME + " TEXT, " +
                        COLUMN_NAME_ADDRESS + "TEXT, " +
                        COLUMN_NAME_CONTACT + "TEXT, " +
                        COLUMN_NAME_PHONE + "TEXT, " +
                        COLUMN_NAME_MAIL + "TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class Customer implements BaseColumns {
        public static final String TABLE_NAME = "customer";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_NAME = "name";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + "INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_TYPE + "INTEGER, " +
                        COLUMN_NAME_NAME + "TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class Sale implements BaseColumns {
        public static final String TABLE_NAME = "sale";
        public static final String COLUMN_NAME_SHUTTLECOCK = Shuttlecock.TABLE_NAME + "_id";
        public static final String COLUMN_NAME_CUSTOMER = Customer.TABLE_NAME + "_id";
        public static final String COLUMN_NAME_IS_PAID = "is_paid";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_QUANTITY = "quantity";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + "INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_SHUTTLECOCK + " INTEGER, " +
                        COLUMN_NAME_CUSTOMER + "INTEGER, " +
                        COLUMN_NAME_IS_PAID + "INTEGER, " +
                        COLUMN_NAME_PRICE + "REAL, " +
                        COLUMN_NAME_QUANTITY + "INTEGER)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class Purchase implements BaseColumns {
        public static final String TABLE_NAME = "purchase";
        public static final String COLUMN_NAME_SHUTTLECOCK = Shuttlecock.TABLE_NAME + "_id";
        public static final String COLUMN_NAME_TRADER = Trader.TABLE_NAME + "_id";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + "INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_SHUTTLECOCK + " INTEGER, " +
                        COLUMN_NAME_TRADER + "INTEGER)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}
