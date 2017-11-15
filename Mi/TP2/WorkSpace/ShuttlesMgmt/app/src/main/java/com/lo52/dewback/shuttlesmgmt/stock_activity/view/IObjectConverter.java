package com.lo52.dewback.shuttlesmgmt.stock_activity.view;

/**
 * Created by Notmoo on 14/11/2017.
 */

public interface IObjectConverter<T, U> {
    U convert(T object);
}
