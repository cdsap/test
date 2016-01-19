package news.agoda.com.sample.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import news.agoda.com.sample.R;
import news.agoda.com.sample.domain.entity.NewsEntity;
import news.agoda.com.sample.presenter.MainPresenter;
import news.agoda.com.sample.presenter.view.MainView;

public class MainActivity extends FragmentActivity implements MainView {
    private MainPresenter mainPresenter;
    private ListView listView;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        mainPresenter = new MainPresenter(this, this);
        listView = (ListView) findViewById(R.id.list_news);
        mainPresenter.init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeProgress() {
        progress.dismiss();
    }

    @Override
    public void onNewsReceived(final List<NewsEntity> newsEntityList) {
        NewsListAdapter adapter = new NewsListAdapter(this, R.layout.list_item_news, newsEntityList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsEntity newsEntity = newsEntityList.get(position);
                Intent intent = new Intent(MainActivity.this, DetailViewActivity.class);
                intent.putExtra(DetailViewActivity.EXTRA_LINK, newsEntity.getArticleUrl());
                startActivity(intent);
            }
        });

    }

    @Override
    public void showProgress() {
        progress = ProgressDialog.show(this, "", "Loading", true);
    }
}
