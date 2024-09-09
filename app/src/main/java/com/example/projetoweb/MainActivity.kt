package com.example.projetoweb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projetoweb.R
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            
            Surface(modifier = Modifier.fillMaxSize()) {
                DiceGameScreen()
            }
        }
    }
}

@Composable
fun DiceGameScreen() {
    var dice1 by remember { mutableStateOf(1) }
    var dice2 by remember { mutableStateOf(1) }
    var totalGames by remember { mutableStateOf(0) }
    var totalWins by remember { mutableStateOf(0) }
    var message by remember { mutableStateOf("Press 'Roll' to play") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            DiceImage(dice1)
            Spacer(modifier = Modifier.width(16.dp))
            DiceImage(dice2)
        }

        Spacer(modifier = Modifier.height(24.dp))

        
        Text(text = message, style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        
        Button(onClick = {
            dice1 = Random.nextInt(1, 7)
            dice2 = Random.nextInt(1, 7)
            totalGames++

            val sum = dice1 + dice2
            if (sum == 7 || sum == 11) {
                totalWins++
                message = "Você Ganhou!"
            } else {
                message = "Você Perdeu!"
            }
        }) {
            Text(text = "Jogar")
        }

        Spacer(modifier = Modifier.height(24.dp))

        
        ScoreDisplay(totalGames = totalGames, totalWins = totalWins)
    }
}

@Composable
fun DiceImage(number: Int) {
    val imageResource = when (number) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = "Dado $number",
        modifier = Modifier.size(100.dp)
    )
}

@Composable
fun ScoreDisplay(totalGames: Int, totalWins: Int) {
    val winPercentage = if (totalGames > 0) (totalWins.toDouble() / totalGames * 100).toInt() else 0
    Text(
        text = "Vitórias: $totalWins/$totalGames = $winPercentage%",
        style = MaterialTheme.typography.bodyLarge
    )
}

@Preview(showBackground = true)
@Composable
fun DiceGameScreenPreview() {
    
    DiceGameScreen()
}
