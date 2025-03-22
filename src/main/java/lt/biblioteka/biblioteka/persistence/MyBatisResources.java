package lt.biblioteka.biblioteka.persistence;

import lt.biblioteka.biblioteka.mybatis.dao.LibraryMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.cdi.SessionFactoryProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class MyBatisResources {

    @Produces
    @ApplicationScoped
    @SessionFactoryProvider
    private SqlSessionFactory produceSqlSessionFactory() {
        try {
            return new SqlSessionFactoryBuilder().build(
                    Resources.getResourceAsStream("MyBatisConfig.xml")
            );
        } catch (IOException e) {
            throw new RuntimeException("MyBatisResources.produceSqlSessionFactory(): ", e);
        }
    }

    @Produces
    @ApplicationScoped
    public LibraryMapper produceLibraryMapper(SqlSessionFactory sqlSessionFactory) {
        // Create a session from the SqlSessionFactory and retrieve the LibraryMapper
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(LibraryMapper.class);
        }
    }
}
