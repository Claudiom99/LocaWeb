package br.com.fiap.locaweb.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.locaweb.R
import br.com.fiap.locaweb.database.repository.UsuarioRepository
import br.com.fiap.locaweb.methods.Style
import br.com.fiap.locaweb.model.UsuarioModel
import com.google.gson.Gson

@Composable
fun LoginScreen(controleGeral: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var erroEmail by remember { mutableStateOf(false) }
    var erroPassword by remember { mutableStateOf(false) }
    var loginNaoExiste by remember { mutableStateOf(false) }
    var isFocusedEmail by remember { mutableStateOf(false) }
    var isFocusedPassword by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val styles = Style(context)

    val textColor = styles.inputText()
    val lineColor = styles.textfieldAndIconsFocus(isFocusedEmail)
    val lineColor1 = styles.textfieldAndIconsFocus(isFocusedPassword)

    val usuarioRepository = UsuarioRepository(context)

    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                }
            }

    ) {
        Image(
            painter = painterResource(id = styles.wallpaper()),
            contentDescription = "Fundo escuro com pedras",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.locaweb_logo),
                contentDescription = "Logo da LocaWeb",
                modifier = Modifier
                    .size(200.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                BasicTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .onFocusChanged { focusState ->
                            isFocusedEmail = focusState.isFocused
                        },
                    textStyle = TextStyle(color = textColor, fontSize = 18.sp),
                    singleLine = true,
                    cursorBrush = SolidColor(textColor),
                    decorationBox = { innerTextFieldEmail ->
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_account_circle_24),
                                    tint = lineColor,
                                    contentDescription = "ícone de usuário genérico",
                                    modifier = Modifier.padding(
                                        end = 15.dp,
                                        top = 5.dp,
                                        start = 5.dp,
                                        bottom = 5.dp
                                    )
                                )
                                val placeholderEmail = if (!isFocusedEmail && email.isEmpty()) {
                                    "Digite seu E-mail"
                                } else {
                                    ""
                                }
                                Text(
                                    text = placeholderEmail,
                                    fontSize = 18.sp,
                                    color = if (placeholderEmail.isNotEmpty()) lineColor else Color.Transparent
                                )
                                innerTextFieldEmail()
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(lineColor)
                            )
                        }
                    }
                )
                if (erroEmail) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Não esqueça o E-mail",
                        fontSize = 14.sp,
                        color = Color.Red,
                        textAlign = TextAlign.Right
                    )
                } else if (loginNaoExiste) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Login não existe",
                        fontSize = 14.sp,
                        color = Color.Red,
                        textAlign = TextAlign.Right
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                BasicTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .onFocusChanged { focusState ->
                            isFocusedPassword = focusState.isFocused
                        },
                    textStyle = TextStyle(color = textColor, fontSize = 18.sp),
                    singleLine = true,
                    cursorBrush = SolidColor(textColor),
                    decorationBox = { innerTextFieldPassword ->
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.lock_24),
                                    tint = lineColor1,
                                    contentDescription = "ícone de cadeado",
                                    modifier = Modifier.padding(
                                        end = 15.dp,
                                        top = 5.dp,
                                        start = 5.dp,
                                        bottom = 5.dp
                                    )
                                )
                                val placeholderPassword =
                                    if (!isFocusedPassword && password.isEmpty()) {
                                        "Digite sua senha"
                                    } else {
                                        ""
                                    }
                                Text(
                                    text = placeholderPassword,
                                    fontSize = 18.sp,
                                    color = if (placeholderPassword.isNotEmpty()) lineColor1 else Color.Transparent
                                )
                                innerTextFieldPassword()
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(lineColor1)
                            )
                        }
                    }
                )
                if (erroPassword) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Não esqueça a senha",
                        fontSize = 14.sp,
                        color = Color.Red,
                        textAlign = TextAlign.Right
                    )
                } else if (loginNaoExiste) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Login não existe",
                        fontSize = 14.sp,
                        color = Color.Red,
                        textAlign = TextAlign.Right
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))


                val gson = Gson()

                Column {
                    Button(
                        onClick = {
                            if (email.isEmpty() || password.isEmpty()) {
                                erroEmail = true
                                erroPassword = true
                            } else {

                                val usuario: UsuarioModel =
                                    usuarioRepository.buscarPorEmailESenha(email, password)
                                val userJson = gson.toJson(usuario)

                                if (usuario == null) {
                                    loginNaoExiste = true
                                } else if (usuario.email == email && usuario.senha == password) {
                                    controleGeral.navigate("menu/$userJson")
                                    val usuarioId = usuario.id
                                    _salvarIdDoUsuario(context, usuarioId)
                                } else {
                                    loginNaoExiste = true
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(Color(0xffFF1E1E)),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.padding(horizontal = 35.dp)
                    ) {
                        Text(
                            text = "Entrar",
                            modifier = Modifier.width(200.dp),
                            textAlign = TextAlign.Center,
                            color = styles.textButtonColor(),
                            fontSize = 25.sp
                        )
                    }

                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.padding(horizontal = 35.dp)
                    ) {
                        Text(
                            text = "Esqueceu sua senha?",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = styles.solidColors(),
                            fontSize = 20.sp,
                            style = TextStyle(textDecoration = TextDecoration.Underline)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(100.dp))

            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        controleGeral.navigate("cadastre-se")
                    },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.padding(horizontal = 35.dp),
                    border = BorderStroke(1.dp, styles.solidColors())
                ) {
                    Text(
                        text = "Cadastre-se",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = styles.solidColors(),
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

fun _salvarIdDoUsuario(context: Context, id: Long) {
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putLong("user_id", id)
        apply()
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}
