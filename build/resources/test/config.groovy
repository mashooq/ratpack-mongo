import org.ratpackframework.groovy.config.Config


(this as Config).with {

	routing.with {
		reloadable = true

		// Uncomment this to allow for statically compiled Groovy files.
//		routing.staticallyCompile = true
	}

}

app {
    mongo {
        host = 'ds037468.mongolab.com'
        port = 37468
        db = 'ratpack'
        username = 'ratpack'
        password = 'ratpack'
    }
}
