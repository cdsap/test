package news.agoda.com.sample.ui;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import news.agoda.com.sample.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by ivil0001 on 19/01/16.
 */
@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {
    private Context targetContext;
    @Rule
    public IntentsTestRule<DetailViewActivity> mActivityRule = new IntentsTestRule<DetailViewActivity>(DetailViewActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            Fresco.initialize(targetContext);
            Intent result = new Intent(targetContext, DetailViewActivity.class);
            result.putExtra(DetailViewActivity.EXTRA_LINK, "http://bits.blogs.nytimes.com/2015/08/11/nebia-a-shower-head-start-up-gets-funded-by-apple-ceo-cook/");
            return result;
        }
    };

    @Test
    public void testTitleIsLoaded() {
        onView(withId(R.id.title)).check(matches(withText("Nebia, a Shower Head Start-Up, Receives Funding From Timothy Cook of Apple")));
    }

    @Test
    public void testClickButtonLaunchActivity() {
        onView(withId(R.id.full_story_link)).perform(click());
        intended(allOf(hasAction(Intent.ACTION_VIEW), hasData("http://bits.blogs.nytimes.com/2015/08/11/nebia-a-shower-head-start-up-gets-funded-by-apple-ceo-cook/")));
    }

}
