package kopring.prac12_aop.member.annotation

import java.lang.annotation.ElementType
import java.lang.annotation.ElementType.TYPE

@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ClassAop()
