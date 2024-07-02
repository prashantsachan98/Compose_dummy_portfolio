package com.example.myporfolio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myporfolio.ui.theme.MyPorfolioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyPorfolioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Portfolio(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

fun getProjectList(): List<Project> {
    return listOf(
        Project("Project 1", "Description 1"),
        Project("Project 2", "Description 2"),
        Project("Project 3", "Description 3")
    )
}

data class Project(val name: String, val description: String)

@Preview(showBackground = true)
@Composable
fun Portfolio(modifier: Modifier = Modifier) {
    val isOpen = remember { mutableStateOf(false) }
    val projectList = remember { getProjectList() }


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .animateContentSize(),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.background,
        tonalElevation = 12.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.studio),
                contentDescription = "user profile photo",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.background, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Portfolio",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W600)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.studio),
                    contentDescription = "insta",
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = "/ Prashant Sachan",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W600),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { isOpen.value = !isOpen.value }) {
                Text(text = "My projects")
            }
            AnimatedVisibility(
                visible = isOpen.value,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                LazyColumn {
                    items(projectList) { project ->
                        ProjectItem(project)
                    }
                }
            }
        }
    }
}

@Composable
fun ProjectItem(project: Project) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.studio),
                contentDescription = "project image",
                modifier = Modifier.size(40.dp)
            )
            Column {
                Text(
                    text = project.name,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W600)
                )
                Text(
                    text = project.description,
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal)
                )
            }
        }
    }
}
