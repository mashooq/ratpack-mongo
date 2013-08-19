Ratpack project template with Heroku build-pack (including Mongo DB Integration)
--------------------------------------------------------------------------------

The project structure follows the Lazybones template.
To run use ./gradlew run
To deploy to Heroku

Use: 
'''
heroku apps:create myapplication --buildpack http://github.com/kr/heroku-buildpack-inline.git
heroku config:add PATH="/app/.jdk/bin:/usr/local/bin:/usr/bin:/bin"
git push heroku master
'''

Review the following files if you are interested in the custom build pack for Ratpack - inspired by Marco Vermulan's post of this subject:

bin/compile, bin/detect, bin/release
Profile
system.properties
