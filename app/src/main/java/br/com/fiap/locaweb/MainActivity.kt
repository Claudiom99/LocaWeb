package br.com.fiap.locaweb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.locaweb.screens.*
import br.com.fiap.locaweb.ui.theme.MonitoramentoDeTemperaturaTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent {
            MonitoramentoDeTemperaturaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val controleGeral = rememberNavController()
                    NavHost(navController = controleGeral, startDestination = "login") {
                        composable(route = "login") {
                            LoginScreen(controleGeral)
                        }
                        composable(route = "menu/{usuario}") {
                            backStackEntry ->
                            MenuScreen(controleGeral, backStackEntry)
                        }
                        composable(route = "Categorias") {
                            CategorizationScreen(controleGeral)
                        }
                        composable(route = "Todos") {
                            EmailScreen(controleGeral)
                        }
                        composable(route = "Social") {
                            EmailScreen2(controleGeral)
                        }
                        composable(route = "Trabalho") {
                            EmailScreen1(controleGeral)
                        }
                        composable(route = "Bancos") {
                            EmailScreen3(controleGeral)
                        }
                        composable(route = "Vagas_emp") {
                            EmailScreen4(controleGeral)
                        }
                        composable(route = "cadastre-se") {
                            CadastrarScreen(controleGeral)
                        }
                        composable(route = "novoEmail") {
                            NovoEmailScreen(controleGeral)
                        }
                        composable(route = "emailRecebido") {
                            EmailRecebidoScreen(controleGeral)
                        }
                        composable(route = "agendar_evento") {
                            AgendarEventoScreen(controleGeral)
                        }
                        composable(route = "alterarCadastro/{usuario}"){
                            backStackEntry ->
                            AlteracaoScreen(controleGeral, backStackEntry)
                        }
                    }
                }
            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MonitoramentoDeTemperaturaTheme {

    }
}*/
