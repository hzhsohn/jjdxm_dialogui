package com.dou361.dialogui.holder;

import android.content.Context;
import android.widget.TextView;

import com.dou361.dialogui.R;

/**
 * ========================================
 * <p>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p>
 * 作 者：陈冠明
 * <p>
 * 个人网站：http://www.dou361.com
 * <p>
 * 版 本：1.0
 * <p>
 * 创建日期：2016/10/31 23:05
 * <p>
 * 描 述：中间弹出框列表
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public class SheetItemHolder extends SuperLvHolder<String> {
    public TextView mTextView;

    public SheetItemHolder(Context context) {
        super(context);
        mTextView = (TextView) rootView.findViewById(R.id.dialogui_tv_msg);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.dialogui_item_sheet;
    }

    @Override
    public void assingDatasAndEvents(Context context, String text) {
        mTextView.setText(text);
    }
}
