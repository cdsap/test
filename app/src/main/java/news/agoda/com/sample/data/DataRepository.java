package news.agoda.com.sample.data;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import news.agoda.com.sample.R;
import news.agoda.com.sample.domain.entity.NewsEntity;
import rx.Observable;


public class DataRepository {
    private static final String TAG = DataRepository.class.getSimpleName();
    Context context;

    public DataRepository(Context context) {
        this.context = context;
    }

    public Observable<List<NewsEntity>> getNews() {
        List<NewsEntity> newsItemList;
        newsItemList = new ArrayList<>();

        String newsListSource = loadResource();
        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(newsListSource);
            JSONArray resultArray = jsonObject.getJSONArray("results");
            for (int i = 0; i < resultArray.length(); i++) {
                JSONObject newsObject = resultArray.getJSONObject(i);
                NewsEntity newsEntity = new NewsEntity(newsObject);
                newsItemList.add(newsEntity);
            }
        } catch (JSONException e) {
            Log.e(TAG, "fail to parse json string");

        }
        return Observable.just(newsItemList);
    }

    private String loadResource() {
        InputStream inputStream = context.getResources().openRawResource(R.raw.news_list);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try {
            InputStreamReader inputReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferReader = new BufferedReader(inputReader);
            int n;
            while ((n = bufferReader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            inputStream.close();
        } catch (IOException ioException) {
            return null;
        }

        return writer.toString();
    }
}
