package kr.co.team.res.config;

import org.hibernate.dialect.MariaDB103Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

/**
 * RegisterSqlFunction
 * @author : jerry
 * @version : 1.0.0
 * 작성일 : 2021/10/18
 * 나스서버 maria 기준 버전 10.3.6
**/
public class RegisterSqlFunction extends MariaDB103Dialect {
    public RegisterSqlFunction() {
        super();
        registerFunction("group_concat", new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
        registerFunction("date_format", new StandardSQLFunction("date_format", StandardBasicTypes.STRING));
        registerFunction("fn_getParentCodeList", new StandardSQLFunction("fn_getParentCodeList", StandardBasicTypes.STRING));
        registerFunction("fn_getMyAtnlcHour", new StandardSQLFunction("fn_getMyAtnlcHour", StandardBasicTypes.BIG_INTEGER));
        //registerFunction("fn_getParentCodeList", new SQLFunctionTemplate(StandardBasicTypes.STRING, "fn_getParentCodeList(?1,?2)"));
    }
}
