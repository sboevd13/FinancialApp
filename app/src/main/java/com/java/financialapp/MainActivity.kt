package com.java.financialapp

import android.content.Intent
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            val (
                nameRef, logoRef,
                ageRef, termRef,
                rateRef, amountRef,
                buttonRef
            ) = createRefs()

            // 1. Логотип
            AsyncImage(
                model = offer.logoUrl,
                contentDescription = "Логотип ${offer.name}",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .constrainAs(logoRef) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Fit
            )

            // 2. Название компании
            Text(
                text = offer.name,
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF1C1C1E),
                modifier = Modifier.constrainAs(nameRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(logoRef.start, margin = 12.dp)
                    width = Dimension.fillToConstraints
                }
            )

            // 3. Барьер, чтобы правая колонка не заезжала под логотип
            val startBarrier = createStartBarrier(logoRef, margin = 12.dp)

            // 4. Левая колонка параметров
            InfoItem(
                label = "Возраст:", value = offer.age,
                modifier = Modifier.constrainAs(ageRef) {
                    top.linkTo(nameRef.bottom, margin = 12.dp)
                    start.linkTo(parent.start)
                }
            )
            InfoItem(
                label = "Ставка:", value = offer.rate, modifier = Modifier.constrainAs(rateRef) {
                    top.linkTo(ageRef.bottom, margin = 12.dp)
                    linkTo(parent.start, amountRef.start, endMargin = 5.dp, bias = 0f)
                    width = Dimension.preferredWrapContent
                }
            )


            // 5. Правая колонка параметров
            InfoItem(
                label = "Срок выдачи:", value = offer.term,
                modifier = Modifier.constrainAs(termRef) {
                    top.linkTo(ageRef.top) // Выравниваем по верху с "Возрастом"
                    start.linkTo(ageRef.end) // Начинается правее "Возраста"
                    end.linkTo(startBarrier)
                    width = Dimension.preferredWrapContent
                }
            )
            InfoItem(
                label = "Сумма:", value = offer.amount,
                modifier = Modifier.constrainAs(amountRef) {
                    top.linkTo(rateRef.top) // Выравниваем по верху со "Ставкой"
                    // --- ВОТ ОНО, КЛЮЧЕВОЕ РЕШЕНИЕ ---
                    start.linkTo(termRef.start) // НАЧАТЬ ТАМ ЖЕ, ГДЕ НАЧИНАЕТСЯ "Срок выдачи"
                    width = Dimension.preferredWrapContent
                }
            )

            // 6. Кнопка
            Button(
                onClick = onGetOfferClick,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(52.dp)
                    .constrainAs(buttonRef) {
                        top.linkTo(rateRef.bottom, margin = 28.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
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