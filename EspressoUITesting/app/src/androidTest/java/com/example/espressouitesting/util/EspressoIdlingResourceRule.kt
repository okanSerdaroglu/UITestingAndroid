package com.example.espressouitesting.util

import androidx.test.espresso.IdlingRegistry

import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Option 1:
 * This option is much more difficult to read and is more verbose
 */
/**
class EspressoIdlingResourceRule : TestRule {


override fun apply(base: Statement?, description: Description?): Statement {
return IdlingResourceStatement(base)
}

class IdlingResourceStatement(private val base: Statement?) : Statement() {

private val idlingResource = EspressoIdlingResource.countingIdlingResource

override fun evaluate() {
// before
IdlingRegistry.getInstance().register(idlingResource)
try {
base?.evaluate()
?: throw Exception("Error evaluating test. Base statement is null.")
} finally {
// after
IdlingRegistry.getInstance().unregister(idlingResource)
}
}
}

}*/


/**
 * option 2
 * Simplified of option 1. (TestWatcher class implements TestRule)
*/
class EspressoIdlingResourceRule : TestWatcher() {

    private val idlingResource = EspressoIdlingResource.countingIdlingResource

    // Before
    override fun starting(description: Description?) {
        IdlingRegistry.getInstance().register(idlingResource)
        super.starting(description)
    }

    // After
    override fun finished(description: Description?) {
        IdlingRegistry.getInstance().unregister(idlingResource)
        super.finished(description)
    }

}