package com.snayab.ahooraelevator.ui.fragments.category.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderView;
import com.snayab.ahooraelevator.databinding.FragmentCategoryBinding;
import com.snayab.ahooraelevator.helpers.SnackBarHelper;
import com.snayab.ahooraelevator.shared_preferences.SharedPreferencesHelper;
import com.snayab.ahooraelevator.ui.fragments.category.adapter.AdapterMainCategoriesList;
import com.snayab.ahooraelevator.ui.fragments.category.adapter.AdapterSubCategoriesList;
import com.snayab.ahooraelevator.ui.fragments.category.model.ElevatorTypesResponse;
import com.snayab.ahooraelevator.ui.fragments.category.view_model.CategoryFragmentViewModel;
import com.snayab.ahooraelevator.ui.fragments.main.adapters.SliderAdapter;
import com.snayab.ahooraelevator.ui.fragments.main.view_model.HomeFragmentViewModel;

import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class CategoryFragment extends Fragment implements AdapterMainCategoriesList.OnMainCategoryClickListiner {

    private String TAG = getClass().getName();
    private CategoryFragmentViewModel categoryFragmentViewModel;
    private CompositeDisposable compositeDisposable;
    private FragmentCategoryBinding binding;
    private AdapterMainCategoriesList adapterMainCategoriesList;
    private AdapterSubCategoriesList adapterSubCategoriesList;
    private ElevatorTypesResponse elevatorTypesRes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        initView();
        initObjects();
        observeForHomePageData();

        return view;
    }

    private void initView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        binding.recyclerViewMainCategory.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManagerSub = new LinearLayoutManager(getContext());
        linearLayoutManagerSub.setOrientation(LinearLayoutManager.VERTICAL);

        binding.recyclerViewSubCategory.setLayoutManager(linearLayoutManagerSub);

    }


    private void initObjects() {
        categoryFragmentViewModel = new CategoryFragmentViewModel();
        compositeDisposable = new CompositeDisposable();
        adapterMainCategoriesList = new AdapterMainCategoriesList(getContext(), new ArrayList<>());
        adapterMainCategoriesList.setOnMainCategoryClickListiner(this);
        adapterSubCategoriesList = new AdapterSubCategoriesList(getContext(), new ArrayList<>());
        binding.recyclerViewMainCategory.setAdapter(adapterMainCategoriesList);
        binding.recyclerViewSubCategory.setAdapter(adapterSubCategoriesList);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    private void observeForHomePageData() {
        categoryFragmentViewModel.getElevatorTypes().subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                binding.lottieAnimationView.setVisibility(View.GONE);
                binding.lottieAnimationView.cancelAnimation();
            }
        }).subscribe(new SingleObserver<ElevatorTypesResponse>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {

                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NotNull ElevatorTypesResponse elevatorTypesResponse) {

                elevatorTypesRes = elevatorTypesResponse;

                adapterMainCategoriesList.addItems(elevatorTypesResponse.getElevators());

            }

            @Override
            public void onError(@NotNull Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());

                SnackBarHelper.showSnackBar(getContext(), binding.getRoot(), "هنگام دریافت اطلاعات این صفحه مشکلی رخ داده است", Snackbar.LENGTH_LONG);

            }
        });
    }


    @Override
    public void onMainCategoryClicked(int position) {

        if (elevatorTypesRes.getElevators().get(position) != null) {
            if (elevatorTypesRes.getElevators().get(position).getSub_elevators() != null && elevatorTypesRes.getElevators().get(position).getSub_elevators().size() > 0) {
                binding.recyclerViewMainCategory.setVisibility(View.GONE);
                binding.recyclerViewSubCategory.setVisibility(View.VISIBLE);
                adapterSubCategoriesList.addItems(elevatorTypesRes.getElevators().get(position).getSub_elevators());

            } else {
                Intent intent = new Intent(getContext(), ElevatorDetailsActivity.class);
                intent.putExtra("title", elevatorTypesRes.getElevators().get(position).getName());
                intent.putExtra("picture", elevatorTypesRes.getElevators().get(position).getPicture());
                intent.putExtra("description", elevatorTypesRes.getElevators().get(position).getDescription().replaceAll("https", "http"));
                getContext().startActivity(intent);

            }
        }


    }


    public boolean isRecyclerViewMainCategoryVisible() {
        if (binding.recyclerViewMainCategory.getVisibility() == View.VISIBLE)
            return true;
        return false;
    }

    public void showRecyclerViewMainCategory() {
        binding.recyclerViewMainCategory.setVisibility(View.VISIBLE);
        binding.recyclerViewSubCategory.setVisibility(View.GONE);
        adapterSubCategoriesList.clearAll();
    }


}

