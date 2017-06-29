package app.yellow.github.core.home.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import app.yellow.github.R;
import app.yellow.github.core.home.HomeActivity;
import app.yellow.github.core.login.LoginActivity;
import app.yellow.github.util.ActivityUtils;
import app.yellow.github.util.Constants;
import app.yellow.github.util.SPUtils;
import app.yellow.github.util.UIUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.language_tv)
    TextView mLanguageTv;
    @BindView(R.id.languagetitles_tv)
    TextView mLanguagetitlesTv;
    @BindView(R.id.sorttype_tv)
    TextView mSorttypeTv;
    @BindView(R.id.cachetime_tv)
    TextView mCachetimeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Setting");
        actionBar.setDisplayHomeAsUpEnabled(true);

        String languge = SPUtils.getString(UIUtils.getContext(), Constants.SP_FIRST_LANGUAGE, "Java");
        mLanguageTv.setText("First Languge（" + languge + "）");

        String langugeTitle = SPUtils.getString(UIUtils.getContext(), Constants.SP_LANGUAGE_TITLES, "");
        List<String> langugeTitles = JSON.parseArray(langugeTitle, String.class);
        mLanguagetitlesTv.setText("Lauguges Titles（" + langugeTitles.size() + "）");

        String type = SPUtils.getString(UIUtils.getContext(), Constants.SP_SORT_TYPE, "Most Stars");
        mSorttypeTv.setText("Sort Type（" + type + "）");

        String cahceTime = SPUtils.getString(UIUtils.getContext(), Constants.SP_CACHE_TIME, "3 分钟");
        mCachetimeTv.setText("Cache Time（" + cahceTime + "）");
    }

    public void selectLanguge(View view) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(this)
                .title("First Languge")
                .items(R.array.items)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        SPUtils.putString(UIUtils.getContext(), Constants.SP_FIRST_LANGUAGE, text + "");
                        mLanguageTv.setText("First Languge（" + text + "）");
                        return true;
                    }
                })
                .positiveText("OK")
                .negativeText("CANCEL")
                .show();

        String languge = SPUtils.getString(UIUtils.getContext(), Constants.SP_FIRST_LANGUAGE, "Java");
        ArrayList<CharSequence> items = materialDialog.getItems();
        for (int i = 0; i < items.size(); i++) {
            if (String.valueOf(items.get(i)).equals(languge)) {
                materialDialog.setSelectedIndex(i);
            }
        }


    }

    public void selectLangugeTitles(View view) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(this)
                .title("Languge Titles")
                .items(R.array.items)
                .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        if (text.length > 4) {
                            Toast.makeText(UIUtils.getContext(), "最多只能选择四个标签", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        SPUtils.putString(UIUtils.getContext(), Constants.SP_LANGUAGE_TITLES, JSON.toJSONString(text) + "");
                        mLanguagetitlesTv.setText("Lauguges Titles（" + text.length + "）");
                        return true;
                    }
                })
                .positiveText("OK")
                .negativeText("CANCEL")
                .show();

        String langugeTitle = SPUtils.getString(UIUtils.getContext(), Constants.SP_LANGUAGE_TITLES, "");
        List<String> langugeTitles = JSON.parseArray(langugeTitle, String.class);
        ArrayList<CharSequence> items = materialDialog.getItems();
        ArrayList<Integer> indexs = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (langugeTitles.contains(String.valueOf(items.get(i)))) {
                indexs.add(i);
            }
        }

        Integer[] indexIndices = new Integer[indexs.size()];
        materialDialog.setSelectedIndices(indexs.toArray(indexIndices));


    }

    public void selectCacheTime(View view) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(this)
                .title("Cache Time")
                .items(R.array.cachetime_items)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        SPUtils.putString(UIUtils.getContext(), Constants.SP_CACHE_TIME, text + "");
                        mCachetimeTv.setText("Cache Time（" + text + "）");
                        Toast.makeText(UIUtils.getContext(), "设置成功，重启后生效", Toast.LENGTH_SHORT).show();
                        return true;
                    }

                })
                .positiveText("OK")
                .negativeText("CANCEL")
                .show();

        String cahceTime = SPUtils.getString(UIUtils.getContext(), Constants.SP_CACHE_TIME, "3 分钟");
        ArrayList<CharSequence> items = materialDialog.getItems();
        for (int i = 0; i < items.size(); i++) {
            if (String.valueOf(items.get(i)).equals(cahceTime)) {
                materialDialog.setSelectedIndex(i);
            }
        }
    }

    public void clearCache(View view) {
        ActivityUtils.clearCache();
        Toast.makeText(UIUtils.getContext(), "缓存清除成功（刷新也会清空缓存）", Toast.LENGTH_SHORT).show();
    }

    public void selectSortType(View view) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(this)
                .title("Sort Type")
                .items(R.array.sort_type_items)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        SPUtils.putString(UIUtils.getContext(), Constants.SP_SORT_TYPE, text + "");
                        mSorttypeTv.setText("Sort Type（" + text + "）");
                        return true;
                    }

                })
                .positiveText("OK")
                .negativeText("CANCEL")
                .show();

        String type = SPUtils.getString(UIUtils.getContext(), Constants.SP_SORT_TYPE, "Most Stars");
        ArrayList<CharSequence> items = materialDialog.getItems();
        for (int i = 0; i < items.size(); i++) {
            if (String.valueOf(items.get(i)).equals(type)) {
                materialDialog.setSelectedIndex(i);
            }
        }

    }

    //Home的展开事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void signOut(View view) {
        ActivityUtils.clearCache();
        SPUtils.putString(UIUtils.getContext(), Constants.SP_BASEAUTH, "");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
        HomeActivity.finishHome();
    }

}
