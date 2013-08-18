package mash

import com.mongodb.DB
import com.mongodb.DBObject
import com.mongodb.MongoClient
import com.mongodb.gridfs.GridFS
import com.mongodb.gridfs.GridFSDBFile
import com.mongodb.gridfs.GridFSInputFile
import com.mongodb.util.JSON

class MongoService {
    private static DB db


    MongoService(String host, int port, String username, String password, String database) {
        MongoClient mongo = new MongoClient(host, port)
        db = mongo.getDB(database)
        if (username) {
            if (!db.authenticate(username, password.toCharArray())) {
                throw new RuntimeException('Could not login to Mongo with provided credentials')
            }
        }
    }

    String collections() {
        db.getCollectionNames().join(', ')
    }

    String find(String collection, String criteria = '{}', String projection = null) {
        def cursor = db.getCollection(collection).find(asDBObject(criteria), asDBObject(projection))
        def items = []
        try {
            while (cursor.hasNext()) {
                items << JSON.serialize(cursor.next())
            }
        } finally {
            cursor.close()
        }
        "[${items.join(',')}]"
    }

    String findOne(String collection, String criteria = '{}') {
        def result = db.getCollection(collection).findOne(asDBObject(criteria))
        result ? JSON.serialize(result) : null
    }

    void save(String collection, String document) {
        db.getCollection(collection).save(asDBObject(document))
    }

    void remove(String collection, String criteria) {
        db.getCollection(collection).remove(asDBObject(criteria))
    }

    void createFile(String collection, byte[] fileContents, String contentType) {
        String filename = UUID.randomUUID().replaceAll('-', '')
        GridFSInputFile file = new GridFS(db, collection).createFile(fileContents)
        file.setFilename(filename)
        file.setContentType(contentType)
        file.save()
        filename
    }

    GridFSDBFile fetchFile(String collection, String filename) {
        new GridFS(db, collection).findOne(filename)
    }

    private DBObject asDBObject(String jsonString) {
        def result
        try {
            result = JSON.parse(jsonString) as DBObject
        } catch (Exception e) {
            e.printStackTrace()
        }
        result
    }
}
