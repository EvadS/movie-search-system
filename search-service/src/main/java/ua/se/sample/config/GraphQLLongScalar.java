package ua.se.sample.config;

import graphql.schema.*;

public class GraphQLLongScalar {


    public static final GraphQLScalarType Long = GraphQLScalarType.newScalar()
            .name("Long")
            .description("Custom scalar for handling Long")
            .coercing(


                    new Coercing() {

                @Override
                public Object serialize(Object dataFetcherResult) {
                    return dataFetcherResult.toString();
                }

                @Override
                public Long parseValue(Object input) {
                    if (input == null) {
                        return null;
                    }
                    if (input instanceof Number) {
                        return ((Number) input).longValue();
                    } else {
                        try {
                            return java.lang.Long.valueOf(input.toString());
                        } catch (NumberFormatException e) {
                            throw new CoercingParseLiteralException("Invalid Long literal.", e);
                        }
                    }
                }

                @Override
                public Long parseLiteral(Object input) {
                    if (input == null) {
                        return null;
                    }
                    if (input instanceof Number) {
                        return ((Number) input).longValue();
                    } else {
                        try {
                            return java.lang.Long.valueOf(input.toString());
                        } catch (NumberFormatException e) {
                            throw new CoercingParseLiteralException("Invalid Long literal.", e);
                        }
                    }
                }
            }

            ).build();
}
