package com.batuhan.cryptocrazy.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.batuhan.cryptocrazy.model.Crypto
import com.batuhan.cryptocrazy.util.Resource
import com.batuhan.cryptocrazy.viewmodel.CryptoDetailViewModel

@Composable
fun CryptoDetailScreen(
    id:String,
    price:String,
    navController: NavController,
    viewModel: CryptoDetailViewModel = hiltViewModel()
) {
    /*

    //Step 1 -> Wrong

       val scope = rememberCoroutineScope()
       //Stateless
       //var cryptoItem : Resource<Crypto> = Resource.Loading()

       //Stateful
       var cryptoItem by remember { mutableStateOf<Resource<Crypto>>(Resource.Loading())}

         scope.launch {
             cryptoItem = viewModel.getCrypto(id)
             println(cryptoItem.data)
         }

      */

    /*
       //Step 2 -> Better

       //Stateless
          //var cryptoItem : Resource<Crypto> = Resource.Loading()

          //Stateful
          var cryptoItem by remember { mutableStateOf<Resource<Crypto>>(Resource.Loading())}

            LaunchedEffect(key1 = Unit) {
                cryptoItem = viewModel.getCrypto(id)
                println(cryptoItem.data)
            }
    */

    //Step 3 -> Best

    val cryptoItem = produceState<Resource<Crypto>>(initialValue = Resource.Loading()) {
        value = viewModel.getCrypto(id)
    }.value


    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFE9F5F2)),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            when(cryptoItem) {

                is Resource.Success -> {
                    val selectedCrypto = cryptoItem.data!![0]
                    Text(text = selectedCrypto.name,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF050505),
                        textAlign = TextAlign.Center
                    )

                    Image(painter = rememberImagePainter(data = selectedCrypto.logo_url),
                        contentDescription = selectedCrypto.name,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(200.dp, 200.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )

                    Text(text = price,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF050505),
                        textAlign = TextAlign.Center

                    )

                }

                is Resource.Error -> {
                    Text(cryptoItem.message!!)
                }

                is Resource.Loading -> {
                    CircularProgressIndicator(color = Color(0xFF0B7B8A))
                }

            }

        }
    }
}