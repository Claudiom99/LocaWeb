package br.com.fiap.monitoramentodetemperatura.screens


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
//import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.monitoramentodetemperatura.R

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EmailRecebidoScreenPreview() {

    EmailRecebidoScreen(rememberNavController())

}

@Composable
fun EmailRecebidoScreen(controleGeral: NavController) {


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

                        controleGeral.navigate("Categorias")
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

                Button(
                    onClick = {controleGeral.navigate("novoEmail")},
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
                value = "email@exemplo.com",
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
                value = "emaildestinatario@exemplo.com",
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
                value = "Esse é um titulo de exemplo.",
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
                value = "Conteudo de Email.\nEsse é um exemplo de texto onde deve ficar o conteúdo do email recebido.\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Sed convallis laoreet hendrerit. In vel faucibus elit. Sed commodo ante sed tortor fermentum tempus. Fusce ullamcorper scelerisque lectus, id semper quam sollicitudin in. Cras at blandit lacus, vel facilisis arcu. Sed quis gravida sem. Maecenas finibus, tortor in tristique volutpat, ligula urna semper nulla, et egestas sem metus at nibh. In a consectetur erat. Etiam convallis ullamcorper nunc. Quisque ornare nulla at pellentesque imperdiet.\n" +
                        "\n" +
                        "Nullam lectus dolor, tincidunt eu suscipit sed, faucibus sit amet est. Donec eu diam urna. Proin eu augue luctus, tempor lorem nec, ultricies risus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nullam luctus est in vehicula ultrices. Curabitur facilisis, elit in mollis vehicula, elit eros sagittis turpis, quis lobortis erat nibh cursus sapien.",
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
