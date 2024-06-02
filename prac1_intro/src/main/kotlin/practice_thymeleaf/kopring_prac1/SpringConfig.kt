package practice_thymeleaf.kopring_prac1

import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import practice_thymeleaf.kopring_prac1.repository.*
import practice_thymeleaf.kopring_prac1.service.MemberService
import javax.sql.DataSource

@Configuration
class SpringConfig(private val em: EntityManager) {

    @Autowired
    private lateinit var dataSource: DataSource

    @Bean
    fun memberService(): MemberService {
        return MemberService(MemberRepo())
    }

    @Bean
    fun MemberRepo(): MemberRepo {
//        return MemoryMemberRepo()
//        return JdbcMemberRepo(dataSource)
//        return JdbcTemplateMemberRepo(dataSource)
        return JpaMemberRepo(em)
    }
}