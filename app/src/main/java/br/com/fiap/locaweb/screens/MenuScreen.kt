package br.com.fiap.locaweb.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import br.com.fiap.locaweb.classes.SheredUser
import br.com.fiap.locaweb.database.repository.UsuarioRepository
import br.com.fiap.locaweb.methods.ButtonTheme
import br.com.fiap.locaweb.methods.Style
import br.com.fiap.locaweb.model.UsuarioModel
import com.google.gson.Gson


@Composable
fun MenuScreen(controleGeral: NavController, backStackEntry: NavBackStackEntry) {

    val context = LocalContext.current
    val usuarioRepository = UsuarioRepository(context)
    val styles = Style(context)
    val wallpaper = styles.wallpaper()


    val userJson = backStackEntry.arguments?.getString("usuario")

    val gson = Gson()
    val usuario = gson.fromJson(userJson, UsuarioModel::class.java)

    val tema = SheredUser().obterTema(context)
    var isChecked by remember { mutableStateOf(tema == "dark") }

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
                .padding(32.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "MENU",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = styles.solidColors(),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )


            Column(
                modifier = Modifier
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Alterar tema",
                        color = styles.solidColors()
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    ButtonTheme(
                        isChecked = isChecked,
                        onCheckedChange = { novoEstado ->
                            isChecked = novoEstado
                            val novoTema = if (novoEstado) "dark" else "light"
                            SheredUser().salvarTema(context, novoTema)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        controleGeral.navigate("Categorias")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xffFF1E1E)),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(vertical = 1.dp)
                ) {
                    Text(
                        text = "Categorias",
                        fontSize = 20.sp,
                        color = styles.textButtonColor()
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        controleGeral.navigate("agendar_evento")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xffFF1E1E)),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(vertical = 1.dp)
                ) {
                    Text(
                        text = "Calendário",
                        fontSize = 20.sp,
                        color = styles.textButtonColor()
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {

                        val userJson =
                            gson.toJson(usuarioRepository.buscarUsuarioPeloId(usuario.id))
                        controleGeral.navigate("alterarCadastro/$userJson")

                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xffFF1E1E)),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(vertical = 1.dp)
                ) {
                    Text(
                        text = "Alterar Cadastro",
                        fontSize = 20.sp,
                        color = styles.textButtonColor()
                    )
                }
            }
            Button(
                onClick = {
                    controleGeral.navigate("login")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffFF1E1E)),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(vertical = 1.dp)
            ) {
                Text(
                    text = "Sair",
                    fontSize = 20.sp,
                    color = styles.textButtonColor()
                )
            }

        }
    }
}


//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun MenuScreenPreview() {
//    MenuScreen(rememberNavController())
//}
