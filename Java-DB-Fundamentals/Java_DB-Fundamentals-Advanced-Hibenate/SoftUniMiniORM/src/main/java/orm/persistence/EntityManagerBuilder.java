package orm.persistence;

import orm.Connector;
import orm.strategies.schema_init_strategies.SchemaInitializationStrategy;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityManagerBuilder {
    private Connection connection;
    private String dataSource;
    private SchemaInitializationStrategy strategy;

    public Connector configureConnectionString(){
        return new Connector(this);
    }

    public StrategyConfigurer configureCreationType() throws Exception {
        return new StrategyConfigurer(this);
    }

    public EntityManager build() throws SQLException {
        return new EntityManager(this.connection, dataSource, strategy);
    }

    Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    String getDataSource() {
        return dataSource;
    }

    public EntityManagerBuilder setDataSource(String dataSource) {
        this.dataSource = dataSource;
        return this;
    }
    void setStrategy(SchemaInitializationStrategy strategy) {
        this.strategy = strategy;
    }
}


