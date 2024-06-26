package br.com.fiap.monitoramentodetemperatura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.monitoramentodetemperatura.screens.*
import br.com.fiap.monitoramentodetemperatura.ui.theme.MonitoramentoDeTemperaturaTheme

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
                        composable(route = "menu") {
                            MenuScreen(controleGeral)
                        }
                        composable(route = "historico") {
                            HistoricoScreen(controleGeral)
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
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MonitoramentoDeTemperaturaTheme {

    }
}
