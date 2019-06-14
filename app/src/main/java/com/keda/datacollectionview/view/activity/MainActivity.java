package com.keda.datacollectionview.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.keda.datacollectionview.R;
import com.keda.datacollectionview.annotation.Mvvm;
import com.keda.datacollectionview.annotation.handler.AnnotationHandler;
import com.keda.datacollectionview.databinding.ActivityMainBinding;
import com.keda.datacollectionview.model.User;
import com.keda.datacollectionview.viewmodel.MainViewModel;
import com.keda.datacollectionview.BR;
import com.keda.datecollection.adapter.DataManager;
import com.keda.datecollection.adapter.recyclerview.DataDealRecyclerViewAdapter;

@Mvvm(layoutId = R.layout.activity_main, viewModel = MainViewModel.class, bindingVariable = BR.viewModel)
public class MainActivity extends BaseActivity
{
    private LinearLayout mRootView;
    private ActivityMainBinding nDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        User user = new User();
        user.org = "苏州科达";
        user.partment = "开发";
        user.userName = "小刚";
        DataDealRecyclerViewAdapter adapter = DataManager.getDataCollectionRecycleViewAdapter(this, user, R.layout.view_linearlayout,BR.viewModel, com.keda.datecollection.annotations.handler.AnnotationHandler.TYPE_COLLECTION);

        nDataBinding = (ActivityMainBinding) mDataBinding;
        RecyclerView recyclerView = nDataBinding.rv;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

}
