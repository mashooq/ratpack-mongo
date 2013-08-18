package mash

import com.google.inject.AbstractModule

class MongoModule extends AbstractModule {
    private static ConfigObject config

    MongoModule(File cfg) {
        config = new ConfigSlurper().parse(cfg.text).app
    }

    @Override
    protected void configure() {
        String host = config.mongo.host ?: 'localhost'
        int port = config.mongo.port ?: 27017
        String db = config.mongo.db ?: 'ratpack'
        String username = config.mongo.username
        String password = config.mongo.password
        bind(MongoService).toInstance(new MongoService(host, port, username, password, db))
    }
}
