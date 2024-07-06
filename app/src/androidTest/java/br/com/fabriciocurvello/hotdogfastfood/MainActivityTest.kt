package br.com.fabriciocurvello.hotdogfastfood

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun nomeClienteTest() {

        // cenário
        val launch = ActivityScenario.launch(MainActivity::class.java)

        val clienteFake = "Roberto Claudio"

        Thread.sleep(2000) //apenas para podermos acompanhar. 2seg de delay.
        //Clicar no et_nome
        Espresso.onView(ViewMatchers.withId(R.id.et_nome)).perform(ViewActions.click())

        Thread.sleep(2000) //apenas para podermos acompanhar. 2seg de delay.
        //Digitar clienteFake no et_nome
        Espresso.onView(ViewMatchers.withId(R.id.et_nome)).perform(ViewActions.typeText(clienteFake))

        Thread.sleep(2000) //apenas para podermos acompanhar. 2seg de delay.
        //Pressionar bt_pedido
        Espresso.onView(ViewMatchers.withId(R.id.bt_pedido)).perform(ViewActions.click())

        Thread.sleep(2000) //apenas para podermos acompanhar. 2seg de delay.
        //Validar se tv_saida_pedido contém clienteFake
        Espresso.onView(ViewMatchers.withId(R.id.tv_saida_pedido)).check(ViewAssertions.matches(ViewMatchers.withText("Cliente: Roberto Claudio

                Proteína: Salsicha
                Sem molho.
                Sem acompanhamentos"")))

        Thread.sleep(2000) //apenas para podermos acompanhar. 2seg de delay.
    }


}