package news.agoda.com.sample.presenter;

import android.content.Context;

import news.agoda.com.sample.domain.GetNewsImpl;
import news.agoda.com.sample.domain.entity.NewsEntity;
import news.agoda.com.sample.presenter.view.DetailView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by ivil0001 on 19/01/16.
 */
public class DetailPresenter {
    DetailView detailView;
    GetNewsImpl getNews;

    public DetailPresenter(DetailView detailView, Context context) {
        this.detailView = detailView;
        getNews = new GetNewsImpl(context);
    }

    public void init(String link) {
        detailView.showProgress();
        getNews.getNewsByLink(link)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsEntity>() {
                    @Override
                    public void call(NewsEntity newsEntityList) {
                        detailView.closeProgress();
                        detailView.onNewReceived(newsEntityList);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        detailView.closeProgress();
                        detailView.showError(throwable.getMessage());
                    }
                });
    }

}
