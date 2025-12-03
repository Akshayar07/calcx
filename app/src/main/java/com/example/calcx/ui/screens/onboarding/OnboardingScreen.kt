package com.example.calcx.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.calcx.R.drawable.onboarding_1
import com.example.calcx.R.drawable.onboarding_2
import com.example.calcx.R.drawable.onboarding_3
import com.example.calcx.navigation.Routes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(navController: NavHostController) {
    val pages = listOf(
        OnboardingPage(
            image = onboarding_1,
            title = "Welcome to Calcx",
            description = "Your all-in-one calculator for age, BMI, and discounts.",
            backgroundColor = Color(0xFFFFFFFF),
            imageSize = 400
        ), OnboardingPage(
            title = "Simple & Smart",
            description = "Clean design with fast and accurate results.",
            image = onboarding_2,
            backgroundColor = Color(0xFF98DFD5),
            imageSize = 300,
        ), OnboardingPage(
            title = "Let’s Get Started!",
            description = "Start calculating smarter — now.",
            image = onboarding_3,
            backgroundColor = Color(0xFFFFADC4),
            imageSize = 300,
        )
    )
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pages.size })

    Scaffold { innerPadding ->
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding), state = pagerState
        ) { page ->

            OnboardingPageContent(
                page = pages[page],
                pagerState = pagerState,
                scope = scope,
                pages = pages,
                navController = navController
            )

        }
    }
}


data class OnboardingPage(
    val title: String,
    val description: String,
    val image: Int,
    val backgroundColor: Color,
    val imageSize: Int
)

@Composable
fun OnboardingPageContent(
    page: OnboardingPage,
    pagerState: PagerState,
    scope: CoroutineScope,
    pages: List<OnboardingPage>,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(page.backgroundColor)
            .padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            Text("Petty")
            if (pagerState.currentPage <= pages.size - 1) {
                TextButton(onClick = {
                    if (pagerState.currentPage < pages.size - 1) {
                        scope.launch {
                            pagerState.scrollToPage(pages.size - 1)
                        }
                    }
                }) {
                    Text("Skip", color = Color.Black)
                }
            }

        }
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(page.imageSize.dp),
            painter = painterResource(id = page.image),
            contentDescription = null
        )
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start
        ) {
            Text(
                page.title, style = TextStyle(
                    fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xff4f4f4f)
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                page.description, style = TextStyle(
                    fontSize = 16.sp, color = Color(0xff4f4f4f)
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedIconButton(
                colors = IconButtonDefaults.outlinedIconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color(0xff4f4f4f)
                ),
                onClick = {
                    if (pagerState.currentPage < pages.size - 1) {
                        scope.launch {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        navController.navigate(Routes.LANDING) {
                            popUpTo(Routes.ONBOARDING_SCREEN) {
                                inclusive = true
                            }
                        }

                    }
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos, contentDescription = "Back"

                )
            }
        }

    }
}


