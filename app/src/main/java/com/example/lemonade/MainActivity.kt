package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonStateColumn(imageId: Int, stringId: Int,onClick: () -> Unit, enableRandomValue: Boolean = false) {

    var squeezeDone by remember {
        mutableStateOf(1)
    }

    val squeezeToDo = Random.nextInt(0, 10)


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Button(onClick = {
            if(enableRandomValue) {
                if(squeezeDone == squeezeToDo) {
                    onClick()
                } else {
                    squeezeDone +=1
                }
            } else {
                onClick()
            }
             },
            modifier = Modifier.size(300.dp),
            contentPadding = PaddingValues(0.dp), shape =  CircleShape) {
            Image(painter = painterResource(id = imageId), contentDescription = "Lemon tree" )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = stringResource(id = stringId), fontWeight = FontWeight.Bold, fontSize = 22.sp, textAlign = TextAlign.Center)
    }
}


@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }

    fun resumeProgress() {
        if(currentStep == 4) {
            currentStep = 1
        } else {
            currentStep +=1
        }

    }

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        when (currentStep) {
            1 -> {
                LemonStateColumn(imageId = R.drawable.lemon_tree   , stringId = R.string.lemon_select, ::resumeProgress )
            }
            2 -> {
                LemonStateColumn(imageId = R.drawable.lemon_squeeze, stringId = R.string.lemon_squeeze, ::resumeProgress, true)
            }
            3 -> {
                LemonStateColumn(imageId = R.drawable.lemon_drink, stringId = R.string.glass_of_lemonade,::resumeProgress)
            }
            4 -> {
                LemonStateColumn(imageId = R.drawable.lemon_restart, stringId = R.string.empty_glass, ::resumeProgress)
            }
        }
    }

}