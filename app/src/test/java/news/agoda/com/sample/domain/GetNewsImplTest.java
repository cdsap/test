package news.agoda.com.sample.domain;

import android.content.Context;
import android.content.res.Resources;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import news.agoda.com.sample.data.JsonWrapper;
import news.agoda.com.sample.domain.entity.NewsEntity;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * Created by inaki on 19/01/16.
 */

@RunWith(MockitoJUnitRunner.class)
public class GetNewsImplTest {
    @Mock
    Context context;
    @Mock
    Resources resources;
    @Mock
    InputStream inputStream;
    @Mock
    BufferedReader streamReaderMock;
    @Mock
    JSONObject jsonObject;
    @Mock
    JSONTokener jsonObjectToken;
    @Mock
    JsonWrapper jsonWrapper;

    @Before
    public void init() throws IOException, JSONException {
        when(context.getResources()).thenReturn(resources);
        when(resources.openRawResource(anyInt())).thenReturn(inputStream);
        doReturn("firstLine").when(streamReaderMock).readLine();
        when(jsonWrapper.getJsonObject()).thenReturn(jsonObject);
    }


    @Test
    public void testGetNews() {
        GetNewsImpl getNews = new GetNewsImpl(context);
        TestSubscriber<List<NewsEntity>> testSubscriber = new TestSubscriber<>();
        getNews.getNews().subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
    }

    @Test
    public void testGetNewNotFound() {
        GetNewsImpl getNews = new GetNewsImpl(context);
        TestSubscriber<NewsEntity> testSubscriber = new TestSubscriber<>();
        getNews.getNewsByLink("1").subscribe(testSubscriber);
        assertEquals(testSubscriber.getOnErrorEvents().size(), 1);
    }
}
