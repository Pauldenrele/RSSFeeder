package com.example.rssfeeder

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import com.example.rssfeeder.Adapter.feedAdapter
import com.example.rssfeeder.Common.HTTPDataHandler
import com.example.rssfeeder.Model.RSSObject
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private val RSS_LINK = "http://rss.nytimes.com/services/xml/rss/nyt/Science.xml"
    private val RSS_2_JSON = " https://api.rss2json.com/v1/api.json?rss_url="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toolbar.title = "NEWS"
        setSupportActionBar(Toolbar)

        val linearLayoutManager = LinearLayoutManager(baseContext , LinearLayoutManager.VERTICAL , false)
        recyclerView.layoutManager = linearLayoutManager

        loadRSS()

    }

    private fun loadRSS() {
        val loadRSSAsync = object :AsyncTask<String,String,String>(){
            internal var mDialog = ProgressDialog(this@MainActivity)
            override fun onPostExecute(result: String?) {
            mDialog.dismiss()
                //Using GSON to convert JSON to Object
                var rssObject:RSSObject
                rssObject =Gson().fromJson<RSSObject>(s , RSSObject::class.java!!)
                val adapter = feedAdapter(rssObject , baseContext)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun doInBackground(vararg params: String): String {
                val result:String
                val http=HTTPDataHandler()
                result = http.getHTTPDataHandler(params[0])
                return  result

            }

            override fun onPreExecute() {
                mDialog.setMessage("Please wait...")
                mDialog.show()
            }
        }

        //Call the aysnc to load Data
        val url_get_data = StringBuilder(RSS_2_JSON)
        url_get_data.append(RSS_LINK)
        loadRSSAsync.execute(url_get_data.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

    }
}
