package com.baidao.jsbridgedemo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.widget.AppCompatTextView;


public class NetworkRemindDialog extends Dialog implements View.OnClickListener {
    private Context context;

    public NetworkRemindDialog(Context activity) {
        super(activity);
        this.context = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_network_remind, null);
        setContentView(view);
        AppCompatTextView tvLeft = view.findViewById(R.id.tv_left);
        AppCompatTextView tvRight = view.findViewById(R.id.tv_right);
        tvLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_right:
                if (context != null) {
                    try {
                        context.startActivity(new Intent(context, ThirdActivity.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
//                this.dismiss();
                break;
            case R.id.tv_left:
                this.dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public void show() {
        super.show();
        if (getWindow() != null) {
            getWindow().setLayout((int) (context.getResources().getDisplayMetrics().widthPixels * 0.8), WindowManager.LayoutParams.WRAP_CONTENT);
        }
    }
}
