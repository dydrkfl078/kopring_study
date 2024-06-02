package practice_thymeleaf.kopring_prac1.repository

import jakarta.persistence.Entity
import jakarta.persistence.EntityManager
import practice_thymeleaf.kopring_prac1.domain.Member

class JpaMemberRepo(private val em: EntityManager): MemberRepo {

    override fun save(member: Member): Member {
        em.persist(member)
        return member
    }

    override fun findById(id: Long): Member? {
        return em.find(Member::class.java,id)
    }

    override fun findByName(name: String): Member? {
        return em.createQuery("select m from Member m where m.name = :name", Member::class.java)
            .setParameter("name",name)
            .resultList
            .firstOrNull()
    }

    override fun findAll(): List<Member> {
        return em.createQuery("select m from Member m", Member::class.java).resultList
    }
}