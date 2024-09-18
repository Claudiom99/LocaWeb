package br.com.fiap.locaweb.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.locaweb.R
import br.com.fiap.locaweb.methods.Style

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorizationScreen(navController: NavController) {
    var pesquisaText by remember { mutableStateOf("") }
    val context = LocalContext.current
    val styles = Style(context)
    val wallpaper = styles.wallpaper()

    Box(
        modifier = Modifier.fillMaxSize()
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
            verticalArrangement = Arrangement.Top
        ) {
            // Barra de Pesquisa
            TextField(
                value = pesquisaText,
                onValueChange = { pesquisaText = it },
                textStyle = TextStyle(color = styles.inputText()),
                placeholder = { Text("Pesquisar Categorias", color = Color.White) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_return_button),
                        contentDescription = "Voltar",
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            navController.navigate("menu")
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

            Spacer(modifier = Modifier.height(32.dp))

            // Botões de Categoria
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ItemDeCategoriaDeEmail(
                        icon = R.drawable.baseline_inbox_24,
                        texto = "Todos",
                        onClick = { navController.navigate("todos") }
                    )
                    ItemDeCategoriaDeEmail(
                        icon = R.drawable.twotone_group_24,
                        texto = "Social",
                        onClick = { navController.navigate("social") }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ItemDeCategoriaDeEmail(
                        icon = R.drawable.twotone_work_outline_24,
                        texto = "Trabalho",
                        onClick = { navController.navigate("trabalho") }
                    )
                    ItemDeCategoriaDeEmail(
                        icon = R.drawable.baseline_account_balance_24,
                        texto = "Bancos",
                        onClick = { navController.navigate("bancos") }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ItemDeCategoriaDeEmail(
                        icon = R.drawable.baseline_notifications_none_24,
                        texto = "Vagas emp",
                        onClick = { navController.navigate("vagas_emp") }
                    )
                    ItemDeCategoriaDeEmail(
                        icon = R.drawable.spam_icon,
                        texto = "Spam",
                        onClick = { navController.navigate("spam_emails") }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botões de Ação
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { /* ação de criar */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF555555)
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_inventory_2_24),
                        contentDescription = "Criar",
                        tint = Color.White,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = "Criar",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = { navController.navigate("NovoEmail") },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF555555)
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_border_color_24),
                        contentDescription = "Novo",
                        tint = Color.White,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = "Novo",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ItemDeCategoriaDeEmail(icon: Int, texto: String, onClick: () -> Unit) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 55.dp)
            .clickable { onClick() }
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = texto,
            tint = Style(context).solidColors(),
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = texto,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Style(context).solidColors()
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CategorizationScreenPreview() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "categorias") {
        composable("categorias") { CategorizationScreen(navController) }
        //composable("menu") { MenuScreen(navController) }
        composable("todos") { EmailScreen(navController) }
        composable("social") { EmailScreen2(navController) }
        composable("trabalho") { EmailScreen1(navController) }
        composable("bancos") { EmailScreen3(navController) }
        composable("vagas_emp") { EmailScreen4(navController) }
        composable("novoEmail") { NovoEmailScreen(navController) }
    }
}
