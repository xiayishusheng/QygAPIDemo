package qyg.apidemo.sqlite3;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonSQLiteOpenHelper extends SQLiteOpenHelper {

	/**
	 * 数据库的构造方法，用来定义数据库的名称 数据库的查询结果集 数据库的版本
	 * @param context 上下文
	 */
	public PersonSQLiteOpenHelper(Context context) {
		super(context, "person.db", null, 1);
	}

	public PersonSQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}



	/**
	 * 数据库第一次被创建的时候 调用的方法
	 * @param db 被创建的数据库
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		//初始化数据库表结构
		db.execSQL("create table person(id integer primary key autoincrement,name varchar(20),number varchar(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		super.onDowngrade(db, oldVersion, newVersion);
	}

}
