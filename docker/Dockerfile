FROM dimajix/flowman:${flowman.version}
MAINTAINER k.kupferschmidt@dimajix.de

# Copy unpacked and filtered artifacts
COPY conf/ ${FLOWMAN_HOME}/conf
COPY plugins/ ${FLOWMAN_HOME}/plugins
COPY flows/ ${FLOWMAN_HOME}/flows
