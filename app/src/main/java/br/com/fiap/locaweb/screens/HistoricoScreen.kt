package br.com.fiap.locaweb.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.locaweb.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HistoricoScreen(controleGeral:NavController){

    
    Box {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0F0F77))

        ) {
            Column (modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .background(Color(0xFF0F0F77))

                ){
                Text(
                    text = "Registros de Temperaturas",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 31.sp,
                    modifier = Modifier.background(Color(0xFF0F0F77))
                )}

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0F0F77))
                    .padding(10.dp)

            ) {

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    modifier = Modifier
                        .padding(15.dp, 0.dp)
                ){

                    Text(
                        text = "Dia  ",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        fontWeight = FontWeight.Bold
                    )}
                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    //   modifier = Modifier.padding(16.dp, 0.dp)


                ) {
                    Text(
                        text = "Mês",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )}
                Spacer(modifier = Modifier.width(30.dp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color.Black),
                ) {
                    Text(
                        text = "Ano",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )}
            }


            Spacer(modifier = Modifier.height(5.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(650.dp)
                    .background(Color(0xFF78C2C0))
                    .padding(10.dp)
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Row (modifier = Modifier
                    .align(alignment= Alignment.CenterHorizontally)
                ){

                    Column {

                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.width(122.dp)
                        ){

                            Text(text = "DATA")
                        }

                        Button(
                            onClick = { /*TODO*/ },
                        ){
                        Text(text = "18/03/1994")
                        }

                        Button(onClick = { /*TODO*/ }
                        ){
                            Text(text = "19/03/1994")
                        }

                        Button(onClick = { /*TODO*/ }
                        ){
                            Text(text = "20/03/1994")
                        }

                        Button(onClick = { /*TODO*/ }
                        ){
                            Text(text = "21/03/1994")
                        }

                        Button(onClick = { /*TODO*/ }
                        ){
                            Text(text = "22/03/1994")
                        }
                    }
                    Column {

                        Button(
                            onClick = { /*TODO*/ }
                        ){
                            Text(text = "Temperatura")
                        }
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.width(128.dp)
                        ){
                            Text(text = "24,4°C")
                        }
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.width(128.dp)
                        ){
                            Text(text = "26,7°C")
                        }
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.width(128.dp)
                        ){
                            Text(text = "28,2°C")

                        }
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.width(128.dp)
                            ){
                            Text(text = "30,4°C")
                        }
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.width(128.dp)
                            ){
                            Text(text = "28,6°C")

                        }


                    }

                }


            }


        }

        Button(
            onClick = {
             controleGeral.navigate("menu")},
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.cinza)),
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(5.dp)
        ){
            Text(
                text = "Voltar",
                fontSize = 20.sp,
                color = Color.Blue,
                textAlign = TextAlign.Center,
            )}


    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HistoricoScreenPreview() {

    HistoricoScreen(rememberNavController())

}