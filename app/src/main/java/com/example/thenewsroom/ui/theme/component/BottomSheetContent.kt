package com.example.thenewsroom.ui.theme.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.thenewsroom.domain.model.Article

@Composable
fun BottomSheetContent(
    article: Article,
    onReadFullStoryButtonClicked: () -> Unit 
){
    Surface(modifier = Modifier.padding(16.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            article.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            ImageHolder(imageUrl = article.urlToImage)

            Spacer(modifier = Modifier.height(8.dp))
            article.content?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                article.author?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                        )
                }

                article.source.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),

                        )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onReadFullStoryButtonClicked,
                modifier = Modifier.fillMaxWidth()) {
                     Text(text = "Read full story ")
            }
        }
        
    }
}