package org.mixitconf.model.dao

import androidx.room.*
import org.mixitconf.model.entity.Talk

@Dao
interface TalkRepository {
    @Insert
    suspend fun create(talk: Talk)

    @Query("select * from Talk")
    suspend fun readAll(): List<Talk>

    @Query("select * from Talk where speakerIds like '%' || :id || '%'")
    suspend fun readAllBySpeakerId(id: String): List<Talk>

    @Query("select * from Talk where id=:id")
    suspend fun readOne(id: String): Talk?

    @Update
    suspend fun update(talk: Talk)

    @Delete
    suspend fun delete(talk: Talk)

    @Query("delete from Talk")
    suspend fun deleteAll()

    @Query("delete from Talk where id in (:ids)")
    suspend fun deleteAllById(ids: List<String>)
}
