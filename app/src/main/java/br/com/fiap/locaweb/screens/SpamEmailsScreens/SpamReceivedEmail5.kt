package br.com.fiap.locaweb.screens.SpamEmailsScreens


//import androidx.compose.ui.platform.LocalContext
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import br.com.fiap.locaweb.R
import br.com.fiap.locaweb.database.repository.UsuarioRepository
import br.com.fiap.locaweb.emailsMock.emailSpam5
import br.com.fiap.locaweb.methods.isSpam
import br.com.fiap.locaweb.model.UsuarioModel
import com.google.gson.Gson

/*@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SpamReceivedEmail5() {

    SpamReceivedEmail5(rememberNavController())

}*/

@Composable
fun SpamReceivedEmail5(controleGeral: NavController, backStackEntry: NavBackStackEntry) {


    //val context = LocalContext.current

    var De by remember() {
        mutableStateOf("")
    }
    var Para by remember() {
        mutableStateOf("")
    }
    var Titulo by remember() {
        mutableStateOf("")
    }
    var corpoEmail by remember() {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val usuarioRepository = UsuarioRepository(context)


    val userJson = backStackEntry.arguments?.getString("usuario")

    val gson = Gson()
    val usuario = gson.fromJson(userJson, UsuarioModel::class.java)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
//        .padding(32.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                IconButton(
                    onClick = {
                        val userJson = gson.toJson(usuario)
                        controleGeral.navigate("spam_emails/$userJson")
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
                Spacer(modifier = Modifier.height(15.dp))
                if(isSpam(emailSpam5)){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.spam_icon),
                            contentDescription = "Ícone de alerta",
                            tint = Color.Yellow,
                            modifier = Modifier.size(40.dp),

                            )
                        Text(
                            text = "Spam Alert",
                            color = Color.Yellow
                        )

                    }
                }

                Button(
                    onClick = {
                        val userJson = gson.toJson(usuario)
                        controleGeral.navigate("novoEmail/$userJson")},
                    modifier = Modifier
                        .size(width = 200.dp, height = 40.dp),

                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                )
                {
                    Text(text = "Responder", color = Color.Black)

                }

            }


            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = emailSpam5.sender,
                readOnly = true,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.LightGray,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White

                ),
                placeholder = {
                    Text(text = "Seu E-mail")
                },
                label = {
                    Text(text = "De:")
                },
                trailingIcon = {
                    IconButton(
                        onClick = { },
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_circle),
                            contentDescription = "Ícone adicionar",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = emailSpam5.receiver,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.LightGray,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White
                ),
                placeholder = {
                    Text(text = "E-mail do destinatário")
                },
                label = {
                    Text(text = "para:")
                },
                trailingIcon = {
                    IconButton(
                        onClick = {  },
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_circle),
                            contentDescription = "Ícone adicionar",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = emailSpam5.title,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.LightGray,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White
                ),
                placeholder = {
                    Text(text = "Assunto do E-mail")
                },
                label = {
                    Text(text = "Título: ")
                },
                trailingIcon = {
                    IconButton(
                        onClick = {  },
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_smart_toy_24),
                            contentDescription = "Ícone inteligência artificial",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = emailSpam5.emailContent,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.LightGray,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White
                ),
                placeholder = {
                    Text(text = "Conteúdo do E-mail")
                },
                label = {
                    Text (text = "Escreva... ")

                }


            )
            Spacer(modifier = Modifier.height(3.dp))
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

            }





        }
    }

}
