package com.example.curvedscrollview_jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.curvedscrollview_jetpackcompose.ui.theme.CurvedScrollviewjetPackComposeTheme
import kotlinx.coroutines.launch
import kotlin.math.abs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurvedScrollviewjetPackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    CurvedScroll()
                }
            }
        }
    }
}

@Composable
fun CurvedScroll() {
    val items = listOf(
        "Dog1",
        "Dog2",
        "Dog3",
        "Dog4",
        "Dog5",
        "Dog6",
        "Dog7",
        "Dog8",
        "Dog9"
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .height(55.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Curved Scrollview",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }


        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp), contentAlignment = Alignment.CenterStart
        ) {
            CurvedScrollItem(items.size) { index ->
                Column(
                    modifier = Modifier.wrapContentSize()
                ) {
                    Image(
                        painter = painterResource(
                            when (index) {
                                0 -> R.drawable.a
                                1 -> R.drawable.b
                                2 -> R.drawable.c
                                3 -> R.drawable.d
                                4 -> R.drawable.e
                                5 -> R.drawable.f
                                6 -> R.drawable.g
                                7 -> R.drawable.h
                                8 -> R.drawable.i
                                else -> R.drawable.j
                            }
                        ), contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.padding(5.dp))

                    Text(text = items[index], style = MaterialTheme.typography.h6)
                }
            }
        }

    }
}


@Composable
fun CurvedScrollItem(itemCount: Int, item: @Composable (Int) -> Unit) {

    val scrollState = rememberScrollState()
    var size = remember { mutableStateOf(IntSize.Zero) }
    val scope = rememberCoroutineScope()
    val indices = remember { IntArray(itemCount) { 0 } }


    val flingBehaviour = object : FlingBehavior {
        override suspend fun ScrollScope.performFling(initialVelocity: Float): Float {
            val value = scrollState.value
            indices.minByOrNull { abs(it - value) }?.let {
                scope.launch {
                    scrollState.animateScrollTo(it)
                }
            }
            return initialVelocity
        }
    }


}













