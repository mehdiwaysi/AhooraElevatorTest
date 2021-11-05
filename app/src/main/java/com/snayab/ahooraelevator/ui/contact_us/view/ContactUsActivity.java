package com.snayab.ahooraelevator.ui.contact_us.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.databinding.ActivityContactUsBinding;
import com.snayab.ahooraelevator.helpers.SnackBarHelper;
import com.snayab.ahooraelevator.ui.base.BaseActivity;
import com.snayab.ahooraelevator.ui.contact_us.model.ContactUsResponseModel;
import com.snayab.ahooraelevator.ui.contact_us.view_model.ContactUsViewModel;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class ContactUsActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = this.getClass().getName();
    private ContactUsViewModel contactUsViewModel;
    private CompositeDisposable compositeDisposable;

    private ActivityContactUsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactUsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
        observeForContactUsData();

    }

    private void init() {
        contactUsViewModel = new ContactUsViewModel(this);
        compositeDisposable = new CompositeDisposable();
        binding.imageViewBack.setOnClickListener(this);
    }

    private void observeForContactUsData() {
        contactUsViewModel.getContactUsData().subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                binding.lottieAnimationView.setVisibility(View.GONE);
                binding.lottieAnimationView.cancelAnimation();
            }
        }).subscribe(new SingleObserver<ContactUsResponseModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull ContactUsResponseModel contactUsResponseModel) {
                Log.e(TAG, "onSuccess: " + "onSuccess");


                if (contactUsResponseModel.getContact_us().getDescription() != null)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        binding.tvDescription.setText(Html.fromHtml(contactUsResponseModel.getContact_us().getDescription(), Html.FROM_HTML_MODE_COMPACT));
                    } else {
                        binding.tvDescription.setText(Html.fromHtml(contactUsResponseModel.getContact_us().getDescription()));
                    }

                binding.linearTelegram.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (contactUsResponseModel.getContact_us().getTelegram() != null)
                            goToTelegram(contactUsResponseModel.getContact_us().getTelegram());
                        else
                            SnackBarHelper.showSnackBar(ContactUsActivity.this, binding.getRoot(), "لینک تعریف نشده است!", Snackbar.LENGTH_LONG);
                    }
                });
                binding.linearWatsApp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (contactUsResponseModel.getContact_us().getWhatsapp() != null)
                            goToWatsApp(contactUsResponseModel.getContact_us().getWhatsapp());
                        else
                            SnackBarHelper.showSnackBar(ContactUsActivity.this, binding.getRoot(), "لینک تعریف نشده است!", Snackbar.LENGTH_LONG);
                    }

                });
                binding.linearInstagram.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (contactUsResponseModel.getContact_us().getInstagram() != null)
                            goToInstagram(contactUsResponseModel.getContact_us().getInstagram());
                        else
                            SnackBarHelper.showSnackBar(ContactUsActivity.this, binding.getRoot(), "لینک تعریف نشده است!", Snackbar.LENGTH_LONG);

                    }
                });
                binding.linearCallFirst.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (contactUsResponseModel.getContact_us().getPrimary_phone() != null) {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:" + contactUsResponseModel.getContact_us().getPrimary_phone()));
                            startActivity(intent);
                        } else
                            SnackBarHelper.showSnackBar(ContactUsActivity.this, binding.getRoot(), "شماره تماس تعریف نشده است!", Snackbar.LENGTH_LONG);

                    }
                });
                binding.linearCallTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (contactUsResponseModel.getContact_us().getSecondary_phone() != null) {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:" + contactUsResponseModel.getContact_us().getSecondary_phone()));
                            startActivity(intent);
                        } else
                            SnackBarHelper.showSnackBar(ContactUsActivity.this, binding.getRoot(), "شماره تماس تعریف نشده است!", Snackbar.LENGTH_LONG);

                    }
                });
                binding.linearWebsite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (contactUsResponseModel.getContact_us().getWebsite() != null) {
                            openSite(contactUsResponseModel.getContact_us().getWebsite());
                        } else
                            SnackBarHelper.showSnackBar(ContactUsActivity.this, binding.getRoot(), "وبسایت تعریف نشده است!", Snackbar.LENGTH_LONG);


                    }
                });

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());

            }
        });

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

            SnackBarHelper.showSnackBar(ContactUsActivity.this, binding.getRoot(), "تلگرام بر روی گوشی شما نصب نیست!", Snackbar.LENGTH_LONG);
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
            SnackBarHelper.showSnackBar(ContactUsActivity.this, binding.getRoot(), "اینستاگرام بر روی گوشی شما نصب نیست!", Snackbar.LENGTH_LONG);

        }
    }

    private void goToWatsApp(String whatsappLink) {

        try {
            Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + "" + whatsappLink + "?body=" + "سلام "));
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } catch (Exception e) {
            e.printStackTrace();
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
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();

    }
}