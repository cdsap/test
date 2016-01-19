package news.agoda.com.sample.ui;


import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import news.agoda.com.sample.R;
import news.agoda.com.sample.domain.entity.NewsEntity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(
            MainActivity.class);

    @Test
    public void testFirstElementIsLaunchingNewActivity() {
        onData(allOf(is(instanceOf(NewsEntity.class))))
                .inAdapterView(withId(R.id.list_news))
                .atPosition(0).perform(click());
        intended(hasComponent(DetailViewActivity.class.getName()));
    }

}
