package com.dou361.dialogui.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.R;
import com.dou361.dialogui.bean.BuildBean;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class SheetHolder extends SuperHolder {
    public ListView lv;

    public SheetHolder(Context context) {
        super(context);
    }

    @Override
    protected void findViews() {
        lv = (ListView) rootView.findViewById(R.id.lv);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.dialogui_holder_sheet;
    }

    @Override
    public void assingDatasAndEvents(final Context context, final BuildBean bean) {
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return bean.lvDatas.size();
            }

            @Override
            public Object getItem(int position) {
                return bean.lvDatas.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                LinearLayout root = (LinearLayout) View.inflate(context, R.layout.dialogui_item_sheet, null);
                TextView view = (TextView) root.findViewById(R.id.dialogui_tv_msg);

                view.setText(bean.lvDatas.get(position));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUIUtils.dismiss(bean.alertDialog, bean.dialog);
                        bean.itemListener.onItemClick(bean.lvDatas.get(position), position);

                    }
                });

                return root;
            }
        });
    }


}
