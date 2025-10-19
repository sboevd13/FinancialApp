package com.java.financialapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.java.financialapp.data.Offer
import com.java.financialapp.ui.OffersViewModel
import com.java.financialapp.ui.theme.FinancialAppTheme
import com.java.financialapp.ui.theme.Inter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinancialAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OffersScreen()
                }
            }
        }
    }
}

@Composable
fun OffersScreen(viewModel: OffersViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(uiState.offers) { offer ->
            OfferCard(
                offer = offer,
                onGetOfferClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(offer.partnerLink))
                    context.startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun OfferCard(offer: Offer, onGetOfferClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)) // ИЗ FIGMA
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            // --- Верхняя секция: Название и Логотип ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = offer.name,
                    modifier = Modifier.weight(1f).padding(top = 4.dp),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFF1C1C1E) // ИЗ FIGMA
                )
                AsyncImage(
                    model = offer.logoUrl,
                    contentDescription = "Логотип ${offer.name}",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            // --- Секция с параметрами (2x2 сетка) ---
            Row(modifier = Modifier.fillMaxWidth()) {
                InfoItem(label = "Возраст:", value = offer.age, modifier = Modifier.weight(1f))
                InfoItem(label = "Срок выдачи:", value = offer.term, modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                InfoItem(label = "Ставка:", value = offer.rate, modifier = Modifier.weight(1f))
                InfoItem(label = "Сумма:", value = offer.amount, modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(28.dp))

            // --- Кнопка ---
            Button(
                onClick = onGetOfferClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1E8449),
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Получить ₽",
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun InfoItem(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF8A8A8E)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF1C1C1E)
        )
    }
}