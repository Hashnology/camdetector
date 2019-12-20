//package com.karimapps.faizan.spy_camdetector;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Handler;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Base64;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.blikoon.qrcodescanner.QrCodeActivity;
//import com.blikoon.qrcodescanner.db.AppDataBase;
//
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.reward.RewardItem;
//import com.google.android.gms.ads.reward.RewardedVideoAd;
//import com.google.android.gms.ads.reward.RewardedVideoAdListener;
//import com.karimapps.qrcodescanner.fragments.HistoryFragment;
//import com.karimapps.qrcodescanner.fragments.WebFragment;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.InterstitialAd;
//import com.google.android.gms.ads.MobileAds;
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity implements View.OnClickListener, RewardedVideoAdListener {
//    private Button button, button_history;
//    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
//    private Context context;
//    Button btn_goto_web, btn_more, btn_share, btn_adds, btn_demo;
//    private static final int REQUEST_CODE_QR_SCAN = 101;
//    private final String LOGTAG = "QRCScanner-MainActivity";
//    android.support.v7.app.ActionBar ab;
//    boolean doubleBackToExitPressedOnce = false;
//    private AdView adView;
//    private InterstitialAd mInterstitialAd;
//    private RewardedVideoAd mRewardedVideoAd;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        requestWindowFeature(Window.FEATURE_NO_TITLE);
////        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.main_new);
//        context = MainActivity.this;
//        showAds();
//
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
////            ActionBar ab = getActionBar();
////            ab.setTitle("My Title");
////            ab.setSubtitle("sub-title");
////        }
//        ab = getSupportActionBar();
//        ab.setTitle("           Qr Code Scanner");
////        ab.setSubtitle("");
//
////        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        button_history = (Button) findViewById(R.id.btn_history);
//        button = (Button) findViewById(R.id.btn_scan);
//
//        btn_goto_web = (Button) findViewById(R.id.btn_goto_web);
//        btn_more = (Button) findViewById(R.id.btn_more);
//        btn_share = (Button) findViewById(R.id.btn_share);
//        btn_adds = (Button) findViewById(R.id.btn_adds);
//        btn_demo = (Button) findViewById(R.id.btn_demo);
//
//        button.setOnClickListener(this);
//        button_history.setOnClickListener(this);
//        btn_goto_web.setOnClickListener(this);
//        btn_more.setOnClickListener(this);
//        btn_share.setOnClickListener(this);
//        btn_adds.setOnClickListener(this);
//        btn_demo.setOnClickListener(this);
//
//
//        mInterstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                if (mInterstitialAd.isLoaded()) {
//                    mInterstitialAd.show();
//                } else {
//                    Log.d("TAG", "The interstitial wasn't loaded yet.");
//                }
//                // Code to be executed when an ad finishes loading.
//            }
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//                // Code to be executed when an ad request fails.
//            }
//
//            @Override
//            public void onAdOpened() {
//                // Code to be executed when the ad is displayed.
//            }
//
//            @Override
//            public void onAdLeftApplication() {
//                // Code to be executed when the user has left the app.
//            }
//
//            @Override
//            public void onAdClosed() {
//
//                // Code to be executed when when the interstitial ad is closed.
//            }
//        });
//
//
//    }
//
//    @Override
//    public void onRewarded(RewardItem reward) {
//        Toast.makeText(this, "onRewarded! currency: " + reward.getType() + "  amount: " +
//                reward.getAmount(), Toast.LENGTH_SHORT).show();
//        // Reward the user.
//    }
//
//    @Override
//    public void onRewardedVideoAdLeftApplication() {
//        Toast.makeText(this, "onRewardedVideoAdLeftApplication",
//                Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onRewardedVideoAdClosed() {
//        Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onRewardedVideoAdFailedToLoad(int errorCode) {
//        Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onRewardedVideoAdLoaded() {
//        Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onRewardedVideoAdOpened() {
//        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onRewardedVideoStarted() {
//        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onRewardedVideoCompleted() {
//        Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
//    }
//
///*    @Override
//    public void onResume() {
//        mRewardedVideoAd.resume(this);
//        super.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        mRewardedVideoAd.pause(this);
//        super.onPause();
//    }
//
//    @Override
//    public void onDestroy() {
//        mRewardedVideoAd.destroy(this);
//        super.onDestroy();
//    }*/
//
//
//    @Override
//    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
////        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//        showAlert();
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce = false;
//            }
//        }, 5000);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (resultCode != Activity.RESULT_OK) {
//            Log.d(LOGTAG, "COULD NOT GET A GOOD RESULT.");
//            if (data == null)
//                return;
//            //Getting the passed result
//            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
//            if (result != null) {
//                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
//                alertDialog.setTitle("Scan Error");
//                alertDialog.setMessage("QR Code could not be scanned");
//                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//                alertDialog.show();
//            }
//            return;
//
//        }
//        if (requestCode == REQUEST_CODE_QR_SCAN) {
//            if (data == null)
//                return;
//            //Getting the passed result
//            String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
////           byte[] qr_code_image_bytes=data.getByteArrayExtra("qr_code_image_bytes");
//            Log.d(LOGTAG, "Have scan result in your app activity :" + result);
//            AppDataBase appDataBase = new AppDataBase(context);
//            appDataBase.open();
//            showAlert(result, appDataBase.getAlreadySavedImage());
//
//            appDataBase.close();
//
////
////   AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
////            alertDialog.setTitle("Scan result");
////            alertDialog.setMessage(result);
////            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
////                    new DialogInterface.OnClickListener() {
////                        public void onClick(DialogInterface dialog, int which) {
////                            dialog.dismiss();
////                        }
////                    });
////            alertDialog.show();
//
//        }
//    }
//
//    private void showAlert(final String str_result, final byte[] qr_code_image_byte) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setView(R.layout.result_alert);
//        final AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//        final EditText et_title = (EditText) alertDialog.findViewById(R.id.et_title);
//        Button btn_change = (Button) alertDialog.findViewById(R.id.btn_change);
//        Button btn_cancel = (Button) alertDialog.findViewById(R.id.btn_cancel);
//        Button btn_save = (Button) alertDialog.findViewById(R.id.btn_save);
//        ImageView iv_result = (ImageView) alertDialog.findViewById(R.id.iv_result);
//        TextView tv_result = (TextView) alertDialog.findViewById(R.id.tv_result);
//        String encoded = Base64.encodeToString(qr_code_image_byte, Base64.DEFAULT);
//        iv_result.setImageBitmap(BitmapFactory.decodeByteArray(qr_code_image_byte, 0, qr_code_image_byte.length));
//        tv_result.setText("Scanned Code \n " + str_result);
//        btn_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.cancel();
//            }
//        });
//        btn_change.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.cancel();
//                goToScan();
//            }
//        });
//        btn_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String title = et_title.getText().toString();
//                if (TextUtils.isEmpty(title)) {
//                    Toast.makeText(context, "Enter title", Toast.LENGTH_SHORT).show();
//                } else {
//                    alertDialog.dismiss();
//                    ab.setTitle("           Qr Code Scanner");
//                    ab.setSubtitle("            History");
//                    AppDataBase appDataBase = new AppDataBase(context);
//                    appDataBase.open();
//                    appDataBase.insertCodes(title, str_result, getCurrentDate(), qr_code_image_byte);
//                    appDataBase.close();
//
//                    if (mInterstitialAd.isLoaded()) {
//                        mInterstitialAd.show();
//                    } else {
//                        Log.d("TAG", "The interstitial wasn't loaded yet.");
//                    }
//                }
//
//            }
//        });
//
//    }
//
//
//    private void openScreenshot(File imageFile) {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        Uri uri = Uri.fromFile(imageFile);
//        intent.setDataAndType(uri, "image/*");
//        startActivity(intent);
//    }
//
//    public void sendMail(String path) {
//        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//        emailIntent.putExtra(Intent.EXTRA_EMAIL,
//                new String[]{"Email@gmail.com"});
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT,
//                "Test Mail");
//        emailIntent.putExtra(Intent.EXTRA_TEXT,
//                "");
//        emailIntent.setType("image/png");
//        Uri myUri = Uri.parse("file://" + path);
//        emailIntent.putExtra(Intent.EXTRA_STREAM, myUri);
//        context.startActivity(Intent.createChooser(emailIntent, "Share Image..."));
//    }
//
//    private void sharelink() {
//        try {
//            Intent i = new Intent(Intent.ACTION_SEND);
//            i.setType("text/plain");
//            i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
//            String sAux = "\nLet me recommend you this application\n\n";
//            sAux = sAux + "https://play.google.com/store/apps/details?id=com.karimapps.qrcodescanner \n\n";
//            i.putExtra(Intent.EXTRA_TEXT, sAux);
//            startActivity(Intent.createChooser(i, "choose one"));
//        } catch (Exception e) {
//            //e.toString();
//        }
//    }
//
//    private void shareMoreAppslink() {
//        try {
//            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
//            try {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
//            } catch (android.content.ActivityNotFoundException anfe) {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
//            }
//        } catch (Exception e) {
//            //e.toString();
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_scan:
//                goToScan();
//                break;
//            case R.id.btn_history:
//                ab.setTitle("           Qr Code Scanner");
//                ab.setSubtitle("            History");
//                goToHistory();
//                break;
//            case R.id.btn_goto_web:
//                ab.setTitle("           Qr Code Scanner");
//                ab.setSubtitle("            Portofolio");
//                goToWeb();
//                break;
//            case R.id.btn_more:
//                ab.setTitle("           Qr Code Scanner");
//                ab.setSubtitle("            More Apps");
//                shareMoreAppslink();
//                break;
//            case R.id.btn_share:
//                ab.setTitle("           Qr Code Scanner");
//                ab.setSubtitle("            Share App");
//                sharelink();
//                break;
//            case R.id.btn_adds:
//                ab.setTitle("           Qr Code Scanner");
//                ab.setSubtitle("            Gift Adds");
////                Toast.makeText(context, "Pending", Toast.LENGTH_SHORT).show();
//
//                if (mInterstitialAd.isLoaded()) {
//                    mInterstitialAd.show();
//                } else {
//                    Log.d("TAG", "The interstitial wasn't loaded yet.");
//                }
//
////                if (mRewardedVideoAd.isLoaded()) {
////                    mRewardedVideoAd.show();
////                }
//
//                break;
//            case R.id.btn_demo:
//                ab.setTitle("           Qr Code Scanner");
//                ab.setSubtitle("            Demo Video");
//                Toast.makeText(context, "Demo video ", Toast.LENGTH_SHORT).show();
//                showDemo();
//                break;
//
//
//        }
//    }
//
//    private void showDemo() {
//        try {
//            String videoId = "qiFxD9aNWmk";
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
//            intent.putExtra("VIDEO_ID", videoId);
//            startActivity(intent);
//        } catch (Exception e) {
//
//        }
//    }
//
//    private void goToScan() {
//        boolean check = checkAndRequestPermissions();
//        if (check) {
//            Intent i = new Intent(MainActivity.this, QrCodeActivity.class);
//            startActivityForResult(i, REQUEST_CODE_QR_SCAN);
//        } else {
//            Toast.makeText(context, "Give Permissions first", Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//    private void goToWeb() {
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        Fragment fragment = new WebFragment();
//        ft.replace(R.id.container_fragment, fragment);
//        ft.commit();
//    }
//
//    private void goToHistory() {
//        ab.setTitle("           Qr Code Scanner");
//        ab.setSubtitle("            History");
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        Fragment fragment = new HistoryFragment();
//        ft.replace(R.id.container_fragment, fragment);
//        ft.commit();
//    }
//
//    private String getCurrentDate() {
//        String date = "";
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:ss");
//        Date today = new Date();
//        date = dateFormat.format(today);
//        return date;
//    }
//
//    private void showAds() {
//        MobileAds.initialize(context, getString(R.string.app_id));
////        MobileAds.initialize(this,
////                "ca-app-pub-3940256099942544~3347511713");
//        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
//        // values/strings.xml.
//        adView = (AdView) findViewById(R.id.ad_view);
//
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);
//
//        /*interstial ads*/
//        mInterstitialAd = new InterstitialAd(this);
//        mInterstitialAd.setAdUnitId(getString(R.string.interstitial));
//        mInterstitialAd.loadAd(new AdRequest.Builder().build());
//
////        loadRewardedVideoAd();
//
//
//    }
//
//    private boolean checkAndRequestPermissions() {
//
//        int cameraPermission = ContextCompat.checkSelfPermission(this,
//                Manifest.permission.CAMERA);
//
//        int writeStoragePermission = ContextCompat.checkSelfPermission(this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        int readStoragePermission = ContextCompat.checkSelfPermission(this,
//                Manifest.permission.READ_EXTERNAL_STORAGE);
//
//
//        List<String> listPermissionsNeeded = new ArrayList<>();
//
//
//        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.CAMERA);
//        }
//
//
//        if (writeStoragePermission != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        }
//        if (readStoragePermission != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
//        }
//
//
//        if (!listPermissionsNeeded.isEmpty()) {
//            ActivityCompat.requestPermissions(this,
//                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           @NonNull String permissions[],
//                                           @NonNull int[] grantResults) {
//        // Make sure it's our original S request
//        if (requestCode == REQUEST_ID_MULTIPLE_PERMISSIONS) {
//            for (int i = 0; i < permissions.length; i++) {
//                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
////                    Log.d("Permissions", "Permission Granted: " + permissions[i]);
////                    goToScan();
////                    Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
//                } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
//                    Log.d("Permissions", "Permission Denied: " + permissions[i]);
//                    Toast.makeText(this, "permission is compulsory for start the app", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//            }
//        } else {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
//    }
//
//    private void loadRewardedVideoAd() {
//        MobileAds.initialize(this, "ca-app-pub-3940256099942544/5224354917");
//        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
//        mRewardedVideoAd.setRewardedVideoAdListener(this);
//
//        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
//                new AdRequest.Builder().build());
//    }
//
//    private void showAlert() {
//        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
//        builder1.setTitle("Rate Us");
//        builder1.setMessage("If you have liked this Qr code scanning app please rate it five star ,also your positive comments are very usefull apperciation for us to develop more apps like this one \n\nThanks");
//        builder1.setCancelable(true);
//
//        builder1.setPositiveButton(
//                "Rate it",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//
//                        shareMoreAppslink();
//
//                    }
//                });
//
//        builder1.setNegativeButton(
//                "Not Now",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//
//                    }
//                });
//
//        builder1.setNeutralButton("Exit App",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                        finish();
//                    }
//                });
//
//
//        AlertDialog alert11 = builder1.create();
//        alert11.show();
//    }
//
//
//}
