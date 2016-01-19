package news.agoda.com.sample.domain;

import android.content.Context;

import java.util.List;


import news.agoda.com.sample.data.DataRepository;
import news.agoda.com.sample.domain.entity.NewsEntity;
import rx.Observable;
import rx.functions.Func1;

public class GetNewsImpl implements GetNews {
    DataRepository dataRepository;
    Context context;

    public GetNewsImpl(Context context) {
        this.context = context;
        dataRepository = new DataRepository(context);
    }

    @Override
    public Observable<List<NewsEntity>> getNews() {
        return dataRepository.getNews().cache();
    }


    @Override
    public Observable<NewsEntity> getNewsByLink(final String link) {
        dataRepository = new DataRepository(context);
        return dataRepository.getNews().flatMap(new Func1<List<NewsEntity>, Observable<NewsEntity>>() {
            @Override
            public Observable<NewsEntity> call(List<NewsEntity> newsEntityList) {
                for (NewsEntity newsEntity : newsEntityList) {
                    if (newsEntity.getArticleUrl().equals(link)) {
                        return Observable.just(newsEntity);
                    }
                }
                return Observable.error(new Throwable("Not found"));
            }
        });
    }
}
