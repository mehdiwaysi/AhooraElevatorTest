package com.snayab.ahooraelevator.ui.about_company.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.databinding.ActivityAboutCompanyBinding;
import com.snayab.ahooraelevator.helpers.SnackBarHelper;
import com.snayab.ahooraelevator.shared_preferences.SharedPreferencesHelper;
import com.snayab.ahooraelevator.ui.adapter.AdapterAboutCompany;
import com.snayab.ahooraelevator.ui.about_company.model.AboutCompanyResponse;
import com.snayab.ahooraelevator.ui.about_company.view_model.AboutCompanyViewModel;
import com.snayab.ahooraelevator.ui.base.BaseActivity;

import java.util.ArrayList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class AboutCompanyActivity extends BaseActivity implements View.OnClickListener, AdapterAboutCompany.OnOtherProductItemSelected {

    private String TAG = this.getClass().getName();

    private ActivityAboutCompanyBinding binding;
    private AdapterAboutCompany adapterAboutCompany;
    private AboutCompanyViewModel aboutCompanyViewModel;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAboutCompanyBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
        showVersion();
        observeForAboutCompanyData();
    }


    private void init() {

        compositeDisposable = new CompositeDisposable();
        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        aboutCompanyViewModel = new AboutCompanyViewModel(this);
        adapterAboutCompany = new AdapterAboutCompany(this, new ArrayList<>());
        adapterAboutCompany.setOnOtherProductItemSelected(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.recyclerViewAboutUs.setLayoutManager(linearLayoutManager);
        binding.recyclerViewAboutUs.setAdapter(adapterAboutCompany);
        binding.imageViewBack.setOnClickListener(this);
    }


    private void observeForAboutCompanyData() {
        aboutCompanyViewModel.getAboutCompanyData(getPackageName(), sharedPreferencesHelper.getUserId()).subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                binding.lottieAnimationView.setVisibility(View.GONE);
                binding.lottieAnimationView.cancelAnimation();
            }
        }).subscribe(new SingleObserver<AboutCompanyResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull AboutCompanyResponse aboutCompanyResponse) {

                binding.tvWeb.setText(aboutCompanyResponse.getAboutUs().getWebsite().substring(8));
                binding.tvInstagram.setText(aboutCompanyResponse.getAboutUs().getInstagram());
                binding.tvCall.setText(aboutCompanyResponse.getAboutUs().getPhone());
                binding.tvTelegram.setText(aboutCompanyResponse.getAboutUs().getTelegram());
                adapterAboutCompany.addItems(aboutCompanyResponse.getAboutUs().getPortfolio());


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.tvDescription.setText(Html.fromHtml(aboutCompanyResponse.getAboutUs().getDescription(), Html.FROM_HTML_MODE_COMPACT));
                    binding.textViewFooter.setText(Html.fromHtml(aboutCompanyResponse.getAboutUs().getFooter(), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    binding.tvDescription.setText(Html.fromHtml(aboutCompanyResponse.getAboutUs().getDescription()));
                    binding.textViewFooter.setText(Html.fromHtml(aboutCompanyResponse.getAboutUs().getFooter()));
                }

                binding.linearTelegram.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToTelegram(aboutCompanyResponse.getAboutUs().getTelegram());
                    }
                });

                binding.linearInstagram.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToInstagram(aboutCompanyResponse.getAboutUs().getInstagram());
                    }
                });
                binding.linearCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + aboutCompanyResponse.getAboutUs().getPhone()));
                        startActivity(intent);
                    }
                });
                binding.linearWeb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openSite(aboutCompanyResponse.getAboutUs().getWebsite());
                    }
                });

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError:observeForAboutUsData: " + e.getMessage());
            }
        });


    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imageViewBack: {
                onBackPressed();
                break;
            }

        }
    }


    public void goToTelegram(String id) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=" + id));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } catch (Exception e) {
           SnackBarHelper.showSnackBar(this, binding.getRoot(), "تلگرام بر روی گوشی شما نصب نیست!", Snackbar.LENGTH_LONG);
        }

    }

    public void goToInstagram(String id) {
        try {
            Uri uri = Uri.parse("instagram://user?username=" + id);
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
            likeIng.setPackage("com.instagram.android");
            likeIng.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(likeIng);

        } catch (ActivityNotFoundException e) {
            SnackBarHelper.showSnackBar(this, binding.getRoot(), "اینستاگرام بر روی گوشی شما نصب نیست!", Snackbar.LENGTH_LONG);
        }

    }


    public void goToGooglePlay(String link, String packageName) {
        try {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(
                    "https://play.google.com/store/apps/details?id=" + packageName));
            intent.setPackage("com.android.vending");
            startActivity(intent);

        } catch (ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
        }

    }

    public void goToCafeBazar(String link, String id) {
        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("bazaar://details?id=" + id));
            i.setPackage("com.farsitel.bazaar");
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
        }


    }


    public void openSite(String link) {

        if (link != null) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(link));
            startActivity(i);
        }
    }

    @Override
    public void onOtherProductItemSelected(String packageName, String link) {
        try {
            Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
            startActivity(intent);
        } catch (Exception e) {
            if (link.contains("play.google.com")) {
                goToGooglePlay(link, packageName);
            } else if (link.contains("cafebazaar")) {
                goToCafeBazar(link, packageName);
            } else {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
            }
        }

    }

    private void showVersion() {
        PackageManager manager = getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = manager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        binding.tvVersion.setText("v " + packageInfo.versionName);

    }

}