package practice_thymeleaf.kopring_prac1.repository

import org.springframework.jdbc.datasource.DataSourceUtils
import org.springframework.jdbc.datasource.DataSourceUtils.getConnection
import practice_thymeleaf.kopring_prac1.domain.Member
import java.sql.*
import javax.sql.DataSource

class JdbcMemberRepo(private val dataSource: DataSource): MemberRepo {

    override fun save(member: Member):Member {
        var sql = "insert into member(name) values(?)"
        var conn : Connection? = null
        var pstmt : PreparedStatement? = null
        var rs : ResultSet? = null
        try {
            conn = getConnection(dataSource)
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            pstmt.setString(1, member.name)
            pstmt.executeUpdate()
            rs = pstmt.generatedKeys
            if (rs.next()) {
                member.id = (rs.getLong(1))
            } else {
                throw SQLException("id 조회 실패")
            }
            return member
        } catch (e:Exception) {
            throw IllegalStateException(e)
        } finally {
            close(conn, pstmt, rs)
        }
    }


    override fun findById(id: Long):Member? {
        var sql = "select * from member where id = ?"
        var conn : Connection? = null
        var pstmt : PreparedStatement? = null
        var rs : ResultSet? = null
        try {
            conn = getConnection(dataSource)
            pstmt = conn.prepareStatement(sql)
            pstmt.setLong(1, id)
            rs = pstmt.executeQuery()
            if(rs.next()) {
                var member = Member(rs.getString("name"))
                member.id = (rs.getLong("id"))
                return member
            } else {
                return null
            }
        } catch ( e: Exception) {
            throw IllegalStateException(e)
        } finally {
            close(conn, pstmt, rs)
        }
    }

    override fun findAll(): List<Member> {
        var sql = "select * from member"
        var conn : Connection? = null
        var pstmt : PreparedStatement? = null
        var rs : ResultSet? = null
        try {
            conn = getConnection(dataSource)
            pstmt = conn.prepareStatement(sql)
            rs = pstmt.executeQuery()
            var members: MutableList<Member> = mutableListOf<Member>()
            while(rs.next()) {
                var member : Member = Member(rs.getString("name"))
                member.id = (rs.getLong("id"))
                members.add(member)
            }
            return members
        } catch ( e: Exception) {
            throw IllegalStateException(e)
        } finally {
            close(conn, pstmt, rs)
        }
    }

    override fun findByName(name: String): Member? {
        var sql = "select * from member where name = ?"
        var conn : Connection? = null
        var pstmt : PreparedStatement? = null
        var rs : ResultSet? = null
        try {
            conn = getConnection(dataSource)
            pstmt = conn.prepareStatement(sql)
            pstmt.setString(1, name)
            rs = pstmt.executeQuery()
            if(rs.next()) {
                var member = Member(rs.getString("name"))
                member.id = (rs.getLong("id"))
                return member
            }
            return null
        } catch (e: Exception) {
            throw IllegalStateException(e)
        } finally {
            close(conn, pstmt, rs)
        }
    }
    private fun getConnection():Connection {
        return DataSourceUtils.getConnection(dataSource)
    }
    private fun close( conn: Connection?,  pstmt: PreparedStatement?,  rs: ResultSet?) {
        try {
            rs?.close()
        } catch (e:SQLException) {
            e.printStackTrace()
        }
        try {
            pstmt?.close()
        } catch (e:SQLException) {
            e.printStackTrace()
        }
        try {
            close(conn)
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    @Throws(SQLException::class)
    private fun close(conn: Connection?) {
        DataSourceUtils.releaseConnection(conn, dataSource)
    }
}
