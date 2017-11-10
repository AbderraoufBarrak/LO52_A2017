package fr.utbm.lpp.ffbad.data.db;

import android.database.Cursor;
import android.provider.BaseColumns;

import fr.utbm.lpp.ffbad.data.CustomerType;

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
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_BRAND + " TEXT, " +
                        COLUMN_NAME_REFERENCE + " TEXT, " +
                        COLUMN_NAME_RATING + " INTEGER, " +
                        COLUMN_NAME_PRICE + " REAL, " +
                        COLUMN_NAME_STOCK + " INTEGER)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static fr.utbm.lpp.ffbad.data.Shuttlecock getFromCursor(Cursor cursor) {
            long id = cursor.getLong(cursor.getColumnIndex(_ID));
            String brand = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_BRAND));
            String reference = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_REFERENCE));
            int rating = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_RATING));
            int stock = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_STOCK));
            double price = cursor.getFloat(cursor.getColumnIndex(COLUMN_NAME_PRICE));

            return new fr.utbm.lpp.ffbad.data.Shuttlecock(id, brand, reference, rating, stock, price);
        }
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
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_NAME + " TEXT, " +
                        COLUMN_NAME_ADDRESS + " TEXT, " +
                        COLUMN_NAME_CONTACT + " TEXT, " +
                        COLUMN_NAME_PHONE + " TEXT, " +
                        COLUMN_NAME_MAIL + " TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class Customer implements BaseColumns {
        public static final String TABLE_NAME = "customer";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_NAME = "name";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_TYPE + " INTEGER, " +
                        COLUMN_NAME_NAME + " TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static fr.utbm.lpp.ffbad.data.Customer getFromCursor(Cursor cursor) {
            long id = cursor.getLong(cursor.getColumnIndex(_ID));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NAME));
            int type = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_TYPE));

            return new fr.utbm.lpp.ffbad.data.Customer(id, name, CustomerType.values()[type]);
        }
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
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_SHUTTLECOCK + " INTEGER, " +
                        COLUMN_NAME_CUSTOMER + " INTEGER, " +
                        COLUMN_NAME_IS_PAID + " INTEGER, " +
                        COLUMN_NAME_PRICE + " REAL, " +
                        COLUMN_NAME_QUANTITY + " INTEGER)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static fr.utbm.lpp.ffbad.data.Sale getFromCursor(Cursor cursor) {
            long id = cursor.getLong(cursor.getColumnIndex(_ID));
            fr.utbm.lpp.ffbad.data.Customer customer = Customer.getFromCursor(cursor);
            fr.utbm.lpp.ffbad.data.Shuttlecock shuttlecock = Shuttlecock.getFromCursor(cursor);
            int quantity = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_QUANTITY));
            boolean isPaid = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_IS_PAID)) == 1;

            return new fr.utbm.lpp.ffbad.data.Sale(id, customer, shuttlecock, quantity, isPaid);
        }
    }

    public static class Purchase implements BaseColumns {
        public static final String TABLE_NAME = "purchase";
        public static final String COLUMN_NAME_SHUTTLECOCK = Shuttlecock.TABLE_NAME + "_id";
        public static final String COLUMN_NAME_TRADER = Trader.TABLE_NAME + "_id";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_SHUTTLECOCK + " INTEGER, " +
                        COLUMN_NAME_TRADER + " INTEGER)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}
