package news.agoda.com.sample.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by inaki on 19/01/16.
 */
public class JsonWrapper {

    String url;

    public JsonWrapper(String creator) {
        this.url = creator;
    }

    public JSONObject getJsonObject() throws JSONException {
        return new JSONObject(url);
    }
}
