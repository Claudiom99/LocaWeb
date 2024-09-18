package br.com.fiap.locaweb.screens

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import br.com.fiap.locaweb.R
import br.com.fiap.locaweb.database.repository.UsuarioRepository
import br.com.fiap.locaweb.model.UsuarioModel
import com.google.gson.Gson

@Composable
fun NovoEmailScreen(controleGeral: NavController, backStackEntry: NavBackStackEntry){

    val context = LocalContext.current
    val usuarioRepository = UsuarioRepository(context)


    val userJson = backStackEntry.arguments?.getString("usuario")

    val gson = Gson()
    val usuario = gson.fromJson(userJson, UsuarioModel::class.java)

    fun vibratePhone(context: Context) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(100)
        }
    }


    var De by remember() {
        mutableStateOf("")
    }
    var Para by remember() {
        mutableStateOf("")
    }
    var Titulo by remember(){
        mutableStateOf("")
    }
    var ConteudoEmail by remember(){
        mutableStateOf("")
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF121212))
//        .padding(32.dp)
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            Spacer(modifier = Modifier.height(20.dp))

            IconButton(
                onClick = {
                    vibratePhone(context)
                    val userJson = gson.toJson(usuario)
                    controleGeral.navigate("menu/$userJson")
                          },
                modifier = Modifier.size(30.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.twotone_keyboard_return_24),
                    contentDescription = "Ícone voltar",
                    modifier = Modifier.size(30.dp),

                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = De,
                onValueChange = {
                    De = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.Gray),
                placeholder = {
                    Text(text = "Seu E-mail")
                },
                label = {
                    Text (text = "De:")
                },
                trailingIcon = {
                    IconButton(
                        onClick = { vibratePhone(context) },
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
                value = Para,
                onValueChange = {
                    Para = it
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.Gray
                ),
                placeholder = {
                    Text(text = "E-mail do destinatário")
                },
                label = {
                    Text (text = "para:")
                },
                trailingIcon = {
                    IconButton(
                        onClick = { vibratePhone(context) },
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
                value = Titulo,
                onValueChange = {
                    Titulo = it
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.Gray
                ),
                placeholder = {
                    Text(text = "Assunto do E-mail")
                },
                label = {
                    Text (text = "Título: ")
                },
                trailingIcon = {
                    IconButton(
                        onClick = { vibratePhone(context) },
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

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = ConteudoEmail,
                onValueChange = {
                    ConteudoEmail = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.Gray
                ),
                placeholder = {
                    Text(text = "Conteúdo do E-mail")
                },
                label = {
                    Text (text = "Escreva... ")
                },
            )

            Spacer(modifier = Modifier.height(28.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { vibratePhone(context) },
                colors = ButtonDefaults.buttonColors(Color(0xff343434)),
                modifier = Modifier
                    .width(160.dp)
                    .height(50.dp)
                    .offset(x = 25.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_settings_24),
                    contentDescription = "Ícone de configurações",
                    modifier = Modifier
                        .size(30.dp),
                    tint = Color.LightGray

                )
                Text(
                    text = "Opções",
                    fontSize = 20.sp,
                    color = Color.LightGray,
                    modifier = Modifier.padding(start = 13.dp)
                )
            }

            Button(
                onClick = { vibratePhone(context) },
                colors = ButtonDefaults.buttonColors(Color(0xff343434)),
                modifier = Modifier
                    .width(160.dp)
                    .height(50.dp)
                    .offset(x = 45.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_rocket_launch_24),
                    contentDescription = "Ícone de enviar email",
                    modifier = Modifier
                        .size(30.dp),
                    tint = Color.LightGray

                )
                Text(
                    text = "Enviar",
                    fontSize = 20.sp,
                    color = Color.LightGray,
                    modifier = Modifier.padding(start = 13.dp)
                )
            }
        }









        }
    }

}

/*@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NovoEmailScreenPreview() {

    NovoEmailScreen(rememberNavController())

}*/