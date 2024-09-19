package br.com.fiap.locaweb.screens.SpamEmailsScreens



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import br.com.fiap.locaweb.R
import br.com.fiap.locaweb.database.repository.UsuarioRepository
import br.com.fiap.locaweb.emailsMock.emailSpam1
import br.com.fiap.locaweb.methods.Style
import br.com.fiap.locaweb.methods.isSpam
import br.com.fiap.locaweb.model.UsuarioModel
import com.google.gson.Gson

/*@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SpamReceivedEmail() {
    SpamReceivedEmail(rememberNavController())
    */

@Composable
fun SpamReceivedEmail(controleGeral: NavController, backStackEntry: NavBackStackEntry) {
    val context = LocalContext.current
    val styles = Style(context)
    val wallpaper = styles.wallpaper()
    val context = LocalContext.current
    val usuarioRepository = UsuarioRepository(context)
    val userJson = backStackEntry.arguments?.getString("usuario")
    val gson = Gson()
    val usuario = gson.fromJson(userJson, UsuarioModel::class.java)
   

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
            modifier = Modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        val userJson = gson.toJson(usuario)
                        controleGeral.navigate("spam_emails/$userJson")
                    },
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_return_button),
                        contentDescription = "Ícone voltar",
                        tint = styles.solidColors(),
                        modifier = Modifier.size(30.dp),

                        )
                }
                Spacer(modifier = Modifier.height(15.dp))
                if (isSpam(emailSpam1)) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.spam_icon),
                            contentDescription = "Ícone de alerta",
                            tint = styles.textSpamColor(),
                            modifier = Modifier.size(40.dp),

                            )
                        Text(
                            text = "Spam Alert",
                            fontWeight = FontWeight.Bold,
                            color = styles.textSpamColor()
                        )

                    }
                }
                Button(
                    onClick = {
                        val userJson = gson.toJson(usuario)
                        controleGeral.navigate("novoEmail/$userJson")},
                    modifier = Modifier
                        .size(width = 200.dp, height = 40.dp),

                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xffFF1E1E))
                )
                {
                    Text(text = "Responder", color = styles.textButtonColor())

                }

            }


            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = emailSpam1.sender,
                readOnly = true,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    styles.solidColors(),
                    focusedBorderColor = styles.solidColors(),
                    unfocusedBorderColor = styles.solidColors(),
                ),
                textStyle = TextStyle(
                    color = styles.solidColors()
                ),
                label = {
                    Text(
                        text = "De:",
                        color = styles.solidColors()
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = { },
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_circle),
                            contentDescription = "Ícone adicionar",
                            modifier = Modifier.size(30.dp),
                            tint = styles.solidColors()
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = emailSpam1.receiver,
                readOnly = true,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    styles.solidColors(),
                    focusedBorderColor = styles.solidColors(),
                    unfocusedBorderColor = styles.solidColors(),
                ),
                textStyle = TextStyle(
                    color = styles.solidColors()
                ),
                label = {
                    Text(
                        text = "Para:",
                        color = styles.solidColors()
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = { },
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_circle),
                            contentDescription = "Ícone adicionar",
                            modifier = Modifier.size(30.dp),
                            tint = styles.solidColors()
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = emailSpam1.title,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    styles.solidColors(),
                    focusedBorderColor = styles.solidColors(),
                    unfocusedBorderColor = styles.solidColors(),
                ),
                textStyle = TextStyle(
                    color = styles.solidColors()
                ),
                label = {
                    Text(
                        text = "Título: ",
                        color = styles.solidColors()
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = { },
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_smart_toy_24),
                            contentDescription = "Ícone inteligência artificial",
                            tint = styles.solidColors(),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = emailSpam1.emailContent,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    styles.solidColors(),
                    focusedBorderColor = styles.solidColors(),
                    unfocusedBorderColor = styles.solidColors(),
                ),
                textStyle = TextStyle(
                    color = styles.solidColors()
                ),
                label = {
                    Text(
                        text = "Escreva... ",
                        color = styles.solidColors()
                    )
                }


            )
            Spacer(modifier = Modifier.height(3.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }


        }
    }

}
