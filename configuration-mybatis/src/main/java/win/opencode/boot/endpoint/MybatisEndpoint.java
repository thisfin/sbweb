package win.opencode.boot.endpoint;

import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wenyou on 2016/10/28.
 */
public class MybatisEndpoint
        extends AbstractEndpoint {
    private MybatisProperties mybatisProperties;
    private SqlSessionFactory sqlSessionFactory;

    public MybatisEndpoint(MybatisProperties mybatisProperties, SqlSessionFactory sqlSessionFactory) {
        super("mybatis", false, true);
        this.mybatisProperties = mybatisProperties;
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Object invoke() {
        List<Map<String, Object>> result = new LinkedList<>();

        Configuration configuration = this.sqlSessionFactory.getConfiguration();
        List<MappedStatement> mappedStatements = new LinkedList<>();
        configuration.getMappedStatements().stream().filter(t -> t instanceof MappedStatement).forEach(t -> mappedStatements.add(t));

        Set<String> statementIds = new HashSet<>();
        for (MappedStatement mappedStatement : mappedStatements) {
            String statementId = mappedStatement.getId();
            if (!statementIds.contains(statementId) && !statementId.contains("!")) {
                Map<String, Object> statement = new HashMap<>();
                statement.put("id", statementId);
                ParameterMap parameterMap = mappedStatement.getParameterMap();
                if (parameterMap != null && parameterMap.getType() != null) {
                    statement.put("parameterMap", parameterMap.getType().getSimpleName());
                }
                List<ResultMap> resultMaps = mappedStatement.getResultMaps();
                if (!resultMaps.isEmpty()) {
                    List<String> resultMapNames = new ArrayList<>();
                    resultMaps.stream().filter(t -> !t.getType().getSimpleName().equalsIgnoreCase("void")).forEach(t -> resultMapNames.add(t.getType().getSimpleName()));
                    if (!resultMapNames.isEmpty()) {
                        statement.put("resultMaps", resultMapNames);
                    }
                }
                String sqlCommandType = mappedStatement.getSqlCommandType().toString();
                if (sqlCommandType != null) {
                    statement.put("commandType", sqlCommandType);
                    if (sqlCommandType.equalsIgnoreCase("INSERT")) {
                        KeyGenerator keygenerator = mappedStatement.getKeyGenerator();
                        if (!(keygenerator instanceof NoKeyGenerator)) {
                            statement.put("keyGenerator", keygenerator.getClass().getSimpleName());
                        }
                    }
                }
                try {
                    statement.put("sql", mappedStatement.getBoundSql(new HashMap<>()).getSql());
                } catch (Exception ignore) {
                }
                if (mappedStatement.getSqlSource() instanceof DynamicSqlSource) {
                    statement.put("dynamic", true);
                }
                result.add(statement);
                statementIds.add(statementId);
            }
        }

        return result;
    }
}
