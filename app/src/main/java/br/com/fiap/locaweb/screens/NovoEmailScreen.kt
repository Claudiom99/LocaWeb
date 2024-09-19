package br.com.fiap.locaweb.screens

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.Image
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import br.com.fiap.locaweb.R
import br.com.fiap.locaweb.methods.Style
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
    val styles = Style(context)
    val wallpaper = styles.wallpaper()
    val solidColor = styles.solidColors()


    var De by remember() { mutableStateOf("") }
    var Para by remember() { mutableStateOf("") }
    var Titulo by remember(){ mutableStateOf("") }
    var ConteudoEmail by remember(){ mutableStateOf("") }

    // Estado para controlar o foco dos campos de entrada
    var isFocusedDe by remember { mutableStateOf(false) }
    var isFocusedPara by remember { mutableStateOf(false) }
    var isFocusedTitulo by remember { mutableStateOf(false) }
    var isFocusedConteudoEmail by remember { mutableStateOf(false) }

    // Cor da linha inferior dos campos de entrada quando focados ou não
    val lineColorDe = styles.textfieldAndIconsFocus(isFocusedDe)
    val lineColorPara = styles.textfieldAndIconsFocus(isFocusedPara)
    val lineColorTitulo = styles.textfieldAndIconsFocus(isFocusedTitulo)
    val lineColorConteudoEmail = styles.textfieldAndIconsFocus(isFocusedConteudoEmail)

    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Image(
            painter = painterResource(id = wallpaper),
            contentDescription = "Fundo escuro com pedras",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
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
                    painter = painterResource(id = R.drawable.icon_return_button),
                    contentDescription = "Ícone voltar",
                    modifier = Modifier.size(30.dp),
                    tint = solidColor

                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = De,
                onValueChange = {
                    De = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        isFocusedDe = focusState.isFocused
                    }
                ,
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = lineColorDe,
                    unfocusedBorderColor = lineColorDe),
                textStyle = TextStyle(
                    color = styles.solidColors()
                ),
                placeholder = {
                    Text(text = "Seu E-mail")
                },
                label = {
                    Text (
                        text = "De:",
                        color = lineColorDe
                        )
                },
                trailingIcon = {
                    IconButton(
                        onClick = { vibratePhone(context) },
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_circle),
                            contentDescription = "Ícone adicionar",
                            tint = solidColor,
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
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        isFocusedPara = focusState.isFocused
                    }
                ,
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = lineColorPara,
                    unfocusedBorderColor = lineColorPara
                ),
                textStyle = TextStyle(
                    color = styles.solidColors()
                ),
                placeholder = {
                    Text(text = "E-mail do destinatário")
                },
                label = {
                    Text (
                        text = "para:",
                        color = lineColorPara
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = { vibratePhone(context) },
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_circle),
                            contentDescription = "Ícone adicionar",
                            tint = solidColor,
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
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        isFocusedTitulo = focusState.isFocused
                    }
                ,
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = lineColorTitulo,
                    unfocusedBorderColor = lineColorTitulo
                ),
                textStyle = TextStyle(
                    color = styles.solidColors()
                ),
                placeholder = {
                    Text(text = "Assunto do E-mail")
                },
                label = {
                    Text (
                        text = "Título: ",
                        color = lineColorTitulo
                        )
                },
                trailingIcon = {
                    IconButton(
                        onClick = { vibratePhone(context) },
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_auto_awesome_24),
                            contentDescription = "Ícone inteligência artificial",
                            tint = solidColor,
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
                    .onFocusChanged { focusState ->
                        isFocusedConteudoEmail = focusState.isFocused
                    }
                    .height(350.dp),
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = lineColorConteudoEmail,
                    unfocusedBorderColor = lineColorConteudoEmail
                ),
                textStyle = TextStyle(
                    color = styles.solidColors()
                ),
                placeholder = {
                    Text(text = "Conteúdo do E-mail")
                },
                label = {
                    Text (
                        text = "Escreva... ",
                        color = lineColorConteudoEmail
                        )
                },
            )

            Spacer(modifier = Modifier.height(28.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { vibratePhone(context) },
                colors = ButtonDefaults.buttonColors(Color(0xffFF1E1E)),
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
                    tint = styles.textButtonColor()

                )
                Text(
                    text = "Opções",
                    fontSize = 20.sp,
                    color = styles.textButtonColor(),
                    modifier = Modifier.padding(start = 13.dp)
                )
            }

            Button(
                onClick = { vibratePhone(context) },
                colors = ButtonDefaults.buttonColors(Color(0xffFF1E1E)),
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
                    tint = styles.textButtonColor()

                )
                Text(
                    text = "Enviar",
                    fontSize = 20.sp,
                    color = styles.textButtonColor(),
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