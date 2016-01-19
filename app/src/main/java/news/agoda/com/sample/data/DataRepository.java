package news.agoda.com.sample.data;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import news.agoda.com.sample.R;
import news.agoda.com.sample.domain.entity.NewsEntity;
import rx.Observable;


public class DataRepository {
    Context context;
    JsonWrapper jsonWrapper;
    BufferedReader streamReader;

    public DataRepository(Context context) {
        this.context = context;
    }

    public Observable<List<NewsEntity>> getNews() {
        List<NewsEntity> newsItemList;
        newsItemList = new ArrayList<>();

        try {
            String newsListSource = loadResource();
            JSONObject jsonObject;
            jsonWrapper = new JsonWrapper(newsListSource);
            jsonObject = jsonWrapper.getJsonObject();
            JSONArray resultArray = jsonObject.getJSONArray("results");
            for (int i = 0; i < resultArray.length(); i++) {
                JSONObject newsObject = resultArray.getJSONObject(i);
                NewsEntity newsEntity = new NewsEntity(newsObject);
                newsItemList.add(newsEntity);
            }
        } catch (JSONException e) {
            Observable.error(new Throwable("fail to parse json string"));
        } catch (IOException e) {
            Observable.error(new Throwable("fail to parse json string"));
        }
        return Observable.just(newsItemList);
    }

    public String loadResource() throws IOException {
        InputStream inputStream = context.getResources().openRawResource(R.raw.news_list);
        streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        return read(streamReader);

    }

    public String read(BufferedReader f) throws IOException {
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = f.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        return responseStrBuilder.toString();
    }
}
