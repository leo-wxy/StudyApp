package wxy.frame.finalframe;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import wxy.frame.finalframe.fragment.article.ArticleFragment;
import wxy.frame.finalframe.fragment.joke.JokeFragment;
import wxy.frame.finalframe.util.LogUtils;

public class MainActivity extends BaseActivity {

    ArticleFragment articleFragment;
    JokeFragment jokeFragment;
    NavigationView nv_left;
    DrawerLayout dl;
    FragmentTransaction ft;
    Fragment currentFragment;
    ActionBarDrawerToggle actionBarDrawerToggle;
    AppBarLayout appBarLayout;
    ActionBar ab;
    Toolbar toolbar;
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    public void findIds() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                LogUtils.e("verticalOffset" + verticalOffset);
//            }
//        });
        setSupportActionBar(toolbar);

        ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(true);
        ab.setDisplayHomeAsUpEnabled(false); // 决定左上角图标的右侧是否有向左的小箭头, true
        // 有小箭头，并且图标可以点击
        ab.setDisplayShowHomeEnabled(false);
        ab.setTitle("文章");

        nv_left = (NavigationView) findViewById(R.id.nv_left);
        if (nv_left != null)
            setDrawerLeft(nv_left);
        dl = (DrawerLayout) findViewById(R.id.dl);

        if (savedInstanceState != null) {
            List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
            for (Fragment fragment : fragmentList) {
                if (fragment instanceof ArticleFragment) {
                    articleFragment = (ArticleFragment) fragment;
                } else if (fragment instanceof JokeFragment) {
                    jokeFragment = (JokeFragment) fragment;
                }
                // 解决重叠问题
                getSupportFragmentManager().beginTransaction().show(articleFragment)
                        .commit();
            }
        } else {
            articleFragment = ArticleFragment.newInstance();
            jokeFragment = JokeFragment.newInstance();
            currentFragment = articleFragment;
            getSupportFragmentManager().beginTransaction().add(R.id.ll_main, articleFragment)
                    .commit();
        }

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.close,
                R.string.open);
        actionBarDrawerToggle.syncState();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // Translucent navigation bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {//弹出请求权限dialog

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
            } else {//选择不在询问后下次进入的操作
                showSnack(toolbar, "没有权限无法进行后续的操作", "前往设置", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startAppSettings();
                    }
                });
            }
        }
    }

    @Override
    public void initViews() {

    }

    private void setDrawerLeft(NavigationView view) {
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                dl.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nv_book:
                        ab.setTitle("文章");
                        switchContent(articleFragment);
                        item.setChecked(true);
                        break;
                    case R.id.nv_joke:
                        ab.setTitle("笑话");
                        switchContent(jokeFragment);
                        item.setChecked(true);
                        break;
                    case R.id.nv_music:
                        ab.setTitle("音乐");
                        item.setChecked(true);
                        break;
                    case R.id.nv_pic:
                        ab.setTitle("图片");
                        item.setChecked(true);
                        break;
                    default:
                        item.setChecked(true);
                        break;
                }
                return true;
            }
        });
    }

    public void switchContent(Fragment to) {
        ft = getSupportFragmentManager().beginTransaction();
        if (currentFragment != to) {
            if (!to.isAdded()) {    // 先判断是否被add过
                ft.hide(currentFragment).add(R.id.ll_main, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                ft.hide(currentFragment).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            currentFragment = to;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {//给予权限
                    showSnack(toolbar, "设置成功");
                } else {//拒绝权限
                    showSnack(toolbar, "没有权限无法进行后续的操作", "继续设置", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    1);
                        }
                    });
                }

            }
        }
    }

    /**
     * 跳转到应用系统设置界面  申请权限
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }

}
