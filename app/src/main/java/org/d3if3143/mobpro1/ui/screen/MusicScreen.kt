package org.d3if3143.mobpro1.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3143.mobpro1.R
import org.d3if3143.mobpro1.database.MusicDb
import org.d3if3143.mobpro1.ui.theme.Mobpro1Theme
import org.d3if3143.mobpro1.util.ViewModelFactory

const val KEY_ID_MUSIC = "idMusic"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicScreen(navController: NavHostController, id: Long? = null) {

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    if (id == null)
                        Text(text = stringResource(id = R.string.tambah_musik))
                    else
                        Text(text = stringResource(id = R.string.edit_musik))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { padding ->
        FormMusic(
            modifier = Modifier.padding(padding), navController, id
        )
    }
}

@Composable
fun FormMusic(
    modifier: Modifier,
    navController: NavHostController,
    id: Long? = null
) {
    val context = LocalContext.current
    val db = MusicDb.getinstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: DetailViewModel = viewModel(factory = factory)

    var showDialog by remember { mutableStateOf(false) }

    var judul by remember { mutableStateOf("") }
    var nama by remember { mutableStateOf("") }
    var tahun by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }

    LaunchedEffect(true) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getMusic(id) ?: return@LaunchedEffect
        judul = data.judul
        nama = data.nama
        tahun = data.tahun
        genre = data.genre
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = judul,
            onValueChange = { judul = it },
            label = { Text(text = stringResource(R.string.judul)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nama,
            onValueChange = { nama = it },
            label = { Text(text = stringResource(R.string.isi_nama)) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = tahun,
            onValueChange = { tahun = it },
            label = { Text(text = stringResource(R.string.isi_tahun)) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            val radioOptions = listOf(
                "POP",
                "ROCK",
                "R&B",
                "INDIE",
                "EDM",
                "JAZZ",
                "RHYTHM AND BLUES"
            )
            radioOptions.forEach { text ->
                ClassOption(
                    label = text,
                    isSelected = genre == text,
                    onOptionSelected = { genre = it },
                    modifier = Modifier
                        .selectable(
                            selected = genre == text,
                            onClick = { genre = text },
                            role = Role.RadioButton
                        )
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            if (id != null) {
                DeleteAction { showDialog = true }
                DisplayAlertDialog(
                    openDialog = showDialog,
                    onDismissRequest = { showDialog = false }) {
                    showDialog = false
                    viewModel.delete(id)
                    navController.popBackStack()
                }
            }
            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = {
                if (judul == "" || nama == "" || tahun == "") {
                    Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                    return@Button
                }
                if (id == null) {
                    viewModel.insert(judul, nama, tahun, genre)
                    Toast.makeText(context, R.string.konfirmasi_tambah, Toast.LENGTH_LONG)
                        .show()
                } else {
                    viewModel.update(id, judul, nama, tahun, genre)
                    Toast.makeText(context, R.string.konfirmasi_update, Toast.LENGTH_LONG)
                        .show()
                }
                navController.popBackStack()
            }) {
                Text(text = stringResource(R.string.simpan))
            }
        }
    }
}

@Composable
fun ClassOption(
    label: String,
    isSelected: Boolean,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = isSelected, onClick = { onOptionSelected(label) })
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 1.dp)
        )
    }
}

@Composable
fun DeleteAction(delete: () -> Unit) {
    Button(onClick = { delete() }) {
        Text(text = stringResource(R.string.hapus))
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MusicScreenPreview() {
    Mobpro1Theme {
        MusicScreen(rememberNavController())
    }
}