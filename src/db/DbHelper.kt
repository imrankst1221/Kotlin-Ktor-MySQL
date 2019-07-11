package infix.imrankst1221.ads.db

import infix.imrankst1221.ads.model.InfixAds
import com.google.gson.Gson
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun connectDB() {
    val url = "jdbc:mysql://root@localhost/infix_ads_db?useUnicode=true&serverTimezone=UTC"
    val driver = "com.mysql.cj.jdbc.Driver"
    Database.connect(url, driver)
}


object InfixAdsTable : Table("infix_ads") {
    val id = integer("id").primaryKey()
    val name = varchar("name", length = 64)
    val url = varchar("name", length = 200)
    val url_image = varchar("name", length = 200)
    val priority = integer("priority")
}

fun getInfixAds(): String {
    var json: String = ""
    transaction {
        val res = InfixAdsTable.selectAll().orderBy(InfixAdsTable.priority, false).limit(5)
        val infixAds = ArrayList<InfixAds>()
        for (item in res) {
            infixAds.add(InfixAds(item[InfixAdsTable.id],
                item[InfixAdsTable.name],
                item[InfixAdsTable.url],
                item[InfixAdsTable.url_image],
                item[InfixAdsTable.priority]))
        }
        json = Gson().toJson(infixAds)
    }
    return json
}
