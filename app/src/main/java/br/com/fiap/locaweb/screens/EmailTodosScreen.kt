package br.com.fiap.locaweb.screens

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
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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

@Composable
fun EmailScreen(controleGeral: NavController, backStackEntry: NavBackStackEntry) {
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
            // Barra de pesquisa com a navegação de voltar
            SearchBar(controleGeral, backStackEntry)

            // Título da lista de emails com ícone
            TitleWithIcon()

            // Lista de emails
            EmailList(controleGeral, backStackEntry)

            // Botão "Novo"
            NewEmailButton(controleGeral, backStackEntry)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(navController: NavController, backStackEntry: NavBackStackEntry) {
    var searchText by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val usuarioRepository = UsuarioRepository(context)


    val userJson = backStackEntry.arguments?.getString("usuario")

    val gson = Gson()
    val usuario = gson.fromJson(userJson, UsuarioModel::class.java)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray, shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 16.dp, vertical = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    val userJson = gson.toJson(usuario)
                    navController.navigate("Categorias/$userJson")
                },
                modifier = Modifier.size(30.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.keyboard_return_24),
                    contentDescription = "Ícone voltar",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp),
                )
            }

            // SearchBar corrigida com parâmetros corretos
            SearchBar(
                query = searchText,
                onQueryChange = { searchText = it },
                onSearch = { /* Lógica de pesquisa */ },
                active = isActive,
                onActiveChange = { isActive = it },
                placeholder = { Text("Pesquisar emails", color = Color.Gray) },
                colors = SearchBarDefaults.colors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.weight(1f),
                content = { /* pode ser deixado vazio ou adicionar ícones de sugestão de pesquisa */ }
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
fun TitleWithIcon() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_inbox_24),
            contentDescription = "Ícone de Email",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = "Todos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun EmailList(navController: NavController, backStackEntry: NavBackStackEntry) {
    val emails = listOf(
        Email("David Moura", "Challenge 1º semestre", "Conteúdo do email"),
        Email("Linkedin", "Recebeu Convite de...", "Conteúdo do email"),
        Email("Banco do Brasil", "Extrato mensal", "Conteúdo do email"),
        Email("Instagram", "Convite para Grupo", "Conteúdo do email"),
        Email("Alerta de Vagas", "FIAP", "Conteúdo do email")
    )

    LazyColumn(
        modifier = Modifier
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(emails) { email ->
            EmailItem(email, navController, backStackEntry)
        }
    }
}

@Composable
fun EmailItem(email: Email, navController: NavController, backStackEntry: NavBackStackEntry) {
    // Mapa que associa o nome do remetente ao ícone correspondente
    val iconesPorRemetente = mapOf(
        "David Moura" to R.drawable.twotone_work_outline_24,
        "Linkedin" to R.drawable.twotone_group_24,
        "Banco do Brasil" to R.drawable.baseline_account_balance_24,
        "Instagram" to R.drawable.twotone_group_24,
        "Alerta de Vagas" to R.drawable.baseline_notifications_none_24
    )

    //ícone correspondente ao remetente
    val icone = iconesPorRemetente[email.name] ?: R.drawable.baseline_person_24

    val context = LocalContext.current
    val usuarioRepository = UsuarioRepository(context)


    val userJson = backStackEntry.arguments?.getString("usuario")

    val gson = Gson()
    val usuario = gson.fromJson(userJson, UsuarioModel::class.java)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
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
fun EmailItem1(email: Email, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                // Ao clicar, navega para a tela de detalhes do e-mail
                navController.navigate("emailRecebido/${email.name}/${email.subject}/${email.content}")
            },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp)
    ) {
        // Conteúdo do item de e-mail
            }
}
@Composable
fun NewEmailButton(controleGeral: NavController, backStackEntry: NavBackStackEntry) {

    val userJson = backStackEntry.arguments?.getString("usuario")

    val gson = Gson()
    val usuario = gson.fromJson(userJson, UsuarioModel::class.java)

    Button(
        onClick = {
            val userJson = gson.toJson(usuario)
            controleGeral.navigate("novoEmail/$userJson") },
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

data class Email(val name: String, val subject: String, val content: String)

/*@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EmailScreenPreview() {
    EmailScreen(rememberNavController())
}*/
