package com.example.homework1.course.views


import android.view.View
import android.view.ViewGroup
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.util.HumanReadables
import androidx.test.espresso.util.TreeIterables
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.homework1.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeoutException

/**
 * https://stackoverflow.com/questions/49796132/android-espresso-wait-for-text-to-appear
 * Perform action of waiting for a specific view id.
 * @param text The id of the view to wait for.
 * @param millis The timeout of until when to wait for.
 */
fun waitString(text: String, millis: Long, shouldBeFound: Boolean): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return isRoot()
        }

        override fun getDescription(): String {
            return "wait for a specific view with text <$text> during $millis millis."
        }

        override fun perform(uiController: UiController, view: View) {
            uiController.loopMainThreadUntilIdle()
            val startTime = System.currentTimeMillis()
            val endTime = startTime + millis
            val viewMatcher = withText(text)

            do {
                for (child in TreeIterables.breadthFirstViewTraversal(view)) {
                    // found view with required ID
                    if (viewMatcher.matches(child)) {
                        if (shouldBeFound) return
                    }
                }

                uiController.loopMainThreadForAtLeast(50)
            } while (System.currentTimeMillis() < endTime)

            if (!shouldBeFound) return

            // timeout happens
            throw PerformException.Builder()
                .withActionDescription(this.description)
                .withViewDescription(HumanReadables.describe(view))
                .withCause(TimeoutException())
                .build()
        }
    }
}

fun waitId(id: Int, millis: Long, shouldBeFound: Boolean): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return isRoot()
        }

        override fun getDescription(): String {
            return "wait for a specific view with text <$id> during $millis millis."
        }

        override fun perform(uiController: UiController, view: View) {
            uiController.loopMainThreadUntilIdle()
            val startTime = System.currentTimeMillis()
            val endTime = startTime + millis
            val viewMatcher = withId(id)

            do {
                for (child in TreeIterables.breadthFirstViewTraversal(view)) {
                    // found view with required ID
                    if (viewMatcher.matches(child)) {
                        if (shouldBeFound) return
                    }
                }

                uiController.loopMainThreadForAtLeast(50)
            } while (System.currentTimeMillis() < endTime)

            if (!shouldBeFound) return

            // timeout happens
            throw PerformException.Builder()
                .withActionDescription(this.description)
                .withViewDescription(HumanReadables.describe(view))
                .withCause(TimeoutException())
                .build()
        }
    }
}



@LargeTest
@RunWith(AndroidJUnit4::class)
class EspressoAutomatedTest {
    companion object {

        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            InstrumentationRegistry.getTargetContext().deleteDatabase("poke-base")
        }

    }

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun espressoAutomatedTest() {
        /*onView(isRoot()).perform(waitId(android.R.id.progress, 5000, true))

        val progressBar = onView(
            allOf(
                withId(android.R.id.progress),
                childAtPosition(
                    allOf(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        progressBar.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                IsInstanceOf.instanceOf(android.widget.TextView::class.java), withText("Ładowanie pokemonów…"),
                childAtPosition(
                    allOf(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Ładowanie pokemonów…")))

        onView(isRoot()).perform(waitString("Ładowanie pokemonów…", 360 * 1000, false))
         */

        val appCompatEditText = onView(
            allOf(
                withId(R.id.LoginTest),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(click())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.LoginTest),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("x"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.LoginTest), withText("x"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(pressImeActionButton())

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.PasswordText),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(replaceText("x"), closeSoftKeyboard())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.PasswordText), withText("x"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatEditText5.perform(pressImeActionButton())

        val appCompatButton = onView(
            allOf(
                withId(R.id.loginButton), withText("Zaloguj"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val editText = onView(
            allOf(
                withId(R.id.LoginTest), withText("x"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        editText.check(matches(withText("x")))

        val editText2 = onView(
            allOf(
                withId(R.id.PasswordText), withText("•"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        editText2.check(matches(withText("•")))

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.newUserButton), withText("Stwórz nowe konto"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        appCompatButton2.perform(click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.LoginTest),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText6.perform(click())

        val appCompatEditText7 = onView(
            allOf(
                withId(R.id.LoginTest),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText7.perform(replaceText("x"), closeSoftKeyboard())

        val appCompatEditText8 = onView(
            allOf(
                withId(R.id.LoginTest), withText("x"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText8.perform(pressImeActionButton())

        val appCompatEditText9 = onView(
            allOf(
                withId(R.id.NameTest),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText9.perform(replaceText("x"), closeSoftKeyboard())

        val appCompatEditText10 = onView(
            allOf(
                withId(R.id.NameTest), withText("x"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText10.perform(pressImeActionButton())

        val appCompatEditText11 = onView(
            allOf(
                withId(R.id.SurnameTest),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatEditText11.perform(replaceText("x"), closeSoftKeyboard())

        val appCompatEditText12 = onView(
            allOf(
                withId(R.id.SurnameTest), withText("x"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatEditText12.perform(pressImeActionButton())

        val appCompatEditText13 = onView(
            allOf(
                withId(R.id.PasswordText),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        appCompatEditText13.perform(replaceText("x"), closeSoftKeyboard())

        val appCompatEditText14 = onView(
            allOf(
                withId(R.id.PasswordText), withText("x"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        appCompatEditText14.perform(pressImeActionButton())

        val editText3 = onView(
            allOf(
                withId(R.id.LoginTest), withText("x"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        editText3.check(matches(withText("x")))

        val editText4 = onView(
            allOf(
                withId(R.id.NameTest), withText("x"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        editText4.check(matches(withText("x")))

        val editText5 = onView(
            allOf(
                withId(R.id.SurnameTest), withText("x"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        editText5.check(matches(withText("x")))

        val editText6 = onView(
            allOf(
                withId(R.id.PasswordText), withText("•"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        editText6.check(matches(withText("•")))

        val appCompatButton3 = onView(
            allOf(
                withId(R.id.button), withText("Stwórz nowe konto"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    8
                ),
                isDisplayed()
            )
        )
        appCompatButton3.perform(click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

        val appCompatEditText15 = onView(
            allOf(
                withId(R.id.LoginTest),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText15.perform(replaceText("x"), closeSoftKeyboard())

        val appCompatEditText16 = onView(
            allOf(
                withId(R.id.LoginTest), withText("x"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText16.perform(pressImeActionButton())

        val appCompatEditText17 = onView(
            allOf(
                withId(R.id.PasswordText),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatEditText17.perform(replaceText("x"), closeSoftKeyboard())

        val appCompatEditText18 = onView(
            allOf(
                withId(R.id.PasswordText), withText("x"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatEditText18.perform(pressImeActionButton())

        val appCompatButton4 = onView(
            allOf(
                withId(R.id.loginButton), withText("Zaloguj"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )

        appCompatButton4.perform(click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

        val textView2 = onView(
            allOf(
                withId(R.id.pokemonName),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.pokemonListView),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(isDisplayed()))

        val linearLayout = onData(anything())
            .inAdapterView(
                allOf(
                    withId(R.id.pokemonListView),
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    )
                )
            )
            .atPosition(0)
        linearLayout.perform(click())

        val textView3 = onView(
            allOf(
                withId(R.id.pokemon_name),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(isDisplayed()))

        val appCompatButton5 = onView(
            allOf(
                withId(R.id.catchPokemonButton), withText("Złap je wszystkie!"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatButton5.perform(click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

        val imageView = onView(
            allOf(
                withId(R.id.wildPokemon),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val imageView2 = onView(
            allOf(
                withId(R.id.pokemonImage),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        imageView2.check(matches(isDisplayed()))

        val textView4 = onView(
            allOf(
                withId(R.id.availablePokeballs), withText("PokeBall: 5"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("PokeBall: 5")))

        val appCompatButton6 = onView(
            allOf(
                withId(R.id.catchButton), withText("ŁAP"),
                childAtPosition(
                    allOf(
                        withId(R.id.bottomBar),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            5
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatButton6.perform(click())

        val textView5 = onView(
            allOf(
                withId(R.id.availablePokeballs), withText("PokeBall: 4"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("PokeBall: 4")))

        val appCompatButton7 = onView(
            allOf(
                withId(R.id.catchButton), withText("ŁAP"),
                childAtPosition(
                    allOf(
                        withId(R.id.bottomBar),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            5
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatButton7.perform(click())

        val textView6 = onView(
            allOf(
                withId(R.id.availablePokeballs), withText("PokeBall: 3"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textView6.check(matches(withText("PokeBall: 3")))

        val appCompatButton8 = onView(
            allOf(
                withId(R.id.goBack), withText("Powrót"),
                childAtPosition(
                    allOf(
                        withId(R.id.topBar),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatButton8.perform(click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

        pressBack()

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

        val editText7 = onView(
            allOf(
                withId(R.id.LoginTest),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        editText7.check(matches(withText("")))

        val editText8 = onView(
            allOf(
                withId(R.id.PasswordText),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        editText8.check(matches(withText("")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
