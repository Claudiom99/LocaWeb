package br.com.fiap.monitoramentodetemperatura.screens

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.monitoramentodetemperatura.R

/**
 * Tela de cadastro com campos para nome, e-mail, senha e confirmação de senha.
 *
 * @param controleGeral NavController para navegação entre telas
 */
@Composable
fun CadastrarScreen(
    controleGeral: NavController
) {
    // Estados para armazenar os valores dos campos de entrada
    var nome by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmar by remember { mutableStateOf("") }
    var emailCadastro by remember { mutableStateOf("") }

    // Estado para controlar o foco dos campos de entrada
    var isFocusedNome by remember { mutableStateOf(false) }
    var isFocusedSenha by remember { mutableStateOf(false) }
    var isFocusedConfirmar by remember { mutableStateOf(false) }
    var isFocusedEmailCadastro by remember { mutableStateOf(false) }

    // Estados para controlar a exibição de mensagens de erro
    var erroNome by remember { mutableStateOf(false) }
    var erroSenha by remember { mutableStateOf(false) }
    var erroConfirmar by remember { mutableStateOf(false) }
    var erroEmail by remember { mutableStateOf(false) }
    var senhaDiferente by remember { mutableStateOf(false) }

    // Cor da linha inferior dos campos de entrada quando focados ou não
    val lineColorNome = if (isFocusedNome) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
    val lineColorSenha = if (isFocusedSenha) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
    val lineColorConfirmar = if (isFocusedConfirmar) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
    val lineColorEmailCadastro = if (isFocusedEmailCadastro) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Imagem de fundo
        Image(
            painter = painterResource(id = R.drawable.fundologin),
            contentDescription = "Fundo escuro com pedras",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier.padding(16.dp).fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            // Logotipo
            Image(
                painter = painterResource(id = R.drawable.locaweb_logo),
                contentDescription = "Logotipo",
                modifier = Modifier
                    .padding(end = 15.dp)
                    .size(200.dp) // Tamanho desejado para o ícone do logotipo
                    .align(Alignment.CenterHorizontally)
            )

            // Título da tela
            Text(
                text = "Crie sua conta",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                color = Color.White
            )

            Column(
                modifier = Modifier.fillMaxWidth().padding(32.dp)
            ) {
                // Campo de entrada para o nome
                BasicTextField(
                    value = nome,
                    onValueChange = {
                        nome = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .onFocusChanged { focusState ->
                            isFocusedNome = focusState.isFocused
                        },
                    textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                    singleLine = true,
                    cursorBrush = SolidColor(Color.White),
                    decorationBox = { innerTextFieldNome ->
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.account_circle_24),
                                    contentDescription = "Ícone de usuário genérico",
                                    modifier = Modifier.padding(end = 15.dp)
                                )
                                val placeholderNome = if (!isFocusedNome && nome.isEmpty()) "Digite seu nome" else ""
                                Text(
                                    text = placeholderNome,
                                    fontSize = 18.sp,
                                    color = if (placeholderNome.isNotEmpty()) Color.Gray else Color.Transparent
                                )
                                innerTextFieldNome()
                            }
                            Box(
                                modifier = Modifier.fillMaxWidth().height(1.dp).background(lineColorNome)
                            )
                        }
                    }
                )
                // Mensagem de erro para o campo nome
                if (erroNome) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Por favor, informe seu nome.",
                        fontSize = 14.sp,
                        color = Color.Red,
                        textAlign = TextAlign.End
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Campo de entrada para o email
                BasicTextField(
                    value = emailCadastro,
                    onValueChange = {
                        emailCadastro = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .onFocusChanged { focusState ->
                            isFocusedEmailCadastro = focusState.isFocused
                        },
                    textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                    singleLine = true,
                    cursorBrush = SolidColor(Color.White),
                    decorationBox = { innerTextFieldEmail ->
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.email_24),
                                    contentDescription = "Ícone de e-mail",
                                    modifier = Modifier.padding(end = 15.dp)
                                )
                                val placeholderEmail = if (!isFocusedEmailCadastro && emailCadastro.isEmpty()) "Digite seu e-mail" else ""
                                Text(
                                    text = placeholderEmail,
                                    fontSize = 18.sp,
                                    color = if (placeholderEmail.isNotEmpty()) Color.Gray else Color.Transparent
                                )
                                innerTextFieldEmail()
                            }
                            Box(
                                modifier = Modifier.fillMaxWidth().height(1.dp).background(lineColorEmailCadastro)
                            )
                        }
                    }
                )
                // Mensagem de erro para o campo email
                if (erroEmail) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Por favor, informe seu e-mail.",
                        fontSize = 14.sp,
                        color = Color.Red,
                        textAlign = TextAlign.End
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Campo de entrada para a senha
                BasicTextField(
                    value = senha,
                    onValueChange = {
                        senha = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .onFocusChanged { focusState ->
                            isFocusedSenha = focusState.isFocused
                        },
                    textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                    singleLine = true,
                    cursorBrush = SolidColor(Color.White),
                    decorationBox = { innerTextFieldSenha ->
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.lock_24),
                                    contentDescription = "Ícone de cadeado",
                                    modifier = Modifier.padding(end = 15.dp)
                                )
                                val placeholderSenha = if (!isFocusedSenha && senha.isEmpty()) "Crie sua senha" else ""
                                Text(
                                    text = placeholderSenha,
                                    fontSize = 18.sp,
                                    color = if (placeholderSenha.isNotEmpty()) Color.Gray else Color.Transparent
                                )
                                innerTextFieldSenha()
                            }
                            Box(
                                modifier = Modifier.fillMaxWidth().height(1.dp).background(lineColorSenha)
                            )
                        }
                    }
                )
                // Mensagem de erro para o campo senha
                if (erroSenha) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Por favor, crie sua senha.",
                        fontSize = 14.sp,
                        color = Color.Red,
                        textAlign = TextAlign.End
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Campo de entrada para confirmar a senha
                BasicTextField(
                    value = confirmar,
                    onValueChange = {
                        confirmar = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .onFocusChanged { focusState ->
                            isFocusedConfirmar = focusState.isFocused
                        },
                    textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                    singleLine = true,
                    cursorBrush = SolidColor(Color.White),
                    decorationBox = { innerTextFieldConfirmar ->
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.lock_24),
                                    contentDescription = "Ícone de cadeado",
                                    modifier = Modifier.padding(end = 15.dp)
                                )
                                val placeholderConfirmar = if (!isFocusedConfirmar && confirmar.isEmpty()) "Repita sua senha" else ""
                                Text(
                                    text = placeholderConfirmar,
                                    fontSize = 18.sp,
                                    color = if (placeholderConfirmar.isNotEmpty()) Color.Gray else Color.Transparent
                                )
                                innerTextFieldConfirmar()
                            }
                            Box(
                                modifier = Modifier.fillMaxWidth().height(1.dp).background(lineColorConfirmar)
                            )
                        }
                    }
                )
                // Mensagem de erro para o campo de confirmação de senha
                if (erroConfirmar || senhaDiferente) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "As senhas não correspondem!",
                        fontSize = 14.sp,
                        color = Color.Red,
                        textAlign = TextAlign.End
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Botão de confirmação para criar a conta
                Column {
                    Button(
                        onClick = {
                            // Validação dos campos antes de navegar para a próxima tela
                            when {
                                nome.isEmpty() -> {
                                    erroNome = true
                                }
                                emailCadastro.isEmpty() -> {
                                    erroEmail = true
                                }
                                senha.isEmpty() -> {
                                    erroSenha = true
                                }
                                confirmar.isEmpty() -> {
                                    erroConfirmar = true
                                }
                                senha != confirmar -> {
                                    senhaDiferente = true
                                }
                                else -> {
                                    controleGeral.navigate("menu")
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(Color(0xffFF1E1E)),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.padding(horizontal = 35.dp)
                    ) {
                        Text(
                            text = "Confirmar",
                            modifier = Modifier.width(200.dp),
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontSize = 25.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botão para navegar de volta para a tela de login
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            controleGeral.navigate("login")
                        },
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.padding(horizontal = 35.dp),
                        border = BorderStroke(1.dp, Color.LightGray)
                    ) {
                        Text(
                            text = "Entrar",
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
}

@Preview
@Composable
fun CadastrarScreenPreview() {
    CadastrarScreen(rememberNavController())
}

