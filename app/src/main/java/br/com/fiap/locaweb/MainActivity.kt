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
import br.com.fiap.locaweb.screens.AgendarEventoScreen
import br.com.fiap.locaweb.screens.AlteracaoScreen
import br.com.fiap.locaweb.screens.CadastrarScreen
import br.com.fiap.locaweb.screens.CategorizationScreen
import br.com.fiap.locaweb.screens.EmailRecebidoScreen
import br.com.fiap.locaweb.screens.EmailScreen
import br.com.fiap.locaweb.screens.EmailScreen1
import br.com.fiap.locaweb.screens.EmailScreen2
import br.com.fiap.locaweb.screens.EmailScreen3
import br.com.fiap.locaweb.screens.EmailScreen4
import br.com.fiap.locaweb.screens.EmailScreen6
import br.com.fiap.locaweb.screens.LoginScreen
import br.com.fiap.locaweb.screens.MenuScreen
import br.com.fiap.locaweb.screens.NotSpamReceivedEmail
import br.com.fiap.locaweb.screens.NovoEmailScreen
import br.com.fiap.locaweb.screens.SpamEmailsScreens.SpamReceivedEmail
import br.com.fiap.locaweb.screens.SpamEmailsScreens.SpamReceivedEmail2
import br.com.fiap.locaweb.screens.SpamEmailsScreens.SpamReceivedEmail3
import br.com.fiap.locaweb.screens.SpamEmailsScreens.SpamReceivedEmail4
import br.com.fiap.locaweb.screens.SpamEmailsScreens.SpamReceivedEmail5
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
                        composable(route = "Categorias/{usuario}") {
                            backStackEntry ->
                            CategorizationScreen(controleGeral, backStackEntry)
                        }
                        composable(route = "Todos/{usuario}") {
                                backStackEntry ->
                            EmailScreen(controleGeral, backStackEntry)
                        }
                        composable(route = "Social/{usuario}") {
                                backStackEntry ->
                            EmailScreen2(controleGeral, backStackEntry)
                        }
                        composable(route = "Trabalho/{usuario}") {
                                backStackEntry ->
                            EmailScreen1(controleGeral, backStackEntry)
                        }
                        composable(route = "Bancos/{usuario}") {
                                backStackEntry ->
                            EmailScreen3(controleGeral, backStackEntry)
                        }
                        composable(route = "Vagas_emp/{usuario}") {
                                backStackEntry ->
                            EmailScreen4(controleGeral, backStackEntry)
                        }
                        composable(route = "spam_emails/{usuario}") {
                                backStackEntry ->
                            EmailScreen6(controleGeral, backStackEntry)
                        }
                        composable(route = "cadastre-se") {

                            CadastrarScreen(controleGeral)
                        }
                        composable(route = "novoEmail/{usuario}") {
                                backStackEntry ->
                            NovoEmailScreen(controleGeral, backStackEntry)
                        }
                        composable(route = "emailRecebido/{usuario}") {
                                backStackEntry ->
                            NotSpamReceivedEmail(controleGeral, backStackEntry)
                        }
                        composable(route = "emailSpamRecebido/{usuario}") {
                                backStackEntry ->
                            SpamReceivedEmail(controleGeral, backStackEntry)
                        }
                        composable(route = "emailSpamRecebido2/{usuario}") {
                                backStackEntry ->
                            SpamReceivedEmail2(controleGeral, backStackEntry)
                        }
                        composable(route = "emailSpamRecebido3/{usuario}") {
                                backStackEntry ->
                            SpamReceivedEmail3(controleGeral, backStackEntry)
                        }
                        composable(route = "emailSpamRecebido4/{usuario}") {
                                backStackEntry ->
                            SpamReceivedEmail4(controleGeral, backStackEntry)
                        }
                        composable(route = "emailSpamRecebido5/{usuario}") {
                                backStackEntry ->
                            SpamReceivedEmail5(controleGeral, backStackEntry)
                        }
                        composable(route = "agendar_evento/{usuario}") {
                                backStackEntry ->
                            AgendarEventoScreen(controleGeral, backStackEntry)
                        }
                        composable(route = "alterarCadastro/{usuario}"){
                            backStackEntry ->
                            AlteracaoScreen(controleGeral, backStackEntry)

                        }
                        composable(route = "emailRecebido/{emailSender}/{emailSubject}/{emailContent}") { backStackEntry ->
                            val emailSender = backStackEntry.arguments?.getString("emailSender") ?: ""
                            val emailSubject = backStackEntry.arguments?.getString("emailSubject") ?: ""
                            val emailContent = backStackEntry.arguments?.getString("emailContent") ?: ""
                            EmailRecebidoScreen(emailSender, emailSubject, emailContent,)
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
