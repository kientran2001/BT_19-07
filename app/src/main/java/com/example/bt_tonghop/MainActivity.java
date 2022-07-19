package com.example.bt_tonghop;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);

        // Open DB

        String path = getFilesDir() + "/mydb";
        try {
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // createTable();

        findViewById(R.id.button_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.beginTransaction();
                try {
//                    Faker faker = new Faker();
//                    String name = faker.name.name();
//                    String phone = faker.phoneNumber.phoneNumber();
//
//                    db.execSQL("insert into tblAMIGO(name, phone) values('" + name + "', '" + phone + "')");

                    ContentValues cv = new ContentValues();

                    cv.put("name", "ABC");
                    cv.put("mssv", "20191111");
                    cv.put("email", "asufhaui@gmail.com");
                    cv.put("ngsinh", "12/09/2000");
                    long ret = db.insert("tblAMIGO", null, cv);
                    Log.v("TAG", "ret = " + ret);

                    cv.put("name", "DEF");
                    cv.put("mssv", "20192222");
                    cv.put("email", "asufhafui@gmail.com");
                    cv.put("ngsinh", "12/09/2001");
                    ret = db.insert("tblAMIGO", null, cv);
                    Log.v("TAG", "ret = " + ret);

                    cv.clear();

                    ret = db.insert("tblAMIGO", null, cv);
                    Log.v("TAG", "ret = " + ret);

                    ret = db.insert("tblAMIGO", "name", cv);
                    Log.v("TAG", "ret = " + ret);

                    db.setTransactionSuccessful();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    db.endTransaction();
                }
            }
        });

        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.beginTransaction();
                try {
//                    db.execSQL("update tblAMIGO set phone='555-0000' where name='AAA'");

                    ContentValues cv = new ContentValues();
                    cv.put("name", "amazonee");

                    long ret = db.update("tblAMIGO", cv, "recID > 9 and recID < 16", null);
                    Log.v("TAG", "ret = " + ret);

                    db.setTransactionSuccessful();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    db.endTransaction();
                }
            }
        });

        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.beginTransaction();
                try {
//                    db.execSQL("delete from tblAMIGO where recID<5");

                    long ret = db.delete("tblAMIGO", "recID > 9 and recID < 16", null);
                    Log.v("TAG", "ret = " + ret);

                    db.setTransactionSuccessful();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    db.endTransaction();
                }
            }
        });

        findViewById(R.id.button_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String sql = "select * from tblAMIGO";
//                Cursor cs = db.rawQuery(sql, null);
                String[] columns = {"recID", "name", "mssv", "email", "ngsinh"};
                Cursor cs = db.query("tblAMIGO", columns,
                        null, null, null, null ,null);

                Log.v("TAG", "# records: " + cs.getCount());

                cs.moveToPosition(-1);
                while (cs.moveToNext()) {
                    int recID = cs.getInt(0);
                    String name = cs.getString(1);
                    String mssv = cs.getString(2);
                    String email = cs.getString(3);
                    String ngsinh = cs.getString(4);

                    Log.v("TAG", recID + " --- " +name + " --- " + mssv + " --- " + email + " --- " + ngsinh);
                }

                ItemAdapter adapter = new ItemAdapter(cs);
                listView.setAdapter(adapter);
            }
        });
    }

    public void createTable() {
        db.beginTransaction();
        try {
            db.execSQL("create table tblAMIGO(" +
                    "recID integer PRIMARY KEY autoincrement," +
                    "name text," +
                    "mssv text," +
                    "email text," +
                    "ngsinh text)");

            db.execSQL("insert into tblAMIGO(name, mssv, email, ngsinh) values('AAA', '555-1111', 'askfj@gmail.com', '12/12/2000')");
            db.execSQL("insert into tblAMIGO(name, mssv, email, ngsinh) values('BBB', '555-2222', 'kjashfiau@gmail.com', '30/04/1999')");
            db.execSQL("insert into tblAMIGO(name, mssv, email, ngsinh) values('CCC', '555-3333', 'asiusu@gmail.com', '10/10/2001')");

            db.setTransactionSuccessful();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}