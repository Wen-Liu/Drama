package com.wenliu.chocolabsexam.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.wenliu.chocolabsexam.object.Drama;

import java.util.List;

@Dao
public interface DramaDao {

    @Query("SELECT * FROM  `drama` ")
    List<DramaEntry> getAll();

//    @Query("SELECT * FROM drama where price >= :price")
//    public List<DramaDatabase> queryByPrice(int price);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDrama(DramaEntry dramaEntry);

    @Delete
    void delete(DramaEntry dramaEntry);

}
