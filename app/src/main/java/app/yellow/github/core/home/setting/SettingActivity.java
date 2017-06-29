package app.yellow.github.core.home.setting;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

import app.yellow.github.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Setting");
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public void selectLanguge(View view) {
        new MaterialDialog.Builder(this)
                .title("Languge Titles")
                .items(R.array.items)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        return true;
                    }
                })
                .positiveText("OK")
                .negativeText("CANCEL")
                .show();
    }

    public void selectLangugeTitles(View view) {
        new MaterialDialog.Builder(this)
                .title("First Languge")
                .items(R.array.items)
                .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {

                        return true;
                    }
                })
                .positiveText("OK")
                .negativeText("CANCEL")
                .show();
    }

    public void selectSortType(View view) {
        new MaterialDialog.Builder(this)
                .title("Sort Type")
                .items(R.array.sort_type_items)
                .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {

                        return true;
                    }
                })
                .positiveText("OK")
                .negativeText("CANCEL")
                .show();
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
}
