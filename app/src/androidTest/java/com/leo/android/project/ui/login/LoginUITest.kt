package com.leo.android.project.ui.login

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.leo.android.project.R
import com.leo.android.project.data.model.LoginResponse
import com.leo.android.project.di.rule.DaggerTestApplication
import com.leo.android.project.di.rule.impl.DaggerFakeApplication
import com.leo.android.project.di.rule.impl.DaggerMockApplication
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.lang.RuntimeException
import java.util.*

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginUITest {

    private val navController: NavController = Mockito.mock(NavController::class.java)

    private lateinit var di: DaggerTestApplication
    private lateinit var vm: LoginViewModel

    private var username = "leo"
    private var password = "password"

    private fun init(testGraph: DaggerTestApplication = DaggerFakeApplication()) {
        di = testGraph

        val fragment =
            launchFragmentInContainer<LoginFragment>(themeResId = R.style.AppTheme)
        fragment.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
            vm = it.viewModel
        }
    }

    fun registerIdling(){
        IdlingRegistry.getInstance().register(di.idlingResource.getResource())
    }

    fun deregisterIdling(){
        IdlingRegistry.getInstance().unregister(di.idlingResource.getResource())
    }

    @Test
    fun a_test_login_username_validation() = runBlocking<Unit> {
        init()

        assertNull("Username validation error message should be null on boot", vm.user.usernameError)

        onView(withId(R.id.edtTxtViewUsername))
            .perform(clearText(), closeSoftKeyboard())

        onView(withId(R.id.btnLogin))
            .perform(click())

        assertNotNull("Username validation error message is empty", vm.user.usernameError)
    }

    @Test
    fun b_test_login_password_validation() = runBlocking<Unit> {
        init()

        onView(withId(R.id.edtTxtViewUsername))
            .perform(clearText(), typeText(username), closeSoftKeyboard())

        assertNull("Password validation error message should be null on boot", vm.pass.passwordError)

        onView(withId(R.id.edtTxtViewPassword))
            .perform(clearText(), closeSoftKeyboard())

        onView(withId(R.id.btnLogin))
            .perform(click())

        assertNotNull("Password validation error message is empty", vm.pass.passwordError)
    }

    @Test
    fun c_test_login_success() = runBlocking<Unit> {
        init()

        onView(withId(R.id.edtTxtViewUsername))
            .perform(clearText(), typeText(username), closeSoftKeyboard())

        onView(withId(R.id.edtTxtViewPassword))
            .perform(clearText(), typeText(password), closeSoftKeyboard())

        onView(withId(R.id.btnLogin))
            .perform(click())

        onView(withId(R.id.txt_message))
            .check(matches(isDisplayed()))

        onView(withId(R.id.btnOk))
            .perform(click())
    }

    @Test
    fun d_test_login_mock_api_invalid_username_password() = runBlocking<Unit> {
        val authenticationFailure = "Invalid username or password"

        init(DaggerMockApplication().apply {
            `when`(mockedRemote.login(username, password))
                .thenReturn(LoginResponse("").apply {
                    statusCode = Int.MAX_VALUE
                    message = authenticationFailure
                })
        })

        onView(withId(R.id.edtTxtViewUsername))
            .perform(clearText(), typeText(username), closeSoftKeyboard())

        onView(withId(R.id.edtTxtViewPassword))
            .perform(clearText(), typeText(password), closeSoftKeyboard())

        onView(withId(R.id.btnLogin))
            .perform(click())

        onView(withId(R.id.txt_message))
            .check(matches(withText(authenticationFailure)))

        onView(withId(R.id.btnOk))
            .perform(click())
    }

    @Test
    fun e_test_login_mock_api_exception_handling() = runBlocking<Unit> {
        val exceptionMessage = "You were expecting something else ?"

        init(DaggerMockApplication().apply {
            `when`(mockedRemote.login(username, password))
                .thenThrow(RuntimeException(exceptionMessage))
        })

        onView(withId(R.id.edtTxtViewUsername))
            .perform(clearText(), typeText(username), closeSoftKeyboard())

        onView(withId(R.id.edtTxtViewPassword))
            .perform(clearText(), typeText(password), closeSoftKeyboard())

        onView(withId(R.id.btnLogin))
            .perform(click())

        onView(withId(R.id.txt_message))
            .check(matches(withText(exceptionMessage)))

        onView(withId(R.id.btnOk))
            .perform(click())
    }

    @Test
    fun f_test_login_success_navigation() = runBlocking<Unit> {
        val randomAccessCode = UUID.randomUUID().toString()

        init(DaggerMockApplication().apply {
            `when`(mockedRemote.login(username, password))
                .thenReturn(LoginResponse(randomAccessCode).apply {
                    statusCode = 200
                })
        })

        onView(withId(R.id.edtTxtViewUsername))
            .perform(clearText(), typeText(username), closeSoftKeyboard())

        onView(withId(R.id.edtTxtViewPassword))
            .perform(clearText(), typeText(password), closeSoftKeyboard())

        onView(withId(R.id.btnLogin))
            .perform(click())

        onView(withId(R.id.btnOk))
            .perform(click())

        verify(navController)
            .navigate(LoginFragmentDirections.actionLoginFragmentToGenreFragment(
                randomAccessCode
            ))
    }

}