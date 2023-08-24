package com.example.baseandroid.data.db

import androidx.room.*
import com.example.baseandroid.data.model.ItemFile

@Dao
interface DocumentReaderDao {

    @Insert/*(onConflict = OnConflictStrategy.REPLACE)*/
    fun insertAllDocumentInDevice(itemFile: ItemFile)

    //All
    @Query("SELECT * FROM DocumentReaders ORDER BY time DESC")
    fun getAllDocumentInDeviceByDateDESC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders ORDER BY time ASC")
    fun getAllDocumentInDeviceByDateASC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders ORDER BY name DESC")
    fun getAllDocumentInDeviceByNameDESC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders ORDER BY name ASC")
    fun getAllDocumentInDeviceByNameASC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders ORDER BY size DESC")
    fun getAllDocumentInDeviceBySizeDESC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders ORDER BY size ASC")
    fun getAllDocumentInDeviceBySizeASC(): List<ItemFile>


    //@Query("SELECT * FROM Target WHERE type = 'WATER' ORDER BY id DESC")
    //WORD
    @Query("SELECT * FROM DocumentReaders WHERE type = 'WORD' ORDER BY time DESC")
    fun getDocumentWordByDateDESC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'WORD' ORDER BY time ASC")
    fun getDocumentWordByDateASC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'WORD' ORDER BY name DESC")
    fun getDocumentWordByNameDESC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'WORD' ORDER BY name ASC")
    fun getDocumentWordByNameASC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'WORD' ORDER BY size DESC")
    fun getDocumentWordBySizeDESC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'WORD' ORDER BY size ASC")
    fun getDocumentWordBySizeASC(): List<ItemFile>


    //PDF
    //@Query("SELECT * FROM Target WHERE type == 'WATER' ORDER BY id DESC")
    @Query("SELECT * FROM DocumentReaders WHERE type = 'PDF' ORDER BY time DESC")
    fun getDocumentPdfByDateDESC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'PDF' ORDER BY time ASC")
    fun getDocumentPdfByDateASC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'PDF' ORDER BY name DESC")
    fun getDocumentPdfByNameDESC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'PDF' ORDER BY name ASC")
    fun getDocumentPdfByNameASC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'PDF' ORDER BY size DESC")
    fun getDocumentPdfBySizeDESC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'PDF' ORDER BY size ASC")
    fun getDocumentPdfBySizeASC(): List<ItemFile>

    //EXCEL
    @Query("SELECT * FROM DocumentReaders WHERE type = 'EXCEL' ORDER BY time DESC")
    fun getDocumentExcelByDateDESC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'EXCEL' ORDER BY time ASC")
    fun getDocumentExcelByDateASC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'EXCEL' ORDER BY name DESC")
    fun getDocumentExcelByNameDESC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'EXCEL' ORDER BY name ASC")
    fun getDocumentExcelByNameASC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'EXCEL' ORDER BY size DESC")
    fun getDocumentExcelBySizeDESC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'EXCEL' ORDER BY size ASC")
    fun getDocumentExcelBySizeASC(): List<ItemFile>


    //PPT
    @Query("SELECT * FROM DocumentReaders WHERE type = 'PPT' ORDER BY time DESC")
    fun getDocumentPptByDateDESC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'PPT' ORDER BY time ASC")
    fun getDocumentPptByDateASC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'PPT' ORDER BY name DESC")
    fun getDocumentPptByNameDESC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'PPT' ORDER BY name ASC")
    fun getDocumentPptByNameASC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'PPT' ORDER BY size DESC")
    fun getDocumentPptBySizeDESC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'PPT' ORDER BY size ASC")
    fun getDocumentPptBySizeASC(): List<ItemFile>

    //TXT
    @Query("SELECT * FROM DocumentReaders WHERE type = 'TXT' ORDER BY time DESC")
    fun getDocumentTxtByDateDESC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'TXT' ORDER BY time ASC")
    fun getDocumentTxtByDateASC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'TXT' ORDER BY name DESC")
    fun getDocumentTxtByNameDESC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'TXT' ORDER BY name ASC")
    fun getDocumentTxtByNameASC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'TXT' ORDER BY size DESC")
    fun getDocumentTxtBySizeDESC(): List<ItemFile>
    @Query("SELECT * FROM DocumentReaders WHERE type = 'TXT' ORDER BY size ASC")
    fun getDocumentTxtBySizeASC(): List<ItemFile>



    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDocumentToRecent(itemFile: ItemFile)*/

    @Query("SELECT * FROM DocumentReaders WHERE isOpen = 'OPEN' ORDER BY id DESC")
    fun getDocumentRecent(): List<ItemFile>

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavourite(itemFile: ItemFile)*/

    @Query("SELECT * FROM DocumentReaders WHERE isFavourite = 'FAVOURITE' ORDER BY id DESC")
    fun getDocumentFavourite(): List<ItemFile>

    /*@Delete()
    fun deleteFavourite(itemFile: ItemFile)*/

    @Update
    fun updateDocument(itemFile: ItemFile)

    @Delete
    fun deleteDocument(itemFile: ItemFile)

}