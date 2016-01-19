package news.agoda.com.sample.domain;

import java.util.List;

import news.agoda.com.sample.domain.entity.NewsEntity;
import rx.Observable;

/**
 * Created by ivil0001 on 19/01/16.
 */
public interface GetNews {
    Observable<List<NewsEntity>> getNews();

    Observable<NewsEntity> getNewsByLink(String link);
}
