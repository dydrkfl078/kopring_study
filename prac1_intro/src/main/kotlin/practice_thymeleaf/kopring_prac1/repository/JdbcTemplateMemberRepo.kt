package practice_thymeleaf.kopring_prac1.repository

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import practice_thymeleaf.kopring_prac1.domain.Member
import java.sql.ResultSet
import javax.sql.DataSource

class JdbcTemplateMemberRepo(private val dataSource: DataSource): MemberRepo {

    private val jdbcTemplate: JdbcTemplate = JdbcTemplate(dataSource)

    override fun save(member: Member): Member {
        val jdbcInsert = SimpleJdbcInsert(jdbcTemplate)
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id")

        val params = HashMap<String,Any>()
        params["name"] = member.name

        val key = jdbcInsert.executeAndReturnKey(MapSqlParameterSource(params))
        member.id = key.toLong()
        return member
    }

    override fun findById(id: Long): Member? {
        return jdbcTemplate.query("select * from member where id = ?", rowMapper, id).firstOrNull()
    }

    override fun findByName(name: String): Member? {
        return jdbcTemplate.query("select * from member where name = ?", rowMapper, name).firstOrNull()
    }

    override fun findAll(): List<Member> {
        return jdbcTemplate.query("select * from member", rowMapper)
    }

    private val rowMapper = RowMapper<Member> { rs: ResultSet, rowNum: Int ->
        val member = Member(rs.getString("name"))
        member.id = rs.getLong("id")
        member
    }
}