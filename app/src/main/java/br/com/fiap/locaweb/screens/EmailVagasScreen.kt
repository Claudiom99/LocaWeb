package br.com.fiap.locaweb.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import br.com.fiap.locaweb.R
import br.com.fiap.locaweb.database.repository.UsuarioRepository
import br.com.fiap.locaweb.model.UsuarioModel
import com.google.gson.Gson
import br.com.fiap.locaweb.methods.Style

@Composable
fun EmailScreen4(controleGeral: NavController, backStackEntry: NavBackStackEntry) {

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
            SearchBar4(controleGeral, backStackEntry)

            // Título da lista de emails com ícone
            TitleWithIcon4(solidColor)

            // Lista de emails
            EmailList4(controleGeral, backStackEntry)

            // Botão "Novo"
            NewEmailButton4(controleGeral, backStackEntry)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar4(navController: NavController, backStackEntry: NavBackStackEntry) {
    var searchText by remember { mutableStateOf("") }
    val context = LocalContext.current
    val usuarioRepository = UsuarioRepository(context)
    val userJson = backStackEntry.arguments?.getString("usuario")
    val gson = Gson()
    val usuario = gson.fromJson(userJson, UsuarioModel::class.java)


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
                     val userJson = gson.toJson(usuario)
                    navController.navigate("Categorias/$userJson")
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
fun TitleWithIcon4(solidColor: Color) {
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
            tint = solidColor,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = "Vagas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = solidColor,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun EmailList4(navController: NavController, backStackEntry: NavBackStackEntry) {
    val emails = listOf(
        EmailItem4("David Moura", "Vaga Dev Junior Remote", "Conteúdo do email"),
        EmailItem4("Claúdio Maciel", "Vaga Dev Junior Remote", "Conteúdo do email"),
        EmailItem4("Rodrigo Inacio", "Vaga Dev Junior Remote", "Conteúdo do email"),
        EmailItem4("Thomas Jefferson", "Vaga Dev Junior Remote", "Conteúdo do email"),
        EmailItem4("5º Membro (Oculto)", "Vaga Dev Junior Remote", "Conteúdo do email")
    )

    LazyColumn(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 25.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(emails) { email ->
            EmailItemComponent(email, navController, backStackEntry)
        }
    }
}

@Composable
fun EmailItemComponent(email: EmailItem4, navController: NavController, backStackEntry: NavBackStackEntry) {

    val context = LocalContext.current
    val usuarioRepository = UsuarioRepository(context)


    val userJson = backStackEntry.arguments?.getString("usuario")

    val gson = Gson()
    val usuario = gson.fromJson(userJson, UsuarioModel::class.java)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .shadow(elevation = 10.dp)
            .clickable {
                val userJson = gson.toJson(usuario)
                navController.navigate("emailRecebido/$userJson")
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
fun NewEmailButton4(controleGeral: NavController, backStackEntry: NavBackStackEntry) {

    val context = LocalContext.current
    val usuarioRepository = UsuarioRepository(context)


    val userJson = backStackEntry.arguments?.getString("usuario")

    val gson = Gson()
    val usuario = gson.fromJson(userJson, UsuarioModel::class.java)

    Button(
        onClick = {
            val userJson = gson.toJson(usuario)
            controleGeral.navigate("novoEmail/$userJson") },
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

data class EmailItem4(val name: String, val subject: String, val content: String)

/*@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EmailScreen4Preview() {
    // Inicializando controleGeral com um objeto NavHostController
    EmailScreen4(rememberNavController())
}*/
