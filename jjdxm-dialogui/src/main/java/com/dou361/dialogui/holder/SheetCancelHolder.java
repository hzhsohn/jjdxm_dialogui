package com.dou361.dialogui.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.R;
import com.dou361.dialogui.bean.BuildBean;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class SheetCancelHolder extends SuperHolder {
    public ListView lv;
    protected Button btnBottom;

    public SheetCancelHolder(Context context) {
        super(context);
    }

    @Override
    protected void findViews() {
        lv = (ListView) rootView.findViewById(R.id.lv);
        btnBottom = (Button) rootView.findViewById(R.id.btn_bottom);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.dialogui_holder_sheet_cancel;
    }

    @Override
    public void assingDatasAndEvents(final Context context, final BuildBean bean) {
        if (TextUtils.isEmpty(bean.bottomTxt)) {
            btnBottom.setVisibility(View.GONE);
        } else {
            btnBottom.setVisibility(View.VISIBLE);
            btnBottom.setText(bean.bottomTxt);
            btnBottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUIUtils.dismiss(bean.dialog, bean.alertDialog);
                    bean.itemListener.onBottomBtnClick();

                }
            });
        }


        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return bean.lvDatas.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {

                LinearLayout root = (LinearLayout) View.inflate(context, R.layout.dialogui_item_sheet, null);
                TextView view = (TextView) root.findViewById(R.id.dialogui_tv_msg);

                view.setText(bean.lvDatas.get(position));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUIUtils.dismiss(bean.dialog, bean.alertDialog);
                        bean.itemListener.onItemClick(bean.lvDatas.get(position), position);

                    }
                });

                return root;
            }
        };

        lv.setAdapter(adapter);
    }


}