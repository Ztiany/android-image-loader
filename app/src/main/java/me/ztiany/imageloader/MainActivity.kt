package me.ztiany.imageloader

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.android.base.image.ImageLoaderFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.ztiany.base.imageloader.exmpale.R

class MainActivity : AppCompatActivity() {

    private lateinit var mFragment: ImageListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFragment = (supportFragmentManager.findFragmentByTag("ImageListFragment") as? ImageListFragment) ?: ImageListFragment()
    }

    fun clear(view: View) {
        lifecycleScope.launch(Dispatchers.IO) {
            ImageLoaderFactory.getImageLoader().clearDisCache(this@MainActivity)
        }
    }

    fun add(view: View?) {
        supportFragmentManager.commit {
            add(R.id.container, mFragment, "ImageListFragment")
        }
    }

    fun remove(view: View?) {
        supportFragmentManager.commit {
            remove(mFragment)
        }
    }
}
