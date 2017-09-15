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
    }

    public static class Trader implements BaseColumns {
        public static final String TABLE_NAME = "trader";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_CONTACT = "contact";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_mail = "mail";
    }

    public static class Customer implements BaseColumns {
        public static final String TABLE_NAME = "customer";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_NAME = "name";
    }

    public static class Purchase implements BaseColumns {
        public static final String TABLE_NAME = "purchase";
        public static final String COLUMN_NAME_SHUTTLECOCK = Shuttlecock.TABLE_NAME + "_id";
        public static final String COLUMN_NAME_CUSTOMER = Customer.TABLE_NAME + "_id";
        public static final String COLUMN_NAME_IS_PAID = "is_paid";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_QUANTITY = "quantity";
    }

    public static class ShuttlecockTrader implements BaseColumns {
        public static final String TABLE_NAME = "shuttlecock_trader";
        public static final String COLUMN_NAME_SHUTTLECOCK = Shuttlecock.TABLE_NAME + "_id";
        public static final String COLUMN_NAME_TRADER = Trader.TABLE_NAME + "_id";
    }

}
