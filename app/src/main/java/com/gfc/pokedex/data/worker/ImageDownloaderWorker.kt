package com.gfc.pokedex.data.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import coil3.imageLoader
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.toBitmap
import com.gfc.pokedex.data.POKEMON_ID_PLACEHOLDER
import com.gfc.pokedex.data.POKEMON_IMAGE_LOCAL_FILENAME
import com.gfc.pokedex.data.POKEMON_IMAGE_URL
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.io.File

@HiltWorker
class ImageDownloadWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val pokemonId = inputData.getInt("pokemonId", -1)

        if (pokemonId == -1) {
            return Result.failure()
        }

        val imageUrl = POKEMON_IMAGE_URL.replace(POKEMON_ID_PLACEHOLDER, pokemonId.toString())
        return try {
            val request = ImageRequest.Builder(applicationContext)
                .data(imageUrl)
                .build()

            val result = applicationContext.imageLoader.execute(request)
            if (result is SuccessResult) {
                val bitmap = result.image.toBitmap()
                val file = File(
                    applicationContext.filesDir,
                    POKEMON_IMAGE_LOCAL_FILENAME.replace(
                        POKEMON_ID_PLACEHOLDER,
                        pokemonId.toString()
                    )
                )
                Log.e("PATH", file.absolutePath)
                file.outputStream().use { output ->
                    bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, output)
                }
                Result.success()
            } else {
                Result.failure()
            }
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
