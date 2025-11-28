package com.qaiser.hyra.tasbeehtcounter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class ZikarList extends Dialog {

    public ZikarList(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zikar_list);
    }
}
