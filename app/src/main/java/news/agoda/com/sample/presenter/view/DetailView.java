package news.agoda.com.sample.presenter.view;

import news.agoda.com.sample.domain.entity.NewsEntity;

/**
 * Created by ivil0001 on 19/01/16.
 */
public interface DetailView {

        void showError(String message);

        void closeProgress();

        void onNewReceived(NewsEntity newsEntity);

        void showProgress();
    }

