package news.agoda.com.sample.ui;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by ivil0001 on 19/01/16.
 */
@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {
    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(
            MainActivity.class);

    @Test
    public void testFabButtonIsClickable() {
        //    onView(withId(R.id.fab)).perform(click());
       // onView(allOf(withId(android.support.design.R.id.snackbar_text), withText("Replace with your own action")))
       //         .check(matches(isDisplayed()));
    }


}
