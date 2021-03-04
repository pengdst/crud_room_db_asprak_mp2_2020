package com.mmnuradityo.crud_room_db.ui.common;

import android.view.View;

/**
 * Created by pengdst on 04/03/2021
 * <p>
 * Github    : https://github.com/pengdst
 * Gitlab    : https://gitlab.com/pengdst
 * LinkedIn  : https://linkedin.com/in/pengdst/
 */
public interface DataClickListener<Data> {
    public void onItemClick(View view, Data data, int position);
}
