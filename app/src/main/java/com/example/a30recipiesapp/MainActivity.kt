package com.example.a30recipiesapp


import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.a30recipiesapp.ui.theme._30RecipiesAppTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


fun loadRecipes(context: Context): List<Recipe> {
    val jsonString = context.assets.open("recipes.json")
        .bufferedReader()
        .use { it.readText() }

    val listType = object: TypeToken<List<Recipe>>() {}.type
    return Gson().fromJson(jsonString, listType)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeList(recipes: List<Recipe>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Печенье на каждый день",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            itemsIndexed(recipes) { index, recipe ->
                RecipeCard(recipe, index + 1)
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe, day: Int) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Центрируем все дочерние элементы
        ) {
            Text(
                text = "День $day",
                style = TextStyle(
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                ),
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            Text(
                text = recipe.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textAlign = TextAlign.Center
            )

            Image(
                painter = rememberAsyncImagePainter(model = recipe.imageUrl),
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Text(
                text = recipe.description,
                style = MaterialTheme.typography.bodyLarge.copy(
                    textAlign = TextAlign.Justify
                ),
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

data class Recipe(
    val title: String,
    val description: String,
    val imageUrl: String,
    val day: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recipes = loadRecipes(this)
        enableEdgeToEdge()
        setContent {
            _30RecipiesAppTheme(darkTheme = isSystemInDarkTheme(), dynamicColor = false) {
                RecipeList(recipes)
            }
        }
    }
}
