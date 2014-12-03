package qyg.apidemo.sqlite3;

import java.util.List;

import qyg.apidemo.sqlite3.bean.Person;
import qyg.apidemo.sqlite3.dao.PersonDao;
import qyg.apidemo.sqlite3.dao.PersonDao2;
import utils.Constants;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

public class TestDB extends AndroidTestCase {

	private String TAG = Constants.logPrefix + getClass().getName();
	
	public void testCreateDB() throws Exception {
		PersonSQLiteOpenHelper helper = new PersonSQLiteOpenHelper(getContext());
		SQLiteDatabase db = helper.getWritableDatabase();
	}
	
	public void testInsert() throws Exception {
		PersonDao2 dao = new PersonDao2(getContext());
		dao.insert("gaici", "12345");
	}
	
	public void testDelete() throws Exception {
		PersonDao2 dao = new PersonDao2(getContext());
		dao.delete("gaici");
	}
	
	public void testUpdate() throws Exception {
		PersonDao2 dao = new PersonDao2(getContext());
		dao.update("gaici", "7890");
	}
	
	public void testSelect() throws Exception {
		PersonDao2 dao = new PersonDao2(getContext());
		dao.select("gaici");
	}
	
	public void selectAll() throws Exception {
		PersonDao2 dao = new PersonDao2(getContext());
		List<Person> persons = dao.selectAll();
		for(Person p : persons) {
			Log.i(TAG, p.toString());
		}
	}
}
