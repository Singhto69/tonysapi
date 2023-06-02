package com.dao

import com.data.MissionTemplate
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

object DatabaseFactory {
    private val jdbcURL = "jdbc:mysql://singhto-db.c0lrdscqey9d.eu-north-1.rds.amazonaws.com:3306/singhtodb"
    private val username = "chad"
    private val password = "123"

    fun selectMissionsByUserId(userName: String?): MutableList<MissionTemplate> {
        val list = mutableListOf<MissionTemplate>()
        val connection = connect()
        if (connection != null && userName != null) {
            var uid: Int = 0

            val statement = connection.createStatement()
            val query = "SELECT id FROM users WHERE username = '$userName'"
            val resultSet: ResultSet = statement.executeQuery(query)

            while (resultSet.next()) {
                uid = resultSet.getInt("id")
            }

            val query2 = "SELECT * FROM missions WHERE userid = '$uid'"
            val resultSet2: ResultSet = statement.executeQuery(query2)

            while (resultSet2.next()) {
                list.add(
                    MissionTemplate(
                        resultSet2.getInt("id"),
                        resultSet2.getString("title"),
                        resultSet2.getString("location"),
                        resultSet2.getString("description"),
                        resultSet2.getString("date"),
                        resultSet2.getString("timeStart"),
                        resultSet2.getString("timeStop"),
                        resultSet2.getInt("userid")
                    )
                )
            }

            resultSet.close()
            resultSet2.close()
            statement.close()
            connection.close()
            return list
        }
        return list

    }


    private fun connect(): Connection? {
        var connection: Connection? = null
        try {
            Class.forName("com.mysql.cj.jdbc.Driver")
            // Use the connection for database operations
            connection = DriverManager.getConnection(jdbcURL, username, password)
            return connection

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return connection
    }

    fun init() {

    }


    /*fun test(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver")
            // Use the connection for database operations
            val connection: Connection = DriverManager.getConnection(jdbcURL, username, password)
            val statement = connection.createStatement()
            val query = "SELECT * FROM users"

            val resultSet: ResultSet = statement.executeQuery(query)

            while (resultSet.next()) {
                val id = resultSet.getInt("id")
                val username = resultSet.getString("username")
                val password = resultSet.getString("password")
                println("ID: $id, Name: $username, Age: $password")
            }

            resultSet.close()
            statement.close()
            connection.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/

}