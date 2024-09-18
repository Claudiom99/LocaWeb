package br.com.fiap.locaweb.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.locaweb.R
import br.com.fiap.locaweb.methods.Style

@Composable
fun EmailScreen6(controleGeral: NavController) {

    val context = LocalContext.current
    val styles = Style(context)
    val wallpaper = styles.wallpaper()
    val solidColor = styles.solidColors()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = wallpaper),
            contentDescription = "Fundo escuro com pedras",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Barra de pesquisa com a navegação de voltar implementada
            SearchBar6(controleGeral)

            // Título da lista de emails com ícone
            TitleWithIcon6(solidColor)

            // Lista de emails
            EmailList6(controleGeral)

            // Botão "Novo"
            NewEmailButton6(controleGeral)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar6(navController: NavController) {
    var searchText by remember { mutableStateOf("") }

    TextField(
        value = searchText,
        onValueChange = { searchText = it },
        textStyle = TextStyle(color = Color.White),
        placeholder = { Text("Pesquisar emails", color = Color.White) },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.icon_return_button),
                contentDescription = "Voltar",
                tint = Color.White,
                modifier = Modifier.clickable {
                    navController.navigate("Categorias")
                }
            )
        },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_account_circle_24),
                contentDescription = "Usuário",
                tint = Color.White
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color(0xFF555555), shape = RoundedCornerShape(24.dp)),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun TitleWithIcon6(solidColor: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.spam_icon),
            contentDescription = "Ícone de Spam",
            tint = solidColor,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = "Alertas de Spam",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = solidColor,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun EmailList6(navController: NavController) {
    val emails = listOf(
        EmailItem6("ofertas@oportunidades-seguras.net", "Ganhe dinheiro rápido agora mesmo!", "emailSpamRecebido" ),
        EmailItem6("contato@ganhadoresfelizes.co", "Parabéns, você ganhou um prêmio!", "emailSpamRecebido2"),
        EmailItem6("suporte@seguranca-contas.net", "Atualize suas informações de conta agora!", "emailSpamRecebido3"),
        EmailItem6("vagas@trabalhoemcasa.com", "Ganhe Dinheiro Trabalhando em Casa!", "emailSpamRecebido4"),
        EmailItem6("descontos@ofertas-imperdiveis.com", "Última Chance! Promoção exclusiva com 50% de desconto", "emailSpamRecebido5")
    )

    LazyColumn(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 25.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(emails) { email ->
            EmailItemComponent6(email, navController)
        }
    }
}

@Composable
fun EmailItemComponent6(email: EmailItem6, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .shadow(elevation = 10.dp)
            .clickable {
                navController.navigate(email.route)
            },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.spam_icon),
                contentDescription = "Remetente",
                tint = Color.Black,
                modifier = Modifier.padding(end = 16.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = email.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = email.subject,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Row(
                modifier = Modifier.padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_outlined_flag_24),
                    contentDescription = "Bandeira",
                    tint = Color.Black,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.sharp_delete_24),
                    contentDescription = "Excluir",
                    tint = Color.Black,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }
}

@Composable
fun NewEmailButton6(controleGeral: NavController) {
    Button(
        onClick = { controleGeral.navigate("novoEmail") },
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.cinza)),
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 25.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Novo Email",
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

data class EmailItem6(val name: String, val subject: String, val route: String)

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EmailScreen6Preview() {
    // Inicializando controleGeral com um objeto NavHostController
    EmailScreen6(rememberNavController())
}
