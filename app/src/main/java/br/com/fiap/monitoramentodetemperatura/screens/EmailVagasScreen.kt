package br.com.fiap.monitoramentodetemperatura.screens

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.monitoramentodetemperatura.R

@Composable
fun EmailScreen4(controleGeral: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Barra de pesquisa com a navegação de voltar implementada
            SearchBar4(controleGeral)

            // Título da lista de emails com ícone
            TitleWithIcon4()

            // Lista de emails
            EmailList4(controleGeral)

            // Botão "Novo"
            NewEmailButton4(controleGeral)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar4(navController: NavController) {
    var searchText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray, shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.twotone_keyboard_return_24),
                contentDescription = "Voltar",
                tint = Color.White,
                modifier = Modifier.padding(end = 8.dp)
                    .clickable {
                        // Navega de volta ao "menu" ou à tela anterior
                        navController.popBackStack()
                    }
            )
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Pesquisar emails", color = Color.Gray) },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_account_circle_24),
                contentDescription = "Perfil",
                tint = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun TitleWithIcon4() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_notifications_none_24),
            contentDescription = "Ícone de Email",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = "Vagas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun EmailList4(navController: NavController) {
    val emails = listOf(
        EmailItem4("David Moura", "Vaga Dev Junior Remote", "Conteúdo do email"),
        EmailItem4("Claúdio Maciel", "Vaga Dev Junior Remote", "Conteúdo do email"),
        EmailItem4("Rodrigo Inacio", "Vaga Dev Junior Remote", "Conteúdo do email"),
        EmailItem4("Thomas Jefferson", "Vaga Dev Junior Remote", "Conteúdo do email"),
        EmailItem4("5º Membro (Oculto)", "Vaga Dev Junior Remote", "Conteúdo do email")
    )

    LazyColumn(
        modifier = Modifier
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(emails) { email ->
            EmailItemComponent(email, navController)
        }
    }
}

@Composable
fun EmailItemComponent(email: EmailItem4, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                navController.navigate("emailRecebido")
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
                painter = painterResource(id = R.drawable.baseline_notifications_none_24),
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
fun NewEmailButton4(controleGeral: NavController) {
    Button(
        onClick = { controleGeral.navigate("novoEmail") },
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.cinza)),
        modifier = Modifier
            .padding(10.dp)
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

data class EmailItem4(val name: String, val subject: String, val content: String)

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EmailScreen4Preview() {
    // Inicializando controleGeral com um objeto NavHostController
    EmailScreen4(rememberNavController())
}
