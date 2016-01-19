package news.agoda.com.sample.presenter;

import android.content.Context;

import java.util.List;

import news.agoda.com.sample.domain.GetNewsImpl;
import news.agoda.com.sample.domain.entity.NewsEntity;
import news.agoda.com.sample.presenter.view.MainView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class MainPresenter {
    MainView mainView;
    GetNewsImpl getNews;
    Context context;

    public MainPresenter(MainView mainView, Context context) {
        this.mainView = mainView;
        this.context = context;
        getNews = new GetNewsImpl(context);
    }

    public void init() {
        mainView.showProgress();
        getNews.getNews()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<NewsEntity>>() {
                    @Override
                    public void call(List<NewsEntity> newsEntityList) {
                        mainView.closeProgress();
                        mainView.onNewsReceived(newsEntityList);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mainView.closeProgress();
                        mainView.showError(throwable.getMessage());

                    }
                });
    }
}
