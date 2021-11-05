package com.snayab.ahooraelevator.ui.rules.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.databinding.ActivityRulesBinding;
import com.snayab.ahooraelevator.ui.base.BaseActivity;
import com.snayab.ahooraelevator.ui.rules.adapter.AdapterRules;
import com.snayab.ahooraelevator.ui.rules.model.RulesResponseModel;
import com.snayab.ahooraelevator.ui.rules.view_model.RulesViewModel;

import java.util.ArrayList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;


public class RulesActivity extends BaseActivity implements View.OnClickListener {

    private String TAG= this.getClass().getName();
    private ActivityRulesBinding binding;
    private RulesViewModel rulesViewModel;
    private CompositeDisposable compositeDisposable;
    private AdapterRules adapterRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRulesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
        setupRecyclerViews();
        observeForRulesData();

    }

    private void init() {
        rulesViewModel = new RulesViewModel(this);
        compositeDisposable = new CompositeDisposable();
        binding.imageViewBack.setOnClickListener(this);

    }

    private void setupRecyclerViews() {
        adapterRules = new AdapterRules(this,new ArrayList<>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerViewRules.setLayoutManager(linearLayoutManager);
        binding.recyclerViewRules.setAdapter(adapterRules);

    }

    private void observeForRulesData() {

        rulesViewModel.getRulesData().subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                binding.lottieAnimationView.setVisibility(View.GONE);
                binding.lottieAnimationView.cancelAnimation();
            }
        }).subscribe(new SingleObserver<RulesResponseModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);

            }

            @Override
            public void onSuccess(@NonNull RulesResponseModel rulesResponseModel) {
                adapterRules.addItems(rulesResponseModel.getRules());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError:observeForRulesData: "+e.getMessage() );
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageViewBack:{
                onBackPressed();
                break;
            }


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}