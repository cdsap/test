package news.agoda.com.sample.presenter.view;


import java.util.List;

import news.agoda.com.sample.domain.entity.NewsEntity;

public interface MainView {


    void showError(String message);

    void closeProgress();

    void onNewsReceived(List<NewsEntity> newsEntityList);

    void showProgress();
}
