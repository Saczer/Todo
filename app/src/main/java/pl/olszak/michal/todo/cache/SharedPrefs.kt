package pl.olszak.michal.todo.cache

import android.content.Context
import android.content.SharedPreferences

/**
 * @author molszak
 *         created on 02.02.2018.
 */
class SharedPrefs(context: Context) {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(PREFERENCES_NAME, PREFERENCES_MODE)
    }

    fun get(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue)
    }

    fun get(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun get(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun put(key: String, value: String) {
        sharedPreferences.edit()
                .putString(key, value)
                .apply()
    }

    fun put(key: String, value: Int) {
        sharedPreferences.edit()
                .putInt(key, value)
                .apply()
    }

    fun put(key: String, value: Boolean) {
        sharedPreferences.edit()
                .putBoolean(key, value)
                .apply()
    }

    companion object {
        private const val PREFERENCES_NAME = "pl.olszak.michal.todo.SHARED_PREFS"
        private const val PREFERENCES_MODE = Context.MODE_PRIVATE
    }
}