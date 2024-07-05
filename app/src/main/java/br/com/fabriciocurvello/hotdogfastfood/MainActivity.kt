package br.com.fabriciocurvello.hotdogfastfood

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
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

         setupViews()
         setupListeners()// fim do Listener
     }// fim do onCreate

    private fun setupViews() {
        //"Linkando" as variáveis às views do activity_main.xml
        nomeEt = findViewById(R.id.et_nome)
        pedidoBt = findViewById(R.id.bt_pedido)
        proteinaRg = findViewById(R.id.rg_proteina)
        catchupCb = findViewById(R.id.cb_catchup)
        mostardaCb = findViewById(R.id.cb_mostarda)
        maioneseCb = findViewById(R.id.cb_maionese)
        alfaceSw = findViewById(R.id.sw_alface)
        tomateSW = findViewById(R.id.sw_tomate)
        queijoRaladoSw = findViewById(R.id.sw_queijo_ralado)
        saidaPedidoTv = findViewById(R.id.tv_saida_pedido)
    }

    private fun setupListeners() {
        //Listener no Botão
        pedidoBt.setOnClickListener {
            val cliente = nomeCliente()
            val proteinaSelecionada = proteina()
            val molhosSelecionados = molhos()
            val acompanhamentosSelecionados = acompanhamentos()

            var pedido = "Cliente: " + cliente + "\n\n" +
                    proteinaSelecionada + "\n" +
                    molhosSelecionados + "\n" +
                    acompanhamentosSelecionados

            saidaPedidoTv.text = pedido
            //Toast.makeText(this,"$cliente", Toast.LENGTH_SHORT).show()
        }
    }



    private fun nomeCliente(): String {
        var cliente = nomeEt.text.toString()
        if (cliente.isNullOrEmpty()) {
            cliente = "Não informou nome"
        }
        return cliente
    }

    private fun proteina(): String {
        val proteinaSelecionadaId = proteinaRg.checkedRadioButtonId
        var proteinaSelecionadaStr: String = ""

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

//    private fun molhos(): String {
//        var molhosSelecionados = "Molhos selecionados: "
//
//        val catchup = catchupCb.isChecked
//        if (catchup) {
//            molhosSelecionados += "\n - Catchup"
//        }
//        val mostarda = mostardaCb.isChecked
//        if (mostarda) {
//            molhosSelecionados += "\n - Mostarda"
//        }
//        val maionese = maioneseCb.isChecked
//        if (maionese) {
//            molhosSelecionados += "\n - Maionese"
//        }
//        if (molhosSelecionados == "Molhos selecionados: ") {
//            molhosSelecionados = "Sem molho."
//        }
//        return molhosSelecionados
//    }


    //FUN molhos() resumida usando mutableListOf:
    private fun molhos(): String {
        val molhosSelecionados = mutableListOf<String>()

        if (catchupCb.isChecked) molhosSelecionados.add("Catchup")
        if (mostardaCb.isChecked) molhosSelecionados.add("Mostarda")
        if (maioneseCb.isChecked) molhosSelecionados.add("Maionese")

        return if (molhosSelecionados.isEmpty()) "Sem molho."
               else "Molhos selecionados:\n - ${molhosSelecionados.joinToString("\n - ")}"
    }

//    private fun acompanhamentos(): String {
//        var acompanhamentosSelecionados = "Acompanhamentos: "
//
//        val alface = alfaceSw.isChecked
//        if (alface) {
//            acompanhamentosSelecionados += "\n - Alface"
//        }
//        val tomate = tomateSW.isChecked
//        if (tomate) {
//            acompanhamentosSelecionados += "\n - Tomate"
//        }
//        val queijoRalado = queijoRaladoSw.isChecked
//        if (queijoRalado) {
//            acompanhamentosSelecionados += "\n - Queijo Ralado"
//        }
//        if (acompanhamentosSelecionados == "Acompanhamentos: ") {
//            acompanhamentosSelecionados = "Sem acompanhamentos"
//        }
//        return acompanhamentosSelecionados
//    }

    //FUN acompanhamentos() resumida usando mutableListOf:
    private fun acompanhamentos(): String {
        val acompanhamentosSelecionados = mutableListOf<String>()

        if (alfaceSw.isChecked) acompanhamentosSelecionados.add("Alface")
        if (tomateSW.isChecked) acompanhamentosSelecionados.add("Tomate")
        if (queijoRaladoSw.isChecked) acompanhamentosSelecionados.add("Queijo Ralado")

        return if (acompanhamentosSelecionados.isEmpty()) "Sem acompanhamentos"
        else "Acompanhamentos:\n - ${acompanhamentosSelecionados.joinToString("\n - ")}"
    }
}