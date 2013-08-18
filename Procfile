---
addons:
config_vars:
  JAVA_HOME: ${BUILD_DIR}/.jdk7
  PATH: ${JAVA_HOME}/bin:${PATH}
  JAVA_OPTS: -Dfile.encoding=UTF-8 -server -Xmx512m -XX:+UseCompressedOops
default_process_types:
  web: build/install/ratpack/bin/ratpack
