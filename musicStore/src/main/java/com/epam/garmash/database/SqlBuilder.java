package com.epam.garmash.database;

import java.util.ArrayList;
import java.util.List;

public class SqlBuilder {

    private static final String IN = " IN (";
    private static final String SELECT = "SELECT * FROM ";
    private static final String SELECT_COUNT = "SELECT COUNT(*) FROM ";
    private static final String LIKE = " LIKE ";
    private static final String WHERE = " WHERE ";
    private static final String AND = " AND ";
    private static final String LIMIT = " LIMIT ";
    private static final String ORDER_BY = " ORDER BY ";
    private static final String MORE_THAN_OR_EQUALS = " >= ";
    private static final String LESS_THAN_OR_EQUALS = " <= ";
    private static final String TOKEN = "?";
    private static final String WHITE_SPACE = " ";
    private static final String SEMICOLON = ";";

    private boolean isWhereUsed = false;
    private StringBuilder query = new StringBuilder();

    private List<String> parameters;

    public List<String> getParameters() {
        return parameters;
    }

    public SqlBuilder() {
        this.parameters = new ArrayList<>();
    }

    public SqlBuilder selectFrom(String tableName) {
        parameters.clear();
        query.setLength(0);
        query.append(SELECT).append(tableName);
        return this;
    }

    public SqlBuilder selectCountFrom(String tableName) {
        parameters.clear();
        query.setLength(0);
        query.append(SELECT_COUNT).append(tableName);
        return this;
    }


    public SqlBuilder whereOrAnd(String whereClause) {
        query.append(isWhereUsed ? AND : WHERE).append(whereClause);
        isWhereUsed = true;
        return this;
    }

    public SqlBuilder like(String andClause) {
        query.append(LIKE).append("\'%").append(andClause).append("%\'");
        return this;
    }

    public SqlBuilder orderBy(String fieldName, String order) {
        query.append(ORDER_BY).append(fieldName).append(WHITE_SPACE)
                .append(order);
        return this;
    }

    public SqlBuilder limitFromTo(int from, int to) {
        query.append(LIMIT).append(from)
                .append(",").append(to);
        return this;
    }

    public SqlBuilder in(List<String> values) {
        query.append(IN);
        for (int i = 0; i < values.size(); i++) {
            parameters.add(values.get(i));
            query.append(TOKEN);
            if (i != values.size() - 1) {
                query.append(",");
            }
        }
        query.append(")");
        return this;
    }

    public SqlBuilder moreOrEquals(String val) {
        parameters.add(val);
        query.append(MORE_THAN_OR_EQUALS).append(TOKEN);
        return this;
    }

    public SqlBuilder lessOrEquals(String val) {
        parameters.add(val);
        query.append(LESS_THAN_OR_EQUALS).append(TOKEN);
        return this;
    }


    public String build() {
        isWhereUsed = false;
        return query.append(SEMICOLON).toString();
    }

}