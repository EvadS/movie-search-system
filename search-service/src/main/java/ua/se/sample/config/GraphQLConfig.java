package ua.se.sample.config;

import graphql.scalars.ExtendedScalars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQLConfig {
   private static final Logger log = LoggerFactory.getLogger(GraphQLConfig.class);


    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder  -> wiringBuilder
                .scalar(ExtendedScalars.Date)
                .scalar(ExtendedScalars.GraphQLLong)
                ;
    }



//    @Bean
//    GraphQlSourceBuilderCustomizer inspectionCustomizer() {
//        return schemaResourceBuilder -> schemaResourceBuilder.inspectSchemaMappings(reportConsumer -> log.info(reportConsumer.toString()));
//    }
}
