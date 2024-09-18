package br.com.fiap.locaweb.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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

    val textColor = Color.White  // Cor do texto
    val lineColor = if (isFocusedEmail) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
    val lineColor1 = if (isFocusedPassword) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)

    //instancia repository
    val context = LocalContext.current
    val usuarioRepository = UsuarioRepository(context)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fundologin),
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
                    .size(200.dp) // Ajuste o tamanho conforme necessário
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
                    cursorBrush = SolidColor(Color.White),
                    decorationBox = { innerTextFieldEmail ->
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_account_circle_24),
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
                                    color = if (placeholderEmail.isNotEmpty()) Color.Gray else Color.Transparent
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
                }
                else if (loginNaoExiste) {
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
                    cursorBrush = SolidColor(Color.White),
                    decorationBox = { innerTextFieldPassword ->
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.lock_24),
                                    contentDescription = "ícone de cadeado",
                                    modifier = Modifier.padding(
                                        end = 15.dp,
                                        top = 5.dp,
                                        start = 5.dp,
                                        bottom = 5.dp
                                    )
                                )
                                val placeholderPassword = if (!isFocusedPassword && password.isEmpty()) {
                                    "Digite sua senha"
                                } else {
                                    ""
                                }
                                Text(
                                    text = placeholderPassword,
                                    fontSize = 18.sp,
                                    color = if (placeholderPassword.isNotEmpty()) Color.Gray else Color.Transparent
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
                }
                else if (loginNaoExiste) {
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

                                val usuario : UsuarioModel = usuarioRepository.buscarPorEmailESenha(email, password)
                                val userJson = gson.toJson(usuario)

                                if(usuario == null){
                                    loginNaoExiste = true
                                }else if(usuario.email.equals(email) && usuario.senha.equals(password)){
                                    controleGeral.navigate("menu/$userJson")
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
                            color = Color.Black,
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
                            color = Color.LightGray,
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
                    border = BorderStroke(1.dp, Color.LightGray)
                ) {
                    Text(
                        text = "Cadastre-se",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.LightGray,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}
