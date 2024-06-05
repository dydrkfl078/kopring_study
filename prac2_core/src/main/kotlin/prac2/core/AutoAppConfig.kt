package prac2.core

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

@Configuration
@ComponentScan (
    basePackages = ["prac2.core"],
//    excludeFilters = { @Filter( type = FilterType.ANNOTATION, classes = Configuration::class)}
    excludeFilters = [Filter( type = FilterType.ANNOTATION, classes = [Configuration::class])]
)
class AutoAppConfig {
}