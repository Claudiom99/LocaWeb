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
fun EmailScreen2(controleGeral: NavController) {

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
            SearchBar2(controleGeral)

            // Título da lista de emails com ícone
            TitleWithIcon2(solidColor)

            // Lista de emails
            EmailList2(controleGeral)

            // Botão "Novo"
            NewEmailButton2(controleGeral)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar2(navController: NavController) {
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
fun TitleWithIcon2(solidColor: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.twotone_group_24),
            contentDescription = "Ícone de Email",
            tint = solidColor,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = "Social",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = solidColor,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun EmailList2(navController: NavController) {
    val emails = listOf(
        Email("David Moura", "Convite Fiap Next ", "Conteúdo do email"),
        Email("Claúdio Maciel", "Convite Fiap Next", "Conteúdo do email"),
        Email("Rodrigo Inacio", "Convite Fiap Next", "Conteúdo do email"),
        Email("Thomas Jefferson", "Convite Fiap Next", "Conteúdo do email"),
        Email("5º Membro (Oculto)", "Convite Fiap Next", "Conteúdo do email")
    )

    LazyColumn(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 25.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(emails) { email ->
            EmailItem2(email, navController)
        }
    }
}

@Composable
fun EmailItem2(email: Email, navController: NavController) {
    // Mapa que associa o nome do remetente ao ícone correspondente
    val iconesPorRemetente = mapOf(
        "David Moura" to R.drawable.twotone_group_24,
        "Claúdio Maciel" to R.drawable.twotone_group_24,
        "Rodrigo Inacio" to R.drawable.twotone_group_24,
        "Thomas Jefferson" to R.drawable.twotone_group_24,
        "5º Membro (Oculto)" to R.drawable.twotone_group_24
    )

    // Obtém o ícone correspondente ao remetente ou usa um ícone padrão
    val icone = iconesPorRemetente[email.name] ?: R.drawable.baseline_person_24

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .shadow(elevation = 10.dp)
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
                painter = painterResource(id = icone),
                contentDescription = "Ícone do Remetente",
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
fun NewEmailButton2(controleGeral: NavController) {
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EmailScreen2Preview() {
    // Inicializando controleGeral com um objeto NavHostController
    EmailScreen2(rememberNavController())
}
