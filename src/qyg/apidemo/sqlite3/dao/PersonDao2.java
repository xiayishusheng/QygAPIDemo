package qyg.apidemo.sqlite3.dao;

import java.util.ArrayList;
import java.util.List;

import qyg.apidemo.sqlite3.PersonSQLiteOpenHelper;
import qyg.apidemo.sqlite3.bean.Person;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonDao2 {

	private PersonSQLiteOpenHelper helper;

	//在构造方法里完成helper的初始化
	public PersonDao2(Context context) {
		helper = new PersonSQLiteOpenHelper(context);
	}
	
	/**
	 * 添加一条记录
	 * @param name 姓名
	 * @param number 电话
	 */
	public long insert(String name, String number) {
		SQLiteDatabase db = helper.getWritableDatabase();
		//db.execSQL("insert into person (name,number) values (?, ?)", new Object[]{name, number});
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("number", number);
		long id = db.insert("person", null, values);
		db.close();
		return id;
	}
	
	/**
	 * 查询结果是否存在
	 * @param name 姓名
	 * @return true:存在,false:不存在
	 */
	public boolean select(String name) {
		SQLiteDatabase db = helper.getReadableDatabase();
		//Cursor cursor = db.rawQuery("update person set number='15138006789' where name=?", new String[]{"zhen"});
		Cursor cursor = db.query("person", null, "name=?", new String[]{name}, null, null, null);
		boolean result = cursor.moveToNext();
		cursor.close();
		db.close();
		return result;
	}
	
	/**
	 * 修改一条记录
	 * @param name 要修的人员的姓名
	 * @param newnumber 新的号码
	 */
	public int update(String name, String newnumber) {
		SQLiteDatabase db = helper.getWritableDatabase();
		//db.execSQL("update person set number=? where name=?", new Object[]{name, number});
		ContentValues values = new ContentValues();
		values.put("number", newnumber);
		int number = db.update("person", values, "name=?", new String[]{newnumber});
		db.close();
		return number;
	}
	
	/**
	 * 删除一条记录
	 * @param name 要删除人员的姓名
	 */
	public int delete(String name) {
		SQLiteDatabase db = helper.getWritableDatabase();
		//db.execSQL("delete from person where name=?", new Object[]{name});
		int number = db.delete("person", "name=?", new String[]{name});
		db.close();
		return number;
	}
	
	/**
	 * 查询所有的人员
	 * @return 所有人员列表
	 */
	public List<Person> selectAll() {
		List<Person> listPersons = new ArrayList<Person>();
		SQLiteDatabase db = helper.getReadableDatabase();
		//Cursor cursor = db.rawQuery("select * from person", null);
		Cursor cursor = db.query("person", new String[]{"name", "id", "number"}, null, null, null, null, null);
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String number = cursor.getString(cursor.getColumnIndex("number"));
			Person person = new Person(id, name, number);
			listPersons.add(person);
		}
		db.close();
		return listPersons;
	}
	
}
