public void readingDB(){

        String SQL_SELECT_patID = "";
        Cursor cursorRecordings;
        SQLiteDatabase sqLiteDatabase;

        sqLiteDatabase = SQLiteDatabase.openDatabase(Environment.getExternalStorageDirectory().getAbsolutePath() + "/uStethApp/databases/24-08-2021_1056_database.db",
                null, SQLiteDatabase.OPEN_READONLY
                        | SQLiteDatabase.NO_LOCALIZED_COLLATORS
                        | SQLiteDatabase.CREATE_IF_NECESSARY);
        
        if(sqLiteDatabase == null){
            Log.e(TAG, "No database");
        }

        SQL_SELECT_patID = "SELECT * FROM AudioDetails";
        cursorRecordings = sqLiteDatabase.rawQuery(SQL_SELECT_patID,null);

        if (cursorRecordings.moveToFirst()) {

            do {

                Log.e(TAG, "audio_ID: "+
                        cursorRecordings.getString(0) + ", Patient_Id:  " +
                        cursorRecordings.getString(1) + ", server pat id:  " +
                        cursorRecordings.getString(7) + ", server_id: " +
                        cursorRecordings.getString(11)
                        );

            }while(cursorRecordings.moveToNext());

        }

    }
