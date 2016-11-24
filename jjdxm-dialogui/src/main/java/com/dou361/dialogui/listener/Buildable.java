package com.dou361.dialogui.listener;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.R;
import com.dou361.dialogui.adapter.SuperLvAdapter;
import com.dou361.dialogui.bean.BuildBean;
import com.dou361.dialogui.config.CommonConfig;
import com.dou361.dialogui.holder.AlertDialogHolder;
import com.dou361.dialogui.holder.SheetCancelHolder;
import com.dou361.dialogui.holder.SheetHolder;
import com.dou361.dialogui.holder.SheetItemHolder;
import com.dou361.dialogui.holder.SuperLvHolder;
import com.dou361.dialogui.utils.ToolUtils;
import com.dou361.dialogui.widget.DateSelectorWheelView;

/**
 * ========================================
 * <p/>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2016/11/1 16:15
 * <p/>
 * 描 述：
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class Buildable {

    protected static int singleChosen;

    protected BuildBean buildByType(BuildBean bean) {
        ToolUtils.fixContext(bean);
        switch (bean.type) {
            case CommonConfig.TYPE_DATEPICK:
                buildDatePickCenter(bean);
                break;
            case CommonConfig.TYPE_LOADING_HORIZONTAL:
                buildLoadingHorizontal(bean);
                break;
            case CommonConfig.TYPE_LOADING_VERTICAL:
                buildLoadingVertical(bean);
                break;
            case CommonConfig.TYPE_MD_LOADING_HORIZONTAL:
                buildMdLoadingHorizontal(bean);
                break;
            case CommonConfig.TYPE_MD_LOADING_VERTICAL:
                buildMdLoadingVertical(bean);
                break;
            case CommonConfig.TYPE_MD_ALERT:
                buildMdAlert(bean);
                break;
            case CommonConfig.TYPE_SINGLE_CHOOSE:
                buildSingleChoose(bean);
                break;
            case CommonConfig.TYPE_MD_MULTI_CHOOSE:
                buildMdMultiChoose(bean);
                break;
            case CommonConfig.TYPE_ALERT:
                buildAlert(bean);
                break;
            case CommonConfig.TYPE_BOTTOM_SHEET_CANCEL:
                buildBottomSheetCancel(bean);
                break;
            case CommonConfig.TYPE_CENTER_SHEET:
                buildCenterSheet(bean);
                break;
            case CommonConfig.TYPE_CUSTOM_ALERT:
                buildCustomAlert(bean);
                break;
            case CommonConfig.TYPE_CUSTOM_BOTTOM_ALERT:
                buildCustomBottomAlert(bean);
                break;
            case CommonConfig.TYPE_BOTTOM_SHEET_VERTICAL:
                buildBottomSheetVertical(bean);
                break;
            case CommonConfig.TYPE_BOTTOM_SHEET_HORIZONTAL:
                buildBottomSheetHorizontal(bean);
                break;
            default:
                break;


        }
        ToolUtils.setDialogStyle(bean);
        ToolUtils.setCancelable(bean);
        return bean;
    }

    private BuildBean buildDatePickCenter(final BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.context);
        View root = View.inflate(bean.context, R.layout.dialogui_datepick_layout, null);

        RelativeLayout rl_title_panel = (RelativeLayout) root
                .findViewById(R.id.rl_title_panel);
        FrameLayout flFirst = (FrameLayout) root
                .findViewById(R.id.fl_first);
        FrameLayout flNext = (FrameLayout) root
                .findViewById(R.id.fl_next);
        TextView tv_title = (TextView) root
                .findViewById(R.id.tv_title);
        TextView tv_first = (TextView) root
                .findViewById(R.id.tv_first);
        TextView tv_next = (TextView) root
                .findViewById(R.id.tv_next);
        FrameLayout fl_top_customPanel = (FrameLayout) root
                .findViewById(R.id.fl_top_customPanel);
        final DateSelectorWheelView dwvDate = (DateSelectorWheelView) root
                .findViewById(R.id.dwv_date);
        FrameLayout fl_bottom_customPanel = (FrameLayout) root
                .findViewById(R.id.fl_bottom_customPanel);
        dwvDate.setShowDate(bean.date);
        dwvDate.setShowDateType(bean.dateType);
        dwvDate.setTitleClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.rl_date_time_title) {
                    if (dwvDate.getDateSelectorVisibility() == View.VISIBLE) {
                        dwvDate.setDateSelectorVisiblility(View.GONE);
                    } else {
                        dwvDate.setDateSelectorVisiblility(View.VISIBLE);
                    }
                }
            }
        });

        builder.setView(root);
        final AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
        if(bean.gravity == Gravity.BOTTOM){
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));}

        flFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        flNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != bean.dateTimeListener) {
                    bean.dateTimeListener.onSaveSelectedDate(bean.tag, dwvDate.getSelectedDate());
                }
                dialog.dismiss();
            }
        });
        return bean;
    }

    private void buildBottomSheetHorizontal(final BuildBean bean) {
        final BottomSheetDialog dialog = new BottomSheetDialog(bean.context);
        LinearLayout root = (LinearLayout) View.inflate(bean.context, R.layout.dialogui_holder_sheet_title, null);
        TextView tvTitle = (TextView) root.findViewById(R.id.dialogui_tv_title);
        if (TextUtils.isEmpty(bean.title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(bean.title);
        }
        GridView listView = new GridView(bean.context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        listView.setLayoutParams(params);
        // ListView listView = (ListView) root.findViewById(R.id.lv);
        listView.setNumColumns(bean.gridColumns);
        root.addView(listView, 1);
        if (bean.mAdapter == null) {
            SuperLvAdapter adapter = new SuperLvAdapter(bean.context) {
                @Override
                protected SuperLvHolder generateNewHolder(Context context, int itemViewType) {
                    return new SheetItemHolder(context);
                }
            };
            bean.mAdapter = adapter;
        }
        listView.setAdapter(bean.mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                bean.itemListener.onItemClick(bean.lvDatas.get(position), position);
            }
        });
        bean.mAdapter.addAll(bean.lvDatas);
        dialog.setContentView(root);
        bean.dialog = dialog;
    }

    private void buildBottomSheetVertical(final BuildBean bean) {
        final BottomSheetDialog dialog = new BottomSheetDialog(bean.context);
        LinearLayout root = (LinearLayout) View.inflate(bean.context, R.layout.dialogui_holder_sheet_title, null);
        TextView tvTitle = (TextView) root.findViewById(R.id.dialogui_tv_title);
        if (TextUtils.isEmpty(bean.title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(bean.title);
        }
        ListView listView = new ListView(bean.context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        listView.setLayoutParams(params);
        listView.setDividerHeight(0);
        // ListView listView = (ListView) root.findViewById(R.id.lv);
        root.addView(listView, 1);
        if (bean.mAdapter == null) {
            SuperLvAdapter adapter = new SuperLvAdapter(bean.context) {
                @Override
                protected SuperLvHolder generateNewHolder(Context context, int itemViewType) {
                    return new SheetItemHolder(context);
                }
            };
            bean.mAdapter = adapter;
        }
        listView.setAdapter(bean.mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                bean.itemListener.onItemClick(bean.lvDatas.get(position), position);
            }
        });
        bean.mAdapter.addAll(bean.lvDatas);
        dialog.setContentView(root);
        bean.dialog = dialog;
    }

    protected BuildBean buildMdLoadingVertical(BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.context);
        View root = View.inflate(bean.context, R.layout.dialogui_loading_vertical, null);
        View llBg = (View) root.findViewById(R.id.dialogui_ll_bg);
        ProgressBar pbBg = (ProgressBar) root.findViewById(R.id.pb_bg);
        TextView tvMsg = (TextView) root.findViewById(R.id.dialogui_tv_msg);
        tvMsg.setText(bean.msg);
        if (bean.isWhiteBg) {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_wihte_round_corner);
            pbBg.setIndeterminateDrawable(bean.context.getResources().getDrawable(R.drawable.dialogui_rotate_mum));
            tvMsg.setTextColor(bean.context.getResources().getColor(R.color.text_black));
        } else {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_gray_round_corner);
            pbBg.setIndeterminateDrawable(bean.context.getResources().getDrawable(R.drawable.dialogui_rotate_mum_light));
            tvMsg.setTextColor(Color.WHITE);
        }
        builder.setView(root);
        AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
        return bean;
    }


    protected BuildBean buildMdLoadingHorizontal(BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.context);
        View root = View.inflate(bean.context, R.layout.dialogui_loading_horizontal, null);
        View llBg = (View) root.findViewById(R.id.dialogui_ll_bg);
        ProgressBar pbBg = (ProgressBar) root.findViewById(R.id.pb_bg);
        TextView tvMsg = (TextView) root.findViewById(R.id.dialogui_tv_msg);
        tvMsg.setText(bean.msg);
        if (bean.isWhiteBg) {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_wihte_round_corner);
            pbBg.setIndeterminateDrawable(bean.context.getResources().getDrawable(R.drawable.dialogui_shape_progress));
            tvMsg.setTextColor(bean.context.getResources().getColor(R.color.text_black));
        } else {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_gray_round_corner);
            pbBg.setIndeterminateDrawable(bean.context.getResources().getDrawable(R.drawable.dialogui_shape_progress_light));
            tvMsg.setTextColor(Color.WHITE);
        }
        builder.setView(root);
        AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
        return bean;
    }

    protected BuildBean buildLoadingVertical(BuildBean bean) {
        Dialog dialog = new Dialog(bean.context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        bean.dialog = dialog;

        View root = View.inflate(bean.context, R.layout.dialogui_loading_vertical, null);
        View llBg = (View) root.findViewById(R.id.dialogui_ll_bg);
        ProgressBar pbBg = (ProgressBar) root.findViewById(R.id.pb_bg);
        TextView tvMsg = (TextView) root.findViewById(R.id.dialogui_tv_msg);
        tvMsg.setText(bean.msg);
        if (bean.isWhiteBg) {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_wihte_round_corner);
            pbBg.setIndeterminateDrawable(bean.context.getResources().getDrawable(R.drawable.dialogui_rotate_mum));
            tvMsg.setTextColor(bean.context.getResources().getColor(R.color.text_black));
        } else {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_gray_round_corner);
            pbBg.setIndeterminateDrawable(bean.context.getResources().getDrawable(R.drawable.dialogui_rotate_mum_light));
            tvMsg.setTextColor(Color.WHITE);
        }
        bean.dialog.setContentView(root);
        return bean;
    }

    protected BuildBean buildLoadingHorizontal(BuildBean bean) {
        Dialog dialog = new Dialog(bean.context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        bean.dialog = dialog;

        View root = View.inflate(bean.context, R.layout.dialogui_loading_horizontal, null);
        View llBg = (View) root.findViewById(R.id.dialogui_ll_bg);
        ProgressBar pbBg = (ProgressBar) root.findViewById(R.id.pb_bg);
        TextView tvMsg = (TextView) root.findViewById(R.id.dialogui_tv_msg);
        tvMsg.setText(bean.msg);
        if (bean.isWhiteBg) {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_wihte_round_corner);
            pbBg.setIndeterminateDrawable(bean.context.getResources().getDrawable(R.drawable.dialogui_shape_progress));
            tvMsg.setTextColor(bean.context.getResources().getColor(R.color.text_black));
        } else {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_gray_round_corner);
            pbBg.setIndeterminateDrawable(bean.context.getResources().getDrawable(R.drawable.dialogui_shape_progress_light));
            tvMsg.setTextColor(Color.WHITE);
        }
        bean.dialog.setContentView(root);
        return bean;
    }

    protected BuildBean buildMdAlert(final BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.context);
        builder.setTitle(bean.title)
                .setMessage(bean.msg)
                .setPositiveButton(bean.text1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bean.listener.onPositive();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(bean.text2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bean.listener.onNegative();
                        dialog.dismiss();
                    }
                }).setNeutralButton(bean.text3, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bean.listener.onNeutral();
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                bean.listener.onCancle();
            }
        });
        bean.alertDialog = dialog;
        return bean;
    }

    protected BuildBean buildSingleChoose(final BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.context);
        singleChosen = bean.defaultChosen;
        builder.setTitle(bean.title)
                .setSingleChoiceItems(bean.wordsMd, bean.defaultChosen, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        singleChosen = i;
                        if (bean.itemListener != null) {
                            bean.itemListener.onItemClick(bean.wordsMd[i], i);
                        }

                        if (bean.listener == null) {
                            DialogUIUtils.dismiss(dialogInterface);
                        }

                    }
                });

        AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
        return bean;
    }

    protected BuildBean buildMdMultiChoose(final BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.context);
        builder.setTitle(bean.title)
                .setCancelable(true)
                .setPositiveButton(bean.text1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (bean.listener != null) {
                            DialogUIUtils.dismiss(dialogInterface);
                            bean.listener.onPositive();
                            bean.listener.onGetChoose(bean.checkedItems);
                        }
                    }
                })
                .setNegativeButton(bean.text2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (bean.listener != null) {
                            DialogUIUtils.dismiss(dialogInterface);
                            bean.listener.onNegative();
                        }
                    }
                })
                .setMultiChoiceItems(bean.wordsMd, bean.checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                    }
                })
        ;

        AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
        return bean;
    }

    protected BuildBean buildAlert(BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.context);
        AlertDialogHolder holder = new AlertDialogHolder(bean.context);
        builder.setView(holder.rootView);
        AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
        holder.assingDatasAndEvents(bean.context, bean);
        return bean;
    }

    private void buildCustomAlert(BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.context);
        builder.setView(bean.customView);
        AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
    }

    private void buildCustomBottomAlert(BuildBean bean) {
        BottomSheetDialog dialog = new BottomSheetDialog(bean.context);
        dialog.setContentView(bean.customView);
        bean.dialog = dialog;
    }

    protected BuildBean buildCenterSheet(BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.context);
        SheetHolder holder = new SheetHolder(bean.context);
        builder.setView(holder.rootView);
        AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
        holder.assingDatasAndEvents(bean.context, bean);
        return bean;
    }

    protected BuildBean buildBottomSheetCancel(BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.context);
        SheetCancelHolder holder = new SheetCancelHolder(bean.context);
        builder.setView(holder.rootView);
        AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
        holder.assingDatasAndEvents(bean.context, bean);
        bean.viewHeight = ToolUtils.mesureHeight(holder.rootView, holder.lv);
        Window window = bean.alertDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setWindowAnimations(R.style.dialogui_bottom_style);
        return bean;
    }


}
