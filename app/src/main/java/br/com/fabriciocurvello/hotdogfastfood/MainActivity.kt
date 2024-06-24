package br.com.fabriciocurvello.hotdogfastfood

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    //Iniciando as variáveis e definindo os seus tipos:
    lateinit var proteinaRg: RadioGroup
    lateinit var catchupCb: CheckBox
    lateinit var mostardaCb: CheckBox
    lateinit var maioneseCb: CheckBox
    lateinit var alfaceSw: Switch
    lateinit var tomateSW: Switch
    lateinit var queijoRaladoSw: Switch
    lateinit var nomeEt: EditText
    lateinit var pedidoBt: Button
    lateinit var saidaPedidoTv: TextView

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         enableEdgeToEdge()
         setContentView(R.layout.activity_main)
         ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
             val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
             v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
             insets
         }

         //"Linkando" as variáveis às views do activity_main.xml
         pedidoBt = findViewById(R.id.bt_pedido)
         proteinaRg = findViewById(R.id.rg_proteina)
         catchupCb = findViewById(R.id.cb_catchup)
         mostardaCb = findViewById(R.id.cb_mostarda)
         maioneseCb = findViewById(R.id.cb_maionese)
         alfaceSw = findViewById(R.id.sw_alface)
         tomateSW = findViewById(R.id.sw_tomate)
         queijoRaladoSw = findViewById(R.id.sw_queijo_ralado)



         //Listener no Botão
         pedidoBt.setOnClickListener {
             val proteinaSelecionada = proteina()
             val molhosSelecionados = molhos()
             val acompanhamentosSelecionados = acompanhamentos()


            Toast.makeText(this,"$acompanhamentosSelecionados", Toast.LENGTH_SHORT).show()

         }// fim do Listener

     }// fim do onCreate

    fun proteina(): String {
        val proteinaSelecionadaId = proteinaRg.checkedRadioButtonId
        var proteinaSelecionadaStr: String = "Proteína"

        if (proteinaSelecionadaId != -1) {
            val proteinaSelecionada: RadioButton = findViewById(proteinaSelecionadaId)

            if (proteinaSelecionada.toString().contains("linguica")) {
                proteinaSelecionadaStr = "Linguiça"
            }
            if (proteinaSelecionada.toString().contains("salsicha")) {
                proteinaSelecionadaStr = "Salsicha"
            }
        }
        return "Proteína: $proteinaSelecionadaStr"
    }

    fun molhos(): String {
        var molhosSelecionados = "Molhos selecionados: "

        val catchup = catchupCb.isChecked
        if (catchup) {
            molhosSelecionados += "\n - Catchup"
        }

        val mostarda = mostardaCb.isChecked
        if (mostarda) {
            molhosSelecionados += "\n - Mostarda"
        }

        val maionese = maioneseCb.isChecked
        if (maionese) {
            molhosSelecionados += "\n - Maionese"
        }

        if (molhosSelecionados == "Molhos selecionados: ") {
            molhosSelecionados = "Sem molho."
        }

        return molhosSelecionados
    }

    private fun acompanhamentos(): String {
        var acompanhamentosSelecionados = "Acompanhamentos: "

        val alface = alfaceSw.isChecked
        if (alface) {
            acompanhamentosSelecionados += "\n - Alface"
        }

        val tomate = tomateSW.isChecked
        if (tomate) {
            acompanhamentosSelecionados += "\n - Tomate"
        }

        val queijoRalado = queijoRaladoSw.isChecked
        if (queijoRalado) {
            acompanhamentosSelecionados += "\n - Queijo Ralado"
        }

        if (acompanhamentosSelecionados == "Acompanhamentos: ") {
            acompanhamentosSelecionados = "Sem acompanhamentos"
        }
        return acompanhamentosSelecionados
    }
}