package prac2.core.discount

import org.springframework.beans.factory.annotation.Qualifier
import java.lang.annotation.Inherited
import kotlin.annotation.AnnotationTarget.*

@Target(CLASS,VALUE_PARAMETER,TYPE_PARAMETER,FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
@MustBeDocumented
@Qualifier("MainDiscountPolicy")
annotation class MainDiscountPolicy()
