public void saveLocalDB() {

        try {

            String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HHmm").format(Calendar.getInstance().getTime());

            final String inFileName = "/data/data/com.example.MyApplication/databases/databaseName";
            File dbFile = new File(inFileName);
            
            if(dbFile.exists()) {

                FileInputStream fis = new FileInputStream(dbFile);

                String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/databases/";


                String outFileName = dirPath + timeStamp + "_database.db";

                File dir = new File(dirPath);

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Open the empty db as the output stream
                OutputStream output = new FileOutputStream(outFileName);

                // Transfer bytes from the inputfile to the outputfile
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }

                // Close the streams
                output.flush();
                output.close();
                fis.close();
            }
            else Log.e(TAG, "Database file not found");
        }
        catch (Exception e)
        {
            Log.e(TAG, e.getMessage());
        }
    }
