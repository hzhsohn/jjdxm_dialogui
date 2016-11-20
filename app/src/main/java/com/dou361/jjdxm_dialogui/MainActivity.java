package com.dou361.jjdxm_dialogui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bottomsheet.BottomSheetBean;
import com.dou361.dialogui.listener.DialogUIDateTimeSaveListener;
import com.dou361.dialogui.listener.DialogUIItemListener;
import com.dou361.dialogui.listener.DialogUIListener;
import com.dou361.dialogui.widget.DateSelectorWheelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    Activity mActivity;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mActivity = this;
        mContext = getApplication();
        DialogUIUtils.init(mContext);
    }

    String msg = "别总是来日方长，这世上挥手之间的都是人走茶凉。";

    @OnClick({R.id.btn_toast_top, R.id.btn_toast_center, R.id.btn_toast, R.id.btn_popu, R.id.btn_select_ymd, R.id.btn_select_ymdhm, R.id.btn_select_ymdhms, R.id.btn_dialog, R.id.btn_loading_vertical, R.id.btn_loading_horizontal, R.id.btn_loading_vertical_gray, R.id.btn_loading_horizontal_gray, R.id.btn_md_loading_vertical, R.id.btn_md_loading_horizontal, R.id.btn_md_alert, R.id.btn_tie_alert, R.id.btn_alert_horizontal,
            R.id.btn_alert_vertical, R.id.btn_bottom_sheet_cancel, R.id.btn_center_sheet, R.id.btn_alert_input,
            R.id.btn_alert_multichoose, R.id.btn_alert_singlechoose, R.id.btn_bottom_sheet, R.id.btn_md_bottom_vertical, R.id.btn_md_bottom_horizontal, R.id.btn_custom_alert})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_toast_top:
                DialogUIUtils.showToastTop("上部的Toast弹出方式");
                break;
            case R.id.btn_toast_center:
                DialogUIUtils.showToastCenter("中部的Toast弹出方式");
                break;
            case R.id.btn_toast:
                DialogUIUtils.showToast("默认的Toast弹出方式");
                break;
            case R.id.btn_popu:

                break;
            case R.id.btn_select_ymd: {
                DialogUIUtils.showDatePick(mActivity, Gravity.CENTER, DateSelectorWheelView.TYPE_YYYYMMDD, 0, new DialogUIDateTimeSaveListener() {
                    @Override
                    public void onSaveSelectedDate(int tag, String selectedDate) {

                    }
                }).show();
            }
            break;
            case R.id.btn_select_ymdhm: {
                DialogUIUtils.showDatePick(mActivity, Gravity.CENTER, DateSelectorWheelView.TYPE_YYYYMMDDHHMM, 0, new DialogUIDateTimeSaveListener() {
                    @Override
                    public void onSaveSelectedDate(int tag, String selectedDate) {

                    }
                }).show();
            }
            break;
            case R.id.btn_select_ymdhms: {
                DialogUIUtils.showDatePick(mActivity, Gravity.BOTTOM, DateSelectorWheelView.TYPE_YYYYMMDDHHMMSS, 0, new DialogUIDateTimeSaveListener() {
                    @Override
                    public void onSaveSelectedDate(int tag, String selectedDate) {

                    }
                }).show();
            }
            break;
            case R.id.btn_custom_alert:
                View rootView = View.inflate(mActivity, R.layout.custom_dialog_layout, null);
                final Dialog dialog = DialogUIUtils.showCustomAlert(this, rootView, Gravity.CENTER, true, false).show();
                rootView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUIUtils.dismiss(dialog);
                    }
                });
                break;
            case R.id.btn_dialog:
                DialogUIUtils.showDialogTie(this, msg).show();
                break;
            case R.id.btn_loading_vertical:
                DialogUIUtils.showLoadingVertical(this, "加载中...").show();
                break;
            case R.id.btn_loading_horizontal:
                DialogUIUtils.showLoadingHorizontal(this, "加载中...").show();
                break;
            case R.id.btn_loading_vertical_gray:
                DialogUIUtils.showLoadingVertical(this, "加载中...", false).show();
                break;
            case R.id.btn_loading_horizontal_gray:
                DialogUIUtils.showLoadingHorizontal(this, "加载中...", false).show();
                break;
            case R.id.btn_md_loading_vertical:
                DialogUIUtils.showMdLoadingVertical(this, "加载中...").show();
                break;
            case R.id.btn_md_loading_horizontal:
                DialogUIUtils.showMdLoadingHorizontal(this, "加载中...").show();
                break;
            case R.id.btn_md_alert:
                DialogUIUtils.showMdAlert(mActivity, "标题", msg, new DialogUIListener() {
                    @Override
                    public void onPositive() {
                        showToast("onPositive");
                    }

                    @Override
                    public void onNegative() {
                        showToast("onNegative");
                    }

                }).show();
                break;
            case R.id.btn_tie_alert:
                DialogUIUtils.showAlert(mActivity, "标题", msg, new DialogUIListener() {
                    @Override
                    public void onPositive() {
                        showToast("onPositive");
                    }

                    @Override
                    public void onNegative() {
                        showToast("onNegative");
                    }

                }).show();
                break;
            case R.id.btn_alert_horizontal:
                DialogUIUtils.showAlertHorizontal(mActivity, "标题", msg, new DialogUIListener() {
                    @Override
                    public void onPositive() {
                        showToast("onPositive");
                    }

                    @Override
                    public void onNegative() {
                        showToast("onNegative");
                    }

                }).show();
                break;
            case R.id.btn_alert_vertical:
                DialogUIUtils.showAlertVertical(this, "标题", msg, new DialogUIListener() {
                    @Override
                    public void onPositive() {
                        showToast("onPositive");
                    }

                    @Override
                    public void onNegative() {
                        showToast("onNegative");
                    }

                }).show();
                break;
            case R.id.btn_bottom_sheet_cancel: {
                List<String> strings = new ArrayList<>();
                strings.add("1");
                strings.add("2");
                strings.add("3");
                DialogUIUtils.showBottomSheetAndCancel(mActivity, strings, "取消", new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text);
                    }

                    @Override
                    public void onBottomBtnClick() {
                        showToast("onItemClick");
                    }
                }).show();
            }
            break;
            case R.id.btn_center_sheet:
                List<String> strings = new ArrayList<>();
                strings.add("1");
                strings.add("2");
                strings.add("3");
                DialogUIUtils.showCenterSheet(mActivity, strings, new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text);
                    }

                    @Override
                    public void onBottomBtnClick() {
                        showToast("onItemClick");
                    }
                }).show();

                break;
            case R.id.btn_alert_input:
                DialogUIUtils.showAlertInput(mActivity, "登录", "请输入用户名", "请输入密码", "登录", "取消", new DialogUIListener() {
                    @Override
                    public void onPositive() {

                    }

                    @Override
                    public void onNegative() {

                    }

                    @Override
                    public void onGetInput(CharSequence input1, CharSequence input2) {
                        super.onGetInput(input1, input2);
                        showToast("input1:" + input1 + "--input2:" + input2);
                    }
                }).show();
                break;
            case R.id.btn_alert_multichoose:
                String[] words = new String[]{"1", "2", "3"};
                boolean[] choseDefault = new boolean[]{false, false, false};
                DialogUIUtils.showMdMultiChoose(mActivity, "标题", words, choseDefault, new DialogUIListener() {
                    @Override
                    public void onPositive() {

                    }

                    @Override
                    public void onNegative() {

                    }
                }).show();
                break;
            case R.id.btn_alert_singlechoose:
                String[] words2 = new String[]{"1", "2", "3"};
                DialogUIUtils.showSingleChoose(mActivity, "单选", 0, words2, new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text + "--" + position);
                    }
                }).show();
                break;
            case R.id.btn_bottom_sheet:
                List<BottomSheetBean> datass = new ArrayList<>();
                datass.add(new BottomSheetBean(0, "1"));
                datass.add(new BottomSheetBean(0, "2"));
                datass.add(new BottomSheetBean(0, "3"));
                DialogUIUtils.showBottomSheet(this, datass, new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {

                    }
                }).show();
                break;
            case R.id.btn_md_bottom_vertical:
                List<BottomSheetBean> datas2 = new ArrayList<>();
                datas2.add(new BottomSheetBean(0, "1"));
                datas2.add(new BottomSheetBean(0, "2"));
                datas2.add(new BottomSheetBean(0, "3"));
                datas2.add(new BottomSheetBean(0, "4"));
                datas2.add(new BottomSheetBean(0, "5"));
                datas2.add(new BottomSheetBean(0, "6"));
                DialogUIUtils.showMdBottomSheetVertical(mActivity, "标题", datas2, "this is cancle button", new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text + "---" + position);
                    }
                }).show();
                break;
            case R.id.btn_md_bottom_horizontal:
                List<BottomSheetBean> datas3 = new ArrayList<>();
                datas3.add(new BottomSheetBean(0, "1"));
                datas3.add(new BottomSheetBean(0, "2"));
                datas3.add(new BottomSheetBean(0, "3"));
                datas3.add(new BottomSheetBean(0, "4"));
                datas3.add(new BottomSheetBean(0, "5"));
                datas3.add(new BottomSheetBean(0, "6"));
                DialogUIUtils.showMdBottomSheetHorizontal(mActivity, "标题", datas3, "this is cancle button", 3, new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text + "---" + position);
                    }
                }).show();
                break;


        }
    }


    public void showToast(CharSequence msg) {
        DialogUIUtils.showToastLong(msg.toString());
    }
}
