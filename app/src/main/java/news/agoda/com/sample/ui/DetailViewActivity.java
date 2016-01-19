package news.agoda.com.sample.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import news.agoda.com.sample.R;
import news.agoda.com.sample.domain.entity.NewsEntity;
import news.agoda.com.sample.presenter.DetailPresenter;
import news.agoda.com.sample.presenter.view.DetailView;

/**
 * News detail view
 */
public class DetailViewActivity extends FragmentActivity implements DetailView {
    public static final String EXTRA_LINK = "extra";
    private ProgressDialog progress;
    private TextView titleView;
    private DraweeView imageView;
    private TextView summaryView;
    private DetailPresenter detailPresenter;
    private String storyUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initViews();
        detailPresenter = new DetailPresenter(this, this);

        Bundle extras = getIntent().getExtras();
        if (TextUtils.isEmpty(extras.getString(EXTRA_LINK))) {
            showError("No extra found");
        } else {
            detailPresenter.init(extras.getString(EXTRA_LINK));
        }
    }

    private void initViews() {
        titleView = (TextView) findViewById(R.id.title);
        imageView = (DraweeView) findViewById(R.id.news_image);
        summaryView = (TextView) findViewById(R.id.summary_content);
    }

    public void onFullStoryClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(storyUrl));
        startActivity(intent);
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
    public void onNewReceived(NewsEntity newsEntity) {
        storyUrl = newsEntity.getArticleUrl();
        titleView.setText(newsEntity.getTitle());
        summaryView.setText(newsEntity.getSummary());
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(ImageRequest.fromUri(Uri.parse(newsEntity.getMediaEntity().get(0).getUrl())))
                .setOldController(imageView.getController()).build();
        imageView.setController(draweeController);
    }

    @Override
    public void showProgress() {
        progress = ProgressDialog.show(this, "", "Loading", true);
    }
}
