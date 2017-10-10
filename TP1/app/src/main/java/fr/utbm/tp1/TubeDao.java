package fr.utbm.tp1;

import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface TubeDao {

    @Insert
    void insertAll(Tube... tubes);

    @Update
    void updateAll(Tube... tubes);

    @Query("SELECT * FROM tube")
    List<Tube> getAll();

    @Delete
    void deleteAll(Tube... tubes);
}
